
package com.banking.app;

import com.banking.model.Account;
import com.banking.model.CheckingAccount;
import com.banking.model.SavingsAccount;

/**
 * Uygulamanın giriş noktası.
 * Projenin çalıştığını gösteren Demo sınıfıdır.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("=== BANKACILIK SİSTEMİ DEMO BAŞLIYOR ===\n");

        // 1. Hesap Oluşturma
        // Tasarruf Hesabı: 1000 TL bakiye, %5 faiz
        Account savings = new SavingsAccount("TR01", "Ahmet Yılmaz", 1000.0, 0.05);
        
        // Vadesiz Hesap: 500 TL bakiye, 1000 TL eksi bakiye limiti
        Account checking = new CheckingAccount("TR02", "Ayşe Demir", 500.0, 1000.0);

        // Başlangıç Durumları
        System.out.println("Müşteri 1 (Tasarruf): " + savings.getBalance() + " TL");
        System.out.println("Müşteri 2 (Vadesiz): " + checking.getBalance() + " TL");
        System.out.println("--------------------------------------------------");

        // 2. Para Yatırma (Deposit)
        System.out.println(">> Ahmet hesabına 200 TL yatırıyor...");
        savings.deposit(200);
        
        // 3. Para Çekme (Withdraw - Polimorfizm Testi)
        System.out.println("\n>> Ahmet hesabından 1300 TL çekmeye çalışıyor (Bakiye: 1200)...");
        savings.withdraw(1300); // HATA vermeli (Tasarruf hesabı eksiye düşmez)
        
        System.out.println("\n>> Ayşe hesabından 800 TL çekmeye çalışıyor (Bakiye: 500, Limit Var)...");
        checking.withdraw(800); // BAŞARILI olmalı (Checking hesabı limiti kullanır)
        
        System.out.println("--------------------------------------------------");

        // 4. Transfer İşlemi (Interface Testi)
        System.out.println("\n>> TRANSFER: Ahmet -> Ayşe'ye 500 TL gönderiyor...");
        boolean result = savings.transfer(checking, 500);
        
        // Son Durumları Yazdır
        System.out.println("\n=== SON DURUM ===");
        System.out.println("Ahmet (Tasarruf) Bakiye: " + savings.getBalance() + " TL");
        System.out.println("Ayşe (Vadesiz) Bakiye : " + checking.getBalance() + " TL");
    }
}