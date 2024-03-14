package com.jmc.bankapp.Controllers.Admin;

import com.jmc.bankapp.Models.CheckingAccount;
import com.jmc.bankapp.Models.Client;
import com.jmc.bankapp.Models.Model;
import com.jmc.bankapp.Models.SavingAccount;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.Random;


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

    public Client client;

    public ListView<Client> listView;
    public ObservableList<Client> clientObservableList;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
            btn_create_client.setOnAction(event -> onCreateClient());
    }
    public CreateClientController(){
        this.client = new Client("","",null,"","",null,null);
    }
    public Client getClient() {
        return client;
    }

    public void onCreateClient() {
        CheckingAccount checkingAccount = null;
        SavingAccount savingAccount = null;
        String name = txt_name.getText();
        String password = txt_password.getText();
        DatePicker datePk = new DatePicker();
        datePk.setValue(LocalDate.now());

        String phone = txt_phone.getText();
        String cccd = txt_cccd.getText();
        String ch_amount = txt_ch_amount.getText();
        String sav_amount = txt_sav_amount.getText();



        if (name.isEmpty() || password.isEmpty() || datePk.getValue() == null || phone.isEmpty() || cccd.isEmpty() || ch_amount.isEmpty() || sav_amount.isEmpty()) {
            error_create_client.setText("Các trường không được bỏ trống");

            if(isNumeric(name)) {
                error_create_client.setText("Tên không được là số hoặc để trống");
            }
            if(!isNumeric(phone)){
                error_create_client.setText("Số điện thoại không được là chữ");
            }
            if(!isNumeric(ch_amount)){
                error_create_client.setText(" Tiền tài khoản không được là chữ");
            }
            if(!isNumeric(sav_amount)){
                error_create_client.setText(" Tiền tiết kiệm không được là chữ");
            }


        } else {
            error_create_client.setText("");
             String sav_acc=generateRandomString();
             String ch_acc=generateRandomString();
           int rs=  Model.getInstance().createDataClient(name,phone,password,Date.valueOf(datePk.getValue()),cccd,ch_acc,sav_acc,20000000,Double.parseDouble(sav_amount),Double.parseDouble(ch_amount),10);
           if (rs > 2){
               error_create_client.setText("Tạo mới thành công");
               savingAccount = new SavingAccount(name,sav_acc,Double.parseDouble(sav_amount),20000000);
               checkingAccount = new CheckingAccount(name,ch_acc,Double.parseDouble(ch_amount),10);
               client.CheckingAccountProperty().set(checkingAccount);
               client.SavingAccountProperty().set(savingAccount);
               Client client  = new Client(name, phone, Date.valueOf(datePk.getValue()).toLocalDate(), cccd, password, savingAccount, checkingAccount); //

           }
        }
    }

    private boolean isNumeric(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    private String generateRandomString() {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < 16; i++) {
            int randomNumber = random.nextInt(10);
            stringBuilder.append(randomNumber);
        }
        if (stringBuilder.length() > 16) {
            stringBuilder.setLength(16);
        }
        System.out.println(stringBuilder.toString());
        return stringBuilder.toString();
    }
}
