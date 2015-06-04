
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import shakki.shakkiohjelma.ohjelmalogiikka.Logiikka;


public class LogiikkaTest {
    String siirrot = "";
    
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
        siirrot = siirrot + Logiikka.mahdollisetSiirrot();
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
    public void kuningasLiikkeetAloitusTilanteessa() {
        String kuningasLiikkeet = Logiikka.mahdollisetC(60);
        assertEquals("", kuningasLiikkeet);
    }
    
    @Test
    public void kuningasLiikkeetTyhjassaTilassa() {
        String kuningasLiikkeet = Logiikka.mahdollisetC(35);
        assertEquals("4332 4333 4334 4342 4344 4352 4353 4354 ", kuningasLiikkeet);
    }
    
    @Test
    public void kuningasLiikkeetLaudanLaidassa() {
        String kuningasLiikkeet = Logiikka.mahdollisetC(32);
        assertEquals("4030 4031 4041 4050 4051 ", kuningasLiikkeet);
    }
    
    @Test
    public void kuningasNappuloidenSyominen() {
        String kuningasLiikkeet = Logiikka.mahdollisetC(16);
        assertEquals("2010p2011p2021 2030 2031 ", kuningasLiikkeet);
    }
    
    /* Lisään myöhemmin enemmän testejä.
     */
    
}
