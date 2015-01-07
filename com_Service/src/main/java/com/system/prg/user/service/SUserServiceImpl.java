package com.system.prg.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.system.prg.role.mapper.SUserRoleMapper;
import com.system.prg.user.entity.SUser;
import com.system.prg.user.mapper.SUserMapper;
import com.system.prg.util.BusinessException;
import com.system.prg.util.DateUtils;
import com.system.prg.util.MD5Encoder;
import com.system.prg.util.PageObject;

@Service
@Transactional(rollbackFor = BusinessException.class)
public class SUserServiceImpl implements SUserService {

	@Autowired
	SUserMapper mapper;
	@Autowired
	SUserRoleMapper sUserRoleMapper;

	@Override
	public SUser insert(SUser record) {
		record.setUserStatus(1l);
		record.setUserType(1l);
		record.setCreateTime(DateUtils.getDateString("yyyy-MM-dd HH:mm:ss"));
		record.setUpdateTime(DateUtils.getDateString("yyyy-MM-dd HH:mm:ss"));
		record.setUserPwd(MD5Encoder.encode(record.getUserPwd()));
		mapper.insert(record);
		return record;
	}

	@Override
	public void deleteByCondition(Map<String, Object> condition) {
		mapper.deleteByCondition(condition);
	}

	@Override
	public void updateByCondition(SUser record) {
		record.setUpdateTime(DateUtils.getDateString("yyyy-MM-dd HH:mm:ss"));
		mapper.updateByCondition(record);
	}
	
	@Override
	public void updateUserPwd(SUser record){
		record.setUserPwd(MD5Encoder.encode(record.getUserPwd()));
		record.setUpdateTime(DateUtils.getDateString("yyyy-MM-dd HH:mm:ss"));
		mapper.updateByCondition(record);
	}

	@Override
	public Integer countByCondition(Map<String, Object> condititon) {
		return mapper.countByCondition(condititon);
	}

	@Override
	public List<SUser> selectByCondition(Map<String, Object> condititon) {
		return mapper.selectByCondition(condititon);
	}
	
	@Override
	public List<SUser> pageList(PageObject po) {
		RowBounds rowBounds = new  RowBounds(po.getStart(),po.getPageSize()) ;
		return mapper.selectByCondition(po.getCondition(),rowBounds);
	}

	@Override
	public void deleteByPrimaryKey(Long userId) {
		Map<String,Object> condition = new HashMap<String,Object>();
		condition.put("userId", userId);
		sUserRoleMapper.deleteByCondition(condition);
		mapper.deleteByPrimaryKey(userId);
		
	}

	@Override
	public SUser findByPrimaryKey(Long userId) {
		return mapper.findByPrimaryKey(userId);
	}
}
