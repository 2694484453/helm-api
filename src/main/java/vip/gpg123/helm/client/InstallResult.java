package vip.gpg123.helm.client;

import lombok.Data;

import java.io.Serializable;

/**
 * @author gaopuguang_zz
 * @version 1.0
 * @description: TODO
 * @date 2023/11/15 18:46
 */
@Data
public class InstallResult implements Serializable {

    private String name;

    private String lastDeployed;

    private String namespace;

    private String status;

    private String revision;
}
