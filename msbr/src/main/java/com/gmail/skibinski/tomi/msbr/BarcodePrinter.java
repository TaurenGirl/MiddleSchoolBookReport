package com.gmail.skibinski.tomi.msbr;

import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.stage.Stage;

public class BarcodePrinter {
    
    public BarcodePrinter() {

    }

    public void setup(Node node, Stage stage) {
        
        PrinterJob job = PrinterJob.createPrinterJob();

        if (job == null) {
            return;
        }

        boolean advance = job.showPageSetupDialog(stage);

        if (advance) {
            print(job,node);
        }
    }

    public void print(PrinterJob job, Node node) {
        boolean printed = job.printPage(node);
        
        if (printed) {
            job.endJob();
        }
    }

}
