package com.dging.dgingmarket.domain.base;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public abstract class BaseCreationTimeEntity {

    @CreatedDate
    @Column(updatable = false, columnDefinition="timestamp(6) default now(6)")
    private Date createdAt;

}
