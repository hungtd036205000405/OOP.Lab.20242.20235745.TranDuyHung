package com.example.shoestore.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    /**
     * HTTP status code hoặc business code.
     * Ví dụ: 200 = thành công, 401 = chưa đăng nhập, 403 = không có quyền, 500 = lỗi server
     */
    @Builder.Default
    int code = 200;

    /**
     * Thông điệp mô tả (Success, Error, ...)
     */
    String message;

    /**
     * Kết quả trả về (có thể là object, list, v.v...)
     */
    T result;

    /**
     * Static helper để tạo response thành công
     */
    public static <T> ApiResponse<T> success(T data) {
        return ApiResponse.<T>builder()
                .code(200)
                .message("Success")
                .result(data)
                .build();
    }

    /**
     * Static helper để tạo response thất bại
     */
    public static <T> ApiResponse<T> error(int code, String message) {
        return ApiResponse.<T>builder()
                .code(code)
                .message(message)
                .build();
    }
}
