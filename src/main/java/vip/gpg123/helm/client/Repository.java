package vip.gpg123.helm.client;

import lombok.Data;

import java.io.Serializable;

/**
 * @author gaopuguang_zz
 * @version 1.0
 * @description: TODO
 * @date 2023/10/30 12:00
 */
@Data
public class Repository implements Serializable {

    private String name;

    private String url;

}
