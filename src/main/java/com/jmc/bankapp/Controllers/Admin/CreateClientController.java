package com.jmc.bankapp.Controllers.Admin;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateClientController implements Initializable {
    public TextField txt_name;
    public TextField txt_password;
    public TextField txt_card_number;
    public DatePicker txt_date;
    public TextField txt_phone;
    public TextField txt_cccd;
    public TextField txt_email;
    public Button btn_create_client;
    public Label error_create_client;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
