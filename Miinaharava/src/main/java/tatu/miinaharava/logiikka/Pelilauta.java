package tatu.miinaharava.logiikka;

public class Pelilauta extends Ruudukko {

    private boolean miinaAvattu = false;

    public Pelilauta(int korkeus, int leveys, int montakoMiinaa) {
        super(korkeus, leveys, montakoMiinaa);
    }

    public Pelilauta() {
    }

    public boolean onkoMiinaAvattu() {
        return this.miinaAvattu;
    }

    private boolean asetaMiinaAvatuksi() {
        this.miinaAvattu = true;
        return true;
    }

    public int montakoMiinaaYmparilla(int rivi, int moneskoRivilla) {
        if (this.onKunnollisetKoordinaatit(rivi, moneskoRivilla)) {
            return this.ruudut[rivi][moneskoRivilla].montakoMiinaaYmparilla();
        }
        return -1;
    }

    public boolean avaaRuutu(int rivi, int moneskoRivilla) {
        if (this.onKunnollisetKoordinaatit(rivi, moneskoRivilla) == false) {
            return false;
        }
        if (this.onkoRuutuAvattu(rivi, moneskoRivilla) == true) {
            return true;
        }
        this.asetaRuutuAvatuksi(rivi, moneskoRivilla);

        if (this.onkoMiina(rivi, moneskoRivilla) == true) {
            this.asetaMiinaAvatuksi();
            return true;
        }
        return this.avaaRuutuLoop(rivi, moneskoRivilla);
    }

    private boolean avaaRuutuLoop(int rivi, int moneskoRivilla) {
        int ymparillaOlevienMiinojenMaara = this.tarkistaYmparoidytRuudut(rivi, moneskoRivilla);
        this.asetaRuudulleLuku(rivi, moneskoRivilla, ymparillaOlevienMiinojenMaara);

        if (ymparillaOlevienMiinojenMaara == 0) {
            return this.avaaYmparoidutRuudut(rivi, moneskoRivilla);
        }
        return true;
    }

    private boolean asetaRuutuAvatuksi(int rivi, int moneskoRivilla) {
        if (this.onKunnollisetKoordinaatit(rivi, moneskoRivilla)) {
            this.ruudut[rivi][moneskoRivilla].avaaRuutu();
            return true;
        }
        return false;
    }

    public boolean merkkaaRuutu(int rivi, int moneskoRivilla) {
        if (this.onKunnollisetKoordinaatit(rivi, moneskoRivilla)) {
                this.ruudut[rivi][moneskoRivilla].merkkaa();
                return true;
        }
        return false;
    }

    boolean asetaRuudulleLuku(int rivi, int moneskoRivilla, int ymparillaOlevienMiinojenMaara) {
        if (this.onKunnollisetKoordinaatit(rivi, moneskoRivilla)) {
            this.ruudut[rivi][moneskoRivilla].asetaMontakoMiinaaYmparilla(ymparillaOlevienMiinojenMaara);
            return true;
        }
        return false;
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
            if (this.onkoMiina(rivi, moneskoRivilla)) {
                return 1;
            }
        }
        return 0;
    }
}
