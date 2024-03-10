package com.jmc.bankapp;

import com.jmc.bankapp.Models.Model;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Model.getInstance().getViewFactory().showLoginWindow();

    }
}
