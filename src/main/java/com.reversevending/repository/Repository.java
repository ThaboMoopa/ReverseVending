package com.reversevending.repository;

import java.util.List;

public interface Repository<T> {
    void add(T t);
    void update(T t);
    void delete(long id);
    T getById(long id);
    List<T> getAll();
}
