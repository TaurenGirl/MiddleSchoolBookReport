package com.gmail.skibinski.tomi.msbr;

import javafx.scene.input.MouseEvent;

public class BarcodeListener {
    
private String buffer;
private boolean listen = false;

    public BarcodeListener() {
        buffer = "";
    }

    public String listen(String key) {
        String string = "";
        buffer = buffer + key;
        if (listen && buffer.length() == 7) {
            string = buffer;
            buffer = "";
            listen = false;
            return string;
        }
        if (buffer.endsWith("07099")) {
            listen = true;
            buffer = "";
        }
        return null;
    }

}
