package vip.gpg123.helm.client;

import lombok.Data;

import java.io.Serializable;

/**
 * @author gaopuguang_zz
 * @version 1.0
 * @description: TODO
 * @date 2023/11/15 18:37
 */
@Data
public class HelmRelease implements Serializable {

    private String name;

    private String namespace;

    private String revision;

    private String updated;

    private String status;

    private String chart;

    private String appVersion;
}
