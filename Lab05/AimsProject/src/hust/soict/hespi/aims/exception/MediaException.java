package hust.soict.hespi.aims.exception;

public class MediaException extends AimsException {

    // Constructor không tham số
    public MediaException() {
        super();
    }

    // Constructor với thông báo lỗi
    public MediaException(String message) {
        super(message);
    }

    // Constructor với nguyên nhân gây ra lỗi
    public MediaException(Throwable cause) {
        super(cause);
    }

    // Constructor với thông báo lỗi và nguyên nhân
    public MediaException(String message, Throwable cause) {
        super(message, cause);
    }

    // Constructor đầy đủ với các tùy chọn kiểm soát
    public MediaException(String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
