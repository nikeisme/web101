package com.example.demo.DTO;

import lombok.Data;

@Data
public class TestRequestBodyDTO { // 반환하고자하는 리소스가 복잡할 때 사용
    private int id;
    private String message;
}
