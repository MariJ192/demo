package org.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class CrearEditarUsuarios {

    private final Usuarios usuarioInstancia = Usuarios.getInstancia(); // Instancia única de Usuarios
    public Label lblTituloWind;
    public TextField lblLogin;
    public TextField lblPass;
    public TextField lblNombre;
    public TextField lblApellido;
    public Button btnAniadir;
    public Button btnCancelar;
    private ObservableList<Usuarios> listaUsuarios;
    private Usuarios usuario; // Usuario a editar, si es necesario
    Stage titStage;

    public void inicializarFormulario(Usuarios usuarios) {
        // Configuración inicial del título del stage y la etiqueta
        titStage = (Stage) lblTituloWind.getScene().getWindow();
        if (usuarios != null) {
            // Si el usuario no es null, se está editando
            titStage.setTitle("Modificar Usuario");
            lblTituloWind.setText("MODIFICAR UN USUARIO");
            this.usuario = usuarios;

            // Cargar los datos del usuario en los campos del formulario
            lblLogin.setText(usuarios.getLogin());
            lblPass.setText(usuarios.getPassword());
            lblNombre.setText(usuarios.getNombre());
            lblApellido.setText(usuarios.getApellido());

        } else {
            // Si el usuario es null, se está creando uno nuevo
            titStage.setTitle("Crear Usuario");
            lblTituloWind.setText("AÑADIR UN USUARIO");
            this.usuario = null;
        }

    }

    @FXML
    public void agregarUsuario(ActionEvent event) {

        if (usuario == null) {
            lblTituloWind.setText("AÑADIR UNA PELICULA");
            // Crear una nueva película
            listaPeliculas = datosFilmoteca.getPeliculas();
            if (listaPeliculas == null) {
                listaPeliculas = FXCollections.observableArrayList();
            }

            // Crear la nueva película
            usuario = new Pelicula();
            usuario.setId(listaPeliculas.size() + 1);
            usuario.setTitle(txtTitulo.getText());
            usuario.setDirector(textDirector.getText());
            usuario.setDescription(txtDescripcion.getText());

            // Añadir la película a la lista
            listaUsuarios.add(usuario);

        } else {
            // Editar una película existente
            usuario.setTitle(txtTitulo.getText());
            usuario.setDirector(textDirector.getText());
            usuario.setDescription(txtDescripcion.getText());
            usuario.setYear(Integer.parseInt(txtAnio.getText()));

        }

        // Cerrar la ventana después de agregar o editar
        titStage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        titStage.close();
    }


    @FXML
    public void cancelarPelicula(ActionEvent event) {
        // Cerrar la ventana sin hacer cambios
        titStage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        titStage.close();
    }
}
