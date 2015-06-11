
package shakki.shakkiohjelma.ohjelmalogiikka;

import java.util.Arrays;

public class NewMain {

    public static void main(String[] args) {
        Logiikka logiikka = new Logiikka();
        String testiLauta[][] = {
        {"r", "k", "b", "q", "c", "b", "k", "r"},
        {"p", "p", "p", "p", "p", "p", "p", "p"},
        {" ", " ", " ", " ", " ", " ", " ", " "},
        {" ", " ", " ", " ", " ", " ", " ", " "},
        {" ", " ", " ", " ", " ", " ", " ", " "},
        {" ", " ", " ", " ", " ", " ", " ", " "},
        {"P", "P", "P", "P", "P", "P", "P", "P"},
        {"R", "K", "B", "Q", "C", "B", "K", "R"}};
        logiikka.vaihdaLauta(testiLauta);
        System.out.println("Siirrot:");
        System.out.println(Logiikka.mahdollisetSiirrot());

    }
    
}
