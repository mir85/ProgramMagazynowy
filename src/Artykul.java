import java.util.ArrayList;
import java.util.Scanner;

public class Artykul {

    private String nazwaArtykulu = "";
    private double netto = 0;
    private int vat = 0;
    private double brutto = netto * vat;

    private ArrayList<String> grupyArtykulow = new ArrayList<>();
    private ArrayList<String> jednostkaMagazynowa = new ArrayList<>();

    public void dodajJednostkeMagazynowa(){
        Scanner skaner = new Scanner(System.in);
        String grupaArtykulow = skaner.nextLine();
        grupyArtykulow.add(grupaArtykulow);
        skaner.close();
    }

    public void dodajJednostke(){
        Scanner skaner1 = new Scanner(System.in);
        String jednostka = skaner1.nextLine();
        jednostkaMagazynowa.add(jednostka);
        skaner1.close();
    }
}
