package com.neptune.utils;

@FunctionalInterface
public interface TransformFrom <F,T>{
    T from(F f);
}

