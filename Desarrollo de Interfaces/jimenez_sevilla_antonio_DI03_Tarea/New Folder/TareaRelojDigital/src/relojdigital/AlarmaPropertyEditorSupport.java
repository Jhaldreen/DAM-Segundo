/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relojdigital;

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
    
    public String getJavaJInitializationString(){
        Date horaAlarma = alarmaPanel.getSelectiveValue().getHoraAlarma();
        boolean activa = alarmaPanel.getSelectiveValue().isActiva();
        return "new relojdigital.Alarma(new java.util.Date("+horaAlarma.getTime()+"l),"+activa+")";
        
    }
    @Override
    public Object getValue(){
        return alarmaPanel.getSelectiveValue();
    }

    private static class AlarmaPanel {

        public AlarmaPanel() {
        }

        private Object getSelectedValue() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
}
