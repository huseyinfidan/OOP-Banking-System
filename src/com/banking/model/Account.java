package com.banking.model;

/**
 * Tüm banka hesapları için temel soyut sınıf.
 * OOP Prensibi: Encapsulation (Kapsülleme) ve Abstraction (Soyutlama).
 */
public abstract class Account implements Transferable {
    
    // Kapsülleme: Değişkenler dışarıya kapalı (private/protected)
    private String accountNumber;
    private String ownerName;
    protected double balance; // Alt sınıflar erişebilsin diye protected

    // Kurucu Metot (Constructor)
    public Account(String accountNumber, String ownerName, double balance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = balance;
    }

    /**
     * Hesaba para yatırma işlemi.
     * Tüm hesap türleri için ortak mantık olduğu için burada tanımladık.
     * @param amount Yatırılacak miktar
     */
    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
            System.out.println(amount + " TL yatırıldı. Yeni Bakiye: " + this.balance);
        } else {
            System.out.println("Yatırılacak miktar pozitif olmalıdır.");
        }
    }

    /**
     * Para çekme işlemi.
     * Abstract (Soyut) yaptık çünkü her hesap türü (Vadesiz, Tasarruf)
     * para çekmeyi farklı kurallarla yapar.
     * OOP Prensibi: Polymorphism (Çok biçimlilik) için temel.
     */
    public abstract void withdraw(double amount);

    // Interface'den gelen transfer metodunu burada uyguluyoruz
    @Override
    public boolean transfer(Account toAccount, double amount) {
        // Önce parayı bu hesaptan çekmeye çalışalım
        // Not: withdraw metodu hata fırlatırsa burası çalışmayabilir, 
        // basit mantıkla kontrol ediyoruz.
        if (this.balance >= amount) {
            this.withdraw(amount); // Çek
            toAccount.deposit(amount); // Karşı tarafa yatır
            System.out.println("Transfer başarılı: " + toAccount.getOwnerName() + " hesabına " + amount + " TL.");
            return true;
        } else {
            System.out.println("Transfer başarısız: Yetersiz bakiye.");
            return false;
        }
    }

    // Getter metotları (Kapsülleme gereği veriyi okumak için)
    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }
}