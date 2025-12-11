public class NodeTiket {
    private String idTiket;
    private String jenisTiket;
    private double harga;
    private String userId;
    private String tanggal;
    private NodeTiket next;
    
    public NodeTiket(String idTiket, String jenisTiket, double harga, String userId, String tanggal) {
        this.idTiket = idTiket;
        this.jenisTiket = jenisTiket;
        this.harga = harga;
        this.userId = userId;
        this.tanggal = tanggal;
        this.next = null;
    }
    
    public String getIdTiket() { return idTiket; }
    public void setIdTiket(String idTiket) { this.idTiket = idTiket; }
    
    public String getJenisTiket() { return jenisTiket; }
    public void setJenisTiket(String jenisTiket) { this.jenisTiket = jenisTiket; }
    
    public double getHarga() { return harga; }
    public void setHarga(double harga) { this.harga = harga; }
    
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    
    public String getTanggal() { return tanggal; }
    public void setTanggal(String tanggal) { this.tanggal = tanggal; }
    
    public NodeTiket getNext() { return next; }
    public void setNext(NodeTiket next) { this.next = next; }
    
    @Override
    public String toString() {
        return "ID: " + idTiket + " | Jenis: " + jenisTiket + " | Harga: Rp" + harga + 
               " | User: " + userId + " | Tanggal: " + tanggal;
    }
}
