package com.example.demo.controller;

import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.TestRequestBodyDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

// 이 컨트롤러는 RestController임을 명시한다.
// http와 관련된 코드 및 요청/응답 매핑을 스프링이 알아서 해준다.
@RequestMapping("test") // 이 메서드의 리소스와 HTTP 메서드를 지정
@RestController
public class TestController {

    @GetMapping
    public String testController() {
        return "Hello World!";
    }

    @GetMapping("/testGetMapping")
    public String testControllerWithPath() {

        return "Hello World!testGetMapping";
    }

    @GetMapping("/{id}") // URL의 경로로 넘어오는 값을 변수로 받음
    public String testControllerWithPathVariables(@PathVariable(required = false) int id){
        return "Hello World! ID " + id;
    }

    @GetMapping("/testRequestParam") // 요청 매개변수로 넘어오는 값을 변수로 받음
    public String testControllerRequestParam(@RequestParam(required = false) int id){
        return "Hello World! ID " + id;
    }

    @GetMapping("/testRequestBody")  // 반환하고자하는 리소스가 복잡할 때 사용
    public String testControllerRequestBody(@RequestBody TestRequestBodyDTO testRequestBodyDTO){
        return "Hello World! ID " + testRequestBodyDTO.getId() + "Message : " +
                testRequestBodyDTO.getMessage();
    }

    @GetMapping("/testResponseBody") //오브젝트를 리턴할 때
    public ResponseDTO<String> testControllerResponseBody() {
        List<String> list = new ArrayList<>();
        list.add("Hello! I'm ResponseDTO!");
        ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
        return response;
    }

    @GetMapping("/testResponseEntity") // HTTP 응답의 바디뿐 아니라 여러 다른 매개 변수들,
    // 예를 들어 status나 header를 조작하고 싶을 때 사용한다.
    public ResponseEntity<?> testControllerResponseEntity(){
        List<String> list = new ArrayList<>();
        list.add("Hello World! I'm ResponseEntity. And you got 400!");
        ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
        // http status를 400으로 설정
        return ResponseEntity.badRequest().body(response);
    }

}

