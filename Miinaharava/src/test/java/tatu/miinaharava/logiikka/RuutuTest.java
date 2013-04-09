package tatu.miinaharava.logiikka;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class RuutuTest {

    Ruutu ruutu;

    @Before
    public void setUp() {
        ruutu = new Ruutu(7, 13);
    }

    @Test
    public void rivinPalautusToimii() {
        assertEquals(7, ruutu.palautaRivi());
    }
    
    @Test
    public void moneskoRivillaPalautusToimii() {
        assertEquals(13, ruutu.palautaMoneskoRivilla());
    }
    
    @Test
    public void ymparillaOlevienMiinojenLukuMaaraToimii() {
        ruutu.asetaMontakoMiinaaYmparilla(2);
        assertEquals(2, ruutu.montakoMiinaaYmparilla());
        assertEquals(true, ruutu.onkoYmparillaOlevienMiinojenMaaraAnnettu());
    }
    
    @Test
    public void ymparillaOlevienMiinojenLukuMaaraEiVoiAsettaaKahdesti() {
        ruutu.asetaMontakoMiinaaYmparilla(2);        
        assertEquals(false, ruutu.asetaMontakoMiinaaYmparilla(2));
    }
    
    @Test
    public void ruudunMerkkaaminenToimii() {
        ruutu.merkkaa();
        ruutu.merkkaa();
        ruutu.merkkaa();
        assertEquals(true, ruutu.onkoMerkattu());
    }
    
    @Test
    public void ruudunMerkkauksenPoistoToimii() {
        ruutu.merkkaa();
        ruutu.merkkaa();
        assertEquals(false, ruutu.onkoMerkattu());
    }
    
    @Test
    public void miinaaEiVoiKahdestiAsettaa() {
       assertEquals(true, ruutu.asetaMiina());
        assertEquals(false, ruutu.asetaMiina());
    }
}