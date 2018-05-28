package sample.methods2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import sample.Main;
import sample.Utilities.ObservableResourceFactory;
import sample.controller.methods.RegresionLinealInputController;
import sample.controller.methods.RegresionLinealOutputController;
import sample.controller.methods.RegresionMultivarInputController;
import sample.controller.methods.RegresionMultivarOutputController;
import sample.methods.model.RegresionLinealModel1;
import sample.methods.model.RegresionLinealModel2;
import sample.methods.model.RegresionMultivarModel;

public class RegresionMulti extends ParentMethod {
    protected RegresionMultivarInputController inputController;
    protected RegresionMultivarOutputController outputController;

    public RegresionMulti(ObservableResourceFactory RESOURCE_FACTORY) {
        super(RESOURCE_FACTORY);
    }

    @Override
    public void initialize(AnchorPane inputSection, AnchorPane outputSection) {
        //Añadir a la ventana la seccion de input y output
        try {
            FXMLLoader inputLoader = new FXMLLoader(Main.class.getResource("fxml/Methods/RegresionMultivarInput.fxml"));
            Parent inputRoot = inputLoader.load();
            RegresionMultivarInputController inputController = inputLoader.getController();
            inputSection.getChildren().setAll(inputRoot);

            FXMLLoader outputLoader = new FXMLLoader(Main.class.getResource("fxml/Methods/RegresionMultivarOutput.fxml"));
            Parent outputRoot = (Parent) outputLoader.load();
            RegresionMultivarOutputController outputController = outputLoader.getController();
            outputSection.getChildren().setAll(outputRoot);

            inputController.setRegresionMulti(this);


            this.inputController = inputController;
            this.outputController = outputController;


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public void initTable() {

    }

    public void regresion(float[] x1, float[] x2, float[] y, int n, float x1Value, float x2Value) {
        float[][] values = new float[n][7];
        float A[] = new float[3];
        String ecu;
        float sx1 = 0, sx2 = 0, sy = 0, sx1y = 0, sx1_2 = 0, sx2_2 = 0, sx1x2 = 0, sx2y = 0, st = 0, sr = 0, xm = 0, ym = 0, a2, a1, a0, r = 0;

        for (int i = 0; i < n; i++) {
            sy += y[i];
            sx1 += x1[i];
            sx2 += x2[i];

            values[i][0] = x1[i] * x1[i];
            sx1_2 += x1[i] * x1[i];

            values[i][1] = x2[i] * x2[i];
            sx2_2 += x2[i] * x2[i];

            values[i][2] = x1[i] * y[i];
            sx1y += x1[i] * y[i];

            values[i][3] = x2[i] * y[i];
            sx2y += x2[i] * y[i];

            values[i][4] = x1[i] * x2[i];
            sx1x2 += x1[i] * x2[i];


        }

        float[][] matriz = new float[3][3];
        matriz[0][0] = n;
        matriz[0][1] = sx1;
        matriz[0][2] = sx2;
        matriz[1][0] = sx1;
        matriz[1][1] = sx1_2;
        matriz[1][2] = sx1x2;
        matriz[2][0] = sx2;
        matriz[2][1] = sx1x2;
        matriz[2][2] = sx2_2;
        float[] B = new float[3];
        B[0] = sy;
        B[1] = sx1y;
        B[2] = sx2y;

        //Resolver con Gauss,debe dar el resultado de a0,a1,a2
        A = solve(matriz, B);
        a0 = A[0];
        a1 = A[1];
        a2 = A[2];


        ecu = "y= (" + a0 + ") + (" + a1 + ")x1" + " + (" + a2 + ")x2";
        System.out.println(ecu);
        inputController.setEquation(ecu);

        for (int j = 0; j < n; j++) {

            values[j][5] = (y[j] - ym) * (y[j] - ym);
            st += (y[j] - ym) * (y[j] - ym);
            values[j][6] = (y[j] - a0 - a1 * x1[j] - a2 * x2[j]) * (y[j] - a0 - a1 * x1[j] - a2 * x2[j]);
            sr += (y[j] - a0 - a1 * x1[j] - a2 * x2[j]) * (y[j] - a0 - a1 * x1[j] - a2 * x2[j]);


        }


        r = (float) Math.sqrt((st - sr) / st);

        float yValue =  a0+a1*x1Value+a2*x2Value;

        inputController.setY(""+yValue);

        System.out.println(r);
        inputController.setR(""+r);

        //setDATA
        TableView myTable = outputController.getTable();
        ObservableList<RegresionMultivarModel> data = FXCollections.observableArrayList();

        for (int i=0; i<values.length; i++){
            data.add(new RegresionMultivarModel(""+y[i],""+x1[i],""+x2[i],""+values[i][0],""+values[i][1],
                    ""+values[i][4],""+values[i][2],""+values[i][3],""+values[i][5],""+values[i][6]));
        }

        myTable.setItems(data);

    }



    private void pivot(float matriz[][], int piv, int var) {
        float temp = 0;
        temp = matriz[piv][piv];
        for (int y = 0; y < (var + 1); y++) {

            matriz[piv][y] = matriz[piv][y] / temp;
        }
    }

    private void makeCeros(float matriz[][], int piv, int var) {
        for (int x = 0; x < var; x++) {
            if (x != piv) {
                float c = matriz[x][piv];
                for (int z = 0; z < (var + 1); z++) {
                    matriz[x][z] = ((-1 * c) * matriz[piv][z]) + matriz[x][z];

                }
                System.out.println("");

            }

        }
    }


    public float[] solve(float[][] A, float[] B) {


        int piv = 0;
        float matriz[][];
        int var = A.length;


        matriz = new float[var][var + 1];

        for (int i = 0; i < var; i++) {
            matriz[i][var] = B[i];

            for (int j = 0; j < var; j++)
                matriz[i][j] = A[i][j];
        }

        //for(int i=0;i<var;i++)

        for (int a = 0; a < var; a++) {
            pivot(matriz, piv, var);
            //meter renglon a pivote

            //hacer ceros
            makeCeros(matriz, piv, var);

            piv++;
        }
        float[] res = new float[var];
        for (int x = 0; x < var; x++) {
            res[x] = (float) matriz[x][var];

        }


        return res;
    }


    @Override
    public void showInfo() {
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setTitle(RESOURCE_FACTORY.getResources().getString("descTitle"));
        info.setHeaderText(RESOURCE_FACTORY.getResources().getString("descGaussHeader"));
        info.setContentText(RESOURCE_FACTORY.getResources().getString("descGaussDescription"));
        info.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        info.getDialogPane().setPrefWidth(650);
        info.show();
    }


    @Override
    public String toString() {
        return "Regresion multivariable";
    }
}