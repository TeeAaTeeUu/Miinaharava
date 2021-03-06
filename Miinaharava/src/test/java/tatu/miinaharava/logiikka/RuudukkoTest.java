package tatu.miinaharava.logiikka;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class RuudukkoTest {

    Ruudukko ruudukko;

    @Before
    public void setUp() {
        ruudukko = new Ruudukko();
    }
    
    public void tulostaRuudukko() {
        for (int kasiteltavaRivi = 1; kasiteltavaRivi <= this.ruudukko.ruudukonKorkeus(); kasiteltavaRivi++) {
            for (int kasiteltavaRuutuRivilla = 1; kasiteltavaRuutuRivilla <= this.ruudukko.ruudukonLeveys(); kasiteltavaRuutuRivilla++) {
                if (this.ruudukko.onkoRuutuMiinoitettu(kasiteltavaRivi, kasiteltavaRuutuRivilla)) {
                    System.out.print("X ");
                } else {
                    System.out.print("O ");
                }
            }
            System.out.println("");
        }
    }
    
    public int getMiinojenMaaraRaakaTarkistus() {
        int miinojenMaara = 0;

        for (int kasiteltavaRivi = 1; kasiteltavaRivi <= this.ruudukko.ruudukonKorkeus(); kasiteltavaRivi++) {
            for (int kasiteltavaRuutuRivilla = 1; kasiteltavaRuutuRivilla <= this.ruudukko.ruudukonLeveys(); kasiteltavaRuutuRivilla++) {
                if (this.ruudukko.onkoRuutuMiinoitettu(kasiteltavaRivi, kasiteltavaRuutuRivilla)) {
                    miinojenMaara++;
                }
            }
        }
        return miinojenMaara;
    }

    @Test
    public void leveysOnOikeaVakiona() {
        assertEquals(10, this.ruudukko.ruudukonLeveys());
    }

    @Test
    public void korkeusOnOikeaVakiona() {
        assertEquals(10, this.ruudukko.ruudukonKorkeus());
    }

    @Test
    public void leveysOnOikea() {
        ruudukko = new Ruudukko(15, 14, 13);
        assertEquals(14, this.ruudukko.ruudukonLeveys());
    }

    @Test
    public void leveysOnOikeaKunLiianPieniAsetettu() {
        ruudukko = new Ruudukko(3, 3, 5);
        assertEquals(5, this.ruudukko.ruudukonLeveys());
    }

    @Test
    public void KorkeusOnOikeaKunLiiasuuriAsetettu() {
        ruudukko = new Ruudukko(75, 45, 5);
        assertEquals(50, this.ruudukko.ruudukonKorkeus());
    }
    
    @Test
    public void leveysOnOikeaKunLiianSuuriAsetettu() {
        ruudukko = new Ruudukko(45, 75, 5);
        assertEquals(50, this.ruudukko.ruudukonLeveys());
    }

    @Test
    public void KorkeusOnOikeaKunLiianPieniAsetettu() {
        ruudukko = new Ruudukko(3, 3, 5);
        assertEquals(5, this.ruudukko.ruudukonKorkeus());
    }

    @Test
    public void korkeusOnOikea() {
        ruudukko = new Ruudukko(15, 14, 13);
        assertEquals(15, this.ruudukko.ruudukonKorkeus());
    }

    @Test
    public void miinojaOnOikeaMaaraVakiona() {
        assertEquals(20, this.ruudukko.miinojenMaara());
    }
    
    @Test
    public void miinojaOnOikeaMaaraJosLiianPieniAnnettu() {
        ruudukko = new Ruudukko(3, 3, 4);
        assertEquals(5, this.ruudukko.miinojenMaara());
    }

    @Test
    public void miinojaOnOikeaMaara() {
        ruudukko = new Ruudukko(15, 14, 13);
        assertEquals(13, this.ruudukko.miinojenMaara());
    }
    
    @Test
    public void miinojenMaaraEiVoiOllaLiianSuuri() {
        ruudukko = new Ruudukko(15, 14, 9999999);
        int miinojenMaksimiMaara = this.ruudukko.ruudukonKorkeus() * this.ruudukko.ruudukonLeveys() / 4;
        assertEquals(miinojenMaksimiMaara, this.ruudukko.miinojenMaara());
    }
    
    @Test
    public void eiKahtaMiinaaPaallekkain() {
        ruudukko.asetaMiina(8, 8);
        assertEquals(false, ruudukko.asetaMiina(8, 8));
    }
    
    @Test
    public void miinaaEiVoiLaittaaHuonoonKoordinaattiin() {
        assertEquals(false, ruudukko.asetaMiina(11, 8));
    }
    
    @Test
    public void miinaaEiVoiTarkastaaJosHuonotKordinaatit() {
        assertEquals(false, ruudukko.onkoRuutuMiinoitettu(11, 8));
    }

    @Test
    public void miinojaOnOikeaMaaraRaakaTarkistus() {
        ruudukko = new Ruudukko(15, 14, 13);
        assertEquals(13, getMiinojenMaaraRaakaTarkistus());
    }

    @Test
    public void miinojaVakionaOnOikeaMaaraRaakaTarkistus() {
        assertEquals(20, getMiinojenMaaraRaakaTarkistus());
    }

    @Test
    public void huonotKoordinaatitTunnistetaan() {
        assertEquals(true, this.ruudukko.onKunnollisetKoordinaatit(10, 8));
        assertEquals(false, this.ruudukko.onKunnollisetKoordinaatit(11, 8));
        assertEquals(true, this.ruudukko.onKunnollisetKoordinaatit(1, 1));
        assertEquals(true, this.ruudukko.onKunnollisetKoordinaatit(10, 10));
        assertEquals(false, this.ruudukko.onKunnollisetKoordinaatit(10, 11));
    }
    
    @Test
    public void ruudukkoOnVakionaOikea() {
        ruudukko = new Ruudukko(12,14);
        assertEquals(14, ruudukko.ruudukonLeveys());
        assertEquals(12, ruudukko.ruudukonKorkeus());
    }
    
    @Test
    public void ymparillaOlevienMiinojenMaaranAsettaminenToimii() {
        ruudukko.asetaMontakoMiinaaYmparilla(7, 6, 1);
        assertEquals(false, ruudukko.asetaMontakoMiinaaYmparilla(7, 6, 1));
        assertEquals(true, ruudukko.onkoYmparillaOlevienMiinojenMaaraAnnettu(7, 6));
    }
}