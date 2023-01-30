package com.dzidziguri.springapplication.propertysourcespringapplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppService {

    @Autowired
    private AppProperties appProperties;

    public AppProperties getAppProperties() {
        return appProperties;
    }
}
