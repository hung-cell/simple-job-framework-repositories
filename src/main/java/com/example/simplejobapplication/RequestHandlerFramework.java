package com.example.simplejobapplication;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class RequestHandlerFramework {

    private final Map<String, HandlerMethod> handlerRegistry = new HashMap<>();

    public void registerHandlers(Object handlerObject) {
        Class<?> handlerClass = handlerObject.getClass();

        for (Method method : handlerClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Handler.class)) {
                Handler handlerAnnotation = method.getAnnotation(Handler.class);
                String path = handlerAnnotation.path();

                handlerRegistry.put(path, new HandlerMethod(handlerObject, method));
            }
        }
    }

    // Method to dispatch requests to the appropriate handler
    public void dispatchRequest(Request request) {
        String path = request.getPath();

        HandlerMethod handlerMethod = handlerRegistry.get(path);
        if(handlerMethod == null) {
            throw new RuntimeException("No handler for path: " + path);
        } else {
            invokeHandlerMethod(handlerMethod);
        }
    }

    private void invokeHandlerMethod(HandlerMethod handlerMethod) {
        try {
            handlerMethod.getMethod().invoke(handlerMethod.getTarget());
        } catch (Exception e) {
            System.out.println("Error invoking handler: " + e.getMessage());
        }
    }

    private static class HandlerMethod {
        private final Object target;
        private final Method method;

        public HandlerMethod(Object target, Method method) {
            this.target = target;
            this.method = method;
        }

        public Object getTarget() {
            return target;
        }

        public Method getMethod() {
            return method;
        }
    }
}
