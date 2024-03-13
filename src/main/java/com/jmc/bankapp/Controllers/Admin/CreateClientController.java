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

    public DatePicker txt_date;
    public TextField txt_phone;
    public TextField txt_cccd;
    public Button btn_create_client;
    public Label error_create_client;
    public TextField txt_ch_amount;
    public TextField txt_sav_amount;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
