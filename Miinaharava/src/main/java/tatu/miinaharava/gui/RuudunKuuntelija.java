package tatu.miinaharava.gui;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import tatu.miinaharava.logiikka.Pelilauta;

public class RuudunKuuntelija extends MouseAdapter {

    private final Pelilauta pelilauta;
    private final GUI gui;

    public RuudunKuuntelija(Pelilauta pelilauta, GUI gui) {
        this.pelilauta = pelilauta;
        this.gui = gui;
    }

    @Override
    public void mouseClicked(MouseEvent klik) {
        RuutuGUI painettuRuutu = (RuutuGUI) klik.getSource();

        pelilauta.avaaRuutu(painettuRuutu.palautaRivi(), painettuRuutu.palautaMoneskoRivilla());

        painettuRuutu.setBackground(Color.white);
        if (pelilauta.onkoMiinaAvattu() == true) {
            JOptionPane.showMessageDialog(null, "Osuit miinaan, hävisit!");
        }
        
        gui.paivitaPelikentta();
    }
}
