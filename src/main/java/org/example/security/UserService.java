package org.example.security;

import org.example.models.Users;

public interface UserService {
    Users findByLogin(String login);
}