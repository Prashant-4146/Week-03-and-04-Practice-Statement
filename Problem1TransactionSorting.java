import java.util.*;

// -------------------- PROBLEM 1 --------------------
class Transaction {
    String id;
    double fee;
    String timestamp;

    Transaction(String id, double fee, String timestamp) {
        this.id = id;
        this.fee = fee;
        this.timestamp = timestamp;
    }

    public String toString() {
        return id + ":" + fee + "@" + timestamp;
    }
}

// -------------------- PROBLEM 2 --------------------
class Client {
    String name;
    int riskScore;
    int balance;

    Client(String n, int r, int b) {
        name = n; riskScore = r; balance = b;
    }

    public String toString() {
        return name + ":" + riskScore;
    }
}

// -------------------- PROBLEM 4 --------------------
class Asset {
    String name;
    int returnRate;
    int volatility;

    Asset(String n, int r, int v) {
        name = n; returnRate = r; volatility = v;
    }

    public String toString() {
        return name + ":" + returnRate + "%";
    }
}

// -------------------- MAIN CLASS --------------------
public class DSAUseCases {

    // ========== PROBLEM 1 ==========
    static void bubbleSortTransactions(List<Transaction> list) {
        for (int i = 0; i < list.size()-1; i++) {
            boolean swapped = false;
            for (int j = 0; j < list.size()-i-1; j++) {
                if (list.get(j).fee > list.get(j+1).fee) {
                    Collections.swap(list, j, j+1);
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    static void insertionSortTransactions(List<Transaction> list) {
        for (int i = 1; i < list.size(); i++) {
            Transaction key = list.get(i);
            int j = i-1;

            while (j >= 0 && 
                   (list.get(j).fee > key.fee ||
                   (list.get(j).fee == key.fee &&
                    list.get(j).timestamp.compareTo(key.timestamp) > 0))) {
                list.set(j+1, list.get(j));
                j--;
            }
            list.set(j+1, key);
        }
    }

    static void findOutliers(List<Transaction> list) {
        System.out.print("Outliers (>50): ");
        boolean found = false;
        for (Transaction t : list) {
            if (t.fee > 50) {
                System.out.print(t + " ");
                found = true;
            }
        }
        if (!found) System.out.print("None");
        System.out.println();
    }

    // ========== PROBLEM 2 ==========
    static void bubbleSortClients(Client[] arr) {
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = 0; j < arr.length-i-1; j++) {
                if (arr[j].riskScore > arr[j+1].riskScore) {
                    Client temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }

    static void insertionSortClients(Client[] arr) {
        for (int i = 1; i < arr.length; i++) {
            Client key = arr[i];
            int j = i-1;

            while (j >= 0 && arr[j].riskScore < key.riskScore) {
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = key;
        }
    }

    // ========== PROBLEM 3 ==========
    static void mergeSort(int[] arr, int l, int r) {
        if (l < r) {
            int m = (l+r)/2;
            mergeSort(arr, l, m);
            mergeSort(arr, m+1, r);
            merge(arr, l, m, r);
        }
    }

    static void merge(int[] arr, int l, int m, int r) {
        int[] temp = new int[r-l+1];
        int i=l, j=m+1, k=0;

        while (i<=m && j<=r) {
            if (arr[i] <= arr[j]) temp[k++] = arr[i++];
            else temp[k++] = arr[j++];
        }

        while (i<=m) temp[k++] = arr[i++];
        while (j<=r) temp[k++] = arr[j++];

        System.arraycopy(temp, 0, arr, l, temp.length);
    }

    static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi-1);
            quickSort(arr, pi+1, high);
        }
    }

    static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low-1;

        for (int j = low; j < high; j++) {
            if (arr[j] > pivot) { // DESC
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;

        return i+1;
    }

    // ========== PROBLEM 5 ==========
    static int linearSearch(String[] arr, String target) {
        for (int i=0;i<arr.length;i++) {
            if (arr[i].equals(target)) return i;
        }
        return -1;
    }

    static int binarySearch(String[] arr, String target) {
        int low=0, high=arr.length-1;

        while (low<=high) {
            int mid=(low+high)/2;
            if (arr[mid].equals(target)) return mid;
            else if (arr[mid].compareTo(target)<0) low=mid+1;
            else high=mid-1;
        }
        return -1;
    }

    // ========== PROBLEM 6 ==========
    static void findFloorCeil(int[] arr, int target) {
        int low=0, high=arr.length-1;
        Integer floor=null, ceil=null;

        while (low<=high) {
            int mid=(low+high)/2;

            if (arr[mid]==target) {
                floor=ceil=arr[mid];
                break;
            }
            if (arr[mid] < target) {
                floor=arr[mid];
                low=mid+1;
            } else {
                ceil=arr[mid];
                high=mid-1;
            }
        }

        System.out.println("Floor: " + floor + ", Ceil: " + ceil);
    }

    // -------------------- MAIN --------------------
    public static void main(String[] args) {

        // Problem 1
        System.out.println("=== Problem 1 ===");
        List<Transaction> tx = new ArrayList<>();
        tx.add(new Transaction("id1",10.5,"10:00"));
        tx.add(new Transaction("id2",25.0,"09:30"));
        tx.add(new Transaction("id3",5.0,"10:15"));

        List<Transaction> b1 = new ArrayList<>(tx);
        bubbleSortTransactions(b1);
        System.out.println("Bubble: " + b1);

        List<Transaction> b2 = new ArrayList<>(tx);
        insertionSortTransactions(b2);
        System.out.println("Insertion: " + b2);

        findOutliers(tx);

        // Problem 2
        System.out.println("\n=== Problem 2 ===");
        Client[] clients = {
            new Client("C",80,1000),
            new Client("A",20,2000),
            new Client("B",50,1500)
        };

        bubbleSortClients(clients);
        System.out.println("Bubble ASC: " + Arrays.toString(clients));

        insertionSortClients(clients);
        System.out.println("Insertion DESC: " + Arrays.toString(clients));

        // Problem 3
        System.out.println("\n=== Problem 3 ===");
        int[] trades = {500,100,300};
        mergeSort(trades,0,trades.length-1);
        System.out.println("Merge: " + Arrays.toString(trades));

        quickSort(trades,0,trades.length-1);
        System.out.println("Quick DESC: " + Arrays.toString(trades));

        // Problem 5
        System.out.println("\n=== Problem 5 ===");
        String[] logs = {"accA","accB","accB","accC"};
        Arrays.sort(logs);

        System.out.println("Linear index: " + linearSearch(logs,"accB"));
        System.out.println("Binary index: " + binarySearch(logs,"accB"));

        // Problem 6
        System.out.println("\n=== Problem 6 ===");
        int[] risks = {10,25,50,100};
        findFloorCeil(risks,30);
    }
}
