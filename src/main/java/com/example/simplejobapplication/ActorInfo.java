package com.example.simplejobapplication;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class ActorInfo {

    private Object actor;

    private Actor anno;

    private List<HandlerInfo> handlerInfos;
}
