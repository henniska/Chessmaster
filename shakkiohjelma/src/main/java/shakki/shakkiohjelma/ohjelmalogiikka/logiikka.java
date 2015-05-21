
package shakki.shakkiohjelma.ohjelmalogiikka;

public class logiikka {
    static String shakkiLauta[][] = {
        {"r", "k", "b", "q", "c", "b", "k", "r"},
        {"p", "p", "p", "p", "p", "p", "p", "p"},
        {" ", " ", " ", " ", " ", " ", " ", " "},
        {" ", " ", " ", " ", " ", " ", " ", " "},
        {" ", " ", " ", " ", " ", " ", " ", " "},
        {" ", " ", " ", " ", " ", " ", " ", " "},
        {"P", "P", "P", "P", "P", "P", "P", "P"},
        {"R", "K", "B", "Q", "C", "B", "K", "R"}};
    
    /* Kirjaimet kuvaavat shakkinappuloita (c/C = kuningas, koska k/K on otettu)
     * Pienet kirjaimet kuvaavat mustia nappuloita ja isot valkoisia 
     */

    
    public static String mahdollisetSiirrot() {
        String siirrot = "";
        
    /* Mahdolliset siirrot talletetaan formaattiin (int x, int y, int i, int j, String syötyNappula)
     * Eli x ja y kertoo alkuperäisen ruudun ja i ja j kertoo minne siirrytään ja syötyNappula kertoo jos joku nappula on syöty  
     * Eli tähän tyyliin: 5251r 3437 4258b 
     * Kun katsotaan onko siirto laillinen, katsotaan kuuluuko siirto tähän String olioon.  
     */        
        for (int i = 0; i < 64; i++) {
            String nappula = shakkiLauta[i/8][i%8];
            
    /* Näin jokainen nappula saa yhden int arvon jolla saa tietää sen paikan laudalla
     */            
            if (nappula.equals("P")) {
                siirrot+= mahdollisetP(i);
                
            } else if (nappula.equals("R")) {
                siirrot+= mahdollisetR(i);
                
            } else if (nappula.equals("K")) {
                siirrot+= mahdollisetK(i);
                
            } else if (nappula.equals("B")) {
                siirrot+= mahdollisetB(i);
                
            } else if (nappula.equals("Q")) {
                siirrot+= mahdollisetQ(i);
                
            } else if (nappula.equals("C")) {
                siirrot+= mahdollisetC(i);
                
            }
        }
        return siirrot;
       
    }
    
    public static String mahdollisetP(int i) {
        return "";
    }
    
    public static String mahdollisetR(int i) {
        return "";
    }
    
    public static String mahdollisetK(int i) {
        return "";
    }
    
    public static String mahdollisetB(int i) {
        return "";
    }
    
    public static String mahdollisetQ(int i) {
        return "";
    }
    
    public static String mahdollisetC(int i) {
        return "";
    }

    /* Näin aikaisessa vaiheessa en keksi hyödyllistä testausta
    */    
}
