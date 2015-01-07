package com.system.prg.dict.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.system.prg.dict.entity.SDetail;
import com.system.prg.dict.mapper.SDetailMapper;
import com.system.prg.util.BusinessException;
import com.system.prg.util.DateUtils;
import com.system.prg.util.PageObject;
import com.system.prg.util.SystemCache;

@Service
@Transactional(rollbackFor = BusinessException.class)
public class SDetailServiceImpl implements SDetailService {

	@Autowired
	SDetailMapper mapper;

	@Override
	public SDetail insert(SDetail record) {
		record.setCreateTime(DateUtils.getDateString("yyyy-MM-dd HH:mm:ss"));
		record.setUpdateTime(DateUtils.getDateString("yyyy-MM-dd HH:mm:ss"));
		record.setDetailStatus(1l);
		mapper.insert(record);
		SystemCache.reCacheDict();
		return record;
	}

	@Override
	public void deleteByCondition(Map<String, Object> condition) {
		mapper.deleteByCondition(condition);
		SystemCache.reCacheDict();
	}

	@Override
	public void updateByCondition(SDetail record) {
		record.setUpdateTime(DateUtils.getDateString("yyyy-MM-dd HH:mm:ss"));
		mapper.updateByCondition(record);
		SystemCache.reCacheDict();
	}

	@Override
	public Integer countByCondition(Map<String, Object> condititon) {
		return mapper.countByCondition(condititon);
	}

	@Override
	public List<SDetail> selectByCondition(Map<String, Object> condititon) {
		return mapper.selectByCondition(condititon);
	}
	
	@Override
	public List<SDetail> pageList(PageObject po) {
		RowBounds rowBounds = new  RowBounds(po.getStart(),po.getPageSize()) ;
		return mapper.selectByCondition(po.getCondition(),rowBounds);
	}

	@Override
	public void deleteByPrimaryKey(Long detailId) {
		mapper.deleteByPrimaryKey(detailId);
		SystemCache.reCacheDict();
	}

	@Override
	public SDetail findByPrimaryKey(Long detailId) {
		return mapper.findByPrimaryKey(detailId);
	}
}
