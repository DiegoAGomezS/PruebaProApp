module ni.edu.uam.pruebaprogramacion {
    requires javafx.controls;
    requires javafx.fxml;


    opens ni.edu.uam.pruebaprogramacion to javafx.fxml;
    exports ni.edu.uam.pruebaprogramacion;
}