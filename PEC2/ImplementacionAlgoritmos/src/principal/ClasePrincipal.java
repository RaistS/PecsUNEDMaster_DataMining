package principal;

/**
{ }  []
E:\UNED\MasterInvestIA\Mineria de datos\Practicas\PEC2\nbMineriaP2\experimento1.arff
 */
import java.util.*;
import auxiliar.Probabilidades;

/**

@author Johni
 */
public class ClasePrincipal
{

    public static void main(String[] args)
    {
        int cuantasGenerar = 100;
        List<boolean[]> instanciasExaminar;

        GeneradorInstancias generador = new GeneradorInstancias();

        //public List<boolean[]> generarInstancias(int cuantas)
        //instancias = pral.generarInstancias(cuantas);//OK
        //instanciasExaminar = generador.generarInstanciasVariablesAleatorias(cuantasGenerar);
        ////////////
        instanciasExaminar = generador.generarInstanciasVariablesIndependientesTablaExhaustiva();
        ////////////
        System.out.println("Generadas ( " + instanciasExaminar.size() + " ) instancias de datos");
        GeneradorInstancias.mostrarInstancias(instanciasExaminar);

        //*Fichero ARFF para Weka
        boolean bOK = generador.crearFicheroArff("experimento1", instanciasExaminar);
        //probando relevancia fuerte, variables X1,X2,X3,X4,X5
        //public boolean bIsRelevanciaFuerte(List<boolean[]> instanciasParam, int iVarToCheck)
        for (int iVar = 1; iVar <= 5; iVar++)
        {
            System.out.print("X" + iVar);
            //
            boolean bIsStrongRelevant = Probabilidades.bIsRelevanciaFuerte(instanciasExaminar, iVar);
            if (bIsStrongRelevant)
            {
                System.out.println(" tiene Relevancia Fuerte? " + bIsStrongRelevant);
            }
            //
            if (!bIsStrongRelevant)
            {//sabemos que no es fuerete
                boolean bIsWeakRelevant = Probabilidades.bIsRelevanciaDebil(instanciasExaminar, iVar);
                System.out.println(" tiene Relevancia Debil? " + bIsWeakRelevant);
            }
        }

    }

}
