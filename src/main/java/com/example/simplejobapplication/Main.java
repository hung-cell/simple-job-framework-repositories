package com.example.simplejobapplication;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Map;

@Component
public class Main implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @PostConstruct
    public void initFrameWork() {

        final Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(Actor.class);

        TransportEngineConfig config = new TransportEngineConfig();
        config.setActorList(new ArrayList<>(beansWithAnnotation.values()));
        new TransportEngine().start(config);
    }
}
