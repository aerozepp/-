package kr.co.esjee.sjcms.admin.user.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.co.esjee.sjcms.BaseController;
import kr.co.esjee.sjcms.admin.user.service.UserTblDefaultVO;
import kr.co.esjee.sjcms.admin.user.service.UserTblService;
import kr.co.esjee.sjcms.admin.user.service.UserTblVO;
import kr.co.esjee.sjcms.admin.user.service.UserUsergroupAppointTblService;
import kr.co.esjee.sjcms.admin.user.service.UserUsergroupAppointTblVO;
import kr.co.esjee.sjcms.admin.user.service.UsergroupTblService;
import kr.co.esjee.sjcms.common.ValidatorMessenger;
import kr.co.esjee.sjcms.common.exception.ControllerException;
import kr.co.esjee.sjcms.common.util.JsonUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.context.MessageSource;
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
 * @Class Name : UserTblController.java
 * @Description : 사용자 관리
 * @Modification Information
 *
 * @author isjung
 * @since 2012-02-09
 * @version 1.0
 * @see Copyright (C) All right reserved.
 */

@Controller
@SessionAttributes(types = UserTblVO.class)
public class UserTblController extends BaseController {

	@Resource(name = "userTblService")
	private UserTblService userTblService;

	@Resource(name = "userUsergroupAppointTblService")
	private UserUsergroupAppointTblService userUsergroupAppointTblService;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	@Resource(name = "usergroupTblService")
	private UsergroupTblService usergroupTblService;

	@Resource(name = "messageSource")
	private MessageSource messageSource;

	/**
	 * USER_TBL 목록을 조회한다. (pageing)
	 *
	 * @param searchVO - 조회할 정보가 담긴 UserTblDefaultVO
	 * @return "/admin/user/UserTblList"
	 * @exception Exception
	 */
	@RequestMapping(value = "/admin/user/UserTblList.do_1")
	public String selectUserTblList(@ModelAttribute("searchVO") UserTblDefaultVO searchVO, ModelMap model) throws Exception {

		getUserTblList(searchVO, model);

		return "/admin/user/UserTblList";
	}

	@RequestMapping(value = "/admin/user/IframeUserTblList.do")
	public String iSelectUserTblList(@ModelAttribute("searchVO") UserTblDefaultVO searchVO, ModelMap model) throws Exception {

		searchVO.setPageUnit(9);
		searchVO.setPageSize(10);
		getUserTblList(searchVO, model);

		return "/admin/user/IframeUserTblList";
	}

	/**
	 * @param searchVO
	 * @param model
	 * @throws Exception
	 */
	public void getUserTblList(UserTblDefaultVO searchVO, ModelMap model) throws Exception {
		/** pageing */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<?> userTblList = userTblService.selectUserTblList(searchVO);
		model.addAttribute("resultList", userTblList);

		int totCnt = userTblService.selectUserTblListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
	}

	@RequestMapping("/admin/user/addUserTblView.do")
	public String addUserTblView(@ModelAttribute("searchVO") UserTblDefaultVO searchVO, Model model) throws Exception {

		model.addAttribute("userTblVO", new UserTblVO());

		return "/admin/user/UserTblRegister";
	}

