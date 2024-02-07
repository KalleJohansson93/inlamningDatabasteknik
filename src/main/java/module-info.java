module com.example.inlamningdatabasteknik {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.inlamningdatabasteknik to javafx.fxml;
    exports com.example.inlamningdatabasteknik;
}