public class ListAdmin {
    private NodeAdmin head;
    
    public ListAdmin() {
        this.head = null;
    }
    
    // tambah admin baru
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
    }
    
    // validasi login admin
    public NodeAdmin login(String adminId, String password) {
        NodeAdmin admin = head;
        if (admin != null && admin.getPassword().equals(password)) {
            return admin;
        }
        return null;
    }
    
    public boolean isEmpty() {
        return head == null;
    }
    
}