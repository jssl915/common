package com.system.prg.role.entity;

import java.io.Serializable;

public class SRoleMenu implements Serializable{

	//角色ID
	private Long roleId; 

	//资源ID
	private Long menuId; 

	public Long  getRoleId(){
		return this.roleId;
	}

	public void setRoleId(Long roleId){
		this.roleId=roleId;
	}

	public Long  getMenuId(){
		return this.menuId;
	}

	public void setMenuId(Long menuId){
		this.menuId=menuId;
	}
}
