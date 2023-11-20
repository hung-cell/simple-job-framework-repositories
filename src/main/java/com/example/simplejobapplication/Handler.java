package com.example.simplejobapplication;


import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Handler {
    String path();
    ProcessType processType() default ProcessType.BLOCKING;
}
