package vip.gpg123.helm;

import org.junit.Test;
import vip.gpg123.helm.client.DefaultHelmClient;
import vip.gpg123.helm.client.HelmClient;
import vip.gpg123.helm.client.HelmRelease;
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
    public void getList() {
        HelmClient client = new DefaultHelmClient();
        List<HelmRelease> helmReleaseList = client.getReleaseList("");
        System.out.println(helmReleaseList);
    }

}
