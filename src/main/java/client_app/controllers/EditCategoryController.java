package client_app.controllers;

import client_app.models.Category;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import okhttp3.*;

import java.io.IOException;

public class EditCategoryController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @FXML
    private TextField txtName;

    @FXML
    private CheckBox checkActive;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnCancel;

    @FXML
    void onButtonClicked(ActionEvent event) throws JsonProcessingException {

        if (event.getSource().equals(btnSave)){

            Category category = new Category();
            category.setName(txtName.getText());
            category.setActive(checkActive.isSelected());

            OkHttpClient client = new OkHttpClient();

            RequestBody body = RequestBody.create(objectMapper.writeValueAsBytes(category),MediaType.parse("application/json; charset=utf-8"));

            Request request = new Request.Builder()
                    .url("http://localhost:8080/api/v1/category/save")
                    .post(body)
                    .build();

            Call call = client.newCall(request);
            try {
                Response response = call.execute();
                System.out.println(response);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

    }

}
