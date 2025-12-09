import java.util.*;

public class ListWahana {
    private NodeWahana head;
    private int size;
    private int idCounter;
    
    public ListWahana() {
        this.head = null;
        this.size = 0;
        this.idCounter = 1;
        
        // inisialisasi wahana default
        initDefaultWahana();
    }
    
    // inisialisasi wahana dan rute default
    private void initDefaultWahana() {
        addWahana("Roller Coaster", "Ekstrem", 24);
        addWahana("Bianglala", "Santai", 40);
        addWahana("Kora-Kora", "Sedang", 30);
        addWahana("Rumah Hantu", "Horor", 15);
        addWahana("Bumper Car", "Anak", 20);
        
        // tambahkan koneksi antar wahana (edge dengan jarak dalam meter)
        addEdge("W001", "W002", 150);
        addEdge("W002", "W001", 150);
        addEdge("W001", "W003", 200);
        addEdge("W003", "W001", 200);
        addEdge("W002", "W003", 100);
        addEdge("W003", "W002", 100);
        addEdge("W002", "W004", 180);
        addEdge("W004", "W002", 180);
        addEdge("W003", "W005", 120);
        addEdge("W005", "W003", 120);
        addEdge("W004", "W005", 90);
        addEdge("W005", "W004", 90);
    }
    
    // tambah wahana baru ke graph
    public void addWahana(String namaWahana, String kategori, int kapasitas) {
        String idWahana = "W" + String.format("%03d", idCounter++);
        NodeWahana newNode = new NodeWahana(idWahana, namaWahana, kategori, kapasitas);
        
        if (head == null) {
            head = newNode;
        } else {
            NodeWahana current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }
        size++;
        System.out.println("✓ Wahana '" + namaWahana + "' berhasil ditambahkan dengan ID: " + idWahana);
    }
    
    // cari wahana berdasarkan id
    public NodeWahana searchWahana(String idWahana) {
        NodeWahana current = head;
        while (current != null) {
            if (current.getIdWahana().equals(idWahana)) {
                return current;
            }
            current = current.getNext();
        }
        return null;
    }
    
