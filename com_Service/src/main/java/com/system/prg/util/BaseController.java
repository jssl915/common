package com.system.prg.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseController {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	protected PageObject po;

	public PageObject getPo() {
		return this.po;
	}

	public void setPo(PageObject po) {
		this.po = po;
	}

	protected PageObject getPageObject(HttpServletRequest request)throws BusinessException {
		PageObject _po = new PageObject();

		String startStr = request.getParameter("start");

		String limitStr = request.getParameter("limit");

		String orderByClause = "";
		String property = request.getParameter("sortBy");
		if ((property != null) && (!"".equals(property))) {
			orderByClause = getColumn(property) + " "
					+ request.getParameter("sortDir");
		}
		int start = Integer.parseInt(startStr);
		int limit = Integer.parseInt(limitStr);
		if (limit == 0) {
			limit = 2147483647;
		}
		_po.setExeQuery(_po.isExeQuery());

		_po.setPageSize(limit);
		_po.setStart(start);
		_po.getCondition().put("beginoffset", Integer.valueOf(start));
		_po.getCondition().put("endoffset", Integer.valueOf(start + limit));
		_po.getCondition().put("rowquantity", Integer.valueOf(limit));
		_po.getCondition().put("orderByClause", orderByClause);
		return _po;
	}

	protected PageObject getPageObject(HttpServletRequest request,
			String orderByClause) throws BusinessException {
		PageObject _po = new PageObject();

		String startStr = request.getParameter("start");

		String limitStr = request.getParameter("limit");

		String property = request.getParameter("sortBy");
		if ((property != null) && (!"".equals(property))) {
			orderByClause = getColumn(property) + " "
					+ request.getParameter("sortDir");
		}
		int start = Integer.parseInt(startStr);
		int limit = Integer.parseInt(limitStr);
		if (limit == 0) {
			limit = 2147483647;
		}
		_po.setExeQuery(_po.isExeQuery());

		_po.setPageSize(limit);
		_po.setStart(start);

		_po.getCondition().put("beginoffset", Integer.valueOf(start));
		_po.getCondition().put("endoffset", Integer.valueOf(start + limit));
		_po.getCondition().put("rowquantity", Integer.valueOf(limit));
		_po.getCondition().put("orderByClause", orderByClause);
		return _po;
	}

	public String getColumn(String property) {
		StringBuffer buffer = new StringBuffer(property);
		for (int i = 0; i < buffer.length(); i++) {
			char c = buffer.charAt(i);
			if ((c >= 'A') && (c <= 'Z')) {
				buffer.insert(i++, '_');
			}
		}
		return buffer.toString();
	}

	public <T> void list(List<T> list, HttpServletResponse response)throws BusinessException {
		GridDataModel model = new GridDataModel();
		model.setRows(list);
		model.setTotal(this.po.getTotalCount());
		writeToPage(JsonMapper.buildNormalMapper().toJson(model), response);
	}

	public <T> void list(List<T> list, PageObject po,HttpServletResponse response) throws BusinessException {
		GridDataModel model = new GridDataModel();
		model.setRows(list);
		model.setTotal(po.getTotalCount());
		writeToPage(JsonMapper.buildNormalMapper().toJson(model), response);
	}

	protected void writeToPage(String content, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			pw.write(content);
		} catch (IOException e) {
			logger.warn("write to page error: ", e);
		} finally {
			if (pw != null) {
				pw.flush();
				pw.close();
			}
		}
	}
}