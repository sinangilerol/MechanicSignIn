package com.lignanislore.mechanicsignin;

import com.lignanislore.mechanicsignin.Datas.KısaYol;
import com.lignanislore.mechanicsignin.Datas.VeriTabani;
import com.lignanislore.mechanicsignin.DialogPencere.DuzenleDialogController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public static int id=99;

    @FXML
    private Button guncelle1;

    @FXML
    public Button sil1;

    @FXML
    private Label web1;

    @FXML
    private Label web2;

    @FXML
    private Button guncelle2;

    @FXML
    private Button sil2;

    @FXML
    private Label web3;

    @FXML
    private Button guncelle3;

    @FXML
    private Button sil3;

    @FXML
    private Label web4;

    @FXML
    private Button guncelle4;

    @FXML
    private Button sil4;

    @Override
    public void initialize(URL location, ResourceBundle resources)  {
        try {

            web1.setText(Main.kısaYols[0].getWebYol());
            web2.setText(Main.kısaYols[1].getWebYol());
            web3.setText(Main.kısaYols[2].getWebYol());
            web4.setText(Main.kısaYols[3].getWebYol());
        }catch (Exception e){
            e.getMessage();
        }
    }

    @FXML
    private void sil(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("KısaYol Siliniyor ");
        alert.setHeaderText("Son kararınız mı ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            if (event.getSource()==sil1){
                id=1;
            }else if (event.getSource()==sil2){
                id=2;
            }else if (event.getSource()==sil3){
                id=3;
            }else if (event.getSource()==sil4){
                id=4;
            }

            kısayolSil();

        }else if (result.get()==ButtonType.CANCEL){
            return;
        }

    }

    private void kısayolSil() {
        KısaYol kısaYol=new KısaYol();
        kısaYol.setAcilisSuresi(0);
        kısaYol.setParola("");
        kısaYol.setKullaniciAdi("");
        kısaYol.setTabSayisi(0);
        kısaYol.setWebYol("");
        kısaYol.setTarayiciPath("");
        VeriTabani.getInstance().veriYaz(id,kısaYol);
        Main.kısaYols[id-1]=kısaYol;
        webDuzenle(id);

    }

    @FXML
    private GridPane anaPencere;

    @FXML
    private void guncelle(ActionEvent event) throws IOException {

        if (event.getSource()==guncelle1){
            id=1;
        }else if (event.getSource()==guncelle2){
            id=2;
        }else if (event.getSource()==guncelle3){
            id=3;
        }else if (event.getSource()==guncelle4){
            id=4;
        }

        DialogOlustur();

    }

    private void DialogOlustur() throws IOException {
        FXMLLoader fxmlLoader=new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("DialogPencere\\DuzenleDialog.fxml"));

        Dialog<ButtonType> dialog=new Dialog<>();
        dialog.initOwner(anaPencere.getScene().getWindow());
        dialog.getDialogPane().setContent(fxmlLoader.load());
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);


        Optional<ButtonType> optional=dialog.showAndWait();

        if(optional.get()== ButtonType.OK){
            DuzenleDialogController controller=fxmlLoader.getController();
            try {

                controller.KisayolDuzenle(id);

            }catch (NumberFormatException e){
                Toolkit.getDefaultToolkit().beep();
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("HATA!!!");
                alert.setHeaderText("Sayısal değer girilecek alana string bir değer yazdınız");
                alert.setContentText("Bu yüzden yaptığınız güncellemeler  kaydedilmedi");
                alert.show();
            }
            webDuzenle(id);
        }


    }

    private void webDuzenle(int id) {
        if (id==1){
            web1.setText(Main.kısaYols[0].getWebYol());
        }else if(id==2){
            web2.setText(Main.kısaYols[1].getWebYol());
        }else if(id==3){
            web3.setText(Main.kısaYols[2].getWebYol());
        }else if(id==4){
            web4.setText(Main.kısaYols[3].getWebYol());
        }
    }




}

