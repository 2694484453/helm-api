package vip.gpg123.helm.base;

import vip.gpg123.helm.client.Executeable;
import vip.gpg123.helm.env.Env;
import vip.gpg123.helm.repo.Repo;

import java.util.List;

/**
 * @author gaopuguang_zz
 * @version 1.0
 * @description: TODO
 * @date 2023/10/30 14:02
 */
public class BaseOperation implements Executeable {
    /**
     * 获取环境变量
     *
     * @return env
     */
    @Override
    public Env getEnvironment() {

        //String command = ExecUtil.executeHelm();
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
     * 处理前缀
     * @return string[]
     */
    public static boolean prefix() {
        if () {

        }

        return false;
    }
}
