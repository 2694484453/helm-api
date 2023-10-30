package vip.gpg123.helm.client;

import vip.gpg123.helm.env.Env;

/**
 * @author Administrator
 */
public interface HelmClient extends Client {


    Env getEnv();

    /**
     * 版本
     *
     * @return v
     */
    @Override
    Version getVersion();
}
