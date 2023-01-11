package auxiliar;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Johni
 */

class Combination
{

    /* arr[] ---> Input Array
	data[] ---> Temporary array to store current combination
	start & end ---> Staring and Ending indexes in arr[]
	index ---> Current index in data[]
	r ---> Size of a combination to be printed */
    static void combinationUtil(int arr[], int n, int r, int index, 
            int data[], int i, List<List<Integer>> combinaciones)
    {
        // Current combination is ready to be printed, print it
        if (index == r)
        {
            List<Integer> nuevaLista = new ArrayList<>();
            
            for (int j = 0; j < r; j++)
            {
                //System.out.print(data[j] + " ");
                nuevaLista.add(data[j]);
            }
            //System.out.println("");
            //
            //guardar la combinacion generada
            combinaciones.add(nuevaLista);
            return;
        }

        // When no more elements are there to put in data[]
        if (i >= n)
        {
            return;
        }

        // current is included, put next at next location
        data[index] = arr[i];
        combinationUtil(arr, n, r, index + 1, data, i + 1, combinaciones);

        // current is excluded, replace it with next (Note that
        // i+1 is passed, but index is not changed)
        combinationUtil(arr, n, r, index, data, i + 1, combinaciones);
    }

    // The main function that prints all combinations of size r
    // in arr[] of size n. This function mainly uses combinationUtil()
    static List<List<Integer>> findCombinations(int arr[], int n, int r)
    {
        List<List<Integer>> combinaciones = new ArrayList<>();
        //
        // A temporary array to store all combination one by one
        int data[] = new int[r];

        // Print all combination using temporary array 'data[]'
        combinationUtil(arr, n, r, 0, data, 0,combinaciones);
        
        //todas
        return combinaciones;
    }
    
    /**    
    @param combinaciones
    @param n
    @param r 
    */
    public static void mostrarCombinaciones(List<List<Integer>> combinaciones, int n, int r)
    {        //
        int cuantas = combinaciones.size();
     
        //
        System.out.println("Combinaciones de " + n + " elementos, tomados en grupos de " + r + " (son " + cuantas + "): ");
        for (int i = 0; i < cuantas; i++)
        {
            List<Integer> grupo = combinaciones.get(i);
            System.out.println(grupo);
            
        }
    }

    /*Driver function to check for above function*/
    public static void main(String[] args)
    {
        int arr[] =
        {
            1, 2, 3, 4, 5
        };
        int r = 4;
        int n = arr.length;
        
        //
        List<List<Integer>> combinaciones = findCombinations(arr, n, r);
        //
        int cuantas = combinaciones.size();
        int calcular = countCombinations(n,r);
        //System.out.println("Combinaciones de " + n + " elementos, tomados en grupos de " + r + " (son " + cuantas + "): ");
        mostrarCombinaciones(combinaciones,  n, r);
        
    }
    
    
    /**
    C(n,r) = n!/((n-r)!*r!)
    @param n
    @param r
    @return 
    */
    public static int countCombinations(int n, int r)
    {
        int numerador = fact(n);
        int denominador = fact(n-r)*fact(r);
        
        int result = numerador/denominador;
        
        return result;
    }
    
    public static int fact(int n)
    {
      int result = 1;
      for(int i=1; i<=n; i++)
      {
          result *= i;//1*2*3*4
      }
      
      return result;
    }
    
    
    
    /**
     X1  X2  X3  X4  X5  Y
     0   1   0   1   1   1
    ej: subconjuntos de 2 elementos
    0 1
    0 0
    0 1
    1 1
    */
}
