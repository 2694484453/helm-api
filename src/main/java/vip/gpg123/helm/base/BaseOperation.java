package vip.gpg123.helm.base;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.setting.yaml.YamlUtil;
import cn.hutool.system.OsInfo;
import cn.hutool.system.SystemUtil;
import vip.gpg123.helm.client.Env;
import vip.gpg123.helm.client.Executable;
import vip.gpg123.helm.client.HelmRelease;
import vip.gpg123.helm.client.InstallResult;
import vip.gpg123.helm.client.ReleaseStatus;
import vip.gpg123.helm.client.Repository;
import vip.gpg123.helm.client.Version;
import vip.gpg123.helm.util.ExecUtil;
import vip.gpg123.helm.util.HelmResultVo;

import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author gaopuguang_zz
 * @version 1.0
 */
public class BaseOperation implements Executable {

    /***
     * 获取版本
     */
    @Override
    public Version getVersion() {
        List<String> init = prefix();
        init.add("helm");
        init.add("version");
        init.add("--template={\\\"version\\\":\\\"{{.Version}}\\\",\\\"gitCommit\\\":\\\"{{.GitCommit}}\\\",\\\"gitTreeState\\\":\\\"{{.GitTreeState}}\\\",\\\"goVersion\\\":\\\"{{.GoVersion}}\\\"}");
        HelmResultVo helmResultVo = ExecUtil.executeHelm(init);
        // 处理
        Object object = helmResultVo.getResult();
        return JSONUtil.toBean(object.toString(), Version.class);
    }

    /**
     * 获取环境变量
     *
     * @return env
     */
    @Override
    public Env getEnvironment() {
        List<String> init = prefix();
        init.add("helm");
        init.add("env");
        HelmResultVo helmResultVo = ExecUtil.executeHelm(init);
        //处理为map
        String res = (String) helmResultVo.getResult();
        String[] arrStr = res.split(",");
        List<String> replaceList = new ArrayList<>();
        for (String str : arrStr) {
            String replaceAfter = "\"" + str.replaceFirst("=", "\":").replace("\\", "/");
            replaceList.add(replaceAfter);
        }
        String jsonStr = "{" + String.join(",", replaceList) + "}";
        System.out.println(jsonStr);
        return JSONUtil.toBean(jsonStr, Env.class);
    }

    /**
     * 获取仓库列表
     *
     * @return repo
     */
    @Override
    public List<Repository> getRepos() {
        List<String> init = prefix();
        init.add("helm");
        init.add("repo");
        init.add("list");
        init.add("--output=json");
        HelmResultVo helmResultVo = ExecUtil.executeHelm(init);
        Object object = helmResultVo.getResult();
        String jsonStr = (String) object;
        return JSONUtil.toList(jsonStr, Repository.class);
    }

    /**
     * 刷新本地仓库索引
     *
     * @param path p
     */
    @Override
    public boolean repoIndex(String path) {
        List<String> init = prefix();
        init.add("helm");
        init.add("repo");
        init.add("index");
        // 默认为当前文件夹
        String repoPath = path.isEmpty() ? "/" : path;
        init.add(repoPath);
        HelmResultVo helmResultVo = ExecUtil.executeHelm(init);
        return helmResultVo.getExitCode() == 0;
    }

    /**
     * 更新仓库索引
     *
     * @param repoName rn
     * @return b
     */
    @Override
    public boolean repoUpdate(String repoName) {
        List<String> init = prefix();
        init.add("helm");
        init.add("repo");
        init.add("update");
        init.add(repoName);
        HelmResultVo helmResultVo = ExecUtil.executeHelm(init);
        return helmResultVo.getExitCode() == 0;
    }

    /**
     * 获取部署实例
     *
     * @param namespace ns
     * @return list
     */
    @Override
    public List<HelmRelease> getReleaseList(String namespace) {
        //
        List<String> init = prefix();
        init.add("helm");
        init.add("list");
        init.add(namespace.isEmpty() ? "-A" : "-n" + namespace);
        init.add("--output=json");
        HelmResultVo helmResultVo = ExecUtil.executeHelm(init);
        Object object = helmResultVo.getResult();
        String jsonStr = (String) object;
        return JSONUtil.toList(jsonStr, HelmRelease.class);
    }

    /**
     * 获取部署状态
     *
     * @param namespace   ns
     * @param releaseName rn
     * @return rs
     */
    @Override
    public ReleaseStatus getReleaseStatus(String namespace, String releaseName) {
        List<String> init = prefix();
        init.add("helm");
        init.add("status");
        init.add(releaseName);
        init.add(namespace.isEmpty() ? "-n default" : "-n " + namespace);
        init.add("--output=json");
        HelmResultVo helmResultVo = ExecUtil.executeHelm(init);
        Object object = helmResultVo.getResult();
        String jsonStr = (String) object;
        return JSONUtil.toBean(jsonStr, ReleaseStatus.class);
    }

    /**
     * 安装
     *
     * @param namespace   ns
     * @param releaseName rn
     * @param chartName   cn
     * @return ir
     */
    @Override
    public InstallResult installWithDefault(String namespace, String releaseName, String chartName) {
        List<String> init = prefix();
        init.add("helm");
        init.add("install");
        init.add(releaseName);
        init.add(chartName);
        init.add(namespace.isEmpty() ? "" : "-n " + namespace);
        init.add("--output=json");
        HelmResultVo helmResultVo = ExecUtil.executeHelm(init);
        Object object = helmResultVo.getResult();
        String jsonStr = (String) object;
        if (helmResultVo.getExitCode() == 0) {
            return JSONUtil.toBean(jsonStr, InstallResult.class);
        } else {
            throw new RuntimeException(helmResultVo.getMessage());
        }
    }

