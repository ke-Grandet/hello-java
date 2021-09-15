package org.example.provider.controller;

import org.example.provider.pojo.dto.HelloDTO;
import org.example.provider.pojo.vo.HelloVO;
import org.example.provider.service.IHelloService;
import lombok.Cleanup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;


@RestController
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    IHelloService helloService;

    @GetMapping("/get/{id}")
    public String get(@PathVariable String id) {
        System.out.println("id: " + id);
        return String.format("value: %s", id);
    }

    @PostMapping("/post")
    public HelloVO post(@RequestBody HelloDTO helloDTO) {
        HelloVO helloVO = new HelloVO();
        helloVO.setFloorName(helloDTO.getFloorName());
        helloVO.setFloorNum(helloDTO.getFloorNum());
        helloVO.setTopoId(helloDTO.getTopoId());
        return helloVO;
    }

    // cmd指令数据库备份
    @RequestMapping("getTheBackupFile")
    public String getTheBackupFile(HttpServletResponse response) {
        String filePath = "D:\\backupDatabase\\";
        response.setHeader("Content-Disposition", "attachment");
        File rootDirectory = new File(filePath);
        if (!rootDirectory.exists()) {
            return String.format("备份文件存放路径%s不存在，请联系管理员", filePath);
        }
        if (!rootDirectory.isDirectory()) {
            return String.format("备份文件存放路径%s不是文件夹，请联系管理员", filePath);
        }
        File targetFile = null;
        // 遍历第一级目录
        long time = 0L;
        File[] files = rootDirectory.listFiles(File::isDirectory);
        if (files == null) {
            return String.format("备份文件存放路径%s的遍历结果为null，请联系管理员", filePath);
        }
        for (File item : files) {
            if (item.lastModified() > time) {
                targetFile = item;
                time = item.lastModified();
            }
        }
        if (targetFile == null) {
            return String.format("目标路径%s不包含任何子目录，请联系管理员", filePath);
        }
        // 遍历第二级目录
        time = 0L;
        files = targetFile.listFiles(File::isFile);
        if (files == null) {
            return String.format("目标路径%s不包含任何子文件，请联系管理员", filePath);
        }
        for (File item : files) {
            if (item.lastModified() > time) {
                targetFile = item;
                time = item.lastModified();
            }
        }
        // 将目标文件写入response输出流
        try {
            @Cleanup BufferedInputStream bis = new BufferedInputStream(new FileInputStream(targetFile));
            @Cleanup BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
            byte[] data = new byte[4096];
            int size;
            size = bis.read(data);
            while (size != -1) {
                bos.write(data, 0, size);
                size = bis.read(data);
            }
            bos.flush();
        } catch (IOException e) {
            return String.format("读取文件时发生错误，请检查目标目录下是否存在文件。错误信息：%s", e.getMessage());
        }
        return "ok";
    }


    @ResponseBody
    @PostMapping("/pause")
    public void pause(String triggerName, String triggerGroup) {
        helloService.pause(triggerName, triggerGroup);
    }

    @ResponseBody
    @PostMapping("/resume")
    public void resume(String triggerName, String triggerGroup) {
        helloService.resume(triggerName, triggerGroup);
    }


}
