package kinaszw;

public class ModelWyniki {
    private static int refleks;
    private static int spostrzegawczosc;
    private static int koherencja;
    private static int podejmowaniedecyzji;

    static void incrementpodejmowanie() {
        podejmowaniedecyzji++;
    }

    static int getSpostrzegawczosc() {
        return spostrzegawczosc;
    }

    static void setSpostrzegawczosc(int spostrzegawczosc) {
        ModelWyniki.spostrzegawczosc = spostrzegawczosc;
    }

    static int getKoherencja() {
        return koherencja;
    }


    static void setKoherencja(int koherencja) {
        ModelWyniki.koherencja = koherencja;
    }

    static int getPodejmowaniedecyzji() {
        return podejmowaniedecyzji;
    }

    public static void setPodejmowaniedecyzji(int podejmowaniedecyzji) {
        ModelWyniki.podejmowaniedecyzji = podejmowaniedecyzji;
    }

    static int getRefleks() {
        return refleks;
    }

    static void setRefleks(int refleks) {
        ModelWyniki.refleks = refleks;
    }
}
