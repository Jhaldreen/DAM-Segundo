
import Alumno.MatriculasBean;
import Alumno.MatriculasBean.BDModificadaEvent;
import Alumno.MatriculasBean.BDModificadaListener;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Antonio
 */
public class AccedeBD implements BDModificadaListener {

    MatriculasBean matricula;

    AccedeBD() {
        matricula = new MatriculasBean();

        matricula.addBDModificadaListener((BDModificadaListener) this);
    }

    public void listado() {

        for (int i = 0; i < 4; i++) {
            matricula.seleccionarFila(i);
            System.out.println("Alumno: " + i);
            System.out.println("\tDNI: " + matricula.getDNI());
            System.out.println("\tNombreModulo: " + matricula.getNombreModulo());
            System.out.println("\tCurso: " + matricula.getCurso());
            System.out.println("\tNota: " + matricula.getNota());

        }
    }

    void anade()//añadimos un nuevo regitro,OJO NO REPETIR DNI
    {
        matricula.setDNI("66859599A");
        matricula.setNombreModulo("DAM");
        matricula.setCurso("21-22");
        matricula.setNota(5);

        try {
            matricula.addMatricula();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccedeBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void capturarBDModificada(BDModificadaEvent ev) {
        System.out.println("Se ha añadido un elemento a la base de datos");
    }
}
