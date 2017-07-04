package kr.co.esjee.sjcms.admin.user.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.co.esjee.sjcms.BaseController;
import kr.co.esjee.sjcms.admin.user.service.UserTblService;
import kr.co.esjee.sjcms.admin.user.service.UserUsergroupAppointTblService;
import kr.co.esjee.sjcms.admin.user.service.UserUsergroupAppointTblVO;
import kr.co.esjee.sjcms.admin.user.service.UsergroupTblDefaultVO;
import kr.co.esjee.sjcms.admin.user.service.UsergroupTblService;
import kr.co.esjee.sjcms.admin.user.service.UsergroupTblVO;
import kr.co.esjee.sjcms.common.ValidatorMessenger;
import kr.co.esjee.sjcms.common.util.JsonUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * @Class Name : UsergroupTblController.java
 * @Description : 사용자 그룹 관리
 * @Modification Information
 *
 * @author isjung
 * @since 2012-02-07
 * @version 1.0
 * @see Copyright (C) All right reserved.
 */

@Controller
@SessionAttributes(types = UsergroupTblVO.class)
public class UsergroupTblController extends BaseController {

	@Resource(name = "userTblService")
	private UserTblService userTblService;

	@Resource(name = "usergroupTblService")
	private UsergroupTblService usergroupTblService;

	@Resource(name = "userUsergroupAppointTblService")
	private UserUsergroupAppointTblService userUsergroupAppointTblService;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/**
	 * USERGROUP_TBL 목록을 조회한다. (pageing)
	 *
	 * @param searchVO - 조회할 정보가 담긴 UsergroupTblDefaultVO
	 * @return "/admin/user/UsergroupTblList"
	 * @exception Exception
	 */
	@RequestMapping(value = "/admin/user/UsergroupTblList.do_1")
	public String selectUsergroupTblList(@ModelAttribute("searchVO") UsergroupTblDefaultVO searchVO, ModelMap model) throws Exception {

		getUsergroupTblList(searchVO, model);

		return "/admin/user/UsergroupTblList";
	}

	@RequestMapping(value = "/admin/user/IframeUsergroupTblList.do")
	public String iSelectUsergroupTblList(@ModelAttribute("searchVO") UsergroupTblDefaultVO searchVO, ModelMap model) throws Exception {

		searchVO.setPageUnit(10);
		searchVO.setPageSize(10);
		getUsergroupTblList(searchVO, model);

		return "/admin/user/IframeUsergroupTblList";
	}

	/**
	 * @param searchVO
	 * @param model
	 * @throws Exception
	 */
	private void getUsergroupTblList(UsergroupTblDefaultVO searchVO, ModelMap model) throws Exception {

		/** pageing */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<?> usergroupTblList = usergroupTblService.selectUsergroupTblList(searchVO);
		model.addAttribute("resultList", usergroupTblList);

		int totCnt = usergroupTblService.selectUsergroupTblListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
	}

	@RequestMapping("/admin/user/addUsergroupTblView.do")
	public String addUsergroupTblView(@ModelAttribute("searchVO") UsergroupTblDefaultVO searchVO, Model model) throws Exception {

		model.addAttribute("usergroupTblVO", new UsergroupTblVO());

		return "/admin/user/UsergroupTblRegister";
	}

