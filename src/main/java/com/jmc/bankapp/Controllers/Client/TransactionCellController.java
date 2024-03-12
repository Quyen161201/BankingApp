package com.jmc.bankapp.Controllers.Client;

import com.jmc.bankapp.Models.Transaction;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class TransactionCellController implements Initializable {

    public FontAwesomeIconView in_icon;
    public FontAwesomeIconView out_icon;
    public Label trans_date;
    public Label trans_sender;
    public Label trans_receive;
    public Label trans_amount;

    public final Transaction transaction;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public TransactionCellController(Transaction transaction){
        this.transaction = transaction;
    }
}
