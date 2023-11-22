package com.example.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.gpg123.helm.client.HelmClient;

/**
 * @author gaopuguang_zz
 * @version 1.0
 */
@RestController
@RequestMapping("/api/helm/")
public class HelmController {


    @Autowired
    private HelmClient helmClient;

    /**
     * 获取客户端版本示例
     * @return o
     */
    @GetMapping(value = "version")
    private Object getVersion() {
        return helmClient.getVersion();
    }

}
