package me.study.jpatodo.common;

public interface BaseRequestDto<T> {
    T toEntity();
}
