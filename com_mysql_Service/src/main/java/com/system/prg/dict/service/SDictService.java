package com.system.prg.dict.service;

import java.util.Map;

import com.system.prg.dict.entity.SDict;
import com.system.prg.util.BaseService;

public interface SDictService extends BaseService<SDict>{
	
	public Map<String, String> getDetailNameMap(String dictName);//得到明细map<name,value>
	
	public Map<String, String> getDetailValueMap(String dictName);//得到明细map<value,name>
	
	public String getDetailValue(String dictName,String detailName);//得到明细value

}
