
package RelojDigital;

import java.awt.Component;
import java.beans.*;
import java.util.Date;

/**
 *
 * @author Antonio
 */


public class AlarmaPropertyEditorSupport extends PropertyEditorSupport {
    
    
    private PanelAlarma alarmaPanel = new PanelAlarma();
    
    @Override
    public boolean supportsCustomEditor(){
        return true;
    }

    @Override
    public Component getCustomEditor() {
        return this.alarmaPanel; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    public String getJavaJInitializationString(){
        Date horaAlarma = alarmaPanel.getSelectiveValue().getHoraAlarma();
        boolean activa = alarmaPanel.getSelectiveValue().isActiva();
        return "new RelojDigital.Alarma(new java.util.Date("+horaAlarma.getTime()+"l),"+activa+")";
        
    }
    @Override
    public Object getValue(){
        return alarmaPanel.getSelectiveValue();
    }


}
        