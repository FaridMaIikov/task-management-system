package com.company.taskmanagementsystem.security;

public interface TokenReader <T>{

    T read(String token);
}
