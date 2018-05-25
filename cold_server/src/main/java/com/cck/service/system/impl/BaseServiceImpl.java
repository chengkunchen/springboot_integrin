package com.cck.service.system.impl;

import com.cck.common.consts.GlobDefine;
import com.cck.mapper.system.BaseMapper;
import com.cck.common.result.PageResult;
import com.cck.service.system.BaseService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;

/**
 * Created by CCK on 2018/3/30.
 */
public class BaseServiceImpl<O,M extends BaseMapper> implements BaseService<O,M> {

    @Autowired
     private M m;

    @Override
    public int insertSelective(O o) {
        return m.insertSelective(o);
    }

    @Override
    public int insertBatch(List<O> os) {
        return m.insertBatch(os);
    }

    @Override
    public int deleteByPrimaryKey(String id) {
        return m.deleteByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(O o) {
        return m.updateByPrimaryKeySelective(o);
    }

    @Override
    public O selectByPrimaryKey(String id) {
        return (O) m.selectByPrimaryKey(id);
    }

    @Override
    public PageResult findListByPage(int pageNum, String search) {
        PageHelper.startPage(pageNum, GlobDefine.PAGE_SIZE);
        List<HashMap> sss = m.findAllBySearch(search);
        return new PageResult(sss);
    }
}
