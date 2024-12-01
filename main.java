import java.io.*;
import java.util.*;

// Abstract Class for MenuItem
abstract class MenuItem {
    private String nama;
    private double harga;
    private String kategori;

    public MenuItem(String nama, double harga, String kategori) {
        this.nama = nama;
        this.harga = harga;
        this.kategori = kategori;
    }

    public String getNama() {
        return nama;
    }

    public double getHarga() {
        return harga;
    }

    public String getKategori() {
        return kategori;
    }

    public abstract void tampilMenu();
}

// Subclass Makanan
class Makanan extends MenuItem {
    private String jenisMakanan;

    public Makanan(String nama, double harga, String kategori, String jenisMakanan) {
        super(nama, harga, kategori);
        this.jenisMakanan = jenisMakanan;
    }

    public String getJenisMakanan() {
        return jenisMakanan;
    }

    @Override
    public void tampilMenu() {
        System.out.println("Makanan: " + getNama() + " | Harga: " + getHarga() + " | Jenis: " + jenisMakanan);
    }
}

// Subclass Minuman
class Minuman extends MenuItem {
    private String jenisMinuman;

    public Minuman(String nama, double harga, String kategori, String jenisMinuman) {
        super(nama, harga, kategori);
        this.jenisMinuman = jenisMinuman;
    }

    public String getJenisMinuman() {
        return jenisMinuman;
    }

    @Override
    public void tampilMenu() {
        System.out.println("Minuman: " + getNama() + " | Harga: " + getHarga() + " | Jenis: " + jenisMinuman);
    }
}

// Subclass Diskon
class Diskon extends MenuItem {
    private double persentaseDiskon;

    public Diskon(String nama, double harga, String kategori, double persentaseDiskon) {
        super(nama, harga, kategori);
        this.persentaseDiskon = persentaseDiskon;
    }

    public double getPersentaseDiskon() {
        return persentaseDiskon;
    }

    @Override
    public void tampilMenu() {
        System.out.println("Diskon: " + getNama() + " | Persentase Diskon: " + persentaseDiskon + "%");
    }
}

// Menu Management
class Menu {
    private ArrayList<MenuItem> daftarMenu;

    public Menu() {
        daftarMenu = new ArrayList<>();
        // Tambahkan dummy data
        tambahItem(new Makanan("Nasi Goreng", 25000, "Makanan", "Goreng"));
        tambahItem(new Makanan("Ayam Bakar", 30000, "Makanan", "Bakar"));
        tambahItem(new Minuman("Es Teh", 5000, "Minuman", "Dingin"));
        tambahItem(new Minuman("Kopi Hitam", 10000, "Minuman", "Panas"));
        tambahItem(new Diskon("Diskon Spesial", -5000, "Diskon", 20.0));
    }

    public void tambahItem(MenuItem item) {
        daftarMenu.add(item);
    }

    public void tampilkanMenu() {
        System.out.println("=== Daftar Menu ===");
        for (MenuItem item : daftarMenu) {
            item.tampilMenu();
        }
    }

    public MenuItem cariItem(String nama) throws Exception {
        for (MenuItem item : daftarMenu) {
            if (item.getNama().equalsIgnoreCase(nama)) {
                return item;
            }
        }
        throw new Exception("Item tidak ditemukan!");
    }
}

// Pesanan Management
class Pesanan {
    private ArrayList<MenuItem> itemPesanan;

    public Pesanan() {
        itemPesanan = new ArrayList<>();
    }

    public void tambahItem(MenuItem item) {
        itemPesanan.add(item);
    }

    public void tampilkanStruk() {
        double total = 0;
        System.out.println("=== Struk Pesanan ===");
        for (MenuItem item : itemPesanan) {
            System.out.println(item.getNama() + " - " + item.getHarga());
            total += item.getHarga();
        }
        System.out.println("Total: " + total);
    }
}

// Main Program
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Menu menu = new Menu(); // Menu dengan dummy data
        Pesanan pesanan = new Pesanan();

        while (true) {
            System.out.println("\n=== Menu Utama ===");
            System.out.println("1. Tambah Item ke Menu");
            System.out.println("2. Tampilkan Menu");
            System.out.println("3. Tambah Pesanan");
            System.out.println("4. Tampilkan Struk Pesanan");
            System.out.println("5. Keluar");
            System.out.print("Pilih: ");
            int pilihan = scanner.nextInt();
            scanner.nextLine(); // Membersihkan buffer

            try {
                switch (pilihan) {
                    case 1:
                        System.out.print("Masukkan jenis (Makanan/Minuman/Diskon): ");
                        String jenis = scanner.nextLine();
                        System.out.print("Masukkan nama: ");
                        String nama = scanner.nextLine();
                        System.out.print("Masukkan harga: ");
                        double harga = scanner.nextDouble();
                        scanner.nextLine();
                        System.out.print("Masukkan kategori: ");
                        String kategori = scanner.nextLine();

                        if (jenis.equalsIgnoreCase("Makanan")) {
                            System.out.print("Masukkan jenis makanan: ");
                            String jenisMakanan = scanner.nextLine();
                            menu.tambahItem(new Makanan(nama, harga, kategori, jenisMakanan));
                        } else if (jenis.equalsIgnoreCase("Minuman")) {
                            System.out.print("Masukkan jenis minuman: ");
                            String jenisMinuman = scanner.nextLine();
                            menu.tambahItem(new Minuman(nama, harga, kategori, jenisMinuman));
                        } else if (jenis.equalsIgnoreCase("Diskon")) {
                            System.out.print("Masukkan persentase diskon: ");
                            double persentaseDiskon = scanner.nextDouble();
                            menu.tambahItem(new Diskon(nama, harga, kategori, persentaseDiskon));
                        }
                        break;
                    case 2:
                        menu.tampilkanMenu();
                        break;
                    case 3:
                        System.out.print("Masukkan nama item yang dipesan: ");
                        String namaPesanan = scanner.nextLine();
                        MenuItem item = menu.cariItem(namaPesanan);
                        pesanan.tambahItem(item);
                        break;
                    case 4:
                        pesanan.tampilkanStruk();
                        break;
                    case 5:
                        System.out.println("Terima kasih!");
                        System.exit(0);
                    default:
                        System.out.println("Pilihan tidak valid!");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
