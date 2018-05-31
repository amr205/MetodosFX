package sample.methods2;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.util.Callback;
import sample.Main;
import sample.Utilities.ObservableResourceFactory;
import sample.controller.methods.RegresionLinealInputController;
import sample.controller.methods.RegresionLinealOutputController;
import sample.controller.methods.RegresionPoliInputController;
import sample.controller.methods.RegresionPoliOutputController;
import sample.methods.model.RegresionLinealModel1;
import sample.methods.model.RegresionLinealModel2;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class RegresionPoli extends ParentMethod{
    protected RegresionPoliInputController inputController;
    protected RegresionPoliOutputController outputController;

    private double[] xs;
    private double[] ys;
    private int n;
    private int grado;
    private double[][]tabla;
    private double xValue;

    public RegresionPoli(ObservableResourceFactory RESOURCE_FACTORY) {
        super(RESOURCE_FACTORY);
    }

    @Override
    public void initialize(AnchorPane inputSection, AnchorPane outputSection) {
        //Añadir a la ventana la seccion de input y output
        try {
            FXMLLoader inputLoader = new FXMLLoader(Main.class.getResource("fxml/Methods/RegresionPoliInput.fxml"));
            Parent inputRoot =  inputLoader.load();
            RegresionPoliInputController inputController =  inputLoader.getController();
            inputSection.getChildren().setAll(inputRoot);

            FXMLLoader outputLoader = new FXMLLoader(Main.class.getResource("fxml/Methods/RegresionPoliOutput.fxml"));
            Parent outputRoot = (Parent) outputLoader.load();
            RegresionPoliOutputController outputController = outputLoader.getController();
            outputSection.getChildren().setAll(outputRoot);

            inputController.setRegresionPoli(this);

            this.inputController = inputController;
            this.outputController = outputController;


        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    public void initTable(){
        TableView<List<String >> myTable = outputController.getTable();
        myTable.getItems().clear();
        myTable.getColumns().clear();

        TableColumn column1 = new TableColumn<List<String>, String>("x");
        column1.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> listStringCellDataFeatures) {
                return new ReadOnlyStringWrapper(listStringCellDataFeatures.getValue().get(0)) ;
            }
        });
        myTable.getColumns().add(column1);

        TableColumn column2 = new TableColumn<List<String>, String>("y");
        column2.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> listStringCellDataFeatures) {
                return new ReadOnlyStringWrapper(listStringCellDataFeatures.getValue().get(1)) ;
            }
        });
        myTable.getColumns().add(column2);


        //add columns for x^n
        System.out.println(tabla[0].length);
        int number = tabla[0].length-grado-2;
        System.out.println("NUMBER"+number);
        for(int i=0; i<number; i++ ) {
            final int tempi = 2+i;
            TableColumn temp = new TableColumn<List<String>, String>("x^"+(i+2));
            temp.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> listStringCellDataFeatures) {
                    return new ReadOnlyStringWrapper(listStringCellDataFeatures.getValue().get(tempi));
                }
            });
            myTable.getColumns().add(temp);

        }

        //add columns for x^n*y
        for(int i=0; i<grado; i++ ) {
            final int tempi = 2+number+i;
            TableColumn temp = new TableColumn<List<String>, String>("X^"+(i+1)+"*y");
            temp.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> listStringCellDataFeatures) {
                    return new ReadOnlyStringWrapper(listStringCellDataFeatures.getValue().get(tempi));
                }
            });
            myTable.getColumns().add(temp);

        }

        //add final columns
        TableColumn temp = new TableColumn<List<String>, String>("st");
        temp.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> listStringCellDataFeatures) {
                return new ReadOnlyStringWrapper(listStringCellDataFeatures.getValue().get(2+number+grado));
            }
        });
        myTable.getColumns().add(temp);
        temp = new TableColumn<List<String>, String>("sr");
        temp.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> listStringCellDataFeatures) {
                return new ReadOnlyStringWrapper(listStringCellDataFeatures.getValue().get(3+number+grado));
            }
        });
        myTable.getColumns().add(temp);

    }


    @Override
    public void showInfo() {
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setTitle("Regresión polinomial");
        info.setHeaderText("Descripción");
        info.setContentText("En determinadas ocasiones no se pueden ajustar los datos a un modelo lineal de forma correcta, por lo que es necesario la creación de una ecuación no lineal polinomial con la cual se pueden ajustar de una mejor forma los datos mientras mayor sea su grado, cabe resaltar que al termino del método es necesario resolver un sistema de ecuaciones por el método de Gauss o Gauss Jordan");
        info.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        info.getDialogPane().setPrefWidth(650);
        info.show();
    }

    public void setData(double[] xs, double[] ys, int n,int grado, double xValue) {
        this.xs = xs;
        this.ys = ys;
        this.n = n;
        this.grado = grado;
        this.tabla=new double [n][grado*2+grado+1];
        this.xValue = xValue;
    }


    private double SumY(){
        double sum = 0;

        for(double i : ys)
            sum +=i;
        return sum;
    }


    private double YProm(){
        double prom = 0;
        for(double i : ys)
            prom +=i;
        prom = prom / n;

        return prom;
    }

    private double PowX(int pot){
        double pow = 0;
        for(int i=0;i<n;i++){
            pow = pow + Math.pow(xs[i],pot);
            if(pot!=1)
                tabla[i][pot-2]=Math.pow(xs[i],pot);

        }
        return pow;
    }

    private double xyValue(int pos){
        double sum = 0;

        int i;


        for(i=0;i<n;i++){
            sum += Math.pow(xs[i],pos) * ys[i];
            tabla[i][grado*2+pos-2]= Math.pow(xs[i],pos) * ys[i];

        }
        return sum;
    }

    private double getSt(){
        double ym = YProm();
        double st = 0;
        double sum;
        for(int aux=0;aux<n;aux++){
            sum = ys[aux] - ym;
            st = st + Math.pow(sum,2);
            tabla[aux][grado*2+grado-1]=Math.pow(sum,2);
        }
        return st;
    }

    private double getSr(double[] res){
        double sr = 0;
        double sum = 0;
        int j = 0;

        for(int i=0;i<ys.length;i++){
            sum = ys[i] - res[0];
            sum = getResta(res,xs[i],sum);
            sum = Math.pow(sum,2);
            sr += sum;
            tabla[i][grado*2+grado]=sum;
        }
        return sr;
    }

    private double getResta(double[] res, double value,double acum){
        int i;
        for(i=1;i<res.length;i++){
            acum = acum - (Math.pow(value,i)*res[i]);
        }
        return acum;
    }

    private double setR(double st,double sr){
        return Math.sqrt((st-sr)/st);
    }

    private String getFuncion(double[] res){
        String funcion = String.valueOf(res[0])+"+";
        int i;
        for(i=1;i<res.length;i++){
            if (i == 1)
               funcion= "("+ String.valueOf(res[i]) + ") x^" + i;
            else
                funcion = funcion +  " + ("+String.valueOf(res[i]) + ") x^" + i ;
        }
        return funcion;
    }

    private double evaluateFunction(double[] res,double xValue){
        double funcion=0.0;
        int i;
        for(i=1;i<res.length;i++){
            if (i == 1)
                funcion= res[i]*xValue;
            else
                funcion = funcion +  res[i]*Math.pow(xValue,i) ;
        }
        return funcion;
    }

    void showTable(){

        TableView<List<String >> myTable = outputController.getTable();
        ObservableList<List<String>> data = FXCollections.observableArrayList();

        for(int i=0;i<n;i++){
            List<String> info = new ArrayList<>();
            info.add(""+xs[i]);
            info.add(""+ys[i]);
            for(int j=0;j<(grado*2+grado+1);j++){
                System.out.print(tabla[i][j]+" ");
                info.add(""+tabla[i][j]);

            }
            data.add(info);
            System.out.println("\n");
        }


        myTable.setItems(data);
    }

    public void solve(){
        initTable();
        int cont = 0;
        int pow = 0;


        double[][] matriz = new double[grado+1][grado+1];
        double[] B = new double[grado+1];
        for(int i = 0; i < (grado + 1); i++) {
            for (int j = 0; j < (grado + 1); j++) {
                if(cont == 0){
                    matriz[i][j] = n;
                    cont = cont + 1;
                }
                else{
                    matriz[i][j] = PowX(pow);
                }
                pow = pow + 1;
            }
            if(cont == 1) {
                B[i] = SumY();
                cont = cont + 1;
            }
            else
                B[i] = xyValue(i);
            pow = i + 1;
        }


        Normal n = new Normal((grado + 1),matriz,B);
        n.eliminacion();
        double sr = getSr(n.getResultados());
        double st = getSt();

        double r = Math.sqrt((st-sr)/st);

        System.out.println(setR(getSt(),getSr(n.getResultados())));
        inputController.setR(""+r);
        System.out.println(getFuncion(n.getResultados()));
        inputController.setEquation("y= "+getFuncion(n.getResultados()));

        inputController.setY(""+evaluateFunction(n.getResultados(),xValue));
        showTable();
    }




    @Override
    public String toString(){
        return "Regresion Pollinomial";
    }
}



