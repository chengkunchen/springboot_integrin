package com.cck.model;

import java.util.ArrayList;
import java.util.List;

public class JoinInfo {

	private int joinType;
	private String joinTable;
	private List<SelectFilter> joinFilter;
	private String[] selectColumn;
	
	/**
	 * 字典表关联构造器
	 * @param joinType 关联类型 0:left join 1:inner join 2:outer join\
	 * @param joinTable 字典表名
	 * @param selectColumn 字典表显示字段列表
	 */
	public JoinInfo(int joinType, String joinTable, String[] selectColumn) {
		super();
		this.joinType = joinType;
		this.joinTable = joinTable;
		this.joinFilter = new ArrayList<SelectFilter>();
		this.selectColumn = selectColumn;
	}
	
	public int getJoinType() {
		return joinType;
	}
	public void setJoinType(int joinType) {
		this.joinType = joinType;
	}
	public String getJoinTable() {
		return joinTable;
	}
	public void setJoinTable(String joinTable) {
		this.joinTable = joinTable;
	}
	public String[] getSelectColumn() {
		return selectColumn;
	}
	public void setSelectColumn(String[] selectColumn) {
		this.selectColumn = selectColumn;
	}
	public List<SelectFilter> getJoinFilter() {
		return joinFilter;
	}
	public void setJoinFilter(List<SelectFilter> joinFilter) {
		this.joinFilter = joinFilter;
	}
	
	public void addFilter(SelectFilter filter){
		this.joinFilter.add(filter);
	}
}
