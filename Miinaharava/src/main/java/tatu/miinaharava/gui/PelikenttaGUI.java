package tatu.miinaharava.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import tatu.miinaharava.logiikka.Pelilauta;

/**
 * Sisältää pelikenttään liittyvät graafiset ominaisuudet, käyttää hyväkseen
 * luokkaa Pelilauta ja RuutuGUI.
 */
public final class PelikenttaGUI extends JFrame {

    private Pelilauta pelilauta;
    private JPanel pelikentta;
    private GridLayout peliRuudukko;
    private RuutuGUI[][] ruudut;
    private RuudunKuuntelija ruudunKuuntelija;


    /**
     * Pyytää ValikkoGUI:ta kyselemään pelilaudan kokoa ja laittaa homman
     * pyörimään sen saamien lukujen mukaisesti.
     */
    public PelikenttaGUI() {
        this.pelilauta = new ValikkoGUI().kyseleTiedot().luoPelilauta();

        this.valmistelePeli();
    }
    
    public PelikenttaGUI(int korkeus, int leveys, int miinoja) {
        this.pelilauta = new Pelilauta(korkeus, leveys, miinoja, false);

        this.valmistelePeli();
    }

    private void valmistelePeli() {
        this.ruudunKuuntelija = new RuudunKuuntelija(this.pelilauta, this);
        
        new ValikkoGUI().luoValikot(this);
        this.asetaRuudukko();
        this.lisataanRuudutRuudukkoon();
        this.asetaPakollisuuksia();
    }

    private void asetaIkkunanAlkusijainti() {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        int x = (dim.width) / 4;
        int y = (dim.height) / 5;

        this.setLocation(x, y);
    }

    /**
     * Tämä on se metodi, jota ruudunkuuntelija kutsuu aina tehtyään jotain,
     * jotta käyttäjälle näkyvä grafiikka päivitettäisiin.
     */
    public void paivitaPelikentta() {
        for (int kasiteltavaRivi = 1; kasiteltavaRivi <= pelilaudanKorkeus(); kasiteltavaRivi++) {
            for (int kasiteltavaRuutuRivilla = 1; kasiteltavaRuutuRivilla <= pelilaudanLeveys(); kasiteltavaRuutuRivilla++) {

                this.tarkistaOnkoRuutuMerkattu(kasiteltavaRivi, kasiteltavaRuutuRivilla);
                this.tarkistaOnkoRuutuAvattu(kasiteltavaRivi, kasiteltavaRuutuRivilla);

                this.tarkistaOnkoPeliPaattynyt(kasiteltavaRivi, kasiteltavaRuutuRivilla);
            }
        }
        this.pelikentta.updateUI();
    }

    private void tarkistaOnkoYmparillaOlevienMiinojenMaaraAnnettu(int kasiteltavaRivi, int kasiteltavaRuutuRivilla) {
        if (this.pelilauta.montakoMiinaaRuudunYmparilla(kasiteltavaRivi, kasiteltavaRuutuRivilla) >= 1) {
            int miinojenMaara = this.pelilauta.montakoMiinaaRuudunYmparilla(kasiteltavaRivi, kasiteltavaRuutuRivilla);
            String tuloste = palautaNumero(miinojenMaara);
            this.ruudut[kasiteltavaRivi][kasiteltavaRuutuRivilla].setText(tuloste);
            this.ruudut[kasiteltavaRivi][kasiteltavaRuutuRivilla].setOpaque(true);
            this.ruudut[kasiteltavaRivi][kasiteltavaRuutuRivilla].setBorder(null);
        } else {
            if (this.pelilauta.onkoRuutuMiinoitettu(kasiteltavaRivi, kasiteltavaRuutuRivilla) == false) {
                this.ruudut[kasiteltavaRivi][kasiteltavaRuutuRivilla].setVisible(false);
            }
        }
    }

    private void tarkistaOnkoRuutuAvattu(int kasiteltavaRivi, int kasiteltavaRuutuRivilla) {
        if (this.pelilauta.onkoRuutuAvattu(kasiteltavaRivi, kasiteltavaRuutuRivilla) == true) {
            this.tarkistaOnkoYmparillaOlevienMiinojenMaaraAnnettu(kasiteltavaRivi, kasiteltavaRuutuRivilla);
        }
    }

