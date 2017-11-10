/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acadt_proyectoexportjdom;

import genericdao.vista.IControlPantallas;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/genericdao/vista/Principal.fxml"));
        Parent root = loader.load();

        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Aplicación JDOM");
        //
        IControlPantallas controlador = (IControlPantallas) loader.getController();
        controlador.setMainApp(this);
        cambiarContenido("/genericdao/vista/Portada.fxml");

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
    
    
//      /**
//     * Pasar por parametros el fichero y cambiar el contenido de la ventana
//     * con un controlador este metodo se dirige a el controlador de Listado
//     * que se encargara de editar el fichero
//     * @param archivoFxml archivofxml nuevo
//     * @param pathArchivo (String)ruta del fichero creado/abierto
//     * @return 
//     */
//    public boolean cambiaContenidoFichero(String archivoFxml,String pathArchivo) throws FileNotFoundException, ParseException{
//        boolean correcto=true;
//        try{
//            FXMLLoader loader = new FXMLLoader(getClass().getResource(archivoFxml));
//            AnchorPane pantalla=(AnchorPane)loader.load();
//            BorderPane bp = (BorderPane) scene.getRoot();
//            bp.setCenter(pantalla);
//            //IControlPantallas controlador = (IControlPantallas)loader.getController();
//            
//            //Establecemos el controlador de listado
////            ListadoController controlador = loader.getController();
////            controlador.setMainApp(this);
////            controlador.controlador(pathArchivo);
//            
//            
//            
//        } catch (IOException ex) {
//            correcto=false;
//        }
//        
//        return correcto;
//    }
//    
//    
    
  
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
