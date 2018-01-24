package com.lignanislore.mechanicsignin;

import com.lignanislore.mechanicsignin.Datas.KısaYol;
import com.lignanislore.mechanicsignin.Datas.VeriTabani;
import com.lignanislore.mechanicsignin.Robot.SerialContact;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main extends Application  {
    //veritabanından kısayollar çekiliyor
    public static KısaYol[] kısaYols = VeriTabani.getInstance().TumVeriOku(4);


    @Override
    public void init() throws Exception {



    }

    @Override
    public void start(Stage primaryStage) throws Exception {


        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("");
        alert.setHeaderText("Yapmak istediğiniz işlemi seçin:");

        //Butonlar olusturuldu
        ButtonType kisayolED = new ButtonType("KısaYol ekle-sil");
        ButtonType yKlavye = new ButtonType("Yardımcı Klavyeyi Baslat");
        ButtonType cikis=new ButtonType("Çıkış", ButtonBar.ButtonData.CANCEL_CLOSE);


        alert.getButtonTypes().setAll(kisayolED, yKlavye,cikis);


        Optional<ButtonType> result = alert.showAndWait();
        if (result.get()==cikis){
            return;
        }else if (result.get() == yKlavye) {

            while (true) {

                List<String> choices = new ArrayList<>();
                for (int i = 0; i < 30; i++) {
                    choices.add("COM" + i);
                }


                ChoiceDialog<String> dialog = new ChoiceDialog<>("COM1", choices);
                dialog.setTitle("Port Seçimi..");
                dialog.setHeaderText("Aygıtınızın bağlı olduğu portu seçiniz");
                dialog.setContentText("Portlar:");


                Optional<String> secilenPort = dialog.showAndWait();
                String port = "";
                if (secilenPort.isPresent()) {
                    port = secilenPort.get();
                } else {
                    return;
                }

                try {
                    SerialContact a = new SerialContact();
                    a.connect(port);
                    //secilen portta cihaz varsa direkt pencereyi göster
                    break;
                } catch (Exception e) {

                    e.printStackTrace();

                    Alert alert1= new Alert(Alert.AlertType.CONFIRMATION);
                    alert1.setTitle("Hata!!");
                    alert1.setHeaderText("Seçtiginiz porta bağlı bir cihaz bulunmuyor");
                    ButtonType tekrar = new ButtonType("tekrar dene..");
                    alert1.getButtonTypes().setAll( tekrar,cikis);
                    Optional<ButtonType> optional = alert1.showAndWait();
                    if (optional.get() == tekrar) {
                        continue;
                    } else {

                        return;

                    }
                }

            }

        }




        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Mechanic SignIn");
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);

    }







}

