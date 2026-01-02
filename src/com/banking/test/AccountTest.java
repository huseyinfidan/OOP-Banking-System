package com.banking.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.banking.model.Account;
import com.banking.model.CheckingAccount;
import com.banking.model.SavingsAccount;

/**
 * JUnit Test Sınıfı.
 * Proje gereksinimi: Unit tests for logic and edge cases.
 */
class AccountTest {

    private Account savings;
    private Account checking;

    // Her testten önce çalışıp temiz bir ortam hazırlar
    @BeforeEach
    void setUp() {
        // Tasarruf: 1000 TL bakiye
        savings = new SavingsAccount("S01", "Test User 1", 1000.0, 0.05);
        // Vadesiz: 500 TL bakiye, 200 TL limit
        checking = new CheckingAccount("C01", "Test User 2", 500.0, 200.0);
    }

    @Test
    void testDeposit() {
        savings.deposit(500.0);
        // Beklenen: 1500, Gerçekleşen: getBalance()
        assertEquals(1500.0, savings.getBalance(), "Para yatırma hatalı.");
    }

    @Test
    void testWithdrawSavingsSufficient() {
        savings.withdraw(500.0);
        assertEquals(500.0, savings.getBalance(), "Tasarruf hesabından para çekilemedi.");
    }

    @Test
    void testWithdrawSavingsInsufficient() {
        // Tasarruf hesabında bakiye yetersizse bakiye DEĞİŞMEMELİ
        savings.withdraw(2000.0); 
        assertEquals(1000.0, savings.getBalance(), "Yetersiz bakiyeye rağmen para çekildi!");
    }

    @Test
    void testWithdrawCheckingOverdraft() {
        // Vadesiz hesapta bakiye 500, çekilen 600. Limit (200) kurtarmalı.
        checking.withdraw(600.0);
        // 500 - 600 = -100 olmalı
        assertEquals(-100.0, checking.getBalance(), "Eksi bakiye limiti çalışmıyor.");
    }
    
    @Test
    void testWithdrawCheckingOverLimit() {
        // Bakiye 500 + Limit 200 = Toplam 700 çekilebilir.
        // 800 çekmeye çalışırsak işlem reddedilmeli.
        checking.withdraw(800.0);
        assertEquals(500.0, checking.getBalance(), "Limiti aşan işlem engellenmedi!");
    }

    @Test
    void testTransfer() {
        // Savings -> Checking'e 300 TL transfer
        boolean result = savings.transfer(checking, 300.0);
        
        assertTrue(result, "Transfer işlemi başarısız döndü.");
        assertEquals(700.0, savings.getBalance(), "Gönderen hesaptan para düşmedi.");
        assertEquals(800.0, checking.getBalance(), "Alıcı hesaba para geçmedi.");
    }
}