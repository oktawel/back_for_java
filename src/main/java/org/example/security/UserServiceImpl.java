package org.example.security;

import org.example.models.Users;
import org.example.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersRepository userRepository;

    @Override
    public Users findByLogin(String login) {
        return userRepository.findByLogin(login);
    }
}