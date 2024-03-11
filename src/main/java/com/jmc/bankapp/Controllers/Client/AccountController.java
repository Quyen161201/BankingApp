package com.jmc.bankapp.Controllers.Client;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AccountController implements Initializable {
    public Label ch_number_acc;
    public Label transaction_limit;
    public Label ch_acc_date;
    public Label ch_acc_balence;
    public Label sav_acc_num;
    public Label withdrawal_limit;
    public Label sav_acc_date;
    public Label sav_acc_bal;
    public TextField amount_to_sv;
    public Button btn_mount_to_sv;
    public TextField amount_to_ch;
    public Button btn_amount_to_ch;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
