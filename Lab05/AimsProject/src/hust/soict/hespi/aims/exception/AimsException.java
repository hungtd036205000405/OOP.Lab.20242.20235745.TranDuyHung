package hust.soict.hespi.aims.exception;

public class AimsException extends Exception {

    // Constructor không tham số
    public AimsException() {
        super();
    }

    // Constructor với thông báo lỗi
    public AimsException(String message) {
        super(message);
    }

    // Constructor với nguyên nhân gây ra lỗi
    public AimsException(Throwable cause) {
        super(cause);
    }

    // Constructor với thông báo lỗi và nguyên nhân
    public AimsException(String message, Throwable cause) {
        super(message, cause);
    }

    // Constructor đầy đủ với các tùy chọn kiểm soát
    public AimsException(String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
