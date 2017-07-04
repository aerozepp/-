package kr.co.esjee.sjcms.admin.user.service;

import java.util.List;

import kr.co.esjee.sjcms.common.ValidatorMessenger;

/**
 * @Class Name : UserTblService.java
 * @Description : UserTbl Business class
 * @Modification Information
 * 
 * @author isjung
 * @since 2012-02-09
 * @version 1.0
 * @see Copyright (C) All right reserved.
 */
public interface UserTblService {

	/**
	 * USER_TBL을 등록한다.
	 * 
	 * @param vo - 등록할 정보가 담긴 UserTblVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	String insertUserTbl(UserTblVO vo) throws Exception;

	/**
	 * USER_TBL을 수정한다.
	 * 
	 * @param vo - 수정할 정보가 담긴 UserTblVO
	 * @return void형
	 * @exception Exception
	 */
	void updateUserTbl(UserTblVO vo) throws Exception;

	/**
	 * USER_TBL을 삭제한다.
	 * 
	 * @param vo - 삭제할 정보가 담긴 UserTblVO
	 * @return void형
	 * @exception Exception
	 */
	void deleteUserTbl(UserTblVO vo) throws Exception;

	/**
	 * USER_TBL을 조회한다.
	 * 
	 * @param vo - 조회할 정보가 담긴 UserTblVO
	 * @return 조회한 USER_TBL
	 * @exception Exception
	 */
	UserTblVO selectUserTbl(UserTblVO vo) throws Exception;

	/**
	 * USER_TBL 목록을 조회한다.
	 * 
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return USER_TBL 목록
	 * @exception Exception
	 */
	List selectUserTblList(UserTblDefaultVO searchVO) throws Exception;

	/**
	 * USER_TBL 총 갯수를 조회한다.
	 * 
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return USER_TBL 총 갯수
	 * @exception
	 */
	int selectUserTblListTotCnt(UserTblDefaultVO searchVO);

	/**
	 * 그룹에 매핑된 USER_TBL 목록을 조회한다.
	 * 
	 * @param userGroupId - 매핑된 그룹아이디
	 * @return USER_TBL 목록
	 * @exception Exception
	 */
	List selectUserTblListByUserGroupId(String userGroupId) throws Exception;

	/**
	 * 사용자 테이블 PK 유효성 체크.
	 * 
	 * @param vo - 사용자 테이블 VO
	 * @param flag - 등록/수정/삭제 타입
	 * @return ValidatorMessenger 유효성 체크 후 메세지
	 * @exception Exception
	 */
	ValidatorMessenger validate(UserTblVO vo, String flag) throws Exception;

	/**
	 * @Method insertUserAndUsergroupAppoint
	 * @Description 사용자 정보 저장 ( 그룹정보 포함 )
	 * 
	 * @author sowoosung
	 * @date 2014. 4. 4.
	 * @param userTblVO
	 * @param appointTblVO
	 * @throws Exception
	 * @return_type void
	 */
	void insertUserAndUsergroupAppoint(UserTblVO userTblVO, UserUsergroupAppointTblVO appointTblVO) throws Exception;

	/**
	 * @Method updateUserAndUsergroupAppoint
	 * @Description 사용자 정보 수정 ( 그룹정보 포함 )
	 * 
	 * @author sowoosung
	 * @date 2014. 4. 4.
	 * @param userTblVO
	 * @param appointTblVO
	 * @throws Exception
	 * @return_type void
	 */
	void updateUserAndUsergroupAppoint(UserTblVO userTblVO, UserUsergroupAppointTblVO appointTblVO) throws Exception;

}
