package sample.resources.lang;


import java.util.ListResourceBundle;

public class Resources_es extends ListResourceBundle{
    @Override
    protected Object[][] getContents() {
        return new Object[][] {
                //resultLabel, resultLabelText,toSolveLabel, toDrawLabel, solveFromLabel, drawFromLabel, methodLabel, equationLabel, inputLabel
                {"resultLabelText", "Resultado"},
                {"toSolveLabel", "a"},
                {"toDrawLabel", "a"},
                {"solveFromLabel", "Resolver de:"},
                {"drawFromLabel", "Dibujar de:"},
                {"methodLabel", "Método: "},
                {"equationLabel", "Ecuación"},
                {"inputLabel", "Entrada"},
                //spanishMenuItem, englishMenuItem, closeMenuItem, newFileMenuItem, defaultThemeMenuItem, darkThemeMenuItem, lightThemeMenuItem;
                {"spanishMenuItem", "Español"},
                {"englishMenuItem", "Ingles"},
                {"closeMenuItem", "Cerrar"},
                {"newFileMenuItem", "Nuevo"},
                {"defaultThemeMenuItem", "Tema por defecto"},
                {"darkThemeMenuItem", "Tema oscuro"},
                {"lightThemeMenuItem", "Tema claro"},
                //fileMenu, languageMenu, styleMenu;
                {"fileMenu", "Archivo"},
                {"languageMenu", "Lenguaje"},
                {"styleMenu", "Estilo"},
                //calculateBtn, howBtn, drawBtn
                {"calculateBtn", "Resolver"},
                {"drawBtn", "Dibujar"},
                //text of atributes
                {"insertInput", "Introduzca los datos porfavor"},
                //How To Messages
                {"howToTitle", "Como introducir ecuación"},
                {"howToHeader", "Usa los siguientes operadores y funciones:\n Ejemplo: log(x)*sin(x)+(1/3)x"},
                {"howToDescription", "OPERADORES INCLUIDOS: \n" +
                        "    Suma: 2 + 2\n" +
                        "    Resta: 2 - 2\n" +
                        "    Multiplicación: 2 * 2\n" +
                        "    División: 2 / 2\n" +
                        "    Exponente: 2 ^ 2\n" +
                        "    Operaciones de signos: +2 - (-2)\n" +
                        "    Modulo: 2 % 2\n\n"+
                        "FUNCIONES INCLUIDAS: \n" +
                        "    abs: valor absoluto\n" +
                        "    acos: arc coseno\n" +
                        "    asin: arc seno\n" +
                        "    atan: arc tangente\n" +
                        "    cbrt: raíz cubica\n" +
                        "    ceil: entero más cercano hacia arriba\n" +
                        "    cos: coseno\n" +
                        "    cosh: coseno hiperbolico\n" +
                        "    exp: (e^x)\n" +
                        "    floor: entero más cercano hacia abajo\n" +
                        "    log: logaritmo natural (base e)\n" +
                        "    log10: logaritno (base 10)\n" +
                        "    log2: logaritmo (base 2)\n" +
                        "    sin: seno\n" +
                        "    sinh: seno hiperbolico\n" +
                        "    sqrt: raíz cuadrada\n" +
                        "    tan: tangente\n" +
                        "    tanh: tangente hiperbolica\n" +
                        "    signum: función de signo" },
                //Error Messages use Method
                {"wrongInputTitle", "Error entrada incorrecta"},
                {"correctFollowingHeader", "Corregir lo siguiente"},
                {"startingPointMethodDescription", "El punto inicial del método debe ser menor que el punto final"},
                {"incorrectRangeMethodDescription", "Entrada incorrecta en el rango del método, checa que introduzcas solo números"},
                {"incorrectErrorMethodDescription", "Entrada incorrecta en el error del método, checa que introduzcas solo números"},
                //Error Messages DrawView
                {"equationErrorHeader", "La ecuación no es valida"},
                {"equationErrorDescription", "Verifica que la variable sea x (no X), más información en el botón de información"},
                {"startingPointDrawDescription", "El punto inicial de la gráfica debe ser menor que el punto final"},
                {"incorrectRangeDrawDescription", "Entrada incorrecta en el rango de la gráfica, checa que introduzcas solo números"},
                //graphic title
                {"graphicTitle", "Gráfica"},
                //optionalFieldsErrors
                {"equationDfxErrorHeader", "La ecuación f'(x) no es valida"},
                //name of methods
                {"bisectionMethod", "Bisección"},
                {"falseRuleMethod", "Regla falsa"},
                {"pointMethod", "Punto fijo"},
                {"NewtonRaphsonMethod", "Newton Raphson"},
                {"SecanteMethod", "Secante"},
                //description of methods
                {"descTitle", "Descripción del método"},
                {"descBisectionHeader", "Bisección"},
                {"descBisectionDescription", "El método de bisección en matemáticas es un método de búsqueda de raíces que divide un intervalo de forma repetida y luego selecciona un subintervalo en el que debe encontrarse una raíz para su posterior procesamiento. Es un método muy simple y robusto, pero también es relativamente lento. Debido a esto, a menudo se usa para obtener una aproximación aproximada a una solución que luego se usa como punto de partida para métodos convergentes más rápidos. El método también se conoce como el método de mitad de intervalo, el método de búsqueda binario, o el método de dicotomía."},
                {"descFalseRuleHeader", "Regla falsa"},
                {"descFalseRuleDescription", "Como en el método de bisección, se parte de un intervalo inicial [a0,b0] con f(a0) y f(b0) de signos opuestos, lo que garantiza que en su interior hay al menos una raíz (véase Teorema de Bolzano). El algoritmo va obteniendo sucesivamente en cada paso un intervalo más pequeño [ak, bk] que sigue incluyendo una raíz de la función f."},
                {"descFixedPointHeader", "Punto fijo"},
                {"descFixedPointDescription", "El método de punto fijo se basa en el despeje de la variable x, de forma que se obtiene una ecuación de la forma f(x)=x  en la cual, a medida que se evalúa recursivamente un valor inicial de x, se aproxima a una igualación, igualación cuyo valor indica la raíz (o su aproximación) de la ecuación original"},
                {"descNewtonRaphsonHeader", "Newton Raphson"},
                {"descNewtonRaphsonDescription", "El método de Newton-Raphson es un método abierto, en el sentido de que no está garantizada su convergencia global. La única manera de alcanzar la convergencia es seleccionar un valor inicial lo suficientemente cercano a la raíz buscada. Así, se ha de comenzar la iteración con un valor razonablemente cercano al cero (denominado punto de arranque o valor supuesto. Una vez que se ha hecho esto, el método linealiza la función por la recta tangente en ese valor supuesto. La abscisa en el origen de dicha recta será, según el método, una mejor aproximación de la raíz que el valor anterior. Se realizarán sucesivas iteraciones hasta que el método haya convergido lo suficiente."},
                {"descSecanteHeader","Secante"},
                {"descSecanteDescription","Es una variación del método de Newton-Raphson donde en vez de calcular la derivada de la función en el punto de estudio, teniendo en mente la definición de derivada, se aproxima la pendiente a la recta que une la función evaluada en el punto de estudio y en el punto de la iteración anterior. \n" +
                        "En otras palabras, el método de la secante es un algoritmo de la raíz de investigación que utiliza una serie de raíces de las líneas secantes para aproximar mejor la raíz de una función f. El método de la secante se puede considerar como una aproximación en diferencias finitas del método de Newton-Raphson. "},
                {"descGaussHeader","Gauss"},
                {"descGaussDescription","En matemáticas, la eliminación de Gauss, llamada así debido a Carl Friedrich Gauss, es un algoritmo del álgebra lineal para determinar las soluciones de un sistema de ecuaciones lineales, encontrar matrices e inversas. Un sistema de ecuaciones se resuelve por el método de Gauss cuando se obtienen sus soluciones mediante la reducción del sistema dado a otro equivalente en el que cada ecuación tiene una incógnita menos que la anterior. El método de Gauss transforma la matriz de coeficientes en una matriz triangular superior. Permitiendo realizar una sustitución hacia atrás de todas las incógnitas"},
                {"descGaussJordanHeader","Gauss Jordan"},
                {"descGaussJordanDescription","Al igual que el método de Gauss, es necesario transformar los coeficientes del sistema de ecuaciones en una matriz aumentada , para que, mediante el uso del algebra lineal, reducir la matriz paso a paso\n" +
                        "Lo que lo diferencia del método Gaussiano es que cuando es eliminada una incógnita, se eliminará de todas las ecuaciones restantes, o sea, las que anteceden a la ecuación principal así como de las que la siguen a continuación. De esta manera el paso de eliminación forma una matriz identidad en vez de una matriz triangular. No es necesario entonces utilizar la sustitución hacia atrás para conseguir la solución."},

                //menu types of methods
                {"problemMenu","Tipos de métodos"},
                {"problem1MenuItem", "Solución de ecuaciones"},
                {"problem2MenuItem", "Solución de sistema de ecuaciones"},
                //parcial2.fxm labels and buttons
                {"selectMethodLabel","¿Qué método desea usar?"},
                //gauss labels and buttons
                {"numberOfEquationsLabel","¿De cuantas ecuaciones es el sistema? "},
                {"numberOfEquationsError","El número de ecuaciones tiene que ser mayor a 1"},
                {"EquationsInputError","Checa la entrada para el número de ecuaciones en el sistema, introduce solo números"},
                {"ErrorInEquations","Al menos un coeficiente de las ecuaciones no es correcto, checa que introduces solo números"}


        };
    }
}