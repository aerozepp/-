package kr.co.esjee.sjcms.admin.user.service.impl;

import kr.co.esjee.sjcms.admin.user.service.UserUsergroupAppointTblVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

/**
 * @Class Name : UserUsergroupAppointTblDAO.java
 * @Description : UserUsergroupAppointTbl DAO Class
 * @Modification Information
 *
 * @author isjung
 * @since 2012-02-16
 * @version 1.0
 * @see Copyright (C) All right reserved.
 */

@Repository("userUsergroupAppointTblDAO")
public class UserUsergroupAppointTblDAO extends EgovAbstractDAO {

	/**
	 * USER_USERGROUP_APPOINT_TBL을 등록한다.
	 *
	 * @param vo - 등록할 정보가 담긴 UserUsergroupAppointTblVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertUserUsergroupAppointTbl(UserUsergroupAppointTblVO vo) throws Exception {
		return (String) insert("userUsergroupAppointTblDAO.insertUserUsergroupAppointTbl_S", vo);
	}

	/**
	 * USER_USERGROUP_APPOINT_TBL을 삭제한다.
	 *
	 * @param vo - 삭제할 정보가 담긴 UserUsergroupAppointTblVO
	 * @return void형
	 * @exception Exception
	 */
	public void deleteUserUsergroupAppointTblByUserId(UserUsergroupAppointTblVO vo) throws Exception {
		delete("userUsergroupAppointTblDAO.deleteUserUsergroupAppointTblByUserId", vo);
	}

	public void deleteUserUsergroupAppointTblByUsergroupId(UserUsergroupAppointTblVO vo) throws Exception {
		delete("userUsergroupAppointTblDAO.deleteUserUsergroupAppointTblByUsergroupId", vo);
	}

}
