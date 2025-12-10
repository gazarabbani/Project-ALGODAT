public class ListBarang {
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
}
