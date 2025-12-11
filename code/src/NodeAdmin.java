public class NodeAdmin {
    private String adminId;
    private String nama;
    private String password;
    private NodeAdmin next;
    
    public NodeAdmin(String adminId, String nama, String password) {
        this.adminId = adminId;
        this.nama = nama;
        this.password = password;
        this.next = null;
    }
    
    public String getAdminId() { return adminId; }
    public void setAdminId(String adminId) { this.adminId = adminId; }
    
    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    public NodeAdmin getNext() { return next; }
    public void setNext(NodeAdmin next) { this.next = next; }
    
    @Override
    public String toString() {
        return "AdminID: " + adminId + " | Nama: " + nama;
    }
}
