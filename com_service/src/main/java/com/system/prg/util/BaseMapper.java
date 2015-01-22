package com.system.prg.util;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

public abstract interface BaseMapper<T>{
	
	public abstract void insert(T paramT);
	
	public void deleteByCondition(Map<String, Object> condition);
	
	public void updateByCondition(T record);
	
	public  List<T> selectByCondition(Map<String,Object> condititon);
	
	public Integer countByCondition(Map<String, Object> condititon);
	
	public abstract List<T> selectByCondition(Map<String,Object> condititon, RowBounds rowBounds);
	
	public void deleteByPrimaryKey(Long id);

	public T findByPrimaryKey(Long id);
}

