package com.system.prg.log.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.system.prg.log.entity.SLog;
import com.system.prg.log.mapper.SLogMapper;
import com.system.prg.util.BusinessException;
import com.system.prg.util.DateUtils;
import com.system.prg.util.PageObject;

@Service
@Transactional(rollbackFor = BusinessException.class)
public class SLogServiceImpl implements SLogService {

	@Autowired
	SLogMapper mapper;

	@Override
	public SLog insert(SLog record) {
		record.setLogTime(DateUtils.getDateString("yyyy-MM-dd HH:mm:ss"));
		mapper.insert(record);
		return record;
	}

	@Override
	public void deleteByCondition(Map<String, Object> condition) {
		mapper.deleteByCondition(condition);
	}

	@Override
	public void updateByCondition(SLog record) {
		mapper.updateByCondition(record);
	}

	@Override
	public Integer countByCondition(Map<String, Object> condititon) {
		return mapper.countByCondition(condititon);
	}

	@Override
	public List<SLog> selectByCondition(Map<String, Object> condititon) {
		return mapper.selectByCondition(condititon);
	}
	
	@Override
	public List<SLog> pageList(PageObject po) {
		RowBounds rowBounds = new  RowBounds(po.getStart(),po.getPageSize()) ;
		return mapper.selectByCondition(po.getCondition(),rowBounds);
	}

	@Override
	public void deleteByPrimaryKey(Long logId) {
		mapper.deleteByPrimaryKey(logId);
	}

	@Override
	public SLog findByPrimaryKey(Long logId) {
		return mapper.findByPrimaryKey(logId);
	}
}
