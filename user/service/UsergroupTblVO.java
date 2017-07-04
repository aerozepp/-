package kr.co.esjee.sjcms.admin.user.service;

/**
 * @Class Name : UsergroupTblVO.java
 * @Description : UsergroupTbl VO class
 * @Modification Information
 * 
 * @author isjung
 * @since 2012-02-07
 * @version 1.0
 * @see Copyright (C) All right reserved.
 */
public class UsergroupTblVO extends UsergroupTblDefaultVO {

	private static final long serialVersionUID = 1L;

	/** USERGROUP_ID */
	private java.lang.String usergroupId;

	/** USERGROUP_NAME */
	private java.lang.String usergroupName;

	/** USERGROUP_EXP */
	private java.lang.String usergroupExp;

	/** DELETE_TF */
	private java.lang.String deleteTF;

	/** REG_ACT_ID */
	private java.lang.String regActId;

	/** REG_ACT_DT */
	private java.sql.Date regActDt;

	/** UPD_ACT_ID */
	private java.lang.String updActId;

	/** UPD_ACT_DT */
	private java.sql.Date updActDt;

	private String defaultTf;

	private int groupLvl;
	
	/** ROWNUM */
	private String rnum;

	public String getRnum() {
		return rnum;
	}

	public void setRnum(String rnum) {
		this.rnum = rnum;
	}

	public java.lang.String getUsergroupId() {
		return this.usergroupId;
	}

	public java.lang.String getUsergroupTF() {
		return deleteTF;
	}

	public void setUsergroupTF(java.lang.String deleteTF) {
		this.deleteTF = deleteTF;
	}

	public void setUsergroupId(java.lang.String usergroupId) {
		this.usergroupId = usergroupId;
	}

	public java.lang.String getUsergroupName() {
		return this.usergroupName;
	}

	public void setUsergroupName(java.lang.String usergroupName) {
		this.usergroupName = usergroupName;
	}

	public java.lang.String getUsergroupExp() {
		return this.usergroupExp;
	}

	public void setUsergroupExp(java.lang.String usergroupExp) {
		this.usergroupExp = usergroupExp;
	}

	public java.lang.String getRegActId() {
		return this.regActId;
	}

	public void setRegActId(java.lang.String regActId) {
		this.regActId = regActId;
	}

	public java.sql.Date getRegActDt() {
		return this.regActDt;
	}

	public void setRegActDt(java.sql.Date regActDt) {
		this.regActDt = regActDt;
	}

	public java.lang.String getUpdActId() {
		return this.updActId;
	}

	public void setUpdActId(java.lang.String updActId) {
		this.updActId = updActId;
	}

	public java.sql.Date getUpdActDt() {
		return this.updActDt;
	}

	public void setUpdActDt(java.sql.Date updActDt) {
		this.updActDt = updActDt;
	}

	public String getDefaultTf() {
		return defaultTf;
	}

	public void setDefaultTf(String defaultTf) {
		this.defaultTf = defaultTf;
	}

	public int getGroupLvl() {
		return groupLvl;
	}

	public void setGroupLvl(int groupLvl) {
		this.groupLvl = groupLvl;
	}
}
