package tatu.miinaharava.gui;

public class Main {

    /**
     * Aloittaa pelin
     *
     * @param args
     */
    public static void main(String[] args) {
        aloitaPeli(10,10,10);
    }

    public static void aloitaPeli(int korkeus, int leveys, int miinoja) {
        new PelikenttaGUI(korkeus, leveys, miinoja);
    }

    public static void aloitaPeli() {
        new PelikenttaGUI();
    }
}
