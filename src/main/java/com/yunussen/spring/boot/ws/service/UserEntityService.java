package com.yunussen.spring.boot.ws.service;

import com.yunussen.spring.boot.ws.dao.UserDao;
import com.yunussen.spring.boot.ws.dto.UserDto;
import com.yunussen.spring.boot.ws.entity.User;
import com.yunussen.spring.boot.ws.exception.UserNotFoundException;
import com.yunussen.spring.boot.ws.model.UserDeleteRequestModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserEntityService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private ModelMapper modelMapper;

    public List<UserDto> getAllUser() {
        List<UserDto> returnValue = new ArrayList<>();
        userDao.findAll().forEach(user -> returnValue.add(modelMapper.map(user, UserDto.class)));
        return returnValue;
    }

    public UserDto getUserByUsername(@NotBlank String username) {
        User user = userDao.findByKullaniciAdi(username).orElseThrow(() -> new UserNotFoundException("kullanıcı bulunamadı"));
        return modelMapper.map(user, UserDto.class);
    }

    public UserDto getUserByPhone(@NotBlank String phoneNumber) {
        User user = userDao.findByTelefon(phoneNumber).orElseThrow(() -> new UserNotFoundException("kullanıcı bulunamadı"));
        return modelMapper.map(user, UserDto.class);
    }

    public UserDto save(@Valid UserDto user) {
        return modelMapper.map(userDao.save(modelMapper.map(user, User.class)), UserDto.class);
    }

    public boolean delete(@Valid UserDeleteRequestModel model) {
        User user = userDao.findByKullaniciAdi(model.getKullaniciAdi()).orElseThrow(() -> new UserNotFoundException("kullanıcı bulunamadı"));
        if (!user.getTelefon().equals(model.getTelefon())) {
            throw new UserNotFoundException("Username ve numara eşleşmedi.");
        }
        userDao.delete(user);
        return true;
    }

    public UserDto update(UserDto model) {
        User user = userDao.getById(model.getId());
        user.setAdi(model.getAdi());
        user.setEmail(model.getEmail());
        user.setSoyadi(model.getSoyadi());
        user.setKullaniciAdi(model.getKullaniciAdi());
        return modelMapper.map(userDao.save(user), UserDto.class);
    }
}
