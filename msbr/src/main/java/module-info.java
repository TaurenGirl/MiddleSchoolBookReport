module com.gmail.skibinski.tomi.msbr {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.gmail.skibinski.tomi.msbr to javafx.fxml;
    exports com.gmail.skibinski.tomi.msbr;
}
