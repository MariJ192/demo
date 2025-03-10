package org.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {

    public MenuButton menu;
    public MenuItem usuarios;
    public MenuItem informes;
    public MenuItem salir;


    @FXML
    public void btnUsuarios(ActionEvent event) {
        //Cuando le damos al boton de generar informe de artistas, se abre otra ventana donde nos lista los artistas
        FXMLLoader loader = new FXMLLoader(getClass().getResource("InformeUsuariosView.fxml"));
        try {
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Informe de usuarios");

            stage.showAndWait();
        } catch (IOException e) {
            System.out.println("No se pudo abrir el archivo");
        }

    }


    public void btnInformes(ActionEvent event) {

    }


    @FXML
    public void btnSalirApp(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación de salida");
        alert.setHeaderText("¿Está seguro de que desea salir?");

        // Mostramos la alerta y esperamos la respuesta
        ButtonType respuesta = alert.showAndWait().orElse(ButtonType.CANCEL);

        if (respuesta == ButtonType.OK) {
            // Si el usuario confirma la salida, cerramos la aplicación
            System.exit(0);
        }
    }


}