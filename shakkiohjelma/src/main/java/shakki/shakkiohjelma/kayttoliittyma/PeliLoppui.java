
package shakki.shakkiohjelma.kayttoliittyma;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing. *;
import shakki.shakkiohjelma.ohjelmalogiikka.Logiikka;

public class PeliLoppui implements Runnable {

    private JFrame shakkimatti;
    private JFrame tasapeli;
    
    @Override
    public void run() {
       /* Avaa uuden JFrame:n riippuen pelin lopputuloksesta
        */        
        String title;
        if (Logiikka.onkoKuningasTurvassa()) {
            title = "Tasapeli";
            tasapeli = new JFrame(title);

            tasapeli.setPreferredSize(new Dimension(500, 60));

            tasapeli.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


            tasapeli.pack();
            tasapeli.setVisible(true);
        } else {
            
            if (Logiikka.valkoisenVuoro) {
                title = "Valkoinen on matissa";
                
            } else {
                title = "Musta on matissa";
            }
            shakkimatti = new JFrame(title);

            shakkimatti.setPreferredSize(new Dimension(500, 60));

            shakkimatti.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


            shakkimatti.pack();
            shakkimatti.setVisible(true);
        }
        
    }
    
}
