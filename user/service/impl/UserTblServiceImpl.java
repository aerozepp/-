package kr.co.esjee.sjcms.admin.user.service.impl;

import java.util.List;

import javax.annotation.Resource;

import kr.co.esjee.sjcms.FormConstant;
import kr.co.esjee.sjcms.admin.user.service.UserTblDefaultVO;
import kr.co.esjee.sjcms.admin.user.service.UserTblService;
import kr.co.esjee.sjcms.admin.user.service.UserTblVO;
import kr.co.esjee.sjcms.admin.user.service.UserUsergroupAppointTblService;
import kr.co.esjee.sjcms.admin.user.service.UserUsergroupAppointTblVO;
import kr.co.esjee.sjcms.common.ValidatorMessenger;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

/**
 * @Class Name : UserTblServiceImpl.java
 * @Description : UserTbl Business Implement class
 * @Modification Information
 * 
 * @author isjung
 * @since 2012-02-09
 * @version 1.0
 * @see Copyright (C) All right reserved.
 */

@Service("userTblService")
public class UserTblServiceImpl extends AbstractServiceImpl implements UserTblService, FormConstant {

	@Resource(name = "userTblDAO")
	private UserTblDAO userTblDAO;

	@Resource(name = "userUsergroupAppointTblService")
	private UserUsergroupAppointTblService userUsergroupAppointTblService;

	@Resource(name = "messageSource")
	private MessageSource messageSource;

	/**
	 * USER_TBL을 등록한다.
	 * 
	 * @param vo - 등록할 정보가 담긴 UserTblVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertUserTbl(UserTblVO vo) throws Exception {
		log.debug(vo.toString());

		/** ID Generation Service */
		// TODO 해당 테이블 속성에 따라 ID 제너레이션 서비스 사용
		// String id = egovIdGnrService.getNextStringId();
		// vo.setId(id);
		log.debug(vo.toString());

		userTblDAO.insertUserTbl(vo);
		// TODO 해당 테이블 정보에 맞게 수정
		return null;
	}

	/**
	 * USER_TBL을 수정한다.
	 * 
	 * @param vo - 수정할 정보가 담긴 UserTblVO
	 * @return void형
	 * @exception Exception
	 */
	public void updateUserTbl(UserTblVO vo) throws Exception {
		userTblDAO.updateUserTbl(vo);
	}

	/**
	 * USER_TBL을 삭제한다.
	 * 
	 * @param vo - 삭제할 정보가 담긴 UserTblVO
	 * @return void형
	 * @exception Exception
	 */
	public void deleteUserTbl(UserTblVO vo) throws Exception {
		userTblDAO.deleteUserTbl(vo);
	}

	/**
	 * USER_TBL을 조회한다.
	 * 
	 * @param vo - 조회할 정보가 담긴 UserTblVO
	 * @return 조회한 USER_TBL
	 * @exception Exception
	 */
	public UserTblVO selectUserTbl(UserTblVO vo) throws Exception {
		UserTblVO resultVO = userTblDAO.selectUserTbl(vo);
		return resultVO;
	}

	/**
	 * USER_TBL 목록을 조회한다.
	 * 
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return USER_TBL 목록
	 * @exception Exception
	 */
	public List selectUserTblList(UserTblDefaultVO searchVO) throws Exception {
		return userTblDAO.selectUserTblList(searchVO);
	}

	/**
	 * USER_TBL 총 갯수를 조회한다.
	 * 
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return USER_TBL 총 갯수
	 * @exception
	 */
	public int selectUserTblListTotCnt(UserTblDefaultVO searchVO) {
		return userTblDAO.selectUserTblListTotCnt(searchVO);
	}

	/**
	 * 그룹에 매핑된 USER_TBL 목록을 조회한다.
	 * 
	 * @param userGroupId - 매핑된 그룹아ㅇ디
	 * @return USER_TBL 목록
	 * @exception Exception
	 */
	public List selectUserTblListByUserGroupId(String usergroupId) throws Exception {
		return userTblDAO.selectUserTblListByUserGroupId(usergroupId);
	}

	public ValidatorMessenger validate(UserTblVO vo, String flag) throws Exception {
		ValidatorMessenger vm = new ValidatorMessenger();
		if (SUBMIT_INSERT.equals(flag)) {
			checkPk(vo, vm);
		} else if (SUBMIT_DELETE.equals(flag)) {
			checkDefault(vo, vm);
		}
		vm.complete();
		return vm;
	}

	public boolean checkPk(UserTblVO vo, ValidatorMessenger vm) throws Exception {
		String flagPk = userTblDAO.selectUserTblPk(vo);
		if (PK_T.equals(flagPk)) {
			vm.append(messageSource.getMessage("errors.id.used", null, LocaleContextHolder.getLocale()));
		} else if (PK_D.equals(flagPk)) {
			vm.append(messageSource.getMessage("errors.id.delete", null, LocaleContextHolder.getLocale()));
		}
		return true;
	}

	public void checkDefault(UserTblVO vo, ValidatorMessenger vm) throws Exception {
		UserTblVO userTblVO = this.selectUserTbl(vo);
		String defaultTf = userTblVO.getDefaultTf();
		if (FLAG_T.equals(defaultTf)) {
			vm.append(messageSource.getMessage("errors.id.default", null, LocaleContextHolder.getLocale()));
		}
	}

	@Override
	public void insertUserAndUsergroupAppoint(UserTblVO userTblVO, UserUsergroupAppointTblVO appointTblVO) throws Exception {
		this.insertUserTbl(userTblVO);
		userUsergroupAppointTblService.saveUserUsergroupAppointTblByUserId(appointTblVO);
	}

	@Override
	public void updateUserAndUsergroupAppoint(UserTblVO userTblVO, UserUsergroupAppointTblVO appointTblVO) throws Exception {
		this.updateUserTbl(userTblVO);
		userUsergroupAppointTblService.saveUserUsergroupAppointTblByUserId(appointTblVO);
	}

}
