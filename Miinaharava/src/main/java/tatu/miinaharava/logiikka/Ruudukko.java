package tatu.miinaharava.logiikka;

/**
 * Hallinnoi ruudukon ruutuja, miinoittaa alustuksessa ja päivittää käyttämiään
 * ruutuja.
 */
public class Ruudukko {

    /**
     *Ruudukon käyttämät ruututaulukko.
     */
    public Ruutu[][] ruudut;
    private int leveys = 10;
    private int korkeus = 10;
    private int montakoMiinaa = 20;
    private boolean onkoRuudukkoMiinoitettu = false;

    /**
     *
     * @return Palauttaa ruudukon korkeuden.
     */
    public int ruudukonKorkeus() {
        return this.korkeus;
    }

    /**
     *
     * @return Palauttaa miinojen määrän.
     */
    public int miinojenMaara() {
        return this.montakoMiinaa;
    }

    /**
     *
     * @return Palauttaa ruudukon leveyden.
     */
    public int ruudukonLeveys() {
        return this.leveys;
    }

    /**
     *Luo vakiokokoisen miinoitetun ruudukon.
     */
    public Ruudukko() {
        this(true);
    }

    /**
     * Luo vakiokokoisen ruudukon miinoitettuna tai ilman.
     *
     * @param miinoitetaanko Eli miinoitetaanko vai ei, false/true.
     */
    public Ruudukko(boolean miinoitetaanko) {
        this.alustaRuudut(this.korkeus, this.leveys);
        if (miinoitetaanko == true) {
            this.miinoitaRuudukko(this.montakoMiinaa);
        }
    }

    /**
     *Luo miinoitetun ruudukon vapaavalintaisella korkeudella, leveydellä ja miinojenmäärällä.
     * @param korkeus ruudukon korkeus
     * @param leveys ruuudukon leveys
     * @param montakoMiinaa miinojen määrä ruudukossa.
     */
    public Ruudukko(int korkeus, int leveys, int montakoMiinaa) {
        this(korkeus, leveys, montakoMiinaa, true);
    }

    /**
     *Luo ruudukon vapaavalintaisella korkeudella, leveydellä ja miinojenmäärällä.
     * @param korkeus ruudukon korkeus
     * @param leveys ruuudukon leveys
     * @param montakoMiinaa miinojen määrä ruudukossa.
     * @param miinoitetaanko miinoitetaanko ruudukko heti vai ei.
     */
    public Ruudukko(int korkeus, int leveys, int montakoMiinaa, boolean miinoitetaanko) {
        this.alustaRuudut(korkeus, leveys);
        if (miinoitetaanko == true) {
            this.miinoitaRuudukko(montakoMiinaa);
        } else {
            this.asetaMontakoMiinaa(montakoMiinaa);
        }
    }

    /**
     *Luo ruudukon vapaavalintaisella korkeudella ja leveydellä.
     * @param korkeus ruudukon korkeus
     * @param leveys ruudukon leveys
     */
    public Ruudukko(int korkeus, int leveys) {
        this.alustaRuudut(korkeus, leveys);
        this.miinoitaRuudukko(montakoMiinaa);
    }

    /**
     *Miinoittaa ruudukon.
     * @param montakoMiinaa kuinka montaa miinaa käytetään miinoituksessa.
     * @return true/false siitä, miten kävi.
     */
    public final boolean miinoitaRuudukko(int montakoMiinaa) {
        return this.miinoitaRuudukko(montakoMiinaa, -1, -1);
    }

    /**
     *Miinoittaa ruudukon niin, ettei painetussa ruudussa ole heti miinaa. Käytetään mukavuus syistä.
     * @param montakoMiinaa kuinka monta miinaa käytetään miinoituksessa.
     * @param mitaRiviaPainettu mitä riviä painettu ruudukossa.
     * @param MitaSarakettaPainettu mitä saraketta painettu ruudukossa.
     * @return false, jos ruudukko oli jo miinoitettu, muuten true.
     */
    public boolean miinoitaRuudukko(int montakoMiinaa, int mitaRiviaPainettu, int MitaSarakettaPainettu) {
        this.asetaMontakoMiinaa(montakoMiinaa);
        
        if (this.onkoRuudukkoMiinoitettu() == false) {
            this.miinoitaRuudukkoLoop(mitaRiviaPainettu, MitaSarakettaPainettu);
            
            if (this.asetaRuudukkoMiinoitetuksi() == true) {
                return true;
            }
        }
        return false;
    }

