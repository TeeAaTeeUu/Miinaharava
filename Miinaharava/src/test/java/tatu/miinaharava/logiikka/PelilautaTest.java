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
                    System.out.print("avattu ");
                } else {
                    System.out.print("kiinni ");
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
                    if (this.pelilauta.onkoRuutuAvattu(kasiteltavaRivi, kasiteltavaRuutuRivilla) == false) {
                        System.out.println("löytyi!");
                        System.out.println(kasiteltavaRivi + ":" + kasiteltavaRuutuRivilla);
                        return this.pelilauta.avaaRuutu(kasiteltavaRivi, kasiteltavaRuutuRivilla);
                    }
                }
            }
        }

        return false;
    }

    private int etsiJaAvaaKaikkiTyhjat() {
        return this.etsiJaAvaaTaiMerkkaaKaikkiTyhjat(true);
    }

    private int etsiJaMerkkaaKaikkiMiinat() {
        return this.etsiJaAvaaTaiMerkkaaKaikkiTyhjat(false);
    }

    private int etsiJaAvaaTaiMerkkaaKaikkiTyhjat(boolean avaa) {
        int miinoja = 0;
        for (int kasiteltavaRivi = 1; kasiteltavaRivi <= this.pelilauta.ruudukonKorkeus(); kasiteltavaRivi++) {
            for (int kasiteltavaRuutuRivilla = 1; kasiteltavaRuutuRivilla <= this.pelilauta.ruudukonLeveys(); kasiteltavaRuutuRivilla++) {
                if (this.pelilauta.onkoRuutuAvattu(kasiteltavaRivi, kasiteltavaRuutuRivilla) == false) {
                    if (this.pelilauta.onkoRuutuMiinoitettu(kasiteltavaRivi, kasiteltavaRuutuRivilla) == false) {
                        this.pelilauta.avaaRuutu(kasiteltavaRivi, kasiteltavaRuutuRivilla);
                    } else {
                        ++miinoja;
                        if (avaa == false) {
                            this.pelilauta.merkkaaRuutu(kasiteltavaRivi, kasiteltavaRuutuRivilla);
                        }
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
    public void peliEiOleVoitettuJosSeEiOleVoitettu() {
        pelilauta.asetaPeliAlkaneeksi();
        pelilauta.avaaRuutu(7, 6);
        assertEquals(false, this.pelilauta.OnkoPeliVoitettu());
    }

    @Test
    public void peliOnVoitettuKunKaikkiMiinatOnMerkattu() {
        pelilauta.asetaPeliAlkaneeksi();

        this.etsiJaMerkkaaKaikkiMiinat();

        assertEquals(true, this.pelilauta.OnkoPeliVoitettu());
    }

    @Test
    public void peliOnVoitettuKunKaikkiRuudutOnAvattuIlmanMiinoja() {
        pelilauta.asetaPeliAlkaneeksi();

        System.out.println(this.etsiJaAvaaKaikkiTyhjat());

        this.tulostaAvatutRuudut();
        System.out.println("...");
        this.tulostaMiinat();

        assertEquals(true, this.pelilauta.OnkoPeliVoitettu());
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

    @Test
    public void pelilautaVakionaOikea() {
        pelilauta = new Pelilauta(13, 12);
        assertEquals(13, pelilauta.ruudukonKorkeus());
        assertEquals(12, pelilauta.ruudukonLeveys());
    }

    @Test
    public void pelilautaVakionaOikea2() {
        pelilauta = new Pelilauta(13, 12, 15, true);
        assertEquals(13, pelilauta.ruudukonKorkeus());
        assertEquals(12, pelilauta.ruudukonLeveys());
    }

    @Test
    public void pelilautaVakionaOikea3() {
        pelilauta = new Pelilauta(true);
        assertEquals(10, pelilauta.ruudukonKorkeus());
        assertEquals(10, pelilauta.ruudukonLeveys());
    }

    @Test
    public void pelinAsettaminenAlkaneeksiToimii() {
        pelilauta.asetaPeliAlkaneeksi();
        pelilauta.asetaPeliAlkaneeksi();
        assertEquals(true, pelilauta.onkoPeliAlkanut());
    }
}