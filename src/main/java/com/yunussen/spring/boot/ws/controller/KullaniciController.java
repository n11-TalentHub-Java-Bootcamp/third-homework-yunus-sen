package com.yunussen.spring.boot.ws.controller;

import com.yunussen.spring.boot.ws.dto.UserDto;
import com.yunussen.spring.boot.ws.model.UserDeleteRequestModel;
import com.yunussen.spring.boot.ws.service.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/kullanici")
public class KullaniciController {
    @Autowired
    private UserEntityService userService;

    @GetMapping("/list")
    public ResponseEntity<List<UserDto>> getAllUser(){
        return ResponseEntity.ok(userService.getAllUser());
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserDto> getUserbyUsername(@PathVariable(value = "username") String username){
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }

    @GetMapping("/phone/{phone}")
    public ResponseEntity<UserDto> getUserByPhone(@PathVariable(value = "phone") String phone){
        return ResponseEntity.ok(userService.getUserByPhone(phone));
    }

    @PostMapping("/")
    public ResponseEntity<UserDto> save(@RequestBody @Valid UserDto user){
        return ResponseEntity.ok(userService.save(user));
    }

    @DeleteMapping("/")
    public ResponseEntity<Boolean> delete(@RequestBody UserDeleteRequestModel model){
        return ResponseEntity.ok(userService.delete(model));
    }

    @PutMapping("/")
    public ResponseEntity<UserDto> update(UserDto user){
        return ResponseEntity.ok(userService.update(user));
    }
}
