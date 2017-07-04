package kr.co.esjee.sjcms.admin.user.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import kr.co.esjee.sjcms.admin.user.service.UserUsergroupAppointTblService;
import kr.co.esjee.sjcms.admin.user.service.UserUsergroupAppointTblVO;
import kr.co.esjee.sjcms.admin.user.service.impl.UserUsergroupAppointTblDAO;
import kr.co.esjee.sjcms.common.util.StringUtil;

/**
 * @Class Name : UserUsergroupAppointTblServiceImpl.java
 * @Description : UserUsergroupAppointTbl Business Implement class
 * @Modification Information
 *
 * @author isjung
 * @since 2012-02-16
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("userUsergroupAppointTblService")
public class UserUsergroupAppointTblServiceImpl extends AbstractServiceImpl implements
        UserUsergroupAppointTblService {

    @Resource(name="userUsergroupAppointTblDAO")
    private UserUsergroupAppointTblDAO userUsergroupAppointTblDAO;

	/**
	 * USER_USERGROUP_APPOINT_TBL을 등록한다.
	 * @param vo - 등록할 정보가 담긴 UserUsergroupAppointTblVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertUserUsergroupAppointTbl(UserUsergroupAppointTblVO vo) throws Exception {
    	log.debug(vo.toString());
    	userUsergroupAppointTblDAO.insertUserUsergroupAppointTbl(vo);
        return null;
    }
    
	public String insertUserUsergroupAppointTblMultiByUserId(UserUsergroupAppointTblVO vo) throws Exception {
    	log.debug(vo.toString());
        if(!StringUtil.isEmpty(vo.getUsergroupId())){
	    	String[] usergroupIds = vo.getUsergroupId().split(",");
	    	for(int i=0;i<usergroupIds.length;i++){
	    		vo.setUsergroupId(usergroupIds[i]);
	    		userUsergroupAppointTblDAO.insertUserUsergroupAppointTbl(vo);
	    	}
        }
        return null;
	}
	
	public String insertUserUsergroupAppointTblMultiByUsergroupId(UserUsergroupAppointTblVO vo) throws Exception {
		log.debug(vo.toString());
        if(!StringUtil.isEmpty(vo.getUserId())){
	    	String[] userIds = vo.getUserId().split(",");
	    	for(int i=0;i<userIds.length;i++){
	    		vo.setUserId(userIds[i]);
	    		userUsergroupAppointTblDAO.insertUserUsergroupAppointTbl(vo);
	    	}
        }
        return null;
	}

    /**
	 * USER_USERGROUP_APPOINT_TBL을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 UserUsergroupAppointTblVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteUserUsergroupAppointTblByUserId(UserUsergroupAppointTblVO vo) throws Exception {
        userUsergroupAppointTblDAO.deleteUserUsergroupAppointTblByUserId(vo);
    }
    
    public void deleteUserUsergroupAppointTblByUsergroupId(UserUsergroupAppointTblVO vo) throws Exception {
        userUsergroupAppointTblDAO.deleteUserUsergroupAppointTblByUsergroupId(vo);
    }

    /**
	 * insertUserUsergroupAppointTblMulti + deleteUserUsergroupAppointTbl
	 * @param vo - UserUsergroupAppointTblVO
	 * @return void형 
	 * @exception Exception
	 */
    @Transactional(propagation=Propagation.REQUIRED)
    public void saveUserUsergroupAppointTblByUserId(UserUsergroupAppointTblVO vo) throws Exception {
    	this.deleteUserUsergroupAppointTblByUserId(vo);
        this.insertUserUsergroupAppointTblMultiByUserId(vo);
    }
    
    @Transactional(propagation=Propagation.REQUIRED)
    public void saveUserUsergroupAppointTblByUsergroupId(UserUsergroupAppointTblVO vo) throws Exception {
    	this.deleteUserUsergroupAppointTblByUsergroupId(vo);
        this.insertUserUsergroupAppointTblMultiByUsergroupId(vo);
    }
}
