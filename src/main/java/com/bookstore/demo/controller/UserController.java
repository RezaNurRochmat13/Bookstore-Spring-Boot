package com.bookstore.demo.controller;

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
}
