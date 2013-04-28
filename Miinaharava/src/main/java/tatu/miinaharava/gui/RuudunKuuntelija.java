package tatu.miinaharava.gui;

import java.awt.HeadlessException;
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
    private RuutuGUI painettuRuutu;

    /**
     * Luo ruudunkuuntelijan, joka toimii pelilogiikan mukaisesti käyttäen hyödykseen sille annettuap pelilautaa. PelikenttäGUI:ta käyttää päivitysten pakottamiseen.
     * @param pelilauta
     * @param gui
     */
    public RuudunKuuntelija(Pelilauta pelilauta, PelikenttaGUI gui) {
        this.pelilauta = pelilauta;
        this.gui = gui;
    }

    /**
     *Tekee sen kaiken, mitä tarvitaan, kun jotain nappia on painettu.
     * @param klik
     */
    @Override
    public void mousePressed(MouseEvent klik) {
        this.pelinPaattymisTarkistus();

        if (onkoPeliPaattynyt() == false) {

            this.painettuRuutu = this.palautaPainettuRuutu(klik);

            if (this.onkoOikeaaPainettu(klik) == true) {
                merkkaaPainettuRuutu();
            } else {
                vasentaNappiaPainettu();
            }
        }
        gui.paivitaPelikentta();

        this.pelinPaattymisTarkistus();
    }

    private void vasentaNappiaPainettu() throws HeadlessException {
        if (pelilauta.onkoPeliAlkanut() == false) {
            miinoitaJaAloita();
        }

        if (onkoPainettuRuutuMerkattu() == false) {
            vasenNappiEiMerkattu();
        }
    }

    private void pelinPaattymisTarkistus() throws HeadlessException {
        if (onkoPeliPaattynyt() == true) {
            if (pelilauta.onkoMiinaAvattu() == true) {
                miinaAvattu();
            } else if (pelilauta.OnkoPeliVoitettu() == true) {
                peliVoitettu();
            }
        }
    }

    private boolean onkoOikeaaPainettu(MouseEvent klik) {
        return SwingUtilities.isRightMouseButton(klik);
    }

    private RuutuGUI palautaPainettuRuutu(MouseEvent klik) {
        return (RuutuGUI) klik.getSource();
    }

    private void miinoitaJaAloita() {
        pelilauta.miinoitaRuudukko(pelilauta.miinojenMaara(), mitaRiviaPainettu(), mitaSarakettaPainettu());
        pelilauta.asetaPeliAlkaneeksi();
    }

    private boolean onkoPainettuRuutuMerkattu() {
        return pelilauta.onkoRuutuMerkattu(mitaRiviaPainettu(), mitaSarakettaPainettu());
    }

    private boolean merkkaaPainettuRuutu() {
        return pelilauta.merkkaaRuutu(mitaRiviaPainettu(), mitaSarakettaPainettu());
    }

    private void avaaPainettuRuutu() {
        pelilauta.avaaRuutu(mitaRiviaPainettu(), mitaSarakettaPainettu());
    }

    private void vasenNappiEiMerkattu() {
        if (onkoAvattu() == false) {
            avaaPainettuRuutu();
        }
    }

    private void miinaAvattu() throws HeadlessException {     
        peliHavitty();
        JOptionPane.showMessageDialog(null, "Koska hävisit, aloitamme uuden pelin!");
        gui.tapaItsesi(); // hehe :)
        Main.aloitaPeli(this.pelilauta.ruudukonKorkeus(), this.pelilauta.ruudukonLeveys(), this.pelilauta.miinojenMaara());
    }

    private void peliVoitettu() throws HeadlessException {
        JOptionPane.showMessageDialog(null, "Voitit pelin!");
    }

    private void peliHavitty() throws HeadlessException {
        JOptionPane.showMessageDialog(null, "Osuit miinaan, hävisit!");
    }

    private boolean onkoAvattu() {
        return pelilauta.onkoRuutuAvattu(mitaRiviaPainettu(), mitaSarakettaPainettu());
    }

    private int mitaSarakettaPainettu() {
        return painettuRuutu.palautaMoneskoRivilla();
    }

    private int mitaRiviaPainettu() {
        return painettuRuutu.palautaRivi();
    }

    private boolean onkoPeliPaattynyt() {
        if (pelilauta.onkoMiinaAvattu() || pelilauta.OnkoPeliVoitettu()) {
            return true;
        }
        return false;
    }
}
