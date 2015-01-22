package com.system.prg.dict.entity;

import java.io.Serializable;

public class SDict implements Serializable{

	//字典ID
	private Long dictId; 

	//字典名称
	private String dictName; 

	//描述
	private String dictDesc; 

	//状态
	private Long dictStatus; 

	//创建时间
	private String createTime; 

	//更新时间
	private String updateTime; 

	public Long  getDictId(){
		return this.dictId;
	}

	public void setDictId(Long dictId){
		this.dictId=dictId;
	}

	public String  getDictName(){
		return this.dictName;
	}

	public void setDictName(String dictName){
		this.dictName = dictName == null ? null : dictName.trim();
	}

	public String  getDictDesc(){
		return this.dictDesc;
	}

	public void setDictDesc(String dictDesc){
		this.dictDesc = dictDesc == null ? null : dictDesc.trim();
	}

	public Long  getDictStatus(){
		return this.dictStatus;
	}

	public void setDictStatus(Long dictStatus){
		this.dictStatus=dictStatus;
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
