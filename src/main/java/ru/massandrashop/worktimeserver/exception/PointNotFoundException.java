package ru.massandrashop.worktimeserver.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PointNotFoundException extends RuntimeException {
    public PointNotFoundException(String macAddress) {
        super("Точка подключения не найдена. Обратитесь к администратору");
        log.info(String.format("----------> Point with MAC address %s was not found.", macAddress));
    }

}