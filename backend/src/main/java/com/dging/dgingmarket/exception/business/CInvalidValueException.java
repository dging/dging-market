package com.dging.dgingmarket.exception.business;

import com.dging.dgingmarket.web.api.dto.common.ErrorCode;

public class CInvalidValueException extends CBusinessException {
    public CInvalidValueException(ErrorCode errorCode) {
        super(errorCode);
    }

    public static class CAlreadySignedupException extends CInvalidValueException {
        public CAlreadySignedupException() {
            super(ErrorCode.ALREADY_SIGNEDUP);
        }
    }

    public static class CLoginFailedException extends CInvalidValueException {
        public CLoginFailedException() {
            super(ErrorCode.LOGIN_FAIL);
        }
    }

    public static class CUserOwnProductException extends CInvalidValueException {
        public CUserOwnProductException() {
            super(ErrorCode.USER_OWN_PRODUCT);
        }
    }

    public static class CFollowMyselfException extends CInvalidValueException {
        public CFollowMyselfException() {
            super(ErrorCode.FOLLOW_MYSELF_ERROR);
        }
    }
}
