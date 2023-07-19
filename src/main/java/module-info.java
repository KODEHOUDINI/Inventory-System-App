module com.example.lesleyedinamabotsi {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
            
                            
    opens com.example.lesleyedinamabotsi2257 to javafx.fxml;
    exports com.example.lesleyedinamabotsi2257;
    exports com.example.lesleyedinamabotsi2257.controller;
    opens com.example.lesleyedinamabotsi2257.controller to javafx.fxml;
}