import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static ListAdmin listAdmin = new ListAdmin();
    static ListUser listUser = new ListUser();
    static ListTiket listTiket = new ListTiket();
    static ListWahana listWahana = new ListWahana();
    static ListBarang listBarang = new ListBarang();
    
    public static void main(String[] args) {
        // admin
        listAdmin.addAdmin("admin", "Administrator", "admin123");

        // dummy wahana
        listWahana.addWahana("Roller Coaster", "Ekstrem", 24);
        listWahana.addWahana("Bianglala", "Santai", 40);
        listWahana.addWahana("Kora-Kora", "Sedang", 30);
        listWahana.addWahana("Rumah Hantu", "Horor", 15);
        listWahana.addWahana("Bumper Car", "Anak", 20);
        
        // tambahkan koneksi antar wahana (edge dengan jarak dalam meter)
        listWahana.addEdge("W001", "W002", 150);
        listWahana.addEdge("W002", "W001", 150);
        listWahana.addEdge("W001", "W003", 200);
        listWahana.addEdge("W003", "W001", 200);
        listWahana.addEdge("W002", "W003", 100);
        listWahana.addEdge("W003", "W002", 100);
        listWahana.addEdge("W002", "W004", 180);
        listWahana.addEdge("W004", "W002", 180);
        listWahana.addEdge("W003", "W005", 120);
        listWahana.addEdge("W005", "W003", 120);
        listWahana.addEdge("W004", "W005", 90);
        listWahana.addEdge("W005", "W004", 90);

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
        listTiket.enqueue("Regular", 50000, "SYSTEM");
        listTiket.enqueue("VIP", 100000, "SYSTEM");
        listTiket.enqueue("Family Package", 150000, "SYSTEM");

        // tiket dummy untuk user
        tambahDummyTiketUser("Regular", 50000, "user1");
        tambahDummyTiketUser("VIP", 50000, "user1");
        tambahDummyTiketUser("VIP", 50000, "user2");
        tambahDummyTiketUser("Regular", 50000, "user2");
        tambahDummyTiketUser("Family Package", 50000, "user3");
        tambahDummyTiketUser("Regular", 50000, "user3");
        tambahDummyTiketUser("Regular", 50000, "user3");
        tambahDummyTiketUser("VIP", 50000, "user3");
        tambahDummyTiketUser("Regular", 50000, "user4");
        tambahDummyTiketUser("VIP", 50000, "user5");
        tambahDummyTiketUser("Regular", 50000, "user5");
        tambahDummyTiketUser("Family Package", 50000, "user6");
        tambahDummyTiketUser("VIP", 50000, "user6");
        tambahDummyTiketUser("Family Package", 50000, "user6");
        tambahDummyTiketUser("Family Package", 50000, "user7");
        
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
                    System.out.println("  Pilihan tidak valid!");
                    tekanEnter();
            }
        }
        
        scanner.close();
    }
    
    // Bantuan untuk jumlah tiket

    public static void tambahDummyTiketUser(String jenis, double harga, String userId) {
        NodeUser user = listUser.searchUser(userId);
        if (user != null) {
            listTiket.push(jenis, harga, userId);
            listTiket.enqueue(jenis, harga, userId);
            user.setNoTiket(user.getNoTiket() + 1);
        }
    }

    // TAMPILAN HEADER

    public static void tampilkanHeaderUtama() {
        System.out.println("╔════════════════════════════════════════════════════════╗");
        System.out.println("║                                                        ║");
        System.out.println("║                    SELAMAT DATANG!                     ║");
        System.out.println("║                                                        ║");
        System.out.println("║                       FUNWORLD                         ║");
        System.out.println("║                                                        ║");
        System.out.println("╚════════════════════════════════════════════════════════╝");
    }
    
    // LOGIN & REGISTER
    
    public static void loginAdmin() {
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
            System.out.println("  Login berhasil! Selamat datang, " + admin.getNama());
            tekanEnter();
            menuAdmin(admin);
        } else {
            System.out.println("  Login gagal! Admin ID atau password salah.");
            System.out.println("  (Default: admin / admin123)");
            tekanEnter();
        }
    }
    
    public static void loginUser() {
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
            System.out.println("  Login berhasil! Selamat datang, " + user.getNama());
            tekanEnter();
            menuUser(user);
        } else {
            System.out.println("  Login gagal! User ID atau password salah.");
            System.out.println("  (Coba: user1 / pass123)");
            tekanEnter();
        }
    }
    
    public static void registerUser() {
        clearScreen();
        System.out.println("╔════════════════════════════════════════════════════════╗");
        System.out.println("║              REGISTER USER BARU                        ║");
        System.out.println("╚════════════════════════════════════════════════════════╝");
        
        System.out.print("User ID (username): ");
        String userId = scanner.nextLine();
        
        // cek apakah userId sudah ada
        if (listUser.searchUser(userId) != null) {
            System.out.println("  User ID sudah digunakan! Pilih yang lain.");
            tekanEnter();
            return;
        }
        
        System.out.print("Nama Lengkap: ");
        String nama = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        
        listUser.addUser(userId, nama, password);
        System.out.println("  Registrasi berhasil! Silakan login.");
        tekanEnter();
    }
    
    //  MENU ADMIN
    
    public static void menuAdmin(NodeAdmin admin) {
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
                    System.out.println("  Logout berhasil!");
                    tekanEnter();
                    running = false;
                    break;
                default:
                    System.out.println("  Pilihan tidak valid!");
                    tekanEnter();
            }
        }
    }
    
    public static void menuKelolaTiket() {
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
                    System.out.println("  Pilihan tidak valid!");
                    tekanEnter();
            }
        }
    }
    
    public static void tambahTiket() {
        System.out.print("\nJenis Tiket: ");
        String jenis = scanner.nextLine();
        System.out.print("Harga: Rp ");
        double harga = inputDouble();
        
        listTiket.enqueue(jenis, harga, "SYSTEM");
        System.out.println("  Tiket baru berhasil ditambahkan!");
        tekanEnter();
    }
    
    public static void editHargaTiket() {
        listTiket.displayJenisTiketTersedia();
        System.out.print("\nMasukkan jenis tiket yang ingin diedit: ");
        String jenis = scanner.nextLine();
        System.out.print("Harga baru: Rp ");
        double hargaBaru = inputDouble();
        
        listTiket.editHargaTiket(jenis, hargaBaru);
        tekanEnter();
    }
    
    public static void menuKelolaUser() {
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
                    System.out.println("  Pilihan tidak valid!");
                    tekanEnter();
            }
        }
    }
    
    public static void hapusUser() {
        System.out.print("\nMasukkan User ID yang akan dihapus: ");
        String userId = scanner.nextLine();
        listUser.deleteUser(userId);
        tekanEnter();
    }
    
    public static void editUser() {

        System.out.print("\nMasukkan User ID yang akan diedit: ");
        String userId = scanner.nextLine();
        
        NodeUser user = listUser.searchUser(userId);
        if (user == null) {
            System.out.println("  User tidak ditemukan!");
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
    
    public static void menuKelolaWahana() {
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
                    System.out.println("  Pilihan tidak valid!");
                    tekanEnter();
            }
        }
    }
    
    public static void tambahWahana() {
        System.out.print("\nNama Wahana: ");
        String nama = scanner.nextLine();
        String kategori;
        boolean validKategori = false;
        do {
            System.out.print("Kategori (Ekstrem/Sedang/Santai/Anak/Horor): ");
            kategori = scanner.nextLine().trim(); // Trim untuk menghilangkan spasi
        
            String kategoriLower = kategori.toLowerCase();
        
            if (kategoriLower.equals("ekstrem") ||
                kategoriLower.equals("sedang") ||
                kategoriLower.equals("santai") ||
                kategoriLower.equals("anak") ||
                kategoriLower.equals("horor"))
            {
                kategori = kategori.substring(0, 1).toUpperCase() + kategoriLower.substring(1);
                validKategori = true;
            } else {
                System.out.println("  Kategori tidak valid! Harap masukkan salah satu dari: Ekstrem/Sedang/Santai/Anak/Horor.");
            }
        } while (!validKategori);
        System.out.print("Kapasitas: ");
        int kapasitas = inputInt();
        
        listWahana.addWahana(nama, kategori, kapasitas);
        System.out.println("  Wahana baru berhasil ditambahkan!");
        tekanEnter();
    }
    
    public static void editWahana() {
        listWahana.displayList();
        System.out.print("\nMasukkan ID Wahana yang akan diedit: ");
        String id = scanner.nextLine();
        
        NodeWahana wahana = listWahana.searchWahana(id);
        if (wahana == null) {
            System.out.println("  Wahana tidak ditemukan!");
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
    
    public static void hapusWahana() {
        listWahana.displayList();
        System.out.print("\nMasukkan ID Wahana yang akan dihapus: ");
        String id = scanner.nextLine();
        listWahana.deleteWahana(id);
        tekanEnter();
    }
    
    public static void tambahRuteWahana() {
        listWahana.displayList();
        System.out.print("\nID Wahana Asal: ");
        String idAsal = scanner.nextLine();
        System.out.print("ID Wahana Tujuan: ");
        String idTujuan = scanner.nextLine();
        System.out.print("Jarak (meter): ");
        int jarak = inputInt();
        
        if (listWahana.addEdge(idAsal, idTujuan, jarak)) {
            System.out.println("  Rute berhasil ditambahkan!");
        } else {
            System.out.println("  Gagal menambahkan rute! Periksa ID wahana.");
        }
        tekanEnter();
    }
    
    public static void menuKelolaBarang() {
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
                    System.out.println("  Pilihan tidak valid!");
                    tekanEnter();
            }
        }
    }
    
    public static void hapusBarang() {
        System.out.print("\nMasukkan Kode Barang: ");
        String kode = scanner.nextLine();
        listBarang.deleteBarang(kode);
        tekanEnter();
    }
    
    // MENU USER
    
    public static void menuUser(NodeUser user) {
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
                    System.out.println("  Logout berhasil!");
                    tekanEnter();
                    running = false;
                    break;
                default:
                    System.out.println("  Pilihan tidak valid!");
                    tekanEnter();
            }
        }
    }
    
    public static void cariJalurTerpendek() {
        listWahana.displayList();
        System.out.print("\nID Wahana Asal: ");
        String idAsal = scanner.nextLine();
        System.out.print("ID Wahana Tujuan: ");
        String idTujuan = scanner.nextLine();
        
        listWahana.dijkstra(idAsal, idTujuan);
        tekanEnter();
    }
    
    public static void pesanTiket(NodeUser user) {
        listTiket.displayJenisTiketTersedia();
        System.out.print("\nMasukkan jenis tiket yang ingin dibeli: ");
        String jenis = scanner.nextLine();
        
        NodeTiket tiket = listTiket.searchTiketByJenis(jenis);
        if (tiket == null) {
            System.out.println("  Jenis tiket tidak ditemukan!");
            tekanEnter();
            return;
        }
        
        // push ke stack user
        listTiket.push(jenis, tiket.getHarga(), user.getUserId());
        
        // enqueue ke riwayat pembelian
        listTiket.enqueue(jenis, tiket.getHarga(), user.getUserId());
        
        // update jumlah tiket user
        user.setNoTiket(user.getNoTiket() + 1);
        
        System.out.println("  Tiket berhasil dibeli!");
        System.out.println("  Total: Rp" + tiket.getHarga());
        tekanEnter();
    }
    
    public static void titipBarang(NodeUser user) {
        System.out.print("\nJenis Barang: ");
        String jenis = scanner.nextLine();
        
        listBarang.enqueue(jenis, user.getUserId());
        tekanEnter();
    }
    
    public static void ambilBarangUser(NodeUser user) {
        listBarang.displayByUser(user.getUserId());
        System.out.print("\nMasukkan Kode Barang: ");
        String kode = scanner.nextLine();
        
        listBarang.ambilBarang(kode, user.getUserId());
        tekanEnter();
    }
    
    // UTILITY FUNCTIONS
    
    public static void clearScreen() {
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
    
    public static void tekanEnter() {
        System.out.print("\nTekan Enter untuk melanjutkan...");
        scanner.nextLine();
    }
    
    public static int inputInt() {
        while (true) {
            try {
                int value = Integer.parseInt(scanner.nextLine());
                return value;
            } catch (NumberFormatException e) {
                System.out.print("  Input harus berupa angka! Coba lagi: ");
            }
        }
    }
    
    public static double inputDouble() {
        while (true) {
            try {
                double value = Double.parseDouble(scanner.nextLine());
                return value;
            } catch (NumberFormatException e) {
                System.out.print("  Input harus berupa angka! Coba lagi: ");
            }
        }
    }
}