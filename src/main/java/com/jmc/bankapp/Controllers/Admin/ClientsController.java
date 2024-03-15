package com.jmc.bankapp.Controllers.Admin;

import com.jmc.bankapp.Models.Client;
import com.jmc.bankapp.Models.Model;
import com.jmc.bankapp.Views.ClientCellFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ClientsController implements Initializable {
    public ListView<Client> clients_listview;
    public AnchorPane listView_client;

    private Client client;
    public ObservableList<Client> clientObservableList;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        clients_listview.setItems(clientObservableList);
        clients_listview.setCellFactory(listClient -> new ClientCellFactory());


    }

    public ClientsController()
    {
        clientObservableList = FXCollections.observableArrayList();
        Model.getInstance().listClient(clientObservableList);


    }
}
