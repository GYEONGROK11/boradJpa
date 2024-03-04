package com.green.boardjpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@MappedSuperclass // 상속받는 엔티티에게 매핑정보만 제공
@EntityListeners(AuditingEntityListener.class)  // 엔티티에 이벤트 발생시점을 알려주는 역할
public class CreatedAtEntity {
    @JsonIgnore
    @CreatedDate // 엔티티가 생성되어 저장될 때 시간이 자동 저장
    @Column(updatable = false,nullable = false) // 엔티티가 수정될 때 시간이 자동 저장
    private LocalDateTime createdAt;

}
