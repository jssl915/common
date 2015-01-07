package com.system.prg.log.service;

import java.util.List;
import java.util.Map;

import com.system.prg.log.entity.SLog;
import com.system.prg.util.PageObject;

public interface SLogService {

	public SLog insert(SLog record);

	public void deleteByCondition(Map<String, Object> condition);

	public void updateByCondition(SLog record);
	
	public List<SLog> selectByCondition(Map<String, Object> condititon);
	
	public Integer countByCondition(Map<String, Object> condititon);

	public List<SLog> pageList(PageObject po);

	public void deleteByPrimaryKey(Long logId);

	public SLog findByPrimaryKey(Long logId);

}
