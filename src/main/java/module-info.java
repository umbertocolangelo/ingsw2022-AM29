    module it.polimi.ingsw {
        requires javafx.controls;
        requires javafx.fxml;

        requires org.controlsfx.controls;
        requires java.desktop;

        opens it.polimi.ingsw.model to javafx.fxml;
        exports it.polimi.ingsw.model;
    }