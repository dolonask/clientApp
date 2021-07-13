package client_app.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import client_app.models.Category;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;
import okhttp3.*;

public class CategoriesController {


    @FXML
    private MenuItem mnItemAdd;

    @FXML
    private MenuItem mnItemEdit;

    @FXML
    private ListView<Category> listCategories;

    private ObjectMapper objectMapper = new ObjectMapper();

    @FXML
    void onMenuItemClicked(ActionEvent event) {

        if (event.getSource().equals(mnItemAdd)){

            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/layout/edit_category.fxml"));
            try {
                Parent parent = loader.load();
                stage.setTitle("Создание категории");
                stage.setScene(new Scene(parent));
                stage.setResizable(false);
                stage.initModality(Modality.APPLICATION_MODAL);

                EditCategoryController editCategoryController = loader.getController();
                editCategoryController.init(new Category());

                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }else if (event.getSource().equals(mnItemEdit)){
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/layout/edit_category.fxml"));
            try {

                Parent parent = loader.load();
                stage.setTitle("Редактирование категории");
                stage.setScene(new Scene(parent));
                stage.setResizable(false);
                stage.initModality(Modality.APPLICATION_MODAL);

                EditCategoryController editCategoryController = loader.getController();
                editCategoryController.init(listCategories.getSelectionModel().getSelectedItem());

                stage.show();


            } catch (IOException e) {
                e.printStackTrace();
            }


            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/layout/edit_category.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }




        }

    }

    @FXML
    void initialize() {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://localhost:8080/api/v1/category/all")
                .build();

        Call call = client.newCall(request);

        try {
            Response response = call.execute();
            List<Category> categories = objectMapper.readValue(response.body().string(), new TypeReference<List<Category>>(){});

            ObservableList<Category> observableList = FXCollections.observableList(categories);

            listCategories.setItems(observableList);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
