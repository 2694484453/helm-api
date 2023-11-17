package vip.gpg123.helm.client;

import io.fabric8.kubernetes.api.model.Config;

/**
 * @author Administrator
 */
public interface HelmClient extends Executable {


    /**
     * 获取默认配置
     * @return c
     */
    Config getConfigDefault();

    /**
     * 从文件获取配置
     *
     * @param filePath fp
     * @return c
     */
    Config getConfigFromFile(String filePath);

    /**
     * 获取默认context
     *
     * @return r
     */
    String getCurrentContext();


    /**
     * 根据config获取当前context
     *
     * @param config c
     * @return s
     */
    String getCurrentContext(Config config);
}
