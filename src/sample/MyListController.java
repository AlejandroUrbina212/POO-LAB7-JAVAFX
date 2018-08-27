package sample;

import administrator.Administrator;
import article.Article;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;

public class MyListController {
    private String name;
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


    public void showData(){
        listNameLabel.setText(this.name);
        listDescriptionLabel.setText(this.description);
        Double pending = Administrator.getInstance().getListByName(this.name).getSumOfPendings();
        pendingLabel.setText(pending.toString());

        this.data = FXCollections.observableArrayList(Administrator.getInstance().getListByName(this.name).getAllArticles());
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
        this.name = name;
        this.description = description;
        Administrator administrator = Administrator.getInstance();
        administrator.addList(name, description);


    }
    public void redirectedFromArticleAdded(String name){
        this.name = name;
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
            articleController.setNameOfCurrentList(this.name);

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


}
