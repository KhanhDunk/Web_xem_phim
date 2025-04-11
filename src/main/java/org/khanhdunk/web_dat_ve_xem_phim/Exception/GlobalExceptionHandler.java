package org.khanhdunk.web_dat_ve_xem_phim.Exception;

import org.khanhdunk.web_dat_ve_xem_phim.DTO.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
// Annotation xử lý ngọại lệ cho toàn cục cho toàn bộ controller trong ứng dụng
// Khi một controller nào đó ném ra exception, Spring sẽ tìm các phương thức được đánh
// dấu bằng @ExceptionHandler trong các @ControllerAdvice để xử lý.
public class GlobalExceptionHandler {

    @ExceptionHandler(value = RuntimeException.class) // bắt tất cả các exception thuộc loại RuntimException
    ResponseEntity<ResponseDTO> handlingRuntimeException(RuntimeException exception)
    {
        ResponseDTO apiResponse = new ResponseDTO();
        apiResponse.setStatus(HttpStatus.UNAUTHORIZED);
        apiResponse.setMessage(exception.getMessage());

        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class) // bắt tất cả các exception khi validate trong controller bị lỗi
    ResponseEntity<String> handlingValidation(MethodArgumentNotValidException exception)
    {
                return ResponseEntity.badRequest().body(exception.getFieldError().getDefaultMessage());
    }

}
