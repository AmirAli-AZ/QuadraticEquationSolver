module com.amirali.quadraticequationsolver {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.amirali.quadraticequationsolver to javafx.fxml;
    exports com.amirali.quadraticequationsolver;
}