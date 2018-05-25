package com.cck.mapper.system;

import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * Created by AAS on 2018/3/29.
 */
public interface BaseMapper<T> {

    int insertSelective(T t);//选择性插入

    int insertBatch(List<T> tList);//批量插入

    int deleteByPrimaryKey(String id);

    int updateByPrimaryKeySelective(T t);

    T selectByPrimaryKey(String id);//用主键查询

    List<HashMap> findAllBySearch(@Param("search") String search);//搜索条件查询

}
