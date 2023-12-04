package com.company.taskmanagementsystem.security;

public interface TokenGenerator<T>{

    String generate(T t);
}
