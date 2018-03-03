package content.resources.lang;


import java.util.ListResourceBundle;

public class Resources_es extends ListResourceBundle{
    @Override
    protected Object[][] getContents() {
        return new Object[][] {
                //resultLabel, resultLabelText,toSolveLabel, toDrawLabel, solveFromLabel, drawFromLabel, methodLabel, equationLabel, inputLabel
                {"resultLabelText", "Resultado"},
                {"toSolveLabel", "hasta"},
                {"toDrawLabel", "hasta"},
                {"solveFromLabel", "Resolver desde"},
                {"drawFromLabel", "Dibujar desde"},
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
        };
    }
}