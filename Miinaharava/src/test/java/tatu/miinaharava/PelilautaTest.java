package tatu.miinaharava;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PelilautaTest {
    
    Pelilauta pelilauta;
    
    @Before
    public void setUp() {
        pelilauta = new Pelilauta();
    }
    
    @Test
    public void avaamatonRuutuOnAvaamaton() {
        assertEquals(false, this.pelilauta.onkoRuutuAvattu(7, 4));
    }
    
    @Test
    public void merkkaamatonRuutuOnMerkkaamaton() {
        pelilauta.asetaRuutuMerkatuksi(7, 8);
        assertEquals(false, this.pelilauta.onkoRuutuMerkattu(7, 4));
    }
    
    @Test
    public void ruudunMerkkaaminenToimii() {
        pelilauta.asetaRuutuMerkatuksi(7, 8);
        assertEquals(true, this.pelilauta.onkoRuutuMerkattu(7, 8));
    }
    
    @Test
    public void merkkaamatonRuutuOnMerkkaamatonVaikkaEiVakioKokoinen() {
        pelilauta.asetaRuutuMerkatuksi(13, 8);
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
        pelilauta.etsiJaAvaaMiina();
        assertEquals(true, pelilauta.onkoMiinaAvattu());
    }
}