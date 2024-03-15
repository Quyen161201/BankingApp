package com.jmc.bankapp.Controllers.Client;

import com.jmc.bankapp.Controllers.LoginController;
import com.jmc.bankapp.Models.Client;
import com.jmc.bankapp.Models.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.net.URL;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import static com.jmc.bankapp.Controllers.Admin.ClientCellController.showAlert;

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

    public Client client;

    private ObservableList<Client> clientObservable;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setDashboard();
        btn_send_money.setOnAction(event -> onCreateTransaction());
    }


    public DashboardController(ObservableList<Client> clientObservable) {
        this.clientObservable = clientObservable;
    }

    public  DashboardController(){}

    public void setDashboard()
    {
        String phone = LoginSession.getPhoneNumber();
        String pass = LoginSession.getPassword();
        clientObservable = FXCollections.observableArrayList();
        Model.getInstance().dataClient(phone, pass,clientObservable);

        //name
        user_name.setText("Xin Chào "+clientObservable.getFirst().nameProperty().getValue());
        // date login
        LocalDate currentDate = LocalDate.now();
        login_date.setText("Hôm nay, "+String.valueOf(currentDate));

        // balance checkinng
        double balance = clientObservable.get(0).CheckingAccountProperty().get().balanceProperty().getValue();
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        checking_bal.setText(currencyFormat.format(balance));

        // balance saving
        double balance_sav = clientObservable.get(0).SavingAccountProperty().get().balanceProperty().getValue();
        saving_bal.setText(currencyFormat.format(balance_sav));
        //chech acc
        String ch_acc = clientObservable.getFirst().CheckingAccountProperty().get().account_numberProperty().getValue();
        String fourLastChars = ch_acc.substring(ch_acc.length() - 4);
        checking_acc_num.setText(fourLastChars);

        //sav acc
        String sv_acc = clientObservable.getFirst().SavingAccountProperty().get().account_numberProperty().getValue();
        String fourLastCharsSav = sv_acc.substring(sv_acc.length() - 4);
        saving_num.setText(fourLastCharsSav);

    }

    public void onCreateTransaction()
    {
        if(payee_address_fld.getText().isEmpty() || amount_fld.getText().isEmpty())

        {
            showAlert("Vui lòng nhập đầy đủ thông tin", Alert.AlertType.ERROR);
            return;
        }
        if(!isNumeric(payee_address_fld.getText())|| !isNumeric(amount_fld.getText())){
            showAlert("Số tài khoản và sô tiền không được là chữ", Alert.AlertType.ERROR);
        }
        else {
            boolean isPhone = Model.model.isPhoneClient(payee_address_fld.getText());

            if(isPhone && !compareStrings(payee_address_fld.getText(),LoginSession.getPhoneNumber()))
            {
                int rs =Model.getInstance().createTransaction(LoginSession.getPhoneNumber(), payee_address_fld.getText(),Double.parseDouble(amount_fld.getText()),message_fld.getText());
                if(rs==1)
                {
                    showAlert("Gửi tiền thành công", Alert.AlertType.INFORMATION);
                    payee_address_fld.setText("");
                    amount_fld.setText("");
                    message_fld.setText("");
                }
                else {
                    showAlert("Gửi tiền thất bại", Alert.AlertType.ERROR);
                }
            }
            else if(compareStrings(payee_address_fld.getText(),LoginSession.getPhoneNumber())){
                showAlert("Số tài khoản không được là số của bạn", Alert.AlertType.ERROR);
            }
            else{
                showAlert("Số tài khoản không đúng", Alert.AlertType.ERROR);
            }

        }

    }

    public static boolean isNumeric(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    public static boolean compareStrings(String str1, String str2) {
        // Loại bỏ khoảng trắng và kí tự không mong muốn
        String cleanStr1 = str1.replaceAll("\\s", "").replaceAll("[^a-zA-Z0-9]", "");
        String cleanStr2 = str2.replaceAll("\\s", "").replaceAll("[^a-zA-Z0-9]", "");

        // Chuyển đổi tất cả các kí tự về chữ thường (hoặc chữ hoa)
        cleanStr1 = cleanStr1.toLowerCase();
        cleanStr2 = cleanStr2.toLowerCase();

        // So sánh hai chuỗi đã được xử lý
        return cleanStr1.equals(cleanStr2);
    }
}
