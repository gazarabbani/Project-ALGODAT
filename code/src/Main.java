import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static ListAdmin listAdmin = new ListAdmin();
    private static ListUser listUser = new ListUser();
    private static ListTiket listTiket = new ListTiket();
    private static ListWahana listWahana = new ListWahana();
    private static ListBarang listBarang = new ListBarang();
    
    public static void main(String[] args) {
        // tambahin user dummy untuk testing
        listUser.addUser("user1", "Gaza", "gaza123");
        listUser.addUser("user2", "Karin", "karin123");
        listUser.addUser("user3", "Jasmine", "jeje123");
        listUser.addUser("user4", "Aziz", "ajis123");
        listUser.addUser("user5", "Ajriya", "ajriya123");
        listUser.addUser("user6", "Eka", "eka123");
        listUser.addUser("user7", "Maul", "Mau123");

        // tambah barang dummy
        listBarang.enqueue("Ransel", "user1");
        listBarang.enqueue("Helm", "user1");
        listBarang.enqueue("Kamera", "user2");
        listBarang.enqueue("Jaket", "user2");
        listBarang.enqueue("Topi", "user3");
        listBarang.enqueue("Laptop", "user3");
        listBarang.enqueue("Kacamata", "user4");
        listBarang.enqueue("Galon", "user4");
        listBarang.enqueue("Meja", "user4");
        listBarang.enqueue("Gitar", "user5");
        listBarang.enqueue("Drum", "user5");
        listBarang.enqueue("Helm", "user5");
        listBarang.enqueue("Pot Bunga", "user6");
        listBarang.enqueue("Cermin DIY", "user6");
        listBarang.enqueue("LCD PRODI", "user6");
        listBarang.enqueue("Proyektor", "user7");
        listBarang.enqueue("Lemari", "user7");

        //tiket dummy
        listTiket.enqueue("Regular", 50000, "user1");
        listTiket.enqueue("VIP", 100000, "user2");
        listTiket.enqueue("Family Package", 150000, "user3");
        
        boolean running = true;
        
        while (running) {
            clearScreen();
            tampilkanHeaderUtama();
            System.out.println("\n╔════════════════════════════════════════════════════════╗");
            System.out.println("║              MENU UTAMA                                ║");
            System.out.println("╠════════════════════════════════════════════════════════╣");
            System.out.println("║  1. Sign IN sebagai Admin                              ║");
            System.out.println("║  2. Sign In sebagai User                               ║");
            System.out.println("║  3. Sign Up User                                       ║");
            System.out.println("║  4. Exit                                               ║");
            System.out.println("╚════════════════════════════════════════════════════════╝");
            System.out.print("Pilih menu: ");
            
            int pilihan = inputInt();
            
            switch (pilihan) {
                case 1:
                    loginAdmin();
                    break;
                case 2:
                    loginUser();
                    break;
                case 3:
                    registerUser();
                    break;
                case 4:
                    System.out.println("\n╔════════════════════════════════════════════════════════╗");
                    System.out.println("║  Terima kasih telah menggunakan sistem kami!           ║");
                    System.out.println("║  Sampai jumpa kembali! 👋                              ║");
                    System.out.println("╚════════════════════════════════════════════════════════╝");
                    running = false;
                    break;
                default:
                    System.out.println("✗ Pilihan tidak valid!");
                    tekanEnter();
            }
        }
        
        scanner.close();
    }
    
