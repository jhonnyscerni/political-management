package br.com.projeto.politicalmanagement.utils;

public interface ModelMapper<T, V> {

    V toResponse(T entity);

    T toEntity(V model);
}
