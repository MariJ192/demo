package org.example.demo;

import com.fasterxml.jackson.databind.SerializationFeature;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class InformeUsuarios {
    public ListView<Usuarios> listaUsuarios;
    private final Usuarios usuario = Usuarios.getInstancia(); // Usamos la instancia de Usuarios
    private ObservableList<Usuarios> listaObsUsuaruis = FXCollections.observableArrayList();


    public void initialize() {

        listaObsUsuaruis.add(new Usuarios("mari19", "mari192", "Maria Jose", "Alcaraz", Usuarios.Role.ESTUDIANTE));
        listaObsUsuaruis.add(new Usuarios("jose10", "123456", "Jose", "Hernandez", Usuarios.Role.ADMIN));
        listaObsUsuaruis.add(new Usuarios("ana22", "qwerty", "Ana", "Perez", Usuarios.Role.PROFESOR));

        // Mostrar los usuarios en el ListView
        listaUsuarios.setItems(listaObsUsuaruis);

        // Configuración de cómo se mostrarán los usuarios en el ListView
        listaUsuarios.setCellFactory(param -> new ListCell<Usuarios>() {
            @Override
            protected void updateItem(Usuarios usuario, boolean empty) {
                super.updateItem(usuario, empty);
                if (empty || usuario == null) {
                    setText(null);
                } else {
                    setText(usuario.getLogin() + " - " + usuario.getNombre() + " " + usuario.getApellido());
                }
            }
        });
    }

    public void agregarUsu(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(HelloController.class.getResource("CrearEditarUsuarios.fxml"));
        try {
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Crear Usuario");

            // Obtener el controlador y pasar NULL para crear un nuevo usuario
            CrearEditarUsuarios crearEditarController = loader.getController();
            crearEditarController.inicializarFormulario(null);

            stage.showAndWait();

            // Recargar los usuarios después de agregar uno nuevo
           // listaUsuarios.setItems(usuario.getListaUsuarios()); // Aseguramos que la lista se actualice

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void eliminarUsuario(ActionEvent event) {
        int indexSeleccionado = listaUsuarios.getSelectionModel().getSelectedIndex();

        if (indexSeleccionado != -1) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmación de eliminación");
            alert.setHeaderText("¿Está seguro de que desea eliminar este usuario?");

            ButtonType respuesta = alert.showAndWait().orElse(ButtonType.CANCEL);
            if (respuesta == ButtonType.OK) {
                // Eliminar el usuario de la lista
                listaUsuarios.getItems().remove(indexSeleccionado);
                listaUsuarios.refresh();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Seleccione un usuario para eliminar.");
            alert.showAndWait();
        }
    }

    public void modificarUsu(ActionEvent event) {
        Usuarios usuarioSeleccionado = listaUsuarios.getSelectionModel().getSelectedItem();

        if (usuarioSeleccionado != null) {
            FXMLLoader loader = new FXMLLoader(HelloController.class.getResource("CrearEditarUsuarios.fxml"));
            try {
                Scene scene = new Scene(loader.load());
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Modificar Usuario");

                // Pasar el usuario seleccionado
                CrearEditarUsuarios crearEditarController = loader.getController();
                crearEditarController.inicializarFormulario(usuarioSeleccionado);

                stage.showAndWait();

                // Recargar los usuarios después de modificar uno
                listaUsuarios.setItems(usuario.getListaUsuarios()); // Aseguramos que la lista se actualice

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Seleccione un usuario para modificar.");
            alert.showAndWait();
        }
    }

    public void btnCerrar(ActionEvent event) {
        Stage titStage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        titStage.close();
    }

    public void generarInformeUsu(ActionEvent event) {
        try {
            String jasperFilePath = "listaUsuarios.jrxml";
            InputStream inputStream = HelloController.class.getResourceAsStream(jasperFilePath);

            // Compilar el informe JRXML a un archivo Jasper
            System.out.println("Compilando : " + jasperFilePath);
            JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);

            // Al informe compilado le cargamos los parametros y la conexión a la base de datos
            // como nuestro informe no tiene nada de eso proporcionamos objetos vacios.
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, new JRBeanCollectionDataSource(listaObsUsuaruis));

            // Mostramos el informe (el valor true al cerrar el informe se cierra la aplicación.
            JasperViewer.viewReport(jasperPrint, true);
        } catch (JRException e) {
            e.printStackTrace();
        }

    }
}
