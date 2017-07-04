package kr.co.esjee.sjcms.admin.user.service;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Class Name : UserTblVO.java
 * @Description : UserTbl VO class
 * @Modification Information
 *
 * @author isjung
 * @since 2012-02-09
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@XmlRootElement(name="userTblVO")
public class UserTblVO extends UserTblDefaultVO{
    private static final long serialVersionUID = 1L;
    
    /** USER_ID */
    private String userId;
    
    /** USER_NAME */
    private String userName;
    
    /** USER_PASS */
    private String userPass;
    
    /** LVL */
    private String lvl;
    
    /** USE_TF */
    private String useTF;
    
    /** DELETE_TF */
    private String deleteTF;
    
    /** REG_ACT_ID */
    private String regActId;
    
    /** REG_ACT_DT */
    private java.util.Date regActDt;
    
    /** UPD_ACT_ID */
    private String updActId;
    
    /** UPD_ACT_DT */
    private java.util.Date updActDt;
    
    /** ROWNUM */
    private String rnum;
    
    public String getRnum() {
		return rnum;
	}

	public void setRnum(String rnum) {
		this.rnum = rnum;
	}

	private String defaultTf;
    
    public String getLvl() {
		return lvl;
	}

	public void setLvl(String lvl) {
		this.lvl = lvl;
	}

	public String getUseTF() {
		return useTF;
	}

	public void setUseTF(String useTF) {
		this.useTF = useTF;
	}

	public String getDeleteTF() {
		return deleteTF;
	}

	public void setDeleteTF(String deleteTF) {
		this.deleteTF = deleteTF;
	}

	public String getUserId() {
        return this.userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public String getUserName() {
        return this.userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getUserPass() {
        return this.userPass;
    }
    
    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }
    
    public String getRegActId() {
        return this.regActId;
    }
    
    public void setRegActId(String regActId) {
        this.regActId = regActId;
    }
    
    public java.util.Date getRegActDt() {
        return this.regActDt;
    }
    
    public void setRegActDt(java.util.Date regActDt) {
        this.regActDt = regActDt;
    }
    
    public String getUpdActId() {
        return this.updActId;
    }
    
    public void setUpdActId(String updActId) {
        this.updActId = updActId;
    }
    
    public java.util.Date getUpdActDt() {
        return this.updActDt;
    }
    
    public void setUpdActDt(java.util.Date updActDt) {
        this.updActDt = updActDt;
    }

	public String getDefaultTf() {
		return defaultTf;
	}

	public void setDefaultTf(String defaultTf) {
		this.defaultTf = defaultTf;
	}
}
