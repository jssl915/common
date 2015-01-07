package com.system.prg.log.entity;

import java.io.Serializable;

public class SLog implements Serializable{

	//null
	private Long logId; 

	//日志描述
	private String logDesc; 

	//访问URL
	private String actionUrl; 

	//访问时间
	private String logTime; 

	//用户IP
	private String userIp; 

	//用户ID
	private Long userId; 

	//用户名称
	private String userName; 

	public Long  getLogId(){
		return this.logId;
	}

	public void setLogId(Long logId){
		this.logId=logId;
	}

	public String  getLogDesc(){
		return this.logDesc;
	}

	public void setLogDesc(String logDesc){
		this.logDesc = logDesc == null ? null : logDesc.trim();
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

	public Long  getUserId(){
		return this.userId;
	}

	public void setUserId(Long userId){
		this.userId=userId;
	}

	public String  getUserName(){
		return this.userName;
	}

	public void setUserName(String userName){
		this.userName = userName == null ? null : userName.trim();
	}
}
