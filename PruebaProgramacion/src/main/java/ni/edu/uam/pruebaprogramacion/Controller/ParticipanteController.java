package ni.edu.uam.pruebaprogramacion.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import ni.edu.uam.pruebaprogramacion.Model.Participante;
import ni.edu.uam.pruebaprogramacion.Validator.ParticipanteValidator;

import java.util.Arrays;

public class ParticipanteController {

    //Participante
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellido;
    @FXML
    private TextField txtEdad;
    @FXML
    private TextField txtTelefono;

    //Categoría
    @FXML
    private CheckBox cbJuvenil;
    @FXML
    private CheckBox cbIntermedia;
    @FXML
    private CheckBox cbSenior;

    // Modalidad
    @FXML
    private CheckBox cbIndividual;
    @FXML
    private CheckBox cbParejas;
    @FXML
    private CheckBox cbEquipos;

    // Características
    @FXML
    private CheckBox cbFederado;
    @FXML
    private CheckBox cbExperiencia;
    @FXML
    private CheckBox cbDisponibilidad;
    @FXML
    private CheckBox cbSeguro;

    // Disciplina
    @FXML
    private CheckBox cbFutbol;
    @FXML
    private CheckBox cbBaloncesto;
    @FXML
    private CheckBox cbVoleibol;
    @FXML
    private CheckBox cbAtletismo;
    @FXML
    private CheckBox cbNatacion;
    @FXML
    private CheckBox cbTenis;

    // Tabla
    @FXML
    private TableView<Participante> tablaParticipante;
    @FXML
    private TableColumn<Participante, String> columnaNombre;
    @FXML
    private TableColumn<Participante, String> columnaApellido;
    @FXML
    private TableColumn<Participante, Integer> columnaEdad;
    @FXML
    private TableColumn<Participante, String> columnaTelefono;
    @FXML
    private TableColumn<Participante, String> columnaCategoria;
    @FXML
    private TableColumn<Participante, String> columnaDisciplina;
    @FXML
    private TableColumn<Participante, String> columnaCaracteristicas;
    @FXML
    private TableColumn<Participante, String> columnaEstado;


    // Lista de participantes
    private ObservableList<Participante> participantes =
            FXCollections.observableArrayList();


    // Inicialización de la tabla
    @FXML
    private void initialize() {
        columnaNombre.setCellValueFactory(
                new PropertyValueFactory<>("nombre")
        );
        columnaApellido.setCellValueFactory(
                new PropertyValueFactory<>("apellido")
        );
        columnaEdad.setCellValueFactory(
                new PropertyValueFactory<>("edad")
        );
        columnaTelefono.setCellValueFactory(
                new PropertyValueFactory<>("telefono")
        );
        columnaCategoria.setCellValueFactory(
                new PropertyValueFactory<>("categoria")
        );
        columnaDisciplina.setCellValueFactory(
                new PropertyValueFactory<>("disciplina")
        );
        columnaCaracteristicas.setCellValueFactory(
                new PropertyValueFactory<>("caracteristicas")
        );
        columnaEstado.setCellValueFactory(
                new PropertyValueFactory<>("estado")
        );
        tablaParticipante.setItems(participantes);
    }


    // Método para agregar participante
    @FXML
    private void mostrarTabla() {

        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String edadTexto = txtEdad.getText();
        String telefono = txtTelefono.getText();

        String categoria = obtenerCategoria();
        String modalidad = obtenerModalidad();
        String disciplina = obtenerDisciplina();
        String caracteristicas = obtenerCaracteristicas();

        Integer edad;

        try {

            edad = Integer.parseInt(edadTexto);

        } catch (NumberFormatException e) {

            mostrarAlerta(
                    "Error",
                    "La edad debe ser un número."
            );

            return;
        }
        Participante participante = new Participante(
                nombre,
                apellido,
                edad,
                telefono,
                categoria,
                modalidad,
                disciplina,
                caracteristicas,
                "Inscrito"
        );
        String error = ParticipanteValidator.validar(participante);
        if (error != null) {
            mostrarAlerta(
                    "Error de validación",
                    error
            );
            return;
        }

        participantes.add(participante);
        limpiarFormulario();
    }


    //Metodos de categoría

    @FXML
    private void seleccionarJuvenil() {

        ejecutarSeleccion(
                cbJuvenil,
                cbIntermedia,
                cbSenior
        );
    }

    @FXML
    private void seleccionarIntermedia() {

        ejecutarSeleccion(
                cbIntermedia,
                cbJuvenil,
                cbSenior
        );
    }

    @FXML
    private void seleccionarSenior() {

        ejecutarSeleccion(
                cbSenior,
                cbJuvenil,
                cbIntermedia
        );
    }


    //Metodos modalidad

    @FXML
    private void seleccionarIndividual() {

        ejecutarSeleccion(
                cbIndividual,
                cbParejas,
                cbEquipos
        );
    }

    @FXML
    private void seleccionarParejas() {

        ejecutarSeleccion(
                cbParejas,
                cbIndividual,
                cbEquipos
        );
    }