	/**
	 * 사용자 정보 저장 Ajax
	 *
	 * @param userTblVO
	 * @param appointTblVO
	 * @param searchVO
	 * @param status
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/admin/user/ajaxAddUserTbl.do")
	public String ajaxAddUserTbl(UserTblVO userTblVO, UserUsergroupAppointTblVO appointTblVO, @ModelAttribute("searchVO") UserTblDefaultVO searchVO,
			SessionStatus status, Model model) throws Exception {

		ValidatorMessenger vm = userTblService.validate(userTblVO, SUBMIT_INSERT);
		model.addAttribute(MODEL_VM_NAME, vm);

		if (vm.isSubmit()) {
			this.addUserTbl(vm, userTblVO, appointTblVO, searchVO, status);
		}

		return AJAX_VM_RESULT;

	}

	@RequestMapping("/admin/user/addUserTbl.do")
	public String addUserTbl(ValidatorMessenger vm, UserTblVO userTblVO, UserUsergroupAppointTblVO appointTblVO,
			@ModelAttribute("searchVO") UserTblDefaultVO searchVO, SessionStatus status) throws Exception {

		if (vm.isSubmit()) {
			userTblVO.setRegActId(super.getUserId());
			/* 2012.03.15 Oh-jung Cho 추가 */
			userTblService.insertUserAndUsergroupAppoint(userTblVO, appointTblVO);
			// status.setComplete();
		} else {
			throw new ControllerException(vm.toString());
		}

		return "forward:/admin/user/updateUserTblView.do";

	}

	@RequestMapping("/admin/user/updateUserTblView.do")
	public String updateUserTblView(@RequestParam("userId") java.lang.String userId, @ModelAttribute("searchVO") UserTblDefaultVO searchVO, Model model)
			throws Exception {

		UserTblVO userTblVO = new UserTblVO();
		userTblVO.setUserId(userId);
		// 변수명은 CoC 에 따라 userTblVO
		model.addAttribute(selectUserTbl(userTblVO, searchVO));

		/* 2012.03.14 Oh-jung Cho 추가 */
		model.addAttribute("resultListByUserId", usergroupTblService.selectUsergroupTblListByUserId(userId));

		return "/admin/user/UserTblRegister";
	}

	@RequestMapping("/admin/user/selectUserTbl.do")
	public @ModelAttribute("userTblVO") UserTblVO selectUserTbl(UserTblVO userTblVO, @ModelAttribute("searchVO") UserTblDefaultVO searchVO) throws Exception {

		return userTblService.selectUserTbl(userTblVO);
	}

	@RequestMapping("/admin/user/ajaxSelectUserTbl.do")
	public @ResponseBody UserTblVO ajaxSelectUserTbl(UserTblVO userTblVO, Model model) throws Exception {

		UserTblVO vo = userTblService.selectUserTbl(userTblVO);
		// model.addAttribute(listuserInfo);
		return vo;
	}

	/**
	 * 사용자 정보 Update Ajax
	 *
	 * @param userTblVO
	 * @param appointTblVO
	 * @param searchVO
	 * @param status
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/admin/user/ajaxUpdateUserTbl.do")
	public String ajaxUpdateUserTbl(UserTblVO userTblVO, UserUsergroupAppointTblVO appointTblVO, @ModelAttribute("searchVO") UserTblDefaultVO searchVO,
			SessionStatus status, Model model) throws Exception {

		ValidatorMessenger vm = new ValidatorMessenger();
		vm.complete();

		model.addAttribute(MODEL_VM_NAME, vm);

		this.updateUserTbl(userTblVO, appointTblVO, searchVO, status);

		return AJAX_VM_RESULT;

	}

	@RequestMapping("/admin/user/updateUserTbl.do")
	public String updateUserTbl(UserTblVO userTblVO, UserUsergroupAppointTblVO appointTblVO, @ModelAttribute("searchVO") UserTblDefaultVO searchVO,
			SessionStatus status) throws Exception {

		userTblVO.setUpdActId(super.getUserId());

		/* 2012.03.15 Oh-jung Cho 추가 */
		userTblService.updateUserAndUsergroupAppoint(userTblVO, appointTblVO);
		return "forward:/admin/user/updateUserTblView.do";

	}

	/**
	 * 사용자 정보 삭제 Ajax
	 *
	 * @param userTblVO
	 * @param searchVO
	 * @param status
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/admin/user/ajaxDeleteUserTbl.do")
	public String ajaxDeleteUserTbl(UserTblVO userTblVO, @ModelAttribute("searchVO") UserTblDefaultVO searchVO, SessionStatus status, Model model)
			throws Exception {

		ValidatorMessenger vm = userTblService.validate(userTblVO, SUBMIT_DELETE);

		model.addAttribute(MODEL_VM_NAME, vm);

		if (vm.isSubmit()) {
			this.deleteUserTbl(vm, userTblVO, searchVO, status);
		}

		return AJAX_VM_RESULT;

	}

	@RequestMapping("/admin/user/deleteUserTbl.do")
	public String deleteUserTbl(ValidatorMessenger vm, UserTblVO userTblVO, @ModelAttribute("searchVO") UserTblDefaultVO searchVO, SessionStatus status)
			throws Exception {

		userTblVO.setUpdActId(super.getUserId());
		userTblService.deleteUserTbl(userTblVO);
		// status.setComplete();

		return "forward:/admin/user/UserTblList.do";

	}

	@RequestMapping(value = "/admin/user/userTblMappingList.do")
	public String selectUserTblListByUserGroupId(@ModelAttribute("searchVO") UserTblDefaultVO searchVO, @RequestParam("usergroupId") String userGroupId,
			ModelMap model) throws Exception {

		List<?> userTblList = userTblService.selectUserTblListByUserGroupId(userGroupId);
		model.addAttribute("resultListByUserGroupId", userTblList);

		return "/admin/user/UserTblMappingList";

	}

	@RequestMapping(value = "/admin/user/addUserUsergroupAppointTblByUsergroupId.do")
	public String addUserUsergroupAppointTblByUsergroupId(UserUsergroupAppointTblVO appointTblVO, SessionStatus status) throws Exception {

		userUsergroupAppointTblService.saveUserUsergroupAppointTblByUsergroupId(appointTblVO);
		// status.setComplete();

		return "forward:/admin/user/userTblMappingList.do";

	}

	/************************************************************************************************************************
	 * 변경 UI 작업 분
	 ************************************************************************************************************************/

	/**
	 * USER_TBL 페이지 이동 시스템관리>시스템관리>사용자관리
	 *
	 * @param searchVO - 조회할 정보가 담긴 UserTblDefaultVO
	 * @return "/admin/user/UserTblList"
	 * @exception Exception
	 */
	@RequestMapping(value = "/admin/user/UserTblList.do")
	public String selectUserList(@ModelAttribute("searchVO") UserTblDefaultVO searchVO, ModelMap model) throws Exception {

		/** pageing */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<?> userTblList = userTblService.selectUserTblList(searchVO);

		int totCnt = userTblService.selectUserTblListTotCnt(searchVO);

		JSONArray jArray = new JSONArray();

		try {
			jArray = JsonUtil.makeJSONArray(userTblList, FLAG_F, null, null);
		} catch (Exception e) {
			e.printStackTrace();
		}

		model.addAttribute("resultList", jArray);
		model.addAttribute("totalCnt", totCnt);

		return "/admin/user/userList";

	}

	@RequestMapping(method = RequestMethod.POST, value = "/admin/user/userListAjax.do", headers = "Accept=application/xml, application/json")
	public @ResponseBody JSONObject selectUserTblList2(UserTblDefaultVO searchVO, ModelMap model) throws Exception {
		/** pageing */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<?> userTblList = userTblService.selectUserTblList(searchVO);

		int totCnt = userTblService.selectUserTblListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute("paginationInfo", paginationInfo);

		JSONArray jArray = new JSONArray();
		JSONObject jObj = new JSONObject();

		try {
			jArray = JsonUtil.makeJSONArray(userTblList, FLAG_F, null, null);
		} catch (Exception e) {
			e.printStackTrace();
		}

		jObj.put("resultList", jArray);
		jObj.put("pageIndex", paginationInfo.getCurrentPageNo());
		jObj.put("pageUnit", paginationInfo.getRecordCountPerPage());
		jObj.put("pageSize", paginationInfo.getPageSize());
		jObj.put("totalCnt", paginationInfo.getTotalRecordCount());

		return jObj;

	}

	/**
	 * 시스템관리>시스템관리>사용자관리>상세조회
	 *
	 * @param searchVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/admin/user/addUserView.do")
	public String addUserView(@ModelAttribute("searchVO") UserTblDefaultVO searchVO, ModelMap model) throws Exception {

		UserTblVO userTblVO = new UserTblVO();
		if (userTblVO != null) {
			model.addAttribute(new UserTblVO());
		}

		return "/admin/user/userDetail";

	}

	/**
	 * USER_TBL 상세보기
	 *
	 * @param userId - 조회할 정보가 담긴 UserTblDefaultVO
	 * @return "/admin/user/userDetail"
	 * @exception Exception
	 */
	@RequestMapping("/admin/user/updateUserView.do")
	public String updateUserView(java.lang.String userId, @ModelAttribute("searchVO") UserTblDefaultVO searchVO, Model model) throws Exception {

		UserTblVO userTblVO = new UserTblVO();
		userTblVO.setUserId(userId);

		model.addAttribute("resultListByUserId", usergroupTblService.selectUsergroupTblListByUserId(userId));
		model.addAttribute(selectUserTbl(userTblVO, searchVO));

		return "/admin/user/userDetail";

	}

	@RequestMapping("/admin/user/ajaxUpdateUserView.do")
	public @ResponseBody Map<String, Object> ajaxUpdateUserView(java.lang.String userId, @ModelAttribute("searchVO") UserTblDefaultVO searchVO, ModelMap model)
			throws Exception {

		UserTblVO userTblVO = new UserTblVO();
		userTblVO.setUserId(userId);

		List<?> list = usergroupTblService.selectUsergroupTblListByUserId(userId);
		JSONArray jArray = new JSONArray();

		try {
			jArray = JsonUtil.makeJSONArray(list, FLAG_F, null, null);
		} catch (Exception e) {
			e.printStackTrace();
		}

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("userList", jArray);
		model.addAttribute(userTblVO);
		map.put("userGrpInfo", selectUserTbl(userTblVO, searchVO));

		return map;
	}

	/**
	 * USER_TBL 상세보기
	 *
	 * @param userId - 조회할 정보가 담긴 UserTblDefaultVO
	 * @return "/admin/user/userDetail"
	 * @exception Exception
	 */
	@RequestMapping("/admin/user/userGrpSetPopup.do")
	public String updateUserGrp(@RequestParam("userId") java.lang.String userId, @ModelAttribute("searchVO") UserTblDefaultVO searchVO, Model model)
			throws Exception {

		return "/admin/user/userGrpSetPopup";
	}

	@RequestMapping(value = "/admin/user/IframeUserList.do")
	public String iSelectUserList(@ModelAttribute("searchVO") UserTblDefaultVO searchVO, ModelMap model) throws Exception {

		searchVO.setPageUnit(9);
		searchVO.setPageSize(10);

		this.getUserTblList(searchVO, model);

		return "/admin/user/IframeUserTblList";
	}

	@RequestMapping(value = "/admin/user/userDefaultSet.do")
	public String userDefaultSet(@ModelAttribute("searchVO") UserTblDefaultVO searchVO, ModelMap model) throws Exception {

		return "/admin/user/UserDefaultSet";
	}
}
