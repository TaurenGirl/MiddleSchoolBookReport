package com.gmail.skibinski.tomi.msbr;

import java.awt.image.BufferedImage;
import java.io.IOException;

import org.krysalis.barcode4j.BarcodeDimension;
import org.krysalis.barcode4j.ChecksumMode;
import org.krysalis.barcode4j.impl.upcean.UPCABean;
import org.krysalis.barcode4j.output.BarcodeCanvasSetupException;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.output.svg.SVGCanvasProvider;
import org.w3c.dom.DocumentFragment;

import javafx.geometry.Dimension2D;

public class BarcodeGenerator {
    
    UPCABean bean;

    public BarcodeGenerator() {
        bean = new UPCABean();
    }

    public BufferedImage generate(String barcodeText) {
        BitmapCanvasProvider canvas;
        canvas = new BitmapCanvasProvider(300, BufferedImage.TYPE_BYTE_BINARY, false, 0);
        bean.generateBarcode(canvas, barcodeText);
        return canvas.getBufferedImage();
    }

}
