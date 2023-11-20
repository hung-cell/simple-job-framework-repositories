package com.example.simplejobapplication;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Actor {

    /**
     * root path
     * @return root path
     */
    String path();
}
