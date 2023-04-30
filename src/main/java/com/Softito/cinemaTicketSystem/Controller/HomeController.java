package com.Softito.cinemaTicketSystem.Controller;


import com.Softito.cinemaTicketSystem.Model.Film;
import com.Softito.cinemaTicketSystem.Model.Role;
import com.Softito.cinemaTicketSystem.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private final RestTemplate restTemplate;

    public HomeController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/filmsPage")
    public String getAllFilms(Model model) {
        List<Film> films = restTemplate.getForObject("http://localhost:8080/films", List.class);
        model.addAttribute("films", films);
        return "filmsPage";
    }
    @GetMapping("/register")
    public String register(){
        return "register";
    }
    @GetMapping("/success")
    public String success(){
        return "success";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @PostMapping("/saveUser")
    public String saveUser(@RequestParam("name") String name,
                           @RequestParam("surname") String surname,
                           @RequestParam("email") String email,
                           @RequestParam("password") String password,
                           Model model) throws IOException {
        User newUser = new User();
        newUser.setName(name);
        newUser.setSurname(surname);
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.setBalance(100L);
        newUser.setIsActive(true);
        // Get the current date
        LocalDate currentDate = LocalDate.now();

        // Format the date in "yyyy-MM-dd" format
        String formattedDate = currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        // Convert the formatted date to a java.util.Date object
        Date date = java.sql.Date.valueOf(formattedDate);

        newUser.setCreated_at(date);
        newUser.setRole(new Role(2L));


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<User> entity = new HttpEntity<>(newUser, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:8080/users/add",
                HttpMethod.POST,
                entity,
                String.class
        );

        return "redirect:/login";
    }
}