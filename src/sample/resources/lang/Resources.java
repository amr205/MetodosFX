package sample.resources.lang;

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
                {"SecanteMethod", "Secant"},
                //description of methods
                {"descTitle", "Method description"},
                {"descBisectionHeader", "Bisection"},
                {"descBisectionDescription", "The bisection method in mathematics is a root-finding method that repeatedly bisects an interval and then selects a subinterval in which a root must lie for further processing. It is a very simple and robust method, but it is also relatively slow. Because of this, it is often used to obtain a rough approximation to a solution which is then used as a starting point for more rapidly converging methods.The method is also called the interval halving method, the binary search method,or the dichotomy method."},
                {"descFalseRuleHeader", "False rule"},
                {"descFalseRuleDescription", "As in the bisection method, we start from an initial interval [a0, b0] with f (a0) and f (b0) of opposite signs, which guarantees that there is at least one root inside (see Bolzano's Theorem). The algorithm obtains successively in each step a smaller interval [ak, bk] that continues including a root of the function f."},
                {"descFixedPointHeader", "Fixed point"},
                {"descFixedPointDescription", "The fixed point method is based on the clearing of the variable x, so that an equation of the form f (x) = x is obtained in which, as an initial value of x is recursively evaluated, it approaches an equalization, equalization whose value indicates the root (or its approximation) of the original equation"},
                {"descNewtonRaphsonHeader", "Newton Raphson"},
                {"descNewtonRaphsonDescription", "The Newton-Raphson method is an open method, in the sense that its global convergence is not guaranteed. The only way to achieve convergence is to select an initial value close enough to the root sought. Thus, the iteration has to start with a value reasonably close to zero (called the starting point or assumed value.) Once this has been done, the method linearizes the function by the tangent line in that assumed value. The origin of this line will be, according to the method, a better approximation of the root than the previous value, successive iterations will be carried out until the method has converged sufficiently."},
                {"descSecanteHeader","Secant"},
                {"descSecanteDescription","It is a variation of the Newton-Raphson method where instead of calculating the derivative of the function at the point of study, keeping in mind the definition of the derivative, the slope is approximated by the line that unites the function evaluated at the study point. and at the point of the previous iteration. In other words, the secant method is a root-research algorithm that uses a series of roots from the secant lines to better approximate the root of a function f. The secant method can be considered as an approximation in finite differences of the Newton-Raphson method."},
                {"descGaussHeader","Gauss"},
                {"descGaussDescription","In mathematics, the elimination of Gauss, named after Carl Friedrich Gauss, is an algorithm of linear algebra to determine the solutions of a system of linear equations, finding matrices and inverses. A system of equations is solved by the Gaussian method when its solutions are obtained by reducing the given system to another equivalent in which each equation has one less unknown than the previous one. The Gaussian method transforms the coefficient matrix into a superior triangular matrix. Allowing a backward substitution of all the unknowns"},
                {"descGaussJordanHeader","Gauss Jordan"},
                {"descGaussJordanDescription","Like the Gaussian method, it is necessary to transform the coefficients of the system of equations into an augmented matrix, so that, by using linear algebra, reduce the matrix step by step What differentiates it from the Gaussian method is that when an incognito is eliminated, it will be eliminated from all the remaining equations, that is, those that precede the main equation as well as those that follow it. In this way the elimination step forms an identity matrix instead of a triangular matrix. It is not necessary then to use the substitution backwards to get the solution."},
                //menu types of methods
                {"problemMenu","Type of methods"},
                {"problem1MenuItem", "Solution to an equation"},
                {"problem2MenuItem", "Solution to system of equations"},
                //parcial2.fxm labels and buttons
                {"selectMethodLabel","Select the method for solution"},
                //Gauss labels and buttons
                {"numberOfEquationsLabel","Enter the number of equations in the system: "},
                {"numberOfEquationsError","The number of equations has to be bigger than 1"},
                {"EquationsInputError","Check your input for the number of equations in the system, introduce only numbers"},
                {"ErrorInEquations","At least one coeficient of the equations is not valid, check you are introducing only numbers"}

        };
    }
}
