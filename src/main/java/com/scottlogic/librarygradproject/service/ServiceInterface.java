package com.scottlogic.librarygradproject.service;

import java.util.UUID;

public interface ServiceInterface<T> {
    T get(UUID id);
}
