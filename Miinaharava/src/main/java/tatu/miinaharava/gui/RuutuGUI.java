package tatu.miinaharava.gui;

import java.awt.Dimension;
import java.awt.Insets;
import javax.swing.*;
import tatu.miinaharava.logiikka.Pelilauta;

public class RuutuGUI extends JButton {

    Dimension d = new Dimension(24, 24);
    Insets rajat = new Insets(0, 0, 0, 0);
    private int moneskoRivi;
    private int moneskoRivilla;
    private final Pelilauta pelilauta;
    private RuudunKuuntelija ruudunKuuntelija;

    public RuutuGUI(int moneskoRivi, int moneskoRivilla, Pelilauta pelilauta, GUI gui) {
        this.moneskoRivi = moneskoRivi;
        this.moneskoRivilla = moneskoRivilla;

        this.pelilauta = pelilauta;
        this.ruudunKuuntelija = new RuudunKuuntelija(this.pelilauta, gui);

        setMargin(rajat);
        setPreferredSize(d);
        setVisible(true);

        addMouseListener(this.ruudunKuuntelija);
    }

    public int palautaRivi() {
        return this.moneskoRivi;
    }

    public int palautaMoneskoRivilla() {
        return this.moneskoRivilla;
    }
}
