package sample;
import administrator.Administrator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import list.List;

import javafx.scene.control.TableView;


import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Controller {

    @FXML
    TableView<List> listsTable;

    @FXML
    TableColumn<List, String> nameColumn;

    @FXML
    TableColumn<List, LocalDate> dateColumn;

    @FXML
    TableColumn<List, String> pendingColumn;

    @FXML
    TableColumn<List, String> estimatedColumn;
    @FXML
    Button newListButton;
    @FXML
    Button editListButton;
    private ObservableList<List> data;
    @FXML
    public void initialize() {
        DateTimeFormatter myDateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        this.data = FXCollections.observableArrayList(Administrator.getInstance().getListBook()); //se instancia el admin y además se extrae el
        //listBook para llenar la tabla de listas

        nameColumn.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );
        dateColumn.setCellValueFactory(
                new PropertyValueFactory<>("date")
        );
        pendingColumn.setCellValueFactory(
                new PropertyValueFactory<>("pending")
        );
        estimatedColumn.setCellValueFactory(
                new PropertyValueFactory<>("estimated")
        );
        this.listsTable.setItems(data);
        System.out.println(data);
    }

    public void openNewList(ActionEvent event) {
        Parent root;
        try {

            // Cargar la nueva ventana
            FXMLLoader loader = new FXMLLoader(getClass().getResource("newList.fxml"));
            root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Nueva Lista");
            stage.setScene(new Scene(root, 600, 400));
            // Muestra la ventana
            stage.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    // FALTA LA PARTE DE EDITAR
    public void onEditListPressed(){
        List listToEdit = listsTable.getSelectionModel().getSelectedItem();
        Parent root;
        try {
            if (listToEdit != null) {
                // Cargar la nueva ventana
                FXMLLoader loader = new FXMLLoader(getClass().getResource("myList.fxml"));
                root = loader.load();
                Stage stage = new Stage();
                stage.setTitle("Lista del Super");
                stage.setScene(new Scene(root, 661, 510));

                // Manda a los Labels de myList.FXML el nombre de la lista
                MyListController myListController = loader.getController();
                myListController.setListToEdit(listToEdit.getName());
                myListController.showData();

                // Muestra la ventana
                stage.show();
                Stage currentStage = (Stage) editListButton.getScene().getWindow();
                currentStage.hide();

            } else { //muestra una ventana de alerta en caso de que no haya una lista seleccionada
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Aviso!");
                alert.setHeaderText("Por favor seleccione una lista de la tabla!");
                alert.show();
            }
        }
        catch (IOException e) {
            e.printStackTrace();

        }
    }
    public void onDeleteListPressed(){ //se instancia una lista del item seleccionado en la dataTable
        List listToDelete =  listsTable.getSelectionModel().getSelectedItem();
        if (listToDelete != null){
            Administrator.getInstance().getListBook().remove(listToDelete); //eliminación de la lista de listBook
            initialize();

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Aviso!");
            alert.setHeaderText("Por favor seleccione una lista de la tabla!");
            alert.show();
        }


    }





}
