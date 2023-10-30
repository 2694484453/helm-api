package vip.gpg123.helm.client;

import vip.gpg123.helm.env.Env;

/**
 * @author Administrator
 */
public interface Client {

    /**
     * 获取环境变量
     *
     * @return env
     */
    Env getEnv();

    /**
     * 版本
     *
     * @return v
     */
    Version getVersion();

}
