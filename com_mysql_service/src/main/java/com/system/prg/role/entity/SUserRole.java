package com.system.prg.role.entity;

import java.io.Serializable;

public class SUserRole implements Serializable{

	private static final long serialVersionUID = 1L;

	//用户ID
	private Long userId; 

	//角色ID
	private Long roleId; 

	public Long  getUserId(){
		return this.userId;
	}

	public void setUserId(Long userId){
		this.userId=userId;
	}

	public Long  getRoleId(){
		return this.roleId;
	}

	public void setRoleId(Long roleId){
		this.roleId=roleId;
	}
}
