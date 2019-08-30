package com.bookstore.demo.controller;

import com.bookstore.demo.exception.ResourceNotFoundException;
import com.bookstore.demo.model.User;
import com.bookstore.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/public/api/v1/")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("user")
    public Map getAllUser() {
        Map newMap = new HashMap();
        List<User> allUser = userRepository.findAll();

        newMap.put("total", allUser.size());
        newMap.put("data", allUser);
        newMap.put("count", allUser.size());

        return newMap;
    }

    @GetMapping("user/{idUser}")
    public Map getDetailUser(@PathVariable(value = "idUser") Integer idUser) {
        Map newMap = new HashMap();

        Optional<User> detailUser = userRepository.findById(idUser);

        if (detailUser.isPresent()) {
            newMap.put("data", detailUser);
        } else {
            newMap.put("message", "Data kosong");
        }
        return newMap;
    }

    @PostMapping("user")
    public Map createNewUser(@Valid @RequestBody User userPayload) {
        Map saveUserMap = new HashMap();
        userRepository.save(userPayload);

        saveUserMap.put("message", "Berhasil simpan user");
        return saveUserMap;
    }

    @PutMapping("user/{idUser}")
    public Map updateUser(@PathVariable(value = "idUser") Integer idUser,
                          @Valid @RequestBody User userUpdatePayload) {
        Map mapUpdateUser = new HashMap();

        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id user", idUser));

        user.setUserName(userUpdatePayload.getUserName());
        user.setUserAddress(userUpdatePayload.getUserAddress());
        user.setUserPhone(userUpdatePayload.getUserPhone());
        user.setUserAge(userUpdatePayload.getUserAge());

        User updateUser = userRepository.save(user);

        mapUpdateUser.put("message", "Update user berhasil");
        mapUpdateUser.put("updated_record", updateUser);

        return mapUpdateUser;
    }

    @DeleteMapping("user/{idUser}")
    public Map deleteUser(@PathVariable("idUser") Integer idUser) {
        Map deleteUserMap = new HashMap();

        User findUser = userRepository.findById(idUser)
        .orElseThrow(() -> new ResourceNotFoundException("User", "id user", idUser));

        userRepository.delete(findUser);

        deleteUserMap.put("message", "User berhasil di delete");

        return deleteUserMap;
    }
}
