package com.system.prg.user.entity;

import java.io.Serializable;

public class SUser implements Serializable{

	private static final long serialVersionUID = 1L;

	//用户ID
	private Long userId; 

	//登录名称
	private String userName; 

	//排序
	private Long userOrder; 

	//用户状态
	private Long userType; 

	//用户状态
	private Long userStatus; 

	//加密密码
	private String userPwd; 

	//创建时间
	private String createTime; 

	//更新时间
	private String updateTime; 

	//真实姓名
	private String realName; 

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

	public Long  getUserOrder(){
		return this.userOrder;
	}

	public void setUserOrder(Long userOrder){
		this.userOrder=userOrder;
	}

	public Long  getUserType(){
		return this.userType;
	}

	public void setUserType(Long userType){
		this.userType=userType;
	}

	public Long  getUserStatus(){
		return this.userStatus;
	}

	public void setUserStatus(Long userStatus){
		this.userStatus=userStatus;
	}

	public String  getUserPwd(){
		return this.userPwd;
	}

	public void setUserPwd(String userPwd){
		this.userPwd = userPwd == null ? null : userPwd.trim();
	}

	public String  getCreateTime(){
		return this.createTime;
	}

	public void setCreateTime(String createTime){
		this.createTime = createTime == null ? null : createTime.trim();
	}

	public String  getUpdateTime(){
		return this.updateTime;
	}

	public void setUpdateTime(String updateTime){
		this.updateTime = updateTime == null ? null : updateTime.trim();
	}

	public String  getRealName(){
		return this.realName;
	}

	public void setRealName(String realName){
		this.realName = realName == null ? null : realName.trim();
	}
}
