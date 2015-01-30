package com.system.prg.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;

public class PageObject implements Serializable {
    private static final long serialVersionUID = 4865350795106659355L;

    private int totalCount = 0;

	private int pageSize = 3;

	private int pageIndex = 1;
	
	private int start = 1;

	private Map<String, Object> condition = new HashMap<String, Object>();

	private String url = "";

	private boolean exeQuery = true;

	private boolean selectFirst = false;

	private String countStatement = "";

	private String selectStatement = "";

	public String toString() {
		return "PageObject(totalCount=" + getTotalCount() + ", pageSize="
				+ getPageSize() + ", pageIndex=" + getPageIndex() + ", url="
				+ getUrl() + ", exeQuery=" + isExeQuery() + ", selectFirst="
				+ getSelectFirst() + ", countStatement=" + getCountStatement()
				+ ", selectStatement=" + getSelectStatement() + ")";
	}

	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof PageObject))
			return false;
		PageObject other = (PageObject) o;
		if (!other.canEqual(this))
			return false;
		if (getTotalCount() != other.getTotalCount())
			return false;
		if (getPageSize() != other.getPageSize())
			return false;
		if (getPageIndex() != other.getPageIndex())
			return false;
		if (getUrl() == null ? other.getUrl() != null : !getUrl().equals(
				other.getUrl()))
			return false;
		if (isExeQuery() != other.isExeQuery())
			return false;
		if (getSelectFirst() != other.getSelectFirst())
			return false;
		if (getCountStatement() == null ? other.getCountStatement() != null
				: !getCountStatement().equals(other.getCountStatement()))
			return false;
		return getSelectStatement() == null ? other.getSelectStatement() == null
				: getSelectStatement().equals(other.getSelectStatement());
	}

	public boolean canEqual(Object other) {
		return other instanceof PageObject;
	}

	public int hashCode() {
		int PRIME = 31;
		int result = 1;
		result = result * PRIME + getTotalCount();
		result = result * PRIME + getPageSize();
		result = result * PRIME + getPageIndex();
		result = result * PRIME + (getUrl() == null ? 0 : getUrl().hashCode());
		result = result * PRIME + (isExeQuery() ? 1231 : 1237);
		result = result * PRIME + (getSelectFirst() ? 1231 : 1237);
		result = result
				* PRIME
				+ (getCountStatement() == null ? 0 : getCountStatement()
						.hashCode());
		result = result
				* PRIME
				+ (getSelectStatement() == null ? 0 : getSelectStatement()
						.hashCode());
		return result;
	}

	public int getTotalCount() {
		return this.totalCount;
	}

	public int getPageSize() {
		return this.pageSize;
	}

	public int getPageIndex() {
		return this.pageIndex;
	}

	public String getUrl() {
		return this.url;
	}

	public boolean isExeQuery() {
		return this.exeQuery;
	}

	public String getCountStatement() {
		return this.countStatement;
	}

	public String getSelectStatement() {
		return this.selectStatement;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public void setExeQuery(boolean exeQuery) {
		this.exeQuery = exeQuery;
	}

	public void setCountStatement(String countStatement) {
		this.countStatement = countStatement;
	}

	public void setSelectStatement(String selectStatement) {
		this.selectStatement = selectStatement;
	}

	public int getOffset() {
		return this.pageSize * (this.pageIndex - 1);
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
		getCondition().put("endoffset",
				String.valueOf(getOffset() + getPageSize()));
	}

	public boolean getSelectFirst() {
		return this.selectFirst;
	}

	public void setSelectFirst(boolean selectFirst) {
		this.selectFirst = selectFirst;
	}

	public void setUrl(String url) {
		if (url != null)
			this.url = url;
	}

	public Map<String, Object> getCondition() {
		return this.condition;
	}

	@SuppressWarnings("unchecked")
	public void setCondition(Object obj) {
		try {
			getCondition().putAll(PropertyUtils.describe(obj));
		} catch (Exception e) {
			//throw new Exception("获取查询条件出错！", e);
		}
	}

	
	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public String getsCondition() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.putAll(this.condition);
		map.put("countStatement", this.countStatement);
		map.put("selectStatement", this.selectStatement);
		map.put("pageSize", String.valueOf(this.pageSize));
		map.put("pageIndex", String.valueOf(this.pageIndex));
		map.put("url", this.url);
		map.put("exeQuery", Boolean.valueOf(this.exeQuery));
		map.put("selectFirst", Boolean.valueOf(this.selectFirst));
		String result = ConditionUtil.getStringByMap(map);
		return result;
	}
}