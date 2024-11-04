package com.dging.dgingmarket.exception.business;

import com.dging.dgingmarket.web.api.dto.common.ErrorCode;

public class CEntityNotFoundException extends CBusinessException {
    public CEntityNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }

    public static class CUserNotFoundException extends CEntityNotFoundException {
        public CUserNotFoundException() {
            super(ErrorCode.USER_NOT_FOUND);
        }
    }

    public static class CProductNotFoundException extends CEntityNotFoundException {
        public CProductNotFoundException() {
            super(ErrorCode.PRODUCT_NOT_FOUND);
        }
    }

    public static class CImageNotFoundException extends CEntityNotFoundException {
        public CImageNotFoundException() {
            super(ErrorCode.IMAGE_NOT_FOUND);
        }
    }
}
