package com.scottlogic.librarygradproject.service;

import java.util.Optional;

public interface ServiceInterface<T> {
    T get(int id);
}
