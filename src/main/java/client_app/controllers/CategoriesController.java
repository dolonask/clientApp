package client_app.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CategoriesController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MenuItem mnItemAdd;

    @FXML
    private MenuItem mnItemEdit;

    @FXML
    private ListView<?> listCategories;

    @FXML
    void onMenuItemClicked(ActionEvent event) {

        if (event.getSource().equals(mnItemAdd)){

            Stage stage = new Stage();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/layout/edit_category.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.setTitle("Создание категории");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();

        }else if (event.getSource().equals(mnItemEdit)){
            Stage stage = new Stage();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/layout/edit_category.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.setTitle("Редактирование категории");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        }

    }

    @FXML
    void initialize() {
    }
}
