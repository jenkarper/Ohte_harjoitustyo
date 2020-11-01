package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(1000);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void kortinSaldoAlussaOikein() {
        assertEquals("saldo: 10.0", kortti.toString());
    }
    
    @Test
    public void lataaminenKasvattaaSaldoaOikein() {
        kortti.lataaRahaa(500);
        assertEquals("saldo: 15.0", kortti.toString());
    }
    
    @Test
    public void ottoVahentaaSaldoaOikein() {
        kortti.otaRahaa(250);
        assertEquals("saldo: 7.50", kortti.toString());
    }
    
    @Test
    public void ottoEiYlitaSaldoa() {
        kortti.otaRahaa(2000);
        assertEquals("saldo: 10.0", kortti.toString());
    }
    
    @Test
    public void onnistunutOttoPalauttaaTrue() {
        assertTrue(kortti.otaRahaa(500));
    }
    
    @Test
    public void epaonnistunutOttoPalauttaaFalse() {
        assertTrue(!kortti.otaRahaa(2000));
    }
    
    @Test
    public void saldoPalauttaaOikeanSaldon() {
        assertEquals(1000, kortti.saldo());
    }
}
