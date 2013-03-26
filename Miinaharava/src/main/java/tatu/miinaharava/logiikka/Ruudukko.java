package tatu.miinaharava.logiikka;

public class Ruudukko {

    public Ruutu[][] ruudut;
    private int leveys = 10;
    private int korkeus = 10;
    private int montakoMiinaa = 20;
    private boolean onkoRuudukkoMiinoitettu = false;

    public int ruudukonKorkeus() {
        return this.korkeus;
    }

    public int miinojenMaara() {
        return this.montakoMiinaa;
    }

    public int ruudukonLeveys() {
        return this.leveys;
    }

    public Ruudukko() {
        this(true);
    }

    public Ruudukko(boolean miinoitetaanko) {
        this.alustaRuudut(this.korkeus, this.leveys);
        if (miinoitetaanko == true) {
            this.miinoitaRuudukko(this.montakoMiinaa);
        }
    }

    public Ruudukko(int korkeus, int leveys, int montakoMiinaa) {
        this(korkeus, leveys, montakoMiinaa, true);
    }
    

    public Ruudukko(int korkeus, int leveys, int montakoMiinaa, boolean miinoitetaanko) {
        this.alustaRuudut(korkeus, leveys);
        if (miinoitetaanko == true) {
            this.miinoitaRuudukko(montakoMiinaa);
        } else {
            this.asetaMontakoMiinaa(montakoMiinaa);
        }
    }

    public Ruudukko(int korkeus, int leveys) {
        this.alustaRuudut(korkeus, leveys);
        this.miinoitaRuudukko(montakoMiinaa);
    }

    public final boolean miinoitaRuudukko(int montakoMiinaa) {
        return this.miinoitaRuudukko(montakoMiinaa, -1, -1);
    }

    public boolean miinoitaRuudukko(int montakoMiinaa, int mitaRiviaPainettu, int MitaRuutuaRivillaPainettu) {
        if (this.onkoRuudukkoMiinoitettu() == false) {
            this.asetaMontakoMiinaa(montakoMiinaa);

            this.miinoitaRuudukkoLoop(mitaRiviaPainettu, MitaRuutuaRivillaPainettu);

            if (this.asetaRuudukkoMiinoitetuksi()) {
                return true;
            }
        }
        return false;
    }

    private void miinoitaRuudukkoLoop(int mitaRiviaPainettu, int MitaRuutuaRivillaPainettu) {
        int asetetutMiinat = 0;

        while (asetetutMiinat < this.montakoMiinaa) {
            int kasiteltavaRivi = (int) (Math.random() * this.korkeus + 1);
            int kasiteltavaRuutuRivilla = (int) (Math.random() * this.leveys + 1);

            if (this.onkoRuutuMiinoitettu(kasiteltavaRivi, kasiteltavaRuutuRivilla) == false) {
                if (mitaRiviaPainettu != kasiteltavaRivi && MitaRuutuaRivillaPainettu != kasiteltavaRuutuRivilla) {

                    this.asetaMiina(kasiteltavaRivi, kasiteltavaRuutuRivilla);
                    asetetutMiinat++;
                }
            }
        }
    }

    private void alustaRuudut(int korkeus, int leveys) {
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

        this.ruudut = new Ruutu[this.korkeus + 1][this.leveys + 1];
        for (int kasiteltavaRivi = 1; kasiteltavaRivi <= this.ruudukonKorkeus(); kasiteltavaRivi++) {
            for (int kasiteltavaRuutuRivilla = 1; kasiteltavaRuutuRivilla <= this.ruudukonLeveys(); kasiteltavaRuutuRivilla++) {
                this.ruudut[kasiteltavaRivi][kasiteltavaRuutuRivilla] = new Ruutu(kasiteltavaRivi, kasiteltavaRuutuRivilla);
            }
        }
    }

    protected boolean onKunnollisetKoordinaatit(int rivi, int moneskoRivilla) {
        if (rivi >= 1 && moneskoRivilla >= 1) {
            if (rivi <= this.ruudukonKorkeus() && moneskoRivilla <= this.ruudukonLeveys()) {
                return true;
            }
        }
        return false;
    }

    boolean asetaMiina(int rivi, int moneskoRivilla) {
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

    boolean asetaMontakoMiinaaYmparilla(int rivi, int moneskoRivilla, int ymparillaOlevienMiinojenMaara) {
        if (this.onKunnollisetKoordinaatit(rivi, moneskoRivilla)) {
            this.ruudut[rivi][moneskoRivilla].asetaMontakoMiinaaYmparilla(ymparillaOlevienMiinojenMaara);
            return true;
        }
        return false;
    }

    public boolean avaaRuutu(int rivi, int moneskoRivilla) {
        if (this.onKunnollisetKoordinaatit(rivi, moneskoRivilla)) {
            return this.ruudut[rivi][moneskoRivilla].avaa();
        }
        return false;
    }

    public boolean merkkaaRuutu(int rivi, int moneskoRivilla) {
        if (this.onKunnollisetKoordinaatit(rivi, moneskoRivilla)) {
            return this.ruudut[rivi][moneskoRivilla].merkkaa();
        }
        return false;
    }

    public int montakoMiinaaRuudunYmparilla(int rivi, int moneskoRivilla) {
        if (this.onKunnollisetKoordinaatit(rivi, moneskoRivilla)) {
            return this.ruudut[rivi][moneskoRivilla].montakoMiinaaYmparilla();
        }
        return -1;
    }

    public boolean onkoRuutuAvattu(int rivi, int moneskoRivilla) {
        if (this.onKunnollisetKoordinaatit(rivi, moneskoRivilla)) {
            return this.ruudut[rivi][moneskoRivilla].onkoAvattu();
        }
        return false;
    }

    public boolean onkoRuutuMerkattu(int rivi, int moneskoRivilla) {
        if (this.onKunnollisetKoordinaatit(rivi, moneskoRivilla)) {
            return this.ruudut[rivi][moneskoRivilla].onkoMerkattu();
        }
        return false;
    }

    public boolean onkoRuutuMiinoitettu(int rivi, int moneskoRivilla) {
        if (this.onKunnollisetKoordinaatit(rivi, moneskoRivilla)) {
            return this.ruudut[rivi][moneskoRivilla].onkoMiina();
        }
        return false;
    }

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
        if (montakoMiinaa < 10) {
            this.montakoMiinaa = 10;
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
}
