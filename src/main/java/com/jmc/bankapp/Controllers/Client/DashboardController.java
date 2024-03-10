package com.jmc.bankapp.Controllers.Client;

import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    public Text user_name;
    public Label login_date;
    public Label checking_bal;
    public Label checking_acc_num;
    public Label saving_bal;
    public Label saving_num;
    public Label income_lbl;
    public Label expense_lbl;
    public ListView transaction_listview;
    public TextField payee_address_fld;
    public TextField amount_fld;
    public TextArea message_fld;
    public Button btn_send_money;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
