package sample;

import administrator.Administrator;
import article.Article;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class NewArticleController {
    String nameOfCurrentList;
    @FXML
    TextField nameTextField;
    @FXML
    TextField quantityTextField;
    @FXML
    TextField priceTextField;
    @FXML
    Button CreateArticleButton;
    @FXML
    Button backToMyListButton;

    public Boolean noneEmpty(){ //verifica si no falta ningún campo
        if (this.nameTextField.getText().equals("") || this.nameTextField == null || this.quantityTextField.getText().equals("") || this.quantityTextField == null
                || this.priceTextField.getText().equals("") || this.priceTextField == null){
            return false;
        }

        return true;
    }

    public void setNameOfCurrentList(String name){
        //cuando es llamado desde +createNewArticle
        this.nameOfCurrentList = name;
    }

    public void onClickCreateArticle(){
        if (noneEmpty()){
            Integer qty = Integer.parseInt(quantityTextField.getText());
            Double price = Double.parseDouble(priceTextField.getText());
            Article myArticle = new Article(nameTextField.getText(), qty, price);
            Administrator.getInstance().addArticle(this.nameOfCurrentList,myArticle);
            Parent root;
            try {

                // Cargar la nueva ventana
                FXMLLoader loader = new FXMLLoader(getClass().getResource("myList.fxml"));
                root = loader.load();
                Stage stage = new Stage();
                stage.setTitle("Lista del Super");
                stage.setScene(new Scene(root, 661, 510));
                //envía el nombre de la lista que se está usando actualmente, de regreso para actualización
                MyListController myListController = loader.getController();
                myListController.redirectedFromArticleAdded(this.nameOfCurrentList);
                myListController.showData();

                // Muestra la ventana
                stage.show();
                //((Node)(event.getSource())).getScene().getWindow().hide();
                Stage currentStage = (Stage) CreateArticleButton.getScene().getWindow();
                currentStage.hide();
            }
            catch (IOException e) {
                e.printStackTrace();
            }



        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Aviso!");
            alert.setHeaderText("Ingreso de datos Inválido \nPor favor ingrese todos los campos correctamente");
            alert.show();
        }
    }
    public void onBackToMyList(){
        Parent root;
        try {
            // Cargar la nueva ventana
            FXMLLoader loader = new FXMLLoader(getClass().getResource("myList.fxml"));
            root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Lista del Super");
            stage.setScene(new Scene(root, 661, 510));
            //envía el nombre de la lista que se está usando actualmente, de regreso para actualización
            MyListController myListController = loader.getController();
            myListController.redirectedFromArticleAdded(this.nameOfCurrentList);
            myListController.showData();
            // Muestra la ventana
            stage.show();
            //((Node)(event.getSource())).getScene().getWindow().hide();
            Stage currentStage = (Stage) backToMyListButton.getScene().getWindow();
            currentStage.hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }


}
