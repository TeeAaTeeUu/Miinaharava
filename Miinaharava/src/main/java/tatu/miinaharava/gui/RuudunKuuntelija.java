package tatu.miinaharava.gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import tatu.miinaharava.logiikka.Pelilauta;

/**
 * Kuuntelee käyttäjän hiireneleitä ja reagoi niihin sen mukaan kuin on oletettavaa Ristinollalle. Käyttää hyväkseen Pelilautaa ja PelikenttaGUIta.
 */
public class RuudunKuuntelija extends MouseAdapter {

    private final Pelilauta pelilauta;
    private final PelikenttaGUI gui;

    public RuudunKuuntelija(Pelilauta pelilauta, PelikenttaGUI gui) {
        this.pelilauta = pelilauta;
        this.gui = gui;
    }

    RuudunKuuntelija() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent klik) {
        RuutuGUI painettuRuutu = (RuutuGUI) klik.getSource();

        if (SwingUtilities.isRightMouseButton(klik) == true) {
            pelilauta.merkkaaRuutu(painettuRuutu.palautaRivi(), painettuRuutu.palautaMoneskoRivilla());
            painettuRuutu.setText("m");
        } else {
            if (pelilauta.onkoPeliAlkanut() == false) {
                pelilauta.miinoitaRuudukko(pelilauta.miinojenMaara(), painettuRuutu.palautaRivi(), painettuRuutu.palautaMoneskoRivilla());
                pelilauta.asetaPeliAlkaneeksi();
            }

            if (pelilauta.onkoRuutuMerkattu(painettuRuutu.palautaRivi(), painettuRuutu.palautaMoneskoRivilla()) == false) {
                pelilauta.avaaRuutu(painettuRuutu.palautaRivi(), painettuRuutu.palautaMoneskoRivilla());
                painettuRuutu.setOpaque(true);
                painettuRuutu.setBorder(null);
            } else {
                pelilauta.merkkaaRuutu(painettuRuutu.palautaRivi(), painettuRuutu.palautaMoneskoRivilla());
                painettuRuutu.setText("");
            }

            gui.paivitaPelikentta();

            if (pelilauta.onkoMiinaAvattu() == true) {
                JOptionPane.showMessageDialog(null, "Osuit miinaan, hävisit!");
            }
        }
    }
}
