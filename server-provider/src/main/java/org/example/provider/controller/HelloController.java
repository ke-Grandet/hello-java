package org.example.provider.controller;

import org.example.provider.pojo.dto.HelloDTO;
import org.example.provider.pojo.vo.HelloVO;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/hello")
public class HelloController {

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

}
