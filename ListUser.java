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
            System.out.println("✓ Data user berhasil diupdate!");
            return true;
        }
        System.out.println("✗ User tidak ditemukan!");
        return false;
    }
    
    // display semua user pake sorting berdasarkan noTiket (Bubble Sort)
    public void displayUserSorted() {
        if (head == null) {
            System.out.println("Tidak ada user terdaftar.");
            return;
        }
        
        // convert linked list ke array buat sorting
        NodeUser[] userArray = new NodeUser[size];
        NodeUser current = head;
        int idx = 0;
        while (current != null) {
            userArray[idx++] = current;
            current = current.getNext();
        }
        
        // bubble sort berdasarkan noTiket (descending)
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (userArray[j].getNoTiket() < userArray[j + 1].getNoTiket()) {
                    NodeUser temp = userArray[j];
                    userArray[j] = userArray[j + 1];
                    userArray[j + 1] = temp;
                }
            }
        }
        
        System.out.println("\n╔════════════════════════════════════════════════════════╗");
        System.out.println("║              DAFTAR USER (Sorted by Tiket)            ║");
        System.out.println("╠════════════════════════════════════════════════════════╣");
        for (NodeUser user : userArray) {
            System.out.println("║ " + user.toString());
        }
        System.out.println("╚════════════════════════════════════════════════════════╝");
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