class Normal {
    private double[][] ecuaciones;
    private double[] coeficientes;
    double[] variables;
    private int n;

    public Normal(int n,double[][] sistema,double[] resultado){
        this.n = n;
        variables = new double[n];
        this.ecuaciones = sistema;
        this.coeficientes = resultado;
        mostrar();
    }

    public void eliminacion(){
        double[] temp;
        int diagonal = 0;
        int multiplicador;
        double razon;
        int i;
        int j;
        while(diagonal<(n-1)){
            i = diagonal + 1;
            for(;i<n;i++){
                if(diagonal!=i){
                    temp = auxiliar(diagonal);
                    multiplicador = signo(ecuaciones[diagonal][diagonal],ecuaciones[i][diagonal]);
                    razon = multiplicador*(ecuaciones[i][diagonal]/ecuaciones[diagonal][diagonal]);
                    temp = multiplicar(temp,razon);
                    sumaCoeficientes(diagonal,razon,i);
                    sumar(temp,i);
                    mostrar();
                }
            }
            diagonal = diagonal + 1;
        }
        setZ(diagonal);
        resolver();
        getVariables();
    }

    private int signo(double x, double y){
        int bandera = 1;
        if((x>0&&y>0)||(x<0&&y<0))
            bandera = -1;
        else
        if((x<0&&y>0)||(x>0&&y<0))
            bandera = -1;
        return bandera;
    }

