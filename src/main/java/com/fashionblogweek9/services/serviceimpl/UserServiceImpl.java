package com.fashionblogweek9.services.serviceimpl;

import com.fashionblogweek9.dtos.userdtos.UserResponseDto;
import com.fashionblogweek9.dtos.userdtos.UserSignupDto;
import com.fashionblogweek9.enums.Role;
import com.fashionblogweek9.exceptions.AlreadyExistsException;
import com.fashionblogweek9.exceptions.NotFoundException;
import com.fashionblogweek9.models.ApiResponse;
import com.fashionblogweek9.models.User;
import com.fashionblogweek9.repositories.UserRepository;
import com.fashionblogweek9.services.UserService;
import com.fashionblogweek9.util.ResponseManager;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ResponseManager responseManager;
    private final HttpSession httpSession;

    @Override
    public boolean isEmailExist(String email) {
        boolean status;
        status = userRepository.existsByEmail(email);
        return status;
    }

    @Override
    public ApiResponse<UserResponseDto> signup(UserSignupDto userSignupDto) throws AlreadyExistsException {
        ApiResponse apiResponse;

        boolean emailExistStatus = isEmailExist(userSignupDto.getEmail());

        if(emailExistStatus == true){
            throw new AlreadyExistsException("Email already exists");
        }

        User user = new User();
        BeanUtils.copyProperties(userSignupDto, user);
        user.setRole(Role.REGISTERED_USER);
        userRepository.save(user);

        UserResponseDto userResponseDto = new UserResponseDto();
        BeanUtils.copyProperties(user, userResponseDto);
        apiResponse = responseManager.success(userResponseDto);

        return apiResponse;
    }

    @Override
    public ApiResponse<UserResponseDto> login(String email, String password) throws NotFoundException {
        boolean isBloggerExistStatus = userRepository.existsByEmailAndPassword(email, password);

        if(isBloggerExistStatus == false)
            throw new NotFoundException("Invalid credentials");

        User user = userRepository.findByEmailAndPassword(email,password);
        UserResponseDto userResponseDto = new UserResponseDto();
        BeanUtils.copyProperties(user, userResponseDto);
        httpSession.setAttribute("userId",user.getId());
        ApiResponse apiResponse = responseManager.success(userResponseDto);

        return apiResponse;
    }
}
