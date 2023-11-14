package vip.gpg123.helm.client;

import vip.gpg123.helm.env.Env;
import vip.gpg123.helm.repo.Repo;

import java.util.List;

/**
 * @author Administrator
 */
public interface Executeable {

    /***
     * 获取版本
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
    List<Repo> getRepos();
}
