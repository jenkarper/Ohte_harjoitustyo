package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class KassapaateTest {
    
    Kassapaate paate;
    Maksukortti kortti1;
    Maksukortti kortti2;
    
    @Before
    public void setup() {
        paate = new Kassapaate();
        kortti1 = new Maksukortti(1000);
        kortti2 = new Maksukortti(10);
    }
    
    // Päätteen omat metodit
    
    @Test
    public void luotuPaateOnOlemassa() {
        assertTrue(paate != null);
    }
    
    @Test
    public void uudenPaatteenSaldoOnOikein() {
        assertTrue(paate.kassassaRahaa()==100000);
    }
    
    @Test
    public void alussaMaukkaitaMyytyNolla() {
        assertTrue(paate.maukkaitaLounaitaMyyty()==0);
    }
    
    @Test
    public void alussaEdullisiaMyytyNolla() {
        assertTrue(paate.edullisiaLounaitaMyyty()==0);
    }
    
    // Käteisostot
    
    @Test
    public void kateisostoLisaaSaldoaOikein() {
        paate.syoEdullisesti(240);
        paate.syoMaukkaasti(400);
        assertTrue(paate.kassassaRahaa()==100640);
    }
    
    @Test
    public void paateLaskeeVaihtorahanOikein() {
        assertTrue(paate.syoEdullisesti(300)==60);
    }
    
    @Test
    public void onnistunutKateismyyntiKasvattaaMyytyjaMaukkaita() {
        paate.syoMaukkaasti(400);
        assertTrue(paate.maukkaitaLounaitaMyyty()==1);
    }
    
    @Test
    public void onnistunutKateismyyntiKasvattaaMyytyjaEdullisia() {
        paate.syoEdullisesti(240);
        assertTrue(paate.edullisiaLounaitaMyyty()==1);
    }
    
    @Test
    public void riittamatonMaukkaanKateismaksuPalautuu() {
        assertTrue(paate.syoMaukkaasti(300)==300);
    }
    
    @Test
    public void riittamatonMaukkaanKateismaksuEiKasvataSaldoa() {
        paate.syoMaukkaasti(300);
        assertTrue(paate.kassassaRahaa()==100000);
    }
    
    @Test
    public void riittamatonMaukkaanKateismaksuEiKasvataMyytyjaMaukkaita() {
        paate.syoMaukkaasti(300);
        assertTrue(paate.maukkaitaLounaitaMyyty()==0);
    }
    
    @Test
    public void riittamatonEdullisenKateismaksuPalautuu() {
        assertTrue(paate.syoEdullisesti(200)==200);
    }
    
    @Test
    public void riittamatonEdullisenKateismaksuEiKasvataSaldoa() {
        paate.syoEdullisesti(200);
        assertTrue(paate.kassassaRahaa()==100000);
    }
    
    @Test
    public void riittamatonEdullisenKateismaksuEiKasvataMyytyjaEdullisia() {
        paate.syoEdullisesti(200);
        assertTrue(paate.edullisiaLounaitaMyyty()==0);
    }
    
    // Korttiostot
    
    @Test
    public void maukkaanKorttimyyntiVeloittaaKorttiaOikein() {
        paate.syoMaukkaasti(kortti1);
        assertTrue(kortti1.saldo()==600);
    }
    
    @Test
    public void maukkaanKorttimyyntiEiKasvataSaldoa() {
        paate.syoMaukkaasti(kortti1);
        assertTrue(paate.kassassaRahaa()==100000);
    }
    
    @Test
    public void maukkaanOnnistunutKorttimyyntiPalauttaaTrue() {
        assertTrue(paate.syoMaukkaasti(kortti1));
    }
    
    @Test
    public void maukkaanOnnistunutKorttimyyntiKasvattaaMyytyjaMaukkaita() {
        paate.syoMaukkaasti(kortti1);
        assertTrue(paate.maukkaitaLounaitaMyyty()==1);
    }
    
    @Test
    public void edullisenKorttimyyntiVeloittaaKorttiaOikein() {
        paate.syoEdullisesti(kortti1);
        assertTrue(kortti1.saldo()==760);
    }
    
    @Test
    public void edullisenKorttimyyntiEiKasvataSaldoa() {
        paate.syoEdullisesti(kortti1);
        assertTrue(paate.kassassaRahaa()==100000);
    }
    
    @Test
    public void edullisenOnnistunutKorttimyyntiPalauttaaTrue() {
        assertTrue(paate.syoEdullisesti(kortti1));
    }
    
    @Test
    public void edullisenOnnistunutKorttimyyntiKasvattaaMyytyjaEdullisia() {
        paate.syoEdullisesti(kortti1);
        assertTrue(paate.edullisiaLounaitaMyyty()==1);
    }
    
    @Test
    public void maukkaanEpaonnistunutKorttimyyntiEiVeloitaKorttia() {
        paate.syoMaukkaasti(kortti2);
        assertTrue(kortti2.saldo()==10);
    }
    
    @Test
    public void maukkaanEpaonnistunutKorttimyyntiPalauttaaFalse() {
        assertTrue(!paate.syoMaukkaasti(kortti2));
    }
    
    @Test
    public void maukkaanEpaonnistunutKorttimyyntiEiKasvataMyytyjaMaukkaita() {
        paate.syoMaukkaasti(kortti2);
        assertTrue(paate.maukkaitaLounaitaMyyty()==0);
    }
    
    @Test
    public void edullisenEpaonnistunutKorttimyyntiEiVeloitaKorttia() {
        paate.syoEdullisesti(kortti2);
        assertTrue(kortti2.saldo()==10);
    }
    
    @Test
    public void edullisenEpaonnistunutKorttimyyntiPalauttaaFalse() {
        assertTrue(!paate.syoEdullisesti(kortti2));
    }
    
    @Test
    public void edullisenEpaonnistunutKorttimyyntiEiKasvataMyytyjaEdullisia() {
        paate.syoEdullisesti(kortti2);
        assertTrue(paate.edullisiaLounaitaMyyty()==0);
    }
    
    @Test
    public void kortinLataaminenKasvattaaKortinSaldoa() {
        paate.lataaRahaaKortille(kortti1, 1000);
        assertTrue(kortti1.saldo()==2000);
    }
    
    @Test
    public void kortinLataaminenKasvattaaKassanSaldoa() {
        paate.lataaRahaaKortille(kortti1, 1000);
        assertTrue(paate.kassassaRahaa()==101000);
    }
    
    @Test
    public void negatiivinenLatausEiMuutaKortinSaldoa() {
        paate.lataaRahaaKortille(kortti1, -20);
        assertTrue(kortti1.saldo()==1000);
    }
    
    @Test
    public void negatiivinenLatausEiMuutaKassanSaldoa() {
        paate.lataaRahaaKortille(kortti1, -20);
        assertTrue(paate.kassassaRahaa()==100000);
    }

}
