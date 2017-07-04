package kr.co.esjee.sjcms.admin.user.service.impl;

import java.util.List;
import java.util.Map;

import kr.co.esjee.sjcms.admin.user.service.UserTblDefaultVO;
import kr.co.esjee.sjcms.admin.user.service.UserTblVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

/**
 * @Class Name : UserTblDAO.java
 * @Description : UserTbl DAO Class
 * @Modification Information
 * 
 * @author isjung
 * @since 2012-02-09
 * @version 1.0
 * @see Copyright (C) All right reserved.
 */

@Repository("userTblDAO")
public class UserTblDAO extends EgovAbstractDAO {

	/**
	 * USER_TBL을 등록한다.
	 * 
	 * @param vo - 등록할 정보가 담긴 UserTblVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertUserTbl(UserTblVO vo) throws Exception {
		return (String) insert("userTblDAO.insertUserTbl_S", vo);
	}

	/**
	 * USER_TBL을 수정한다.
	 * 
	 * @param vo - 수정할 정보가 담긴 UserTblVO
	 * @return void형
	 * @exception Exception
	 */
	public void updateUserTbl(UserTblVO vo) throws Exception {
		update("userTblDAO.updateUserTbl_S", vo);
	}

	/**
	 * USER_TBL을 삭제한다.
	 * 
	 * @param vo - 삭제할 정보가 담긴 UserTblVO
	 * @return void형
	 * @exception Exception
	 */
	public void deleteUserTbl(UserTblVO vo) throws Exception {
		delete("userTblDAO.deleteUserTbl_S", vo);
	}

	/**
	 * USER_TBL을 조회한다.
	 * 
	 * @param vo - 조회할 정보가 담긴 UserTblVO
	 * @return 조회한 USER_TBL
	 * @exception Exception
	 */
	public UserTblVO selectUserTbl(UserTblVO vo) throws Exception {
		return (UserTblVO) selectByPk("userTblDAO.selectUserTbl_S", vo);
	}

	/**
	 * @Method selectUserTbl
	 * @Description 입력받은 사용자의 정보를 map 형태로 조회 한다.
	 * 
	 * @author sowoosung
	 * @date 2014. 6. 20.
	 * @param param
	 * @return
	 * @throws Exception
	 * @return_type EgovMap
	 */
	public UserTblVO selectUserTbl(Map<String, Object> param) throws Exception {
		return (UserTblVO) selectByPk("userTblDAO.selectUserTbl_S", param);
	}

	/**
	 * USER_TBL 목록을 조회한다.
	 * 
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return USER_TBL 목록
	 * @exception Exception
	 */
	public List selectUserTblList(UserTblDefaultVO searchVO) throws Exception {
		return list("userTblDAO.selectUserTblList_D", searchVO);
	}

	/**
	 * USER_TBL 총 갯수를 조회한다.
	 * 
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return USER_TBL 총 갯수
	 * @exception
	 */
	public int selectUserTblListTotCnt(UserTblDefaultVO searchVO) {
		return (Integer) selectByPk("userTblDAO.selectUserTblListTotCnt_S", searchVO);
	}

	/**
	 * 그룹에 매핑된 USER_TBL 목록을 조회한다.
	 * 
	 * @param userGroupId - 매핑된 그룹아ㅇ디
	 * @return USER_TBL 목록
	 * @exception Exception
	 */
	public List selectUserTblListByUserGroupId(String usergroupId) throws Exception {
		return list("userTblDAO.selectUserTblListByUserGroupId", usergroupId);
	}

	/**
	 * PK 체크 한다.
	 * 
	 * @param userId - 사용자 아이디
	 * @return String PK 체크 타입
	 * @exception Exception
	 */
	public String selectUserTblPk(UserTblVO vo) throws Exception {
		return (String) selectByPk("userTblDAO.selectUserTblPk_S", vo);
	}
	
}
