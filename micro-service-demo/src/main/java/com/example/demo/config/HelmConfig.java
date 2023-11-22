package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vip.gpg123.helm.client.DefaultHelmClient;
import vip.gpg123.helm.client.HelmClient;

/**
 * @author gaopuguang_zz
 * @version 1.0
 */
@Configuration
public class HelmConfig {

    /**
     * 注册客户端
     * @return client
     */
    @Bean
    protected HelmClient helmClient(){
        return new DefaultHelmClient();
    }
}
