package com.system.prg.dict.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.system.prg.dict.entity.SDetail;
import com.system.prg.dict.mapper.SDetailMapper;
import com.system.prg.util.BaseServiceImpl;
import com.system.prg.util.BusinessException;
import com.system.prg.util.DateUtils;
import com.system.prg.util.SystemCache;

@Service
@Transactional(rollbackFor = BusinessException.class)
public class SDetailServiceImpl extends BaseServiceImpl<SDetail> implements SDetailService {

	@Autowired
	SDetailMapper mapper;
	@Autowired
	public void setMapper(SDetailMapper mapper) {	
		super.setMapper(mapper);//对base里的mapper进行动态注入
	}
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
	public void updateByCondition(SDetail record) {
		record.setUpdateTime(DateUtils.getDateString("yyyy-MM-dd HH:mm:ss"));
		mapper.updateByCondition(record);
		SystemCache.reCacheDict();
	}

	@Override
	public void deleteByPrimaryKey(Long detailId) {
		mapper.deleteByPrimaryKey(detailId);
		SystemCache.reCacheDict();
	}
}
