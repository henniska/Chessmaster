
package shakki.shakkiohjelma.kayttoliittyma;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event. *;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import shakki.shakkiohjelma.ohjelmalogiikka.Logiikka;

public class Piirtoalusta extends JPanel implements MouseListener, MouseMotionListener {

    Image image;
    static int hiiriX;
    static int hiiriY;
    static int uusiHiiriX;
    static int uusiHiiriY;
    static int x = 0;
    static int y = 0;
    static int ruudunKoko = 32;
    private PeliLoppui l;
    
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.blue);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        
        for (int i = 0; i < 64; i+=2) {
            
           /* Jotta shakkilauta muuttaa kokoaan jos Jframe kokoa muutetaan
            */
            g.setColor(new Color(255, 200, 100));
            g.fillRect((i%8 + (i/8)%2)*ruudunKoko, (i/8)*ruudunKoko, ruudunKoko, ruudunKoko);
            g.setColor(new Color(150, 50, 30));
            g.fillRect(((i+1)%8 - ((i+1)/8)%2)*ruudunKoko, ((i+1)/8)*ruudunKoko, ruudunKoko, ruudunKoko);
            
        }

       /* Kyseisessä kuvassa on kaikki pelinappulat rivissä
        */        
        ImageIcon s = new ImageIcon("image/Chesspieces.png");
        image = s.getImage();
        
       /* Pelinappuloiden kuvat saadaan rajaamalla ne aiemmasta kuvasta
        * Pelaajan vuoro määrittelee laitetaanko mustat vai valkoiset nappulat laudan 'alapuolelle'
        */        
        int z;
        if (Logiikka.valkoisenVuoro) {
            z = 3;
        } else {
            z = 4;
        }
        
        for (int i = 0; i < 64; i++) {
            int j = -1;
            int k = -1;
            switch (Logiikka.shakkiLauta[i/8][i%8]) {
                
                case "P": j=5; k=z%2;
                    break;
                case "p": j=5; k=z%3;
                    break;
                case "R": j=2; k=z%2;
                    break;
                case "r": j=2; k=z%3;
                    break;
                case "K": j=3; k=z%2;
                    break;
                case "k": j=3; k=z%3;
                    break;
                case "B": j=4; k=z%2;
                    break;
                case "b": j=4; k=z%3;
                    break;
                case "Q": j=1; k=z%2;
                    break;
                case "q": j=1; k=z%3;
                    break;
                case "C": j=0; k=z%2;
                    break;
                case "c": j=0; k=z%3;
                    break;
            }
            if (j!=-1 || k!=-1) {
                g.drawImage(image, (i%8)*ruudunKoko, (i/8)*ruudunKoko, (i%8+1)*ruudunKoko, (i/8+1)*ruudunKoko, j*64, k*64, (j+1)*64, (k+1)*64,this);
            }   
        }
        
    }   

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
       /* Jos on laudan sisällä
        */        
        if (e.getX()<8*ruudunKoko && e.getY()<8*ruudunKoko) {
            hiiriX = e.getX();
            hiiriY = e.getY();
            repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getX()<8*ruudunKoko && e.getY()<8*ruudunKoko) {
            uusiHiiriX = e.getX();
            uusiHiiriY = e.getY();
            if (e.getButton() == MouseEvent.BUTTON1) {
                
                String hiirenSiirto;
                if (uusiHiiriY/ruudunKoko == 0 && hiiriY/ruudunKoko == 1 && 'P' == Logiikka.shakkiLauta[hiiriY/ruudunKoko][hiiriX/ruudunKoko].charAt(0)) {
                   /* Sotilaan korottaminen
                    */
                    hiirenSiirto = "" + hiiriX/ruudunKoko + uusiHiiriX/ruudunKoko + Logiikka.shakkiLauta[uusiHiiriY/ruudunKoko][uusiHiiriX/ruudunKoko] + "QP";
                } else {
                   /* Normaali liike
                    */     
                    hiirenSiirto = "" + hiiriY/ruudunKoko + hiiriX/ruudunKoko + uusiHiiriY/ruudunKoko + uusiHiiriX/ruudunKoko + Logiikka.shakkiLauta[uusiHiiriY/ruudunKoko][uusiHiiriX/ruudunKoko];
                }
                String pelaajanMahdollisetSiirrot = Logiikka.mahdollisetSiirrot();
                if (pelaajanMahdollisetSiirrot.replaceAll(hiirenSiirto, "").length() < pelaajanMahdollisetSiirrot.length()) {
                   /* Onko laillinen siirto
                    * Myös täytyy nollata hiiriarvot jottei metodia kutsuta useita kertoja
                    */
                    
                    hiiriX = 0;
                    hiiriY = 0;
                    uusiHiiriX = 0;
                    uusiHiiriY = 0;
                    Logiikka.teeSiirto(hiirenSiirto);
                    repaint();

                    if (Logiikka.mahdollisetSiirrot().isEmpty()) {
                        l = new PeliLoppui();
                        l.run();
                    }
                    
                    repaint();
                }
                
                
            }
            
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
    
}
