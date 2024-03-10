package com.jmc.bankapp.Views;

import com.jmc.bankapp.Controllers.Client.ClientController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewFactory {
    private AnchorPane dashboardView;

    public  ViewFactory (){}

    public AnchorPane getDashboardView() {
        if(dashboardView == null)
        {
            try {
                dashboardView = new FXMLLoader((getClass().getResource("/Fxml/Clinet/Dashboard.fxm"))).load();

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

        }
        return dashboardView;
    }

    public void showLoginWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/login.fxml"));
        Scene scene = null;
        try
        {
            scene = new Scene(loader.load());

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Hà Nội Bank");
        stage.show();

    }
    public  void showClientWindow()
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Client/Client.fxml"));
        ClientController clientController = new ClientController();
        loader.setController(clientController);
        Scene scene = null;
        try
        {
            scene = new Scene(loader.load());

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Hà Nội Bank");
        stage.show();
    }
}
