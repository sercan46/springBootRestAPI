package com.sercan.ozbek.springbootrestapi.api;

import com.sercan.ozbek.springbootrestapi.dto.UserDto;
import com.sercan.ozbek.springbootrestapi.entity.User;
import com.sercan.ozbek.springbootrestapi.service.UserService;
import com.sercan.ozbek.springbootrestapi.util.CustomPage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@Validated @RequestBody UserDto user){
        UserDto resultUser=userService.createUser(user);
        return ResponseEntity.ok(resultUser);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<UserDto>> getUsers(){
       List<UserDto> users=userService.getUsers();
       return ResponseEntity.ok(users);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id){
        UserDto user= userService.getUser(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/edit/{id}")
    public  ResponseEntity<UserDto> updateUser(@PathVariable Long id, @Validated @RequestBody UserDto user){
        UserDto resultUser= userService.updateUser(id,user);
       return ResponseEntity.ok(resultUser);
    }

    @GetMapping("/pagination")
    public ResponseEntity<Page<User>> pagination(@Validated @RequestParam int currentPage, @Validated @RequestParam int pageSize){
        return ResponseEntity.ok(userService.pagination(currentPage,pageSize));
    }

    @GetMapping("/pagination/v1")
    public ResponseEntity<Page<User>> pagination(Pageable pageable){
        return ResponseEntity.ok(userService.pagination(pageable));
    }

    @GetMapping("/pagination/v2")
    public ResponseEntity<Slice<User>> slice(Pageable pageable){
        return ResponseEntity.ok(userService.slice(pageable));
    }

    @GetMapping("/pagination/v3")
    public ResponseEntity<CustomPage<UserDto>> customPagination(Pageable pageable){
        return ResponseEntity.ok(userService.customPagination(pageable));
    }

    @DeleteMapping("/remove/{userId}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable Long userId){
        Boolean status= userService.deleteUser(userId);
        return  ResponseEntity.ok(status);
    }
}
