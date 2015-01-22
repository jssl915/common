package com.system.util;

import java.sql.Connection;
import java.util.Properties;

import org.apache.ibatis.executor.statement.PreparedStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.RowBounds;

@Intercepts( { @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
public class DiclectStatementHandlerInterceptor implements Interceptor {

	public Object intercept(Invocation invocation) throws Throwable {
		RoutingStatementHandler statement = (RoutingStatementHandler) invocation.getTarget();
		StatementHandler handler = (StatementHandler) ReflectUtil.getClassField(statement, "delegate");
		//PreparedStatementHandler handler = (PreparedStatementHandler) ReflectUtil.getClassField(statement, "delegate");
		if (handler instanceof PreparedStatementHandler){
			RowBounds rowBounds = (RowBounds) ReflectUtil.getSuperClassField(handler, "rowBounds");
			if (rowBounds.getLimit() > 0 && rowBounds.getLimit() < RowBounds.NO_ROW_LIMIT) {
				BoundSql boundSql = statement.getBoundSql();
				String sql = boundSql.getSql();
				sql = getLimitString(sql, rowBounds.getOffset(), rowBounds.getLimit());
				ReflectUtil.setClassField(boundSql, "sql", sql);
			}
		}
		return invocation.proceed();
	}

	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties properties) {
	}
	
	public String getLimitString(String sql, int offset, int limit) {
		limit = offset+limit;
		sql = sql.trim();
		boolean isForUpdate = false;
		if ( sql.toLowerCase().endsWith(" for update") ) {
			sql = sql.substring( 0, sql.length()-11 );
			isForUpdate = true;
		}
		StringBuffer pagingSelect = new StringBuffer( sql.length()+100 );
		pagingSelect.append(sql);
		pagingSelect.append(" limit "+offset+","+limit);
		if ( isForUpdate ) {
			pagingSelect.append( " for update" );
		}
		return pagingSelect.toString();
	}
}