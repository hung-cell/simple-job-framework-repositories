package com.example.simplejobapplication;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class TransportEngineConfig {

    private List<Object> actorList;
}
