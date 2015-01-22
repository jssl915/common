package com.system.prg.util;

import java.util.List;

public class Tree<E> {
	 
	protected String text;
	 
	protected boolean expanded;
	 
	protected List<E> children;
	 
	protected String id;
	 
	protected String pid;
	
	protected String classes;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isExpanded() {
		return expanded;
	}

	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}

	public List<E> getChildren() {
		return children;
	}

	public void setChildren(List<E> children) {
		this.children = children;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getClasses() {
		return classes;
	}

	public void setClasses(String classes) {
		this.classes = classes;
	}
	
	
	
}
