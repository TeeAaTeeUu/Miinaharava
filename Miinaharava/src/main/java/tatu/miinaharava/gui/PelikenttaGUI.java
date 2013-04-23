package tatu.miinaharava.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import tatu.miinaharava.logiikka.Pelilauta;

/**
 * Sisältää pelikenttään liittyvät graafiset ominaisuudet, käyttää hyväkseen luokkaa Pelilauta ja RuutuGUI.
 */
public final class PelikenttaGUI extends JFrame {

    private Pelilauta pelilauta;
    private JPanel pelikentta;
    private GridLayout peliRuudukko;
    private RuutuGUI[][] ruudut;
    private RuudunKuuntelija ruudunKuuntelija;

    public PelikenttaGUI() {
        this.pelilauta = new Pelilauta(50, 50, 10, false);
        this.ruudunKuuntelija = new RuudunKuuntelija(this.pelilauta, this);

        this.valmistelePeli();
    }

    public void valmistelePeli() {
        asetaRuudukko();

        for (int kasiteltavaRivi = 1; kasiteltavaRivi <= this.pelilauta.ruudukonKorkeus(); kasiteltavaRivi++) {
            for (int kasiteltavaRuutuRivilla = 1; kasiteltavaRuutuRivilla <= this.pelilauta.ruudukonLeveys(); kasiteltavaRuutuRivilla++) {
                this.ruudut[kasiteltavaRivi][kasiteltavaRuutuRivilla] = new RuutuGUI(kasiteltavaRivi, kasiteltavaRuutuRivilla, pelilauta, this, this.ruudunKuuntelija);
                this.pelikentta.add(this.ruudut[kasiteltavaRivi][kasiteltavaRuutuRivilla]);
            }
        }
        asetaPakollisuuksia();
    }

    private void asetaIkkunanAlkusijainti() {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        int x = (dim.width) / 4;
        int y = (dim.height) / 5;

        //System.out.println("leveys: " + x); //debug
        //System.out.println("korkeus: " + y); //debug

        this.setLocation(x, y);
    }

    public void paivitaPelikentta() {
        for (int kasiteltavaRivi = 1; kasiteltavaRivi <= this.pelilauta.ruudukonKorkeus(); kasiteltavaRivi++) {
            for (int kasiteltavaRuutuRivilla = 1; kasiteltavaRuutuRivilla <= this.pelilauta.ruudukonLeveys(); kasiteltavaRuutuRivilla++) {

                this.tarkistaOnkoRuutuAvattu(kasiteltavaRivi, kasiteltavaRuutuRivilla);
                
                if(this.pelilauta.onkoMiinaAvattu() == true) {
                    tarkistaOnkoRuutuMiinoitettu(kasiteltavaRivi, kasiteltavaRuutuRivilla);
                }
                    
            }
            this.pelikentta.updateUI();
        }
    }

    private void tarkistaOnkoYmparillaOlevienMiinojenMaaraAnnettu(int kasiteltavaRivi, int kasiteltavaRuutuRivilla) {
        String tuloste;
        if (this.pelilauta.onkoYmparillaOlevienMiinojenMaaraAnnettu(kasiteltavaRivi, kasiteltavaRuutuRivilla) == true) {
            int miinojenMaara = this.pelilauta.montakoMiinaaRuudunYmparilla(kasiteltavaRivi, kasiteltavaRuutuRivilla);
            if (miinojenMaara == 0) {
                tuloste = "";
            } else {
                tuloste = Integer.toString(miinojenMaara);
            }
            this.ruudut[kasiteltavaRivi][kasiteltavaRuutuRivilla].setText(tuloste);
            this.ruudut[kasiteltavaRivi][kasiteltavaRuutuRivilla].setBorder(null);
            this.ruudut[kasiteltavaRivi][kasiteltavaRuutuRivilla].setOpaque(true);
        }
    }

    private void tarkistaOnkoRuutuAvattu(int kasiteltavaRivi, int kasiteltavaRuutuRivilla) {
        if (this.pelilauta.onkoRuutuAvattu(kasiteltavaRivi, kasiteltavaRuutuRivilla) == true) {
            this.ruudut[kasiteltavaRivi][kasiteltavaRuutuRivilla].setOpaque(true);
            this.ruudut[kasiteltavaRivi][kasiteltavaRuutuRivilla].setBorder(null);
            this.tarkistaOnkoRuutuMiinoitettu(kasiteltavaRivi, kasiteltavaRuutuRivilla);
            this.tarkistaOnkoYmparillaOlevienMiinojenMaaraAnnettu(kasiteltavaRivi, kasiteltavaRuutuRivilla);
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

        this.peliRuudukko = new GridLayout(this.pelilauta.ruudukonKorkeus(), this.pelilauta.ruudukonLeveys()); //tehdään gridlayout ruudukon mukaan, jolloin ruutuja vain helppo lisäillä
        this.pelikentta.setLayout(this.peliRuudukko);

        this.ruudut = new RuutuGUI[this.pelilauta.ruudukonKorkeus() + 1][this.pelilauta.ruudukonLeveys() + 1]; //luodaan ruudukun kokoinen taulukko ruutu-olioita varten
    }

    private void tarkistaOnkoRuutuMiinoitettu(int kasiteltavaRivi, int kasiteltavaRuutuRivilla) {
        if (this.pelilauta.onkoRuutuMiinoitettu(kasiteltavaRivi, kasiteltavaRuutuRivilla) == true) {
            this.ruudut[kasiteltavaRivi][kasiteltavaRuutuRivilla].setText("X");
        }
    }

}