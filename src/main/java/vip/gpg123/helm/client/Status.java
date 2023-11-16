package vip.gpg123.helm.client;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author gaopuguang_zz
 * @version 1.0
 */
@Data
public class Status implements Serializable {

    private String name;

    private ReleaseInfo info;

    private String manifest;

    private List<Hook> hooks;

    private String version;

    private String namespace;
}
