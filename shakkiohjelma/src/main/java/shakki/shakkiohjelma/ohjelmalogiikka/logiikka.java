
package shakki.shakkiohjelma.ohjelmalogiikka;

public class Logiikka {
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
        
    /* Mahdolliset siirrot talletetaan formaattiin (int x, int y, int i, int j, String syÃ¶tyNappula)
     * Eli x ja y kertoo alkuperÃ¤isen ruudun ja i ja j kertoo minne siirrytÃ¤Ã¤n ja syÃ¶tyNappula kertoo jos joku nappula on syÃ¶ty  
     * Eli tÃ¤hÃ¤n tyyliin: 5251r 3437 4258b 
     * Kun katsotaan onko siirto laillinen, katsotaan kuuluuko siirto tÃ¤hÃ¤n String olioon.  
     */        
        for (int i = 0; i < 64; i++) {
            String nappula = shakkiLauta[i/8][i%8];
            
    /* NÃ¤in jokainen nappula saa yhden int arvon jolla saa tietÃ¤Ã¤ sen paikan laudalla
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
    
    public static String mahdollisetK(int i) {
        return "";
    }
    
    public static String mahdollisetR(int i) {
        String siirrot = "";
        String syotyNappula = "";
        int a = i/8;
        int b = i%8;
        int numero = 1;
        for (int j = -1; j <= 1; j++) {
            for (int w = -1; w <= 1; w++) {
                if (j == 0 || w == 0) {
                    
    /* Jos j ja w ovat 0 niin koodi antaa aina vain nappulan aloituspaikan.
     */ 
                    if (j == 0 && w == 0) {
                        continue;
                    }
                    
    /* Try and catch pysäyttävät koodin jos koordinaatit ovat laudan ulkopuolella eli < 0 tai > 7.
     */               
                    try {
                        
                        char tyhja = ' ';
                        while (tyhja == shakkiLauta[a+numero*j][b+numero*w].charAt(0)) {
                            syotyNappula = shakkiLauta[a+numero*j][b+numero*w];
                            shakkiLauta[a][b] = " ";
                            shakkiLauta[a+numero*j][b+numero*w] = "R";
                            if (onkoShakissa()) {
                                siirrot = siirrot + a + b + (a+numero*j) + (b+numero*w) + syotyNappula;
                            }
                            shakkiLauta[a][b] = "R";
                            shakkiLauta[a+numero*j][b+numero*w] = syotyNappula;
                            numero++;
                        }
                        
    /* Jos while loop on pysähtynyt se tarkoittaa, että koordinaatit ovat joko oman tai vastustajan nappulan kohdalla.
     */                     
                        if (Character.isLowerCase(shakkiLauta[a+numero*j][b+numero*w].charAt(0))) {
                            syotyNappula = shakkiLauta[a+numero*j][b+numero*w];
                            shakkiLauta[a][b] = " ";
                            shakkiLauta[a+numero*j][b+numero*w] = "R";
                            if (onkoShakissa()) {
                                siirrot = siirrot + a + b + (a+numero*j) + (b+numero*w) + syotyNappula;
                            }
                            shakkiLauta[a][b] = "R";
                            shakkiLauta[a+numero*j][b+numero*w] = syotyNappula;
                        }

                    } catch (Exception e) {}
                    numero = 1;
                }
            }
            
        }
        
        return siirrot;
    }
    

    public static String mahdollisetB(int i) {
        String siirrot = "";
        String syotyNappula = "";
        int a = i/8;
        int b = i%8;
        int numero = 1;
        for (int j = -1; j <= 1; j+=2) {
            for (int w = -1; w <= 1; w+=2) {
                
                try {
                
                    char tyhja = ' ';
                    while (tyhja == shakkiLauta[a+numero*j][b+numero*w].charAt(0)) {
                        syotyNappula = shakkiLauta[a+numero*j][b+numero*w];
                        shakkiLauta[a][b] = " ";
                        shakkiLauta[a+numero*j][b+numero*w] = "B";
                        if (onkoShakissa()) {
                            siirrot = siirrot + a + b + (a+numero*j) + (b+numero*w) + syotyNappula;
                        }
                        shakkiLauta[a][b] = "B";
                        shakkiLauta[a+numero*j][b+numero*w] = syotyNappula;
                        numero++;
                    }
                    if (Character.isLowerCase(shakkiLauta[a+numero*j][b+numero*w].charAt(0))) {
                        syotyNappula = shakkiLauta[a+numero*j][b+numero*w];
                        shakkiLauta[a][b] = " ";
                        shakkiLauta[a+numero*j][b+numero*w] = "B";
                        if (onkoShakissa()) {
                            siirrot = siirrot + a + b + (a+numero*j) + (b+numero*w) + syotyNappula;
                        }
                        shakkiLauta[a][b] = "B";
                        shakkiLauta[a+numero*j][b+numero*w] = syotyNappula;
                    }

                } catch (Exception e) {}
                numero = 1;
            }
            
        }
        
        return siirrot;
    }
    
    public static String mahdollisetQ(int i) {
        
        String siirrot = "";
        String syotyNappula = "";
        int a = i/8;
        int b = i%8;
        int numero = 1;
        for (int j = -1; j <= 1; j++) {
            for (int w = -1; w <= 1; w++) {
                if (j != 0 || w != 0) {
                    try {

        /* On paljon koodin toistoa, jonka voi myöhemmin laittaa erilliseksi metodiksi.
         */                
                        char tyhja = ' ';
                        while (tyhja == shakkiLauta[a+numero*j][b+numero*w].charAt(0)) {
                            syotyNappula = shakkiLauta[a+numero*j][b+numero*w];
                            shakkiLauta[a][b] = " ";
                            shakkiLauta[a+numero*j][b+numero*w] = "Q";
                            if (onkoShakissa()) {
                                siirrot = siirrot + a + b + (a+numero*j) + (b+numero*w) + syotyNappula;
                            }
                            shakkiLauta[a][b] = "Q";
                            shakkiLauta[a+numero*j][b+numero*w] = syotyNappula;
                            numero++;
                        }
                        if (Character.isLowerCase(shakkiLauta[a+numero*j][b+numero*w].charAt(0))) {
                            syotyNappula = shakkiLauta[a+numero*j][b+numero*w];
                            shakkiLauta[a][b] = " ";
                            shakkiLauta[a+numero*j][b+numero*w] = "Q";
                            if (onkoShakissa()) {
                                siirrot = siirrot + a + b + (a+numero*j) + (b+numero*w) + syotyNappula;
                            }
                            shakkiLauta[a][b] = "Q";
                            shakkiLauta[a+numero*j][b+numero*w] = syotyNappula;
                        }

                    } catch (Exception e) {}
                    numero = 1;
                }
            }
            
        }
        
        return siirrot;
    }
    
    public static String mahdollisetC(int i) {

        String siirrot = "";
        String syotyNappula = "";
        int a = i/8;
        int b = i%8;
        for (int j = 0; j < 9; j++) {
            if (j != 4) {
                
    /* TÃ¤mÃ¤ kÃ¤y lÃ¤pi jokaisen kuninkaan liikkumapaikan (8), j==4 ei lasketa sillÃ¤ se on kuninkaan tÃ¤mÃ¤nhetkinen paikka.
     * Liikkuminen sallitaan jos paikka on tyhjÃ¤ tai mustan nappulan hallitsema, eikÃ¤ kuningas joudu shakkiin.           
     */         
                if (((a-1 + j/3) < 0 || (b-1 + j%3) < 0) || ((a-1 + j/3) > 7 || (b-1 + j%3) > 7)) {
                    continue;
                }

                String liikkumapaikka = shakkiLauta[a-1 + j/3][b-1 + j%3];
                char tyhja = ' ';
                
                if (Character.isLowerCase(liikkumapaikka.charAt(0)) || tyhja == (liikkumapaikka.charAt(0))) {
                    syotyNappula = liikkumapaikka;
                    shakkiLauta[a][b] = " ";
                    shakkiLauta[a-1 + j/3][b-1 + j%3] = "C";
                    if (onkoShakissa()) {
                        siirrot = siirrot + a + b + (a-1 + j/3) + (b-1 + j%3) + syotyNappula;
                    }
                    shakkiLauta[a][b] = "C";
                    shakkiLauta[a-1 + j/3][b-1 + j%3] = syotyNappula;
                }

            }
            
        }

    /* PitÃ¤Ã¤ lisÃ¤tÃ¤ linnoittautuminen.
     */                        
        return siirrot;
    }

    public static boolean onkoShakissa() {
        return true;
    }   
}
