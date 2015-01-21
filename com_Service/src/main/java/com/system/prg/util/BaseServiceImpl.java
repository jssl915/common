package com.system.prg.util;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;


public abstract class BaseServiceImpl<T> implements BaseService<T> {
	
	protected BaseMapper<T> mapper;

	public void setMapper(BaseMapper<T> mapper) {
		this.mapper = mapper;
	}
	
	@Override
	public T insert(T obj) {
		this.mapper.insert(obj);
		return obj;
	}
	
	@Override
	public void deleteByCondition(Map<String, Object> condition) {
		mapper.deleteByCondition(condition);
	}

	@Override
	public void updateByCondition(T record) {
		mapper.updateByCondition(record);
	}
	@Override
	public List<T> selectByCondition(Map<String, Object> condititon) {
		return mapper.selectByCondition(condititon);
	}
	
	@Override
	public Integer countByCondition(Map<String, Object> condititon) {
		return mapper.countByCondition(condititon);
	}
	@Override
	public List<T> pageList(PageObject po) {
		RowBounds rowBounds = new  RowBounds(po.getStart(),po.getPageSize()) ;
		return this.mapper.selectByCondition(po.getCondition(),rowBounds);
	}
	
	@Override
	public T findByPrimaryKey(Long id) {
		return mapper.findByPrimaryKey(id);
	}
	
	@Override
	public void deleteByPrimaryKey(Long id) {
		mapper.deleteByPrimaryKey(id);
	}
}