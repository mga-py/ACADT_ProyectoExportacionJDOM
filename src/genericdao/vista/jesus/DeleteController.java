package genericdao.vista.jesus;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import pkg002academia.IControlPantallas;
import pkg002academia.Main;

/**
 *
 * @author Jesús González <jesusgonzalezmerin@gmail.com>
 */
public class DeleteController implements Initializable, IControlPantallas {

    //Atributos
    private Main menuVentanas;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Menú borrado cargado.");

    }

    @Override
    public void setMainApp(Main mainApp) {
        menuVentanas = mainApp;
    }

}
