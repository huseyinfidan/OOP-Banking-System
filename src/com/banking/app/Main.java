package com.banking.app;

import java.util.Locale;
import java.util.Scanner;
import com.banking.model.Account;
import com.banking.model.Bank;
import com.banking.model.CheckingAccount;
import com.banking.model.SavingsAccount;

/**
 * Bankacılık Uygulaması Ana Sınıfı (Interactive Mode).
 * Kullanıcıdan veri alarak hesap işlemlerini yönetir.
 * * @author Hüseyin Fidan
 * @version 2.0
 */
public class Main {

    // Scanner nesnesi tüm metotlarda kullanılsın diye static yaptık
    private static Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
    private static Bank bank = new Bank();

    public static void main(String[] args) {
        boolean running = true;

        System.out.println("***********************************");
        System.out.println("* BANKACILIK SİSTEMİNE HOŞGELDİNİZ    *");
        System.out.println("***********************************");

        while (running) {
            System.out.println("\n--- İŞLEM MENÜSÜ ---");
            System.out.println("1. Yeni Hesap Oluştur");
            System.out.println("2. Para Yatır (Deposit)");
            System.out.println("3. Para Çek (Withdraw)");
            System.out.println("4. Para Transferi (Transfer)");
            System.out.println("5. Bakiyeyi Sorgula");
            System.out.println("6. Tüm Hesapları Listele");
            System.out.println("0. Çıkış");
            System.out.print("Seçiminiz: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Buffer temizleme (Enter tuşu hatasını önler)

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    performDeposit();
                    break;
                case 3:
                    performWithdraw();
                    break;
                case 4:
                    performTransfer();
                    break;
                case 5:
                    showBalance();
                    break;
                case 6:
                    bank.displayAllAccounts();
                    break;
                case 0:
                    System.out.println("Sistemden çıkılıyor. İyi günler!");
                    running = false;
                    break;
                default:
                    System.out.println("Geçersiz seçim! Lütfen tekrar deneyin.");
            }
        }
        scanner.close();
    }

    // 1. Hesap Oluşturma Metodu
    private static void createAccount() {
        System.out.println("\n--- HESAP OLUŞTURMA ---");
        System.out.print("Müşteri Adı Soyadı: ");
        String name = scanner.nextLine();

        System.out.print("Hesap Numarası (Örn: 101): ");
        String accNum = scanner.nextLine();

        System.out.print("Başlangıç Bakiyesi: ");
        double balance = scanner.nextDouble();

        System.out.println("Hesap Türü Seçin (1: Vadesiz/Checking, 2: Birikim/Savings): ");
        int type = scanner.nextInt();

        Account newAccount;
        if (type == 1) {
            System.out.print("Eksi Bakiye Limiti (Overdraft Limit): ");
            double limit = scanner.nextDouble();
            newAccount = new CheckingAccount(accNum, name, balance, limit);
        } else {
            System.out.print("Faiz Oranı (Örn: 0.05): ");
            double rate = scanner.nextDouble();
            newAccount = new SavingsAccount(accNum, name, balance, rate);
        }

        bank.addAccount(newAccount);
        System.out.println(">>> Hesap başarıyla oluşturuldu!");
    }

    // 2. Para Yatırma Metodu
    private static void performDeposit() {
        System.out.print("İşlem yapılacak hesap numarası: ");
        String id = scanner.nextLine();
        Account acc = findAccountById(id);

        if (acc != null) {
            System.out.print("Yatırılacak Tutar: ");
            double amount = scanner.nextDouble();
            acc.deposit(amount);
            System.out.println(">>> İşlem tamam. Yeni Bakiye: " + acc.getBalance());
        }
    }

    // 3. Para Çekme Metodu
    private static void performWithdraw() {
        System.out.print("İşlem yapılacak hesap numarası: ");
        String id = scanner.nextLine();
        Account acc = findAccountById(id);

        if (acc != null) {
            System.out.print("Çekilecek Tutar: ");
            double amount = scanner.nextDouble();
            acc.withdraw(amount);
            // Bakiye kontrolünü Account sınıfı zaten yapıyor, sonucu yazdırıyoruz.
            System.out.println(">>> Güncel Bakiye: " + acc.getBalance());
        }
    }

    // 4. Transfer Metodu
    private static void performTransfer() {
        System.out.print("Gönderen Hesap No: ");
        String fromId = scanner.nextLine();
        Account fromAcc = findAccountById(fromId);

        System.out.print("Alıcı Hesap No: ");
        String toId = scanner.nextLine();
        Account toAcc = findAccountById(toId);

        if (fromAcc != null && toAcc != null) {
            System.out.print("Transfer Tutarı: ");
            double amount = scanner.nextDouble();
            boolean success = fromAcc.transfer(toAcc, amount);
            if (success) {
                System.out.println(">>> Transfer Başarılı!");
            } else {
                System.out.println(">>> Transfer Başarısız (Yetersiz bakiye olabilir).");
            }
        }
    }

    // 5. Bakiye Sorgulama
    private static void showBalance() {
        System.out.print("Hesap Numarası: ");
        String id = scanner.nextLine();
        Account acc = findAccountById(id);
        if (acc != null) {
            System.out.println(">>> Hesap Sahibi: " + acc.getOwnerName()); // Getter eklemediysek hata verebilir, sadece bakiye de olur.
            System.out.println(">>> Mevcut Bakiye: " + acc.getBalance());
        }
    }

    // Yardımcı Metot: Listeden Hesap Bulma
    private static Account findAccountById(String id) {
        for (Account acc : bank.getAccounts()) {
            if (acc.getAccountNumber().equals(id)) {
                return acc;
            }
        }
        System.out.println("!!! HATA: Bu numaraya ait hesap bulunamadı.");
        return null;
    }
}