    private void miinoitaRuudukkoLoop(int mitaRiviaPainettu, int MitaSarakettaPainettu) {
        int asetetutMiinat = 0;

        while (asetetutMiinat < this.montakoMiinaa) {
            int kasiteltavaRivi = this.luoRandom(this.ruudukonKorkeus());
            int kasiteltavaSarake = this.luoRandom(this.ruudukonLeveys());

            if (this.onkoRuutuMiinoitettu(kasiteltavaRivi, kasiteltavaSarake) == false) {
                if ((mitaRiviaPainettu == kasiteltavaRivi && MitaSarakettaPainettu == kasiteltavaSarake) == false) {

                        this.asetaMiina(kasiteltavaRivi, kasiteltavaSarake);
                        asetetutMiinat++;
                }
            }
        }
    }

    private void alustaRuudut(int korkeus, int leveys) {
        this.asetaRuudukonLeveysJaKorkeus(leveys, korkeus);

        this.ruudut = new Ruutu[this.korkeus + 1][this.leveys + 1];
        for (int kasiteltavaRivi = 1; kasiteltavaRivi <= this.ruudukonKorkeus(); kasiteltavaRivi++) {
            for (int kasiteltavaSarake = 1; kasiteltavaSarake <= this.ruudukonLeveys(); kasiteltavaSarake++) {
                this.ruudut[kasiteltavaRivi][kasiteltavaSarake] = new Ruutu(kasiteltavaRivi, kasiteltavaSarake);
            }
        }
    }

    /**
     *Tarkistaa onko annetut koordinaatit kunnolliset, eli löytyykö ruutu ruudukosta.
     * @param rivi mikä rivi
     * @param moneskoRivilla mikä sarake
     * @return false, jos huonot, true jos kunnolliset.
     */
    protected boolean onKunnollisetKoordinaatit(int rivi, int moneskoRivilla) {
        if (rivi >= 1 && moneskoRivilla >= 1) {
            if (rivi <= this.ruudukonKorkeus() && moneskoRivilla <= this.ruudukonLeveys()) {
                return true;
            }
        }
        return false;
    }

    /**
     *Asettaa miinan ruutuun.
     * @param rivi millä rivillä
     * @param moneskoRivilla millä sarakkeella
     * @return false, jos jo miinoitettu tai epäkelvot koordinaatit. Muuten true.
     */
    protected boolean asetaMiina(int rivi, int moneskoRivilla) {
        if (this.onKunnollisetKoordinaatit(rivi, moneskoRivilla)) {
            if (this.onkoRuutuMiinoitettu(rivi, moneskoRivilla)) {
                return false;
            } else {
                this.ruudut[rivi][moneskoRivilla].asetaMiina();
                return true;
            }
        }
        return false;
    }

    /**
     *Asettaa halutulle ruudulle ympärillä olevien miinojen määrän.
     * @param rivi millä rivillä.
     * @param moneskoRivilla millä sarakkeella
     * @param ymparillaOlevienMiinojenMaara laskettu miinojen määrä.
     * @return true, jos homma onnistui.
     */
    protected boolean asetaMontakoMiinaaYmparilla(int rivi, int moneskoRivilla, int ymparillaOlevienMiinojenMaara) {
        if (this.onKunnollisetKoordinaatit(rivi, moneskoRivilla)) {
            if (this.ruudut[rivi][moneskoRivilla].asetaMontakoMiinaaYmparilla(ymparillaOlevienMiinojenMaara) == true) {
                return true;
            }
        }
        return false;
    }

    /**
     *Avaa halutun ruudun.
     * @param rivi millä rivillä.
     * @param moneskoRivilla millä sarakkeella.
     * @return true jos avaaminen onnistuu, muuten false.
     */
    public boolean avaaRuutu(int rivi, int moneskoRivilla) {
        if (this.onKunnollisetKoordinaatit(rivi, moneskoRivilla)) {
            return this.ruudut[rivi][moneskoRivilla].avaa();
        }
        return false;
    }

