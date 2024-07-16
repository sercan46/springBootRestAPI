package com.sercan.ozbek.springbootrestapi.service.impl;

import com.sercan.ozbek.springbootrestapi.advice.ApiExceptionHandler;
import com.sercan.ozbek.springbootrestapi.advice.UserNotFound;
import com.sercan.ozbek.springbootrestapi.dto.UserDto;
import com.sercan.ozbek.springbootrestapi.entity.User;
import com.sercan.ozbek.springbootrestapi.repository.UserRepository;
import com.sercan.ozbek.springbootrestapi.service.UserService;
import com.sercan.ozbek.springbootrestapi.util.CustomPage;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user= modelMapper.map(userDto,User.class);
        user.setCreatedDate(new Date());
        user.setCreatedBy("Admin");
        return modelMapper.map(userRepository.save(user),UserDto.class);
    }

    @Override
    public List<UserDto> getUsers() {
        List<User> users= userRepository.findAll();
        List<UserDto> dtos=users.stream().map(user->modelMapper.map(user, UserDto.class)).toList();
        return dtos;
    }

    @Override
    public UserDto getUser(Long id) {
        Optional<User> user= userRepository.findById(id);
        if(user.isPresent()){
            return modelMapper.map(user.get(), UserDto.class);
        }
        throw new RuntimeException("Kullanıcı Bulunamadı");
    }

    @Override
    public UserDto updateUser(Long id, UserDto user) {
        Optional<User> resultUser = userRepository.findById(id);
        if(resultUser.isPresent()){
            resultUser.get().setFirstName(user.getFirstName());
            resultUser.get().setLastName(user.getLastName());
            resultUser.get().setFirstName(user.getFirstName());
            resultUser.get().setUpdatedAt(new Date());
            resultUser.get().setUpdatedBy("Admin");
            return modelMapper.map(userRepository.save(resultUser.get()), UserDto.class);
        }
        return null;
    }

    @Override
    public Page<User> pagination(int currentPage, int pageSize) {
        Pageable pageable = PageRequest.of(currentPage,pageSize);
        return userRepository.findAll(pageable);
    }

    @Override
    public Page<User> pagination(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Slice<User> slice(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Boolean deleteUser(Long userId) {
        Optional<User> resultUser = userRepository.findById(userId);
        if(resultUser.isPresent()){
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }

    @Override
    public CustomPage<UserDto> customPagination(Pageable pageable) {
       Page<User> data= userRepository.findAll(pageable);
       UserDto[] dtos= modelMapper.map(data.getContent(),UserDto[].class);
       return new CustomPage<UserDto>(data, Arrays.asList(dtos));
    }
}
