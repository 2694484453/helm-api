package vip.gpg123.helm.base;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.system.OsInfo;
import cn.hutool.system.SystemUtil;
import vip.gpg123.helm.client.Executeable;
import vip.gpg123.helm.client.Version;
import vip.gpg123.helm.env.Env;
import vip.gpg123.helm.repo.Repo;
import vip.gpg123.helm.util.ExecUtil;
import vip.gpg123.helm.util.HelmResultVo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author gaopuguang_zz
 * @version 1.0
 */
public class BaseOperation implements Executeable {

    /***
     * 获取版本
     */
    @Override
    public Version getVersion() {
        List<String> init = prefix();
        init.add("helm");
        init.add("version");
        HelmResultVo helmResultVo = ExecUtil.executeHelm(init);
        // 处理
        List<String> resultList = (List<String>) helmResultVo.getResult();
        Object object = resultList.get(0).replace("version.BuildInfo", "");
        //
        Version version = new Version();

        System.out.println(version.toString());

        return null;
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

        return null;
    }

    /**
     * 获取仓库列表
     *
     * @return repo
     */
    @Override
    public List<Repo> getRepos() {
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
