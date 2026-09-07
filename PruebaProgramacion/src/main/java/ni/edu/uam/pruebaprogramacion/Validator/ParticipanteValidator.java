package ni.edu.uam.pruebaprogramacion.Validator;

import ni.edu.uam.pruebaprogramacion.Model.Participante;

public class ParticipanteValidator {

    public static String validar(Participante participante) {
        if (participante.getNombre() == null ||
                participante.getNombre().trim().isEmpty()) {
            return "El nombre es obligatorio.";
        }
        if (participante.getNombre().trim().length() < 5) {

            return "El nombre debe tener mínimo 5 caracteres.";
        }
        if (participante.getApellido() == null ||
                participante.getApellido().trim().isEmpty()) {

            return "El apellido es obligatorio.";
        }
        if (participante.getEdad() == null) {

            return "La edad es obligatoria.";
        }
        if (participante.getEdad() < 15 ||
                participante.getEdad() > 60) {

            return "La edad debe estar entre 15 y 60 años.";
        }
        if (participante.getTelefono() == null ||
                participante.getTelefono().trim().isEmpty()) {
            return "El teléfono es obligatorio.";
        }
        if (!participante.getTelefono().matches("\\d+")) {
            return "El teléfono solo debe contener números.";
        }
        if (participante.getCategoria() == null ||
                participante.getCategoria().trim().isEmpty()) {
            return "Debe seleccionar una categoría.";
        }
        if (participante.getModalidad() == null ||
                participante.getModalidad().trim().isEmpty()) {
            return "Debe seleccionar una modalidad.";
        }
        if (participante.getDisciplina() == null ||
                participante.getDisciplina().trim().isEmpty()) {
            return "Debe seleccionar una disciplina.";
        }
        return null;
    }
}