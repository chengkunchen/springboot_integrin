package com.cck.mapper.data;

import com.cck.model.IdBean;
import com.cck.model.JoinInfo;
import com.cck.model.TableInfo;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;


public interface DataMapper {
	/**
	 * 查询数据列表
	 * @param tableInfo 主要查询表信息
	 * @param joins 字典表关联信息 无关联传null或空list
	 * @return
	 * @throws Exception
	 */
	public List<HashMap> findList(@Param("tableInfo") TableInfo tableInfo, @Param("joins") List<JoinInfo> joins);
	

	/**
	 * 查询数据明细
	 * @param tableInfo 主要查询表信息
	 * @param joins 字典表关联信息 无关联传null或空list
	 * @return
	 * @throws Exception
	 */
	public HashMap findDetail(@Param("tableInfo") TableInfo tableInfo, @Param("joins") List<JoinInfo> joins) ;
	

	/**
	 * 更新数据
	 * @param tableInfo 更新表信息
	 * @return 更新条数
	 */
	public int updateByMap(TableInfo tableInfo) ;
	
	/**
	 * 删除数据
	 * @param tableInfo 删除表信息
	 * @return 删除条数
	 * @throws Exception
	 */
	public int delData(TableInfo tableInfo) ;
	

	/**
	 * 新增数据(SEQ主键)
	 * @param tableInfo 新增表信息
	 * @param seqName 主键seq名称
	 * @param id 主键列名称
	 * @throws Exception
	 */
	public void saveByMap(@Param("tableInfo") TableInfo tableInfo, @Param("seqName") String seqName, @Param("id") IdBean id);

	/**
	 * 新增数据(非SEQ主键)
	 * @param tableInfo 新增表信息
	 * @throws Exception
	 */
	public void saveByMapNoSeq(@Param("tableInfo") TableInfo tableInfo);

}