    // tambah edge (koneksi antar wahana)
    public boolean addEdge(String idWahanaAsal, String idWahanaTujuan, int jarak) {
        NodeWahana asal = searchWahana(idWahanaAsal);
        NodeWahana tujuan = searchWahana(idWahanaTujuan);
        
        if (asal == null || tujuan == null) {
            return false;
        }
        
        EdgeWahana newEdge = new EdgeWahana(tujuan, jarak);
        
        if (asal.getEdges() == null) {
            asal.setEdges(newEdge);
        } else {
            EdgeWahana current = asal.getEdges();
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newEdge);
        }
        return true;
    }
    
    // edit wahana
    public boolean editWahana(String idWahana, String namaBaru, String kategoriBaru, Integer kapasitasBaru) {
        NodeWahana wahana = searchWahana(idWahana);
        if (wahana != null) {
            if (namaBaru != null && !namaBaru.isEmpty()) {
                wahana.setNamaWahana(namaBaru);
            }
            if (kategoriBaru != null && !kategoriBaru.isEmpty()) {
                wahana.setKategori(kategoriBaru);
            }
            if (kapasitasBaru != null) {
                wahana.setKapasitas(kapasitasBaru);
            }
            System.out.println("✓ Wahana berhasil diupdate!");
            return true;
        }
        System.out.println("✗ Wahana tidak ditemukan!");
        return false;
    }
    
    // hapus wahana dari graph
    public boolean deleteWahana(String idWahana) {
        if (head == null) {
            System.out.println("✗ Graph wahana kosong!");
            return false;
        }
        
        // hapus edge yang mengarah ke wahana ini dari wahana lain
        NodeWahana current = head;
        while (current != null) {
            removeEdgeTo(current, idWahana);
            current = current.getNext();
        }
        
        // hapus node wahana
        if (head.getIdWahana().equals(idWahana)) {
            head = head.getNext();
            size--;
            System.out.println("✓ Wahana berhasil dihapus!");
            return true;
        }
        
        current = head;
        while (current.getNext() != null) {
            if (current.getNext().getIdWahana().equals(idWahana)) {
                current.setNext(current.getNext().getNext());
                size--;
                System.out.println("✓ Wahana berhasil dihapus!");
                return true;
            }
            current = current.getNext();
        }
        
        System.out.println("✗ Wahana tidak ditemukan!");
        return false;
    }
    
    // helper method untuk hapus edge ke wahana tertentu
    private void removeEdgeTo(NodeWahana wahana, String idTujuan) {
        EdgeWahana edge = wahana.getEdges();
        
        if (edge == null) return;
        
        // jika edge pertama mengarah ke tujuan
        if (edge.getTujuan().getIdWahana().equals(idTujuan)) {
            wahana.setEdges(edge.getNext());
            return;
        }
        
        // cari edge yang mengarah ke tujuan
        while (edge.getNext() != null) {
            if (edge.getNext().getTujuan().getIdWahana().equals(idTujuan)) {
                edge.setNext(edge.getNext().getNext());
                return;
            }
            edge = edge.getNext();
        }
    }
    
    // display graph wahana
    public void displayGraph() {
        if (head == null) {
            System.out.println("Tidak ada wahana tersedia.");
            return;
        }
        
        System.out.println("\n╔═══════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                         PETA WAHANA (GRAPH)                               ║");
        System.out.println("╠═══════════════════════════════════════════════════════════════════════════╣");
        
        NodeWahana current = head;
        while (current != null) {
            System.out.println("║ " + current.toString());
            
            EdgeWahana edge = current.getEdges();
            if (edge != null) {
                System.out.print("║   → Terhubung ke: ");
                while (edge != null) {
                    System.out.print(edge.getTujuan().getNamaWahana() + " (" + edge.getJarak() + "m)");
                    edge = edge.getNext();
                    if (edge != null) System.out.print(", ");
                }
                System.out.println();
            }
            System.out.println("║");
            current = current.getNext();
        }
        System.out.println("╚═══════════════════════════════════════════════════════════════════════════╝");
    }
    
    // display daftar wahana
    public void displayList() {
        if (head == null) {
            System.out.println("Tidak ada wahana tersedia.");
            return;
        }
        
        System.out.println("\n╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║                      DAFTAR WAHANA                             ║");
        System.out.println("╠════════════════════════════════════════════════════════════════╣");
        
        NodeWahana current = head;
        while (current != null) {
            System.out.println("║ " + current.toString());
            current = current.getNext();
        }
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
    }
    
    // cari jalur terpendek pake Dijkstra
    public void dijkstra(String idWahanaAsal, String idWahanaTujuan) {
        NodeWahana asal = searchWahana(idWahanaAsal);
        NodeWahana tujuan = searchWahana(idWahanaTujuan);
        
        if (asal == null || tujuan == null) {
            System.out.println("✗ Wahana tidak ditemukan!");
            return;
        }
        
        // inisialisasi
        Map<String, Integer> jarak = new HashMap<>();
        Map<String, String> prev = new HashMap<>();
        Set<String> visited = new HashSet<>();
        PriorityQueue<NodeJarak> pq = new PriorityQueue<>((a, b) -> a.jarak - b.jarak);
        
        // set semua jarak ke infinity
        NodeWahana temp = head;
        while (temp != null) {
            jarak.put(temp.getIdWahana(), Integer.MAX_VALUE);
            temp = temp.getNext();
        }
        
        // jarak ke node asal = 0
        jarak.put(idWahanaAsal, 0);
        pq.add(new NodeJarak(idWahanaAsal, 0));
        
        // algoritma Dijkstra
        while (!pq.isEmpty()) {
            NodeJarak current = pq.poll();
            String currentId = current.id;
            
            if (visited.contains(currentId)) continue;
            visited.add(currentId);
            
            if (currentId.equals(idWahanaTujuan)) break;
            
            NodeWahana currentWahana = searchWahana(currentId);
            EdgeWahana edge = currentWahana.getEdges();
            
            while (edge != null) {
                String neighborId = edge.getTujuan().getIdWahana();
                int newJarak = jarak.get(currentId) + edge.getJarak();
                
                if (newJarak < jarak.get(neighborId)) {
                    jarak.put(neighborId, newJarak);
                    prev.put(neighborId, currentId);
                    pq.add(new NodeJarak(neighborId, newJarak));
                }
                
                edge = edge.getNext();
            }
        }
        
        // tampilin hasil
        if (!prev.containsKey(idWahanaTujuan) && !idWahanaAsal.equals(idWahanaTujuan)) {
            System.out.println("✗ Tidak ada jalur dari " + asal.getNamaWahana() + 
                             " ke " + tujuan.getNamaWahana());
            return;
        }
        
        // reconstruct path
        List<String> path = new ArrayList<>();
        String current = idWahanaTujuan;
        while (current != null) {
            path.add(0, current);
            current = prev.get(current);
        }
        
        System.out.println("\n╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║              JALUR TERPENDEK (DIJKSTRA)                        ║");
        System.out.println("╠════════════════════════════════════════════════════════════════╣");
        System.out.println("║ Dari: " + asal.getNamaWahana());
        System.out.println("║ Ke: " + tujuan.getNamaWahana());
        System.out.println("║ Total Jarak: " + jarak.get(idWahanaTujuan) + " meter");
        System.out.println("║");
        System.out.print("║ Rute: ");
        for (int i = 0; i < path.size(); i++) {
            NodeWahana w = searchWahana(path.get(i));
            System.out.print(w.getNamaWahana());
            if (i < path.size() - 1) System.out.print(" → ");
        }
        System.out.println();
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
    }
    
    // helper class untuk priority queue di Dijkstra
    private class NodeJarak {
        String id;
        int jarak;
        
        NodeJarak(String id, int jarak) {
            this.id = id;
            this.jarak = jarak;
        }
    }
    
    public boolean isEmpty() {
        return head == null;
    }
    
    public int getSize() {
        return size;
    }
    
    public NodeWahana getHead() {
        return head;
    }
}