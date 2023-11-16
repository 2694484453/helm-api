package vip.gpg123.helm.client;

import lombok.Data;

import java.io.Serializable;

/**
 * @author gaopuguang_zz
 * @version 1.0
 * @description: TODO
 * @date 2023/11/16 18:18
 */
@Data
public class Hook implements Serializable {

    private String name;

    private String kind;

    private String path;

    private String manifest;

    private String[] events;

    private LastRun lastRun;
}
