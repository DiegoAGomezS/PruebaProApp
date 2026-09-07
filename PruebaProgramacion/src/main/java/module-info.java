module ni.edu.uam.pruebaprogramacion {
    requires javafx.controls;
    requires javafx.fxml;


    opens ni.edu.uam.pruebaprogramacion to javafx.fxml;
    exports ni.edu.uam.pruebaprogramacion;
    opens ni.edu.uam.pruebaprogramacion.Controller to javafx.fxml;
    exports ni.edu.uam.pruebaprogramacion.Controller;
    opens ni.edu.uam.pruebaprogramacion.Model to javafx.base;
    exports ni.edu.uam.pruebaprogramacion.Model;

}