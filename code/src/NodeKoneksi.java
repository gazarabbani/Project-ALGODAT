public class NodeKoneksi { //edge untuk graph wahana
    String namaWahana;
    NodeKoneksi next;

    public NodeKoneksi(String namaWahana){
        this.namaWahana = namaWahana;
        this.next = null;
    }
}
