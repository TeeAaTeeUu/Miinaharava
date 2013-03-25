package tatu.miinaharava.logiikka;

public class Ruudukko {

    public Ruutu[][] ruudut;
    private int leveys = 10;
    private int korkeus = 10;
    private int montakoMiinaa = 10;

    public int ruudukonKorkeus() {
        return this.korkeus;
    }

    public int miinojenMaara() {
        return this.montakoMiinaa;
    }

    public int ruudukonLeveys() {
        return this.leveys;
    }

    boolean asetaMiina(int rivi, int moneskoRivilla) {
        if (this.onKunnollisetKoordinaatit(rivi, moneskoRivilla)) {
            if (this.onkoMiina(rivi, moneskoRivilla)) {
                return false;
            } else {
                this.ruudut[rivi][moneskoRivilla].asetaMiina();
                return true;
            }
        }
        return false;
    }

    public Ruudukko() {
        this.alustaRuudut(this.korkeus, this.leveys);
        this.miinoitaRuudukko(this.montakoMiinaa);
    }

    public Ruudukko(int korkeus, int leveys, int montakoMiinaa) {
        this.alustaRuudut(korkeus, leveys);
        this.miinoitaRuudukko(montakoMiinaa);
    }

    public boolean onkoMiina(int rivi, int moneskoRivilla) {
        if (this.onKunnollisetKoordinaatit(rivi, moneskoRivilla)) {
            return this.ruudut[rivi][moneskoRivilla].onkoMiina();
        }
        return false;

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
    
    public boolean onkoYmparillaOlevienMiinojenMaaraAnnettu(int rivi, int moneskoRivilla) {
        if (this.onKunnollisetKoordinaatit(rivi, moneskoRivilla)) {
            return this.ruudut[rivi][moneskoRivilla].onkoYmparillaOlevienMiinojenMaaraAnnettu();
        }
        return false;
    }

    private boolean miinoitaRuudukko(int montakoMiinaa) {
        if (montakoMiinaa < 5) {
            this.montakoMiinaa = 5;
        } else {
            this.montakoMiinaa = montakoMiinaa;
        }
        
        int kasiteltavaRivi;
        int kasiteltavaRuutuRivilla;
        int asetetutMiinat = 0;

        while (asetetutMiinat < this.montakoMiinaa) {
            kasiteltavaRivi = (int) (Math.random() * this.korkeus + 1);
            kasiteltavaRuutuRivilla = (int) (Math.random() * this.leveys + 1);

            if (this.onkoMiina(kasiteltavaRivi, kasiteltavaRuutuRivilla) == false) {
                this.asetaMiina(kasiteltavaRivi, kasiteltavaRuutuRivilla);

                asetetutMiinat++;
            }
        }
        return false;
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

    public boolean onKunnollisetKoordinaatit(int rivi, int moneskoRivilla) {
        if (rivi >= 1 && moneskoRivilla >= 1) {
            if (rivi <= this.ruudukonKorkeus() && moneskoRivilla <= this.ruudukonLeveys()) {
                return true;
            }
        }
        return false;
    }
}
