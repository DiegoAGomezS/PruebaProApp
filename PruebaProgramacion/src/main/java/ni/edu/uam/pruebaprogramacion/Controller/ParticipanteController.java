package ni.edu.uam.pruebaprogramacion.Controller;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import ni.edu.uam.pruebaprogramacion.Model.Participante;
import ni.edu.uam.pruebaprogramacion.Validator.ParticipanteValidator;

import java.util.Arrays;

public class ParticipanteController {

    // Campos de texto
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellido;
    @FXML
    private TextField txtEdad;
    @FXML
    private TextField txtTelefono;


    // Categoría
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


    @FXML
    public void initialize() {
        configurarTabla();
        tablaParticipante.setItems(participantes);
    }
    // Configurar tabla

    private void configurarTabla() {
        columnaNombre.setCellValueFactory(
                dato -> new SimpleStringProperty(
                        dato.getValue().getNombre()
                )
        );
        columnaApellido.setCellValueFactory(
                dato -> new SimpleStringProperty(
                        dato.getValue().getApellido()
                )
        );
        columnaEdad.setCellValueFactory(
                dato -> new SimpleObjectProperty<>(
                        dato.getValue().getEdad()
                )
        );
        columnaTelefono.setCellValueFactory(
                dato -> new SimpleStringProperty(
                        dato.getValue().getTelefono()
                )
        );
        columnaCategoria.setCellValueFactory(
                dato -> new SimpleStringProperty(
                        dato.getValue().getCategoria()
                )
        );
        columnaDisciplina.setCellValueFactory(
                dato -> new SimpleStringProperty(
                        dato.getValue().getDisciplina()
                )
        );
        columnaCaracteristicas.setCellValueFactory(
                dato -> new SimpleStringProperty(
                        dato.getValue().getCaracteristicas()
                )
        );
        columnaEstado.setCellValueFactory(
                dato -> new SimpleStringProperty(
                        dato.getValue().getEstado()
                )
        );
    }


    // Mostrar tabla / agregar participante
    @FXML
    private void mostrarTabla() {
        Integer edad = null;
        if (!txtEdad.getText().trim().isEmpty()) {
            try {
                edad = Integer.parseInt(
                        txtEdad.getText().trim()
                );
            } catch (NumberFormatException e) {
                mostrarAlerta(
                        Alert.AlertType.ERROR,
                        "Error",
                        "La edad debe ser un número entero."
                );
                return;
            }
        }
        Participante participante = new Participante(
                txtNombre.getText(),
                txtApellido.getText(),
                edad,
                txtTelefono.getText(),
                obtenerCategoria(),
                obtenerModalidad(),
                obtenerDisciplina(),
                obtenerCaracteristicas(),
                "Registrado"
        );
        // Validar participante
        String error =
                ParticipanteValidator.validar(participante);
        if (error != null) {
            mostrarAlerta(
                    Alert.AlertType.ERROR,
                    "Error de validación",
                    error
            );
            return;
        }
        // Agregar participante
        participantes.add(participante);
        limpiarFormulario();
        mostrarAlerta(
                Alert.AlertType.INFORMATION,
                "Registro exitoso",
                "El participante fue agregado correctamente."
        );
    }


    // Eliminar participante
    @FXML
    private void eliminarParticipante() {
        Participante participante =
                tablaParticipante
                        .getSelectionModel()
                        .getSelectedItem();


        if (participante == null) {
            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Eliminar participante",
                    "Debe seleccionar un participante de la tabla."
            );
            return;
        }
        Alert confirmacion =
                new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Eliminar participante");
        confirmacion.setHeaderText(null);
        confirmacion.setContentText(
                "¿Está seguro de eliminar al participante "
                        + participante.getNombre()
                        + " "
                        + participante.getApellido()
                        + "?"
        );


        confirmacion.showAndWait().ifPresent(
                respuesta -> {
                    if (respuesta.getButtonData().isDefaultButton()) {
                        participantes.remove(participante);
                        mostrarAlerta(
                                Alert.AlertType.INFORMATION,
                                "Participante eliminado",
                                "El participante fue eliminado correctamente."
                        );
                    }
                }
        );
    }


    // Obtener categoría

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
        return "";
    }


    // Obtener modalidad

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
        return "";
    }


    // Obtener disciplina
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
        return "";
    }


    // Obtener características

    private String obtenerCaracteristicas() {
        StringBuilder caracteristicas =
                new StringBuilder();
        if (cbFederado.isSelected()) {
            caracteristicas.append("Federado, ");
        }
        if (cbExperiencia.isSelected()) {
            caracteristicas.append("Experiencia previa, ");
        }
        if (cbDisponibilidad.isSelected()) {
            caracteristicas.append(
                    "Disponibilidad fines de semana, "
            );
        }
        if (cbSeguro.isSelected()) {
            caracteristicas.append("Seguro deportivo, ");
        }
        if (caracteristicas.length() == 0) {
            return "Ninguna";
        }
        return caracteristicas.substring(
                0,
                caracteristicas.length() - 2
        );
    }


    // Seleccionar categoría

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


    // Seleccionar modalidad

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


    // Seleccionar disciplina
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

    // Método para selección única

    private void ejecutarSeleccion(
            CheckBox seleccionado,
            CheckBox... opciones) {
        if (seleccionado.isSelected()) {
            Arrays.stream(opciones)
                    .forEach(
                            opcion ->
                                    opcion.setSelected(false)
                    );
        }
    }

    // Limpiar formulario
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

    // Mostrar alerta
    private void mostrarAlerta(
            Alert.AlertType tipo,
            String titulo,
            String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}