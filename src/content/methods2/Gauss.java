package content.methods2;

import content.Main;
import content.Utilities.ObservableResourceFactory;
import content.controller.methods.GaussInputController;
import content.controller.methods.GaussOtputController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.util.Scanner;

public class Gauss extends ParentMethod{
    private GaussInputController inputController;
    private GaussOtputController outputController;

    public Gauss(ObservableResourceFactory RESOURCE_FACTORY) {
        super(RESOURCE_FACTORY);
    }

    @Override
    public void initialize(AnchorPane inputSection, AnchorPane outputSection) {
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
        outputController.clear();
        int N = B.length;
        for (int k = 0; k < N; k++)
        {
            /** find pivot row **/
            int max = k;
            for (int i = k + 1; i < N; i++)
                if (Math.abs(A[i][k]) > Math.abs(A[max][k]))
                    max = i;

            /** swap row in A matrix **/
            double[] temp = A[k];
            A[k] = A[max];
            A[max] = temp;

            /** swap corresponding values in constants matrix **/
            double t = B[k];
            B[k] = B[max];
            B[max] = t;

            /** pivot within A and B **/
            for (int i = k + 1; i < N; i++)
            {
                double factor = A[i][k] / A[k][k];
                B[i] -= factor * B[k];
                printRowEchelonForm(A, B);
                for (int j = k; j < N; j++){
                    A[i][j] -= factor * A[k][j];

                }
            }
        }

        /** Print row echelon form **/
        printRowEchelonForm(A, B);

        /** back substitution **/
        double[] solution = new double[N];
        for (int i = N - 1; i >= 0; i--)
        {
            double sum = 0.0;
            for (int j = i + 1; j < N; j++)
                sum += A[i][j] * solution[j];
            solution[i] = (B[i] - sum) / A[i][i];
        }
        /** Print solution **/
        printSolution(solution);
    }
    /** function to print in row    echleon form **/
    public void printRowEchelonForm(double[][] A, double[] B)
    {
        int N = B.length;

        System.out.println("\nRow Echelon form : ");
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
                System.out.printf("%.3f ", A[i][j]);
            System.out.printf("| %.3f\n", B[i]);
        }
        System.out.println();

        outputController.addIteration(A,B);
    }
    /** function to print solution **/
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

    /** Main function **/



    @Override
    public String toString(){
        return "Gauss";
    }
}
