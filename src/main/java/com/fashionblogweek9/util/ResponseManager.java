package com.fashionblogweek9.util;

import com.fashionblogweek9.models.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ResponseManager<T> {
    public ApiResponse<T> success(T data) {
        return new ApiResponse<T>((T) "Request Successful", true, data);
    }
}
