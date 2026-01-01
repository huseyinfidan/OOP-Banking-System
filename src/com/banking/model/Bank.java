package com.banking.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Banka Sınıfı.
 * Tüm hesapları bir liste içinde tutar ve yönetir.
 */
public class Bank {
    
    // Hesapları tutan liste (Polimorfizm: List içinde Account tutuyoruz)
    private List<Account> accounts;

    public Bank() {
        // Listeyi başlatıyoruz
        this.accounts = new ArrayList<>();
    }

    /**
     * Bankaya yeni bir hesap ekler.
     * @param account Eklenecek hesap nesnesi (Savings veya Checking olabilir)
     */
    public void addAccount(Account account) {
        this.accounts.add(account);
        System.out.println("Hesap eklendi: " + account.getAccountNumber());
    }

    /**
     * Bankadaki tüm hesapları ve bakiyelerini listeler.
     */
    public void displayAllAccounts() {
        System.out.println("\n--- Bankadaki Tüm Hesaplar ---");
        for (Account acc : accounts) {
            System.out.println("No: " + acc.getAccountNumber() + 
                               " | Sahibi: " + acc.getOwnerName() + 
                               " | Bakiye: " + acc.getBalance() + " TL");
        }
        System.out.println("------------------------------\n");
    }
    
    // Hesap listesini döndüren metot (Testlerde işimize yarayabilir)
    public List<Account> getAccounts() {
        return accounts;
    }
}