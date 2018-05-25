package com.cck.service.system;

import com.cck.common.result.PageResult;

import java.util.List;


/**
 * Created by CCK on 2018/3/29.
 */
//O代表实体    M代表 mapper
public interface BaseService<O,M> {

    int insertSelective(O o);//选择性插入

    default int insertBatch(List<O> oList)//批量插入 实现类可以不实现
    {
        return 0;//返回零表示失败
    }


    int deleteByPrimaryKey(String id);


    int updateByPrimaryKeySelective(O o);

    O selectByPrimaryKey(String id);//用主键查询

    PageResult findListByPage(int pageNum, String search);

}
