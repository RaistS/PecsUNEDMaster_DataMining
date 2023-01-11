package auxiliar;

/**
{ }  []
E:\UNED\MasterInvestIA\Mineria de datos\Practicas\PEC2\nbMineriaP2\experimento1.arff
 */
import java.util.*;
import principal.GeneradorInstancias;

/**

@author Johni
 */
public class Probabilidades
{

    /**
    Probabilidad condicionada
    p(Y=y cuando B=b) = P(Y interseccion B)/P(B)
    .......
    Generadas ( 100 ) instancias de datos
 X1  X2  X3  X4  X5  Y
 1   1   0   1   1   0
 1   1   0   1   1   0
 0   1   0   1   0   1

     */
    /**
    
    @param instanciasParam
    @param bY, la clase    
    @param instB, una fila (una instancia donde miraremosx X1..X5
    @param List<Integer> listVarsInstB variables validas en instB, ej: X2,X4
    @return 
     */
    private static double probabYcuandoB(List<boolean[]> instanciasParam,
            boolean bY, boolean[] instB,
            List<Integer> listVarsInstB)
    {
        double dbR;
        boolean bMatch;
        int countInstanciasParam = instanciasParam.size();

        //contar coincidencias de (Y=y cuando B=b)
        double matches = 0;
        for (int i = 0; i < countInstanciasParam; i++)
        {
            boolean bMatch1 = true;
            boolean bMatch2 = true;
            //
            boolean[] instP = instanciasParam.get(i);
            boolean bYinst = instP[instP.length - 1];//ultimo elemento es la clase Y
            if (bYinst != bY)//Y=y
            {
                bMatch1 = false;
            }

            //la 'fila' B sin la clase Y en las variables validas
            for (int k = 0; bMatch1 && k < listVarsInstB.size(); k++)
            {
                //en la lista vienen como 1,2,3,..5 y se pone desde 0
                Integer iVar = listVarsInstB.get(k) - 1;

                if (instP[iVar] != instB[iVar])
                {
                    bMatch2 = false;
                }
            }

            //si todo coincide contar
            if (bMatch1 && bMatch2)
            {
                matches++;
            }
        }
        double prob_YB = matches / countInstanciasParam;
        //
        //contar ocurrencias de B=b
        matches = 0;
        for (int i = 0; i < countInstanciasParam; i++)
        {
            bMatch = true;
            boolean[] instP = instanciasParam.get(i);

            //la 'fila' B sin la clase Y en las variables validas
            for (int k = 0; k < listVarsInstB.size(); k++)
            {
                //en la lista vienen como 1,2,3,..5 y se pone desde 0
                Integer iVar = listVarsInstB.get(k) - 1;

                if (instP[iVar] != instB[iVar])
                {
                    bMatch = false;
                }
            }

            //si todo coincide contar
            if (bMatch)
            {
                matches++;
            }
        }
        double prob_B = matches / countInstanciasParam;

        //
        //Resultado
        dbR = prob_YB / prob_B;

        return dbR;
    }

    /**    
    Una característica Xi es relevante fuerte si  existen un xi, un y, y un si 
    para los cuales:
    p(Xi = xi, Si = si) > 0, tal que  p(Y = y | Xi = xi, Si = si) != p(Y = y | Si = si)
    O lo que es lo mismo, existen dos instancias A y B que difieren únicamente
     en Xi y satisfacen YA != YB
    ......
    Version que deja todas las variables de la instancia antes de comprobar si es fuerte
    @param instanciasParam
    @param iVarToCheck
    @return 
     */
    public static boolean bIsRelevanciaFuerte(List<boolean[]> instanciasParam, int iVarToCheck)
    {

        List<Integer> listVarsInstB = new ArrayList<>();

        //rellenar con 1,..5 para las variables validas
        //la 'fila' B sin la clase Y en las variables validas
        for (int k = 1; k <= 5; k++)
        {
            //en la lista vienen como 1,2,3,..5
            listVarsInstB.add(k);
        }

        return bIsRelevanciaFuerte(instanciasParam, listVarsInstB, iVarToCheck);
    }

