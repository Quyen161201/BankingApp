package com.jmc.bankapp.Controllers.Admin;

import com.jmc.bankapp.Models.Client;
import com.jmc.bankapp.Models.Model;
import com.jmc.bankapp.Views.ClientCellFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

import static com.jmc.bankapp.Controllers.Admin.ClientCellController.showAlert;
import static com.jmc.bankapp.Controllers.Admin.ClientCellController.showConfirmDialog;

public class DepositController implements Initializable {
    public TextField search_field;
    public Button search_btn;
    public TextField deposit_field;
    public Button deposit_btn;

    Client client;
    public ListView<Client> view_client;
    public ObservableList<Client> clientObservable;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        search_btn.setOnAction(event -> searchClient());
        deposit_btn.setOnAction(event -> setDepositAccount());

    }
    public DepositController() {
        // Constructor mặc định
    }
    public DepositController(Client client)
    {
        this.client = client;

    }

    public void searchClient()
    {

        clientObservable = FXCollections.observableArrayList();
        Model.getInstance().searchCLient(search_field.getText(),clientObservable);
        view_client.setItems(clientObservable);
        view_client.setCellFactory(listClient -> new ClientCellFactory());

    }

    public void setDepositAccount()
    {
        boolean result = showConfirmDialog("Bạn có muốn tiếp tục không?");
        if(result){
           int rs= Model.getInstance().depositAccount(Double.parseDouble(deposit_field.getText()),search_field.getText());
            if(rs == 1){
                showAlert("Chuyển tiền thành cônng", Alert.AlertType.INFORMATION);
                deposit_field=null;
            }
            else {
                showAlert("chuyển tiền thất bại", Alert.AlertType.ERROR);
            }
        }

    }




}
