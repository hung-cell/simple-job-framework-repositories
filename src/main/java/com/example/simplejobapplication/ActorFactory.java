package com.example.simplejobapplication;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ActorFactory {
    static List<ActorInfo> load(List<Object> actorList) {
        List<ActorInfo> actorInfos = new ArrayList<>();
        actorList.forEach(actor -> {
            final Class<?> clz = actor.getClass();
            try {
                final Actor anno = clz.getAnnotation(Actor.class);

                ActorInfo actorInfo = new ActorInfo().setActor(actor).setAnno(anno);
                actorInfo.setHandlerInfos(loadHandlerInfos4Actor(actorInfo));

                actorInfos.add(actorInfo);
            } catch (Throwable t) {
                System.out.println("[ActorFactory] process Actor[" + clz + "] failed!");
            }
        });

        return actorInfos;
    }


    private static List<HandlerInfo> loadHandlerInfos4Actor(ActorInfo actorInfo) {
        List<HandlerInfo> ret = new ArrayList<>();

        Actor anno = actorInfo.getAnno();
        String rootPath = anno.path();
        Object actor = actorInfo.getActor();

        findHandlerMethod(rootPath, actor.getClass(), ret);
        return ret;
    }

    private static void findHandlerMethod(String rootPath, Class<?> clz, List<HandlerInfo> result) {
        Method[] declaredMethods = clz.getDeclaredMethods();
        for (Method handlerMethod: declaredMethods) {
            Handler handlerMethodAnnotation = handlerMethod.getAnnotation(Handler.class);
            if (handlerMethodAnnotation == null) {
                continue;
            }

            HandlerLocation handlerLocation = new HandlerLocation()
                    .setRootPath(suitPath(rootPath))
                    .setMethodPath(suitPath(handlerMethodAnnotation.path()));

            HandlerInfo handlerInfo = new HandlerInfo()
                    .setAnno(handlerMethodAnnotation)
                    .setMethod(handlerMethod)
                    .setLocation(handlerLocation);
            result.add(handlerInfo);
        }
    }

    private static String suitPath(String path) {
        if (path.startsWith("/")) {
            return path.replaceFirst("/", "");
        }
        return path;
    }
}

