package com.neptune.utils;

@FunctionalInterface
public interface ToDTO<T> {
    T toDto();
}
