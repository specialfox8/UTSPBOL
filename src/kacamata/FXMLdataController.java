/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package kacamata;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author steve
 */
public class FXMLdataController implements Initializable {

    
    @FXML
    private Button keluar;
    @FXML
    private TableView<kacamodel> tbvkaca;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    showdata();
    }
        private void showdata(){
            ObservableList<kacamodel> data=FXMLDocumentController.dtkaca.Load();
        if(data!=null){            
            tbvkaca.getColumns().clear();
            tbvkaca.getItems().clear();
            TableColumn col=new TableColumn("idkaca");
            col.setCellValueFactory(new PropertyValueFactory<kacamodel, String>("idkaca"));
            tbvkaca.getColumns().addAll(col);
            col=new TableColumn("tipekaca");
            col.setCellValueFactory(new PropertyValueFactory<kacamodel, String>("tipekaca"));
            tbvkaca.getColumns().addAll(col);
          col=new TableColumn("harga");
           col.setCellValueFactory(new PropertyValueFactory<kacamodel, String>("harga"));
            tbvkaca.getColumns().addAll(col);
            tbvkaca.setItems(data);
        }else {
            Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tbvkaca.getScene().getWindow().hide();
        }
     }
    @FXML
    private void keluarklik(ActionEvent event) {
    keluar.getScene().getWindow().hide();
    }

 } 

