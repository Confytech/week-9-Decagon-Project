package com.fashionblogweek9.controllers;

import com.fashionblogweek9.dtos.userdtos.UserResponseDto;
import com.fashionblogweek9.dtos.userdtos.UserSignupDto;
import com.fashionblogweek9.exceptions.AlreadyExistsException;
import com.fashionblogweek9.exceptions.NotFoundException;
import com.fashionblogweek9.models.ApiResponse;
import com.fashionblogweek9.models.User;
import com.fashionblogweek9.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/fashion-blog")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<UserResponseDto>> signup(@RequestBody UserSignupDto userSignupDto) throws AlreadyExistsException {
        ApiResponse<UserResponseDto> user = userService.signup(userSignupDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<UserResponseDto>> login(@RequestBody User user) throws NotFoundException {
        ApiResponse<UserResponseDto> userResponse = userService.login(user.getEmail(), user.getPassword());
        return new ResponseEntity<>(userResponse, HttpStatus.ACCEPTED);
    }
}
