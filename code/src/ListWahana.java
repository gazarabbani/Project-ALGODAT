
public class ListWahana {
    private NodeWahana head;
    private int size;
    private int idCounter;
    
    public ListWahana() {
        this.head = null;
        this.size = 0;
        this.idCounter = 1;
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
        System.out.println("  Wahana '" + namaWahana + "' berhasil ditambahkan dengan ID: " + idWahana);
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
            System.out.println("  Wahana berhasil diupdate!");
            return true;
        }
        System.out.println("  Wahana tidak ditemukan!");
        return false;
    }
    
    // hapus wahana dari graph
    public boolean deleteWahana(String idWahana) {
        if (head == null) {
            System.out.println("  Graph wahana kosong!");
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
            System.out.println("  Wahana berhasil dihapus!");
            return true;
        }
        
        current = head;
        while (current.getNext() != null) {
            if (current.getNext().getIdWahana().equals(idWahana)) {
                current.setNext(current.getNext().getNext());
                size--;
                System.out.println("  Wahana berhasil dihapus!");
                return true;
            }
            current = current.getNext();
        }
        
        System.out.println("  Wahana tidak ditemukan!");
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
            System.out.println("  Wahana tidak ditemukan!");
            return;
        }
        
        DijkstraList dataList = new DijkstraList();
        
        NodeWahana temp = head;
        while (temp != null) {
            dataList.add(new DijkstraData(temp.getIdWahana()));
            temp = temp.getNext();
        }
        
        DijkstraData asalData = dataList.get(idWahanaAsal);
        if (asalData != null) {
            asalData.jarak = 0;
        }

        while (!dataList.allVisited()) {
            DijkstraData currentData = dataList.getMinUnvisited();
            
            if (currentData == null || currentData.jarak == Integer.MAX_VALUE) {
                break;
            }
            
            String currentId = currentData.id;
            currentData.visited = true;
            
            if (currentId.equals(idWahanaTujuan)) {
                break;
            }
            
            NodeWahana currentWahana = searchWahana(currentId);
            EdgeWahana edge = currentWahana.getEdges();
            
            while (edge != null) {
                String neighborId = edge.getTujuan().getIdWahana();
                DijkstraData neighborData = dataList.get(neighborId);
                
                if (neighborData != null && !neighborData.visited) {
                    // Cek jarak
                    int newJarak = currentData.jarak + edge.getJarak();
                    
                    if (newJarak < neighborData.jarak) {
                        neighborData.jarak = newJarak;
                        neighborData.prevId = currentId;
                    }
                }
                
                edge = edge.getNext();
            }
        }
        
        // tampilin hasil
        DijkstraData tujuanData = dataList.get(idWahanaTujuan);

        if (tujuanData == null || tujuanData.prevId == null && !idWahanaAsal.equals(idWahanaTujuan)) {
            System.out.println("  Tidak ada jalur dari " + asal.getNamaWahana() + 
                               " ke " + tujuan.getNamaWahana());
            return;
        }
        System.out.println("\n╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║                     JALUR TERPENDEK (DIJKSTRA)                   ║");
        System.out.println("╠════════════════════════════════════════════════════════════════╣");
        System.out.println("║ Dari: " + asal.getNamaWahana());
        System.out.println("║ Ke: " + tujuan.getNamaWahana());
        System.out.println("║ Total Jarak: " + tujuanData.jarak + " meter");
        System.out.println("║");

        String pathString = "";
        String currentId = idWahanaTujuan;
        
        while (currentId != null) {
            NodeWahana w = searchWahana(currentId);
            
            if (pathString.length() > 0) {
                pathString = " → " + pathString;
            }
            pathString = w.getNamaWahana() + pathString;
            
            DijkstraData data = dataList.get(currentId);
            currentId = data.prevId;
        }
        System.out.println("║ Rute: " + pathString);
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
    }
}