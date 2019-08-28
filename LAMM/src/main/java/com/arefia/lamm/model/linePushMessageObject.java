package com.arefia.lamm.model;

import lombok.Data;

@Data
public class linePushMessageObject {
    private String sourcer;
    private String messageType;
    private String filename;
    private String message;
    private String pusher;
    private String pushfid;
}
