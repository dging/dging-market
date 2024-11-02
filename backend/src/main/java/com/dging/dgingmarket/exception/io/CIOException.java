package com.dging.dgingmarket.exception.io;

import com.dging.dgingmarket.web.api.dto.common.ErrorCode;
import lombok.Getter;

@Getter
public class CIOException extends RuntimeException {

    private final ErrorCode errorCode;

    public CIOException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public static class CCloudCommunicationException extends CIOException {

        public CCloudCommunicationException() {
            super(ErrorCode.CLOUD_COMMUNICATION_ERROR);
        }
    }

    public static class CFileConvertFailedException extends CIOException {

        public CFileConvertFailedException() {
            super(ErrorCode.FILE_CONVERT_FAILED);
        }
    }

    public static class CInvalidFileFormatException extends CIOException {

        public CInvalidFileFormatException() {
            super(ErrorCode.INVALID_FILE_FORMAT);
        }
    }

}
