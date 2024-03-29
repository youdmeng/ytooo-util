package ml.ytooo.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class ServiceException extends RuntimeException {

    private Integer errCode;
    private transient Object errData;

    public ServiceException(Integer errCode, String message, Object errData, Throwable throwable) {
        super(message, throwable);
        this.errCode = errCode;
        this.errData = errData;
    }
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Integer errCode, Object errData, String message) {
        super(message);
        this.errCode = errCode;
        this.errData = errData;
    }

    public Integer getErrCode() {
        return errCode;
    }

    public Object getErrData() {
        return errData;
    }

}
