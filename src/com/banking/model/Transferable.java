package com.banking.model;

/**
 * Hesaplar arası para transferi işlemlerini tanımlayan arayüz.
 * Proje gereksinimi: Interface kullanımı.
 */
public interface Transferable {
    
    /**
     * Başka bir hesaba para transfer eder.
     * * @param toAccount Paranın gönderileceği alıcı hesap
     * @param amount Gönderilecek miktar
     * @return İşlem başarılıysa true, değilse false döner
     */
    boolean transfer(Account toAccount, double amount);
}
public interface Transferable {

}
