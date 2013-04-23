package tatu.miinaharava.logiikka;

/**
 * Toteuttaa korkeantason ristinolla-logiikan, eli avatessa ruutua avaa myös
 * mahdolliset muutokset muissa ruuduissa avatun ympärillä.
 */
public class Pelilauta extends Ruudukko {

    private boolean miinaAvattu = false;
    private boolean peliAlkanut = false;
    private boolean peliVoitettu = false;

    /**
     * Luo miinoitetun pelilaudan vapaavalintaisella korkeudella, leveydellä ja
     * miinojenmäärällä.
     *
     * @param korkeus ruudukonkorkeus
     * @param leveys ruuudukon leveys
     * @param montakoMiinaa miinojen määrä pelilaudalla.
     */
    public Pelilauta(int korkeus, int leveys, int montakoMiinaa) {
        super(korkeus, leveys, montakoMiinaa);
    }

    /**
     * Luo pelilaudan vapaavalintaisella korkeudella ja leveydellä.
     *
     * @param korkeus pelilaudan korkeus
     * @param leveys pelilaudan leveys
     */
    public Pelilauta(int korkeus, int leveys) {
        super(korkeus, leveys);
    }

    /**
     * Luo vakiokokoisen miinoitetun pelilaudan.
     */
    public Pelilauta() {
        super();
    }

    /**
     * Luo pelilaudan vapaavalintaisella korkeudella, leveydellä ja
     * miinojenmäärällä.
     *
     * @param korkeus pelilaudan korkeus
     * @param leveys pelilaudan leveys
     * @param montakoMiinaa miinojen määrä pelilaudassa.
     * @param miinoitetaanko miinoitetaanko pelilauta heti vai ei.
     */
    public Pelilauta(int korkeus, int leveys, int montakoMiinaa, boolean miinoitetaanko) {
        super(korkeus, leveys, montakoMiinaa, miinoitetaanko);
    }

    /**
     * Luo vakiokokoisen pelilaudan miinoitettuna tai ilman.
     *
     * @param miinoitetaanko Eli miinoitetaanko vai ei, false/true.
     */
    public Pelilauta(boolean miinoitetaanko) {
        super(miinoitetaanko);
    }

    /**
     *
     * @return palauttaa jos miinaruutu avattu.
     */
    public boolean onkoMiinaAvattu() {
        return this.miinaAvattu;
    }

    private boolean asetaMiinaAvatuksi() {
        this.miinaAvattu = true;
        return true;
    }

    /**
     *
     * @return josko ensimmäinen ruutu on jo avattu.
     */
    public boolean onkoPeliAlkanut() {
        return peliAlkanut;
    }

    /**
     * Asettaa pelin alkaneeksi.
     *
     * @return aina true, ellei peli päättynyt.
     */
    public boolean asetaPeliAlkaneeksi() {
        this.peliAlkanut = true;
        if (this.onkoMiinaAvattu() == false) {
            return true;
        }
        return false;
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
        boolean onnistuikoRuutujenAvaaminen = this.avaaRuutuLoop(rivi, moneskoRivilla);

        return onnistuikoRuutujenAvaaminen;
    }

    private boolean avaaRuutuLoop(int rivi, int moneskoRivilla) {
        int ymparillaOlevienMiinojenMaara = this.tarkistaYmparoidytRuudut(rivi, moneskoRivilla);
        this.asetaMontakoMiinaaYmparilla(rivi, moneskoRivilla, ymparillaOlevienMiinojenMaara);

        if (ymparillaOlevienMiinojenMaara == 0) {
            return this.avaaYmparoidutRuudut(rivi, moneskoRivilla);
        }
        return true;
    }

    /**
     * Tarkistaa ympäröidyt ruudut ruudun avauksen jälkeen, ja toimii
     * pelilogiikan mukaisesti.
     *
     * @param rivi millä rivillä
     * @param moneskoRivilla millä sarakkeella
     * @return miinojen määrä.
     */
    protected int tarkistaYmparoidytRuudut(int rivi, int moneskoRivilla) {
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

    private boolean tarkistaOnkoKaikkiMiinatMerkattu() {
        for (int kasiteltavaRivi = 1; kasiteltavaRivi <= this.ruudukonKorkeus(); kasiteltavaRivi++) {
            for (int kasiteltavaSarake = 1; kasiteltavaSarake <= this.ruudukonLeveys(); kasiteltavaSarake++) {
                if (this.onkoRuutuMiinoitettu(kasiteltavaRivi, kasiteltavaSarake) == true) {
                    if (this.onkoRuutuMerkattu(kasiteltavaRivi, kasiteltavaSarake) == false) {
                        return false;
                    }
                }
            }
        }
        this.asetaPeliVoitetuksi();

        return true;
    }

    private boolean tarkistaOnkoKaikkiRuudutAvattu() {
        for (int kasiteltavaRivi = 1; kasiteltavaRivi <= this.ruudukonKorkeus(); kasiteltavaRivi++) {
            for (int kasiteltavaSarake = 1; kasiteltavaSarake <= this.ruudukonLeveys(); kasiteltavaSarake++) {
                if (this.onkoRuutuMiinoitettu(kasiteltavaRivi, kasiteltavaSarake) == false) {
                    if (this.onkoRuutuAvattu(kasiteltavaRivi, kasiteltavaSarake) == false) {
                        return false;
                    }
                }
            }
        }
        this.asetaPeliVoitetuksi();

        return true;
    }

    public boolean OnkoPeliVoitettu() {
        if (this.onkoPeliAlkanut() == true) {
            this.tarkistaOnkoKaikkiMiinatMerkattu();
            this.tarkistaOnkoKaikkiRuudutAvattu();
        }
        return this.peliVoitettu;
    }
 
    private void asetaPeliVoitetuksi() {
        if (this.onkoMiinaAvattu() == false) {
            this.peliVoitettu = true;
        }
    }
}
