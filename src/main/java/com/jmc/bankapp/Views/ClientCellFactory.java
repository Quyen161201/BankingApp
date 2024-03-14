package com.jmc.bankapp.Views;

import com.jmc.bankapp.Controllers.Admin.ClientCellController;
import com.jmc.bankapp.Controllers.Admin.DepositController;
import com.jmc.bankapp.Models.Client;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

public class ClientCellFactory extends ListCell<Client> {
    @Override
    protected void updateItem(Client client, boolean empty) {
        super.updateItem(client,empty);
        if(empty)
        {
            setText(null);
            setGraphic(null);
        }
        else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Admin/ClientCell.fxml"));
            FXMLLoader loaderSearch = new FXMLLoader(getClass().getResource("/FXML/Admin/ClientCell.fxml"));
            ClientCellController controller = new ClientCellController(client);
            DepositController depositController = new DepositController(client);
            loaderSearch.setController(depositController);
            loader.setController(controller);
            try{
                setGraphic(loader.load());
                setGraphic(loaderSearch.load());
            }
            catch (Exception e)
                {
                    e.printStackTrace();
                }

        }
    }
}
