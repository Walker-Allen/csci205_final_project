module csci205_final_project {
    exports org.team04;
    requires java.base;
    requires java.desktop;
    requires java.sql;
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    opens org.team04 to javafx.fxml;
}