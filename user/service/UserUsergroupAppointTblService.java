package kr.co.esjee.sjcms.admin.user.service;

import kr.co.esjee.sjcms.admin.user.service.UserUsergroupAppointTblVO;

/**
 * @Class Name : UserUsergroupAppointTblService.java
 * @Description : UserUsergroupAppointTbl Business class
 * @Modification Information
 *
 * @author isjung
 * @since 2012-02-16
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface UserUsergroupAppointTblService {
	
	/**
	 * USER_USERGROUP_APPOINT_TBL을 등록한다.
	 * @param vo - 등록할 정보가 담긴 UserUsergroupAppointTblVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    String insertUserUsergroupAppointTbl(UserUsergroupAppointTblVO vo) throws Exception;
    
    /**
	 * USER_USERGROUP_APPOINT_TBL을 등록한다.
	 * @param vo - 등록할 정보가 담긴 UserUsergroupAppointTblVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    String insertUserUsergroupAppointTblMultiByUserId(UserUsergroupAppointTblVO vo) throws Exception;
    
    /**
	 * USER_USERGROUP_APPOINT_TBL을 등록한다.
	 * @param vo - 등록할 정보가 담긴 UserUsergroupAppointTblVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    String insertUserUsergroupAppointTblMultiByUsergroupId(UserUsergroupAppointTblVO vo) throws Exception;
    
    /**
	 * USER_USERGROUP_APPOINT_TBL을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 UserUsergroupAppointTblVO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteUserUsergroupAppointTblByUserId(UserUsergroupAppointTblVO vo) throws Exception;
    
    void deleteUserUsergroupAppointTblByUsergroupId(UserUsergroupAppointTblVO vo) throws Exception;
    
    /**
	 * USER_USERGROUP_APPOINT_TBL을 삭제하고 신규 데이터 입력 ( insertUserUsergroupAppointTblMulti + deleteUserUsergroupAppointTbl )
	 * @param vo - UserUsergroupAppointTblVO
	 * @return void형 
	 * @exception Exception
	 */
    
    void saveUserUsergroupAppointTblByUserId(UserUsergroupAppointTblVO vo) throws Exception;
    
    void saveUserUsergroupAppointTblByUsergroupId(UserUsergroupAppointTblVO vo) throws Exception;
    
}
