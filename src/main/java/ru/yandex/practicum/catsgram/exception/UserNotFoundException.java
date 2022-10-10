package ru.yandex.practicum.catsgram.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message) {
        super(String.format("Пользователь %s не найден", message));
    }
}
