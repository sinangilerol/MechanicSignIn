package com.lignanislore.mechanicsignin.DialogPencere;

import com.lignanislore.mechanicsignin.Controller;
import com.lignanislore.mechanicsignin.Datas.KısaYol;
import com.lignanislore.mechanicsignin.Datas.VeriTabani;
import com.lignanislore.mechanicsignin.Main;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;


public class DuzenleDialogController  {

    @FXML
    private TextField textTyol;

    @FXML
    private TextField textweb;

    @FXML
    private TextField textTabsayisi;

    @FXML
    private TextField textkullanici;

    @FXML
    private TextField textparola;

    @FXML
    private TextField textsure;

    public void KisayolDuzenle(int id){
        KısaYol kısaYol=new KısaYol();
        kısaYol.setTarayiciPath(textTyol.getText().toString());
        kısaYol.setWebYol(textweb.getText().toString());

        kısaYol.setKullaniciAdi(textkullanici.getText().toString());
        kısaYol.setParola(textparola.getText().toString());


        kısaYol.setAcilisSuresi(Integer.parseInt(textsure.getText().toString()));
        kısaYol.setTabSayisi(Integer.parseInt(textTabsayisi.getText()));



        Main.kısaYols[id-1]=kısaYol;
        VeriTabani.getInstance().veriYaz(id,kısaYol);



    }

    public void initialize(){


            textTyol.setText(Main.kısaYols[Controller.id-1].getTarayiciPath());
            textweb.setText(Main.kısaYols[Controller.id-1].getWebYol());
            textTabsayisi.setText(String.valueOf(Main.kısaYols[Controller.id-1].getTabSayisi()));
            textkullanici.setText(Main.kısaYols[Controller.id-1].getKullaniciAdi());
            textparola.setText(Main.kısaYols[Controller.id-1].getParola());
            textsure.setText(String.valueOf(Main.kısaYols[Controller.id-1].getAcilisSuresi()));



    }








}
