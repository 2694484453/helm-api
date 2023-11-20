package vip.gpg123.helm.client;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.system.OsInfo;
import cn.hutool.system.SystemUtil;
import io.fabric8.kubernetes.api.model.Config;
import io.fabric8.kubernetes.api.model.ConfigBuilder;
import io.fabric8.kubernetes.api.model.NamedAuthInfo;
import io.fabric8.kubernetes.api.model.NamedCluster;
import io.fabric8.kubernetes.api.model.NamedContext;
import io.fabric8.kubernetes.client.internal.KubeConfigUtils;
import vip.gpg123.helm.base.BaseOperation;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * @author gaopuguang_zz
 * @version 1.0
 */
public class DefaultHelmClient extends BaseOperation implements HelmClient {


    /**
     * 获取默认配置
     */
    @Override
    public Config getConfigDefault() {
        String filePath = getSystemDefaultConfigFilePath();
        try {
            return KubeConfigUtils.parseConfig(new File(filePath));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 从文件获取配置
     */
    @Override
    public Config getConfigFromFile(String filePath) {
        try {
            return KubeConfigUtils.parseConfig(new File(filePath));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取默认context
     *
     * @return r
     */
    @Override
    public String getCurrentContext() {
        String configFilePath = getSystemDefaultConfigFilePath();
        File configFile = new File(configFilePath);
        try {
            Config config = KubeConfigUtils.parseConfig(configFile);
            return config.getCurrentContext();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据config获取当前context
     *
     * @param config c
     * @return nc
     */
    @Override
    public String getCurrentContext(Config config) {
        return config.getCurrentContext();
    }

    /**
     * 导出配置文件到指定目录
     *
     * @param path p
     * @return b
     */
    @Override
    public boolean exportDefaultConfigToFile(String path) {
        File file = new File(path);
        FileUtil.touch(file);
        //
        InputStream inputStream = FileUtil.getInputStream(getSystemDefaultConfigFilePath());
        OutputStream outputStream = FileUtil.getOutputStream(file);
        IoUtil.copy(inputStream, outputStream);
        try {
            inputStream.close();
            outputStream.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return true;
    }


    /**
     * 添加config
     *
     * @param configBuilder c
     * @return b
     */
    @Override
    public boolean addCluster(ConfigBuilder configBuilder) {
        //获取原有config
        Config sourceConfig = getConfigDefault();
        //配置config对象
        Config newConfig = configBuilder.build();
        // 添加cluster
        List<NamedCluster> clusterList = sourceConfig.getClusters();
        clusterList.addAll(newConfig.getClusters());
        // 添加context
        List<NamedContext> contextList = sourceConfig.getContexts();
        contextList.addAll(newConfig.getContexts());
        // 添加users
        List<NamedAuthInfo> userList = sourceConfig.getUsers();
        userList.addAll(newConfig.getUsers());
        sourceConfig.setClusters(clusterList);
        sourceConfig.setContexts(contextList);
        sourceConfig.setUsers(userList);
        try {
            KubeConfigUtils.persistKubeConfigIntoFile(sourceConfig, "d://add");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return true;
    }


    private static String getSystemDefaultConfigFilePath() {
        String configFilePath = "";
        OsInfo osInfo = SystemUtil.getOsInfo();
        if (osInfo.isWindows()) {
            String userName = SystemUtil.getUserInfo().getName();
            configFilePath = "C:\\Users\\" + userName + "\\.kube\\config";
        }
        if (osInfo.isLinux()) {
            configFilePath = "/root/.kube/config";
        }
        if (osInfo.isMac()) {
            configFilePath = "/root/.kube/config";
        }
        if (!FileUtil.exist(configFilePath)) {
            throw new RuntimeException("could not find the default configFile in your system path,please check!");
        }
        return configFilePath;
    }
}
