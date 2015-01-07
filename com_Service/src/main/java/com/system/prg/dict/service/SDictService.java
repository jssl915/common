package com.system.prg.dict.service;

import java.util.List;
import java.util.Map;

import com.system.prg.dict.entity.SDict;
import com.system.prg.util.PageObject;

public interface SDictService {

	public SDict insert(SDict record);

	public void deleteByCondition(Map<String, Object> condition);

	public void updateByCondition(SDict record);
	
	public List<SDict> selectByCondition(Map<String, Object> condititon);
	
	public Integer countByCondition(Map<String, Object> condititon);

	public List<SDict> pageList(PageObject po);

	public void deleteByPrimaryKey(Long dictId);

	public SDict findByPrimaryKey(Long dictId);
	
	public Map<String, String> getDetailNameMap(String dictName);//得到明细map<name,value>
	
	public Map<String, String> getDetailValueMap(String dictName);//得到明细map<value,name>
	
	public String getDetailValue(String dictName,String detailName);//得到明细value

}
