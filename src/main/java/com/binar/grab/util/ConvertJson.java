package com.binar.grab.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Component;

@Component
public class ConvertJson {

    public String toJson(Object obj){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String prettyJsonString = gson.toJson(obj);
        return prettyJsonString;
    }

}