    private double[] auxiliar(int x){
        double[] temp = new double[n];
        int i;
        for(i=0;i<n;i++)
            temp[i] = ecuaciones[x][i];
        return temp;
    }

    private double[] multiplicar(double[] p,double cambio){
        int i;
        for(i=0;i<n;i++) {
            p[i] = p[i] * cambio;
        }
        return p;
    }

    private void mostrar(){
        int i;
        int j;
        for(i=0;i<n;i++){
            for(j=0;j<n;j++)
                System.out.print(ecuaciones[i][j]+" | ");
            System.out.println(coeficientes[i]);
        }
        System.out.println("-------------------------------------");
        System.out.println(" ");
    }

    private void sumaCoeficientes(int contador,double cambio,int pos){
        double temp = coeficientes[contador] * cambio;
        coeficientes[pos] = coeficientes[pos] + temp;
    }

    private void sumar(double[] suma,int pos){
        int i;
        for(i=0;i<n;i++){
            ecuaciones[pos][i] = ecuaciones[pos][i] + suma[i];
        }
    }

    private void setZ(int diagonal){
        int i;
        double temp = ecuaciones[diagonal][diagonal];
        for(i=0;i<n;i++)
            ecuaciones[diagonal][i] = ecuaciones[diagonal][i] / ecuaciones[diagonal][diagonal];
        coeficientes[diagonal] = coeficientes[diagonal] / temp;
        mostrar();
    }

    private void ecuacion(double[] ecuacion, double coe, int j){
        int i = n -1;
        double resultado = 0;
        for (;i>j;i--){
            ecuacion[i] = ecuacion[i] * variables [i];
            resultado = resultado + ecuacion[i];
        }
        resultado = coe + (resultado * -1);
        resultado = resultado / ecuacion[j];
        variables[j] = resultado;
    }

    private void resolver(){
        variables[n-1] = coeficientes[n-1];
        double[] temp;
        int i = n-2;
        for(;i>-1;i--){
            temp = auxiliar(i);
            ecuacion(temp,coeficientes[i],i);
        }
    }

    private void getVariables() {
        DecimalFormat format = new DecimalFormat("###.##");
        for(double n : variables)
            System.out.println(format.format(n));
    }

    public double[] getResultados(){
        return variables;

    }
}
