package application;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class CircleGraph extends Application {
	boolean f=false;
	 Data[]data;
	public static void main(String[] args) {
		launch(args);
	}
	


	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		WelcomeGUI welcomeGUI=new WelcomeGUI();
		data=welcomeGUI.data;
		int height=600,Width=600; 
		BorderPane root=new BorderPane();
		graph(root,height,Width);
		 Scene scene=new Scene(root,height,Width);
		 Stage stage=new Stage();
		 stage.setScene(scene);
		 stage.show();
	}



	public CircleGraph() throws Exception {
		super();
		start(new Stage());
		
		
		// TODO Auto-generated constructor stub
	}
	public void graph(BorderPane root, int hight,int width) {
		
		double total=0;
		double startAlpha=0;
		double startNextAlpha=0;
	       double a=0;

		Pane pane=new Pane();
		double []alpah=new double[data.length];
		for(int i=0;i<data.length;i++) {
			total+=data[i].getPercentage();
		}
		for(int i=0;i<data.length;i++) {
       alpah[i]=(data[i].getPercentage()/total)*360;
		}
	for(int i=0;i<data.length;i++) {
			if(i!=0) {
				a+=alpah[i-1];
			}
			System.out.println("a"+alpah[0]);
			
			startAlpha=	drow(pane, hight/2, width/2, 60,2*(a+alpah[i]), startAlpha, data[i].getrColor(), data[i].getgColor(),data[i].getbColor());
			//drow(pane, hight/2, width/2, 60,alpah[1], startAlpha, data[1].getrColor(), data[1].getgColor(),data[1].getbColor());
			//drow(pane, hight/2, width/2, 60,alpah[2], startAlpha, data[2].getrColor(), data[2].getgColor(),data[2].getbColor());
		
//	startAlpha+=alpah[i];
//	a+=alpah[i];
		}
	root.setCenter(pane);
		 
		
	}
	/*
	 * public void circleSmooth(GL2 gl,float centerX , float centerY,float r , int alpha){
		 gl.glBegin(GL2.GL_LINES);
		 
		 for (int i = 0, j=0; i <alpha; i++,j++) { 
			
			 if(j==30 ||i==0){
				j=0;
				 double angle= Math.PI *i/180;
				 double x = 1.4*Math.cos(angle)*r;
				 double y =1.4* Math.sin(angle)*r;
				 gl.glVertex2f((float) centerX, (float)centerY);
				 gl.glVertex2f((float) x+centerX, (float)y+centerY);
			 }else{
				 double angle= Math.PI *i/180;
				 double x = Math.cos(angle)*r;
				 double y = Math.sin(angle)*r;
		
		 gl.glVertex2f((float) centerX, (float)centerY);
		 gl.glVertex2f((float) x+centerX, (float)y+centerY);
		 
		 }}*/
	 public double drow(Pane root,double xCenter,double yCenter,double raduis ,double alpha,double startAlpha,double d,double g,double h) {
		double w = 0;
		
		 for (double i =startAlpha; i <alpha; i++) { 
				 double angle= Math.PI *i/360;
				 System.out.println(angle);

				 double x = Math.cos(angle)*raduis;
				 double y = Math.sin(angle)*raduis;
				
		Line line=new Line(xCenter,yCenter,x+xCenter,y+yCenter);
		line.setStroke(Color.RED);
		line.setStyle(" -fx-stroke:" + "#" + decimalToHexTwoDigits((int)d)+ decimalToHexTwoDigits((int) g)+ decimalToHexTwoDigits((int) h) + ";");
		
		root.getChildren().add(line);
		 w=i;
		 }
		 root.setOnMouseClicked(e->{
			 System.out.println("x="+e.getX()+" y= "+e.getY());
		 });
		 return w+1;
	 }
	 public static String decimalToHexTwoDigits(int num) {
			int res1 = 0;
			int mod[] = new int[2];
			String res = "";
			res1 = (int) num / 16;
			mod[0] = num % 16;
			mod[1] = (int) res1 % 16;
			for (int i = mod.length - 1; i >= 0; i--) {
				if (mod[i] > 9) {
					switch (mod[i]) {
					case 10:
						res += "A";
						break;
					case 11:
						res += "B";
						break;
					case 12:
						res += "C";
						break;
					case 13:
						res += "D";
						break;
					case 14:
						res += "E";
						break;
					case 15:
						res += "F";
					}
				} else
					res += mod[i];
			}
			return res;
		}
}
