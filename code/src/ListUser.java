public class ListUser {
    private NodeUser head;
    private int size;
    
    public ListUser() {
        this.head = null;
        this.size = 0;
    }
    
    // tambah user baru (untuk registrasi)
    public void addUser(String userId, String nama, String password) {
        NodeUser newNode = new NodeUser(userId, nama, password);
        if (head == null) {
            head = newNode;
        } else {
            NodeUser current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }
        size++;
        System.out.println("✓ User berhasil ditambahkan!");
    }
    
    // cari user berdasarkan userId
    public NodeUser searchUser(String userId) {
        NodeUser current = head;
        while (current != null) {
            if (current.getUserId().equals(userId)) {
                return current;
            }
            current = current.getNext();
        }
        return null;
    }
    
    // validasi login user
    public NodeUser login(String userId, String password) {
        NodeUser user = searchUser(userId);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
    
    // hapus user (Dequeue - hapus dari depan)
    public boolean deleteUser(String userId) {
        if (head == null) {
            System.out.println("✗ List user kosong!");
            return false;
        }
        
        if (head.getUserId().equals(userId)) {
            head = head.getNext();
            size--;
            System.out.println("✓ User berhasil dihapus!");
            return true;
        }
        
        NodeUser current = head;
        while (current.getNext() != null) {
            if (current.getNext().getUserId().equals(userId)) {
                current.setNext(current.getNext().getNext());
                size--;
                System.out.println("✓ User berhasil dihapus!");
                return true;
            }
            current = current.getNext();
        }
        
        System.out.println("✗ User tidak ditemukan!");
        return false;
    }
    
    // edit data user
    public boolean editUser(String userId, String namaBaru, String passwordBaru) {
        NodeUser user = searchUser(userId);
        if (user != null) {
            if (namaBaru != null && !namaBaru.isEmpty()) {
                user.setNama(namaBaru);
            }
            if (passwordBaru != null && !passwordBaru.isEmpty()) {
                user.setPassword(passwordBaru);
            }
            System.out.println("  Data user berhasil diupdate!");
            return true;
        }
        System.out.println("  User tidak ditemukan!");
        return false;
    }
    

    public void displayUserSorted() {
        if (head == null) {
            System.out.println("  Daftar user kosong.");
            return;
        }

        // BUAT SALINAN LIST (Agar urutan asli di head tidak rusak)
        NodeUser tempHead = new NodeUser(head.getUserId(), head.getNama(), head.getPassword());
        tempHead.setNoTiket(head.getNoTiket());
    
        NodeUser currentOriginal = head.getNext();
        NodeUser currentNew = tempHead;

        while (currentOriginal != null) {
            NodeUser newNode = new NodeUser(currentOriginal.getUserId(), currentOriginal.getNama(), currentOriginal.getPassword());
            newNode.setNoTiket(currentOriginal.getNoTiket());
            currentNew.setNext(newNode);
            currentNew = newNode;
            currentOriginal = currentOriginal.getNext();
        }

        // SORTING LIST SALINAN (Bubble Sort)
        for (NodeUser i = tempHead; i != null; i = i.getNext()) {
            for (NodeUser j = i.getNext(); j != null; j = j.getNext()) {
                if (i.getNoTiket() < j.getNoTiket()) {
                    String tId = i.getUserId();
                    String tNama = i.getNama();
                    int tTiket = i.getNoTiket();

                    i.setUserId(j.getUserId());
                    i.setNama(j.getNama());
                    i.setNoTiket(j.getNoTiket());

                    j.setUserId(tId);
                    j.setNama(tNama);
                    j.setNoTiket(tTiket);
                }
            }
        }

        System.out.println("\n╔════════════════════════════════════════════════════════╗");
        System.out.println("║                    DAFTAR USER                         ║");
        System.out.println("╠════════════════════════════════════════════════════════╣");
        NodeUser printTemp = tempHead;
        while (printTemp != null) {
            System.out.println("ID: " + printTemp.getUserId() + " | Nama: " + printTemp.getNama() + " | Tiket: " + printTemp.getNoTiket());
            printTemp = printTemp.getNext();
        }
    }

    // display semua user (blum sorting)
    public void displayAll() {
        if (head == null) {
            System.out.println("Tidak ada user terdaftar.");
            return;
        }
        
        System.out.println("\n╔════════════════════════════════════════════════════════╗");
        System.out.println("║                    DAFTAR USER                         ║");
        System.out.println("╠════════════════════════════════════════════════════════╣");
        NodeUser current = head;
        while (current != null) {
            System.out.println("║ " + current.toString());
            current = current.getNext();
        }
        System.out.println("╚════════════════════════════════════════════════════════╝");
    }
    
    public boolean isEmpty() {
        return head == null;
    }
    
    public int getSize() {
        return size;
    }
}