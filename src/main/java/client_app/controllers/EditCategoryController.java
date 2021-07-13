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

    private Category category;

    private ObjectMapper objectMapper = new ObjectMapper();

    public void init(Category category){
        this.category = category;

        if (category.getId() != null){

            OkHttpClient okHttpClient = new OkHttpClient();

//            HttpUrl.Builder builder = HttpUrl.parse("http://localhost:8080/api/v1/category/").newBuilder();
//            builder.addQueryParameter("id", String.valueOf(category.getId()));

            Request request = new Request.Builder()
                    .url("http://localhost:8080/api/v1/category/"+category.getId())
                    .build();

            Call call = okHttpClient.newCall(request);

            try {
                Response response = call.execute();
                if (response.code() == 200) {
                    category = objectMapper.readValue(response.body().string(), Category.class);
                }else{
                    System.out.println(response.code());
                    System.out.println(response.toString());
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            txtName.setText(category.getName());
            checkActive.setSelected(category.isActive());


        }else{
            checkActive.setSelected(true);
        }
    }

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

            category.setName(txtName.getText());
            category.setActive(checkActive.isSelected());

            OkHttpClient client = new OkHttpClient();

            RequestBody body = RequestBody.create(objectMapper.writeValueAsBytes(category),MediaType.parse("application/json; charset=utf-8"));
            Request request;

            if (category.getId() == null) {
                request = new Request.Builder()
                        .url("http://localhost:8080/api/v1/category/save")
                        .post(body)
                        .build();
            }else{
                request = new Request.Builder()
                        .url("http://localhost:8080/api/v1/category/update")
                        .put(body)
                        .build();
            }

            Call call = client.newCall(request);
            try {
                Response response = call.execute();
                System.out.println(response);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }else if (event.getSource().equals(btnCancel)){
            btnCancel.getScene().getWindow().hide();
        }

    }

}
