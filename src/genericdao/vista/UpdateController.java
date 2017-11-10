package genericdao.vista;

import acadt_proyectoexportjdom.ACADT_ProyectoExportJDOM;
import genericdao.modelo.dao.AlumnosDao;
import genericdao.modelo.dao.CursosAlumnoDao;
import genericdao.modelo.dao.CursosDao;
import genericdao.modelo.entities.Alumno;
import genericdao.modelo.entities.Curso;
import genericdao.modelo.entities.CursoAlumno;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Jesús González <jesusgonzalezmerin@gmail.com>
 */
public class UpdateController implements Initializable, IControlPantallas {

    //Atributos
    private ACADT_ProyectoExportJDOM menuVentanas;
    private AlumnosDao alumnoDao;
    private CursosDao cursoDao;

    @FXML
    private TextField tfMatricula;
    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfApellido01;
    @FXML
    private TextField tfApellido02;
    private TextField tfCurso;
    @FXML
    private TextField tfCodigoCurso;
    @FXML
    private TextField tfDescripcionCurso;
    @FXML
    private TextField tfFiltro;
    @FXML
    private Label lbMatricula;
    @FXML
    private Label lbNombre;
    @FXML
    private Label lbApellido1;
    @FXML
    private Label lbApellido2;
    @FXML
    private Label lbIdCurso;
    @FXML
    private Label lbCodigoCurso;
    @FXML
    private Label lbDescripcion;
    @FXML
    private TextField tfFiltroCurso;
    @FXML
    private ComboBox<Curso> cbCodigoCurso;
    @FXML
    private TableView<Curso> tvCursosInsertados;
    @FXML
    private TableColumn<Curso, String> tcCodCurso;
    @FXML
    private TableColumn<Curso, String> tcDescripcion;
    @FXML
    private Button btInsertarCurso;
    private List<Curso> listaCursosInsertados;
    private List<Curso> lista;
    Alumno alumno;
    @FXML
    private Button btBorrarCurso;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Menú actualizar cargado.");
        listaCursosInsertados = new ArrayList<>();
        alumno = new Alumno();

