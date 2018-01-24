package com.lignanislore.mechanicsignin.Datas;

import java.sql.*;

public class VeriTabani {
    //-----------------------SINGLETON YAPISI--------------------------------------
    private static VeriTabani instance=null;
    private VeriTabani(){


    }

    public static VeriTabani getInstance(){
        if (instance==null){
            instance=new VeriTabani();
        }
        return instance;
    }
//-----------------------SINGLETON YAPISI---------------------------------------

    private Connection baglanti;


    private boolean veriTabaniAc() {

        try {
            baglanti = DriverManager.getConnection("jdbc:sqlite:kisayollar.db");

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    private void veritabaniKapat(){
        if (baglanti!=null){
            try {
                baglanti.close();

            } catch (SQLException e) {


            }
        }
    }


    public boolean veriYaz(final int id,final KısaYol kısaYol){
        if (!veriTabaniAc()){
            return false ;
        }

        String sorgu="update yollar set tarayiciPath='" +kısaYol.getTarayiciPath() +"',webYol='" +kısaYol.getWebYol() +
                "',tabSayisi=" +kısaYol.getTabSayisi() +",acilisSuresi=" +kısaYol.getAcilisSuresi() +
                ",kullaniciAdi='" +kısaYol.getKullaniciAdi() +"',parola='" +kısaYol.getParola() +"' where id="+id;

        try (Statement statement=baglanti.createStatement()){

            statement.executeUpdate(sorgu);

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;

        }


    }

    public KısaYol veriOku(final int id){
        if (!veriTabaniAc()){
            System.out.println("........Veri Okunamadı.......");
            return null;
        }

        KısaYol kısaYol = new KısaYol();

        try(Statement statement=baglanti.createStatement() ;
            ResultSet resultSet=statement.executeQuery("select * FROM yollar where id="+id)) {
            kısaYol.setTarayiciPath(resultSet.getString("tarayiciPath"));
            kısaYol.setWebYol(resultSet.getString("webYol"));
            kısaYol.setTabSayisi(resultSet.getInt("tabSayisi"));
            kısaYol.setAcilisSuresi(resultSet.getInt("acilisSuresi"));
            kısaYol.setKullaniciAdi(resultSet.getString("kullaniciAdi"));
            kısaYol.setParola(resultSet.getString("parola"));

        }catch (SQLException e) {
            e.printStackTrace();
            kısaYol=null;
        }

        veritabaniKapat();
        return kısaYol;

    }


    public KısaYol [] TumVeriOku(final int kısayolSayi){
        KısaYol[] kısaYols=new KısaYol[kısayolSayi];
        for (int i=0;i<kısayolSayi;i++){
            kısaYols[i]=veriOku(i+1);
        }
        return kısaYols;

    }

}
