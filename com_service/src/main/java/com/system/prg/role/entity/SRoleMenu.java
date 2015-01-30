package com.system.prg.role.entity;

import java.io.Serializable;

public class SRoleMenu implements Serializable{

	private static final long serialVersionUID = 1L;
	
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
