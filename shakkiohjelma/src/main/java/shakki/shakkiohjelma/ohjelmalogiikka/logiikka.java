
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

        String siirrot = "";
        String syotyNappula = "";
        int a = i/8;
        int b = i%8;
        for (int j = 0; j < 9; j++) {
            if (j != 4) {
                
    /* Tämä käy läpi jokaisen kuninkaan liikkumapaikan (8), j==4 ei lasketa sillä se on kuninkaan tämänhetkinen paikka.
     * Liikkuminen sallitaan jos paikka on tyhjä tai mustan nappulan hallitsema, eikä kuningas joudu shakkiin.           
     */         
                if (((a-1 + j/3) < 0 || (b-1 + j%3) < 0) || ((a-1 + j/3) > 7 || (b-1 + j%3) > 7)) {
                    continue;
                }

                String liikkumapaikka = shakkiLauta[a-1 + j/3][b-1 + j%3];
                char tyhja = ' ';
                
                if (Character.isLowerCase(liikkumapaikka.charAt(0)) || tyhja == (liikkumapaikka.charAt(0))) {
                    syotyNappula = liikkumapaikka;
                    shakkiLauta[a][b] = " ";
                    liikkumapaikka = "C";
                    if (onkoShakissa()) {
                        siirrot = siirrot + a + b + (a-1 + j/3) + (b-1 + j%3) + syotyNappula;
                    }
                    shakkiLauta[a][b] = "C";
                    liikkumapaikka = syotyNappula;
                }

            }
            
        }

    /* Pitää lisätä linnoittautuminen.
     */                        
        return siirrot;
    }

    public static boolean onkoShakissa() {
        return true;
    }   
}