    /**
     *Merkkaa halutun ruudun.
     * @param rivi millä rivillä.
     * @param moneskoRivilla millä sarakkeella.
     * @return true, jos onnistui, muuten false.
     */
    public boolean merkkaaRuutu(int rivi, int moneskoRivilla) {
        if (this.onKunnollisetKoordinaatit(rivi, moneskoRivilla)) {
            return this.ruudut[rivi][moneskoRivilla].merkkaa();
        }
        return false;
    }

    /**
     *Palauttaa halutun ruudun ympärillä olevien miinojen määrän.
     * @param rivi
     * @param moneskoRivilla
     * @return palauttaa ympärillä olevien miinojen lukumäärän.
     */
    public int montakoMiinaaRuudunYmparilla(int rivi, int moneskoRivilla) {
        if (this.onKunnollisetKoordinaatit(rivi, moneskoRivilla)) {
            return this.ruudut[rivi][moneskoRivilla].montakoMiinaaYmparilla();
        }
        return -1;
    }

    /**
     *Tarkistaa onko haluttu ruutu avattu.
     * @param rivi millä rivillä.
     * @param moneskoRivilla millä sarakkeella.
     * @return palauttaa true, jos ruutu on avattu, muuten false.
     */
    public boolean onkoRuutuAvattu(int rivi, int moneskoRivilla) {
        if (this.onKunnollisetKoordinaatit(rivi, moneskoRivilla)) {
            return this.ruudut[rivi][moneskoRivilla].onkoAvattu();
        }
        return false;
    }

    /**
     *Tarkistaa onko haluttu ruutu merkattu.
     * @param rivi millä rivillä.
     * @param moneskoRivilla millä sarakkeella.
     * @return palauttaa true, jos ruutu on merkattu, muuten false.
     */
    public boolean onkoRuutuMerkattu(int rivi, int moneskoRivilla) {
        if (this.onKunnollisetKoordinaatit(rivi, moneskoRivilla)) {
            return this.ruudut[rivi][moneskoRivilla].onkoMerkattu();
        }
        return false;
    }
    
    /**
     *Tarkistaa onko haluttu ruutu miinoitettu.
     * @param rivi millä rivillä.
     * @param moneskoRivilla millä sarakkeella.
     * @return palauttaa true, jos ruudussa on miina, muuten false.
     */
    public boolean onkoRuutuMiinoitettu(int rivi, int moneskoRivilla) {
        if (this.onKunnollisetKoordinaatit(rivi, moneskoRivilla)) {
            return this.ruudut[rivi][moneskoRivilla].onkoMiina();
        }
        return false;
    }

        /**
     *Tarkistaa onko ympärillä olevien miinojen määrä annettu.
     * @param rivi millä rivillä.
     * @param moneskoRivilla millä sarakkeella.
     * @return palauttaa true, jos ruutu tietää ympärillä olevien miinojen määrän, muuten false.
     */
    public boolean onkoYmparillaOlevienMiinojenMaaraAnnettu(int rivi, int moneskoRivilla) {
        if (this.onKunnollisetKoordinaatit(rivi, moneskoRivilla)) {
            return this.ruudut[rivi][moneskoRivilla].onkoYmparillaOlevienMiinojenMaaraAnnettu();
        }
        return false;
    }

    private boolean onkoRuudukkoMiinoitettu() {
        return this.onkoRuudukkoMiinoitettu;
    }

    private void asetaMontakoMiinaa(int montakoMiinaa) {
        int ruutujenMaara = this.ruudukonKorkeus() * this.ruudukonLeveys();
        if (montakoMiinaa < 10) {
            this.montakoMiinaa = 10;
        } else if (montakoMiinaa > ruutujenMaara / 4) {
            this.montakoMiinaa = ruutujenMaara / 4;
        } else {
            this.montakoMiinaa = montakoMiinaa;
        }
    }

    private boolean asetaRuudukkoMiinoitetuksi() {
        if (this.onkoRuudukkoMiinoitettu == true) {
            return false;
        }
        this.onkoRuudukkoMiinoitettu = true;
        return true;
    }

    private int luoRandom(int korkeusTaiLeveys) {
        return (int) (Math.random() * korkeusTaiLeveys + 1);
    }

    private void asetaRuudukonLeveysJaKorkeus(int leveys, int korkeus) {
        if (leveys < 5) {
            this.leveys = 5;
        } else {
            this.leveys = leveys;
        }

        if (korkeus < 5) {
            this.korkeus = 5;
        } else {
            this.korkeus = korkeus;
        }
    }
}
