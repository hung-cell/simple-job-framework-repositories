package com.example.simplejobapplication;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.lang.reflect.Method;


@Getter
@Setter
@ToString
@Accessors(chain = true)
public class HandlerInfo {

    private HandlerLocation location;
    /**
     * handler 对应的方法
     */
    private Method method;

    /**
     * Handler 注解携带的信息
     */
    private Handler anno;
}

