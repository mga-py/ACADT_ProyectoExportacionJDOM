package genericdao.modelo.exportacionJDOM;

import genericdao.modelo.dao.AlumnosDao;
import genericdao.modelo.dao.CursosAlumnoDao;
import genericdao.modelo.dao.CursosDao;
import genericdao.modelo.entities.Alumno;
import genericdao.modelo.entities.Curso;
import genericdao.modelo.entities.CursoAlumno;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class JDOM {

    public List<ArrayList> importarXml(String path) {
        List<ArrayList> listaArrays = new ArrayList<>();

        //Creamos las listas en donde vamos a importar los datos
        //para pasarselas al metodo que las pida
        ArrayList<Curso> ListaCompletaCursos = new ArrayList();
        ArrayList<Alumno> listaCompletaAlumnos = new ArrayList();
        ArrayList<CursoAlumno> ListaCompletaCursosAlumnos = new ArrayList();

        //Se crea un SAXBuilder para poder parsear el archivo
        SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File(path);
        try {
            //Se crea el documento a traves del archivo
            Document document = (Document) builder.build(xmlFile);

            //---------------------ALUMNOS-------------------
            //Se obtiene la raiz 'academia'
            Element rootNode = document.getRootElement();

            //Se obtiene la lista de hijos de la raiz 'academia'
            List listaAlumnos = rootNode.getChildren("Alumnos");

            //Se recorre la lista de hijos de 'Alumnos'
            for (int i = 0; i < listaAlumnos.size(); i++) {
                //Se obtiene el elemento 'alumnos'
                Element alumnos = (Element) listaAlumnos.get(i);

                //Se obtiene la lista de hijos del tag 'alumnos'
                List lista_campos = alumnos.getChildren();

                //Se recorre la lista de campos
                for (int j = 0; j < lista_campos.size(); j++) {
                    //Se obtiene el elemento 'alumno'
                    Element campo = (Element) lista_campos.get(j);

                    //Se obtienen los valores que estan entre los tags '<alumno></alumno>'
                    //Se obtiene el valor que esta entre los tags '<nombre></nombre>'
                    Integer id = Integer.parseInt(campo.getAttributeValue("id"));
                    Integer matricula = Integer.parseInt(campo.getChildTextTrim("matricula"));
                    String nombre = campo.getChildTextTrim("nombre");
                    String apellido1 = campo.getChildTextTrim("apellido1");
                    String apellido2 = campo.getChildTextTrim("apellido2");

                    Alumno alumnoTemp = new Alumno();
                    alumnoTemp.setId(id);
                    alumnoTemp.setMatricula(matricula);
                    alumnoTemp.setNombre(nombre);
                    alumnoTemp.setApellido1(apellido1);
                    alumnoTemp.setApellido2(apellido2);

                    listaCompletaAlumnos.add(alumnoTemp);
                }

            }
            //------------------Cursos------------------

            //Se obtiene la raiz 'academia'
            //Se obtiene la lista de hijos de la raiz 'academia'
            List listaCursos = rootNode.getChildren("Cursos");

            //Se recorre la lista de hijos de 'Cursos'
            for (int i = 0; i < listaCursos.size(); i++) {
                //Se obtiene el elemento 'Cursos'
                Element cursos = (Element) listaCursos.get(i);

                //Se obtiene la lista de hijos del tag 'Cursos'
                List lista_camposCursos = cursos.getChildren();

                //Se recorre la lista de cursos
                for (int j = 0; j < lista_camposCursos.size(); j++) {

                    //Se obtiene el elemento 'Curso'
                    Element campo = (Element) lista_camposCursos.get(j);

                    //Se obtienen los valores que estan entre los tags '<curso></curso>'
                    //Se obtiene el valor que esta entre los tags '<descripcion></descripcion>'
                    Integer id = Integer.parseInt(campo.getAttributeValue("id"));
                    String codCurso = campo.getChildTextTrim("codCurso");
                    String descripcion = campo.getChildTextTrim("descripcion");

                    Curso cursoTemp = new Curso();
                    cursoTemp.setId(id);
                    cursoTemp.setCodCurso(codCurso);
                    cursoTemp.setDescripcion(descripcion);

                    ListaCompletaCursos.add(cursoTemp);

                }

            }

            //------------------CursosAlumnos------------------
            //Se obtiene la raiz 'academia'
            //Se obtiene la lista de hijos de la raiz 'academia'
            List listaCursosAlumnos = rootNode.getChildren("CursosAlumnos");

            //Se recorre la lista de hijos de 'CursosAlumnos'
            for (int i = 0; i < listaCursosAlumnos.size(); i++) {
                //Se obtiene el elemento 'cursoAlumno'
                Element cursoAlumno = (Element) listaCursosAlumnos.get(i);

                //Se obtiene la lista de hijos del tag 'CursosAlumnos'
                List lista_camposCursosAlumnos = cursoAlumno.getChildren();

                //Se recorre la lista de campos
                for (int j = 0; j < lista_camposCursosAlumnos.size(); j++) {

                    //Se obtiene el elemento 'cursoAlumno'
                    Element campo = (Element) lista_camposCursosAlumnos.get(j);

                    //Se obtienen los valores que estan entre los tags '<cursoAlumno></cursoAlumno>'
                    //Se obtiene el valor que esta entre los tags '<idCurso></idCurso>'
                    String idCurso = campo.getChildTextTrim("idCurso");
                    String idAlumno = campo.getChildTextTrim("idAlumno");

                    CursoAlumno cursoAlumnoTemp = new CursoAlumno();
                    cursoAlumnoTemp.setIdAlumno(Integer.parseInt(idAlumno));
                    cursoAlumnoTemp.setIdCurso(Integer.parseInt(idCurso));
                    ListaCompletaCursosAlumnos.add(cursoAlumnoTemp);

                }

            }

        } catch (IOException io) {
            System.out.println(io.getMessage());
        } catch (JDOMException jdomex) {
            System.out.println(jdomex.getMessage());
        }

        listaArrays.add(ListaCompletaCursos);//get(0)
        listaArrays.add(ListaCompletaCursosAlumnos);//get(1)
        listaArrays.add(listaCompletaAlumnos);//get(2)

        return listaArrays;
    }

    public void exportarXml(String path) {
        AlumnosDao alumnosDao = new AlumnosDao();
        List<Alumno> listaAlumnos = alumnosDao.listAll();
        CursosDao cursosDao = new CursosDao();
        List<Curso> listaCursos = cursosDao.listAll();
        CursosAlumnoDao cursosAlumnosDao = new CursosAlumnoDao();
        List<CursoAlumno> listaCursosAlumnos = cursosAlumnosDao.listAll();

        try {

            //Creamos el elemento 'academia'
            Element academia = new Element("academia");
            Document doc = new Document(academia);

            //doc.setRootElement(academia);
//---------------------------ALUMNOS--------------------------
            //Creamos el elemento Alumnos
            Element alumnos = new Element("Alumnos");

            //Recorremos la lista de alumnos y recogemos su atributo
            for (int i = 0; i < listaAlumnos.size(); i++) {
                //Creamos el elemento alumno
                Element alumno = new Element("Alumno");
                //Creamos el atributo id para recoger la id del alumno
                Attribute idalumno = new Attribute("id", String.valueOf(listaAlumnos.get(i).getId()));
                //añadimos a la "Lista" de alumnos el contenido de cada alumno
                alumnos.addContent(alumno);
                //Establecemos el atributo a cada alumno, que se corresponderia con la id
                alumno.setAttribute(idalumno);
                //Añadimos los contenidos...
                alumno.addContent(new Element("matricula").setText(String.valueOf(listaAlumnos.get(i).getMatricula())));
                alumno.addContent(new Element("nombre").setText(listaAlumnos.get(i).getNombre()));
                alumno.addContent(new Element("apellido1").setText(listaAlumnos.get(i).getApellido1()));
                alumno.addContent(new Element("apellido2").setText(listaAlumnos.get(i).getApellido2()));

            }
            //Esta es la parte donde se añade(sacarlo del bucle)
            doc.getRootElement().addContent(alumnos);

//---------------------------CURSOS--------------------------
            Element cursos = new Element("Cursos");

            for (int i = 0; i < listaCursos.size(); i++) {
                Element curso = new Element("Curso");
                Attribute idCurso = new Attribute("id", String.valueOf(listaCursos.get(i).getId()));
                cursos.addContent(curso);
                curso.setAttribute(idCurso);
                curso.addContent(new Element("codCurso").setText(String.valueOf(listaCursos.get(i).getCodCurso())));
                curso.addContent(new Element("descripcion").setText(listaCursos.get(i).getDescripcion()));

            }

            //Esta es la parte donde se añade(sacarlo del bucle)
            doc.getRootElement().addContent(cursos);

//---------------------------CURSOSALUMNOS--------------------------
            Element cursosAlumnos = new Element("CursosAlumnos");

            for (int i = 0; i < listaCursosAlumnos.size(); i++) {
                Element cursoAlumno = new Element("cursoAlumno");
                cursosAlumnos.addContent(cursoAlumno);
                cursoAlumno.addContent(new Element("idCurso").setText(String.valueOf(listaCursosAlumnos.get(i).getIdCurso())));
                cursoAlumno.addContent(new Element("idAlumno").setText(String.valueOf(listaCursosAlumnos.get(i).getIdAlumno())));

            }

            //Esta es la parte donde se añade(sacarlo del bucle)
            doc.getRootElement().addContent(cursosAlumnos);

            XMLOutputter xmlOutput = new XMLOutputter();

            // Establece el formato 
            xmlOutput.setFormat(Format.getPrettyFormat());
            //Elige el documento en el que va a guardar la informacion
            xmlOutput.output(doc, new FileWriter(path));

            System.out.println("Archivo Exportado!");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
