package com.codegym.repository;

import java.util.List;

public interface Reposity<U> {
    List<U> findAll();

    void save(U model);
}
