package com.jmc.bankapp.Controllers.Client;

import com.jmc.bankapp.Models.CheckingAccount;
import com.jmc.bankapp.Models.Client;
import com.jmc.bankapp.Models.Model;
import com.jmc.bankapp.Models.SavingAccount;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import static com.jmc.bankapp.Controllers.Admin.ClientCellController.showAlert;
import static com.jmc.bankapp.Controllers.Admin.ClientCellController.showConfirmDialog;

public class AccountController implements Initializable {
    public Label ch_number_acc;
    public Label transaction_limit;

    public Label ch_acc_balence;
    public Label sav_acc_num;
    public Label withdrawal_limit;

    public Label sav_acc_bal;
    public TextField amount_to_sv;
    public Button btn_mount_to_sv;
    public TextField amount_to_ch;
    public Button btn_amount_to_ch;

    private ObservableList<SavingAccount> savingAccountObservableList;
    private ObservableList<CheckingAccount> checkingAccountObservableList;


    public AccountController(){}

    public AccountController(ObservableList<SavingAccount> savingAccountObservableList, ObservableList<CheckingAccount> checkingAccountObservableList){
        this.savingAccountObservableList = savingAccountObservableList;
        this.checkingAccountObservableList = checkingAccountObservableList;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dataCheckingAccAndSavingAcc();
        btn_mount_to_sv.setOnAction(event -> onCheckingAccountBalance());
        btn_amount_to_ch.setOnAction(event -> onSavingAccountBalance());


    }

    public void dataCheckingAccAndSavingAcc()
    {

        savingAccountObservableList = FXCollections.observableArrayList();
        checkingAccountObservableList = FXCollections.observableArrayList();
        String phone= LoginSession.getPhoneNumber();
        Model.getInstance().savingAccountAndCheckingAccount(phone,savingAccountObservableList,checkingAccountObservableList);

        double withdrawal = savingAccountObservableList.getFirst().withdrawalLimitProperty().getValue();
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        withdrawal_limit.setText(currencyFormat.format(withdrawal));

        double sav_balance = savingAccountObservableList.getFirst().balanceProperty().getValue();
        sav_acc_bal.setText(currencyFormat.format(sav_balance));

        double ch_balance = checkingAccountObservableList.getFirst().balanceProperty().get();
        ch_acc_balence.setText(currencyFormat.format(ch_balance));

        sav_acc_num.setText(String.valueOf(savingAccountObservableList.getFirst().account_numberProperty().get()));

        transaction_limit.setText(String.valueOf(checkingAccountObservableList.getFirst().transactionLimitProperty().getValue()));

        ch_number_acc.setText(String.valueOf(checkingAccountObservableList.getFirst().account_numberProperty().getValue()));


    }
    public void onCheckingAccountBalance()
    {
        boolean checkMess = showConfirmDialog("Bạn có đồng ý chuyen tiền");
        if(checkMess)
        {
            String phone= LoginSession.getPhoneNumber();
            double balance = Double.parseDouble(amount_to_sv.getText());
            int rs = Model.getInstance().updateCheckingAccountBalance(phone,balance);
            if(rs>1)
            {
                double sav_bal_old= convertCurrencyToDouble(sav_acc_bal.getText());
                showAlert("Bạn đã chuyển tiền thành công",Alert.AlertType.INFORMATION);

                double sav_acc_bal_new =sav_bal_old + balance;
                NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
                sav_acc_bal.setText(currencyFormat.format(sav_acc_bal_new));

                double ch_bal_old= convertCurrencyToDouble(ch_acc_balence.getText());
                double ch_acc_bal_new =ch_bal_old - balance;
                ch_acc_balence.setText(currencyFormat.format(ch_acc_bal_new));
                amount_to_sv.setText("");

            }
            else showAlert("Bạn đã chuyển tiền không thành công",Alert.AlertType.ERROR);

        }

    }

    public void onSavingAccountBalance()
    {
        boolean checkMess = showConfirmDialog("Bạn có đồng ý chuyen tiền");
        if(checkMess)
        {
            String phone= LoginSession.getPhoneNumber();
            double balance = Double.parseDouble(amount_to_ch.getText());
            int rs = Model.getInstance().updateCheckingAccountBalance(phone,balance);
            if(rs>1)
            {
                double ch_bal_old= convertCurrencyToDouble(ch_acc_balence.getText());
                showAlert("Bạn đã rut tiền thành công",Alert.AlertType.INFORMATION);
                double sv_bal_old= convertCurrencyToDouble(sav_acc_bal.getText());
                double sv_acc_bal_new =sv_bal_old - balance;
                double ch_acc_bal_new =ch_bal_old + balance;
                NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
                sav_acc_bal.setText(currencyFormat.format(sv_acc_bal_new));
                ch_acc_balence.setText(currencyFormat.format(ch_acc_bal_new));
                amount_to_ch.setText("");

            }
            else showAlert("Bạn đã chuyển tiền không thành công",Alert.AlertType.ERROR);

        }
    }
    public static void showAlert(String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public double convertCurrencyToDouble(String currency) {

        String cleanedCurrency = currency.replaceAll("[^0-9]", "");

        try {
            double amount = Double.parseDouble(cleanedCurrency);
            return amount;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            // Xử lý ngoại lệ nếu chuỗi không thể chuyển đổi thành double
            return 0.0; // Hoặc giá trị mặc định khác tùy thuộc vào yêu cầu của bạn
        }
    }

}