    /**
     * 指定参数安装
     *
     * @param namespace ns
     * @param params    p
     * @return r
     */
    @Override
    public InstallResult installWithParams(String namespace, String releaseName, String chartName, List<String> params) {
        List<String> init = prefix();
        init.add("helm");
        init.add("install");
        init.add(releaseName);
        init.add(chartName);
        init.add(namespace.isEmpty() ? "" : "-n " + namespace);
        init.add("--output=json");
        HelmResultVo helmResultVo = ExecUtil.executeHelm(init);
        String jsonStr = (String) helmResultVo.getResult();
        if (helmResultVo.getExitCode() == 0) {
            return JSONUtil.toBean(jsonStr, InstallResult.class);
        }
        throw new RuntimeException(helmResultVo.getMessage());
    }

    /**
     * 安装json字符串
     *
     * @param namespace   ns
     * @param releaseName rn
     * @param chartName   cn
     * @param jsonStr     j
     * @return r
     */
    @Override
    public InstallResult installWithJsonStr(String namespace, String releaseName, String chartName, String jsonStr) {
        // 处理字符串
        if (jsonStr.isEmpty()) {
            throw new RuntimeException("jsonStr is empty!");
        }
        JSONObject jsonObject = JSONUtil.parseObj(jsonStr);
        File file = FileUtil.createTempFile(UUID.randomUUID().toString(), ".yaml", true);
        try {
            FileWriter fileWriter = new FileWriter(file);
            // 写入文件
            YamlUtil.dump(jsonObject, fileWriter);
            fileWriter.close();
            // 安装文件
            return installWithFile(namespace, releaseName, chartName, file.getAbsolutePath());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 使用文件安装
     *
     * @param namespace ns
     * @param filePath  fp
     * @return rs
     */
    @Override
    public InstallResult installWithFile(String namespace, String releaseName, String chartName, String filePath) {
        List<String> init = prefix();
        init.add("helm");
        init.add("install");
        init.add("-f");
        init.add(filePath);
        init.add(releaseName);
        init.add(chartName);
        init.add(namespace.isEmpty() ? "" : "-n " + namespace);
        init.add("--output=json");
        HelmResultVo helmResultVo = ExecUtil.executeHelm(init);
        String jsonStr = (String) helmResultVo.getResult();
        if (helmResultVo.getExitCode() == 0) {
            return JSONUtil.toBean(jsonStr, InstallResult.class);
        }
        throw new RuntimeException(helmResultVo.getMessage());
    }

    /**
     * 使用输入流安装
     *
     * @param namespace   ns
     * @param inputStream in
     * @return rs
     */
    @Override
    public InstallResult installWithInputStream(String namespace, String releaseName, String chartName, InputStream inputStream) {
        // 处理is写入临时文件
        File file = FileUtil.createTempFile(UUID.randomUUID().toString(), ".yaml", true);
        OutputStream outputStream = FileUtil.getOutputStream(file);
        IoUtil.copy(inputStream, outputStream);
        try {
            // 关闭
            inputStream.close();
            outputStream.close();
            InstallResult installResult = installWithFile(namespace, releaseName, chartName, file.getAbsolutePath());
            // 安装完成后删除临时文件
            FileUtil.del(file);
            return installResult;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取values
     *
     * @param namespace   ns
     * @param releaseName rn
     * @return r
     */
    @Override
    public Map<String, Object> getReleaseValues(String namespace, String releaseName) {
        List<String> init = prefix();
        init.add("helm");
        init.add("get");
        init.add("values");
        init.add(releaseName);
        init.add(namespace.isEmpty() ? "" : "-n " + namespace);
        init.add("--output=json");
        HelmResultVo helmResultVo = ExecUtil.executeHelm(init);
        if (helmResultVo.getExitCode() == 0) {
            return (Map<String, Object>) helmResultVo.getResult();
        }
        throw new RuntimeException(helmResultVo.getMessage());
    }

    /**
     * 卸载
     *
     * @param namespace   ns
     * @param releaseName rn
     * @return b
     */
    @Override
    public boolean uninstall(String namespace, String releaseName) {
        List<String> init = prefix();
        init.add("helm");
        init.add("uninstall");
        init.add(namespace.isEmpty() ? "" : "-n " + namespace);
        HelmResultVo helmResultVo = ExecUtil.executeHelm(init);
        if (helmResultVo.getExitCode() == 0) {
            return true;
        }
        throw new RuntimeException(helmResultVo.getMessage());
    }

    /**
     * 处理前缀,初始化
     *
     * @return string[]
     */
    public static List<String> prefix() {
        List<String> list = new ArrayList<>();
        OsInfo osInfo = SystemUtil.getOsInfo();
        if (ObjectUtil.isNotEmpty(osInfo)) {
            String osName = osInfo.getName();
            switch (osName) {
                case "Linux":
                    list.add("/bin/sh");
                    break;
                case "Windows":
                    list.add("cmd.exe");
                    list.add("/c");
                    break;
                case "Unix":
                    break;
                default:
                    break;
            }
        }
        return list;
    }
}
