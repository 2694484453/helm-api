package vip.gpg123.helm.base;

import lombok.Getter;
import vip.gpg123.helm.client.Version;
import vip.gpg123.helm.client.Env;

/**
 * @author gaopuguang_zz
 * @version 1.0
 */
@Getter
public class OperationContext {

    protected Version version;

    protected Env env;

    protected String kubeContext;
}
