package vip.gpg123.helm.client;

import lombok.Data;

/**
 * @author gaopuguang_zz
 * @version 1.0
 * @description: TODO
 * @date 2023/10/30 11:53
 */
@Data
public class Version {

    private String version;

    private String gitCommit;

    private String gitTreeState;

    private String goVersion;
}
