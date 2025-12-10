public class ListAdmin {
    private NodeAdmin head;
    private int size;
    
    public ListAdmin() {
        this.head = null;
        this.size = 0;
        // default admin
        addAdmin("admin", "Administrator", "admin123");
    }
    
    public void addAdmin(String adminId, String nama, String password) {
        NodeAdmin newNode = new NodeAdmin(adminId, nama, password);
        if (head == null) {
            head = newNode;
        } else {
            NodeAdmin current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }
        size++;
    }
    
    public NodeAdmin searchAdmin(String adminId) {
        NodeAdmin current = head;
        while (current != null) {
            if (current.getAdminId().equals(adminId)) {
                return current;
            }
            current = current.getNext();
        }
        return null;
    }
    
    public NodeAdmin login(String adminId, String password) {
        NodeAdmin admin = searchAdmin(adminId);
        if (admin != null && admin.getPassword().equals(password)) {
            return admin;
        }
        return null;
    }
    
    public void displayAll() {
        if (head == null) {
            System.out.println("Tidak ada admin terdaftar.");
            return;
        }
        
        System.out.println("\n╔════════════════════════════════════════════════════════╗");
        System.out.println("║                   DAFTAR ADMIN                         ║");
        System.out.println("╠════════════════════════════════════════════════════════╣");
        NodeAdmin current = head;
        while (current != null) {
            System.out.println("║ " + current.toString());
            current = current.getNext();
        }
        System.out.println("╚════════════════════════════════════════════════════════╝");
    }

    public void displaySearch(String adminId) {
        NodeAdmin result = searchAdmin(adminId);
        if (result != null) {
            System.out.println("\n=== HASIL PENCARIAN ADMIN ===");
            System.out.println(result.toString());
        } else {
            System.out.println("\nAdmin dengan ID " + adminId + " tidak ditemukan.");
        }
    }
    
    public boolean isEmpty() {
        return head == null;
    }
    
    public int getSize() {
        return size;
    }
}