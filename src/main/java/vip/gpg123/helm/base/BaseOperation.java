package vip.gpg123.helm.base;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import cn.hutool.system.OsInfo;
import cn.hutool.system.SystemUtil;
import vip.gpg123.helm.client.Executable;
import vip.gpg123.helm.client.HelmRelease;
import vip.gpg123.helm.client.InstallResult;
import vip.gpg123.helm.client.Version;
import vip.gpg123.helm.client.Env;
import vip.gpg123.helm.client.Repository;
import vip.gpg123.helm.util.ExecUtil;
import vip.gpg123.helm.util.HelmResultVo;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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
     * 安装
     *
     * @param namespace   ns
     * @param releaseName rn
     * @param chartName   cn
     * @return ir
     */
    @Override
    public InstallResult install(String namespace, String releaseName, String chartName) {
        List<String> init = prefix();
        init.add("helm");
        init.add("install");
        init.add(releaseName);
        init.add(chartName);
        init.add(namespace.isEmpty() ? "" : "-n " + namespace);
        init.add("--output=json");
        HelmResultVo helmResultVo = ExecUtil.executeHelm(init);
        Object object = helmResultVo.getResult();
        String JsonStr = (String) object;
        if (helmResultVo.getExitCode() == 0) {
            InstallResult installResult = JSONUtil.toBean(JsonStr, InstallResult.class);

        }

        return null;
    }

    /**
     * 指定参数安装
     *
     * @param namespace ns
     * @param params    p
     * @return r
     */
    @Override
    public InstallResult installWithParams(String namespace, List<String> params) {
        return null;
    }

    /**
     * 使用文件安装
     *
     * @param namespace ns
     * @param filePath  fp
     * @return rs
     */
    @Override
    public InstallResult installWithFile(String namespace, String filePath) {
        return null;
    }

    /**
     * 使用输入流安装
     *
     * @param namespace   ns
     * @param inputStream in
     * @return rs
     */
    @Override
    public InstallResult installWithInputStream(String namespace, InputStream inputStream) {
        return null;
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
