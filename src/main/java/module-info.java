module fbg.fittrack {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.google.gson;
    opens fbg.fittrack to com.google.gson, javafx.fxml;

    exports fbg.fittrack;
}