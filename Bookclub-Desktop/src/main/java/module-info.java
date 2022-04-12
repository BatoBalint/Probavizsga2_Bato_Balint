module hu.petrik.bookclubdesktop {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;

    opens hu.petrik.bookclubdesktop to javafx.fxml;
    exports hu.petrik.bookclubdesktop;
}