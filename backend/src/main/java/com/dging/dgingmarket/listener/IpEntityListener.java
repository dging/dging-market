package com.dging.dgingmarket.listener;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class IpEntityListener {

    @PrePersist
    public void setCreationIp(Object entity) {
        setIpAddress(entity, CreationIp.class);
        setIpAddress(entity, UpdateIp.class);
    }

    @PreUpdate
    public void setUpdateIp(Object entity) {
        setIpAddress(entity, UpdateIp.class);
    }

    private void setIpAddress(Object entity, Class<? extends Annotation> annotationClass) {
        // 현재 요청의 HttpServletRequest 가져오기
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if (attributes == null) return;

        HttpServletRequest request = attributes.getRequest();
        String ipAddress = getClientIp(request);

        // 엔티티 클래스의 모든 필드 조회
        for (Field field : entity.getClass().getDeclaredFields()) {
            // 지정한 애노테이션이 붙은 필드 찾아 설정
            if (field.isAnnotationPresent(annotationClass)) {
                field.setAccessible(true);
                try {
                    // IP 주소를 설정 (최초 저장 시 creationIp)
                    if (annotationClass.equals(CreationIp.class)) {
                        if (field.get(entity) == null) {
                            field.set(entity, ipAddress);
                        }
                    } else {
                        // 업데이트 시 updateIp를 항상 설정
                        field.set(entity, ipAddress);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace(); //TODO: 예외처리 해야 함
                }
            }
        }
    }

    private String getClientIp(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("X-Real-IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress.split(",")[0]; // 첫 번째 IP 반환
    }
}