        //  lista=new ArrayList<>();
        rellenarCombo();

    }

    @Override
    public void setMainApp(ACADT_ProyectoExportJDOM mainApp) {
        menuVentanas = mainApp;
    }

    @FXML
    private void updateAlumno(ActionEvent event) {

        //alumno.setId(Integer.valueOf(tfFiltro.getText()));
        alumno.setMatricula(Integer.valueOf(tfMatricula.getText()));
        alumno.setNombre(tfNombre.getText());
        alumno.setApellido1(tfApellido01.getText());
        alumno.setApellido2(tfApellido02.getText());
        alumnoDao.update(alumno);

        tfNombre.clear();
        tfMatricula.clear();
        tfApellido01.clear();
        tfApellido02.clear();
        tfCodigoCurso.clear();
        tfFiltro.clear();

        CursosAlumnoDao cursosAlumnoDao = new CursosAlumnoDao();
        CursoAlumno cursoAlumno = new CursoAlumno();
        cursoAlumno.setIdAlumno(alumno.getId());
////////////////////////        for (int i = 0; i < cursosAlumnoDao.listCursosAlumno(alumno.getId()).size(); i++) {
////////////////////////            for (int j = 0; j < listaCursosInsertados.size(); j++) {
////////////////////////                if (cursosAlumnoDao.listCursosAlumno(alumno.getId()).get(i).getId() != listaCursosInsertados.get(j).getId()) {
////////////////////////                    
////////////////////////                    if (!cursosAlumnoDao.exist(cursosAlumnoDao.get(i, alumno.getId()))) {
////////////////////////                        cursosAlumnoDao.delete(i, alumno.getId());
////////////////////////                        System.out.println("hola");
////////////////////////                    }
////////////////////////                    
////////////////////////                    
////////////////////////                    
////////////////////////                }
////////////////////////            }
////////////////////////        }

        for (int i = 0; i < listaCursosInsertados.size(); i++) {

            cursoAlumno.setIdCurso(listaCursosInsertados.get(i).getId());
            System.out.println("ESTOY ACTUALIZANDDOOOOOO");
            System.out.println(listaCursosInsertados);
            cursosAlumnoDao.add(cursoAlumno);
          //  cursosAlumnoDao.add(cursoAlumno);
        }

        //System.out.println(alumno);
    }

    @FXML
    private void updateCourse(ActionEvent event) {
        Curso curso = new Curso();
        //curso.setId(Integer.parseInt(tfFiltro.getText()));
        curso.setCodCurso(tfCodigoCurso.getText());
        curso.setDescripcion(tfDescripcionCurso.getText());
        cursoDao.update(curso);

        tfCodigoCurso.clear();
        tfDescripcionCurso.clear();
        tfFiltroCurso.clear();

        //System.out.println(curso);
    }

    @FXML
    private void back(ActionEvent event) {
        menuVentanas.cambiarContenido("/genericdao/vista/List.fxml");
    }

    @FXML
    private void findAlumno(ActionEvent event) {
        alumnoDao = new AlumnosDao();
        CursosAlumnoDao cursosAlumnoDao = new CursosAlumnoDao();

        alumno.setMatricula(Integer.valueOf(tfFiltro.getText()));
        if (alumnoDao.exist(alumno)) {
            System.out.println("EXISTE");
            //Asignamos campos de alumno para modificar
            alumno = alumnoDao.get(Integer.valueOf(tfFiltro.getText()));
            tfMatricula.setText(String.valueOf(alumno.getMatricula()));

            tfNombre.setText(alumno.getNombre());
            tfApellido01.setText(alumno.getApellido1());
            tfApellido02.setText(alumno.getApellido2());
            
            listaCursosInsertados=new ArrayList<>();

            listaCursosInsertados.addAll(cursosAlumnoDao.listCursosAlumno(alumno.getId()));
            listCursos(listaCursosInsertados);

        } else {
            System.out.println("NO EXISTE");
        }

    }

    @FXML
    private void findCourse(ActionEvent event) {
        cursoDao = new CursosDao();
        Curso cursoTemp = new Curso();
        cursoTemp.setCodCurso(tfFiltroCurso.getText());
        if (cursoDao.exist(cursoTemp)) {
            System.out.println("EXISTE");
            //Asignamos campos de curso para modificar
            cursoTemp = cursoDao.get(cursoTemp);
            tfCodigoCurso.setText(cursoTemp.getCodCurso());
            tfDescripcionCurso.setText(cursoTemp.getDescripcion());

        } else {
            System.out.println("NO EXISTE");
        }
    }

    public void rellenarCombo() {
        cursoDao = new CursosDao();
        lista = cursoDao.listAll();
        cbCodigoCurso.getItems().addAll(lista);

    }

    public void listCursos(List<Curso> listTemp) {
        tvCursosInsertados.refresh();
        tcCodCurso.setCellValueFactory(new PropertyValueFactory<>("codCurso"));
        tcDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        tvCursosInsertados.setItems(FXCollections.observableArrayList(listTemp));

    }

    @FXML
    private void insertarCursos(ActionEvent event) {

        Curso curso = cbCodigoCurso.getSelectionModel().getSelectedItem();
        listaCursosInsertados.add(curso);
        listCursos(listaCursosInsertados);

    }

    @FXML
    private void borrarCurso(ActionEvent event) {
        for (int i = 0; i < listaCursosInsertados.size(); i++) {
            if (tvCursosInsertados.getSelectionModel().getSelectedItem().getId() == listaCursosInsertados.get(i).getId()) {
                listaCursosInsertados.remove(i);
            }

        }
        listCursos(listaCursosInsertados);

    }
    


}
