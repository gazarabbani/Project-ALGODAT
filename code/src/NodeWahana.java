public class NodeWahana {
    private String idWahana;
    private String namaWahana;
    private String kategori;
    private int kapasitas;
    private EdgeWahana edges; // adjacency list untuk koneksi ke wahana lain
    private NodeWahana next;
    
    public NodeWahana(String idWahana, String namaWahana, String kategori, int kapasitas) {
        this.idWahana = idWahana;
        this.namaWahana = namaWahana;
        this.kategori = kategori;
        this.kapasitas = kapasitas;
        this.edges = null;
        this.next = null;
    }
    
    public String getIdWahana() { return idWahana; }
    public void setIdWahana(String idWahana) { this.idWahana = idWahana; }
    
    public String getNamaWahana() { return namaWahana; }
    public void setNamaWahana(String namaWahana) { this.namaWahana = namaWahana; }
    
    public String getKategori() { return kategori; }
    public void setKategori(String kategori) { this.kategori = kategori; }
    
    public int getKapasitas() { return kapasitas; }
    public void setKapasitas(int kapasitas) { this.kapasitas = kapasitas; }
    
    public EdgeWahana getEdges() { return edges; }
    public void setEdges(EdgeWahana edges) { this.edges = edges; }
    
    public NodeWahana getNext() { return next; }
    public void setNext(NodeWahana next) { this.next = next; }
    
    @Override
    public String toString() {
        return "ID: " + idWahana + " | Wahana: " + namaWahana + 
               " | Kategori: " + kategori + " | Kapasitas: " + kapasitas;
    }
}

// untuk edge dalam graph (koneksi antar wahana sama jarak)
class EdgeWahana {
    private NodeWahana tujuan;
    private int jarak; // jarak dlm meter
    private EdgeWahana next;
    
    public EdgeWahana(NodeWahana tujuan, int jarak) {
        this.tujuan = tujuan;
        this.jarak = jarak;
        this.next = null;
    }
    
    public NodeWahana getTujuan() { return tujuan; }
    public void setTujuan(NodeWahana tujuan) { this.tujuan = tujuan; }
    
    public int getJarak() { return jarak; }
    public void setJarak(int jarak) { this.jarak = jarak; }
    
    public EdgeWahana getNext() { return next; }
    public void setNext(EdgeWahana next) { this.next = next; }
}
