package com.cck.service.charts;

import com.cck.model.TableInfo;

import java.util.HashMap;
import java.util.List;

/**
 * Created by CCK on 2018/4/4.
 */
public interface AsdService {

    List<HashMap> findListByAsd(TableInfo tableInfo);
}
