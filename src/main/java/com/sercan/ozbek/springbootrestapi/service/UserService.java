package com.sercan.ozbek.springbootrestapi.service;

import com.sercan.ozbek.springbootrestapi.dto.UserDto;
import com.sercan.ozbek.springbootrestapi.entity.User;
import com.sercan.ozbek.springbootrestapi.util.CustomPage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto user);
    List<UserDto> getUsers();
    UserDto getUser(Long id);
    UserDto updateUser(Long id, UserDto user);
    Page<User> pagination(int currentPage, int pageSize);
    Page<User> pagination(Pageable pageable);
    Slice<User> slice(Pageable pageable);
    Boolean deleteUser(Long userId);
    CustomPage<UserDto> customPagination(Pageable pageable);
}
