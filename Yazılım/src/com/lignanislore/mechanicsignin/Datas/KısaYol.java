package com.lignanislore.mechanicsignin.Datas;

public class KısaYol {
    public KısaYol(String tarayiciPath, String webYol, int tabSayisi, int acilisSuresi, String kullaniciAdi, String parola) {
        this.tarayiciPath = tarayiciPath;
        this.webYol = webYol;
        this.tabSayisi = tabSayisi;
        this.acilisSuresi = acilisSuresi;
        this.kullaniciAdi = kullaniciAdi;
        this.parola = parola;
    }

    public KısaYol() {

    }

    private String tarayiciPath;
    private String webYol;
    private int tabSayisi;
    private int acilisSuresi;
    private String kullaniciAdi;
    private String parola;

    public String getTarayiciPath() {
        return tarayiciPath;
    }

    public void setTarayiciPath(String tarayiciPath) {
        this.tarayiciPath = tarayiciPath;
    }

    public String getWebYol() {
        return webYol;
    }

    public void setWebYol(String webYol) {
        this.webYol = webYol;
    }

    public int getTabSayisi() {
        return tabSayisi;
    }

    public void setTabSayisi(int tabSayisi) {
        this.tabSayisi = tabSayisi;
    }

    public int getAcilisSuresi() {
        return acilisSuresi;
    }

    public void setAcilisSuresi(int acilisSuresi) {
        this.acilisSuresi = acilisSuresi;
    }

    public String getKullaniciAdi() {
        return kullaniciAdi;
    }

    public void setKullaniciAdi(String kullaniciAdi) {
        this.kullaniciAdi = kullaniciAdi;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }
}
