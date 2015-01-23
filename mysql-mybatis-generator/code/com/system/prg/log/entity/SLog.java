package com.system.prg.log.entity;

import java.io.Serializable;

public class SLog implements Serializable{

	//
	private Integer logId; 

	//
	private String actionUrl; 

	//
	private String logTime; 

	//
	private String userIp; 

	//
	private Integer userId; 

	//
	private String logDesc; 

	//
	private String userName; 

	public Integer  getLogId(){
		return this.logId;
	}

	public void setLogId(Integer logId){
		this.logId=logId;
	}

	public String  getActionUrl(){
		return this.actionUrl;
	}

	public void setActionUrl(String actionUrl){
		this.actionUrl = actionUrl == null ? null : actionUrl.trim();
	}

	public String  getLogTime(){
		return this.logTime;
	}

	public void setLogTime(String logTime){
		this.logTime = logTime == null ? null : logTime.trim();
	}

	public String  getUserIp(){
		return this.userIp;
	}

	public void setUserIp(String userIp){
		this.userIp = userIp == null ? null : userIp.trim();
	}

	public Integer  getUserId(){
		return this.userId;
	}

	public void setUserId(Integer userId){
		this.userId=userId;
	}

	public String  getLogDesc(){
		return this.logDesc;
	}

	public void setLogDesc(String logDesc){
		this.logDesc = logDesc == null ? null : logDesc.trim();
	}

	public String  getUserName(){
		return this.userName;
	}

	public void setUserName(String userName){
		this.userName = userName == null ? null : userName.trim();
	}
}
