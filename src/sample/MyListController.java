package sample;

import administrator.Administrator;
import article.Article;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import list.List;

import java.io.IOException;

public class MyListController {
    private String currentListName;
    private String description;
    @FXML
    Label listNameLabel;
    @FXML
    Label listDescriptionLabel;
    @FXML
    Label pendingLabel;
    @FXML
    Button addArticleButton;
    @FXML
    TableView<Article> articlesTable;
    @FXML
    TableColumn<Article, String> articleColumn;
    @FXML
    TableColumn<Article, String> quantityColumn;
    @FXML
    TableColumn<Article, String> priceColumn;
    @FXML
    TableColumn<Article, String> totalColumn;
    @FXML
    TableColumn<Article, String> stateColumn;
    @FXML
    Button changeStateButton;
    @FXML
    Button backToSampleButton;

    private ObservableList<Article> data;
    @FXML



    public void showData(){
        listNameLabel.setText(this.currentListName);
        listDescriptionLabel.setText(this.description);
        Double pending = Administrator.getInstance().getListByName(this.currentListName).getSumOfPendings();
        pendingLabel.setText(pending.toString());

        this.data = FXCollections.observableArrayList(Administrator.getInstance().getListByName(this.currentListName).getAllArticles());
        articleColumn.setCellValueFactory(
                new  PropertyValueFactory<>("name")
        );
        quantityColumn.setCellValueFactory(
                new PropertyValueFactory<>("quantity")
        );
        priceColumn.setCellValueFactory(
                new PropertyValueFactory<>("price")
        );
        totalColumn.setCellValueFactory(
                new PropertyValueFactory<>("total")
        );
        stateColumn.setCellValueFactory(
                new PropertyValueFactory<>("state")
        );
        this.articlesTable.setItems(data);

    }
    public void setProperties(String name, String description){
        this.currentListName = name;
        this.description = description;
        Administrator administrator = Administrator.getInstance();
        administrator.addList(name, description);


    }
    public void redirectedFromArticleAdded(String name){
        this.currentListName = name;
        this.description = Administrator.getInstance().getListByName(this.currentListName).getDescription();
    }

    public void setListToEdit(String nameOfList){
        this.currentListName = nameOfList;
        this.description = Administrator.getInstance().getListByName(this.currentListName).getDescription();
    }




    public void onAddArticleClick(){
        Parent root;
        try {
            // Cargar la nueva ventana
            FXMLLoader loader = new FXMLLoader(getClass().getResource("newArticle.fxml"));
            root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Nuevo Artículo");
            stage.setScene(new Scene(root, 600, 400));
            //envía el nombre de la lista que se está usando actualmente
            NewArticleController articleController = loader.getController();
            articleController.setNameOfCurrentList(this.currentListName);

            // Muestra la ventana
            stage.show();
            //((Node)(event.getSource())).getScene().getWindow().hide();
            Stage currentStage = (Stage) addArticleButton.getScene().getWindow();
            currentStage.hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void OnClickBackToSample(){
        Parent root;
        try {
            // Cargar la nueva ventana
            FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
            root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Control de Listas de Compra");
            stage.setScene(new Scene(root, 759, 571));
            //No se necesita enviar nada ya que se vuelve a incicializar
            // Muestra la ventana
            stage.show();

            Stage currentStage = (Stage) backToSampleButton.getScene().getWindow();
            currentStage.hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void onClickToggle(){
        Article article =  articlesTable.getSelectionModel().getSelectedItem();
        if (article != null){
            Administrator.getInstance().getListByName(this.currentListName).toggleArticlebyName(article.getName());
            showData();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Aviso!");
            alert.setHeaderText("Por favor seleccione una lista de la tabla!");
            alert.show();
        }
    }

//comment
}
