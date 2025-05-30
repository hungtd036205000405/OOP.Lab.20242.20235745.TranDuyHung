package hust.soict.hespi.aims.exception;

public class PlayerException extends MediaException {

    // Constructor không tham số
    public PlayerException() {
        super();
    }

    // Constructor với thông báo lỗi
    public PlayerException(String message) {
        super(message);
    }

    // Constructor với nguyên nhân gây ra lỗi
    public PlayerException(Throwable cause) {
        super(cause);
    }

    // Constructor với thông báo lỗi và nguyên nhân
    public PlayerException(String message, Throwable cause) {
        super(message, cause);
    }

    // Constructor đầy đủ với các tùy chọn kiểm soát
    public PlayerException(String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
