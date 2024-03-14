package com.jmc.bankapp.Controllers.Admin;

import com.jmc.bankapp.Models.Client;
import com.jmc.bankapp.Models.Model;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;
import javafx.scene.layout.AnchorPane;


import java.net.URL;
import java.util.ResourceBundle;

public class ClientCellController implements Initializable {
    public Label fld_name;
    public Label lbl_ch_acc;
    public Label lbl_sav_acc;
    public Label lbl_date_create;
    public Button btn_delete;

    public Client client;

    public ListView<Client> listView;
    public ObservableList<Client> clientObservableList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fld_name.setText(client.nameProperty().get());
        lbl_ch_acc.setText(String.valueOf(client.CheckingAccountProperty().get().account_numberProperty().getValue()));
        lbl_sav_acc.setText(String.valueOf(client.SavingAccountProperty().get().account_numberProperty().getValue()));
        lbl_date_create.setText(String.valueOf(client.phoneProperty().get()));

        btn_delete.setOnAction(event -> onDelete());
    }

    public ClientCellController(Client client)
    {
        this.client = client;

    }

    private void onDelete(){
        boolean result = showConfirmDialog("Bạn có muốn tiếp tục không?");
        if(result){
            int rs = Model.getInstance().deleteClient(client.nameProperty().get());
            if(rs == 1){
                showAlert("Xóa thành cônng", Alert.AlertType.INFORMATION);

            }
        }


    }

    public boolean showConfirmDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Xác nhận");
        alert.setHeaderText(null);
        alert.setContentText(message);

        ButtonType buttonYes = new ButtonType("Yes");
        ButtonType buttonNo = new ButtonType("No");
        alert.getButtonTypes().setAll(buttonYes, buttonNo);

        alert.showAndWait();

        return alert.getResult() == buttonYes;
    }

    public static void showAlert(String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


}
