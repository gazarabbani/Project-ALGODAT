public class NodeBarang {
    private String kodeBarang;
    private String jenisBarang;
    private String userId;
    private String waktuTitip;
    private NodeBarang next;
    
    public NodeBarang(String kodeBarang, String jenisBarang, String userId, String waktuTitip) {
        this.kodeBarang = kodeBarang;
        this.jenisBarang = jenisBarang;
        this.userId = userId;
        this.waktuTitip = waktuTitip;
        this.next = null;
    }
    
    public String getKodeBarang() { return kodeBarang; }
    public void setKodeBarang(String kodeBarang) { this.kodeBarang = kodeBarang; }
    
    public String getJenisBarang() { return jenisBarang; }
    public void setJenisBarang(String jenisBarang) { this.jenisBarang = jenisBarang; }
    
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    
    public String getWaktuTitip() { return waktuTitip; }
    public void setWaktuTitip(String waktuTitip) { this.waktuTitip = waktuTitip; }
    
    public NodeBarang getNext() { return next; }
    public void setNext(NodeBarang next) { this.next = next; }
    
    @Override
    public String toString() {
        return "Kode: " + kodeBarang + " | Jenis: " + jenisBarang +
               " | User: " + userId + " | Waktu: " + waktuTitip;
    }
}