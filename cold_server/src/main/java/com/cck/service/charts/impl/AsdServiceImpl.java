package com.cck.service.charts.impl;

import com.cck.mapper.data.DataMapper;
import com.cck.model.TableInfo;
import com.cck.service.charts.AsdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * Created by CCK on 2018/4/4.
 */
@Service("asdService")
public class AsdServiceImpl implements AsdService {
    @Autowired
    private DataMapper dataMapper;
    @Override
    public List<HashMap> findListByAsd(TableInfo tableInfo) {
        tableInfo.setSelectColumns(new String[] {"replace(jeid,'_','') jeid","typeclass","nx_id","sversion"});
        return dataMapper.findList(tableInfo,null);
}
}
