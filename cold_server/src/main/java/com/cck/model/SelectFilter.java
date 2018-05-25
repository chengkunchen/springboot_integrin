package com.cck.model;

public class SelectFilter {
	
	private String columnName;
	private String filterType;
	private String filterValue;

	public SelectFilter() {
		super();
	}

	public SelectFilter(String columnName, String filterType, String filterValue) {
		this();
		this.columnName = columnName;
		this.filterType = filterType;
		this.filterValue = filterValue;
	}
	
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getFilterType() {
		return filterType;
	}
	public void setFilterType(String filterType) {
		this.filterType = filterType;
	}
	public String getFilterValue() {
		return filterValue;
	}
	public void setFilterValue(String filterValue) {
		this.filterValue = filterValue;
	}
	
}
