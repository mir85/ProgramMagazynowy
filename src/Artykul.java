import java.util.ArrayList;
import java.util.Scanner;

public class Artykul {

    private String nazwaArtykulu;
    private double netto;
    private int vat = 0;
    private double brutto = netto * vat;

    private String grupaArtykulu;

    ArrayList<String> grupaArtykuluAL = new ArrayList<>();

    public String getGrupaArtykulu() {

        if (!grupaArtykuluAL.contains(grupaArtykulu)){
            System.out.println("Podaj poprawna grupe badz stworz nowa");
        }
        if (grupaArtykuluAL.contains(grupaArtykulu))
        return grupaArtykulu;
        return grupaArtykulu;
    }

    private String jednostkaMagazynowa;

    public Artykul(String nazwaArtykulu, double netto, int vat, String grupaArtykulu, String jednostkaMagazynowa) {
        this.nazwaArtykulu = nazwaArtykulu;
        this.netto = netto;
        this.vat = vat;
        this.grupaArtykulu = grupaArtykulu;
        this.jednostkaMagazynowa = jednostkaMagazynowa;
    }

    public void dodajArtykul(){
        Scanner skaner = new Scanner(System.in);
        System.out.println("Nazwa artykułu:");
        nazwaArtykulu = skaner.nextLine();
        System.out.println("Cena netto:");
        netto = skaner.nextDouble();
        System.out.println("Nazwa artykułu:");
        vat = skaner.nextInt();
        skaner.close();
    }

    public void dodajJednostkeMagazynowa(){
        Scanner skaner = new Scanner(System.in);
        String grupaArtykulow = skaner.nextLine();
        grupaArtykuluAL.add(grupaArtykulow);
        skaner.close();
    }

    /*public void dodajJednostke(){
        Scanner skaner1 = new Scanner(System.in);
        String jednostka = skaner1.nextLine();
        jednostkaMagazynowa.add(jednostka);
        skaner1.close();
    }
*/

}
