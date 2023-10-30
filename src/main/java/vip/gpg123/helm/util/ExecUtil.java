package vip.gpg123.helm.util;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

/**
 * @author gaopuguang_zz
 * @version 1.0
 * @description: TODO
 * @date 2023/10/30 11:50
 */
@Slf4j
public class ExecUtil {

    /**
     * 执行command-linux环境下
     */
    public static HelmResultVo executeHelm(String[] command) {
        StringJoiner message = new StringJoiner("\n");
        List<String> data = new ArrayList<>();
        int exitCode = 1;
        Process process = null;
        try {
            //启动进程
            process = Runtime.getRuntime().exec(command);
            // 获取命令执行的输入流
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                // 记录正确结果
                data.add(line);
            }
            // 获取命令执行的错误流
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream(), StandardCharsets.UTF_8));
            while ((line = errorReader.readLine()) != null) {
                System.out.println(line);
                // 记录错误结果
                message.add(line);
            }
            // 退出码
            exitCode = process.waitFor();
            //关闭流释放资源
            process.getOutputStream().close();
            reader.close();
            errorReader.close();
        } catch (IOException | InterruptedException e) {
            // 记录异常信息
            message.add(e.getMessage());
            e.printStackTrace();
        } finally {
            if (process != null) {
                // 销毁当前进程，阻断当前命令执行
                process.destroy();
            }
        }

        String logMessage = "执行命令：" + Arrays.toString(command) + ",执行结果：" + (exitCode == 0 ? "成功" : "失败");
        //log.info(logMessage);
        return HelmResultVo.deal(exitCode, message.toString(), data, logMessage);
    }


}
