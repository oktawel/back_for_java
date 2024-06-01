package org.example.security;

import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TokenBlacklistService {
    private final Set<String> blacklist = ConcurrentHashMap.newKeySet();

    public void invalidateToken(String token) {
        blacklist.add(token);
    }

    public boolean isTokenInvalid(String token) {
        return blacklist.contains(token);
    }
}
