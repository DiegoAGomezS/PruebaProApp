package ni.edu.uam.pruebaprogramacion.Controller;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import ni.edu.uam.pruebaprogramacion.Model.Participante;
import ni.edu.uam.pruebaprogramacion.Validator.ParticipanteValidator;

import java.util.Arrays;

public class ParticipanteController {

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtApellido;

    @FXML
    private TextField txtEdad;

    @FXML
    private TextField txtTelefono;


    // CATEGORÍA

    @FXML
    private CheckBox cbJuvenil;

    @FXML
    private CheckBox cbIntermedia;

    @FXML
    private CheckBox cbSenior;


    // GÉNERO

    @FXML
    private CheckBox cbMasculino;

    @FXML
    private CheckBox cbFemenino;

    @FXML
    private CheckBox cbOtro;


    // MODALIDAD

    @FXML
    private CheckBox cbIndividual;

    @FXML
    private CheckBox cbParejas;

    @FXML
    private CheckBox cbEquipos;


    // CARACTERÍSTICAS

    @FXML
    private CheckBox cbFederado;

    @FXML
    private CheckBox cbExperiencia;

    @FXML
    private CheckBox cbDisponibilidad;

    @FXML
    private CheckBox cbSeguro;


    // DISCIPLINA

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


    // TABLA

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
    private TableColumn<Participante, String> columnaGenero;

    @FXML
    private TableColumn<Participante, String> columnaModalidad;

    @FXML
    private TableColumn<Participante, String> columnaDisciplina;

    @FXML
    private TableColumn<Participante, String> columnaCaracteristicas;

    @FXML
    private TableColumn<Participante, String> columnaEstado;


    private ObservableList<Participante> participantes =
            FXCollections.observableArrayList();


    // Guarda el participante que estamos editando
    private Participante participanteEditando = null;


    @FXML
    public void initialize() {

        configurarTabla();

        tablaParticipante.setItems(participantes);
    }


    // ==========================================
    // CONFIGURAR TABLA
    // ==========================================

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

        columnaGenero.setCellValueFactory(
                dato -> new SimpleStringProperty(
                        dato.getValue().getGenero()
                )
        );

