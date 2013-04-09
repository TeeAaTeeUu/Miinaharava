package tatu.miinaharava.logiikka;

/**
 * Toteuttaa korkeantason ristinolla-logiikan, eli avatessa ruutua avaa myös mahdolliset muutokset muissa ruuduissa avatun ympärillä.
 */
public class Pelilauta extends Ruudukko {

    private boolean miinaAvattu = false;
    private boolean peliAlkanut = false;

    public Pelilauta(int korkeus, int leveys, int montakoMiinaa) {
        super(korkeus, leveys, montakoMiinaa);
    }
    
    public Pelilauta(int korkeus, int leveys) {
        super(korkeus, leveys);
    }

    public Pelilauta() {
        super();
    }
    
    public Pelilauta(int korkeus, int leveys, int montakoMiinaa, boolean miinoitetaanko) {
        super(korkeus, leveys, montakoMiinaa, miinoitetaanko);
    }
    
    public Pelilauta(boolean miinoitetaanko) {
        super(miinoitetaanko);
    }

    public boolean onkoMiinaAvattu() {
        return this.miinaAvattu;
    }

    private boolean asetaMiinaAvatuksi() {
        this.miinaAvattu = true;
        return true;
    }
    
    public boolean onkoPeliAlkanut() {
        return peliAlkanut;
    }
    
    public boolean asetaPeliAlkaneeksi() {
        this.peliAlkanut = true;
        return true;
    }

    @Override
    public boolean avaaRuutu(int rivi, int moneskoRivilla) {
        if (this.onKunnollisetKoordinaatit(rivi, moneskoRivilla) == false) {
            return false;
        }
        if (this.onkoRuutuAvattu(rivi, moneskoRivilla) == true) {
            return true;
        }
        super.avaaRuutu(rivi, moneskoRivilla);

        if (this.onkoRuutuMiinoitettu(rivi, moneskoRivilla) == true) {
            this.asetaMiinaAvatuksi();
            return true;
        }
        return this.avaaRuutuLoop(rivi, moneskoRivilla);
    }

    private boolean avaaRuutuLoop(int rivi, int moneskoRivilla) {
        int ymparillaOlevienMiinojenMaara = this.tarkistaYmparoidytRuudut(rivi, moneskoRivilla);
        this.asetaMontakoMiinaaYmparilla(rivi, moneskoRivilla, ymparillaOlevienMiinojenMaara);

        if (ymparillaOlevienMiinojenMaara == 0) {
            return this.avaaYmparoidutRuudut(rivi, moneskoRivilla);
        }
        return true;
    }

    int tarkistaYmparoidytRuudut(int rivi, int moneskoRivilla) {
        int miinojenMaara = 0;

        miinojenMaara += this.tarkistaRuutu(rivi - 1, moneskoRivilla - 1);
        miinojenMaara += this.tarkistaRuutu(rivi - 1, moneskoRivilla);
        miinojenMaara += this.tarkistaRuutu(rivi - 1, moneskoRivilla + 1);
        miinojenMaara += this.tarkistaRuutu(rivi, moneskoRivilla - 1);
        miinojenMaara += this.tarkistaRuutu(rivi, moneskoRivilla + 1);
        miinojenMaara += this.tarkistaRuutu(rivi + 1, moneskoRivilla - 1);
        miinojenMaara += this.tarkistaRuutu(rivi + 1, moneskoRivilla);
        miinojenMaara += this.tarkistaRuutu(rivi + 1, moneskoRivilla + 1);

        if (miinojenMaara >= 0 && miinojenMaara <= 8) {
            return miinojenMaara;
        }
        return -1;
    }

    private boolean avaaYmparoidutRuudut(int rivi, int moneskoRivilla) {
        this.avaaRuutu(rivi - 1, moneskoRivilla - 1);
        this.avaaRuutu(rivi - 1, moneskoRivilla);
        this.avaaRuutu(rivi - 1, moneskoRivilla + 1);
        this.avaaRuutu(rivi, moneskoRivilla - 1);
        this.avaaRuutu(rivi, moneskoRivilla + 1);
        this.avaaRuutu(rivi + 1, moneskoRivilla - 1);
        this.avaaRuutu(rivi + 1, moneskoRivilla);
        this.avaaRuutu(rivi + 1, moneskoRivilla + 1);

        return true;
    }

    private int tarkistaRuutu(int rivi, int moneskoRivilla) {
        if (this.onKunnollisetKoordinaatit(rivi, moneskoRivilla)) {
            if (this.onkoRuutuMiinoitettu(rivi, moneskoRivilla)) {
                return 1;
            }
        }
        return 0;
    }
}
