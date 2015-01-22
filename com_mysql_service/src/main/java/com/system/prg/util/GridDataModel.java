package com.system.prg.util;

import java.util.ArrayList;
import java.util.List;

public class GridDataModel<T> {
	private int total;
	private List<T> rows = new ArrayList<T>();

	public List<T> getRows() {
		return this.rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public int getTotal() {
		return this.total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
}