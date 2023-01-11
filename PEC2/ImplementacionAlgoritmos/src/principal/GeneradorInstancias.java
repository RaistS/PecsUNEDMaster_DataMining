package principal;

/**
E:\UNED\MasterInvestIA\Mineria de datos\Practicas\PEC2\nbMineriaP2\experimento1.arff
 */
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Johni
 */
public class GeneradorInstancias
{

    public String[] strAtributos =
    {
        "X1", "X2", "X3", "X4", "X5", "Y"
    };

    public boolean crearFicheroArff(String strNombreRelacion, List<boolean[]> instanciasParam)
    {
        String rutaFicheroTxt = strNombreRelacion + ".arff";//<--extension en minusculas!
        try
        {
            //crear fichero texto
            PrintWriter pw = new PrintWriter(rutaFicheroTxt);

            //escrbir cabecera            
            //% 1. Title: Iris Plants Database
            pw.println("% " + strNombreRelacion);
            pw.println("% " + "Number of attributes including class: " + (strAtributos.length));
            pw.println("% " + "Number of classes: " + 2);
            pw.println("% " + "Number of instances: " + instanciasParam.size());
            pw.println("\n");
            //
            //@RELATION iris
            pw.println("@RELATION " + strNombreRelacion + "\n\n");

            //@ATTRIBUTE sepallength	tienen que ser NOMINALES (valores entre llaves)            
            for (int t = 0; t < strAtributos.length - 1; t++)
            {
                ////@ATTRIBUTE 	X1	{0,1}
                pw.println("@ATTRIBUTE " + "\t" + strAtributos[t] + "\t" + "{0,1}");

            }

            pw.println("@ATTRIBUTE classY  {" + "0" + "," + "1" + "}" + "\n");

            //@DATA
            pw.println("@DATA");
            //5.1,3.5,1.4,0.2,Iris-setosa
            for (int i = 0; i < instanciasParam.size(); i++)
            {
                boolean[] instan = instanciasParam.get(i);

                //mostrar ceros y unos
                String strX1 = instan[0] == true ? "1" : "0";
                String strX2 = instan[1] == true ? "1" : "0";
                String strX3 = instan[2] == true ? "1" : "0";
                String strX4 = instan[3] == true ? "1" : "0";
                String strX5 = instan[4] == true ? "1" : "0";
                String strY = instan[5] == true ? "1" : "0";

                // X1  X2  X3  X4  X5  Y
                //  0   0 1 1 0 1            
                pw.println(strX1 + "," + strX2 + "," + strX3 + "," + strX4 + "," + strX5 + "," + strY);

            }

            pw.println("%\n%\n");

            //*Cerrar
            pw.flush();
            pw.close();

            System.out.println("Se ha creado el fichero \"" + rutaFicheroTxt + "\" para escritura, con " + instanciasParam.size() + " instancias");

        } catch (FileNotFoundException ex)
        {
            System.out.println("Fallo creando fichero \"" + rutaFicheroTxt + "\" para escritura");
            return false;
        }

        return true;

    }

    /*
Crea el fichero y pone la cabecera
F:\0.CppideVisualab\MyProjects\2021-05-09_stdThread\Console64bits\Console64bits\iris.ARFF
     
        //Son 26 ternas (26 angulos)
        //int numTernas = sizeof(ternasVectoresPivot) / (3 * sizeof(int));
        int numClases = this
        ->vecClases.size();
        //*Cabecera
        //% 1. Title: Iris Plants Database
        fout << "% " << strDescripcionRelacion << endl;
        fout << "% " << "Number of attributes: " << (numTernasAngulos + 1) << endl;
        fout << "% " << "Number of classes: " << numClases << endl;
        fout << endl << endl;
        //
        //@RELATION iris
        fout << "@RELATION " << strNombreRelacion << endl << endl;
        fout << endl;
        //@ATTRIBUTE sepallength	REAL ...
        //Son 26 ternas (26 angulos)    
        for (int t = 0; t < numTernasAngulos; t++)
        {
            //@ATTRIBUTE 2_3_4	REAL (NUMERIC) ...
            fout << "@ATTRIBUTE "
                    << ternasVectoresPivot[t][0] << "_"
                    << ternasVectoresPivot[t][1] << "_"
                    << ternasVectoresPivot[t][2] << "\t" << "REAL" << endl;
        }
        fout << "@ATTRIBUTE " << "FrontSideBack" << "\t" << "{ Frente, Perfil, Espaldas }" << endl;//NOMINAL
        //
        //@ATTRIBUTE class 	{Iris-setosa,Iris-versicolor,Iris-virginica}
        fout << "@ATTRIBUTE class  {";
        for (int c = 0; c < numClases; c++)
        {
            fout << this
            ->vecClases.at(c);// strClases[c];
            if (c < numClases - 1)
            {
                fout << ",";
            }
        }
        fout << "}" << endl;
        fout << endl;
        //@DATA
        fout << "@DATA" << endl;
        //5.1,3.5,1.4,0.2,Iris-setosa
        //4.9,3.0,1.4,0.2,Iris-setosa

        return true;
    }
     */
    public static void mostrarArrayBooleanAs0y1(boolean[] instan)
    {
        String str = " ";
        for (int i = 0; i < instan.length; i++)
        {
            String strX = instan[i] == true ? "1" : "0";
            str += strX + "   ";
            
        }
        //mostrar ceros y unos        
        // X1  X2  X3  X4  X5  Y
        //  0   0 1 1 0 1            
        System.out.println(str);

    }

