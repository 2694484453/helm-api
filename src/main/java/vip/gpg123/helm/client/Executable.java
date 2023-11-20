package vip.gpg123.helm.client;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

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
     * 刷新本地仓库索引
     *
     * @param path p
     * @return b
     */
    boolean repoIndex(String path);

    /**
     * 添加仓库
     * @param repoName rn
     * @return r
     */
    boolean repoAdd(String repoName);

    /**
     * 移除仓库
     * @param repoName rn
     * @return r
     */
    boolean repoRemove(String repoName);

    /**
     * 更新仓库索引
     *
     * @param repoName rn
     * @return b
     */
    boolean repoUpdate(String repoName);


    /**
     * 获取部署实例
     *
     * @param namespace ns
     * @return list
     */
    List<HelmRelease> getReleaseList(String namespace);

    /**
     * 获取部署状态
     *
     * @param namespace   ns
     * @param releaseName rn
     * @return rs
     */
    ReleaseStatus getReleaseStatus(String namespace, String releaseName);


    /**
     * 默认参数安装
     *
     * @param namespace   ns
     * @param releaseName rn
     * @param chartName   cn
     * @return ir
     */
    InstallResult installWithDefault(String namespace, String releaseName, String chartName);

    /**
     * 指定参数安装
     *
     * @param namespace   ns
     * @param releaseName rn
     * @param chartName   cn
     * @param params      p
     * @return r
     */
    InstallResult installWithParams(String namespace, String releaseName, String chartName, List<String> params);


    /**
     * 安装json字符串
     * @param namespace ns
     * @param releaseName rn
     * @param chartName cn
     * @param jsonStr j
     * @return r
     */
    InstallResult installWithJsonStr(String namespace, String releaseName, String chartName, String jsonStr);


    /**
     * 使用文件安装
     *
     * @param namespace   ns
     * @param releaseName rn
     * @param chartName   cn
     * @param filePath    fp
     * @return rs
     */
    InstallResult installWithFile(String namespace, String releaseName, String chartName, String filePath);


    /**
     * 使用输入流安装
     *
     * @param namespace   ns
     * @param inputStream in
     * @param releaseName rn
     * @param chartName   cn
     * @return rs
     */
    InstallResult installWithInputStream(String namespace, String releaseName, String chartName, InputStream inputStream);


    /**
     * 获取values
     *
     * @param namespace   ns
     * @param releaseName rn
     * @return r
     */
    Map<String, Object> getReleaseValues(String namespace, String releaseName);

    /**
     * 卸载
     *
     * @param namespace   ns
     * @param releaseName rn
     * @return b
     */
    boolean uninstall(String namespace, String releaseName);
}
