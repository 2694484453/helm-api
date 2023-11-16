package vip.gpg123.helm.client;

import lombok.Data;

import java.io.Serializable;

/**
 * @author gaopuguang_zz
 * @version 1.0
 * @description: TODO
 * @date 2023/11/16 18:21
 */
@Data
public class LastRun implements Serializable {

    private String startedAt;

    private String completedAt;

    private String phase;
}
