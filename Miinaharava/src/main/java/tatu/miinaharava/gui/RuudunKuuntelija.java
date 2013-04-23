package tatu.miinaharava.gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import tatu.miinaharava.logiikka.Pelilauta;

/**
 * Kuuntelee käyttäjän hiireneleitä ja reagoi niihin sen mukaan kuin on
 * oletettavaa Ristinollalle. Käyttää hyväkseen Pelilautaa ja PelikenttaGUIta.
 */
public class RuudunKuuntelija extends MouseAdapter {

    private Pelilauta pelilauta;
    private PelikenttaGUI gui;

    public RuudunKuuntelija(Pelilauta pelilauta, PelikenttaGUI gui) {
        this.pelilauta = pelilauta;
        this.gui = gui;
    }

    RuudunKuuntelija() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent klik) {
        if (pelilauta.onkoMiinaAvattu() == true) {
            JOptionPane.showMessageDialog(null, "Osuit miinaan, hävisit!");
        } else if (pelilauta.OnkoPeliVoitettu() == true) {
            JOptionPane.showMessageDialog(null, "Voitit pelin!");
        } else {
            RuutuGUI painettuRuutu = (RuutuGUI) klik.getSource();

            if (SwingUtilities.isRightMouseButton(klik) == true) {
                pelilauta.merkkaaRuutu(painettuRuutu.palautaRivi(), painettuRuutu.palautaMoneskoRivilla());
                if (pelilauta.onkoRuutuMerkattu(painettuRuutu.palautaRivi(), painettuRuutu.palautaMoneskoRivilla())) {
                    painettuRuutu.setText("m");
                } else {
                    painettuRuutu.setText("");
                }
            } else {
                if (pelilauta.onkoPeliAlkanut() == false) {
                    pelilauta.miinoitaRuudukko(pelilauta.miinojenMaara(), painettuRuutu.palautaRivi(), painettuRuutu.palautaMoneskoRivilla());
                    pelilauta.asetaPeliAlkaneeksi();
                }

                if (pelilauta.onkoRuutuMerkattu(painettuRuutu.palautaRivi(), painettuRuutu.palautaMoneskoRivilla()) == false) {
                    pelilauta.avaaRuutu(painettuRuutu.palautaRivi(), painettuRuutu.palautaMoneskoRivilla());
                    painettuRuutu.setOpaque(true);
                    painettuRuutu.setBorder(null);
                }

                if (pelilauta.onkoMiinaAvattu() == true) {
                    JOptionPane.showMessageDialog(null, "Osuit miinaan, hävisit!");
                    JOptionPane.showMessageDialog(null, "Koska hävisit, aloitamme uuden pelin!");
                    gui = new PelikenttaGUI();
                    pelilauta = gui.palautaPelilauta();
                }
                if (pelilauta.OnkoPeliVoitettu() == true) {
                    JOptionPane.showMessageDialog(null, "Voitit pelin!");
                }

                gui.paivitaPelikentta();
            }
        }
    }
}
