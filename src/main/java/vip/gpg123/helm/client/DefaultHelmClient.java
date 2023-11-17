package vip.gpg123.helm.client;

import cn.hutool.core.io.FileUtil;
import cn.hutool.system.OsInfo;
import cn.hutool.system.SystemUtil;
import io.fabric8.kubernetes.api.model.Config;
import io.fabric8.kubernetes.client.internal.KubeConfigUtils;
import vip.gpg123.helm.base.BaseOperation;

import java.io.File;

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
