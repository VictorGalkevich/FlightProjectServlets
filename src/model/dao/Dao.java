package model.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<K, T> {
    List<T> getAll();
    Optional<T> getById();
    int delete(T t);
    int update(T t);
    int insert(T t);
}
