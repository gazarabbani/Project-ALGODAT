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
        System.out.println("  Barang berhasil dititipkan!");
        System.out.println("  Kode Barang Anda: " + kodeBarang);
        System.out.println("  Simpan kode ini untuk mengambil barang!");
    }
    
    public NodeBarang dequeue() {
        if (front == null) {
            System.out.println("  Tidak ada barang titipan!");
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

    public NodeBarang searchBarang(String kodeBarang) {
        NodeBarang current = front;
        while (current != null) {
            if (current.getKodeBarang().equals(kodeBarang)) {
                return current;
            }
            current = current.getNext();
        }
        return null;
    }
    
    public boolean ambilBarang(String kodeBarang, String userId) {
        if (front == null) {
            System.out.println("  Tidak ada barang titipan!");
            return false;
        }
        
        //cek apakah barang pertama
        if (front.getKodeBarang().equals(kodeBarang)) {
            if (!front.getUserId().equals(userId)) {
                System.out.println("  Ini bukan barang Anda!");
                return false;
            }
            NodeBarang barang = dequeue();
            System.out.println("  Barang berhasil diambil!");
            System.out.println("  " + barang.toString());
            return true;
        }
        
        // cari di tengah/belakang
        NodeBarang current = front;
        while (current.getNext() != null) {
            if (current.getNext().getKodeBarang().equals(kodeBarang)) {
                if (!current.getNext().getUserId().equals(userId)) {
                    System.out.println("  Ini bukan barang Anda!");
                    return false;
                }
                
                NodeBarang barang = current.getNext();
                current.setNext(current.getNext().getNext());
                
                if (current.getNext() == null) {
                    rear = current;
                }
                
                size--;
                System.out.println("  Barang berhasil diambil!");
                System.out.println("  " + barang.toString());
                return true;
            }
            current = current.getNext();
        }
        
        System.out.println("  Barang dengan kode '" + kodeBarang + "' tidak ditemukan!");
        return false;
    }

}