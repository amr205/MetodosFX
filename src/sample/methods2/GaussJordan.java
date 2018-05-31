package sample.methods2;

import javafx.scene.control.Alert;
import javafx.scene.layout.Region;
import sample.Utilities.ObservableResourceFactory;

//se encarga de realizar el método y mostrar la información en pantalla

public class GaussJordan extends Gauss{
    public GaussJordan(ObservableResourceFactory RESOURCE_FACTORY) {
        super(RESOURCE_FACTORY);
    }

    private void muestramatriz(double matriz[][], int var) {
        double[][] A = new double[matriz.length][matriz.length];
        double[] B = new double[matriz.length];

        for(int y= 0;y<matriz.length;y++){
            for (int x=0;x<matriz.length;x++){
                A[y][x]=matriz[y][x];
            }
            B[y]=matriz[y][matriz.length];
        }

        outputController.addIteration(A,B);
    }

    private void pivot(double matriz[][], int piv, int var) {
        double temp = 0;
        temp = matriz[piv][piv];
        for (int y = 0; y < (var + 1); y++) {

            matriz[piv][y] = matriz[piv][y] / temp;
        }
    }

    private void makeCeros(double matriz[][], int piv, int var) {
        for (int x = 0; x < var; x++) {
            if (x != piv) {
                double c = matriz[x][piv];
                for (int z = 0; z < (var + 1); z++) {
                    matriz[x][z] = ((-1 * c) * matriz[piv][z]) + matriz[x][z];

                }
                System.out.println("");
                muestramatriz(matriz, var);
            }

        }
    }




    @Override
    public void solve(double[][] A,double[] B){
        outputController.clear();

        int  piv = 0;
        double matriz[][];
        int var = A.length;


        matriz = new double[var][var + 1];

        for (int i = 0; i < var; i++){
            matriz[i][var]=B[i];

            for (int j = 0; j < var; j++)
                matriz[i][j] = A[i][j];
        }

        //for(int i=0;i<var;i++)

        for (int a = 0; a < var; a++) {
            pivot(matriz, piv, var);
            //meter renglon a pivote
            muestramatriz(matriz, var);
            //hacer ceros
            makeCeros(matriz, piv, var);

            piv++;
        }
        inputController.startResult();
        for (int x = 0; x < var; x++) {
            System.out.println("La variable X" + (x + 1) + " es: " + matriz[x][var]);
            inputController.addResultLabel(matriz[x][var],(x+1));
        }

    }

    @Override
    public String toString(){
        return "Gauss Jordan";
    }

    @Override
    public void showInfo() {
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setTitle(RESOURCE_FACTORY.getResources().getString("descTitle"));
        info.setHeaderText(RESOURCE_FACTORY.getResources().getString("descGaussJordanHeader"));
        info.setContentText(RESOURCE_FACTORY.getResources().getString("descGaussJordanDescription"));
        info.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        info.getDialogPane().setPrefWidth(650);
        info.show();
    }


}