    @FXML
    private void seleccionarEquipos() {

        ejecutarSeleccion(
                cbEquipos,
                cbIndividual,
                cbParejas
        );
    }


    //Metodos de Disciplina

    @FXML
    private void seleccionarFutbol() {

        ejecutarSeleccion(
                cbFutbol,
                cbBaloncesto,
                cbVoleibol,
                cbAtletismo,
                cbNatacion,
                cbTenis
        );
    }

    @FXML
    private void seleccionarBaloncesto() {

        ejecutarSeleccion(
                cbBaloncesto,
                cbFutbol,
                cbVoleibol,
                cbAtletismo,
                cbNatacion,
                cbTenis
        );
    }

    @FXML
    private void seleccionarVoleibol() {

        ejecutarSeleccion(
                cbVoleibol,
                cbFutbol,
                cbBaloncesto,
                cbAtletismo,
                cbNatacion,
                cbTenis
        );
    }

    @FXML
    private void seleccionarAtletismo() {

        ejecutarSeleccion(
                cbAtletismo,
                cbFutbol,
                cbBaloncesto,
                cbVoleibol,
                cbNatacion,
                cbTenis
        );
    }

    @FXML
    private void seleccionarNatacion() {
        ejecutarSeleccion(
                cbNatacion,
                cbFutbol,
                cbBaloncesto,
                cbVoleibol,
                cbAtletismo,
                cbTenis
        );
    }

    @FXML
    private void seleccionarTenis() {

        ejecutarSeleccion(
                cbTenis,
                cbFutbol,
                cbBaloncesto,
                cbVoleibol,
                cbAtletismo,
                cbNatacion
        );
    }


    //Metodo de ejecutarSeleccion
    //Permite que solo uno sea seleccionado

    private void ejecutarSeleccion(
            CheckBox seleccionado,
            CheckBox... opciones) {

        if (seleccionado.isSelected()) {

            Arrays.stream(opciones)
                    .forEach(opcion -> opcion.setSelected(false));
        }
    }


    //Metodos de obtención de datos

    private String obtenerCategoria() {

        if (cbJuvenil.isSelected()) {
            return "Juvenil";
        }

        if (cbIntermedia.isSelected()) {
            return "Intermedia";
        }

        if (cbSenior.isSelected()) {
            return "Senior";
        }

        return null;
    }


    private String obtenerModalidad() {

        if (cbIndividual.isSelected()) {
            return "Individual";
        }

        if (cbParejas.isSelected()) {
            return "Parejas";
        }

        if (cbEquipos.isSelected()) {
            return "Equipos";
        }

        return null;
    }


    private String obtenerDisciplina() {

        if (cbFutbol.isSelected()) {
            return "Fútbol";
        }

        if (cbBaloncesto.isSelected()) {
            return "Baloncesto";
        }

        if (cbVoleibol.isSelected()) {
            return "Voleibol";
        }

        if (cbAtletismo.isSelected()) {
            return "Atletismo";
        }

        if (cbNatacion.isSelected()) {
            return "Natación";
        }

        if (cbTenis.isSelected()) {
            return "Tenis";
        }

        return null;
    }


    private String obtenerCaracteristicas() {
        StringBuilder caracteristicas = new StringBuilder();
        if (cbFederado.isSelected()) {
            caracteristicas.append("Federado");
        }

        if (cbExperiencia.isSelected()) {
            if (caracteristicas.length() > 0) {
                caracteristicas.append(", ");
            }
            caracteristicas.append("Experiencia previa");
        }

        if (cbDisponibilidad.isSelected()) {
            if (caracteristicas.length() > 0) {
                caracteristicas.append(", ");
            }
            caracteristicas.append("Disponibilidad fines de semana");
        }

        if (cbSeguro.isSelected()) {
            if (caracteristicas.length() > 0) {
                caracteristicas.append(", ");
            }
            caracteristicas.append("Seguro deportivo");
        }
        return caracteristicas.toString();
    }


    // Método para mostrar alertas

    private void mostrarAlerta(
            String titulo,
            String mensaje) {

        Alert alerta = new Alert(Alert.AlertType.ERROR);

        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);

        alerta.showAndWait();
    }


    // Método para limpiar el formulario

    private void limpiarFormulario() {

        txtNombre.clear();
        txtApellido.clear();
        txtEdad.clear();
        txtTelefono.clear();

        cbJuvenil.setSelected(false);
        cbIntermedia.setSelected(false);
        cbSenior.setSelected(false);
        cbIndividual.setSelected(false);
        cbParejas.setSelected(false);
        cbEquipos.setSelected(false);

        cbFederado.setSelected(false);
        cbExperiencia.setSelected(false);
        cbDisponibilidad.setSelected(false);
        cbSeguro.setSelected(false);

        cbFutbol.setSelected(false);
        cbBaloncesto.setSelected(false);
        cbVoleibol.setSelected(false);
        cbAtletismo.setSelected(false);
        cbNatacion.setSelected(false);
        cbTenis.setSelected(false);
    }
}