module com.gmail.skibinski.tomi.msbr {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.swing;
    requires barcode4j;

    opens com.gmail.skibinski.tomi.msbr to javafx.fxml;
    exports com.gmail.skibinski.tomi.msbr;
}
