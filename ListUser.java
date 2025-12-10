public class ListUser {
    // cari user berdasarkan userId
    public NodeUser searchUser(String userId) {
        NodeUser current = head;
        while (current != null) {
            if (current.getUserId().equals(userId)) {
                return current;
            }
            current = current.getNext();
        }
        return null;
    }

    public NodeUser login(String userId, String password) {
        NodeUser user = searchUser(userId);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public void displayUserSorted() {
        if (head == null) {
            System.out.println("Tidak ada user terdaftar.");
            return;
        }
        
        // convert linked list ke array buat sorting
        NodeUser[] userArray = new NodeUser[size];
        NodeUser current = head;
        int idx = 0;
        while (current != null) {
            userArray[idx++] = current;
            current = current.getNext();
        }
        
        // bubble sort berdasarkan noTiket (descending)
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (userArray[j].getNoTiket() < userArray[j + 1].getNoTiket()) {
                    NodeUser temp = userArray[j];
                    userArray[j] = userArray[j + 1];
                    userArray[j + 1] = temp;
                }
            }
        }
        
        System.out.println("\n╔════════════════════════════════════════════════════════╗");
        System.out.println("║              DAFTAR USER (Sorted by Tiket)            ║");
        System.out.println("╠════════════════════════════════════════════════════════╣");
        for (NodeUser user : userArray) {
            System.out.println("║ " + user.toString());
        }
        System.out.println("╚════════════════════════════════════════════════════════╝");
    }
}
