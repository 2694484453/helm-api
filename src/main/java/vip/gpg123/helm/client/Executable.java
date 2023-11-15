package vip.gpg123.helm.client;

import java.io.InputStream;
import java.util.List;

/**
 * @author Administrator
 */
public interface Executable {

    /***
     * 获取版本
     * @return version
     */
    Version getVersion();

    /**
     * 获取环境变量
     *
     * @return env
     */
    Env getEnvironment();

    /**
     * 获取仓库列表
     *
     * @return repo
     */
    List<Repository> getRepos();


    /**
     * 获取部署实例
     *
     * @param namespace ns
     * @return list
     */
    List<HelmRelease> getReleaseList(String namespace);


    /**
     * 默认参数安装
     *
     * @param namespace ns
     * @param releaseName rn
     * @param chartName cn
     * @return ir
     */
    InstallResult install(String namespace, String releaseName, String chartName);

    /**
     * 指定参数安装
     *
     * @param namespace ns
     * @param params    p
     * @return r
     */
    InstallResult installWithParams(String namespace, List<String> params);


    /**
     * 使用文件安装
     *
     * @param namespace ns
     * @param filePath  fp
     * @return rs
     */
    InstallResult installWithFile(String namespace, String filePath);

    /**
     * 使用输入流安装
     *
     * @param namespace   ns
     * @param inputStream in
     * @return rs
     */
    InstallResult installWithInputStream(String namespace, InputStream inputStream);


}
