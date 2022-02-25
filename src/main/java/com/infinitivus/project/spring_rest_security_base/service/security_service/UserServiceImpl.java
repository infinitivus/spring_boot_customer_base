package com.infinitivus.project.spring_rest_security_base.service.security_service;

import com.infinitivus.project.spring_rest_security_base.entity.security.UserData;
import com.infinitivus.project.spring_rest_security_base.entity.security.UserRole;
import com.infinitivus.project.spring_rest_security_base.repository.security_repository.IRoleRepository;
import com.infinitivus.project.spring_rest_security_base.repository.security_repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRoleRepository roleRepository;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

//    @Override
//    public boolean verificationSchema() {
//        List<UserData> allUser = listAllUser();
//        if (allUser.isEmpty()) {
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    @Override
//    public void createRoles() {
//        UserRole admin = new UserRole("ROLE_ADMIN");
//        UserRole master = new UserRole("ROLE_MASTER");
//        UserRole manager = new UserRole("ROLE_MANAGER");
//        roleRepository.save(admin);
//        roleRepository.save(master);
//        roleRepository.save(manager);
//    }

//    @Override
//    public void saveAdmin(UserData user) {
//        UserRole roleAdmin = roleRepository.findByRole("ROLE_ADMIN");
//        encoder(user);
//        user.addRole(roleAdmin);
//        userRepository.save(user);
//    }

//    @Override
//    public UserData findByUsername(String username) {
//        return userRepository.findByUsername(username);
//    }


    @Override
    public List<UserData> listAllUser() {
        return userRepository.findAll();
    }

    @Override
    public UserData getUser(Integer id) {
        return userRepository.findById(id).get();
    }

    // Save new user
    @Override
    public Integer registrationUser(UserData user) {
        UserRole roleUser = roleRepository.findByRole("ROLE_USER");
        encoder(user);
        user.addRole(roleUser);
        userRepository.save(user);
        return user.getId();
    }

    @Override
    public void updateRoleToUser(Integer id, String userRole) {
        UserRole role = roleRepository.findByRole(userRole);
        UserData user = getUser(id);
        user.clearRole();
        user.addRole(role);
        userRepository.save(user);
    }

    public void encoder(UserData user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }

    @Override
    public void deleteUser(Integer id) {
        UserData user = getUser(id);
        user.clearRole();
        userRepository.save(user);
        userRepository.delete(user);
    }
}

