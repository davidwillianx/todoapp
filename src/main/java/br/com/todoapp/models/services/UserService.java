package br.com.todoapp.models.services;

import br.com.todoapp.models.domains.User;
import br.com.todoapp.models.repositories.contracts.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public User searchByEmail(String email){
        return userRepository.findByEmail(email);
    }

}
