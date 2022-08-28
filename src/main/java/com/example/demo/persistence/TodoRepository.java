package com.example.demo.persistence;

import com.example.demo.Model.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//TodoEntity는 테이블에 매핑될 엔티티 클래스, String은 엔티티의 기본키의 타입
@Repository
// persistence layer
// 1. 데이터베이스와 통신하며 필요한 쿼리를 보내고 해석해 엔티티 오브젝트로 변환해 주는 역할
public interface  TodoRepository extends JpaRepository<TodoEntity,String> {

    List<TodoEntity> findByUserId(String userId);
}
