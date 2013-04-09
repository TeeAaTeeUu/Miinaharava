package tatu.miinaharava.logiikka;

/**
 * Toimittaa yhden ruudun virkaa ristinollassa, pitää tiedon sisällöstään ja paikastaan.
 */
public class Ruutu {

    private int moneskoRivi = -1;
    private int moneskoRivilla = -1;
    private boolean onkoMiina = false;
    private int montakoMiinaaYmparilla = -1;
    private boolean onkoYmparillaOlevienMiinojenMaaraAnnettu = false;
    private boolean onkoMerkattu = false;
    private boolean onkoAvattu = false;

    /**
     * Luo Ruudun, joka tietää oman paikkansa kordinaatit
     * 
     * @param moneskoRivi
     * @param moneskoRivilla
     */
    public Ruutu(int moneskoRivi, int moneskoRivilla) {
        this.moneskoRivi = moneskoRivi;
        this.moneskoRivilla = moneskoRivilla;
    }

    /**
     * 
     * 
     * @return Oman rivinsä.
     */
    public int palautaRivi() {
        return this.moneskoRivi;
    }

    /**
     *
     * @return Monesko on rivillä.
     */
    public int palautaMoneskoRivilla() {
        return this.moneskoRivilla;
    }

    /**
     *
     * @return Jos ruutu sisältää miinan.
     */
    public boolean onkoMiina() {
        return this.onkoMiina;
    }

    /**
     *
     * @return Josko ympärillä olevien miinojen määrä on annettu.
     */
    public boolean onkoYmparillaOlevienMiinojenMaaraAnnettu() {
        return this.onkoYmparillaOlevienMiinojenMaaraAnnettu;
    }

    /**
     *
     * @return Onko Ruutu kiinni vai jo avattu.
     */
    public boolean onkoAvattu() {
        return this.onkoAvattu;
    }

    /**
     *
     * @return Jo käyttäjä on merkannut ruudun potentiaalisena miinana.
     */
    public boolean onkoMerkattu() {
        return this.onkoMerkattu;
    }

    /**
     *
     * @return Palauttaa miinojen määrän ympärillä.
     */
    public int montakoMiinaaYmparilla() {
        return this.montakoMiinaaYmparilla;
    }

    /**
     * Tarkistaa onko määrä jo asetettu, ja jos ei niin asettaa.
     * 
     * @param montakoMiinaaYmparilla
     * @return
     * true, jos onnistuu.
     * false, jos epännistuu.
     */
    public boolean asetaMontakoMiinaaYmparilla(int montakoMiinaaYmparilla) {
        if (this.onkoYmparillaOlevienMiinojenMaaraAnnettu == true) {
            return false;
        }
        this.montakoMiinaaYmparilla = montakoMiinaaYmparilla;
        this.onkoYmparillaOlevienMiinojenMaaraAnnettu = true;
        return true;
    }

    /**
     * Merkkaa ruudun potentiaalisena miinana. uudelleen merkkaaminen poistaa merkin.
     *
     * @return
     * true/false josko onnistui.
     */
    public boolean merkkaa() {
        if (this.onkoMerkattu == true) {
            this.onkoMerkattu = false;
            return true;
        }
        this.onkoMerkattu = true;
        return true;
    }

    /**
     * asettaa ruutuun miinan. Vain pelin alustusvaiheessa käytössä.
     *
     * @return
     */
    public boolean asetaMiina() {
        if (this.onkoMiina == true) {
            return false;
        }
        this.onkoMiina = true;
        return true;
    }

    /**
     * Laittaa ruudun avatuksi.
     *
     * @return
     */
    public boolean avaa() {
        this.onkoAvattu = true;
        return true;
    }
}