    /**    
    @param instanciasParam
    @param listVarsInstB las variables que se consideraran de la instancia
    @param iVarToCheck
    @return 
     */
    public static boolean bIsRelevanciaFuerte(List<boolean[]> instanciasParam, List<Integer> listVarsInstB, int iVarToCheck)
    {
        int countInstanciasParam = instanciasParam.size();

        for (int i = 0; i < countInstanciasParam; i++)
        {
            boolean[] inst_i = instanciasParam.get(i);//completa
            //
            boolean[] instXS = new boolean[inst_i.length - 1];//copia            
            //copiar
            System.arraycopy(inst_i, 0, instXS, 0, inst_i.length - 1);

            //
            //comparar probabilidades condicionadas de instancia completa VS instancia sin Xm
            //double probabYcuandoB(List<boolean[]> instanciasParam, boolean bY, boolean[] instB)
            //System.out.print("instXS: \n"); //debug
            //GeneradorInstancias.mostrarInstancia(instXS); //debug
            //
            double dbXStrue = probabYcuandoB(instanciasParam, true, instXS, listVarsInstB);//sin excluir nada            
            double dbXSfalse = probabYcuandoB(instanciasParam, false, instXS, listVarsInstB);//sin excluir nada
            //eliminando iFeature (desde cero)
            listVarsInstB.remove((Integer) iVarToCheck);//como objeto
            double dbStrue = probabYcuandoB(instanciasParam, true, instXS, listVarsInstB);//quitado iVar
            double dbSfalse = probabYcuandoB(instanciasParam, false, instXS, listVarsInstB);//quitado iVar
            //con y=1 ó true
            if (dbXStrue != dbStrue)
            {
                return true;
            }
            //con y=0 ó false
            if (dbXSfalse != dbSfalse)
            {
                return true;
            }

        }
        return false;

    }

    /**
    Para comprobar si una característica es relevante débil, una vez comprobado que no es relevante fuerte,
tendremos que comprobar que esta no es relevante fuerte en los diferentes subconjuntos de 𝑆. Para
ello será necesario utilizar el algoritmo 𝐴4 de la Tabla 2, que en un primer paso genera una lista de
combinaciones de variables posibles en un conjunto 𝑆 dado. El segundo paso de 𝐴4 es comprobar
si la variable a estudiar es relevante fuerte en algún subconjunto de 𝑆. Para ello generamos todas
las combinaciones posibles de características de un elemento, así como todas las de dos elementos,
tres elementos y cuatro elementos. De todas esas combinaciones obtenidas, se seleccionan aquellas en
las que no aparezca la variable a estudiar, obteniendo el subconjunto a estudiar. Una vez obtenido
el subconjunto de características, comprobamos si la variable a estudiar es relevante fuerte en dicho
subconjunto. Si se cumple la condición, esta variable resultaría ser de relevancia débil. 
    
    Si no se cumple
ninguna condición de relevancia débil ( 𝑝(𝑋𝑖 = 𝑥𝑖, 𝑆𝑖′ = 𝑠′ 𝑖) > 0 tal que 𝑝(𝑌 = 𝑦|𝑋𝑖 = 𝑥𝑖, 𝑆𝑖′ = 𝑠′ 𝑖) ̸=
𝑝(𝑌 = 𝑦|𝑆𝑖′ = 𝑠′ 𝑖)), la variable estudiada sería irrelevante.
3.2. Demostración de la relevancia de cada variab
    
    @param instanciasParam
    @param iVarToCheck
    @return     
     */
    public static boolean bIsRelevanciaDebil(List<boolean[]> instanciasParam, int iVarToCheck)
    {
        List<Integer> grupoFeaturesTodas = new ArrayList<>();
        
        for (int r = 1; r <= 4; r++)
        {
            //grupos
            int[] arrAux =
            {
                1, 2, 3, 4, 5
            };
            //quitamos 'iVarToCheck' del array
            int[] arrCombs = new int[arrAux.length - 1];
            int dest = 0;
            for (int ori = 0; ori < arrAux.length; ori++)
            {
                if (arrAux[ori] != iVarToCheck)
                {
                    arrCombs[dest] = arrAux[ori];
                    dest++;
                }
                
                //de paso relleanr una lista con "1","2","3","4","5"
                grupoFeaturesTodas.add(arrAux[ori]);
            }

            List<List<Integer>> combinaciones = Combination.findCombinations(arrCombs, arrCombs.length, r);
            //Combination.mostrarCombinaciones(combinaciones, arrCombs.length, r);//debug
            //
            for (int k = 0; k < combinaciones.size(); k++)
            {
                List<Integer> grupo = combinaciones.get(k);
                //De todas esas combinaciones obtenidas, se seleccionan aquellas en
                //las que no aparezca la variable a estudiar,
                for (int m = 0; m < grupo.size(); m++)
                {
                    Integer feature = grupo.get(m);
                    //hacer una copia que tendra la caracteristica borrada
                    //afecta como borrar la caracteristica (como X3) en TODAS las intancias                   
                    List<Integer> grupoTest = new ArrayList<>();
                    grupoTest.addAll(grupoFeaturesTodas);
                    //
                    //quitar una a una cada caracteristica DE LAS INSTANCIAS dejando grupoTest solo con las validas
                    grupoTest.remove((Integer) feature);//desde 1
                    boolean bFuerte = bIsRelevanciaFuerte(instanciasParam, grupoTest, iVarToCheck);
                    if (bFuerte)
                    {
                        System.out.println(" Debil en grupo: " + grupoTest);//debug
                        return true;
                    }
                }
            }

        }

        //probados todos los for
        return false;
    }

}
