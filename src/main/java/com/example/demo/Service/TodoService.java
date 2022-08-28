package com.example.demo.Service;

import com.example.demo.Model.TodoEntity;
import com.example.demo.persistence.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// 용도에 따라 로그 레벨을 제공해주는 라이브러리
@Slf4j
@Service // 스프링 컴포넌트이며 기능적으로는 비즈니스 로직을 수행하는 서비스 레이어임을 알려주는 어노테이션
// Serivce :
// HTTP나 데이터베이스와 같은 외부 컴포넌트로부터 추상화되어 온전히 비즈니스 로직에만 집중할 수 있게 해줌
public class TodoService {

    @Autowired
    private TodoRepository repository;

    public String testService() {
        // TodoEntity 생성
        TodoEntity entity = TodoEntity.builder().title("my first todo item").build();

        // TodoEntity 저장
        repository.save(entity);

        //TodoEntity 검색
        TodoEntity savedEntity = repository.findById(entity.getId()).get();
        return savedEntity.getTitle();
    }

    public List<TodoEntity> create(final TodoEntity entity) {
        //validations
        validate(entity);

        repository.save(entity);

        log.info("Entity Id: {} is saved.", entity.getId());

        return repository.findByUserId(entity.getUserId());

    }

    private void validate(final TodoEntity entity) {
        if (entity == null) {
            log.warn("Entity cannot be null");
            throw new RuntimeException("Entity cannot be null.");
        }
        if (entity.getUserId() == null) {
            log.warn("Unknown user.");
            throw new RuntimeException("Unknown user.");
        }

    }

    // 검색 API 구현
    public List<TodoEntity> retrieve(final String userId) {
        return repository.findByUserId(userId);
    }

    // UPDATE API 구현
    public List<TodoEntity> update(final TodoEntity entity) {
//      1. 저장할 엔티티가 유효한지 확인
        validate(entity);
        // 2. 넘겨받은 엔티티 id를 이용해 TodoEntity를 가져온다. 존재하지 않는 엔티티는 업데이트를 할 수 없기 때문.
        final Optional<TodoEntity> original = repository.findById(entity.getId());

        original.ifPresent(todo -> {
            // 3. 반환된 TodoEntity가 존재하면 값을 새 entity 값으로 덮어 씌운다.
            todo.setTitle(entity.getTitle());
            todo.setDone(entity.isDone());

            // 4. 데이터베이스에 새 값을 저장한다.
            repository.save(todo);
        });

        // // Retrieve Todo에서 만든 메서드를 이용해 사용자의 모든 Todo 리스트를 리턴
        return retrieve(entity.getUserId());
    }

    // DELETE API 구현
    public List<TodoEntity> delete (final TodoEntity entity) {
        // 1. 저장할 엔티티 유효 여부
        validate(entity);

        try {
            // 2. 엔티티를 삭제
            repository.delete(entity);
        } catch (Exception e) {
            // 3. exception 발생하면 id와 exception을 로깅
            log.error("error deleting entity.", entity.getId(), e);

            // 4. 컨트롤러로 exception을 보냄. 데이터베이스 내부 로직을 캡슐화하려면 e를 리턴하지 않고 새 exception 오브젝트를 리턴한다.
            throw new RuntimeException("error deleting entity"+ entity.getId());
        }
        // 5. 새 Todo 리스트를 가져와 리턴한다.
        return retrieve(entity.getUserId());

    }
}
