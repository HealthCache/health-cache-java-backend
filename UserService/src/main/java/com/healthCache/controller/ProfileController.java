package com.healthCache.controller;


import com.healthCache.exceptions.ResourceNotFoundException;
import com.healthCache.model.User;
import com.healthCache.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/profile")
public class ProfileController {
    private final UserService userService;

    @Autowired
    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User u){
        String username = u.getUsername();
        String password = u.getPassword();
        System.out.println("U is "+ u);
        User user = this.userService.findByUsername(username);
        if (user.getPassword().equals(password)){
            return ResponseEntity.ok().body(user);
        }else {
            return new ResponseEntity<User>(u, HttpStatus.FORBIDDEN);
        }
    }


    @PostMapping("/save")
    public User saveUser(@RequestBody User user){
        return this.userService.save(user);
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok(
                this.userService.findAll()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok().body(this.userService.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User not Found")
        ));
    }


    @PutMapping("/update/{id}")
    public User updateUser(@RequestBody User newUser, @PathVariable(value = "id") Long id){
        return this.userService.findById(id)
                .map(user -> {
                    user.setUsername(newUser.getUsername());
                    user.setPassword(newUser.getPassword());
                    user.setFirstName(newUser.getFirstName());
                    user.setLastName(newUser.getLastName());
                    user.setEmail(newUser.getEmail());
                    user.setDob(newUser.getDob());
                    user.setAddressLineOne(newUser.getAddressLineOne());
                    user.setAddressLineTwo(newUser.getAddressLineTwo());
                    user.setCity(newUser.getCity());
                    user.setZipcode(newUser.getZipcode());
                    user.setPhoneNo(newUser.getPhoneNo());
                    user.setProfilePic(newUser.getProfilePic());
                    user.setRole(newUser.getRole());
                    user.setGender(newUser.getGender());
                    user.setRelationshipStatus(newUser.getRelationshipStatus());

                    return this.userService.update(user);
                }).orElseThrow(() -> new ResourceNotFoundException("User not Found"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeUser(@PathVariable(value = "id") Long id){
        User user = this.userService.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("User not Found")
                );
        this.userService.delete(user);
        return ResponseEntity.ok().build();
    }

}