	/**
	 * 사용자 그룹 추가 Ajax
	 *
	 * @param usergroupTblVO
	 * @param appointTblVO
	 * @param searchVO
	 * @param status
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/admin/user/ajaxAddUsergroupTbl.do")
	public String ajaxAddUserTbl(UsergroupTblVO usergroupTblVO, UserUsergroupAppointTblVO appointTblVO,
			@ModelAttribute("searchVO") UsergroupTblDefaultVO searchVO, SessionStatus status, Model model) throws Exception {

		ValidatorMessenger vm = usergroupTblService.validate(usergroupTblVO, SUBMIT_INSERT);

		model.addAttribute(MODEL_VM_NAME, vm);

		if (vm.isSubmit()) {
			this.addUsergroupTbl(vm, usergroupTblVO, appointTblVO, searchVO, status);
		}

		return AJAX_VM_RESULT;

	}

	@RequestMapping("/admin/user/addUsergroupTbl.do")
	public String addUsergroupTbl(ValidatorMessenger vm, UsergroupTblVO usergroupTblVO, UserUsergroupAppointTblVO appointTblVO,
			@ModelAttribute("searchVO") UsergroupTblDefaultVO searchVO, SessionStatus status) throws Exception {

		usergroupTblVO.setRegActId(super.getUserId());

		usergroupTblService.insertUsergroupTbl(usergroupTblVO);

		userUsergroupAppointTblService.saveUserUsergroupAppointTblByUsergroupId(appointTblVO);

		// status.setComplete();
		return "forward:/admin/user/UsergroupTblList.do";

	}

	@RequestMapping("/admin/user/updateUsergroupTblView.do")
	public String updateUsergroupTblView(@RequestParam("usergroupId") java.lang.String usergroupId, @ModelAttribute("searchVO") UsergroupTblDefaultVO searchVO,
			Model model) throws Exception {

		UsergroupTblVO usergroupTblVO = new UsergroupTblVO();
		usergroupTblVO.setUsergroupId(usergroupId);
		// 변수명은 CoC 에 따라 usergroupTblVO
		model.addAttribute(selectUsergroupTbl(usergroupTblVO, searchVO));

		model.addAttribute("resultListByUserGroupId", userTblService.selectUserTblListByUserGroupId(usergroupId));

		return "/admin/user/UsergroupTblRegister";
	}

	@RequestMapping("/admin/user/selectUsergroupTbl.do")
	public @ModelAttribute("usergroupTblVO") UsergroupTblVO selectUsergroupTbl(UsergroupTblVO usergroupTblVO,
			@ModelAttribute("searchVO") UsergroupTblDefaultVO searchVO) throws Exception {

		return usergroupTblService.selectUsergroupTbl(usergroupTblVO);
	}

	@RequestMapping("/admin/user/ajaxSelectUsergroupTbl.do")
	public @ResponseBody UsergroupTblVO ajaxSelectUsergroupTbl(UsergroupTblVO usergroupTblVO, Model model) throws Exception {

		UsergroupTblVO vo = usergroupTblService.selectUsergroupTbl(usergroupTblVO);
		List<UsergroupTblVO> listuserInfo = new ArrayList<UsergroupTblVO>();
		listuserInfo.add(vo);
		// model.addAttribute(listuserInfo);

		return vo;
	}

	/**
	 * 사용자 그룹 수정 Ajax
	 *
	 * @param usergroupTblVO
	 * @param appointTblVO
	 * @param searchVO
	 * @param status
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/admin/user/ajaxUpdateUsergroupTbl.do")
	public String ajaxUpdateUserTbl(UsergroupTblVO usergroupTblVO, UserUsergroupAppointTblVO appointTblVO,
			@ModelAttribute("searchVO") UsergroupTblDefaultVO searchVO, SessionStatus status, Model model) throws Exception {

		ValidatorMessenger vm = new ValidatorMessenger();
		vm.complete();

		model.addAttribute(MODEL_VM_NAME, vm);

		this.updateUsergroupTbl(usergroupTblVO, appointTblVO, searchVO, status);

		return AJAX_VM_RESULT;

	}

	@RequestMapping("/admin/user/updateUsergroupTbl.do")
	public String updateUsergroupTbl(UsergroupTblVO usergroupTblVO, UserUsergroupAppointTblVO appointTblVO,
			@ModelAttribute("searchVO") UsergroupTblDefaultVO searchVO, SessionStatus status) throws Exception {

		usergroupTblVO.setUpdActId(super.getUserId());
		usergroupTblService.updateUsergroupTbl(usergroupTblVO);

		userUsergroupAppointTblService.saveUserUsergroupAppointTblByUsergroupId(appointTblVO);

		// status.setComplete();
		return "forward:/admin/user/UsergroupTblList.do";

	}

	/**
	 * 사용자 그룹 삭제 Ajax
	 *
	 * @param usergroupTblVO
	 * @param searchVO
	 * @param status
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/admin/user/ajaxDeleteUsergroupTbl.do")
	public String ajaxDeleteUserTbl(UsergroupTblVO usergroupTblVO, @ModelAttribute("searchVO") UsergroupTblDefaultVO searchVO, SessionStatus status, Model model)
			throws Exception {

		ValidatorMessenger vm = usergroupTblService.validate(usergroupTblVO, SUBMIT_DELETE);
		model.addAttribute(MODEL_VM_NAME, vm);

		if (vm.isSubmit()) {
			this.deleteUsergroupTbl(vm, usergroupTblVO, searchVO, status);
		}

		return AJAX_VM_RESULT;
	}

	@RequestMapping("/admin/user/deleteUsergroupTbl.do")
	public String deleteUsergroupTbl(ValidatorMessenger vm, UsergroupTblVO usergroupTblVO, @ModelAttribute("searchVO") UsergroupTblDefaultVO searchVO,
			SessionStatus status) throws Exception {

		usergroupTblVO.setUpdActId(super.getUserId());
		usergroupTblService.deleteUsergroupTbl(usergroupTblVO);

		// status.setComplete();
		return "forward:/admin/user/UsergroupTblList.do";

	}

	@RequestMapping(value = "/admin/user/usergroupTblMappingList.do")
	public String selectUsergroupTblListByUserId(@ModelAttribute("searchVO") UsergroupTblDefaultVO searchVO, @RequestParam("userId") String userId,
			ModelMap model) throws Exception {

		List<?> usergroupTblList = usergroupTblService.selectUsergroupTblListByUserId(userId);
		model.addAttribute("resultListByUserId", usergroupTblList);

		return "/admin/user/UsergroupTblMappingList";
	}

	@RequestMapping(value = "/admin/user/addUserUsergroupAppointTblByUserId.do")
	public String addUserUsergroupAppointTblByUserId(UserUsergroupAppointTblVO appointTblVO, SessionStatus status) throws Exception {

		userUsergroupAppointTblService.saveUserUsergroupAppointTblByUserId(appointTblVO);
		// status.setComplete();

		return "forward:/admin/user/usergroupTblMappingList.do";
	}

	/**
	 * 그룹별 사용자
	 *
	 * @param usergroupId
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/admin/user/UserTblListByUserGroupId.do")
	public String UserTblListByUserGroupId(@RequestParam("usergroupId") java.lang.String usergroupId, Model model) throws Exception {

		UsergroupTblVO usergroupTblVO = new UsergroupTblVO();
		usergroupTblVO.setUsergroupId(usergroupId);
		// 변수명은 CoC 에 따라 usergroupTblVO

		model.addAttribute("resultListByUserGroupId", userTblService.selectUserTblListByUserGroupId(usergroupId));

		return "/admin/user/UserTblListByUserGroupId";
	}

	@RequestMapping(value = "/admin_new/user/userGrpList.do")
	public String userGrpList(@ModelAttribute("searchVO") UsergroupTblDefaultVO searchVO, ModelMap model) throws Exception {

		getUsergroupTblList(searchVO, model);
		try {
			List<Object> list = (List<Object>) model.get("resultList");
			model.addAttribute("userGrpList", JsonUtil.makeJSONArray(list, FLAG_F, null, null).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/admin/user/userGrpSetPopup";
	}

	/**********************************************************************************************************
	 * 추가부분
	 ********************************************************************************************************/
	@RequestMapping(value = "/admin/user/IframeUsergroupPopTblList.do")
	public String iSelectUsergroupPopTblList(@ModelAttribute("searchVO") UsergroupTblDefaultVO searchVO, ModelMap model) throws Exception {

		searchVO.setPageUnit(9);
		searchVO.setPageSize(10);
		getUsergroupTblList(searchVO, model);

		return "/admin/user/IframeUsergroupPopTblList";
	}

