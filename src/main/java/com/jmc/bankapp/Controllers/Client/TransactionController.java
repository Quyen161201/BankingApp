package com.jmc.bankapp.Controllers.Client;

import com.jmc.bankapp.Models.Client;
import com.jmc.bankapp.Models.Model;
import com.jmc.bankapp.Models.Transaction;
import com.jmc.bankapp.Views.ClientCellFactory;
import com.jmc.bankapp.Views.TransactionCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class TransactionController implements Initializable {

    public ListView<Transaction> transaction_listview;

    public ObservableList<Transaction> transactionObservableList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        transaction_listview.setItems(transactionObservableList);
        transaction_listview.setCellFactory(listClient -> new TransactionCell());
    }

    public TransactionController()
    {

        transactionObservableList = FXCollections.observableArrayList();
        Model.getInstance().transactionList(LoginSession.getPhoneNumber(),transactionObservableList);
    }
}
