package kr.co.esjee.sjcms.admin.user.service;

import java.util.HashMap;
import java.util.List;

import kr.co.esjee.sjcms.admin.user.service.UsergroupTblDefaultVO;
import kr.co.esjee.sjcms.admin.user.service.UsergroupTblVO;
import kr.co.esjee.sjcms.common.ValidatorMessenger;

/**
 * @Class Name : UsergroupTblService.java
 * @Description : UsergroupTbl Business class
 * @Modification Information
 *
 * @author isjung
 * @since 2012-02-07
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface UsergroupTblService {
	
	/**
	 * USERGROUP_TBL을 등록한다.
	 * @param vo - 등록할 정보가 담긴 UsergroupTblVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    String insertUsergroupTbl(UsergroupTblVO vo) throws Exception;
    
    /**
	 * USERGROUP_TBL을 수정한다.
	 * @param vo - 수정할 정보가 담긴 UsergroupTblVO
	 * @return void형
	 * @exception Exception
	 */
    void updateUsergroupTbl(UsergroupTblVO vo) throws Exception;
    
    /**
	 * USERGROUP_TBL을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 UsergroupTblVO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteUsergroupTbl(UsergroupTblVO vo) throws Exception;
    
    /**
	 * USERGROUP_TBL을 조회한다.
	 * @param vo - 조회할 정보가 담긴 UsergroupTblVO
	 * @return 조회한 USERGROUP_TBL
	 * @exception Exception
	 */
    UsergroupTblVO selectUsergroupTbl(UsergroupTblVO vo) throws Exception;
    
    /**
	 * USERGROUP_TBL 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return USERGROUP_TBL 목록
	 * @exception Exception
	 */
    List selectUsergroupTblList(UsergroupTblDefaultVO searchVO) throws Exception;
    
    List selectUsergroupTblAllList(HashMap<String, Object> param) throws Exception;
    
    List selectMemoUsergroupTblList() throws Exception;
    
    /**
	 * USERGROUP_TBL 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return USERGROUP_TBL 목록
	 * @exception Exception
	 */
    List selectUsergroupTblListByUserId(String userId) throws Exception;
    
    /**
	 * USERGROUP_TBL 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return USERGROUP_TBL 총 갯수
	 * @exception
	 */
    int selectUsergroupTblListTotCnt(UsergroupTblDefaultVO searchVO);
    
    /**
	 * 사용자 그릅 테이블 PK 유효성 체크.
	 * @param vo - 사용자 그룹  테이블 VO
	 * @param flag - 등록/수정/삭제 타입
	 * @return ValidatorMessenger 유효성 체크 후 메세지
	 * @exception Exception
	 */
	ValidatorMessenger validate(UsergroupTblVO vo, String flag) throws Exception;
}
