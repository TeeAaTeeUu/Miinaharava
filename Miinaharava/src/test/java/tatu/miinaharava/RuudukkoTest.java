package tatu.miinaharava;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class RuudukkoTest {

    Ruudukko ruudukko;

    @Before
    public void setUp() {
        ruudukko = new Ruudukko();
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
        assertEquals(10, this.ruudukko.miinojenMaara());
    }

    @Test
    public void miinojaOnOikeaMaara() {
        ruudukko = new Ruudukko(15, 14, 13);
        assertEquals(13, this.ruudukko.miinojenMaara());
    }
    
    @Test
    public void eiKahtaMiinaaPaallekkain() {
        ruudukko.asetaMiina(8, 8);
        assertEquals(false, ruudukko.asetaMiina(8, 8));
    }
    
    @Test
    public void miinaaEiVoiTarkastaaJosHuonotKordinaatit() {
        assertEquals(false, ruudukko.onkoMiina(11, 8));
    }

    @Test
    public void miinojaOnOikeaMaaraRaakaTarkistus() {
        ruudukko = new Ruudukko(15, 14, 13);
        assertEquals(13, this.ruudukko.getMiinojenMaaraRaakaTarkistus());
    }

    @Test
    public void miinojaVakionaOnOikeaMaaraRaakaTarkistus() {
        assertEquals(10, this.ruudukko.getMiinojenMaaraRaakaTarkistus());
    }

    @Test
    public void huonotKoordinaatitTunnistetaan() {
        assertEquals(true, this.ruudukko.onKunnollisetKoordinaatit(10, 8));
        assertEquals(false, this.ruudukko.onKunnollisetKoordinaatit(11, 8));
        assertEquals(true, this.ruudukko.onKunnollisetKoordinaatit(1, 1));
        assertEquals(true, this.ruudukko.onKunnollisetKoordinaatit(10, 10));
        assertEquals(false, this.ruudukko.onKunnollisetKoordinaatit(10, 11));
    }
}