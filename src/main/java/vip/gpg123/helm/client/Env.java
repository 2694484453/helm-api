package vip.gpg123.helm.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author gaopuguang_zz
 * @version 1.0
 */
@Data
@ToString
public class Env implements Serializable {

    private String helmBin;

    private String helmBurstLimit;

    private String helmCacheHome;

    private String helmConfigHome;

    private String helmDataHome;

    private String helmDebug;

    private String helmKubeapiserver;

    private String helmKubeasgroups;

    private String helmKubeasuser;

    private String helmKubecafile;

    private String helmKubecontext;

    private String helmKubeinsecureSkipTlsVerify;

    private String helmKubetlsServerName;

    private String helmKubetoken;

    private String helmMaxHistory;

    private String helmNamespace;

    private String helmPlugins;

    private String helmRegistryConfig;

    private String helmRepositoryCache;

    private String helmRepositoryConfig;
}
