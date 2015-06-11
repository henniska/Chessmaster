
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import shakki.shakkiohjelma.ohjelmalogiikka.Logiikka;


public class LogiikkaTest {
    Logiikka logiikka = new Logiikka();
            
    
    public LogiikkaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void liikkeetAloitusTilanteessa() {
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
        String liikkeet = logiikka.mahdollisetSiirrot();
        assertEquals("6050 6040 6151 6141 6252 6242 6353 6343 6454 6444 6555 6545 6656 6646 6757 6747 7150 7152 7655 7657 ", liikkeet);
    }
    
    @Test
    public void kuningasLiikkeetTyhjassaTilassa() {
        String testiLauta[][] = {
        {" ", " ", " ", " ", " ", " ", " ", " "},
        {" ", " ", " ", " ", " ", " ", " ", " "},
        {" ", " ", " ", " ", " ", " ", " ", " "},
        {" ", " ", " ", " ", " ", " ", " ", " "},
        {" ", " ", " ", "C", " ", " ", " ", " "},
        {" ", " ", " ", " ", " ", " ", " ", " "},
        {" ", " ", " ", " ", " ", " ", " ", " "},
        {" ", " ", " ", " ", " ", " ", " ", " "}};
        logiikka.vaihdaLauta(testiLauta);
        String kuningasLiikkeet = logiikka.mahdollisetC(35);
        assertEquals("4332 4333 4334 4342 4344 4352 4353 4354 ", kuningasLiikkeet);
    }
    
    @Test
    public void kuningasLiikkeetLaudanLaidassa() {
        String testiLauta[][] = {
        {" ", " ", " ", " ", " ", " ", " ", " "},
        {" ", " ", " ", " ", " ", " ", " ", " "},
        {" ", " ", " ", " ", " ", " ", " ", " "},
        {" ", " ", " ", " ", " ", " ", " ", " "},
        {"C", " ", " ", " ", " ", " ", " ", " "},
        {" ", " ", " ", " ", " ", " ", " ", " "},
        {" ", " ", " ", " ", " ", " ", " ", " "},
        {" ", " ", " ", " ", " ", " ", " ", " "}};
        logiikka.vaihdaLauta(testiLauta);
        String kuningasLiikkeet = logiikka.mahdollisetSiirrot();
        assertEquals("4030 4031 4041 4050 4051 ", kuningasLiikkeet);
    }
    
    @Test
    public void kuningasNappuloidenSyominen() {
        String testiLauta[][] = {
        {" ", " ", " ", " ", " ", " ", " ", " "},
        {"p", "p", " ", " ", " ", " ", " ", " "},
        {"C", " ", " ", " ", " ", " ", " ", " "},
        {" ", " ", " ", " ", " ", " ", " ", " "},
        {" ", " ", " ", " ", " ", " ", " ", " "},
        {" ", " ", " ", " ", " ", " ", " ", " "},
        {" ", " ", " ", " ", " ", " ", " ", " "},
        {" ", " ", " ", " ", " ", " ", " ", " "}};
        logiikka.vaihdaLauta(testiLauta);
        String kuningasLiikkeet = logiikka.mahdollisetC(16);
        assertEquals("2010p2011p2030 2031 ", kuningasLiikkeet);
    }
    
    @Test
    public void onkoKuningasTurvassa() {
        String testiLauta[][] = {
        {" ", " ", " ", " ", " ", " ", " ", " "},
        {" ", " ", " ", " ", " ", "b", " ", " "},
        {" ", " ", " ", " ", " ", " ", " ", " "},
        {" ", " ", " ", "C", " ", " ", " ", " "},
        {" ", " ", " ", " ", " ", " ", " ", " "},
        {" ", " ", " ", " ", " ", " ", " ", " "},
        {" ", " ", " ", " ", " ", " ", " ", " "},
        {" ", " ", " ", " ", " ", " ", " ", " "}};
        logiikka.vaihdaLauta(testiLauta);
        boolean vastaus = logiikka.onkoKuningasTurvassa();
        assertEquals(false, vastaus);
    }
    
    
    /* Lisään myöhemmin enemmän testejä.
     */
    
}
