package content.resources.lang;


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
                //trabajo extra cu
                {"descTitle", "Descripción del método"},
                {"descBisectionHeader", "Bisección"},
                {"descBisectionDescription", "El método de bisección en matemáticas es un método de búsqueda de raíces que divide un intervalo de forma repetida y luego selecciona un subintervalo en el que debe encontrarse una raíz para su posterior procesamiento. Es un método muy simple y robusto, pero también es relativamente lento. Debido a esto, a menudo se usa para obtener una aproximación aproximada a una solución que luego se usa como punto de partida para métodos convergentes más rápidos. El método también se conoce como el método de mitad de intervalo, el método de búsqueda binario, o el método de dicotomía."},
                //menu types of methods
                {"problemMenu","Tipos de métodos"},
                {"problem1MenuItem", "Solución de ecuaciones"},
                {"problem2MenuItem", "Solución de sistema de ecuaciones"},
                //parcial2.fxm labels and buttons
                {"selectMethodLabel","¿Qué método desea usar?"},
                //gauss labels and buttons
                {"numberOfEquationsLabel","¿De cuantas ecuaciones es el sistema? "}


        };
    }
}