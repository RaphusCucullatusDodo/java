package cn.raphuscucullatus.java.web.foundational.exception;

/**
 * 业务异常
 *
 * @author raphus cucullatus
 * @version 2021/8/2117:10
 * @since JDK8
 */
public class BusinessException extends RuntimeException {
    public BusinessException() {
    }

    public BusinessException(String message) {
        super(message);
    }

}
