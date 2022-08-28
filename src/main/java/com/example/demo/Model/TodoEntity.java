package com.example.demo.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Builder // 빌더패턴을 사용해 오브젝트 생성
@NoArgsConstructor // 매개변수가 없는 생성자 구현
@AllArgsConstructor// 클래스의 모든 멤버 변수를 매개변수로 받는 생성자를 구현
@Data // 클래스 멤버 변수의 Getter/Setter 메서드를 구현
@Entity
@Table(name = "Todo")
public class TodoEntity {
    @Id // 기본키 지정
    @GeneratedValue(generator = "system-uuid") //ID를 자동으로 생성
    @GenericGenerator(name="system-uuid",strategy = "uuid") // 나만의 Generator를 사용하고 싶을 경우
    // 문자열 형태 : uuid , 매개변수 strategy : uuid
    private String id;
    private String userId;
    private String title;
    private boolean done;
}
