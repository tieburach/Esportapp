package sample;

public class ModelWyniki {
    private static int refleks;
    private static int spostrzegawczosc;
    private static int koherencja;
    private static int podejmowaniedecyzji;

    public static void incrementpodejmowanie(){
        podejmowaniedecyzji++;
    }

    public static int getSpostrzegawczosc() {
        return spostrzegawczosc;
    }

    public static void setSpostrzegawczosc(int spostrzegawczosc) {
        ModelWyniki.spostrzegawczosc = spostrzegawczosc;
    }

    public static int getKoherencja() {
        return koherencja;
    }

    public static void setKoherencja(int koherencja) {
        ModelWyniki.koherencja = koherencja;
    }

    public static int getPodejmowaniedecyzji() {
        return podejmowaniedecyzji;
    }

    public static void setPodejmowaniedecyzji(int podejmowaniedecyzji) {
        ModelWyniki.podejmowaniedecyzji = podejmowaniedecyzji;
    }

    public static int getRefleks() {
        return refleks;
    }

    public static void setRefleks(int refleks) {
        ModelWyniki.refleks = refleks;
    }
}
