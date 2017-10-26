package genericdao.vistajesus;

import acadt_proyectoexportjdom.ACADT_ProyectoExportJDOM;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;


/**
 *
 * @author Jesús González <jesusgonzalezmerin@gmail.com>
 */
public class DeleteController implements Initializable, IControlPantallas {

    //Atributos
    private ACADT_ProyectoExportJDOM menuVentanas;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Menú borrado cargado.");

    }

    @Override
    public void setMainApp(ACADT_ProyectoExportJDOM mainApp) {
        menuVentanas = mainApp;
    }

}
