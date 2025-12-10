import java.text.SimpleDateFormat;
import java.util.Date;

public class ListBarang {
    private NodeBarang front;
    private NodeBarang rear;
    private int size;
    private int idCounter;
    
    public ListBarang() {
        this.front = null;
        this.rear = null;
        this.size = 0;
        this.idCounter = 1;
    }
    
    // enqueue/titip barang (tambah dari belakang)
    public void enqueue(String jenisBarang, String userId) {
        String timestamp = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
        String kodeBarang = "B" + String.format("%04d", idCounter++);
        NodeBarang newNode = new NodeBarang(kodeBarang, jenisBarang, userId, timestamp);
        
        if (rear == null) {
            front = rear = newNode;
        } else {
            rear.setNext(newNode);
            rear = newNode;
        }
        size++;
        System.out.println("✓ Barang berhasil dititipkan!");
        System.out.println("  Kode Barang Anda: " + kodeBarang);
        System.out.println("  Simpan kode ini untuk mengambil barang!");
    }
    
    // dequeue/ambil barang (hapus dari depan)
    public NodeBarang dequeue() {
        if (front == null) {
            System.out.println("✗ Tidak ada barang titipan!");
            return null;
        }
        
        NodeBarang temp = front;
        front = front.getNext();
        
        if (front == null) {
            rear = null;
        }
        
        size--;
        return temp;
    }
  
    // hapus barang (buat admin) gapake validasi userId
    public boolean deleteBarang(String kodeBarang) {
        if (front == null) {
            System.out.println("✗ Tidak ada barang titipan!");
            return false;
        }
        
        if (front.getKodeBarang().equals(kodeBarang)) {
            NodeBarang barang = dequeue();
            System.out.println("✓ Barang berhasil dihapus!");
            System.out.println("  " + barang.toString());
            return true;
        }
        
        NodeBarang current = front;
        while (current.getNext() != null) {
            if (current.getNext().getKodeBarang().equals(kodeBarang)) {
                NodeBarang barang = current.getNext();
                current.setNext(current.getNext().getNext());
                
                if (current.getNext() == null) {
                    rear = current;
                }
                
                size--;
                System.out.println("✓ Barang berhasil dihapus!");
                System.out.println("  " + barang.toString());
                return true;
            }
            current = current.getNext();
        }
        
        System.out.println("✗ Barang dengan kode '" + kodeBarang + "' tidak ditemukan!");
        return false;
    }
    
    // display semua barang titipan
    public void displayAll() {
        if (front == null) {
            System.out.println("Tidak ada barang titipan.");
            return;
        }
        
        System.out.println("\n╔═══════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                         DAFTAR BARANG TITIPAN                             ║");
        System.out.println("╠═══════════════════════════════════════════════════════════════════════════╣");
        
        NodeBarang current = front;
        while (current != null) {
            System.out.println("║ " + current.toString());
            current = current.getNext();
        }
        System.out.println("╚═══════════════════════════════════════════════════════════════════════════╝");
    }
    
    // display barang user yg mau di cari (pake id user)
    public void displayByUser(String userId) {
        if (front == null) {
            System.out.println("Anda belum menitipkan barang.");
            return;
        }
        
        System.out.println("\n╔═══════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                         BARANG TITIPAN ANDA                               ║");
        System.out.println("╠═══════════════════════════════════════════════════════════════════════════╣");
        
        NodeBarang current = front;
        int count = 0;
        while (current != null) {
            if (current.getUserId().equals(userId)) {
                System.out.println("║ " + current.toString());
                count++;
            }
            current = current.getNext();
        }
        
        if (count == 0) {
            System.out.println("║ Anda belum menitipkan barang.                                             ║");
        }
        System.out.println("╚═══════════════════════════════════════════════════════════════════════════╝");
    }
    
    public boolean isEmpty() {
        return front == null;
    }
    
    public int getSize() {
        return size;
    }
}