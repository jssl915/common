package com.system.prg.user.web;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ConcurrentAccessException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.system.prg.user.entity.SUser;
import com.system.prg.user.service.SUserService;
import com.system.prg.util.MD5Encoder;
@Component
public class DatabaseRealm extends AuthorizingRealm {
	private Logger logger = LoggerFactory.getLogger(DatabaseRealm.class);
	@Autowired
	private SUserService sUserService;
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		Object object = authcToken.getCredentials();
		// 输入密码进行MD5转码后与数据库密码进行比较
		token.setPassword(MD5Encoder.encode(String.valueOf((char[]) object)).toCharArray());
		String currentLoginIP;
		try {
			currentLoginIP = SecurityUtils.getSubject().getSession().getAttribute("currentLoginIP").toString();
		} catch (Exception e) {
			currentLoginIP ="";
		}

		if ( SecurityController.isLogin(token.getUsername()) && (!currentLoginIP.equals(SecurityController.getLastLoginIP(token.getUsername()))) ) {
			throw new ConcurrentAccessException();
		}
		try {
			Map<String, Object> condititon = new HashMap<String, Object>();
			condititon.put("userName", token.getUsername());
			condititon.put("userStatus", 1);
			List<SUser> userList = sUserService.selectByCondition(condititon);
			if(userList.size()>0) {
				SUser user = userList.get(0);
				return new SimpleAuthenticationInfo(user.getUserName(),user.getUserPwd(), getName());
			}else {
				return null;
			}
		} catch (Exception e) {
			logger.debug("shiro security auth exception. ", e);
			throw new AccountException(e.getMessage(), e);
		}
	}

	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
	 */
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		return null;
	}

	/**
	 * 更新用户授权信息缓存.
	 */
	public void clearCachedAuthorizationInfo(String principal) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
		clearCachedAuthorizationInfo(principals);
	}

	/**
	 * 清除所有用户授权信息缓存.
	 */
	public void clearAllCachedAuthorizationInfo() {
		Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
		if (cache != null) {
			for (Object key : cache.keys()) {
				cache.remove(key);
			}
		}
	}

}
