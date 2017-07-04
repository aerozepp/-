package kr.co.esjee.sjcms.admin.user.service;

import java.io.Serializable;

/**
 * @Class Name : UserUsergroupAppointTblVO.java
 * @Description : UserUsergroupAppointTbl VO class
 * @Modification Information
 *
 * @author isjung
 * @since 2012-02-16
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class UserUsergroupAppointTblVO implements Serializable{
    private static final long serialVersionUID = 1L;
    
    /** USER_ID */
    private String userId;
    
    /** USERGROUP_ID */
    private String usergroupId;
    
    public String getUserId() {
        return this.userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public String getUsergroupId() {
        return this.usergroupId;
    }
    
    public void setUsergroupId(String usergroupId) {
        this.usergroupId = usergroupId;
    }
    
}
