package com.example.demo.DTO;

import com.example.demo.Model.TodoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor // 매개변수가 없는 생성자 구현
@AllArgsConstructor// 클래스의 모든 멤버 변수를 매개변수로 받는 생성자를 구현
@Data // 클래스 멤버 변수의 Getter/Setter 메서드를 구현
public class TodoDTO {

    private String id;
    private String title;
    private boolean done;

    public TodoDTO(final TodoEntity entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.done = entity.isDone();
    }

    public static TodoEntity toEntity(final TodoDTO dto) {
        return TodoEntity.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .done(dto.isDone())
                .build();
    }
}
