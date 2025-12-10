public class NodeUser {
    private String userId;
    private String nama;
    private String password;
    private int noTiket;
    private NodeUser next;
    
    public NodeUser(String userId, String nama, String password) {
        this.userId = userId;
        this.nama = nama;
        this.password = password;
        this.noTiket = 0;
        this.next = null;
    }
    
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    
    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    public int getNoTiket() { return noTiket; }
    public void setNoTiket(int noTiket) { this.noTiket = noTiket; }
    
    public NodeUser getNext() { return next; }
    public void setNext(NodeUser next) { this.next = next; }
    
    @Override
    public String toString() {
        return "UserID: " + userId + " | Nama: " + nama + " | Jumlah Tiket: " + noTiket;
    }
}