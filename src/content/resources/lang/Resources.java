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

        };
    }
}
