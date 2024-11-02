package com.dging.dgingmarket.domain.base;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public abstract class BaseTimeEntity extends BaseCreationTimeEntity {

    @LastModifiedDate
    @Column(columnDefinition="timestamp(6) default now(6)")
    private Date lastModifiedAt;

}
