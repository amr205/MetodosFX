package sample.methods2;

import javafx.scene.control.Alert;
import javafx.scene.layout.Region;
import sample.Main;
import sample.Utilities.ObservableResourceFactory;
import sample.controller.methods.GaussInputController;
import sample.controller.methods.GaussOtputController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

public class Gauss extends ParentMethod{
    protected GaussInputController inputController;
    protected GaussOtputController outputController;

    public Gauss(ObservableResourceFactory RESOURCE_FACTORY) {
        super(RESOURCE_FACTORY);
    }

    @Override
    public void initialize(AnchorPane inputSection, AnchorPane outputSection) {
        //Añadir a la ventana la seccion de input y output
        try {
            FXMLLoader inputLoader = new FXMLLoader(Main.class.getResource("fxml/Methods/GaussInput.fxml"));
            AnchorPane inputRoot = (AnchorPane) inputLoader.load();
            GaussInputController inputController = (GaussInputController) inputLoader.getController();
            inputSection.getChildren().setAll(inputRoot);

            FXMLLoader outputLoader = new FXMLLoader(Main.class.getResource("fxml/Methods/GaussOutput.fxml"));
            Parent outputRoot = (Parent) outputLoader.load();
            GaussOtputController outputController = (GaussOtputController) outputLoader.getController();
            outputSection.getChildren().setAll(outputRoot);

            inputController.setGauss(this);

            this.inputController = inputController;
            this.outputController = outputController;


        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void solve(double[][] A, double[] B)
    {
        //solucionar
        outputController.clear();
        int N = B.length;
        for (int k = 0; k < N; k++)
        {

            int max = k;
            for (int i = k + 1; i < N; i++)
                if (Math.abs(A[i][k]) > Math.abs(A[max][k]))
                    max = i;

            double[] temp = A[k];
            A[k] = A[max];
            A[max] = temp;

            double t = B[k];
            B[k] = B[max];
            B[max] = t;

            for (int i = k + 1; i < N; i++)
            {
                double factor = A[i][k] / A[k][k];
                B[i] -= factor * B[k];
                outputController.addIteration(A,B);
                for (int j = k; j < N; j++){
                    A[i][j] -= factor * A[k][j];

                }
            }
        }

        outputController.addIteration(A,B);

        double[] solution = new double[N];
        for (int i = N - 1; i >= 0; i--)
        {
            double sum = 0.0;
            for (int j = i + 1; j < N; j++)
                sum += A[i][j] * solution[j];
            solution[i] = (B[i] - sum) / A[i][i];
        }
        printSolution(solution);
    }


    public void printSolution(double[] sol)
    {

        int N = sol.length;
        System.out.println("\nSolution : ");
        inputController.startResult();
        for (int i = 0; i < N; i++) {
            System.out.printf("%.3f ", sol[i]);
            inputController.addResultLabel(sol[i],(i+1));
        }
        System.out.println();

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
    public String toString(){
        return "Gauss";
    }
}
