package com.example.simplejobapplication;


import org.springframework.stereotype.Component;

@Component
@Actor(path = "/server")
public class ExampleHandler {

    @Handler(path = "/example", processType = ProcessType.BLOCKING)
    public void handleExampleRequest() {
        System.out.println("Handling example request in a blocking manner");
    }

    @Handler(path = "/another", processType = ProcessType.NON_BLOCKING)
    public void handleAnotherRequest() {
        System.out.println("Handling another request in a non-blocking manner");
    }

}
