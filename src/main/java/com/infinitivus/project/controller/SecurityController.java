package com.infinitivus.project.controller;

import com.infinitivus.project.entity.security.UserData;
import com.infinitivus.project.service.security_service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SecurityController {

    @Autowired
    private IUserService userService;

    //Creating a new user with the User role
// POST http://localhost:8080/registration
//     {"username":"USER","password":"USER"}
    @PostMapping("/registration")
    public ResponseEntity<String> createUser(@RequestBody UserData user) {
        ResponseEntity<String> resp;
        try {
            Integer id = userService.registrationUser(user);
            resp = new ResponseEntity<>(
                    "User '" + id + "' created", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseEntity<>(
                    "Unable to save user",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return resp;
    }

    // Output of all users with roles to the console
// GET http://localhost:8080/users/getAll
    @GetMapping("/users/getAll")
    public ResponseEntity<?> getAllUser() {
        ResponseEntity<?> resp ;
        try {
            List<UserData> list = userService.listAllUser();
            resp = new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseEntity<>(
                    "Unable to get all user",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return resp;
    }

    // getting one user by id
// GET http://localhost:8080/users/getUser/1
    @GetMapping("/users/getUser/{id}")
    public ResponseEntity<?> getOneUser(@PathVariable Integer id) {
        ResponseEntity<?> resp ;
        try {
            UserData user = userService.getUser(id);
            resp = new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseEntity<>(
                    "Unable to find user",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return resp;
    }

    //   Deleting a user
//DELETE http://localhost:8080/users/remove/1
    @DeleteMapping("/users/remove/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
        ResponseEntity<String> resp ;
        try {
            userService.deleteUser(id);
            resp = new ResponseEntity<>(
                    "Users '" + id + "' deleted", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseEntity<>(
                    "Unable to delete user", HttpStatus.INTERNAL_SERVER_ERROR);//500
        }
        return resp;
    }

    //  assigning a role to a user by ID
//PATCH http://localhost:8080/users/modify/3/ROLE_ADMIN
    @PatchMapping("/users/modify/{id}/{role}")
    public ResponseEntity<String> modifyUser(@PathVariable Integer id, @PathVariable String role) {
        ResponseEntity<String> resp ;
        try {
            userService.updateRoleToUser(id, role);
            resp = new ResponseEntity<>(
                    "User '" + id + "' updated role",
                    HttpStatus.PARTIAL_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseEntity<>(
                    "Unable to update user",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return resp;
    }
}


