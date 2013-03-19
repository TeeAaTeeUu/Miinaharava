package tatu.miinaharava;

public class Ruudukko {

    private boolean[][] ruudut;
    private int leveys = 10;
    private int korkeus = 10;
    private static final boolean tyhja = false;
    private static final boolean miina = true;
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

    public boolean asetaMiina(int rivi, int moneskoRivilla) {
        if (this.onKunnollisetKoordinaatit(rivi, moneskoRivilla)) {
            if (this.onkoMiina(rivi, moneskoRivilla)) {
                return false;
            } else {
                this.ruudut[rivi - 1][moneskoRivilla - 1] = true;
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
            return this.ruudut[rivi - 1][moneskoRivilla - 1];
        }
        return false;

    }

    private boolean miinoitaRuudukko(int montakoMiinaa) {
        this.montakoMiinaa = montakoMiinaa;

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
        if (asetetutMiinat == this.miinojenMaara()) {
            if (asetetutMiinat == this.getMiinojenMaaraRaakaTarkistus()) {
                return true;
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

        this.ruudut = new boolean[this.ruudukonKorkeus()][this.ruudukonLeveys()];
    }

    public boolean onKunnollisetKoordinaatit(int rivi, int moneskoRivilla) {
        rivi -= 1;
        moneskoRivilla -= 1;
        if (rivi >= 0 && moneskoRivilla >= 0) {
            if (rivi < this.ruudukonKorkeus() && moneskoRivilla < this.ruudukonLeveys()) {
                return true;
            }
        }
        return false;
    }

    public int getMiinojenMaaraRaakaTarkistus() {
        int miinojenMaara = 0;

        for (int kasiteltavaRivi = 1; kasiteltavaRivi <= this.ruudukonKorkeus(); kasiteltavaRivi++) {
            for (int kasiteltavaRuutuRivilla = 1; kasiteltavaRuutuRivilla <= this.ruudukonLeveys(); kasiteltavaRuutuRivilla++) {
                if (this.onkoMiina(kasiteltavaRivi, kasiteltavaRuutuRivilla)) {
                    miinojenMaara++;
                }
            }
        }
        return miinojenMaara;
    }

    public void tulostaRuudukko() {
        for (int kasiteltavaRivi = 1; kasiteltavaRivi <= this.ruudukonKorkeus(); kasiteltavaRivi++) {
            for (int kasiteltavaRuutuRivilla = 1; kasiteltavaRuutuRivilla <= this.ruudukonLeveys(); kasiteltavaRuutuRivilla++) {
                if (this.onkoMiina(kasiteltavaRivi, kasiteltavaRuutuRivilla)) {
                    System.out.print("X ");
                } else {
                    System.out.print("O ");
                }
            }
            System.out.println("");
        }
    }
}
