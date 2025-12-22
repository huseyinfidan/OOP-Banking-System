package com.banking.model;

/**
 * Vadesiz Hesap (Checking Account) Sınıfı.
 * Account sınıfından miras alır.
 * Özelliği: Bakiye yetersiz olsa bile "Kredili Mevduat Limiti" (Overdraft) 
 * dahilinde para çekilmesine izin verir.
 */
public class CheckingAccount extends Account {

    private double overdraftLimit; // Eksi bakiye limiti (Örn: 500 TL)

    /**
     * Kurucu Metot
     * @param accountNumber Hesap No
     * @param ownerName Hesap Sahibi
     * @param balance Başlangıç Bakiyesi
     * @param overdraftLimit Eksiye düşebilme limiti (Pozitif girilmeli)
     */
    public CheckingAccount(String accountNumber, String ownerName, double balance, double overdraftLimit) {
        super(accountNumber, ownerName, balance);
        this.overdraftLimit = overdraftLimit;
    }

    /**
     * Polimorfizm Örneği: withdraw metodu eziliyor (Override).
     * Burada bakiye + limit yetiyorsa çekime izin verilir.
     */
    @Override
    public void withdraw(double amount) {
        // Mevcut bakiye + limit, istenen tutarı karşılıyor mu?
        if (balance + overdraftLimit >= amount) {
            balance -= amount;
            System.out.println("Vadesiz hesaptan " + amount + " TL çekildi. Kalan: " + balance);
        } else {
            System.out.println("İşlem başarısız: Limit yetersiz.");
        }
    }
    
    // Limiti öğrenmek için getter
    public double getOverdraftLimit() {
        return overdraftLimit;
    }
}