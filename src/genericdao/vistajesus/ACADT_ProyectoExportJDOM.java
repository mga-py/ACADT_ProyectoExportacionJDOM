/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genericdao.vistajesus;

import acadt_proyectoexportjdom.*;
import genericdao.vistajesus.IControlPantallas;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author Baltasar Rangel Pinilla  <mga-py>----<baltasarrangel93@gmail.com">
 */
public class ACADT_ProyectoExportJDOM extends Application {
    //Atributos
    private Scene scene;

    @Override
    public void start(Stage stage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("Principal.fxml"));

        //Es lo mismo, pero para poder cargar el controlador.
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/genericdao/vistajesus/Principal.fxml"));
        Parent root = loader.load();

        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Aplicación JDOM");
        //
        IControlPantallas controlador = (IControlPantallas) loader.getController();
        controlador.setMainApp(this);
        cambiarContenido("/genericdao/vistajesus/Portada.fxml");

        stage.show();
    }

    public boolean cambiarContenido(String archivoFxml) {
        boolean correcto = true;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(archivoFxml));
            AnchorPane pantalla = (AnchorPane) loader.load();
            BorderPane bp = (BorderPane) scene.getRoot();
            bp.setCenter(pantalla);
            IControlPantallas controlador = (IControlPantallas) loader.getController();
            controlador.setMainApp(this);

        } catch (IOException ex) {
            correcto = false;
        }
        return correcto;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
