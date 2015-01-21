package com.system.prg.menu.entity;

import java.io.Serializable;

public class SMenu implements Serializable{

	//资源ID
	private Long menuId; 

	//资源名称
	private String menuName; 

	//资源描述
	private String menuDesc; 

	//资源URL
	private String menuUrl; 

	//资源PID
	private Long menuPid; 

	//资源类型
	private Long menuType; 

	//资源状态
	private Long menuStatus; 

	//资源级别
	private Long menuLevel; 

	//资源图标
	private String menuIcon; 

	//创建时间
	private String createTime; 

	//更新时间
	private String updateTime; 

	//菜单排序
	private Long menuOrder; 

	public Long  getMenuId(){
		return this.menuId;
	}

	public void setMenuId(Long menuId){
		this.menuId=menuId;
	}

	public String  getMenuName(){
		return this.menuName;
	}

	public void setMenuName(String menuName){
		this.menuName = menuName == null ? null : menuName.trim();
	}

	public String  getMenuDesc(){
		return this.menuDesc;
	}

	public void setMenuDesc(String menuDesc){
		this.menuDesc = menuDesc == null ? null : menuDesc.trim();
	}

	public String  getMenuUrl(){
		return this.menuUrl;
	}

	public void setMenuUrl(String menuUrl){
		this.menuUrl = menuUrl == null ? null : menuUrl.trim();
	}

	public Long  getMenuPid(){
		return this.menuPid;
	}

	public void setMenuPid(Long menuPid){
		this.menuPid=menuPid;
	}

	public Long  getMenuType(){
		return this.menuType;
	}

	public void setMenuType(Long menuType){
		this.menuType=menuType;
	}

	public Long  getMenuStatus(){
		return this.menuStatus;
	}

	public void setMenuStatus(Long menuStatus){
		this.menuStatus=menuStatus;
	}

	public Long  getMenuLevel(){
		return this.menuLevel;
	}

	public void setMenuLevel(Long menuLevel){
		this.menuLevel=menuLevel;
	}

	public String  getMenuIcon(){
		return this.menuIcon;
	}

	public void setMenuIcon(String menuIcon){
		this.menuIcon = menuIcon == null ? null : menuIcon.trim();
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

	public Long  getMenuOrder(){
		return this.menuOrder;
	}

	public void setMenuOrder(Long menuOrder){
		this.menuOrder=menuOrder;
	}
}
