package ru.massandrashop.worktimeserver.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OfficeNotFoundException extends RuntimeException {
    public OfficeNotFoundException(Long id) {
        super("Офис не найден. Обратитесь к администратору");
        log.info(String.format("----------> Office with id %d was not found.", id));
    }

}