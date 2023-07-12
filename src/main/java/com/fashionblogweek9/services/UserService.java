package com.fashionblogweek9.services;

import com.fashionblogweek9.dtos.userdtos.UserResponseDto;
import com.fashionblogweek9.dtos.userdtos.UserSignupDto;
import com.fashionblogweek9.exceptions.AlreadyExistsException;
import com.fashionblogweek9.exceptions.NotFoundException;
import com.fashionblogweek9.models.ApiResponse;

public interface UserService {
    boolean isEmailExist(String email);
    ApiResponse<UserResponseDto> signup(UserSignupDto userSignupDto) throws AlreadyExistsException;
    ApiResponse<UserResponseDto> login(String email, String password) throws NotFoundException;
}
