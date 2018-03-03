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
        };
    }
}
