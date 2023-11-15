package vip.gpg123.helm;

import org.junit.Test;
import vip.gpg123.helm.client.DefaultHelmClient;
import vip.gpg123.helm.client.HelmClient;
import vip.gpg123.helm.client.Version;

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
        client.getEnvironment();
    }

}
