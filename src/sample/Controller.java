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

    private ObservableList<List> data;
    @FXML
    public void initialize() {
        DateTimeFormatter myDateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        this.data = FXCollections.observableArrayList(Administrator.getInstance().getListBook());

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






}
