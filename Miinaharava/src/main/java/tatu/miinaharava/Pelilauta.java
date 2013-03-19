package tatu.miinaharava;

public class Pelilauta extends Ruudukko {

    private boolean[][] avatutRuudut;
    private boolean[][] merkatutRuudut;
    private int[][] lasketutRuudut;
    private boolean miinaAvattu = false;

    public Pelilauta(int korkeus, int leveys, int montakoMiinaa) {
        super(korkeus, leveys, montakoMiinaa);
        this.alustaAvatutRuudut();
        this.alustaMerkatutRuudut();
        this.alustaLasketutRuudut();
    }

    public Pelilauta() {
        this.alustaAvatutRuudut();
        this.alustaMerkatutRuudut();
        this.alustaLasketutRuudut();
    }

    public boolean onkoMiinaAvattu() {
        return this.miinaAvattu;
    }

    private boolean asetaMiinaAvatuksi() {
        if (this.miinaAvattu) {
            return false;
        } else {
            this.miinaAvattu = true;
            return true;
        }
    }

    public boolean onkoRuutuAvattu(int rivi, int moneskoRivilla) {
        if (this.onKunnollisetKoordinaatit(rivi, moneskoRivilla)) {
            return this.avatutRuudut[rivi - 1][moneskoRivilla - 1];
        }
        return false;
    }

    public boolean onkoRuutuMerkattu(int rivi, int moneskoRivilla) {
        if (this.onKunnollisetKoordinaatit(rivi, moneskoRivilla)) {
            return this.merkatutRuudut[rivi - 1][moneskoRivilla - 1];
        }
        return false;
    }

