package sample.methods2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import sample.Main;
import sample.Utilities.ObservableResourceFactory;
import sample.controller.methods.*;
import sample.methods.model.BiseccionModel;
import sample.methods.model.NewtonRapsonMultivarModel;
import sample.methods.model.SecanteModel;

import java.math.BigDecimal;

public class NewtonRaphsonMultivar extends ParentMethod{
    protected NewtonRMInputController inputController;
    protected NewtonRaphsonMultivarController outputController;

    public NewtonRaphsonMultivar(ObservableResourceFactory RESOURCE_FACTORY) {
        super(RESOURCE_FACTORY);
    }

    @Override
    public void initialize(AnchorPane inputSection, AnchorPane outputSection) {
        //Añadir a la ventana la seccion de input y output
        try {
            FXMLLoader inputLoader = new FXMLLoader(Main.class.getResource("fxml/Methods/NewtonRMInput.fxml"));
            Parent inputRoot =  inputLoader.load();
            NewtonRMInputController inputController = (NewtonRMInputController) inputLoader.getController();
            inputSection.getChildren().setAll(inputRoot);

            FXMLLoader outputLoader = new FXMLLoader(Main.class.getResource("fxml/Methods/NewtonRaphsonMultivarOutput.fxml"));
            Parent outputRoot = (Parent) outputLoader.load();
            NewtonRaphsonMultivarController outputController = (NewtonRaphsonMultivarController) outputLoader.getController();
            outputSection.getChildren().setAll(outputRoot);

            inputController.setRESOURCE_FACTORY(RESOURCE_FACTORY);
            inputController.setNewton(this);

            this.inputController = inputController;
            this.outputController = outputController;


        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void solve(Float err,Float Sx,Float Sy, String E1,String E2, String E1x,String E1y, String E2x,  String E2y){
        BigDecimal x, y, e1, e2, e1x, e2x, e1y, e2y, Dx, Dy,erx,ery,xant,yant,Rx,Ry;
        ObservableList<NewtonRapsonMultivarModel> data = FXCollections.observableArrayList();
        int n;
        n=1;

        System.out.println(err+"   "+Sx+"   "+Sy);
        Expression Ec1 = new ExpressionBuilder(E1).variables("x","y").build();
        Expression Ec2 = new ExpressionBuilder(E2).variables("x","y").build();
        Expression Ec1x = new ExpressionBuilder(E1x).variables("x","y").build();
        Expression Ec2x = new ExpressionBuilder(E2x).variables("x","y").build();
        Expression Ec1y = new ExpressionBuilder(E1y).variables("x","y").build();
        Expression Ec2y = new ExpressionBuilder(E2y).variables("x","y").build();


        erx = new BigDecimal(20).setScale(6, BigDecimal.ROUND_HALF_UP);
        ery = new BigDecimal(20).setScale(6, BigDecimal.ROUND_HALF_UP);

        x=new BigDecimal(Sx).setScale(6, BigDecimal.ROUND_HALF_UP);
        y=new BigDecimal(Sy).setScale(6, BigDecimal.ROUND_HALF_UP);

        System.out.println(x.doubleValue()+"     "+y.doubleValue());

        do{
            System.out.println("------------------------------------");
            Ec1.setVariable("x",x.doubleValue());
            Ec1.setVariable("y",y.doubleValue());

            double e1Double= Ec1.evaluate();

            System.out.println("f1"+e1Double);
            e1=new BigDecimal(e1Double).setScale(6, BigDecimal.ROUND_HALF_UP);

            double e2Double= Ec2.setVariable("x",x.doubleValue()).setVariable("y",y.doubleValue()).evaluate();
            e2=new BigDecimal(e2Double).setScale(6, BigDecimal.ROUND_HALF_UP);
            System.out.println("f1"+e2Double);

            double e1xDouble= Ec1x.setVariable("x",x.doubleValue()).setVariable("y",y.doubleValue()).evaluate();
            e1x=new BigDecimal(e1xDouble).setScale(6, BigDecimal.ROUND_HALF_UP);
            System.out.println("e1x"+e1xDouble);

            double e2xDouble= Ec2x.setVariable("x",x.doubleValue()).setVariable("y",y.doubleValue()).evaluate();
            e2x=new BigDecimal(e2xDouble).setScale(6, BigDecimal.ROUND_HALF_UP);
            System.out.println("e2x"+e2xDouble);

            double e1yDouble= Ec1y.setVariable("x",x.doubleValue()).setVariable("y",y.doubleValue()).evaluate();
            e1y=new BigDecimal(e1yDouble).setScale(6, BigDecimal.ROUND_HALF_UP);
            System.out.println("e1y"+e1yDouble);

            double e2yDouble= Ec2y.setVariable("x",x.doubleValue()).setVariable("y",y.doubleValue()).evaluate();
            e2y=new BigDecimal(e2yDouble).setScale(6, BigDecimal.ROUND_HALF_UP);
            System.out.println("e2y"+e2yDouble);

            //double DxDouble = ((-e1.doubleValue()*e2y.doubleValue())+(e2.doubleValue()*e1y.doubleValue()))/((e1x.doubleValue()*e2y.doubleValue())-(e2x.doubleValue()*e1y.doubleValue()));
            //Dx=new BigDecimal(DxDouble).setScale(6, BigDecimal.ROUND_HALF_UP);
            double DxDouble1 =((-e1Double)*e2yDouble);
            System.out.println("Dx "+DxDouble1);
            double DxDouble11=(e2Double*e1yDouble);
            System.out.println("Dx "+DxDouble11);
            double DxDouble111=DxDouble1+DxDouble11;
            System.out.println("Dx "+DxDouble111);
            double DxDouble2 =(e1xDouble*e2yDouble);
            System.out.println("Dx "+DxDouble2);
            double DxDouble22=e2xDouble*e1yDouble;
            System.out.println("Dx "+DxDouble22);
            double DxDouble222=DxDouble2-DxDouble22;
            System.out.println("Dx "+DxDouble222);
            double DxDouble3=DxDouble111/DxDouble222;
            System.out.println("Dx "+DxDouble3);
            double DxDouble=DxDouble3;
            System.out.println("Dx "+DxDouble);


            //double DyDouble = ((-e2.doubleValue()*e1x.doubleValue())+(e1.doubleValue()*e2x.doubleValue()))/((e1x.doubleValue()*e2y.doubleValue())-(e2x.doubleValue()*e1y.doubleValue()));
            //Dy=new BigDecimal(DyDouble).setScale(6, BigDecimal.ROUND_HALF_UP);
            double DyDouble =((-e2Double)*e1xDouble+e1Double*e2xDouble)/(e1xDouble*e2yDouble-e2xDouble*e1yDouble);
            System.out.println("Dy "+DyDouble);


            xant=x;
            yant=y;

            //Double xDouble= x.doubleValue()+Dx.doubleValue();
            Double xDouble=x.doubleValue()+DxDouble;
            x=new BigDecimal(xDouble).setScale(6, BigDecimal.ROUND_HALF_UP);

            //Double yDouble= y.doubleValue()+Dy.doubleValue();
            Double yDouble=y.doubleValue()+DyDouble;
            y=new BigDecimal(yDouble).setScale(6, BigDecimal.ROUND_HALF_UP);

            if(n!=1) {


                double erxDouble = Math.abs((x.doubleValue()-xant.doubleValue())/x.doubleValue()*100);
                erx = new BigDecimal(erxDouble).setScale(6,BigDecimal.ROUND_HALF_UP);
                System.out.println("ex"+erxDouble);

                double eryDouble = Math.abs((y.doubleValue()-yant.doubleValue())/y.doubleValue()*100);
                ery = new BigDecimal(eryDouble).setScale(6,BigDecimal.ROUND_HALF_UP);
                System.out.println("ey"+eryDouble);

                data.add(new NewtonRapsonMultivarModel(Integer.toString(n),String.format("%.6f", x),String.format("%.6f", y),String.format("%.6f", e1),
                        String.format("%.6f", e2), String.format("%.6f", e1x), String.format("%.6f", e1y),String.format("%.6f", e2x),String.format("%.6f", e2y),String.format("%.6f", DxDouble),
                        String.format("%.6f", DyDouble), String.format("%.6f", erxDouble), String.format("%.6f", eryDouble)));
            }
            else
            {
                data.add(new NewtonRapsonMultivarModel(Integer.toString(n),String.format("%.6f", x),String.format("%.6f", y),String.format("%.6f", e1),
                        String.format("%.6f", e2), String.format("%.6f", e1x), String.format("%.6f", e1y),String.format("%.6f", e2x),String.format("%.6f", e2y),String.format("%.6f", DxDouble),
                        String.format("%.6f", DyDouble), "-------","---------"));
            }

            n++;





        }while(erx.floatValue()>err||ery.floatValue()>err);

        Rx=x;
        Ry=y;
        TableView<NewtonRapsonMultivarModel> table = outputController.getTable();
        table.setItems(data);


    }

    public void initTable(){
        TableView table = outputController.getTable();
        table.getItems().clear();
        table.getColumns().clear();

        TableColumn column = new TableColumn("no");
        column.setCellValueFactory(new PropertyValueFactory<NewtonRapsonMultivarModel,String>("no"));

        TableColumn column1 = new TableColumn("x");
        column1.setCellValueFactory(new PropertyValueFactory<NewtonRapsonMultivarModel,String>("x"));

        TableColumn column2 = new TableColumn("y");
        column2.setCellValueFactory(new PropertyValueFactory<NewtonRapsonMultivarModel,String>("y"));

        TableColumn column3 = new TableColumn("f1");
        column3.setCellValueFactory(new PropertyValueFactory<NewtonRapsonMultivarModel,String>("f1"));

        TableColumn column4 = new TableColumn("f2");
        column4.setCellValueFactory(new PropertyValueFactory<NewtonRapsonMultivarModel,String>("f2"));

        TableColumn column5 = new TableColumn("df1x");
        column5.setCellValueFactory(new PropertyValueFactory<NewtonRapsonMultivarModel,String>("df1x"));

        TableColumn column6 = new TableColumn("df1y");
        column6.setCellValueFactory(new PropertyValueFactory<NewtonRapsonMultivarModel,String>("df1y"));

        TableColumn column7 = new TableColumn("df2x");
        column7.setCellValueFactory(new PropertyValueFactory<NewtonRapsonMultivarModel,String>("df2x"));

        TableColumn column8 = new TableColumn("df2y");
        column8.setCellValueFactory(new PropertyValueFactory<NewtonRapsonMultivarModel,String>("df2y"));

        TableColumn column9 = new TableColumn("deltaX");
        column9.setCellValueFactory(new PropertyValueFactory<NewtonRapsonMultivarModel,String>("deltaX"));

        TableColumn column10 = new TableColumn("deltaY");
        column10.setCellValueFactory(new PropertyValueFactory<NewtonRapsonMultivarModel,String>("deltaY"));

        TableColumn column11 = new TableColumn("error X");
        column11.setCellValueFactory(new PropertyValueFactory<NewtonRapsonMultivarModel,String>("ex"));

        TableColumn column12 = new TableColumn("error Y");
        column12.setCellValueFactory(new PropertyValueFactory<NewtonRapsonMultivarModel,String>("ey"));

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        table.getColumns().addAll(column,column1,column2, column3,column4, column5, column6, column7, column8,column9,column10,column11,column12);

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
        return "Newton Raphson Multivariable";
    }
}
