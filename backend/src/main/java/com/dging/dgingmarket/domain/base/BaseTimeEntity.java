package com.dging.dgingmarket.domain.base;

import lombok.Getter;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public abstract class BaseTimeEntity extends BaseCreationTimeEntity {

    @LastModifiedDate
    @Column(columnDefinition="timestamp(6) default now(6)")
    private Date lastModifiedAt;

}
