package com.jmc.bankapp.Views;

import com.jmc.bankapp.Controllers.Client.TransactionCellController;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import com.jmc.bankapp.Models.Transaction;

public class TransactionCell extends ListCell<Transaction> {
    @Override
    protected void updateItem(Transaction transaction, boolean empty) {
        super.updateItem(transaction, empty);
        if(empty)
        {
            setText(null);
            setGraphic(null);
        }
        else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Client/TransactionCell.fxml"));
            TransactionCellController controller = new TransactionCellController(transaction);
            loader.setController(controller);
            setText(null);
            try {
                setGraphic(loader.load());
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
