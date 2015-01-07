package com.system.prg.dict.service;

import java.util.List;
import java.util.Map;

import com.system.prg.dict.entity.SDetail;
import com.system.prg.util.PageObject;

public interface SDetailService {

	public SDetail insert(SDetail record);

	public void deleteByCondition(Map<String, Object> condition);

	public void updateByCondition(SDetail record);
	
	public List<SDetail> selectByCondition(Map<String, Object> condititon);
	
	public Integer countByCondition(Map<String, Object> condititon);

	public List<SDetail> pageList(PageObject po);

	public void deleteByPrimaryKey(Long detailId);

	public SDetail findByPrimaryKey(Long detailId);

}
