/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genericdao.modelo.exportacionJDOM;

import genericdao.modelo.dao.AlumnosDao;
import genericdao.modelo.entities.Alumno;
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

/**
 *
 * @author Baltasar Rangel Pinilla  <mga-py>----<baltasarrangel93@gmail.com">
 */
public class JDOM {
//    SAXBuilder builder =new SAXBuilder();
//    Document doc = builder.build(new File("fichero.xml"));
//    
//    Element root =doc.getRootChildren();
//    
//    List<Element> allChildren =root.getChildren("noticia")
//    
//    
//    

    public void importarXml() {
        //Se crea un SAXBuilder para poder parsear el archivo
        SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File("academia.xml");
        try {
            //Se crea el documento a traves del archivo
            Document document = (Document) builder.build(xmlFile);

            //Se obtiene la raiz 'tables'
            Element rootNode = document.getRootElement();

            //Se obtiene la lista de hijos de la raiz 'tables'
            List list = rootNode.getChildren("tabla");

            //Se recorre la lista de hijos de 'tables'
            for (int i = 0; i < list.size(); i++) {
                //Se obtiene el elemento 'tabla'
                Element tabla = (Element) list.get(i);

                //Se obtiene el atributo 'nombre' que esta en el tag 'tabla'
                String nombreTabla = tabla.getAttributeValue("nombre");

                System.out.println("Tabla: " + nombreTabla);

                //Se obtiene la lista de hijos del tag 'tabla'
                List lista_campos = tabla.getChildren();

                System.out.println("\tNombre\t\tTipo\t\tValor");

                //Se recorre la lista de campos
                for (int j = 0; j < lista_campos.size(); j++) {
                    //Se obtiene el elemento 'campo'
                    Element campo = (Element) lista_campos.get(j);

                    //Se obtienen los valores que estan entre los tags '<campo></campo>'
                    //Se obtiene el valor que esta entre los tags '<nombre></nombre>'
                    Integer id = Integer.parseInt(campo.getChildTextTrim("id"));
                    Integer matricula = Integer.parseInt(campo.getChildTextTrim("matricula"));
                    String nombre = campo.getChildTextTrim("nombre");
                    String apellido1 = campo.getChildTextTrim("apellido1");
                    String apellido2 = campo.getChildTextTrim("apellido2");

                    //System.out.println("\t" + id + "\t\t" + matricula + "\t\t" + nombre+ "\t\t" + apellido1+ "\t\t" + apellido2);
                    System.out.println(id);
                    System.out.println(matricula);
                    System.out.println(nombre);
                    System.out.println(apellido1);
                    System.out.println(apellido2);
                }
            }
        } catch (IOException io) {
            System.out.println(io.getMessage());
        } catch (JDOMException jdomex) {
            System.out.println(jdomex.getMessage());
        }
    }

    public void exportarXml() {
        AlumnosDao alumnosDao = new AlumnosDao();
        List<Alumno> listaAlumnos=alumnosDao.listAll();
        System.out.println(listaAlumnos);
        

        try {

            Element academia = new Element("academia");
            Document doc = new Document(academia);

            //doc.setRootElement(academia);
            Element alumnos = new Element("Alumnos");
            
            for (int i = 0; i < listaAlumnos.size(); i++) {
                Element alumno = new Element("Alumno");
            Attribute idalumno = new Attribute("id", String.valueOf(listaAlumnos.get(i).getId()));
            alumnos.addContent(alumno);
            alumno.setAttribute(idalumno);
            alumno.addContent(new Element("matricula").setText(String.valueOf(listaAlumnos.get(i).getMatricula())));
            alumno.addContent(new Element("nombre").setText(listaAlumnos.get(i).getNombre()));
            alumno.addContent(new Element("apellido1").setText(listaAlumnos.get(i).getApellido1()));
            alumno.addContent(new Element("apellido2").setText(listaAlumnos.get(i).getApellido2()));
            alumno.addContent(new Element("listacursos").setText(String.valueOf(listaAlumnos.get(i).getCursos())));

            
            }
            //Esta es la parte donde se añade(sacarlo del bucle)
            doc.getRootElement().addContent(alumnos);

            Element cursos = new Element("cursos");
            Element curso = new Element("curso");
            Attribute idCurso = new Attribute("id", "i");
            cursos.addContent(curso);
            curso.setAttribute(idCurso);
            curso.addContent(new Element("codCurso").setText("333"));
            curso.addContent(new Element("descripcion").setText("mates"));
            curso.addContent(new Element("lista").setText("ninguna"));

            doc.getRootElement().addContent(cursos);

            // new XMLOutputter().output(doc, System.out);
            XMLOutputter xmlOutput = new XMLOutputter();

            // display nice nice
            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(doc, new FileWriter("academiaExportado.xml"));

            System.out.println("Archivo Exportado!");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