// TAMPILAN HEADER
    
    private static void tampilkanHeaderUtama() {
        System.out.println("╔════════════════════════════════════════════════════════╗");
        System.out.println("║                                                        ║");
        System.out.println("║                    SELAMAT DATANG!                     ║");
        System.out.println("║                                                        ║");
        System.out.println("║                       FUNWORLD                         ║");
        System.out.println("║                                                        ║");
        System.out.println("╚════════════════════════════════════════════════════════╝");
    }
    
    // LOGIN & REGISTER
    
    private static void loginAdmin() {
        clearScreen();
        System.out.println("╔════════════════════════════════════════════════════════╗");
        System.out.println("║              LOGIN ADMIN                               ║");
        System.out.println("╚════════════════════════════════════════════════════════╝");
        
        System.out.print("Admin ID: ");
        String adminId = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        
        NodeAdmin admin = listAdmin.login(adminId, password);
        
        if (admin != null) {
            System.out.println("✓ Login berhasil! Selamat datang, " + admin.getNama());
            tekanEnter();
            menuAdmin(admin);
        } else {
            System.out.println("✗ Login gagal! Admin ID atau password salah.");
            System.out.println("  (Default: admin / admin123)");
            tekanEnter();
        }
    }
    
    private static void loginUser() {
        clearScreen();
        System.out.println("╔════════════════════════════════════════════════════════╗");
        System.out.println("║              LOGIN USER                                ║");
        System.out.println("╚════════════════════════════════════════════════════════╝");
        
        System.out.print("User ID: ");
        String userId = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        
        NodeUser user = listUser.login(userId, password);
        
        if (user != null) {
            System.out.println("✓ Login berhasil! Selamat datang, " + user.getNama());
            tekanEnter();
            menuUser(user);
        } else {
            System.out.println("✗ Login gagal! User ID atau password salah.");
            System.out.println("  (Coba: user1 / pass123)");
            tekanEnter();
        }
    }
    
    private static void registerUser() {
        clearScreen();
        System.out.println("╔════════════════════════════════════════════════════════╗");
        System.out.println("║              REGISTER USER BARU                        ║");
        System.out.println("╚════════════════════════════════════════════════════════╝");
        
        System.out.print("User ID (username): ");
        String userId = scanner.nextLine();
        
        // cek apakah userId sudah ada
        if (listUser.searchUser(userId) != null) {
            System.out.println("✗ User ID sudah digunakan! Pilih yang lain.");
            tekanEnter();
            return;
        }
        
        System.out.print("Nama Lengkap: ");
        String nama = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        
        listUser.addUser(userId, nama, password);
        System.out.println("✓ Registrasi berhasil! Silakan login.");
        tekanEnter();
    }
    
    //  MENU ADMIN
    
    private static void menuAdmin(NodeAdmin admin) {
        boolean running = true;
        
        while (running) {
            clearScreen();
            System.out.println("╔════════════════════════════════════════════════════════╗");
            System.out.println("║              MENU ADMIN                                ║");
            System.out.println("║              Logged in as: " + String.format("%-28s", admin.getNama()) + "║");
            System.out.println("╠════════════════════════════════════════════════════════╣");
            System.out.println("║  1. Kelola Tiket                                       ║");
            System.out.println("║  2. Kelola User                                        ║");
            System.out.println("║  3. Kelola Wahana                                      ║");
            System.out.println("║  4. Kelola Barang Titipan                              ║");
            System.out.println("║  5. Logout                                             ║");
            System.out.println("╚════════════════════════════════════════════════════════╝");
            System.out.print("Pilih menu: ");
            
            int pilihan = inputInt();
            
            switch (pilihan) {
                case 1:
                    menuKelolaTiket();
                    break;
                case 2:
                    menuKelolaUser();
                    break;
                case 3:
                    menuKelolaWahana();
                    break;
                case 4:
                    menuKelolaBarang();
                    break;
                case 5:
                    System.out.println("✓ Logout berhasil!");
                    tekanEnter();
                    running = false;
                    break;
                default:
                    System.out.println("✗ Pilihan tidak valid!");
                    tekanEnter();
            }
        }
    }
    
    private static void menuKelolaTiket() {
        boolean running = true;
        
        while (running) {
            clearScreen();
            System.out.println("╔════════════════════════════════════════════════════════╗");
            System.out.println("║              KELOLA TIKET                              ║");
            System.out.println("╠════════════════════════════════════════════════════════╣");
            System.out.println("║  1. Tambah Tiket Baru                                  ║");
            System.out.println("║  2. Lihat Riwayat Pembelian                            ║");
            System.out.println("║  3. Hapus Tiket dari Riwayat                           ║");
            System.out.println("║  4. Edit Harga Tiket                                   ║");
            System.out.println("║  5. Kembali                                            ║");
            System.out.println("╚════════════════════════════════════════════════════════╝");
            System.out.print("Pilih menu: ");
            
            int pilihan = inputInt();
            
            switch (pilihan) {
                case 1:
                    tambahTiket();
                    break;
                case 2:
                    listTiket.displayQueue();
                    tekanEnter();
                    break;
                case 3:
                    listTiket.displayQueue();
                    System.out.print("\nMasukkan ID Tiket yang akan dihapus: ");
                    String idTiket = scanner.nextLine();
                    listTiket.dequeue(idTiket);
                    tekanEnter();
                    break;
                case 4:
                    editHargaTiket();
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("✗ Pilihan tidak valid!");
                    tekanEnter();
            }
        }
    }
    
    private static void tambahTiket() {
        System.out.print("\nJenis Tiket: ");
        String jenis = scanner.nextLine();
        System.out.print("Harga: Rp ");
        double harga = inputDouble();
        
        listTiket.enqueue(jenis, harga, "SYSTEM");
        System.out.println("✓ Tiket baru berhasil ditambahkan!");
        tekanEnter();
    }
    
    private static void editHargaTiket() {
        listTiket.displayJenisTiketTersedia();
        System.out.print("\nMasukkan jenis tiket yang ingin diedit: ");
        String jenis = scanner.nextLine();
        System.out.print("Harga baru: Rp ");
        double hargaBaru = inputDouble();
        
        listTiket.editHargaTiket(jenis, hargaBaru);
        tekanEnter();
    }
    
    private static void menuKelolaUser() {
        boolean running = true;
        
        while (running) {
            clearScreen();
            System.out.println("╔════════════════════════════════════════════════════════╗");
            System.out.println("║              KELOLA USER                               ║");
            System.out.println("╠════════════════════════════════════════════════════════╣");
            System.out.println("║  1. Lihat Semua User                                   ║");
            System.out.println("║  2. Lihat User (Sorted by Tiket)                       ║");
            System.out.println("║  3. Hapus User                                         ║");
            System.out.println("║  4. Edit User                                          ║");
            System.out.println("║  5. Kembali                                            ║");
            System.out.println("╚════════════════════════════════════════════════════════╝");
            System.out.print("Pilih menu: ");
            
            int pilihan = inputInt();
            
            switch (pilihan) {
                case 1:
                    listUser.displayAll();
                    tekanEnter();
                    break;
                case 2:
                    listUser.displayUserSorted();
                    tekanEnter();
                    break;
                case 3:
                    listUser.displayAll();
                    hapusUser();
                    break;
                case 4:
                    listUser.displayAll();
                    editUser();
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("✗ Pilihan tidak valid!");
                    tekanEnter();
            }
        }
    }
    
    private static void hapusUser() {
        System.out.print("\nMasukkan User ID yang akan dihapus: ");
        String userId = scanner.nextLine();
        listUser.deleteUser(userId);
        tekanEnter();
    }
    
    private static void editUser() {

        System.out.print("\nMasukkan User ID yang akan diedit: ");
        String userId = scanner.nextLine();
        
        NodeUser user = listUser.searchUser(userId);
        if (user == null) {
            System.out.println("✗ User tidak ditemukan!");
            tekanEnter();
            return;
        }
        
        System.out.println("\nData saat ini: " + user.toString());
        System.out.print("Nama baru (kosongkan jika tidak diubah): ");
        String nama = scanner.nextLine();
        System.out.print("Password baru (kosongkan jika tidak diubah): ");
        String password = scanner.nextLine();
        
        listUser.editUser(userId, nama, password);
        tekanEnter();
    }
    
    private static void menuKelolaWahana() {
        boolean running = true;
        
        while (running) {
            clearScreen();
            System.out.println("╔════════════════════════════════════════════════════════╗");
            System.out.println("║              KELOLA WAHANA                             ║");
            System.out.println("╠════════════════════════════════════════════════════════╣");
            System.out.println("║  1. Tambah Wahana                                      ║");
            System.out.println("║  2. Lihat Graph Wahana                                 ║");
            System.out.println("║  3. Edit Wahana                                        ║");
            System.out.println("║  4. Hapus Wahana                                       ║");
            System.out.println("║  5. Tambah Rute Antar Wahana                           ║");
            System.out.println("║  6. Kembali                                            ║");
            System.out.println("╚════════════════════════════════════════════════════════╝");
            System.out.print("Pilih menu: ");
            
            int pilihan = inputInt();
            
            switch (pilihan) {
                case 1:
                    tambahWahana();
                    break;
                case 2:
                    listWahana.displayGraph();
                    tekanEnter();
                    break;
                case 3:
                    editWahana();
                    break;
                case 4:
                    hapusWahana();
                    break;
                case 5:
                    tambahRuteWahana();
                    break;
                case 6:
                    running = false;
                    break;
                default:
                    System.out.println("✗ Pilihan tidak valid!");
                    tekanEnter();
            }
        }
    }
    
    private static void tambahWahana() {
        System.out.print("\nNama Wahana: ");
        String nama = scanner.nextLine();
        System.out.print("Kategori (Ekstrem/Sedang/Santai/Anak/Horor): ");
        String kategori = scanner.nextLine();
        System.out.print("Kapasitas: ");
        int kapasitas = inputInt();
        
        listWahana.addWahana(nama, kategori, kapasitas);
        tekanEnter();
    }
    
    private static void editWahana() {
        listWahana.displayList();
        System.out.print("\nMasukkan ID Wahana yang akan diedit: ");
        String id = scanner.nextLine();
        
        NodeWahana wahana = listWahana.searchWahana(id);
        if (wahana == null) {
            System.out.println("✗ Wahana tidak ditemukan!");
            tekanEnter();
            return;
        }
        
        System.out.println("\nData saat ini: " + wahana.toString());
        System.out.print("Nama baru (kosongkan jika tidak diubah): ");
        String nama = scanner.nextLine();
        System.out.print("Kategori baru (kosongkan jika tidak diubah): ");
        String kategori = scanner.nextLine();
        System.out.print("Kapasitas baru (0 jika tidak diubah): ");
        int kapasitas = inputInt();
        
        listWahana.editWahana(id, nama, kategori, kapasitas > 0 ? kapasitas : null);
        tekanEnter();
    }
    
    private static void hapusWahana() {
        listWahana.displayList();
        System.out.print("\nMasukkan ID Wahana yang akan dihapus: ");
        String id = scanner.nextLine();
        listWahana.deleteWahana(id);
        tekanEnter();
    }
    
    private static void tambahRuteWahana() {
        listWahana.displayList();
        System.out.print("\nID Wahana Asal: ");
        String idAsal = scanner.nextLine();
        System.out.print("ID Wahana Tujuan: ");
        String idTujuan = scanner.nextLine();
        System.out.print("Jarak (meter): ");
        int jarak = inputInt();
        
        if (listWahana.addEdge(idAsal, idTujuan, jarak)) {
            System.out.println("✓ Rute berhasil ditambahkan!");
        } else {
            System.out.println("✗ Gagal menambahkan rute! Periksa ID wahana.");
        }
        tekanEnter();
    }
    
    private static void menuKelolaBarang() {
        boolean running = true;
        
        while (running) {
            clearScreen();
            System.out.println("╔════════════════════════════════════════════════════════╗");
            System.out.println("║              KELOLA BARANG TITIPAN                     ║");
            System.out.println("╠════════════════════════════════════════════════════════╣");
            System.out.println("║  1. Lihat Semua Barang Titipan                         ║");
            System.out.println("║  2. Hapus/Ambil Barang                                 ║");
            System.out.println("║  3. Kembali                                            ║");
            System.out.println("╚════════════════════════════════════════════════════════╝");
            System.out.print("Pilih menu: ");
            
            int pilihan = inputInt();
            
            switch (pilihan) {
                case 1:
                    listBarang.displayAll();
                    tekanEnter();
                    break;
                case 2:
                    listBarang.displayAll();
                    hapusBarang();
                    break;
                case 3:
                    running = false;
                    break;
                default:
                    System.out.println("✗ Pilihan tidak valid!");
                    tekanEnter();
            }
        }
    }
    
    private static void hapusBarang() {
        System.out.print("\nMasukkan Kode Barang: ");
        String kode = scanner.nextLine();
        listBarang.deleteBarang(kode);
        tekanEnter();
    }
    
    // MENU USER
    
    private static void menuUser(NodeUser user) {
        boolean running = true;
        
        while (running) {
            clearScreen();
            System.out.println("╔════════════════════════════════════════════════════════╗");
            System.out.println("║              MENU USER                                 ║");
            System.out.println("║              Logged in as: " + String.format("%-28s", user.getNama()) + "║");
            System.out.println("╠════════════════════════════════════════════════════════╣");
            System.out.println("║  1. Lihat Daftar Wahana & Peta                         ║");
            System.out.println("║  2. Cari Jalur Terpendek (Dijkstra)                    ║");
            System.out.println("║  3. Pesan Tiket                                        ║");
            System.out.println("║  4. Lihat Tiket Saya                                   ║");
            System.out.println("║  5. Titip Barang                                       ║");
            System.out.println("║  6. Ambil Barang                                       ║");
            System.out.println("║  7. Lihat Barang Titipan Saya                          ║");
            System.out.println("║  8. Logout                                             ║");
            System.out.println("╚════════════════════════════════════════════════════════╝");
            System.out.print("Pilih menu: ");
            
            int pilihan = inputInt();
            
            switch (pilihan) {
                case 1:
                    listWahana.displayGraph();
                    tekanEnter();
                    break;
                case 2:
                    cariJalurTerpendek();
                    break;
                case 3:
                    pesanTiket(user);
                    break;
                case 4:
                    listTiket.displayStack(user.getUserId());
                    tekanEnter();
                    break;
                case 5:
                    titipBarang(user);
                    break;
                case 6:
                    ambilBarangUser(user);
                    break;
                case 7:
                    listBarang.displayByUser(user.getUserId());
                    tekanEnter();
                    break;
                case 8:
                    System.out.println("✓ Logout berhasil!");
                    tekanEnter();
                    running = false;
                    break;
                default:
                    System.out.println("✗ Pilihan tidak valid!");
                    tekanEnter();
            }
        }
    }
    
    private static void cariJalurTerpendek() {
        listWahana.displayList();
        System.out.print("\nID Wahana Asal: ");
        String idAsal = scanner.nextLine();
        System.out.print("ID Wahana Tujuan: ");
        String idTujuan = scanner.nextLine();
        
        listWahana.dijkstra(idAsal, idTujuan);
        tekanEnter();
    }
    
    private static void pesanTiket(NodeUser user) {
        listTiket.displayJenisTiketTersedia();
        System.out.print("\nMasukkan jenis tiket yang ingin dibeli: ");
        String jenis = scanner.nextLine();
        
        NodeTiket tiket = listTiket.searchTiketByJenis(jenis);
        if (tiket == null) {
            System.out.println("✗ Jenis tiket tidak ditemukan!");
            tekanEnter();
            return;
        }
        
        // push ke stack user
        listTiket.push(jenis, tiket.getHarga(), user.getUserId());
        
        // enqueue ke riwayat pembelian
        listTiket.enqueue(jenis, tiket.getHarga(), user.getUserId());
        
        // update jumlah tiket user
        user.setNoTiket(user.getNoTiket() + 1);
        
        System.out.println("✓ Tiket berhasil dibeli!");
        System.out.println("  Total: Rp" + tiket.getHarga());
        tekanEnter();
    }
    
    private static void titipBarang(NodeUser user) {
        System.out.print("\nJenis Barang: ");
        String jenis = scanner.nextLine();
        
        listBarang.enqueue(jenis, user.getUserId());
        tekanEnter();
    }
    
    private static void ambilBarangUser(NodeUser user) {
        listBarang.displayByUser(user.getUserId());
        System.out.print("\nMasukkan Kode Barang: ");
        String kode = scanner.nextLine();
        
        listBarang.ambilBarang(kode, user.getUserId());
        tekanEnter();
    }
    
    // UTILITY FUNCTIONS
    
    private static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            // print baris kosong
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }
    
    private static void tekanEnter() {
        System.out.print("\nTekan Enter untuk melanjutkan...");
        scanner.nextLine();
    }
    
    private static int inputInt() {
        while (true) {
            try {
                int value = Integer.parseInt(scanner.nextLine());
                return value;
            } catch (NumberFormatException e) {
                System.out.print("✗ Input harus berupa angka! Coba lagi: ");
            }
        }
    }
    
    private static double inputDouble() {
        while (true) {
            try {
                double value = Double.parseDouble(scanner.nextLine());
                return value;
            } catch (NumberFormatException e) {
                System.out.print("✗ Input harus berupa angka! Coba lagi: ");
            }
        }
    }
}