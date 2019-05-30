package com.arefia.lamm.model;

import java.util.HashMap;

import org.json.JSONObject;

import lombok.Data;

@Data
public class webCommunicationModel {
    private String connURL;
    private HashMap<String, String> headers;
    private JSONObject bodys;
    private String urlparms;
}
