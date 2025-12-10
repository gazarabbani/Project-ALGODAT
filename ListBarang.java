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