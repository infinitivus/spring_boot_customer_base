package com.infinitivus.project.spring_rest_security_base.controller;

import com.infinitivus.project.spring_rest_security_base.entity.security.UserData;
import com.infinitivus.project.spring_rest_security_base.service.security_service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SecurityController {

    @Autowired
    private IUserService userService;

    //Создание нового пользователя с ролью User ok
    @PostMapping("/registration")
    public ResponseEntity<String> createUser(@RequestBody UserData user) {
        ResponseEntity<String> resp = null;
        try {
            Integer id = userService.registrationUser(user);
            resp = new ResponseEntity<String>(
                    "User '" + id + "' created", HttpStatus.CREATED); //201
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseEntity<String>(
                    "Unable to save user",
                    HttpStatus.INTERNAL_SERVER_ERROR); //500
        }
        return resp;
    }

    // Вывод всех пользователей с ролями на консоль ok
    @GetMapping("/user/getAllUser")
    public ResponseEntity<?> getAllUser() {
        ResponseEntity<?> resp = null;
        try {
            List<UserData> list = userService.listAllUser();
            resp = new ResponseEntity<List<UserData>>(list, HttpStatus.OK);//200
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseEntity<String>(
                    "Unable to get all user",
                    HttpStatus.INTERNAL_SERVER_ERROR);//500
        }
        return resp;
    }

    // получение одного пользователя по id ok
    @GetMapping("/user/getUser/{id}")
    public ResponseEntity<?> getOneInvoice(@PathVariable Integer id) {
        ResponseEntity<?> resp = null;
        try {
            UserData user = userService.getUser(id);
            resp = new ResponseEntity<UserData>(user, HttpStatus.OK);//200
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseEntity<String>(
                    "Unable to find user",
                    HttpStatus.INTERNAL_SERVER_ERROR);//500
        }
        return resp;
    }

    // Удаление пользователя ok
    @DeleteMapping("/user/remove/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
        ResponseEntity<String> resp = null;
        try {
            userService.deleteUser(id);
            resp = new ResponseEntity<String>(
                    "Users '" + id + "' deleted", HttpStatus.OK);//200
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseEntity<String>(
                    "Unable to delete user", HttpStatus.INTERNAL_SERVER_ERROR);//500
        }
        return resp;
    }

    //  присвоение роли пользователю по ID ok
    @PatchMapping("/user/modify/{id}/{role}")
    public ResponseEntity<String> modifyUser(@PathVariable Integer id, @PathVariable String role) {
        ResponseEntity<String> resp = null;
        try {
            userService.updateRoleToUser(id, role);
            resp = new ResponseEntity<String>(
                    "User '" + id + "' updated role",
                    HttpStatus.PARTIAL_CONTENT); //206
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseEntity<String>(
                    "Unable to update user",
                    HttpStatus.INTERNAL_SERVER_ERROR); //500
        }
        return resp;
    }
}

//    // Редактирование данных пользователя
//    @PutMapping("/modify/{id}")
//    public ResponseEntity<String> modifyUser(@RequestBody UserData user,@PathVariable Integer id) {
//        ResponseEntity<String> resp = null;
//        try {
//            userService.updateUser(user,id);
//            resp = new ResponseEntity<String>(
//                    "User '"+ id+"' updated",
//                    HttpStatus.RESET_CONTENT); //205
//        } catch (Exception e) {
//            e.printStackTrace();
//            resp = new ResponseEntity<String>(
//                    "Unable to update user",
//                    HttpStatus.INTERNAL_SERVER_ERROR); //500
//        }
//        return resp;
//    }

/**
 * To update one Invoice just like where clause condition, updates Invoice object & returns Status as ResponseEntity<String>
 */
//    @PatchMapping("/modify/{id}/{number}")
//    public ResponseEntity<String> updateInvoiceNumberById(
//            @PathVariable Long id,
//            @PathVariable String number
//    ) {
//        ResponseEntity<String> resp = null;
//        try {
//            service.updateInvoiceNumberById(number, id);
//            resp = new ResponseEntity<String>(
//                    "Invoice '" + number + "' Updated",
//                    HttpStatus.PARTIAL_CONTENT); //206- Reset-Content(PUT)
//
//        } catch (InvoiceNotFoundException pne) {
//            throw pne; // re-throw exception to handler
//        } catch (Exception e) {
//            e.printStackTrace();
//            resp = new ResponseEntity<String>(
//                    "Unable to Update Invoice",
//                    HttpStatus.INTERNAL_SERVER_ERROR); //500-ISE
//        }
//        return resp;
//    }

