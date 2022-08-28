package com.example.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder // 빌더패턴을 사용해 오브젝트 생성
@NoArgsConstructor // 매개변수가 없는 생성자 구현
@AllArgsConstructor// 클래스의 모든 멤버 변수를 매개변수로 받는 생성자를 구현
@Data // 클래스 멤버 변수의 Getter/Setter 메서드를 구현
public class ResponseDTO<T> {

    private String error;
    private List<T> data;

}
