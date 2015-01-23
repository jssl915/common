package com.system.prg.log.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import com.system.prg.log.mapper.SLogMapper;
import com.system.prg.util.BaseServiceImpl;
import com.system.prg.util.BusinessException;
import com.system.prg.log.entity.SLog;

@Service
@Transactional(rollbackFor = BusinessException.class)
public class SLogServiceImpl extends BaseServiceImpl<SLog> implements SLogService{

	@Autowired
	SLogMapper mapper;
	@Autowired
	public void setMapper(SLogMapper mapper) {
		super.setMapper(mapper);
	}
}
