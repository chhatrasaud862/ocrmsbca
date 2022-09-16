package com.ocrms.ocrmsbca.components;

import org.springframework.stereotype.Component;

/**
 * @author CHHATRA SAUD
 * @Date 16/09/2022
 */
@Component
public class Notifications{
    private String name;
    private String message;

    public String getNotifications(String name, String message) {
        return name;
    }
}
