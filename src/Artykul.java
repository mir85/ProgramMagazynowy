import java.util.Scanner;

public class Artykul {

    private String nazwaArtykulu;
    private double netto;
    private double vat;
    private String grupaArtykulu;
    private String jednostkaMagazynowa;

    public double getNetto() {
        return netto;
    }

    public void setNetto(double netto) {
        this.netto = netto;
    }

    public double getVat() {
        return vat;
    }

    public void setVat(double vat) {
        this.vat = vat;
    }

    public String getGrupaArtykulu() {
        return grupaArtykulu;
    }

    public void setGrupaArtykulu(String grupaArtykulu) {
        this.grupaArtykulu = grupaArtykulu;
    }

    public String getJednostkaMagazynowa() {
        return jednostkaMagazynowa;
    }

    public void setJednostkaMagazynowa(String jednostkaMagazynowa) {
        this.jednostkaMagazynowa = jednostkaMagazynowa;
    }

    public void setNazwaArtykulu(String nazwaArtykulu) {
        this.nazwaArtykulu = nazwaArtykulu;
    }

    public String getNazwaArtykulu() {
        return nazwaArtykulu;
    }

    public Artykul() {
        this.nazwaArtykulu = nazwaArtykulu;
        this.netto = netto;
        this.vat = vat;
        this.grupaArtykulu = grupaArtykulu;
        this.jednostkaMagazynowa = jednostkaMagazynowa;
    }

}
