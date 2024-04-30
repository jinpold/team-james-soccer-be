package com.james.api.common.query;

import java.util.List;
import java.util.Optional;

public interface QueryService<T> {
    List<T> findAll();
    Optional<T> findById(Long id);
    Long count();
//    boolean existsById(Long id);
}
