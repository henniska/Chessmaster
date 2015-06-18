
package shakki.shakkiohjelma.kayttoliittyma;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing. *;


public class Käyttöliittymä implements Runnable {
    private JFrame frame;
    private Piirtoalusta p;

    

    @Override
    public void run() {
        frame = new JFrame("Chessmaster");

        frame.setPreferredSize(new Dimension(280, 300));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }
    
    public void luoKomponentit(Container container) {
        p = new Piirtoalusta();
        container.add(p);
        
        
    }


    public JFrame getFrame() {
        return frame;
    }
}
