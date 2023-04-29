package com.Softito.cinemaTicketSystem.Controller;

import com.Softito.cinemaTicketSystem.Model.Ticket;
import com.Softito.cinemaTicketSystem.Model.User;
import com.Softito.cinemaTicketSystem.Services.TicketService;
import com.Softito.cinemaTicketSystem.Services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService service ;

    @GetMapping("")
    public List<User> getAllUser() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping("/add")
    public User createUser(@RequestBody User entity) {
        return service.create(entity);
    }
    @DeleteMapping("/delete/{id}")
    public Boolean deleteUser(@PathVariable Long id) {
        return service.delete(id);
    }

    @PutMapping("/update/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User entity) {
        return service.update(id, entity);
    }
}
