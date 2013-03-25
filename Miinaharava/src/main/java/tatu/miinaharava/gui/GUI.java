package tatu.miinaharava.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import tatu.miinaharava.logiikka.Pelilauta;

public final class GUI extends JFrame {

    private Pelilauta pelilauta;
    private JPanel pelikentta;
    private GridLayout peliRuudukko;
    private RuutuGUI[][] ruudut;

    public GUI() {
        this.pelilauta = new Pelilauta();

//        this.luoValikot();

        this.valmistelePeli();
    }

    public void valmistelePeli() {
        this.pelikentta = new JPanel();

        this.peliRuudukko = new GridLayout(this.pelilauta.ruudukonKorkeus(), this.pelilauta.ruudukonLeveys()); //tehdään gridlayout ruudukon mukaan, jolloin ruutuja vain helppo lisäillä
        this.pelikentta.setLayout(this.peliRuudukko);

        this.ruudut = new RuutuGUI[this.pelilauta.ruudukonKorkeus() + 1][this.pelilauta.ruudukonLeveys() + 1]; //luodaan ruudukun kokoinen taulukko ruutu-olioita varten

        for (int kasiteltavaRivi = 1; kasiteltavaRivi <= this.pelilauta.ruudukonKorkeus(); kasiteltavaRivi++) {
            for (int kasiteltavaRuutuRivilla = 1; kasiteltavaRuutuRivilla <= this.pelilauta.ruudukonLeveys(); kasiteltavaRuutuRivilla++) {
                this.ruudut[kasiteltavaRivi][kasiteltavaRuutuRivilla] = new RuutuGUI(kasiteltavaRivi, kasiteltavaRuutuRivilla, pelilauta, this);
                this.pelikentta.add(this.ruudut[kasiteltavaRivi][kasiteltavaRuutuRivilla]);
            }
        }

        setLayout(new BorderLayout());
        add("Center", pelikentta);
        setTitle("Miinaharava-peli");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        this.asetaIkkunanAlkusijainti();

        pelikentta.doLayout();
        pack();
        setVisible(true);
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

                if (this.pelilauta.onkoRuutuMerkattu(kasiteltavaRivi, kasiteltavaRuutuRivilla) == true) {
                    this.ruudut[kasiteltavaRivi][kasiteltavaRuutuRivilla].setText("X");
                }

                if (this.pelilauta.onkoRuutuAvattu(kasiteltavaRivi, kasiteltavaRuutuRivilla) == true) {
                    this.ruudut[kasiteltavaRivi][kasiteltavaRuutuRivilla].setBackground(Color.GRAY);

                    if (this.pelilauta.onkoMiina(kasiteltavaRivi, kasiteltavaRuutuRivilla) == true) {
                        this.ruudut[kasiteltavaRivi][kasiteltavaRuutuRivilla].setText("B");
                    }
                    
                    if (this.pelilauta.onkoYmparillaOlevienMiinojenMaaraAnnettu(kasiteltavaRivi, kasiteltavaRuutuRivilla) == true) {
                        int miinojenMaara = this.pelilauta.montakoMiinaaYmparilla(kasiteltavaRivi, kasiteltavaRuutuRivilla);
                        this.ruudut[kasiteltavaRivi][kasiteltavaRuutuRivilla].setText(Integer.toString(miinojenMaara));
                    }
                }
            }
            this.pelikentta.updateUI();
        }
    }
}