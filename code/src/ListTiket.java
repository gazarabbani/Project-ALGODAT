import java.text.SimpleDateFormat;
import java.util.Date;

public class ListTiket {
    private NodeTiket front;
    private NodeTiket rear;
    private NodeTiket top;
    private int queueSize;
    private int stackSize;
    private int idCounter;
    
    public ListTiket() {
        this.front = null;
        this.rear = null;
        this.top = null;
        this.queueSize = 0;
        this.stackSize = 0;
        this.idCounter = 1;
    }
    
    // tambah tiket ke riwayat (dari belakang)
    public void enqueue(String jenisTiket, double harga, String userId) {
        String timestamp = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
        NodeTiket newNode = new NodeTiket("T" + String.format("%03d", idCounter++), 
                                          jenisTiket, harga, userId, timestamp);
        
        if (rear == null) {
            front = rear = newNode;
        } else {
            rear.setNext(newNode);
            rear = newNode;
        }
        queueSize++;
    }

    // hapus tiket berdasarkan ID tiket
    public boolean dequeue(String idTiket) {
        if (front == null) {
            System.out.println("✗ Queue tiket kosong!");
            return false;
        }

        if (front.getIdTiket().equals(idTiket)) {
            front = front.getNext();
            queueSize--;
            System.out.println("✓ Tiket dengan ID " + idTiket + " berhasil dihapus dari riwayat!");
            return true;
        }
        NodeTiket current = front;
        while (current.getNext() != null) {
            if (current.getNext().getIdTiket().equals(idTiket)) {
                current.setNext(current.getNext().getNext());
                queueSize--;
                System.out.println("✓ Tiket dengan ID " + idTiket + " berhasil dihapus dari riwayat!");
                return true;
            }
            current = current.getNext();
        }
        System.out.println("✗ Tiket dengan ID " + idTiket + " tidak ditemukan!");
        return false;
    }
    
    // display semua riwayat pembelian
    public void displayQueue() {
        if (front == null) {
            System.out.println("Tidak ada riwayat pembelian tiket.");
            return;
        }
        
        System.out.println("\n╔═══════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                         RIWAYAT PEMBELIAN TIKET                           ║");
        System.out.println("╠═══════════════════════════════════════════════════════════════════════════╣");
        
        NodeTiket current = front;
        while (current != null) {
            System.out.println("║ " + current.toString());
            current = current.getNext();
        }
        System.out.println("╚═══════════════════════════════════════════════════════════════════════════╝");
    }
    
    // cari tiket berdasarkan jenis untuk edit harga
    public NodeTiket searchTiketByJenis(String jenisTiket) {
        NodeTiket current = front;
        while (current != null) {
            if (current.getJenisTiket().equalsIgnoreCase(jenisTiket)) {
                return current;
            }
            current = current.getNext();
        }
        return null;
    }
    
    // edit harga tiket berdasarkan jenis
    public boolean editHargaTiket(String jenisTiket, double hargaBaru) {
        NodeTiket current = front;
        boolean found = false;
        
        while (current != null) {
            if (current.getJenisTiket().equalsIgnoreCase(jenisTiket)) {
                current.setHarga(hargaBaru);
                found = true;
            }
            current = current.getNext();
        }
        
        if (found) {
            System.out.println("✓ Harga tiket " + jenisTiket + " berhasil diupdate!");
            return true;
        } else {
            System.out.println("✗ Tiket dengan jenis '" + jenisTiket + "' tidak ditemukan!");
            return false;
        }
    }
    
    // STACK (Tiket User) 
    
    // tambah tiket ke stack user
    public void push(String jenisTiket, double harga, String userId) {
        String timestamp = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
        NodeTiket newNode = new NodeTiket("UT" + String.format("%03d", stackSize + 1), 
                                          jenisTiket, harga, userId, timestamp);
        
        newNode.setNext(top);
        top = newNode;
        stackSize++;
    }
    
    // hapus tiket paling atas dari stack
    public NodeTiket pop() {
        if (top == null) {
            System.out.println("✗ Stack tiket kosong!");
            return null;
        }
        
        NodeTiket temp = top;
        top = top.getNext();
        stackSize--;
        return temp;
    }
    
    // lihat tiket paling atas tanpa menghapus
    public NodeTiket peek() {
        return top;
    }
    
    // display stack/tampilkan tiket user
    public void displayStack(String userId) {
        if (top == null) {
            System.out.println("Anda belum memiliki tiket.");
            return;
        }
        
        System.out.println("\n╔═══════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                            TIKET ANDA                                     ║");
        System.out.println("╠═══════════════════════════════════════════════════════════════════════════╣");
        
        NodeTiket current = top;
        int count = 0;
        while (current != null) {
            if (current.getUserId().equals(userId)) {
                System.out.println("║ " + current.toString());
                count++;
            }
            current = current.getNext();
        }
        
        if (count == 0) {
            System.out.println("║ Anda belum memiliki tiket.                                                ║");
        }
        System.out.println("╚═══════════════════════════════════════════════════════════════════════════╝");
    }
    
    // get daftar jenis tiket tersedia
    public void displayJenisTiketTersedia() {
        if (front == null) {
            System.out.println("Tidak ada tiket tersedia.");
            return;
        }
        
        System.out.println("\n╔═══════════════════════════════════════════════════════╗");
        System.out.println("║              JENIS TIKET TERSEDIA                     ║");
        System.out.println("╠═══════════════════════════════════════════════════════╣");
        
        NodeTiket current = front;
        while (current != null) {
            if (current.getUserId().equals("SYSTEM")) {
                System.out.println("║ Jenis: " + current.getJenisTiket() + " | Harga: Rp" + current.getHarga());
            }
            current = current.getNext();
        }
        System.out.println("╚═══════════════════════════════════════════════════════╝");
    }
    
    public boolean isQueueEmpty() {
        return front == null;
    }
    
    public boolean isStackEmpty() {
        return top == null;
    }
    
    public int getQueueSize() {
        return queueSize;
    }
    
    public int getStackSize() {
        return stackSize;
    }
}