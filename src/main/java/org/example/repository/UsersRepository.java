package org.example.repository;

import org.example.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    Users findByLogin(String login); // поиск только по логину

    // Поиск по логину и паролю
    @Query("SELECT u FROM Users u WHERE u.login = :login AND u.password = :password")
    Optional<Users> findByLoginAndPassword(@Param("login") String login, @Param("password") String password);
}
