
package shakki.shakkiohjelma.ohjelmalogiikka;
import java.util.*;

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
    
    static int kuninkaanPaikkaIso = 60, kuninkaanPaikkaPieni = 5;
    
    public void vaihdaLauta (String[][] uusiLauta) {
        
        shakkiLauta = uusiLauta;
        char kuningas = 'C';
        kuninkaanPaikkaIso = 0;
        kuninkaanPaikkaPieni = 0;
        while (kuningas != shakkiLauta[kuninkaanPaikkaIso/8][kuninkaanPaikkaIso%8].charAt(0)) {kuninkaanPaikkaIso++;}  
    }
    
    
    public static void teeSiirto (String siirto) {
        if (siirto.charAt(4)!= 'P') {
            
            shakkiLauta[Character.getNumericValue(siirto.charAt(2))][Character.getNumericValue(siirto.charAt(3))]
                    = shakkiLauta[Character.getNumericValue(siirto.charAt(0))][Character.getNumericValue(siirto.charAt(1))];
            shakkiLauta[Character.getNumericValue(siirto.charAt(0))][Character.getNumericValue(siirto.charAt(1))] = " ";
            
        } else {
            /* Sotilaan korottaminen
             */
            shakkiLauta[1][Character.getNumericValue(siirto.charAt(0))] = " ";
            shakkiLauta[0][Character.getNumericValue(siirto.charAt(1))] = String.valueOf(siirto.charAt(3));
           
        }
    }
    
    public static void otaTakaisinSiirto (String siirto) {
        if (siirto.charAt(4)!= 'P') {
            
            shakkiLauta[Character.getNumericValue(siirto.charAt(0))][Character.getNumericValue(siirto.charAt(1))]
                    = shakkiLauta[Character.getNumericValue(siirto.charAt(2))][Character.getNumericValue(siirto.charAt(3))];
            shakkiLauta[Character.getNumericValue(siirto.charAt(2))][Character.getNumericValue(siirto.charAt(3))]
                    = String.valueOf(siirto.charAt(4));
            
        } else {
            /* Sotilaan korottaminen
             */
            shakkiLauta[1][Character.getNumericValue(siirto.charAt(0))] = "P";
            shakkiLauta[0][Character.getNumericValue(siirto.charAt(1))] = String.valueOf(siirto.charAt(2));
           
        }
    }
    
    public static void kaannaLauta () {
        
    }
    
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
        String siirrot = "";
        String syotyNappula = "";
        char tyhja = ' ';
        int a = i/8;
        int b = i%8;
        for (int j = -1; j <= 1; j+=2) {
            /* Syöminen
             */
            try {
                if (Character.isLowerCase(shakkiLauta[a-1][b+j].charAt(0)) && i>=16) {
                    syotyNappula = shakkiLauta[a-1][b+j];
                    shakkiLauta[a][b] = " ";
                    shakkiLauta[a-1][b+j] = "P";
                    if (onkoKuningasTurvassa()) {
                        siirrot = siirrot + a + b + (a-1) + (b+j) + syotyNappula;
                    }
                    shakkiLauta[a][b] = "P";
                    shakkiLauta[a-1][b+j] = syotyNappula;
                }
                    
            } catch (Exception e) {}
            /* Sotilaan korottaminen ja samalla nappulan syöminen
             */
            try {
                if (tyhja == shakkiLauta[a-1][b].charAt(0) && i<16) {
                    String[] mahdolliset = {"Q", "K", "B", "R"};
                    for (int w = 0; w < 4; w++) {
                        syotyNappula = shakkiLauta[a-1][b];
                        shakkiLauta[a][b] = " ";
                        shakkiLauta[a-1][b] = mahdolliset[w];
                        if (onkoKuningasTurvassa()) {
                            /* Tarvitaan lisää tietoa kuin ennen
                             * Formaatti on nyt: rivi1, rivi2, syöty nappula, uusi nappula, P niinkuin promootio
                             */
                            siirrot = siirrot + b + b + syotyNappula + mahdolliset[w] + "P";
                        }
                        shakkiLauta[a][b] = "P";
                        shakkiLauta[a-1][b] = syotyNappula;
                    }
                }
                    
            } catch (Exception e) {}
            /* Sotilaan korottaminen ilman syömistä
             */        
            try {
                if (Character.isLowerCase(shakkiLauta[a-1][b+j].charAt(0)) && i<16) {
                    String[] mahdolliset = {"Q", "K", "B", "R"};
                    for (int w = 0; w < 4; w++) {
                        syotyNappula = shakkiLauta[a-1][b+j];
                        shakkiLauta[a][b] = " ";
                        shakkiLauta[a-1][b+j] = mahdolliset[w];
                        if (onkoKuningasTurvassa()) {
                            /* Tarvitaan lisää tietoa kuin ennen
                             * Formaatti on nyt: rivi1, rivi2, syöty nappula, uusi nappula, P niinkuin promootio
                             */
                            siirrot = siirrot + b + (b+j) + syotyNappula + mahdolliset[w] + "P";
                        }
                        shakkiLauta[a][b] = "P";
                        shakkiLauta[a-1][b+j] = syotyNappula;
                    }
                }
                    
            } catch (Exception e) {}
        }
        /* Siirtyminen ylöspäin yhden ruudun verran
         */        
        try {
            if (tyhja == shakkiLauta[a-1][b].charAt(0) && i>=16) {
                syotyNappula = shakkiLauta[a-1][b];
                shakkiLauta[a][b] = " ";
                shakkiLauta[a-1][b] = "P";
                if (onkoKuningasTurvassa()) {
                    siirrot = siirrot + a + b + (a-1) + b + syotyNappula;
                }
                shakkiLauta[a][b] = "P";
                shakkiLauta[a-1][b] = syotyNappula;
            }

        } catch (Exception e) {}
        /* Siirtyminen ylöspäin kahden ruudun verran
         */        
        try {
            if (tyhja == shakkiLauta[a-1][b].charAt(0) && tyhja == shakkiLauta[a-2][b].charAt(0) && i>=48) {
                syotyNappula = shakkiLauta[a-2][b];
                shakkiLauta[a][b] = " ";
                shakkiLauta[a-2][b] = "P";
                if (onkoKuningasTurvassa()) {
                    siirrot = siirrot + a + b + (a-2) + b + syotyNappula;
                }
                shakkiLauta[a][b] = "P";
                shakkiLauta[a-2][b] = syotyNappula;
            }

        } catch (Exception e) {}
        
        return siirrot;
    }
    
    public static String mahdollisetK(int i) {
        String siirrot = "";
        String syotyNappula = "";
        int a = i/8;
        int b = i%8;
        for (int j = -1; j <= 1; j+=2) {
            for (int w = -1; w <= 1; w+=2) {
                try {
                    char tyhja = ' ';
                    if (Character.isLowerCase(shakkiLauta[a+j][b+w*2].charAt(0)) || tyhja == shakkiLauta[a+j][b+w*2].charAt(0)) {
                        syotyNappula = shakkiLauta[a+j][b+w*2];
                        shakkiLauta[a][b] = " ";
                        shakkiLauta[a+j][b+w*2] = "K";
                        if (onkoKuningasTurvassa()) {
                            siirrot = siirrot + a + b + (a+j) + (b+w*2) + syotyNappula;
                        }
                        shakkiLauta[a][b] = "K";
                        shakkiLauta[a+j][b+w*2] = syotyNappula;
                    }
                } catch (Exception e) {}
                
                try {
                    char tyhja = ' ';
                    if (Character.isLowerCase(shakkiLauta[a+j*2][b+w].charAt(0)) || tyhja == shakkiLauta[a+j*2][b+w].charAt(0)) {
                        syotyNappula = shakkiLauta[a+j*2][b+w];
                        shakkiLauta[a][b] = " ";
                        shakkiLauta[a+j*2][b+w] = "K";
                        if (onkoKuningasTurvassa()) {
                            siirrot = siirrot + a + b + (a+j*2) + (b+w) + syotyNappula;
                        }
                        shakkiLauta[a][b] = "K";
                        shakkiLauta[a+j*2][b+w] = syotyNappula;
                    }
                } catch (Exception e) {}

            }
            
        }
        return siirrot;
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
                            if (onkoKuningasTurvassa()) {
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
                            if (onkoKuningasTurvassa()) {
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
                        if (onkoKuningasTurvassa()) {
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
                        if (onkoKuningasTurvassa()) {
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
                            if (onkoKuningasTurvassa()) {
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
                            if (onkoKuningasTurvassa()) {
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
                    int kuningasNumero = kuninkaanPaikkaIso;
                    kuninkaanPaikkaIso = i + (j/3)*8 + j%3 -9;
                    if (onkoKuningasTurvassa()) {
                        siirrot = siirrot + a + b + (a-1 + j/3) + (b-1 + j%3) + syotyNappula;
                    }
                    shakkiLauta[a][b] = "C";
                    shakkiLauta[a-1 + j/3][b-1 + j%3] = syotyNappula;
                    kuninkaanPaikkaIso = kuningasNumero;
                }

            }
            
        }

    /* PitÃ¤Ã¤ lisÃ¤tÃ¤ linnoittautuminen.
     */                        
        return siirrot;
    }

    public static boolean onkoKuningasTurvassa() {
        int numero = 1;
        char tyhja = ' ';
        char lahetti = 'b';
        char torni = 'r';
        char ratsu = 'k';
        char kuningatar = 'q';
        char sotilas = 'p';
        char kuningas = 'c';
        
        /* Lähetti + Kuningatar
         */
        for (int i = -1; i <= 1; i+=2) {
            for (int j = -1; j <= 1; j+=2) {
                try {
                    while (tyhja == shakkiLauta[kuninkaanPaikkaIso/8 + numero*i][kuninkaanPaikkaIso%8 + numero*j].charAt(0)) {numero++;} {
                        if (lahetti == shakkiLauta[kuninkaanPaikkaIso/8 + numero*i][kuninkaanPaikkaIso%8 + numero*j].charAt(0)) {
                            return false;
                        }
                        if (kuningatar == shakkiLauta[kuninkaanPaikkaIso/8 + numero*i][kuninkaanPaikkaIso%8 + numero*j].charAt(0)) {
                            return false;
                        }
                    }
                } catch (Exception e) {}
                numero = 1;
            }
        }
        /* Torni + Kuningatar
         */
        for (int i = -1; i <= 1; i+=2) {
            try {
                while (tyhja == shakkiLauta[kuninkaanPaikkaIso/8][kuninkaanPaikkaIso%8 + numero*i].charAt(0)) {numero++;} {
                    if (torni == shakkiLauta[kuninkaanPaikkaIso/8][kuninkaanPaikkaIso%8 + numero*i].charAt(0)) {
                        return false;
                    }
                    if (kuningatar == shakkiLauta[kuninkaanPaikkaIso/8][kuninkaanPaikkaIso%8 + numero*i].charAt(0)) {
                        return false;
                    }
                }
            } catch (Exception e) {}
            numero = 1;
            try {
                while (tyhja == shakkiLauta[kuninkaanPaikkaIso/8+ numero*i][kuninkaanPaikkaIso%8].charAt(0)) {numero++;} {
                    if (torni == shakkiLauta[kuninkaanPaikkaIso/8 + numero*i][kuninkaanPaikkaIso%8].charAt(0)) {
                        return false;
                    }
                    if (kuningatar == shakkiLauta[kuninkaanPaikkaIso/8 + numero*i][kuninkaanPaikkaIso%8].charAt(0)) {
                        return false;
                    }
                }
            } catch (Exception e) {}
            numero = 1;
        }
        /* Ratsu
         */
        for (int i = -1; i <= 1; i+=2) {
            for (int j = -1; j <= 1; j+=2) {
                try { 
                    if (ratsu == shakkiLauta[kuninkaanPaikkaIso/8 + i][kuninkaanPaikkaIso%8 + j*2].charAt(0)) {
                        return false;
                    }
                } catch (Exception e) {}
                try { 
                    if (ratsu == shakkiLauta[kuninkaanPaikkaIso/8 + i*2][kuninkaanPaikkaIso%8 + j].charAt(0)) {
                        return false;
                    }
                } catch (Exception e) {}
            }
        }
        /* Sotilas
         */
        if (kuninkaanPaikkaIso >= 16) {
            try { 
                if (sotilas == shakkiLauta[kuninkaanPaikkaIso/8 - 1][kuninkaanPaikkaIso%8 - 1].charAt(0)) {
                    return false;
                }
            } catch (Exception e) {}
            try { 
                if (sotilas == shakkiLauta[kuninkaanPaikkaIso/8 - 1][kuninkaanPaikkaIso%8 + 1].charAt(0)) {
                    return false;
                }
            } catch (Exception e) {}
        }
        /* Kuningas
         */
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i!=0 || j!=0) {
                    try { 
                        if (kuningas == shakkiLauta[kuninkaanPaikkaIso/8 + i][kuninkaanPaikkaIso%8 + j].charAt(0)) {
                            return false;
                        }
                    } catch (Exception e) {}                   
                }           
            }
        }
        
        return true;
    }   
}
