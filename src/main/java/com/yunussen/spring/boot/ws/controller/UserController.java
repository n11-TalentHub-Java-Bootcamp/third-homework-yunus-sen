package com.yunussen.spring.boot.ws.controller;


import com.yunussen.spring.boot.ws.entity.User;
import com.yunussen.spring.boot.ws.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/")
public class UserController {
    /**
     * http://localhost:8080/api/users/
     */

    @Autowired
    private UserService userService;

    /**
     * /
     * @return
     */
    @GetMapping("")
    public List<User> findAll() {
        return userService.findAll();
    }

    /**
     * /id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public User findById(@PathVariable String id) {
        return userService.findById(id);
    }

    /**
     * /
     * @param user
     * @return
     */
    @PostMapping("")
    public ResponseEntity<Object> save(@RequestBody User user) {

        user = userService.save(user);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    /**
     * id
     * @param id
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        userService.deleteById(id);
    }

}