package vip.gpg123.helm;

import org.junit.Test;
import vip.gpg123.helm.client.DefaultHelmClient;
import vip.gpg123.helm.client.HelmClient;

public class HelmApiApplicationTests {

    @Test
    public void contextLoads() {
        HelmClient client = new DefaultHelmClient();
        client.getVersion();

    }

}
