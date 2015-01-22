package com.system.prg.util;

import java.util.List;
import java.util.Map;


public abstract interface BaseService<T>{
	
	public abstract T insert(T paramT);
	
	public void deleteByCondition(Map<String, Object> condition);
	
	public void updateByCondition(T record);
	
	public List<T> selectByCondition(Map<String, Object> condititon);
	
	public Integer countByCondition(Map<String, Object> condititon);
	
	public abstract List<T> pageList(PageObject po);
	
	public void deleteByPrimaryKey(Long id);

	public T findByPrimaryKey(Long id);
}