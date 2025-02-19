
//Import di Classi Java necessarie al funzionamento del programma
import java.util.Scanner;
import java.util.Random;

// Classe principale, con metodo main
class Esercizio {

    private static Random random = new Random();
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int a, n, i, j, e;

        System.out.println("Inserisci il numero di animali che arriveranno al lago: ");
        a = Integer.parseInt( input.nextLine() );

        // Alloco spazio al più per A animali, perchè nel caso peggiore il vettore non crescerà più di A elementi.
        int[] v = new int[a];

        // La dimensione effettiva del vettore inizialmente è 0.
        n = 0;
        i = 1;
        while (i <= a) {

            // Genero un animale di dimensione fra 30 e 100 cm e lo inserisco in coda al vettore. 
            // Considero solo misure multiple di 10 (30, 40, 50, ecc...)
            e = (3 + random.nextInt(7)) * 10;
            System.out.println("Arriva animale di " + e + " cm. ");
            n = inserisciInVettore(v, n, e, n);

            // Elimino tutti gli animali più piccoli arrivati prima
            j = 0;
            while (j < n) {
                if (v[j] < v[n - 1]) {
                    System.out.println(Integer.toString(v[j]) + " scappa via. ");
                    n = eliminaDaVettore(v, n, j);
                } else {
                    j = j + 1;
                }
            }

            // Visualizzo lo stato del lago, dopo l'arrivo dell'ultimo animale
            System.out.println("Lago: ");
            visualizzaVettore(v, n);
            i = i + 1;
        }
        n = rimuoviDuplicati(v, n);
        System.out.println("Animali con dimensioni diverse rimaste al lago:");
        visualizzaVettore(v, n);
    }
    
    public static int eliminaDaVettore(int[] v, int n, int ie) {
        int i, n2;

        n2 = n - 1;
        i = ie;
        while (i <= n - 2) {
            v[i] = v[i + 1];
            i = i + 1;
        }
        
        return n2;
    }
    
    public static int inserisciInVettore(int[] v, int n, int e, int ie) {
        int i, n2;

        n2 = n + 1;
        i = n2 - 1;
        while (i >= ie + 1) {
            v[i] = v[i - 1];
            i = i - 1;
        }
        v[ie] = e;
        
        return n2;
    }
    
    public static int rimuoviDuplicati(int[] v, int n) {
        int i, j;

        i = 0;
        while (i <= n - 2) {
            j = i + 1;
            while (j <= n - 1) {
                if (v[i] == v[j]) {
                    System.out.println("Elimino " + v[j] + " da cella " + j);
                    n = eliminaDaVettore(v, n, j);
                } else {
                    j = j + 1;
                }
            }
            i = i + 1;
        }
        
        return n;
    }
    
    public static void visualizzaVettore(int[] v, int n) {
        int i;

        i = 0;
        while (i < n) {
            System.out.println(Integer.toString(i) + ": " + v[i]);
            i = i + 1;
        }
    }
}
