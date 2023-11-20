package com.example.simplejobapplication;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Getter
@Setter
@ToString
@Accessors(chain = true)
public class HandlerLocation implements Serializable {
    /**
     * 根路径
     */
    private String rootPath;
    /**
     * 方法路径
     */
    private String methodPath;

    public String toPath() {
        return String.format("/%s/%s", rootPath, methodPath);
    }
}