	/**
	 * 사용자 그룹 목록 페이지 이동
	 *
	 * @param searchVO - 조회할 정보가 담긴 UsergroupTblDefaultVO
	 * @return "/admin/user/userGrplList"
	 * @exception Exception
	 */
	@RequestMapping(value = "/admin/user/UsergroupTblList.do")
	public String selectUsergroupList(@ModelAttribute("searchVO") UsergroupTblDefaultVO searchVO, ModelMap model, java.lang.String usergroupId)
			throws Exception {

		getUsergroupTblList(searchVO, model);
		JSONArray jArray = new JSONArray();

		List<Object> list = (List) model.get("resultList");
		PaginationInfo paginationInfo = (PaginationInfo) model.get("paginationInfo");
		try {
			jArray = JsonUtil.makeJSONArray(list, FLAG_F, null, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("resultList", jArray);
		model.addAttribute("totalCnt", paginationInfo.getTotalRecordCount());
		model.addAttribute("usergroupId", usergroupId);

		return "/admin/user/userGrpList";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/admin/user/userGrpListAjax.do")
	public @ResponseBody JSONObject userGrpListAjax(UsergroupTblDefaultVO searchVO, ModelMap model) throws Exception {

		getUsergroupTblList(searchVO, model);

		JSONArray jArray = new JSONArray();
		JSONObject jObj = new JSONObject();

		List<Object> list = (List) model.get("resultList");
		PaginationInfo paginationInfo = (PaginationInfo) model.get("paginationInfo");
		try {
			jArray = JsonUtil.makeJSONArray(list, FLAG_F, null, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		jObj.put("resultList", jArray);
		jObj.put("totalCnt", paginationInfo.getTotalRecordCount());

		return jObj;
	}

	@RequestMapping("/admin/user/updateUserGrpView.do")
	public String updateUserGrpView(@RequestParam("usergroupId") java.lang.String usergroupId, @ModelAttribute("searchVO") UsergroupTblDefaultVO searchVO,
			Model model) throws Exception {

		UsergroupTblVO usergroupTblVO = new UsergroupTblVO();
		usergroupTblVO.setUsergroupId(usergroupId);
		// 변수명은 CoC 에 따라 usergroupTblVO
		model.addAttribute(selectUsergroupTbl(usergroupTblVO, searchVO));

		model.addAttribute("resultListByUserGroupId", userTblService.selectUserTblListByUserGroupId(usergroupId));

		return "/admin/user/userGrpDetail";
	}

	@RequestMapping("/admin/user/ajaxUpdateUserGrpView.do")
	public @ResponseBody Map<String, Object> ajaxUpdateUserGrpView(@RequestParam("usergroupId") java.lang.String usergroupId,
			@ModelAttribute("searchVO") UsergroupTblDefaultVO searchVO, ModelMap model) throws Exception {

		UsergroupTblVO usergroupTblVO = new UsergroupTblVO();
		usergroupTblVO.setUsergroupId(usergroupId);

		List<?> list = userTblService.selectUserTblListByUserGroupId(usergroupId);
		JSONArray jArray = new JSONArray();
		try {
			jArray = JsonUtil.makeJSONArray(list, FLAG_F, null, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
		model.addAttribute(usergroupTblVO);
		map.put("userList", jArray);
		map.put("userGrpInfo", selectUsergroupTbl(usergroupTblVO, searchVO));

		return map;
	}

	/**
	 * 시스템관리>시스템관리>사용자그룹관리>상세조회
	 *
	 * @param searchVO UsergroupTblDefaultVO
	 * @param model
	 * @return /admin/user/userGrpDetail
	 * @throws Exception
	 */
	@RequestMapping("/admin/user/addUserGrpView.do")
	public String addUsergroupView(@ModelAttribute("searchVO") UsergroupTblDefaultVO searchVO, ModelMap model) throws Exception {

		model.addAttribute(new UsergroupTblVO());

		return "/admin/user/userGrpDetail";
	}

	/**
	 * 그룹 사용자 설정 팝업
	 *
	 * @param userId - 조회할 정보가 담긴 UserTblDefaultVO
	 * @return "/admin/user/userDetail"
	 * @exception Exception
	 */
	@RequestMapping("/admin/user/userSetPopup.do")
	public String updateUserGrp(@RequestParam("usergroupId") java.lang.String usergroupId, @ModelAttribute("searchVO") UsergroupTblDefaultVO searchVO,
			Model model) throws Exception {

		return "/admin/user/userSetPopup";
	}

	@RequestMapping("/admin/user/ajaxSelectUserTblListByUserGroupIdw.do")
	public @ResponseBody List<?> ajaxselectUserTblListByUserGroupId(@RequestParam("usergroupId") java.lang.String usergroupId,
			@ModelAttribute("searchVO") UsergroupTblDefaultVO searchVO, ModelMap model) throws Exception {

		List<?> usergroupList = userTblService.selectUserTblListByUserGroupId(usergroupId);
		model.addAttribute("usergroupList", JsonUtil.makeJSONArray(usergroupList, FLAG_F, null, null));

		return usergroupList;
	}
}
