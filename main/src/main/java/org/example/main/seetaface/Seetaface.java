package org.example.main.seetaface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Seetaface {

    public static class CmdException extends Exception {
        private final String result;
        private final String error;
        private final int status;

        public CmdException(String result, String error, int status, Exception e) {
            super(e.getMessage());
            this.result = result;
            this.error = error;
            this.status = status;
            this.setStackTrace(e.getStackTrace());
        }

        @Override
        public String getLocalizedMessage() {
            return String.format(
                    "%s\nresult: %s\nerror: %s\nstatus: %d", this.getMessage(), result, error, status);
        }
    }

    private static final String seetafacePath = "/home/testman/hello/seetafacecmp";  // seetafacecmp程序的路径
    private static final String modelPath = "/home/testman/hello/model";  // seetafacecmp程序依赖的model目录的路径

    // 返回值为相似度
    public double seetaface(String pathImg1, String pathImg2) throws Exception {
        String faceCommand = seetafacePath + " " + pathImg1 + " " + pathImg2 + " " + modelPath;
        String[] command = new String[]{"/bin/sh", "-c", faceCommand};
        Process process;
        int status = 0;
        String result = "这里是结果信息";
        String error = "这里是错误信息";
        try {
            process = Runtime.getRuntime().exec(command);
            try (
                    BufferedReader resultReader =
                            new BufferedReader(new InputStreamReader(process.getInputStream()));
                    BufferedReader errorReader =
                            new BufferedReader(new InputStreamReader(process.getErrorStream()))
            ) {
                result = resultReader.readLine();  // seetafacecmp程序的输出流，识别结果在这输出
                error = errorReader.readLine();  // seetafacecmp程序的错误流
                status = process.waitFor();  // 等待seetafacecmp程序工作完成并获取执行状态
                process.destroy();
            } catch (IOException | InterruptedException e) {
                throw new CmdException(result, error, status, e);
            }
        } catch (IOException e) {
            throw new CmdException(result, error, status, e);
        }
        if (status != 0) {
            throw new CmdException(result, error, status, new Exception("seetafacecmp程序错误"));
        }
        try {
            if (Double.parseDouble(result) < 0) {
                switch (Integer.parseInt(result)) {
                    case -1000:
                        throw new Exception("参数错误");
                    case -999:
                        throw new Exception("图片未找到");
                    case -998:
                        throw new Exception("在图片中查找人脸失败");
                    case -997:
                        throw new Exception("读取图片出错");
                    default:
                        throw new Exception("人脸识别程序返回了非预期的结果");
                }
            } else {
                return Double.parseDouble(result);
            }
        } catch (NumberFormatException e) {
            NumberFormatException exception = new NumberFormatException("人脸识别程序返回了非预期的结果");
            exception.setStackTrace(e.getStackTrace());
            throw exception;
        }
    }
}