    public int ruudunLuku(int rivi, int moneskoRivilla) {
        if (this.onKunnollisetKoordinaatit(rivi, moneskoRivilla)) {
            return this.lasketutRuudut[rivi - 1][moneskoRivilla - 1];
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

        if (this.onkoMiina(rivi, moneskoRivilla)) {
            this.miinaAvattu = true;
            return true;
        }
        return this.avaaRuutuLoop(rivi, moneskoRivilla);
    }

    private boolean avaaRuutuLoop(int rivi, int moneskoRivilla) {
        if (this.onKunnollisetKoordinaatit(rivi, moneskoRivilla) == false) {
            return false;
        }

        if (this.onkoRuutuAvattu(rivi, moneskoRivilla) == true) {
            return true;
        }

        if (this.onkoMiina(rivi, moneskoRivilla)) {
            this.miinaAvattu = true;
            return true;
        }

        int ymparillaOlevienMiinojenMaara = this.tarkistaYmparoidytRuudut(rivi, moneskoRivilla);
        this.asetaRuudulleLuku(rivi, moneskoRivilla, ymparillaOlevienMiinojenMaara);

        if (ymparillaOlevienMiinojenMaara == 0) {
            this.asetaRuutuAvatuksi(rivi, moneskoRivilla);
            return this.avaaYmparoidutRuudut(rivi, moneskoRivilla);
        }
        this.asetaRuutuAvatuksi(rivi, moneskoRivilla);

        return true;
    }

    private boolean asetaRuutuAvatuksi(int rivi, int moneskoRivilla) {
        if (this.onKunnollisetKoordinaatit(rivi, moneskoRivilla)) {
            if (this.onkoRuutuAvattu(rivi, moneskoRivilla)) {
                return false;
            } else {
                this.avatutRuudut[rivi - 1][moneskoRivilla - 1] = true;
                return true;
            }
        }
        return false;
    }

    public boolean asetaRuutuMerkatuksi(int rivi, int moneskoRivilla) {
        if (this.onKunnollisetKoordinaatit(rivi, moneskoRivilla)) {
            if (this.onkoRuutuAvattu(rivi, moneskoRivilla)) {
                return false;
            } else {
                this.merkatutRuudut[rivi - 1][moneskoRivilla - 1] = true;
                return true;
            }
        }
        return false;
    }

    private boolean asetaRuudulleLuku(int rivi, int moneskoRivilla, int ymparillaOlevienMiinojenMaara) {
        if (this.onKunnollisetKoordinaatit(rivi, moneskoRivilla)) {
            this.lasketutRuudut[rivi - 1][moneskoRivilla - 1] = ymparillaOlevienMiinojenMaara;
            return true;
        }
        return false;
    }

    private int tarkistaYmparoidytRuudut(int rivi, int moneskoRivilla) {
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
        this.avaaRuutuLoop(rivi - 1, moneskoRivilla - 1);
        this.avaaRuutuLoop(rivi - 1, moneskoRivilla);
        this.avaaRuutuLoop(rivi - 1, moneskoRivilla + 1);
        this.avaaRuutuLoop(rivi, moneskoRivilla - 1);
        this.avaaRuutuLoop(rivi, moneskoRivilla + 1);
        this.avaaRuutuLoop(rivi + 1, moneskoRivilla - 1);
        this.avaaRuutuLoop(rivi + 1, moneskoRivilla);
        this.avaaRuutuLoop(rivi + 1, moneskoRivilla + 1);

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

    public void tulostaAvatutRuudut() {
        for (int kasiteltavaRivi = 1; kasiteltavaRivi <= this.ruudukonKorkeus(); kasiteltavaRivi++) {
            for (int kasiteltavaRuutuRivilla = 1; kasiteltavaRuutuRivilla <= this.ruudukonLeveys(); kasiteltavaRuutuRivilla++) {
                if (this.onkoRuutuAvattu(kasiteltavaRivi, kasiteltavaRuutuRivilla)) {
                    System.out.print("X ");
                } else {
                    System.out.print("O ");
                }
            }
            System.out.println("");
        }
    }

    public void tulostaLasketutRuudut() {
        for (int kasiteltavaRivi = 1; kasiteltavaRivi <= this.ruudukonKorkeus(); kasiteltavaRivi++) {
            for (int kasiteltavaRuutuRivilla = 1; kasiteltavaRuutuRivilla <= this.ruudukonLeveys(); kasiteltavaRuutuRivilla++) {
                System.out.print(this.ruudunLuku(kasiteltavaRivi, kasiteltavaRuutuRivilla));
            }
            System.out.println("");
        }
    }

    private void alustaAvatutRuudut() {
        this.avatutRuudut = new boolean[this.ruudukonKorkeus()][this.ruudukonLeveys()];

        for (int kasiteltavaRivi = 1; kasiteltavaRivi <= this.ruudukonKorkeus(); kasiteltavaRivi++) {
            for (int kasiteltavaRuutuRivilla = 1; kasiteltavaRuutuRivilla <= this.ruudukonLeveys(); kasiteltavaRuutuRivilla++) {
                this.avatutRuudut[kasiteltavaRivi - 1][kasiteltavaRuutuRivilla - 1] = false;
            }
        }
    }

    private void alustaMerkatutRuudut() {
        this.merkatutRuudut = new boolean[this.ruudukonKorkeus()][this.ruudukonLeveys()];
    }

    private boolean alustaLasketutRuudut() {
        this.lasketutRuudut = new int[this.ruudukonKorkeus()][this.ruudukonLeveys()];

        for (int kasiteltavaRivi = 1; kasiteltavaRivi <= this.ruudukonKorkeus(); kasiteltavaRivi++) {
            for (int kasiteltavaRuutuRivilla = 1; kasiteltavaRuutuRivilla <= this.ruudukonLeveys(); kasiteltavaRuutuRivilla++) {
                this.asetaRuudulleLuku(kasiteltavaRivi, kasiteltavaRuutuRivilla, -1);
            }
        }
        return true;
    }

    public boolean etsiJaAvaaMiina() {
        for (int kasiteltavaRivi = 1; kasiteltavaRivi <= this.ruudukonKorkeus(); kasiteltavaRivi++) {
            for (int kasiteltavaRuutuRivilla = 1; kasiteltavaRuutuRivilla <= this.ruudukonLeveys(); kasiteltavaRuutuRivilla++) {
                if (this.onkoMiina(kasiteltavaRivi, kasiteltavaRuutuRivilla)) {
                    System.out.println("löytyi!");
                    System.out.println(kasiteltavaRivi + ":" + kasiteltavaRuutuRivilla);
                    return this.avaaRuutu(kasiteltavaRivi, kasiteltavaRuutuRivilla);
                }
            }
        }
        return false;
    }
}
