package kg.it.secure.jdbcauth.controller;

import kg.it.secure.jdbcauth.model.Bulk;
import kg.it.secure.jdbcauth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class BasicController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/admin")
    @ResponseBody
    public Bulk getAdmin(){
        return new Bulk(1, "Hello admin");
    }

    @GetMapping("/user")
    @ResponseBody
    public Bulk getUser(){
        return new Bulk(2, "Hello user");
    }

    /*@PostMapping("/block/{id}")
    @ResponseBody
    public Bulk blockUser(@PathParam("name")int id){
        userRepository.getOne(id);
    }*/
}
