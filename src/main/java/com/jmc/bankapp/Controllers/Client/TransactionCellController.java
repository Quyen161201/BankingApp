package com.jmc.bankapp.Controllers.Client;

import com.jmc.bankapp.Models.Transaction;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.scene.image.Image;

public class TransactionCellController implements Initializable {

    public FontAwesomeIconView in_icon;
    public FontAwesomeIconView out_icon;
    public Label trans_date;
    public Label trans_sender;
    public Label trans_receive;
    public Label trans_amount;

    public Label lbl_status;

    public final Transaction transaction;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        setStyleTrans();

    }

    public TransactionCellController(Transaction transaction){
        this.transaction = transaction;
    }

    public void setStyleTrans(){
        trans_date.setText(transaction.dateProperty().getValue().toString());
        trans_sender.setText(transaction.senderProperty().getValue());
        trans_receive.setText(transaction.receiverProperty().get());

        double trans_amo =transaction.amountProperty().get();
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        trans_amount.setText(currencyFormat.format(trans_amo));

        String phone = LoginSession.getPhoneNumber();
        if(phone.equals(transaction.senderProperty().getValue())){
            lbl_status.setText("Đã chuyển");

        }
        else {
            lbl_status.setText("Đã nhận");
        }
    }
}
