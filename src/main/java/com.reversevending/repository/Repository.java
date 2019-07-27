package com.reversevending.repository;

import java.util.List;

public interface Repository<T> {
    void add(T t);
    void update(T t);
    void delete(Long id);
    T getById(Long id);
    List<T> getAll();
}
