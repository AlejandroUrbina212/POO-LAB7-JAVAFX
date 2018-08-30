package sample;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;



import java.io.IOException;

public class NewListController {
    @FXML
    TextField nameTextField;

    @FXML
    TextField descriptionTextField;
    @FXML
    Button createButton;
    @FXML
    Button backToSampleButton;


    private boolean verifyTextFields(){ //verifica si los campos y si no están llenos muestra un mensaje.
        return !this.nameTextField.getText().equals("") && this.nameTextField != null && !this.descriptionTextField.getText().equals("") && this.descriptionTextField != null;
    }
    public void onClickAdd(ActionEvent event){
        Parent root;
        try {
            if (verifyTextFields()) {
                // Cargar la nueva ventana
                FXMLLoader loader = new FXMLLoader(getClass().getResource("myList.fxml"));
                root = loader.load();
                Stage stage = new Stage();
                stage.setTitle("Lista del Super");
                stage.setScene(new Scene(root, 661, 510));

                // Manda a los Labels el nombre y la descripción de la lista myList.Controller
                MyListController myListController = loader.getController();
                myListController.setProperties(nameTextField.getText(), descriptionTextField.getText());
                myListController.showData();

                // Muestra la ventana
                stage.show();
                Stage currentStage = (Stage) createButton.getScene().getWindow();
                currentStage.close();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Aviso!");
                alert.setHeaderText("Ingreso de datos Inválido \nPor favor ingresa una descripción para la lista");
                alert.show();
            }
        }
        catch (IOException e) {
            e.printStackTrace();

        }
    }
    public void OnClickBackToSample(ActionEvent event){
        Parent root;
        try {
            // Cargar la nueva ventana
            FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
            root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Control de Listas de Compras");
            stage.setScene(new Scene(root, 759, 571));
            //No se necesita enviar nada ya que se vuelve a incicializar
            // Muestra la ventana
            stage.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
