package com.banking.model;

/**
 * Tasarruf Hesabı Sınıfı.
 * Account sınıfından miras alır (Inheritance).
 * Para çekme işlemini kendine göre özelleştirir (Polymorphism).
 */
public class SavingsAccount extends Account {

    // Tasarruf hesabına özel faiz oranı özelliği
    private double interestRate;

    /**
     * Kurucu Metot
     * @param accountNumber Hesap No
     * @param ownerName Hesap Sahibi
     * @param balance Başlangıç Bakiyesi
     * @param interestRate Faiz Oranı (Örn: 0.05)
     */
    public SavingsAccount(String accountNumber, String ownerName, double balance, double interestRate) {
        super(accountNumber, ownerName, balance); // Üst sınıfın (Account) kurucusunu çağırır
        this.interestRate = interestRate;
    }

    /**
     * Polimorfizm Örneği: withdraw metodu eziliyor (Override).
     * Tasarruf hesabında bakiye yetersizse işlem yapılmaz.
     */
    @Override
    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Tasarruf hesabından " + amount + " TL çekildi. Kalan: " + balance);
        } else {
            System.out.println("İşlem başarısız: Tasarruf hesabında yetersiz bakiye.");
        }
    }
    
    // Faiz hesaplama metodu (Opsiyonel özellik olarak ekleyebiliriz)
    public void addInterest() {
        double interest = balance * interestRate;
        balance += interest;
        System.out.println("Faiz eklendi: " + interest + " TL. Yeni Bakiye: " + balance);
    }
    
    public double getInterestRate() {
        return interestRate;
    }
}