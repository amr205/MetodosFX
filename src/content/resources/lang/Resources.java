package content.resources.lang;

import java.util.ListResourceBundle;

public class Resources extends ListResourceBundle{
    @Override
    protected Object[][] getContents() {
        return new Object[][] {
                //resultLabel, resultLabelText,toSolveLabel, toDrawLabel, solveFromLabel, drawFromLabel, methodLabel, equationLabel, inputLabel
                {"resultLabelText", "Result"},
                {"toSolveLabel", "to"},
                {"toDrawLabel", "to"},
                {"solveFromLabel", "Solve from"},
                {"drawFromLabel", "Draw from"},
                {"methodLabel", "Method: "},
                {"equationLabel", "Equation"},
                {"inputLabel", "Input"},
                //spanishMenuItem, englishMenuItem, closeMenuItem, newFileMenuItem, defaultThemeMenuItem, darkThemeMenuItem, lightThemeMenuItem;
                {"spanishMenuItem", "Spanish"},
                {"englishMenuItem", "English"},
                {"closeMenuItem", "Exit"},
                {"newFileMenuItem", "New"},
                {"defaultThemeMenuItem", "Default theme"},
                {"darkThemeMenuItem", "Dark theme"},
                {"lightThemeMenuItem", "Light theme"},
                //fileMenu, languageMenu, styleMenu;
                {"fileMenu", "File"},
                {"languageMenu", "Language"},
                {"styleMenu", "Style"},
                //calculateBtn, howBtn, drawBtn
                {"calculateBtn", "Calculate"},
                {"drawBtn", "Draw"},
                //text of atributes
                {"insertInput", "Please insert input"},
                //How To Messages
                {"howToTitle", "How to enter a equation"},
                {"howToHeader", "Use the follow operators and functions:\n Example: log(x)*sin(x)+(1/3)x"},
                {"howToDescription", "BUILT IN OPERATORS: \n" +
                        "    Addition: 2 + 2\n" +
                        "    Subtraction: 2 - 2\n" +
                        "    Multiplication: 2 * 2\n" +
                        "    Division: 2 / 2\n" +
                        "    Exponentation: 2 ^ 2\n" +
                        "    Unary Minus,Plus (Sign Operators): +2 - (-2)\n" +
                        "    Modulo: 2 % 2\n\n"+
                        "BUILT IN FUNCTIONS: \n" +
                        "    abs: absolute value\n" +
                        "    acos: arc cosine\n" +
                        "    asin: arc sine\n" +
                        "    atan: arc tangent\n" +
                        "    cbrt: cubic root\n" +
                        "    ceil: nearest upper integer\n" +
                        "    cos: cosine\n" +
                        "    cosh: hyperbolic cosine\n" +
                        "    exp: euler's number raised to the power (e^x)\n" +
                        "    floor: nearest lower integer\n" +
                        "    log: logarithmus naturalis (base e)\n" +
                        "    log10: logarithm (base 10)\n" +
                        "    log2: logarithm (base 2)\n" +
                        "    sin: sine\n" +
                        "    sinh: hyperbolic sine\n" +
                        "    sqrt: square root\n" +
                        "    tan: tangent\n" +
                        "    tanh: hyperbolic tangent\n" +
                        "    signum: signum function" },
                //Error Messages use Method
                {"wrongInputTitle", "Error wrong input"},
                {"correctFollowingHeader", "Please correct the following"},
                {"startingPointMethodDescription", "Starting point of the method needs to be lower than final point"},
                {"incorrectRangeMethodDescription", "Incorrect input in range of the method, check you are entering only numbers"},
                {"incorrectErrorMethodDescription", "Incorrect input in error of the method, check you are entering only numbers"},
                //Error Messages DrawView
                {"equationErrorHeader", "The equation is not valid"},
                {"equationErrorDescription", "Check you are using x as variable (not X)\nMore info in the info button"},
                {"startingPointDrawDescription", "Starting point of the graphic needs to be lower than final point"},
                {"incorrectRangeDrawDescription", "Incorrect input in range of the graphic, check you are entering only numbers"},
                //graphic title
                {"graphicTitle", "Graphic"},
                //optionalFieldsErrors
                {"equationDfxErrorHeader", "The equation f'(x) is not valid"},
                //name of methods
                {"bisectionMethod", "Bisection"},
                {"falseRuleMethod", "False position"},
                {"pointMethod", "Fixed point"},
                {"NewtonRaphsonMethod", "Newton Raphson"},
                {"SecanteMethod", "secante en english"},
                //description of methods
                {"descTitle", "Method description"},
                {"descBisectionHeader", "Bisection"},
                {"descBisectionDescription", "The bisection method in mathematics is a root-finding method that repeatedly bisects an interval and then selects a subinterval in which a root must lie for further processing. It is a very simple and robust method, but it is also relatively slow. Because of this, it is often used to obtain a rough approximation to a solution which is then used as a starting point for more rapidly converging methods.The method is also called the interval halving method, the binary search method,or the dichotomy method."},
                {"descFalseRuleHeader", "False rule"},
                {"descBisectionDescription", ""},
                //menu types of methods
                {"problemMenu","Type of methods"},
                {"problem1MenuItem", "Solution to an equation"},
                {"problem2MenuItem", "Solution to system of equations"},
                //parcial2.fxm labels and buttons
                {"selectMethodLabel","Select the method for solution"},
                //Gauss labels and buttons
                {"numberOfEquationsLabel","Enter the number of equations in the system: "}

        };
    }
}
