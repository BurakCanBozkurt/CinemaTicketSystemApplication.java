package com.Softito.cinemaTicketSystem.Services;

import com.Softito.cinemaTicketSystem.Model.User;
import com.Softito.cinemaTicketSystem.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IBaseService<User> {

    @Autowired
    private UserRepository repository;

    @Override
    public List<User> getAll() {
        return repository.findAll();
    }

    @Override
    public User getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public User create(User entity) {
        return repository.save(entity);
    }

    @Override
    public User update(Long id, User entity) {
        User existingUser = getById(id);
        if (existingUser != null) {
            existingUser.setUpdated_at(entity.getUpdated_at());
            existingUser.setName(entity.getName());
            existingUser.setBalance(entity.getBalance());
            existingUser.setSurname(entity.getSurname());
            existingUser.setEmail(entity.getEmail());
            existingUser.setPassword(entity.getPassword());
            existingUser.setPhoto(entity.getPhoto());
            existingUser.setIsActive(entity.getIsActive());
            return repository.save(existingUser);
        }
        return null;
    }

    @Override
    public boolean delete(Long id) {
        if (getById(id) != null) {
            repository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}