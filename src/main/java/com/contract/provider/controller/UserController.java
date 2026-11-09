package com.contract.provider.controller;

import com.contract.provider.model.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/{id}")
    public User getUser(
            @PathVariable Integer id) {

        return new User(
                1,
                "Krishna",
                "krishna@test.com"
        );
    }
}