/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programahotel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;


/**
 *
 * @author Antonio
 */
public class FXMLDocumentController implements Initializable {
    
   
    @FXML
    private ComboBox comboEvento;
    @FXML
    private Button botonSalir;
    
        public void handlebuttonSalir(ActionEvent event) throws IOException{   
      Platform.exit();
     System.exit(0);
   }
    
 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboEvento.getItems().addAll("Congreso","Jornada","Evento");
    }    
    
}
