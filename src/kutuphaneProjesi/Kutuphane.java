package kutuphaneProjesi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static kutuphaneProjesi.Main.anaProgram;
import static kutuphaneProjesi.Main.cikis;

import static kutuphaneProjesi.Ogrenci.kitapİadeTarihi;
import static kutuphaneProjesi.Ogrenci.oduncKitaplarList;

public class Kutuphane extends Kitap {


    static Scanner input = new Scanner(System.in);
    static Kitap kitap1 = new Kitap("Kukla", "Ahmet Ümit", "Kelebek", "2010", "1111", "Roman");
    static Kitap kitap2 = new Kitap("Devrim", "Deniz Tokay", "Can", "2020", "2222", "Deneme");
    static Kitap kitap3 = new Kitap("Zübük", "Aziz Nesin", "Okyanus", "1976", "3333", "Hikaye");

    static List<Kitap> mevcutKitaplar = new ArrayList<>(List.of(kitap1, kitap2, kitap3));

    static List<Kitap> silinecekKitaplar = new ArrayList<>();
    static Kitap odunc = null;

    public static void kutuphaneMenu() {

        String tercih = "";

        System.out.println("**********KUTUPHANEMİMİZE  HOŞGELDİNİZ**********\n" + "\t" +
                "1- Kitap Ekle\n" + "\t"
                + "2- Kitap Sil\n" + "\t" + "3- Katalog Listele\n" + "\t" +
                "4- Kitap Ara\n" + "\t" + "5- Ana Menu\n" + "\t" + "6- Cıkıs");
        tercih = input.nextLine();
        switch (tercih) {
            case "1":
                kitapEkle();
                kutuphaneMenu();
                break;
            case "2":
                kitapSil();
                kutuphaneMenu();
                break;
            case "3":
                katalogListele();
                kutuphaneMenu();
                break;
            case "4":
                kitapAra();
                kutuphaneMenu();
                break;
            case "5":
                anaProgram();
                break;
            case "6":
                cikis();
                break;
            default:
                System.out.println("Lütfen geçerli bir giriş yapınız");
                kutuphaneMenu();
                break;
        }
    }

    public static boolean mevcutKitaplarKontrol(String kitapAdi) {
        boolean flag = false;
        for (Kitap kitap : mevcutKitaplar) {
            if (kitap.getKitapAdi().equalsIgnoreCase(kitapAdi)) {
                odunc = kitap;
                flag = true;
                break;
            }
        }
        return flag;//kütüphanede(mevcutlistte) olup olmadığını return eder
    }

    public static boolean oduncKitaplarListKontrol(String kitapAdi) {
        boolean flag2 = false;
        for (Kitap each : oduncKitaplarList) {
            if (each.getKitapAdi().equalsIgnoreCase(kitapAdi)) {
                flag2 = true;
                break;
            }
        }
        return flag2;////odunclistte olup olmadığını return eder
    }

    static void kitapAra() {

        System.out.println("Aradığınız kitabın ismini giriniz: ");
        String kitapAdi = input.nextLine();

        if (mevcutKitaplarKontrol(kitapAdi)) {
            System.out.println("Aranan Kitap: " + kitapAdi + " ismi ile kütüphanemizdedir");
        } else if (oduncKitaplarListKontrol(kitapAdi)) {
            System.out.println("Kitap başkasına ödünc verildi.  " + kitapİadeTarihi + " tarihinde odunc alınabilir");
        } else if (!oduncKitaplarListKontrol(kitapAdi)) {
            System.out.println("Odunclistesinde kitap bulunamadı");
        } else
            System.out.println("Bu kitap kitap kütüphanemize kayıtlı değildir.");
    }

    private static void katalogListele() {

        System.out.println("*********KUTUPHANE KİTAP LİSTESİ*********\n");
        System.out.printf("%-15s %-15s %-15s %-15s %-9s %s\n", "KİTAP İSMİ", "YAZAR", "YAYINEVİ", "YAYIN TARIHI", "ISBN NO", "TUR");
        for (int i = 0; i < mevcutKitaplar.size(); i++) {
            System.out.printf("%-15s %-15s %-15s %-15s %-9s %s\n", mevcutKitaplar.get(i).getKitapAdi(),
                    mevcutKitaplar.get(i).getYazar(), mevcutKitaplar.get(i).getYayinevi(), mevcutKitaplar.get(i).getYayinTarihi(),
                    mevcutKitaplar.get(i).getIsbn(), mevcutKitaplar.get(i).getKitapTuru());
        }
    }

    private static void kitapSil() {
        System.out.println("Silinecek kitabın isbn numarasını giriniz");
        String isbn = input.next();

        for (Kitap kitap : mevcutKitaplar) {
            if (kitap.getIsbn().equalsIgnoreCase(isbn)) {
                silinecekKitaplar.add(kitap);
            } else {
                for (Kitap each : oduncKitaplarList) {
                    if (each.getIsbn().equalsIgnoreCase((isbn))) {
                        System.out.println("Silmek istediğiniz kitap öğrenciye ödünç verildi. " + kitapİadeTarihi + " tarihinde kütüphaneye iade edilecek");
                        break;
                    } else
                        System.out.println("Silmek istediğiniz kitap kütüphanemize kayıtlı değildir.");
                }
                break;
            }
        }
        if (!silinecekKitaplar.isEmpty()) {
            mevcutKitaplar.removeAll(silinecekKitaplar);
            System.out.println("Silmek istediğniz kitap silindi");
        }

    }

    public static void kitapEkle() {

        System.out.println("Kitabın ismini giriniz");
        String kitapAdi = input.nextLine();

        System.out.println("Kitabın yazarini giriniz");
        String yazar = input.nextLine();

        System.out.println("Kitabın yayinevini giriniz");
        String yayinevi = input.nextLine();

        System.out.println("Kitabın yayin tarihini giriniz");
        String yayinTarihi = input.nextLine();

        System.out.println("Kitabın isbn numarasını giriniz");
        String isbn = input.nextLine();


        System.out.println("Kitabın turunu giriniz");
        String kitapTuru = input.nextLine();

        mevcutKitaplar.add(new Kitap(kitapAdi, yazar, yayinevi, yayinTarihi, isbn, kitapTuru));
        System.out.println("Kitap kütüphaneye başarıyla kaydedildi.");


    }

    @Override
    public String toString() {
        return "Kutuphane{} " + super.toString();
    }

}