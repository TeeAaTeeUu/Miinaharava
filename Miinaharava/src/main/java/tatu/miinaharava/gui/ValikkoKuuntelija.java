package tatu.miinaharava.gui;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class ValikkoKuuntelija implements ActionListener {

    private int korkeus;
    private int leveys;
    private int miinoja;
    private PelikenttaGUI gui;
    private boolean lopeta = false;
    private boolean info = false;
    private boolean custom = false;

    public ValikkoKuuntelija(String syote, PelikenttaGUI gui) {
        this.gui = gui;

        if (syote.equals("pieni")) {
            korkeus = 10;
            leveys = 10;
            miinoja = 10;
        } else if (syote.equals("normaali")) {
            korkeus = 15;
            leveys = 15;
            miinoja = 25;
        } else if (syote.equals("suuri")) {
            korkeus = 30;
            leveys = 30;
            miinoja = 75;
        } else if (syote.equals("lopeta")) {
            lopeta = true;
        } else if (syote.equals("info")) {
            info = true;
        } else if (syote.equals("custom")) {
            custom = true;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (lopeta) {
            sulje();
        } else if (info) {
            info();
        } else if (custom) {
            custom();
        } else {
            aloitaUusi();
        }
    }

    private void sulje() {
        System.exit(0);
    }

    private void info() throws HeadlessException {
        String pelinTiedot = "Miinaharava peli\n"
                + "Ohjelmoinut Tatu Tallberg, 2013";
        JOptionPane.showMessageDialog(null, pelinTiedot,
                "Tietoa pelistä", JOptionPane.INFORMATION_MESSAGE);
    }

    private void aloitaUusi() {
        gui.dispose();
        Main.aloitaPeli(korkeus, leveys, miinoja);
    }

    private void custom() {
        gui.dispose();
        Main.aloitaPeli();
    }
}
