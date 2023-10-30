package vip.gpg123.helm.client;

import vip.gpg123.helm.env.Env;

/**
 * @author gaopuguang_zz
 * @version 1.0
 * @description: TODO
 * @date 2023/10/30 12:05
 */
public class DefaultHelmClient extends BaseClient implements HelmClient{


    /**
     * 版本
     *
     * @return v
     */
    @Override
    public Version getVersion() {
        return super.getVersion();
    }

    /**
     * 获取环境变量
     *
     * @return env
     */
    @Override
    public Env getEnv() {
        return super.getEnv();
    }
}
