package tatu.miinaharava.logiikka;

public class Ruutu {

    private int moneskoRivi = -1;
    private int moneskoRivilla = -1;
    private boolean onkoMiina = false;
    private int montakoMiinaaYmparilla = -1;
    private boolean onkoYmparillaOlevienMiinojenMaaraAnnettu = false;
    private boolean onkoMerkattu = false;
    private boolean onkoAvattu = false;

    public Ruutu(int moneskoRivi, int moneskoRivilla) {
        this.moneskoRivi = moneskoRivi;
        this.moneskoRivilla = moneskoRivilla;
    }

    public int palautaRivi() {
        return this.moneskoRivi;
    }

    public int palautaMoneskoRivilla() {
        return this.moneskoRivilla;
    }

    public boolean onkoMiina() {
        return this.onkoMiina;
    }

    public boolean onkoYmparillaOlevienMiinojenMaaraAnnettu() {
        return this.onkoYmparillaOlevienMiinojenMaaraAnnettu;
    }

    public boolean onkoAvattu() {
        return this.onkoAvattu;
    }

    public boolean onkoMerkattu() {
        return this.onkoMerkattu;
    }

    public int montakoMiinaaYmparilla() {
        return this.montakoMiinaaYmparilla;
    }

    public boolean asetaMontakoMiinaaYmparilla(int montakoMiinaaYmparilla) {
        if (this.onkoYmparillaOlevienMiinojenMaaraAnnettu == true) {
            return false;
        }
        this.montakoMiinaaYmparilla = montakoMiinaaYmparilla;
        this.onkoYmparillaOlevienMiinojenMaaraAnnettu = true;
        return true;
    }

    public boolean merkkaa() {
        if (this.onkoMerkattu == true) {
            this.onkoMerkattu = false;
            return true;
        }
        this.onkoMerkattu = true;
        return true;
    }

    public boolean asetaMiina() {
        if (this.onkoMiina == true) {
            return false;
        }
        this.onkoMiina = true;
        return true;
    }

    public boolean avaa() {
        this.onkoAvattu = true;
        return true;
    }
}
