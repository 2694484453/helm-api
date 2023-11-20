package vip.gpg123.helm;

import io.fabric8.kubernetes.api.model.Cluster;
import io.fabric8.kubernetes.api.model.Config;
import io.fabric8.kubernetes.api.model.ConfigBuilder;
import io.fabric8.kubernetes.api.model.Context;
import io.fabric8.kubernetes.api.model.NamedCluster;
import io.fabric8.kubernetes.api.model.NamedContext;
import org.junit.Test;
import vip.gpg123.helm.client.DefaultHelmClient;
import vip.gpg123.helm.client.HelmClient;
import vip.gpg123.helm.client.HelmRelease;
import vip.gpg123.helm.client.InstallResult;
import vip.gpg123.helm.client.ReleaseStatus;
import vip.gpg123.helm.client.Version;
import vip.gpg123.helm.client.Env;
import vip.gpg123.helm.client.Repository;

import java.util.List;

public class HelmApiApplicationTests {

    @Test
    public void getVersion() {
        HelmClient client = new DefaultHelmClient();
        Version version = client.getVersion();
        System.out.println(version);
    }

    @Test
    public void getEnv() {
        HelmClient client = new DefaultHelmClient();
        Env env = client.getEnvironment();
        System.out.println(env);
    }

    @Test
    public void getRepos() {
        HelmClient client = new DefaultHelmClient();
        List<Repository> repositoryList = client.getRepos();
        System.out.println(repositoryList);
    }

    @Test
    public void indexRepo() {
        HelmClient client = new DefaultHelmClient();
        boolean isSuccess = client.repoIndex("D:\\project\\server\\helm-repo");
        System.out.println(isSuccess);
    }

    @Test
    public void updateRepo() {
        HelmClient client = new DefaultHelmClient();
        boolean isSuccess = client.repoUpdate("gitee");
        System.out.println(isSuccess);
    }

    @Test
    public void getList() {
        HelmClient client = new DefaultHelmClient();
        List<HelmRelease> helmReleaseList = client.getReleaseList("");
        System.out.println(helmReleaseList);
    }

    @Test
    public void install() {
        HelmClient client = new DefaultHelmClient();
        InstallResult installResult = client.installWithDefault("default", "demo", "gitee/spring-boot-demo");
        System.out.println(installResult);
    }

    @Test
    public void uninstall() {
        HelmClient client = new DefaultHelmClient();
        boolean isSuccess = client.uninstall("default", "demo");
        System.out.println(isSuccess);
    }

    @Test
    public void getReleaseStatus() {
        HelmClient client = new DefaultHelmClient();
        ReleaseStatus releaseStatus = client.getReleaseStatus("default", "demo");
        System.out.println(releaseStatus);
    }

    @Test
    public void getCurrentContext() {
        HelmClient client = new DefaultHelmClient();
        Config config = client.getConfigDefault();
        System.out.println(config);
        System.out.println(client.getCurrentContext());
    }

    @Test
    public void export(){
        HelmClient client = new DefaultHelmClient();
        client.exportDefaultConfigToFile("D://config11-20");
    }

    @Test
    public void add(){
        HelmClient client = new DefaultHelmClient();
        ConfigBuilder configBuilder = new ConfigBuilder();
        configBuilder.setNewClusterLike(0,new NamedCluster(new Cluster(),"demo"));
        configBuilder.setNewContextLike(0,new NamedContext(new Context(),"demo-context"));
        client.addCluster(configBuilder);
    }

}