        columnaModalidad.setCellValueFactory(
                dato -> new SimpleStringProperty(
                        dato.getValue().getModalidad()
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


    // ==========================================
    // AGREGAR
    // ==========================================

    @FXML
    private void mostrarTabla() {

        Integer edad;

        try {

            edad = Integer.parseInt(txtEdad.getText());

        } catch (NumberFormatException e) {

            mostrarAlerta(
                    Alert.AlertType.ERROR,
                    "Error",
                    "La edad debe ser un número."
            );

            return;
        }


        Participante participante = new Participante(
                txtNombre.getText(),
                txtApellido.getText(),
                edad,
                txtTelefono.getText(),
                obtenerCategoria(),
                obtenerGenero(),
                obtenerModalidad(),
                obtenerDisciplina(),
                obtenerCaracteristicas(),
                "Registrado"
        );


        String error =
                ParticipanteValidator.validar(participante);


        if (error != null) {

            mostrarAlerta(
                    Alert.AlertType.ERROR,
                    "Datos inválidos",
                    error
            );

            return;
        }


        participantes.add(participante);

        limpiarFormulario();


        mostrarAlerta(
                Alert.AlertType.INFORMATION,
                "Registro exitoso",
                "El participante fue registrado correctamente."
        );
    }


    // ==========================================
    // EDITAR
    // ==========================================

    @FXML
    private void editarParticipante() {

        /*
         * Si no estamos editando a nadie,
         * primero obtenemos el participante
         * seleccionado en la tabla.
         */

        if (participanteEditando == null) {

            Participante seleccionado =
                    tablaParticipante
                            .getSelectionModel()
                            .getSelectedItem();


            if (seleccionado == null) {

                mostrarAlerta(
                        Alert.AlertType.WARNING,
                        "Editar participante",
                        "Debe seleccionar un participante de la tabla."
                );

                return;
            }


            participanteEditando = seleccionado;


            cargarDatosFormulario(seleccionado);


            mostrarAlerta(
                    Alert.AlertType.INFORMATION,
                    "Editar participante",
                    "Los datos fueron cargados en el formulario.\n"
                            + "Modifique los datos y presione Editar nuevamente."
            );

            return;
        }


        /*
         * Si ya estamos editando,
         * obtenemos los nuevos datos.
         */

        Integer edad;

        try {

            edad = Integer.parseInt(txtEdad.getText());

        } catch (NumberFormatException e) {

            mostrarAlerta(
                    Alert.AlertType.ERROR,
                    "Error",
                    "La edad debe ser un número."
            );

            return;
        }


        participanteEditando.setNombre(
                txtNombre.getText()
        );

        participanteEditando.setApellido(
                txtApellido.getText()
        );

        participanteEditando.setEdad(edad);

        participanteEditando.setTelefono(
                txtTelefono.getText()
        );

        participanteEditando.setCategoria(
                obtenerCategoria()
        );

        participanteEditando.setGenero(
                obtenerGenero()
        );

        participanteEditando.setModalidad(
                obtenerModalidad()
        );

        participanteEditando.setDisciplina(
                obtenerDisciplina()
        );

        participanteEditando.setCaracteristicas(
                obtenerCaracteristicas()
        );


        String error =
                ParticipanteValidator.validar(
                        participanteEditando
                );


        if (error != null) {

            mostrarAlerta(
                    Alert.AlertType.ERROR,
                    "Datos inválidos",
                    error
            );

            return;
        }


        tablaParticipante.refresh();

        participanteEditando = null;

        limpiarFormulario();


        mostrarAlerta(
                Alert.AlertType.INFORMATION,
                "Edición exitosa",
                "El participante fue actualizado correctamente."
        );
    }


    // ==========================================
    // CARGAR DATOS PARA EDITAR
    // ==========================================

    private void cargarDatosFormulario(
            Participante participante) {

        txtNombre.setText(
                participante.getNombre()
        );

        txtApellido.setText(
                participante.getApellido()
        );

        txtEdad.setText(
                String.valueOf(
                        participante.getEdad()
                )
        );

        txtTelefono.setText(
                participante.getTelefono()
        );


        // Categoría

        cbJuvenil.setSelected(
                participante.getCategoria()
                        .equals("Juvenil")
        );

        cbIntermedia.setSelected(
                participante.getCategoria()
                        .equals("Intermedia")
        );

        cbSenior.setSelected(
                participante.getCategoria()
                        .equals("Senior")
        );


        // Género

        cbMasculino.setSelected(
                participante.getGenero()
                        .equals("Masculino")
        );

        cbFemenino.setSelected(
                participante.getGenero()
                        .equals("Femenino")
        );

        cbOtro.setSelected(
                participante.getGenero()
                        .equals("Otro")
        );


        // Modalidad

        cbIndividual.setSelected(
                participante.getModalidad()
                        .equals("Individual")
        );

        cbParejas.setSelected(
                participante.getModalidad()
                        .equals("Parejas")
        );

        cbEquipos.setSelected(
                participante.getModalidad()
                        .equals("Equipos")
        );


        // Disciplina

        cbFutbol.setSelected(
                participante.getDisciplina()
                        .equals("Fútbol")
        );

        cbBaloncesto.setSelected(
                participante.getDisciplina()
                        .equals("Baloncesto")
        );

        cbVoleibol.setSelected(
                participante.getDisciplina()
                        .equals("Voleibol")
        );

        cbAtletismo.setSelected(
                participante.getDisciplina()
                        .equals("Atletismo")
        );

        cbNatacion.setSelected(
                participante.getDisciplina()
                        .equals("Natación")
        );

        cbTenis.setSelected(
                participante.getDisciplina()
                        .equals("Tenis")
        );


        // Características

        String caracteristicas =
                participante.getCaracteristicas();


        cbFederado.setSelected(
                caracteristicas.contains("Federado")
        );

        cbExperiencia.setSelected(
                caracteristicas.contains(
                        "Experiencia previa"
                )
        );

        cbDisponibilidad.setSelected(
                caracteristicas.contains(
                        "Disponibilidad fines de semana"
                )
        );

        cbSeguro.setSelected(
                caracteristicas.contains(
                        "Seguro deportivo"
                )
        );
    }


    // ==========================================
    // ELIMINAR
    // ==========================================

    @FXML
    private void eliminarParticipante() {

        Participante seleccionado =
                tablaParticipante
                        .getSelectionModel()
                        .getSelectedItem();


        if (seleccionado == null) {

            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Eliminar participante",
                    "Debe seleccionar un participante de la tabla."
            );

            return;
        }


        Alert confirmacion =
                new Alert(Alert.AlertType.CONFIRMATION);

        confirmacion.setTitle(
                "Eliminar participante"
        );

        confirmacion.setHeaderText(null);

        confirmacion.setContentText(
                "¿Está seguro de eliminar al participante "
                        + seleccionado.getNombre()
                        + " "
                        + seleccionado.getApellido()
                        + "?"
        );


        confirmacion.showAndWait().ifPresent(
                respuesta -> {

                    if (respuesta == ButtonType.OK) {

                        participantes.remove(
                                seleccionado
                        );

                        limpiarFormulario();

                        participanteEditando = null;


                        mostrarAlerta(
                                Alert.AlertType.INFORMATION,
                                "Participante eliminado",
                                "El participante fue eliminado correctamente."
                        );
                    }
                }
        );
    }


    // ==========================================
    // LIMPIAR
    // ==========================================

    @FXML
    private void limpiarFormulario() {

        txtNombre.clear();
        txtApellido.clear();
        txtEdad.clear();
        txtTelefono.clear();


        cbJuvenil.setSelected(false);
        cbIntermedia.setSelected(false);
        cbSenior.setSelected(false);


        cbMasculino.setSelected(false);
        cbFemenino.setSelected(false);
        cbOtro.setSelected(false);


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


        participanteEditando = null;
    }


    // ==========================================
    // OBTENER CATEGORÍA
    // ==========================================

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


    // ==========================================
    // OBTENER GÉNERO
    // ==========================================

    private String obtenerGenero() {

        if (cbMasculino.isSelected()) {
            return "Masculino";
        }

        if (cbFemenino.isSelected()) {
            return "Femenino";
        }

        if (cbOtro.isSelected()) {
            return "Otro";
        }

        return "";
    }


    // ==========================================
    // OBTENER MODALIDAD
    // ==========================================

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


    // ==========================================
    // OBTENER DISCIPLINA
    // ==========================================

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


    // ==========================================
    // OBTENER CARACTERÍSTICAS
    // ==========================================

    private String obtenerCaracteristicas() {

        StringBuilder caracteristicas =
                new StringBuilder();


        if (cbFederado.isSelected()) {

            caracteristicas.append("Federado");
        }

        if (cbExperiencia.isSelected()) {

            if (caracteristicas.length() > 0) {
                caracteristicas.append(", ");
            }

            caracteristicas.append(
                    "Experiencia previa"
            );
        }

        if (cbDisponibilidad.isSelected()) {

            if (caracteristicas.length() > 0) {
                caracteristicas.append(", ");
            }

            caracteristicas.append(
                    "Disponibilidad fines de semana"
            );
        }

        if (cbSeguro.isSelected()) {

            if (caracteristicas.length() > 0) {
                caracteristicas.append(", ");
            }

            caracteristicas.append(
                    "Seguro deportivo"
            );
        }


        if (caracteristicas.length() == 0) {
            return "Ninguna";
        }


        return caracteristicas.toString();
    }


    // ==========================================
    // SELECCIÓN EXCLUSIVA
    // ==========================================

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


    // ==========================================
    // CATEGORÍAS
    // ==========================================

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


    // ==========================================
    // GÉNERO
    // ==========================================

    @FXML
    private void seleccionarMasculino() {

        ejecutarSeleccion(
                cbMasculino,
                cbFemenino,
                cbOtro
        );
    }

    @FXML
    private void seleccionarFemenino() {

        ejecutarSeleccion(
                cbFemenino,
                cbMasculino,
                cbOtro
        );
    }

    @FXML
    private void seleccionarOtro() {

        ejecutarSeleccion(
                cbOtro,
                cbMasculino,
                cbFemenino
        );
    }


    // ==========================================
    // MODALIDAD
    // ==========================================

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


    // ==========================================
    // DISCIPLINA
    // ==========================================

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


    // ==========================================
    // VER INFORMACIÓN
    // ==========================================

    @FXML
    private void verInformacion() {

        Participante seleccionado =
                tablaParticipante
                        .getSelectionModel()
                        .getSelectedItem();


        if (seleccionado == null) {

            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Información",
                    "Debe seleccionar un participante."
            );

            return;
        }


        String informacion =
                "Nombre: "
                        + seleccionado.getNombre()
                        + " "
                        + seleccionado.getApellido()
                        + "\n\n"

                        + "Edad: "
                        + seleccionado.getEdad()
                        + "\n\n"

                        + "Teléfono: "
                        + seleccionado.getTelefono()
                        + "\n\n"

                        + "Categoría: "
                        + seleccionado.getCategoria()
                        + "\n\n"

                        + "Género: "
                        + seleccionado.getGenero()
                        + "\n\n"

                        + "Modalidad: "
                        + seleccionado.getModalidad()
                        + "\n\n"

                        + "Disciplina: "
                        + seleccionado.getDisciplina()
                        + "\n\n"

                        + "Características: "
                        + seleccionado.getCaracteristicas()
                        + "\n\n"

                        + "Estado: "
                        + seleccionado.getEstado();


        mostrarAlerta(
                Alert.AlertType.INFORMATION,
                "Información del participante",
                informacion
        );
    }


    // ==========================================
    // ACERCA DE
    // ==========================================

    @FXML
    private void mostrarAcercaDe() {

        mostrarAlerta(
                Alert.AlertType.INFORMATION,
                "Acerca del sistema",
                "Sistema de Gestión de Torneo Deportivo\n\n"
                        + "Aplicación desarrollada en JavaFX.\n"
                        + "Permite registrar, editar, consultar "
                        + "y eliminar participantes."
        );
    }


    // ==========================================
    // SALIR
    // ==========================================

    @FXML
    private void salir() {

        Alert confirmacion =
                new Alert(Alert.AlertType.CONFIRMATION);

        confirmacion.setTitle("Salir");
        confirmacion.setHeaderText(null);
        confirmacion.setContentText(
                "¿Está seguro de que desea salir?"
        );


        confirmacion.showAndWait().ifPresent(
                respuesta -> {

                    if (respuesta == ButtonType.OK) {

                        System.exit(0);
                    }
                }
        );
    }


    // ==========================================
    // ALERTA
    // ==========================================

    private void mostrarAlerta(
            Alert.AlertType tipo,
            String titulo,
            String mensaje) {

        Alert alerta =
                new Alert(tipo);

        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);

        alerta.showAndWait();
    }
}