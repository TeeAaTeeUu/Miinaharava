package tatu.miinaharava.gui;

import java.awt.HeadlessException;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import tatu.miinaharava.logiikka.Pelilauta;

/**
 * Kyselee pelin alussa ruudukon koon ja miinojen määrän väsymättömästi, kunnes
 * numeroita saadaan.
 */
public class ValikkoGUI {

    private int miinoja;
    private int leveys;
    private int korkeus;
    private JMenuBar paavalikko;
    private JMenu pelivalikko;
    private JMenu infovalikko;
    private JMenuItem pieni;
    private JMenuItem normaali;
    private JMenuItem suuri;
    private JMenuItem lopeta;
    private JMenuItem tietoaPelista;
    private JMenuItem custom;
    private PelikenttaGUI gui;

    /**
     * tekee kyselyt ja tallentaa ne itselleen muistiin.
     */
    public ValikkoGUI() {
    }

    public ValikkoGUI kyseleTiedot() {
        korkeus = kysyLukua("Kuinka korkean kentän haluat?");

        leveys = kysyLukua("Kuinka leveän kentän haluat?");

        miinoja = kysyLukua("Kuinka monta miinaa haluat?");

        return this;
    }

    private int kysyLukua(String teksti) {
        int luku = 0;
        String syote;
        boolean kelvotonSyote = true;
        while (kelvotonSyote) {
            syote = pyydaSyote(teksti);
            if (this.onkoMuutettavissaLuvuksi(syote)) {
                luku = palautaLuku(syote);
                kelvotonSyote = false;
            } else if (onTyhja(syote)) {
                lopeta();
            }
        }

        return luku;
    }

    private String pyydaSyote(String teksti) throws HeadlessException {
        return JOptionPane.showInputDialog(teksti);
    }

    private int palautaLuku(String syote) throws NumberFormatException {
        return Integer.parseInt(syote);
    }

    private boolean onTyhja(String syote) {
        return syote == null || syote.equals("");
    }

    private void lopeta() {
        System.exit(0);
    }

    private boolean onkoMuutettavissaLuvuksi(String syote) {
        try {
            palautaLuku(syote);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * Käytännössä PelikenttäGUIn pyynnöstä palauttaa kyselyllä saatujen
     * tulosten mukaisen pelilaudan.
     *
     * @return kyselyjen mukaisen pelilaudan
     */
    protected Pelilauta luoPelilauta() {
        return new Pelilauta(korkeus, leveys, miinoja, false);
    }

    protected void luoValikot(final PelikenttaGUI gui) {
        this.paavalikko = new JMenuBar();
        
        this.gui = gui;

        luoPelivalikko();

        luoInfoValikko();

        gui.setJMenuBar(paavalikko);
    }

    private void luoPelivalikko() {
        this.pelivalikko = new JMenu("Peli");
        
        luoPieni();
        luoNormaali();
        luoSuuri();
        LuoVapaa();

        pelivalikko.addSeparator();
        
        luoLopeta();

        paavalikko.add(pelivalikko);
    }

    private void luoInfoValikko() {
        this.infovalikko = new JMenu("Info");
        LuoTietoa();

        paavalikko.add(infovalikko);
    }

    private void luoPieni() {
        this.pieni = new JMenuItem("Uusi peli: pieni (10x10)");
        this.pieni.addActionListener(new ValikkoKuuntelija("pieni", gui));
        pelivalikko.add(this.pieni);
    }

    private void luoNormaali() {
        this.normaali = new JMenuItem("Uusi peli: normaali (15x15)");
        this.normaali.addActionListener(new ValikkoKuuntelija("normaali", gui));
        pelivalikko.add(this.normaali);
    }

    private void luoSuuri() {
        this.suuri = new JMenuItem("Uusi peli: suuri (30x30)");
        this.suuri.addActionListener(new ValikkoKuuntelija("suuri", gui));
        pelivalikko.add(this.suuri);
    }

    private void LuoVapaa() {
        this.custom = new JMenuItem("Uusi peli: omavalintaisen kokoinen");
        this.custom.addActionListener(new ValikkoKuuntelija("custom", gui));
        pelivalikko.add(this.custom);
    }

    private void luoLopeta() {
        this.lopeta = new JMenuItem("Lopeta");
        this.lopeta.addActionListener(new ValikkoKuuntelija("lopeta", gui));
        pelivalikko.add(lopeta);
    }

    private void LuoTietoa() {
        this.tietoaPelista = new JMenuItem("Tietoa pelistä");
        this.tietoaPelista.addActionListener(new ValikkoKuuntelija("info", gui));
        infovalikko.add(tietoaPelista);
    }
}
