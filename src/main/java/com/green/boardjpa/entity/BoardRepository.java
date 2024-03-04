package com.green.boardjpa.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board,Long>{
    // JpaRepository를 상속받아서 기본적인 CRUD를 사용할 수 있음
    // JpaRepository<Entity,PK타입>
    // repository를 생성하면 자동으로 구현체가 생성됨

}
