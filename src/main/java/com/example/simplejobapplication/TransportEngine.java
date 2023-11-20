package com.example.simplejobapplication;

import lombok.AllArgsConstructor;

import java.lang.reflect.Method;
import java.util.List;

@AllArgsConstructor
public class TransportEngine {

    public void start(TransportEngineConfig engineConfig) {
        System.out.println("TransportEngine started");

        List<ActorInfo> actorInfos = ActorFactory.load(engineConfig.getActorList());

        Request request = new Request("example");

        dispatchRequest(actorInfos, request);
    }

    private void dispatchRequest(List<ActorInfo> actorInfos, Request request) {
        String requestPath = request.getPath();
        for (ActorInfo actorInfo : actorInfos) {
            for (HandlerInfo handlerInfo : actorInfo.getHandlerInfos()) {
                if(handlerInfo.getLocation().getMethodPath().equals(requestPath)) {
                    invokeHandler(actorInfo.getActor(), handlerInfo.getMethod());
                    return;  // Assuming you want to handle only the first match
                }
            }
        }
    }

    private void invokeHandler(Object actor, Method method) {
        try {
            method.invoke(actor);
        } catch (Exception e) {
            System.out.println("Error invoking handler: " + e.getMessage());
        }
    }
}
