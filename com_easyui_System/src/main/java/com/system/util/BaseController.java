package com.system.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.system.prg.util.BusinessException;
import com.system.prg.util.GridDataModel;
import com.system.prg.util.JsonMapper;
import com.system.prg.util.PageObject;
import com.system.prg.util.StringUtils;

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
		PageObject po = new PageObject();
		String currPageStr = request.getParameter("page");
		String pageSizeStr = request.getParameter("rows");
		String property = request.getParameter("sort");
		int start = Integer.parseInt(currPageStr);
		int limit = Integer.parseInt(pageSizeStr);
		po.setExeQuery(po.isExeQuery());
		po.setPageSize(limit);
		po.setStart(start);
		po.getCondition().put("beginoffset", Integer.valueOf(start));
		po.getCondition().put("endoffset", Integer.valueOf(start + limit));
		po.getCondition().put("rowquantity", Integer.valueOf(limit));
		return po;
	}

	protected PageObject getPageObject(HttpServletRequest request,String orderByClause) throws BusinessException {
		PageObject po = new PageObject();
		String currPageStr = request.getParameter("page");
		String pageSizeStr = request.getParameter("rows");
		String property = request.getParameter("sort");
		if (StringUtils.isEmpty(orderByClause)) {
			if (!StringUtils.isEmpty(property)) {
				orderByClause = getColumn(property) + " "+ request.getParameter("order");
			}
		}
		int start = Integer.parseInt(currPageStr)-1;
		int limit = Integer.parseInt(pageSizeStr);
		po.setExeQuery(po.isExeQuery());
		po.setPageSize(limit);
		po.setStart(start);
		po.getCondition().put("orderByClause", orderByClause);
		po.getCondition().put("beginoffset", Integer.valueOf(start));
		po.getCondition().put("endoffset", Integer.valueOf(start + limit));
		po.getCondition().put("rowquantity", Integer.valueOf(limit));
		return po;
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
		System.out.println(JsonMapper.buildNormalMapper().toJson(model));
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