package com.jmc.bankapp.Controllers;

import com.jmc.bankapp.Models.Model;
import com.jmc.bankapp.Views.AccountType;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public ChoiceBox<AccountType> acc_selector;
    public Label payee_address_lbl;
    public TextField payee_address_field;
    public TextField password_field;
    public Button login_btn;
    public Label error_lbl;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        acc_selector.setItems(FXCollections.observableArrayList(AccountType.ADMIN,AccountType.CLIENT));
        acc_selector.setValue(Model.getInstance().getViewFactory().getLoginAccountType());
        acc_selector.valueProperty().addListener(observable -> Model.getInstance().getViewFactory().setLoginAccountType(acc_selector.getValue()));

        login_btn.setOnAction(event -> onLogin());
    }

    //check login
    private void onLogin(){

        Stage stage =(Stage) error_lbl.getScene().getWindow();


        if(Model.getInstance().getViewFactory().getLoginAccountType()==AccountType.CLIENT)
        {
           Model.getInstance().dataClient(payee_address_field.getText(),password_field.getText());
           if(Model.getInstance().getClientSuccessLoginFlag())
           {
               Model.getInstance().getViewFactory().showClientWindow();

               Model.getInstance().getViewFactory().closeStage(stage);

           }
           else {
               payee_address_field.setText("");
               password_field.setText("");
               error_lbl.setText("Tài khoản hoặc mật khẩu không chính xác ");
           }

        }
        else {
            Model.getInstance().dataAdmin(payee_address_field.getText(),password_field.getText());
            if(Model.getInstance().getAdminSuccessLoginFlag())
            {
                Model.getInstance().getViewFactory().showAdminWindow();

                Model.getInstance().getViewFactory().closeStage(stage);

            }
            else {
                payee_address_field.setText("");
                password_field.setText("");
                error_lbl.setText("Tài khoản hoặc mật khẩu không chính xác ");
            }

        }

    }



}
