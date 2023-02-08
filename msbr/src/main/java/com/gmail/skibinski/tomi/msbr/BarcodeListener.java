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
            string = buffer.substring(0, 6);
            buffer = "";
            listen = false;
            return string;
        }
        if (buffer.endsWith("09181")) {
            listen = true;
            buffer = "";
        }
        return null;
    }

}
