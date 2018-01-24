package com.lignanislore.mechanicsignin.Robot;

import com.lignanislore.mechanicsignin.Datas.KısaYol;
import com.lignanislore.mechanicsignin.Main;
import javafx.application.Platform;
import javafx.scene.control.Alert;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class RobotControl  {
    public static void yazmayaBasla(final int id){
        KısaYol yazilacakKısayol= Main.kısaYols[id-1];

        if (!tarayiciAc(yazilacakKısayol.getTarayiciPath())){
            hata("Tarayıcı Acılamadı");
            return;
        }

        if (!Bekle(yazilacakKısayol.getAcilisSuresi())){
            hata("Bekleme gerçekleşmedi!");
            return;
        }


        if (!webGir(yazilacakKısayol.getWebYol())){
            hata("Adres girilirken sorun oluştu!!!");
            return;
        }


        if (!Bekle(yazilacakKısayol.getAcilisSuresi())){
            hata("Bekleme gerçekleşmedi!");
            return;
        }




        if (!tabKoy(yazilacakKısayol.getTabSayisi())){
            hata("Tab eklerken bir sorun oluştu!");
            return;

        }


        if (!kullaniciGir(yazilacakKısayol.getKullaniciAdi())){
            hata("kullanıcı adı girilirken sorun oluştu!!!");
            return;
        }

        if (!parolaGir(yazilacakKısayol.getParola())){
            hata("Parola girilirken sorun oluştu!!!");
            return;
        }








    }




    private static boolean tarayiciAc(String webYol) {
        File file=new File(webYol);
        if (!Desktop.isDesktopSupported()){
            return false;
        }
        Desktop desktop=Desktop.getDesktop();
        if (file.exists()) {
            try {
                desktop.open(file);
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }



    private static boolean Bekle(int acilisSuresi) {
        try {
            Thread.sleep(acilisSuresi*1000);
            return true;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }


    private static boolean webGir(String webYol) {
        if (webYol.trim().isEmpty()){
            return false;
        }
        try {
            Yaz yaz=new Yaz();
            yaz.type(webYol);
            yaz.type("\n");
        } catch (AWTException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    private static boolean tabKoy(int tabSayisi) {

        try {
            Yaz yaz=new Yaz();
            for (int i=0;i<tabSayisi;i++){

                yaz.type("\t"); }
        } catch (AWTException e) {
            e.printStackTrace();
            return false;
        }

        return true;


    }


    private static boolean kullaniciGir(String kullaniciAdi) {
        if (kullaniciAdi.trim().isEmpty()){
            return false;
        }

        try {
            Yaz yaz=new Yaz();
            yaz.type(kullaniciAdi);
            yaz.type("\t");
        } catch (AWTException e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }

    private static boolean parolaGir(String parola) {
        if (parola.trim().isEmpty()){
            return false;
        }

        try {
            Yaz yaz=new Yaz();
            yaz.type(parola);
            yaz.type("\n");
        } catch (AWTException e) {
            e.printStackTrace();
            return false;
        }
        return true;


    }

    private static void hata(String icerik){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Hata!");
                alert.setHeaderText(icerik);
                alert.setContentText("Lütfen bilgilerinizi kontrol edin");
                alert.showAndWait();
            }
        });

    }

}

