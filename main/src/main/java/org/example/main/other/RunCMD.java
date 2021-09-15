package org.example.main.other;

import java.io.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RunCMD {

    static Lock lock = new ReentrantLock();

    public static String getToken(Lock lock1, String tokenToolPath, String timestamp) {
        String resultCode;
        StringBuilder stringBuilder = new StringBuilder();
        String command = String.format("cmd.exe /c %s\\MySSH.exe token %s", tokenToolPath, timestamp);
        // 同步代码
//        Lock lock = new ReentrantLock();  // 已证不可用，多个线程间使用的必须是同一个Lock对象
        lock.lock();
        try (
                FileInputStream fileInputStream =
                        new FileInputStream(tokenToolPath + "\\token.txt")
        ) {
            Process process = Runtime.getRuntime().exec(command);  // 执行cmd指令
            int status = process.waitFor();  // 获取cmd执行结果
            if (status == 0) {  // 成功则读取文件内容
                int i = fileInputStream.read();
                while (i != -1) {
                    stringBuilder.append((char)i);
                    i = fileInputStream.read();
                }
                resultCode = stringBuilder.toString();
            } else {
                resultCode = "请确保token程序文件完整可用。";
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();  // 未找到token.txt
            resultCode = "目录路径：" + tokenToolPath + "\n";
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();  // Runtime执行cmd命令时发生错误，或waitFor获取cmd命令结果时发生错误
            resultCode = e.getMessage();
        }
        finally {
            lock.unlock();
        }
        return resultCode;
    }


    // cmd with print error
    static void hello() {
        String[] command = new String[]{"/bin/sh", "-c", "md5sum com.runoob.hello"};
        Process process;
        int status = 0;
        String md5 = "null";
        try {
            process = Runtime.getRuntime().exec(command);
            InputStream inputStream = process.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            md5 = bufferedReader.readLine();
            status = process.waitFor();
            process.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (status != 0) {
            System.out.println("出错了");
        }
        System.out.println("md5: " + md5);
    }
}
