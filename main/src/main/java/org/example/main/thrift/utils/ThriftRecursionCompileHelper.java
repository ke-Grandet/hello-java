package org.example.main.thrift.utils;

import java.io.File;

/**
 * 递归编译thrift文件
 */
public class ThriftRecursionCompileHelper {

    static String thriftPath = "F:\\IdeaProjects\\VSAP网关\\thrift-java\\thrift-0.10.0.exe";
    static String filePath = "F:\\IdeaProjects\\VSAP网关\\thrift-java";

    /**
     * 静态方法递归编译thrift文件
     *
     * @param file       thrift文件的根目录
     * @param thriftPath thrift.exe所在路径
     * @throws Exception Runtime异常
     */
    static void compile(File file, String thriftPath) throws Exception {
        if (file.isFile()) {
            Process process = Runtime.getRuntime().exec(
                    new String[]{thriftPath, "--gen", "java", file.getAbsolutePath()}
            );
            int code = process.waitFor();
        } else if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files == null) {
                System.out.println("绝对路径错误！");
                return;
            }
            for (File item : files) {
                compile(item, thriftPath);
            }
        } else {
            System.out.println("目标既不是文件也不是目录");
        }
    }

}
