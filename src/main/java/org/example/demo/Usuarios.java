package org.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Usuarios {

    private String login;
    private String password;
    private String nombre;
    private String apellido;
    private Role role;  // Variable para el rol
    public static Usuarios instancia = null;

    // Lista de usuarios
    private final ObservableList<Usuarios> listaUsuarios = FXCollections.observableArrayList();


    // Enum para los roles
    public enum Role {
        ADMIN,
        ESTUDIANTE,
        PROFESOR
    }

    private ObservableList<Notas> listaNotas;

    // Constructor con role
    public Usuarios(String login, String password, String nombre, String apellido, Role role) {
        this.login = login;
        this.password = password;
        this.nombre = nombre;
        this.apellido = apellido;
        this.role = role;
    }

    // Constructor sin role (por defecto será ESTUDIANTE)
    public Usuarios(String login, String password, String nombre, String apellido) {
        this.login = login;
        this.password = password;
        this.nombre = nombre;
        this.apellido = apellido;
        this.role = Role.ESTUDIANTE;  // Asignar un rol por defecto si no se especifica
    }

    // Constructor con lista de notas
    public Usuarios(String login, String password, String nombre, String apellido, ObservableList<Notas> listaNotas) {
        this.login = login;
        this.password = password;
        this.nombre = nombre;
        this.apellido = apellido;
        this.listaNotas = listaNotas;
    }

    // Constructor vacío
    public Usuarios() {}

    // Método Singleton para obtener la instancia única de Usuarios
    public static Usuarios getInstancia() {
        if (instancia == null) {
            instancia = new Usuarios();
        }
        return instancia;
    }

    // Getter para la lista de usuarios
    public ObservableList<Usuarios> getListaUsuarios() {
        return listaUsuarios;
    }

    // Método para agregar un usuario a la lista
    public void agregarUsuario(Usuarios usuario) {
        listaUsuarios.add(usuario);
    }

    // Getter y Setter para el rol
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    // Getter y Setter para login
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    // Getter y Setter para password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Getter y Setter para nombre
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Getter y Setter para apellido
    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    // Método para obtener la instancia del usuario (en caso de ser necesario)
    public static void setInstancia(Usuarios instancia) {
        Usuarios.instancia = instancia;
    }

    // Getter para la lista de notas
    public ObservableList<Notas> getListaNotas() {
        return listaNotas;
    }

    public void setListaNotas(ObservableList<Notas> listaNotas) {
        this.listaNotas = listaNotas;
    }


    @Override
    public String toString() {
        return "Usuarios{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", role=" + role +  // Ahora `role` se imprime correctamente
                ", listaNotas=" + listaNotas +
                '}';
    }
}
