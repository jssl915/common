package com.system.prg.dict.entity;

import java.io.Serializable;

public class SDetail implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	//字典明细ID
	private Long detailId; 

	//字典ID
	private Long dictId; 

	//字典字段名称
	private String detailName; 

	//字典字段值
	private Long detailValue; 

	//描述
	private String detailDesc; 

	//状态
	private Long detailStatus; 

	//创建时间
	private String createTime; 

	//更新时间
	private String updateTime; 

	public Long  getDetailId(){
		return this.detailId;
	}

	public void setDetailId(Long detailId){
		this.detailId=detailId;
	}

	public Long  getDictId(){
		return this.dictId;
	}

	public void setDictId(Long dictId){
		this.dictId=dictId;
	}

	public String  getDetailName(){
		return this.detailName;
	}

	public void setDetailName(String detailName){
		this.detailName = detailName == null ? null : detailName.trim();
	}

	public Long  getDetailValue(){
		return this.detailValue;
	}

	public void setDetailValue(Long detailValue){
		this.detailValue=detailValue;
	}

	public String  getDetailDesc(){
		return this.detailDesc;
	}

	public void setDetailDesc(String detailDesc){
		this.detailDesc = detailDesc == null ? null : detailDesc.trim();
	}

	public Long  getDetailStatus(){
		return this.detailStatus;
	}

	public void setDetailStatus(Long detailStatus){
		this.detailStatus=detailStatus;
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
