package vip.gpg123.helm.client;

import io.fabric8.kubernetes.api.model.Config;
import io.fabric8.kubernetes.api.model.ConfigBuilder;

/**
 * @author Administrator
 */
public interface HelmClient extends BaseClient {


    /**
     * 获取默认配置对象
     *
     * @return c
     */
    Config getConfigDefault();

    /**
     * 获取默认context
     *
     * @return r
     */
    String getCurrentContext();

    /**
     * 从文件获取配置
     *
     * @param filePath fp
     * @return c
     */
    Config getConfigFromFile(String filePath);


    /**
     * 根据config获取当前context
     *
     * @param config c
     * @return s
     */
    String getCurrentContext(Config config);

    /**
     * 导出配置文件到指定目录
     *
     * @param path p
     * @return b
     */
    boolean exportDefaultConfigToFile(String path);


    /**
     * 添加config
     *
     * @param configBuilder c
     * @return b
     */
    boolean addCluster(ConfigBuilder configBuilder);
}
