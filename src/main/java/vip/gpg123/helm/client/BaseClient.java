package vip.gpg123.helm.client;

import vip.gpg123.helm.env.Env;

/**
 * @author gaopuguang_zz
 * @version 1.0
 * @description: TODO
 * @date 2023/10/30 12:03
 */
public abstract class BaseClient implements Client {

    private Env env;

    private Version version;

    /**
     * 获取环境变量
     *
     * @return env
     */
    @Override
    public Env getEnv() {
        return env;
    }

    /**
     * 获取版本
     *
     * @return repo
     */
    @Override
    public Version getVersion() {
        return version;
    }
}