    /**
    { }  []
    
     */
    public static void mostrarInstancias(List<boolean[]> instanciasParam)
    {
        System.out.println(" X1  X2  X3  X4  X5  Y");
        for (int i = 0; i < instanciasParam.size(); i++)
        {
            boolean[] instan = instanciasParam.get(i);

            //mostrar ceros y unos
            mostrarArrayBooleanAs0y1(instan);
            //
            

        }

    }

    /*    
El estudiante generar´a un conjunto de datos artificial compuesto por 100
instancias caracterizadas cada una de ellas por cinco variables predictoras: 
    una variable relevante en sentido fuerte, 
    tres variables relevantes en sentido debil
    y una variable totalmente irrelevante. 
Aunque se pueden crear conjuntos como
el descrito a partir de muchas definiciones de clase, sugerimos: 
i) extender el ejemplo XOR de [1] a tres dimensiones (es decir, definir la variable clase como
la funci´on XOR de 3 variables), 
ii) definir la cuarta variable como una funcion de un subconjunto de las tres primeras 
    (por ejemplo, X4=X2 XOR X3) y 
iii) la quinta como una variable booleana aleatoria.
A continuacion, aplicaria 5 tecnicas de las mencionadas en [2] al conjunto de
datos asi generado. 
Deber´a seleccionar un m´ınimo de tres t´ecnicas de filtrado, el
an´alisis de componentes principales y la t´ecnica de envoltura con un clasificador
de los estudiados en el tema 2.
Como sugerencia (y sin que esto pretenda ser una lista exhaustiva ni actualizada) 
podemos mencionar los paquetes caret y FSelector de R. En python,
ten´eis algunas funciones implementadas en sklearn.feature selection.
     */
    public List<boolean[]> generarInstanciasVariablesAleatorias(int cuantasParam)
    {
        List<boolean[]> listaRet = new ArrayList();
        boolean bX1, bX2, bX3;
        boolean bX4, bX5, bY;

        Random random = new Random();

        //X1  X2  X3  X4  X5  Y
        //Las variables independientes son X1,X2,X3,X5
        for (int i = 0; i < cuantasParam; i++)
        {
            bX1 = random.nextBoolean();
            bX2 = random.nextBoolean();
            bX3 = random.nextBoolean();
            bX5 = random.nextBoolean();

            //X4 =X2 XOR X3
            bX4 = bX2 ^ bX3; //1

            //clase bY es la funcion XOR(a,b,c) de tres variables
            bY = bX1 ^ bX2 ^ bX3;//bx4;//1
            //bY = bX1 ^ (bX2 ^ bX3);//bx4;//1
            //bY = bX1 ^ bX4;

            //guardar
            boolean[] bInstance =
            {
                bX1, bX2, bX3, bX4, bX5, bY
            };

            listaRet.add(bInstance);

        }

        return listaRet;
    }

    /**
    Tabla de 16 filas con todas las combinaciones
    @return 
     */
    public List<boolean[]> generarInstanciasVariablesIndependientesTablaExhaustiva()
    {
        List<boolean[]> listaRet = new ArrayList();
        boolean bX1 = false, bX2 = true, bX3 = true, bX4 = true, bX5 = true, bY = true;

        //Las variables independientes son X1,X2,X3,X5
        for (int i = 0; i < 16; i++)
        {
            //para la siguiente iteracion
            //X1,X2,X3 son aleatorias
            bX1 = (i % 4 == 0) && (i > 0) ? !bX1 : bX1;//00001111
            bX2 = (i % 2 == 0) ? !bX2 : bX2;//00110011
            bX3 = !bX3;//01010101

            //X1  X2  X3  X4  X5  Y
            //0   1   0   1   0   0
            //X4 =X2 XOR X3
            bX4 = bX2 ^ bX3; //1

            bX5 = (i % 8 == 0) ? !bX5 : bX5;//0000000011111111

            //clase bY es la funcion XOR(a,b,c) de tres variables
            bY = bX1 ^ bX2 ^ bX3;//bx4;//1
            //bY = bX1 ^ (bX2 ^ bX3);//bx4;//1
            //bY = bX1 ^ bX4;

            //guardar
            boolean[] bInstance =
            {
                bX1, bX2, bX3, bX4, bX5, bY
            };

            listaRet.add(bInstance);

        }

        return listaRet;
    }

    /**
    Usando las que ya estan en instanciasParam, se generan repeticiones escogiendo
    aleatoriamente datos de la misma y poniendolo todo en una nueva lista.
    @param cuantasMas (total de datos = instanciasParam.size() + cuantasMas),
    ej: 16 + 84 = 100
    @param instanciasParam
    @return 
     */
    public List<boolean[]> generarInstanciasAdicionales(int cuantasMas, List<boolean[]> instanciasParam)
    {
        List<boolean[]> listaRet = new ArrayList();
        Random random = new Random();

        int cuantasTabla = instanciasParam.size();

        //de entrada las que se proporicionan
        listaRet.addAll(instanciasParam);

        //Las variables independientes son X1,X2,X3,X5
        for (int i = 0; i < cuantasMas; i++)
        {

            int index = random.nextInt(cuantasTabla);
            boolean[] dato = instanciasParam.get(index);
            listaRet.add(dato);
        }

        return listaRet;
    }

}
