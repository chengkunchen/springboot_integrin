package com.cck.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by CCK on 2018/4/9.
 */


@Component
@ConfigurationProperties(prefix = "ymlmanager")
public class YmlConfig {
    private Map<String,Object> mapProps = new HashMap<>();

    public Map<String,Object> getMapProps() {
        return mapProps;
    }

    public void setMapProps(Map<String, Object> mapProps) {
        this.mapProps = mapProps;
    }
}
