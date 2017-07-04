package kr.co.esjee.sjcms.admin.user.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import kr.co.esjee.sjcms.admin.user.service.UsergroupTblDefaultVO;
import kr.co.esjee.sjcms.admin.user.service.UsergroupTblVO;

/**
 * @Class Name : UsergroupTblDAO.java
 * @Description : UsergroupTbl DAO Class
 * @Modification Information
 *
 * @author isjung
 * @since 2012-02-07
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("usergroupTblDAO")
public class UsergroupTblDAO extends EgovAbstractDAO {

	/**
	 * USERGROUP_TBL을 등록한다.
	 * @param vo - 등록할 정보가 담긴 UsergroupTblVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertUsergroupTbl(UsergroupTblVO vo) throws Exception {
        return (String)insert("usergroupTblDAO.insertUsergroupTbl_S", vo);
    }

    /**
	 * USERGROUP_TBL을 수정한다.
	 * @param vo - 수정할 정보가 담긴 UsergroupTblVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateUsergroupTbl(UsergroupTblVO vo) throws Exception {
        update("usergroupTblDAO.updateUsergroupTbl_S", vo);
    }

    /**
	 * USERGROUP_TBL을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 UsergroupTblVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteUsergroupTbl(UsergroupTblVO vo) throws Exception {
        delete("usergroupTblDAO.deleteUsergroupTbl_S", vo);
    }

    /**
	 * USERGROUP_TBL을 조회한다.
	 * @param vo - 조회할 정보가 담긴 UsergroupTblVO
	 * @return 조회한 USERGROUP_TBL
	 * @exception Exception
	 */
    public UsergroupTblVO selectUsergroupTbl(UsergroupTblVO vo) throws Exception {
        return (UsergroupTblVO) selectByPk("usergroupTblDAO.selectUsergroupTbl_S", vo);
    }

    /**
	 * USERGROUP_TBL 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return USERGROUP_TBL 목록
	 * @exception Exception
	 */
    public List selectUsergroupTblList(UsergroupTblDefaultVO searchVO) throws Exception {
        return list("usergroupTblDAO.selectUsergroupTblList_D", searchVO);
    }
    
    public List<EgovMap> selectUsergroupTblAllList(HashMap<String, Object> param) throws Exception {
    	return list("usergroupTblDAO.selectUsergroupTblAllList_D", param);
    }

    public List selectMemoUsergroupTblList() throws Exception {
    	return list("usergroupTblDAO.selectMemoUsergroupTblList_D", "");
    }
    
    /**
	 * USERGROUP_TBL 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return USERGROUP_TBL 목록
	 * @exception Exception
	 */
    public List selectUsergroupTblListByUserId(String userId) throws Exception {
        return list("usergroupTblDAO.selectUsergroupTblListByUserId", userId);
    }
    
    /**
	 * USERGROUP_TBL 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return USERGROUP_TBL 총 갯수
	 * @exception
	 */
    public int selectUsergroupTblListTotCnt(UsergroupTblDefaultVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("usergroupTblDAO.selectUsergroupTblListTotCnt_S", searchVO);
    }
    
    /**
	 * PK 체크 한다.
	 * @param userGroupId - 그룹아이디 
	 * @return String PK 체크 타입
	 * @exception Exception
	 */
    public String selectUsergroupTblPk(UsergroupTblVO vo) throws Exception {
        return (String) getSqlMapClientTemplate().queryForObject("usergroupTblDAO.selectUsergroupTblPk_S", vo);
    }
}
