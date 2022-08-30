package com.example.demo.controller;

import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.TodoDTO;
import com.example.demo.model.TodoEntity;
import com.example.demo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController // 내부에 @Component 어노테이션을 갖고 있다. @Service @RestController 모두 자바 빈, 스프링이 관리
@RequestMapping("todo")
// Controller
// 1. HTTP요청과 응답을 어떻게 넘겨받고 리턴하느냐 / 외부세계와 통신하는 규약 정의
public class TodoController {

    @Autowired // 스프링이 TodoController 오브젝트를 생성할 때, @Autowired가 붙어있다는 것을 확인
    // 알아서 빈을 찾은 다음 그 빈을 이 인스턴스 멤버 변수에 연결하는 뜻
    // Todocontroller를 초기화할 때, 스프링은 알아서 TodoService를 초기화 또는 검색해 TodoController에 자동으로 주입
    private TodoService service;

    @GetMapping("/test")
    public ResponseEntity<?> testTodo() {
        String str = service.testService(); // 테스트 서비스 사용
        List<String> list = new ArrayList<>();
        list.add(str);
        ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
        return ResponseEntity.ok().body(response);
    }

   @PostMapping
   public ResponseEntity<?> createTodo(@RequestBody TodoDTO dto) {
       try {
           String temporaryUserId = "temporary-user";

           // 1. TodoEntity로 변환한다.
           TodoEntity entity = TodoDTO.toEntity(dto);

           // 2. id를 null로 초기화한다.생성 당시에는 id가 없어야 하기 때문이다.
           entity.setId(null);

           // 3. 임시 사용자 아이디를 설정해준다. 지금은 인증과 인가기능이 없다.
           // 사용자 temporary-user만 로긍인 없이 사용할 수 있는 애플리케이션인 상태
           entity.setUserId(temporaryUserId);

           // 4. 서비스를 이용해 Todo 엔티티를 생성한다.
           List<TodoEntity> entities = service.create(entity);

           // 5. 자바 스트림을 이용해 리턴된 엔티티 리스트를 TodoDTO 리스트로 변환한다.
           List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());

           // 6. 변환된 TodoDTO 리스트를 이용해 ResponseDTO를 초기화한다.
           ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();

           // 7. ResponseDTO를 리턴한다.
           return ResponseEntity.ok().body(response);

       } catch (Exception e) {
           String error = e.getMessage();
           ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(error).build();
           return ResponseEntity.badRequest().body(response);
       }
   }

   // 검색 API 구현
   @GetMapping
    public ResponseEntity<?> retrieveTodoList() {
     String temporaryUserId = "temporary-user"; // temporary user id

       // 1. 서비스 메서드의 retrieve() 메서드를 사용해 Todo 리스트를 가져온다.
       List<TodoEntity> entities = service.retrieve(temporaryUserId);

       // 2. 자바 스트림을 이용해 리턴된 엔티티 리스트를 TodoDTO리스트로 변환한다.
       List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());

       // 3. 변환된 TodoDTO리스트를 이용해 ResponseDTO를 초기화한다.
       ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();

       // 4. ResponseDTO를 리턴한다.
       return ResponseEntity.ok().body(response);
   }

   @PutMapping
    public ResponseEntity<?> updateTodo(@RequestBody TodoDTO dto) {
        String temporaryUserId = "temporary-user";

        // 1. dto를 entity로 변환한다.
       TodoEntity entity = TodoDTO.toEntity(dto);

       // 2. id를 temporaryUserId로 초기화한다. 인증과 인가에서 수정할 예정
       entity.setUserId(temporaryUserId);

       // 3. 서비스를 이용해 entity를 업데이트
       List<TodoEntity> entities = service.update(entity);

       // 4. 자바 스트림을 이용해 리턴된 엔티티 리스트를 TodoDTO 리스트로 변환한다.
       List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());

       // 5. 변환된 TodoDTO 리스트를 이용해 ResponseDTO를 초기화 한다.
       ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();

       // 6. ResponseDTO를 리턴한다.
       return ResponseEntity.ok().body(response);
   }

   @DeleteMapping
    public ResponseEntity<?> deleteTodo(@RequestBody TodoDTO dto) {
        try {
            String temporaryUserId = "temporary-user"; // temporary id

            // 1. TodoEntity로 변환한다.
            TodoEntity entity = TodoDTO.toEntity(dto);

            // 2. 임시 사용자 아이디 설정. temporary-user만 사용할 수 있음. 인증과 인가 기능 추가 예정
            entity.setUserId(temporaryUserId);

            // 3. 서비스를 이용해 Entity 삭제
            List<TodoEntity> entities = service.delete(entity);

            //4. 자바 스트림을 이용해 리턴된 엔티티 리스트를 TodoDTO리스트로 변환한다.
            List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());

            // 5. 변환된 Todo리스트를 이용해 ResponseDTO를 초기화한다.
            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();

            // 6. ResponseDTO를 리턴한다.
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            // 7. 예외가 있는 경우, dto 대신 error 메세지를 넣어 리턴
            String error = e.getMessage();
            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);
        }
   }
}
