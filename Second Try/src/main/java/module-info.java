module testinho {
    requires javafx.controls;
    requires javafx.fxml;

    opens testinho to javafx.fxml;
    exports testinho;
}
