package com.example.shoestore.service;

import com.example.shoestore.dto.request.UserCreateRequest;
import com.example.shoestore.dto.request.UserUpdateRequest;
import com.example.shoestore.entity.User;
// import com.example.shoestore.mapper.UserMapper;
import com.example.shoestore.exception.AppException;
import com.example.shoestore.exception.ErrrorCode;
import com.example.shoestore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    //@Autowired
    //private UserMapper userMapper;
    public User createUser(UserCreateRequest request){
        //User user = userMapper.toUser(request);
     //thay vì sử dụng userMapper, ta có thể trực tiếp tạo đối tượng User từ request
        User user = new User();
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("ErrrorCode.USER_EXISTED");
        }
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDob(request.getDob());
        PasswordEncoder  passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(user.getPassword()));// set password đã mã hóa
        return userRepository.save(user);

    }
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(String id){
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User updateUser(String userId, UserUpdateRequest request){
        User user = getUser(userId);
        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDob(request.getDob());
        return userRepository.save(user);

    }
}
