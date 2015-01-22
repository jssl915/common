package com.system.prg.role.entity;

import java.io.Serializable;

public class SRole implements Serializable{

	//角色ID
	private Long roleId; 

	//角色名称
	private String roleName; 

	//描述
	private String roleDesc; 

	//null
	private Long roleOrder; 

	//角色类型
	private Long roleType; 

	//角色状态
	private Long roleStatus; 

	//创建时间
	private String createTime; 

	//更新时间
	private String updateTime; 

	public Long  getRoleId(){
		return this.roleId;
	}

	public void setRoleId(Long roleId){
		this.roleId=roleId;
	}

	public String  getRoleName(){
		return this.roleName;
	}

	public void setRoleName(String roleName){
		this.roleName = roleName == null ? null : roleName.trim();
	}

	public String  getRoleDesc(){
		return this.roleDesc;
	}

	public void setRoleDesc(String roleDesc){
		this.roleDesc = roleDesc == null ? null : roleDesc.trim();
	}

	public Long  getRoleOrder(){
		return this.roleOrder;
	}

	public void setRoleOrder(Long roleOrder){
		this.roleOrder=roleOrder;
	}

	public Long  getRoleType(){
		return this.roleType;
	}

	public void setRoleType(Long roleType){
		this.roleType=roleType;
	}

	public Long  getRoleStatus(){
		return this.roleStatus;
	}

	public void setRoleStatus(Long roleStatus){
		this.roleStatus=roleStatus;
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
}
