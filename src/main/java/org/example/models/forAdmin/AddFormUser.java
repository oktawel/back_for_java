package org.example.models.forAdmin;

import jakarta.persistence.*;
import org.example.models.Role;

public class AddFormUser {
    private String login;
    private String password;
    private Role role;
}
