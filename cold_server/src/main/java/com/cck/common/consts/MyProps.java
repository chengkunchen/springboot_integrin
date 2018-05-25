package com.cck.common.consts;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by CCK on 2018/4/3.
 */
@Component
@ConfigurationProperties(prefix = "myproperties")
public class MyProps {

   private String wjtFilePath;

    public String getWjtFilePath() {
        return wjtFilePath;
    }

    public void setWjtFilePath(String wjtFilePath) {
        this.wjtFilePath = wjtFilePath;
    }
}
