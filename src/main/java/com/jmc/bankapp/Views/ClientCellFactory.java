package com.jmc.bankapp.Views;

import com.jmc.bankapp.Controllers.Admin.ClientCellController;
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
            ClientCellController controller = new ClientCellController(client);
            loader.setController(controller);
            try{
                setGraphic(loader.load());
            }
            catch (Exception e)
                {
                    e.printStackTrace();
                }

        }
    }
}
