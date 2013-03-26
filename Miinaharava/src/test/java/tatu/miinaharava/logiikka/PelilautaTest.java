package tatu.miinaharava.logiikka;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PelilautaTest {

    Pelilauta pelilauta;

    @Before
    public void setUp() {
        pelilauta = new Pelilauta();
//        this.tulostaMiinat();
//        System.out.println("väli");
    }

    public void tulostaLasketutRuudut() {
        for (int kasiteltavaRivi = 1; kasiteltavaRivi <= this.pelilauta.ruudukonKorkeus(); kasiteltavaRivi++) {
            for (int kasiteltavaRuutuRivilla = 1; kasiteltavaRuutuRivilla <= this.pelilauta.ruudukonLeveys(); kasiteltavaRuutuRivilla++) {
                System.out.print(this.pelilauta.montakoMiinaaRuudunYmparilla(kasiteltavaRivi, kasiteltavaRuutuRivilla));
            }
            System.out.println("");
        }
    }

    public void tulostaAvatutRuudut() {
        for (int kasiteltavaRivi = 1; kasiteltavaRivi <= this.pelilauta.ruudukonKorkeus(); kasiteltavaRivi++) {
            for (int kasiteltavaRuutuRivilla = 1; kasiteltavaRuutuRivilla <= this.pelilauta.ruudukonLeveys(); kasiteltavaRuutuRivilla++) {
                if (this.pelilauta.onkoRuutuAvattu(kasiteltavaRivi, kasiteltavaRuutuRivilla)) {
                    System.out.print("X ");
                } else {
                    System.out.print("O ");
                }
            }
            System.out.println("");
        }
    }
    
    public void tulostaMiinat() {
        for (int kasiteltavaRivi = 1; kasiteltavaRivi <= this.pelilauta.ruudukonKorkeus(); kasiteltavaRivi++) {
            for (int kasiteltavaRuutuRivilla = 1; kasiteltavaRuutuRivilla <= this.pelilauta.ruudukonLeveys(); kasiteltavaRuutuRivilla++) {
                if (this.pelilauta.onkoRuutuMiinoitettu(kasiteltavaRivi, kasiteltavaRuutuRivilla)) {
                    System.out.print("X ");
                } else {
                    System.out.print("O ");
                }
            }
            System.out.println("");
        }
    }

    private boolean etsiJaAvaaMiina() {
        for (int kasiteltavaRivi = 1; kasiteltavaRivi <= this.pelilauta.ruudukonKorkeus(); kasiteltavaRivi++) {
            for (int kasiteltavaRuutuRivilla = 1; kasiteltavaRuutuRivilla <= this.pelilauta.ruudukonLeveys(); kasiteltavaRuutuRivilla++) {
                if (this.pelilauta.onkoRuutuMiinoitettu(kasiteltavaRivi, kasiteltavaRuutuRivilla)) {
                    System.out.println("löytyi!");
                    System.out.println(kasiteltavaRivi + ":" + kasiteltavaRuutuRivilla);
                    return this.pelilauta.avaaRuutu(kasiteltavaRivi, kasiteltavaRuutuRivilla);
                }
            }
        }
        return false;
    }

    private int etsiJaAvaaKaikkiTyhjat() {
        int miinoja = 0;
        for (int kasiteltavaRivi = 1; kasiteltavaRivi <= this.pelilauta.ruudukonKorkeus(); kasiteltavaRivi++) {
            for (int kasiteltavaRuutuRivilla = 1; kasiteltavaRuutuRivilla <= this.pelilauta.ruudukonLeveys(); kasiteltavaRuutuRivilla++) {
                if (this.pelilauta.onkoRuutuAvattu(kasiteltavaRivi, kasiteltavaRuutuRivilla) == false) {
                    if (this.pelilauta.onkoRuutuMiinoitettu(kasiteltavaRivi, kasiteltavaRuutuRivilla) == false) {
                        this.pelilauta.avaaRuutu(kasiteltavaRivi, kasiteltavaRuutuRivilla);
                    } else {
                        ++miinoja;
                    }
                }
            }
        }
        return miinoja;
    }

    @Test
    public void avaamatonRuutuOnAvaamaton() {
        assertEquals(false, this.pelilauta.onkoRuutuAvattu(7, 4));
    }

    @Test
    public void merkkaamatonRuutuOnMerkkaamaton() {
        pelilauta.merkkaaRuutu(7, 8);
        assertEquals(false, this.pelilauta.onkoRuutuMerkattu(7, 4));
    }

    @Test
    public void ruudunMerkkaaminenToimii() {
        pelilauta.merkkaaRuutu(7, 8);
        assertEquals(true, this.pelilauta.onkoRuutuMerkattu(7, 8));
    }

    @Test
    public void merkkaamatonRuutuOnMerkkaamatonVaikkaEiVakioKokoinen() {
        pelilauta.merkkaaRuutu(13, 8);
        pelilauta = new Pelilauta(13, 14, 15);
        assertEquals(false, this.pelilauta.onkoRuutuMerkattu(7, 4));
    }

    @Test
    public void avaaRuudun() {
        pelilauta.avaaRuutu(7, 6);
        assertEquals(true, this.pelilauta.onkoRuutuAvattu(7, 6));
    }

    @Test
    public void miinanAvaaminenAsettaaMiinanAvatuksi() {
        this.etsiJaAvaaMiina();
        assertEquals(true, pelilauta.onkoMiinaAvattu());
    }

    @Test
    public void ruutuEiVoiOllaAvattuJosHuonotKoordinaatit() {
        assertEquals(false, pelilauta.onkoRuutuAvattu(11, 8));
    }

    @Test
    public void ruutuEiVoiOllaMerkattuJosHuonotKoordinaatit() {
        assertEquals(false, pelilauta.onkoRuutuMerkattu(11, 8));
    }

    @Test
    public void ruudunLukuOnOikea() {
        int miinoja = pelilauta.tarkistaYmparoidytRuudut(8, 8);
        pelilauta.asetaMontakoMiinaaYmparilla(8, 8, miinoja);
        assertEquals(miinoja, pelilauta.montakoMiinaaRuudunYmparilla(8, 8));
    }

    @Test
    public void kaikkienRuutujenAvaaminenOnnistuu() {
        assertEquals(pelilauta.miinojenMaara(), etsiJaAvaaKaikkiTyhjat());
    }
}