package com.jmc.bankapp.Controllers.Admin;

import com.jmc.bankapp.Models.Client;
import com.jmc.bankapp.Models.Model;
import com.jmc.bankapp.Views.ClientCellFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class DepositController implements Initializable {
    public TextField search_field;
    public Button search_btn;
    public TextField deposit_field;
    public Button deposit_btn=null;

    Client client;
    public ListView<Client> view_client;
    public ObservableList<Client> clientObservable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        search_btn.setOnAction(event -> searchClient());

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




}