    private void tarkistaOnkoRuutuMerkattu(int kasiteltavaRivi, int kasiteltavaRuutuRivilla) {
        if (this.pelilauta.onkoRuutuMerkattu(kasiteltavaRivi, kasiteltavaRuutuRivilla) == true) {
            this.ruudut[kasiteltavaRivi][kasiteltavaRuutuRivilla].setText("m");
        } else {
            this.ruudut[kasiteltavaRivi][kasiteltavaRuutuRivilla].setText(null);
        }
    }

    private void asetaPakollisuuksia() {
        setLayout(new BorderLayout());
        add("Center", pelikentta);
        setTitle("Miinaharava-peli");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        this.asetaIkkunanAlkusijainti();

        pelikentta.doLayout();
        pack();
        setVisible(true);
    }

    private void asetaRuudukko() {
        this.pelikentta = new JPanel();

        this.peliRuudukko = new GridLayout(pelilaudanKorkeus(), pelilaudanLeveys()); //tehdään gridlayout ruudukon mukaan, jolloin ruutuja vain helppo lisäillä
        this.pelikentta.setLayout(this.peliRuudukko);

        this.ruudut = new RuutuGUI[pelilaudanKorkeus() + 1][pelilaudanLeveys() + 1]; //luodaan ruudukun kokoinen taulukko ruutu-olioita varten
    }

    private void tarkistaOnkoRuutuMiinoitettu(int kasiteltavaRivi, int kasiteltavaRuutuRivilla) {
        if (this.pelilauta.onkoRuutuMiinoitettu(kasiteltavaRivi, kasiteltavaRuutuRivilla) == true) {
            this.ruudut[kasiteltavaRivi][kasiteltavaRuutuRivilla].setText("X");
        }
    }

    public Pelilauta palautaPelilauta() {
        return this.pelilauta;
    }

    private void lisataanRuudutRuudukkoon() {
        for (int kasiteltavaRivi = 1; kasiteltavaRivi <= pelilaudanKorkeus(); kasiteltavaRivi++) {
            for (int kasiteltavaRuutuRivilla = 1; kasiteltavaRuutuRivilla <= pelilaudanLeveys(); kasiteltavaRuutuRivilla++) {
                this.ruudut[kasiteltavaRivi][kasiteltavaRuutuRivilla] = luoRuutuGUI(kasiteltavaRivi, kasiteltavaRuutuRivilla);
                lisaaRuutuRuudukkoon(kasiteltavaRivi, kasiteltavaRuutuRivilla);
            }
        }
    }

    private RuutuGUI luoRuutuGUI(int kasiteltavaRivi, int kasiteltavaRuutuRivilla) {
        return new RuutuGUI(kasiteltavaRivi, kasiteltavaRuutuRivilla, pelilauta, this, this.ruudunKuuntelija);
    }

    private Component lisaaRuutuRuudukkoon(int kasiteltavaRivi, int kasiteltavaRuutuRivilla) {
        return this.pelikentta.add(this.ruudut[kasiteltavaRivi][kasiteltavaRuutuRivilla]);
    }

    private int pelilaudanKorkeus() {
        return this.pelilauta.ruudukonKorkeus();
    }

    private int pelilaudanLeveys() {
        return this.pelilauta.ruudukonLeveys();
    }

    public void tapaItsesi() {
        this.dispose();
    }

    private String palautaNumero(int miinojenMaara) {
        String tuloste;
        if (miinojenMaara == 0) {
            tuloste = "";
        } else {
            tuloste = Integer.toString(miinojenMaara);
        }
        return tuloste;
    }

    private void tarkistaOnkoPeliPaattynyt(int kasiteltavaRivi, int kasiteltavaRuutuRivilla) {
        if (this.pelilauta.onkoMiinaAvattu() == true || this.pelilauta.OnkoPeliVoitettu()) {
            tarkistaOnkoRuutuMiinoitettu(kasiteltavaRivi, kasiteltavaRuutuRivilla);
        }
    }


}