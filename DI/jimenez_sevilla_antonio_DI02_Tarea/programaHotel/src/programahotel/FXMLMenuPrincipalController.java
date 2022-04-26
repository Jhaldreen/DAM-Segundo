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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Antonio
 */
public class FXMLMenuPrincipalController implements Initializable {

    @FXML
    private Button btnSalir;
    @FXML
    private Button btnReservas;

    public void handlebtnReservas(ActionEvent event) throws IOException{
     Stage stage =(Stage) btnReservas.getScene().getWindow(); 
     Parent root = FXMLLoader.load(getClass().getResource("FXMLMenuPrincipal.fxml"));
     if(event.getSource()==btnReservas){        
       stage=(Stage) btnReservas.getScene().getWindow();       
       root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
     }
      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
      }
     public void handlebtnSalir(ActionEvent event) throws IOException{   
      Platform.exit();
     System.exit(0);
   }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

}
