package vip.gpg123.helm.client;

import lombok.Data;

import java.io.Serializable;

/**
 * @author gaopuguang_zz
 * @version 1.0
 */
@Data
public class ReleaseInfo implements Serializable {

    private String firstDeployed;

    private String lastDeployed;

    private String deleted;

    private String description;

    private String status;

    private String notes;
}
