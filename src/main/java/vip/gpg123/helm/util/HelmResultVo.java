package vip.gpg123.helm.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author gaopuguang
 * @date 2023/6/19 14:11
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HelmResultVo implements Serializable {

    /**
     * 退出码
     */
    private int exitCode;

    /**
     * 返回信息
     */
    private String message;

    /**
     * 返回数据
     */
    private Object result;

    /**
     * 返回日志
     */
    private Object logger;

    /**
     * 处理结果
     */
    public static HelmResultVo deal(int exitCode, String message, Object result, Object logger) {
        if (exitCode == 0) {
            message = "命令执行成功";
            return new HelmResultVo(exitCode, message, result, logger);
        } else {
            // 设置detailMessage
            Throwable throwable = new Throwable(logger.toString());
            throw new RuntimeException(message,throwable);
        }
    }
}
