package com.cck.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TableInfo {
	
	private String tableName;
	private List<SelectFilter> filters;
	private Map<String,String> data;
	private String orders;
	private String[] selectColumns;
	private boolean groupFlag;
	
	public TableInfo() {
		super();
		filters = new ArrayList<SelectFilter>();
	}
	
	public TableInfo(String tableName) {
		super();
		this.tableName = tableName;
		this.groupFlag = false;
		this.filters = new ArrayList<SelectFilter>();
	}

	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public List<SelectFilter> getFilters() {
		return filters;
	}
	public void setFilters(List<SelectFilter> filters) {
		this.filters = filters;
	}
	public Map<String, String> getData() {
		return data;
	}
	public void setData(Map<String, String> data) {
		this.data = data;
	}
	public String getOrders() {
		return orders;
	}
	public void setOrders(String orders) {
		this.orders = orders;
	}
	public String[] getSelectColumns() {
		return selectColumns;
	}
	public void setSelectColumns(String[] selectColumns) {
		this.selectColumns = selectColumns;
	}
	public boolean isGroupFlag() {
		return groupFlag;
	}
	public void setGroupFlag(boolean groupFlag) {
		this.groupFlag = groupFlag;
	}
	
}
