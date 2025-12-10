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