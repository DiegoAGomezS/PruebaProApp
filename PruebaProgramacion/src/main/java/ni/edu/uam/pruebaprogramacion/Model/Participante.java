package ni.edu.uam.pruebaprogramacion.Model;

public class Participante {

    private String nombre;
    private String apellido;
    private Integer edad;
    private String telefono;
    private String categoria;
    private String modalidad;
    private String disciplina;
    private String caracteristicas;
    private String estado;

    public Participante() {
    }

    public Participante(String nombre,
                        String apellido,
                        Integer edad,
                        String telefono,
                        String categoria,
                        String modalidad,
                        String disciplina,
                        String caracteristicas,
                        String estado) {

        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.telefono = telefono;
        this.categoria = categoria;
        this.modalidad = modalidad;
        this.disciplina = disciplina;
        this.caracteristicas = caracteristicas;
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}