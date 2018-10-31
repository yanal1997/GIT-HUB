package application;

import java.lang.reflect.Array;
import java.util.Arrays;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CircleGraph extends Application {
	boolean f=false;
	 Data[]data;
	private String choose;
	WelcomeGUI welcomeGUI;
	public static void main(String[] args) {
		launch(args);
	}
	


	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		 welcomeGUI=new WelcomeGUI();
		data=welcomeGUI.data;
		int height=600,Width=600; 
		BorderPane root=new BorderPane();
		ScrollPane scrollpane=new ScrollPane();
		System.out.println("ch"+choose);
		if(choose.contains("Pi")) {
			graph(root,height,Width);

		}
		else if(choose.contains("Bar")) {
			barGraph(scrollpane,root);
			root.setCenter(scrollpane);


		}else if(choose.contains("Cicle")) {
			graphArc(root, height, Width);

		}
		else if(choose.contains("Dote")) {
			xGraph(scrollpane,root);
			root.setCenter(scrollpane);


		}
		 Scene scene=new Scene(root,height,Width);
		 Stage stage=new Stage();
		 stage.setScene(scene);
		 stage.show();
	}



	public CircleGraph(String choose) throws Exception {
		super();
		this.choose=choose;
		start(new Stage());
		
		
		// TODO Auto-generated constructor stub
	}
	public void graph(BorderPane root, int hight,int width) {
		
		double total=0;
		double startAlpha=0;
		double startNextAlpha=0;
	       double a=0;
	       Pane pane=new Pane();
			Label [ ]colorLabel=new Label[data.length];
			Label [ ]nameLabel=new Label[data.length];
			Label [ ]preLabel=new Label[data.length];

			HBox []hbox=new HBox[data.length];
			VBox vbox=new VBox(10); 
			ScrollPane spn=new ScrollPane();
			int p=0;
			for(int i=0;i<data.length;i++) {
				p+=data[i].getPercentage();
			}
			
			for(int i=0;i<data.length;i++) {
				colorLabel[i]=new Label();
				colorLabel[i].setMinSize(60, 30);;
				colorLabel[i].setStyle(" -fx-background-color:" + "#" + decimalToHexTwoDigits((int)data[i].getrColor())
				+ decimalToHexTwoDigits((int) data[i].getgColor())
				+ decimalToHexTwoDigits((int) data[i].getbColor()) + ";");
				nameLabel[i]=new Label(data[i].getNameForData());
				nameLabel[i].setMinSize(60, 30);
				double t=Math.floor((data[i].getPercentage()/p)*100);
				preLabel[i]=new Label(t+"%");
				preLabel[i].setMinSize(60, 30);
				preLabel[i].setMaxSize(60, 30);

				hbox[i]=new HBox(10);
				hbox[i].getChildren().addAll(nameLabel[i],colorLabel[i],preLabel[i]);
				vbox.getChildren().add(hbox[i]);
			}
			spn.setContent(vbox);
			Label l=new Label(welcomeGUI.getTitle());
			
			l.setStyle("-fx-font-size: 32pt;");
			root.setTop(l);
			root.setAlignment(l, Pos.CENTER);
			
			root.setBottom(spn);
		Pane pane2=new Pane();
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
			
			startAlpha=	drow(pane2, hight/2, width/2, 60,2*(a+alpah[i]), startAlpha, data[i].getrColor(), data[i].getgColor(),data[i].getbColor());
			//drow(pane, hight/2, width/2, 60,alpah[1], startAlpha, data[1].getrColor(), data[1].getgColor(),data[1].getbColor());
			//drow(pane, hight/2, width/2, 60,alpah[2], startAlpha, data[2].getrColor(), data[2].getgColor(),data[2].getbColor());
		
//	startAlpha+=alpah[i];
//	a+=alpah[i];
		}
	root.setCenter(pane2);
		 
		
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

				 double x = Math.cos(angle)*raduis;
				 double y = Math.sin(angle)*raduis;
				
		Line line=new Line(xCenter,yCenter,x+xCenter,y+yCenter);
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
	 public void barGraph(ScrollPane  root,BorderPane root2) {
		 Pane pane=new Pane();
			Label [ ]colorLabel=new Label[data.length];
			Label [ ]nameLabel=new Label[data.length];
			Label [ ]prLabel=new Label[data.length];

			HBox []hbox=new HBox[data.length];
			VBox vbox=new VBox(10); 
			ScrollPane spn=new ScrollPane();
			int pf=0;
			for(int i=0;i<data.length;i++) {
				pf+=data[i].getPercentage();
			}
			
			
			for(int i=0;i<data.length;i++) {
				
				colorLabel[i]=new Label();
				colorLabel[i].setMinSize(60, 30);;
				colorLabel[i].setStyle(" -fx-background-color:" + "#" + decimalToHexTwoDigits((int)data[i].getrColor())
				+ decimalToHexTwoDigits((int) data[i].getgColor())
				+ decimalToHexTwoDigits((int) data[i].getbColor()) + ";");
				nameLabel[i]=new Label(data[i].getNameForData());
				prLabel[i]=new Label(Math.floor((data[i].getPercentage()/pf)*100)+"%" );
				nameLabel[i].setMinSize(60, 30);
				nameLabel[i].setMaxSize(60, 30);
				prLabel[i].setMinSize(60, 30);
				prLabel[i].setMaxSize(60, 30);

				hbox[i]=new HBox(10);
				hbox[i].getChildren().addAll(nameLabel[i],colorLabel[i],prLabel[i]);
				vbox.getChildren().add(hbox[i]);
			}
			spn.setContent(vbox);
			root2.setBottom(spn);
			Label ll=new Label(welcomeGUI.getTitle());
			ll.setStyle("-fx-font-size: 32pt;");
			root2.setTop(ll);
			
		 double precnt;
		 int value = 10;
		 Pane p=new Pane();
		 int width =0;
		 int totalPercentage=0;
		int []x=new int[data.length];
		int []y=new int[data.length];
		 for(int i=0;i<data.length;i++) {
			 width+=80;
			 
		 }
		 for(int i=0;i<data.length;i++) {
			 	totalPercentage+=data[i].getPercentage();
		 }
		 
		 
		 Line hight=new Line(50, 50, 50, 350);
		 Line widthLine=new Line(50, 350,width,350);

		 
		 
		 for(int i=50;i<350;i+=30) {
			 Line line=new Line(50,i,45,i);

			

			 p.getChildren().add(line);

		 }
		 for(int i=350;i>50;i-=30) {

			 Text text=new Text(value+"");
			 value+=10;
			 text.setX(30);
			 text.setY(i-25);

			 p.getChildren().add(text);

		 }
		 
		 for(int i=80,j=0;i<width ;i+=55,j++) {
			 Line line=new Line(i,350,i,355);
			Text text=new Text(data[j].getNameForData());
			 text.setX(i-5);
			 text.setY(365);
			 x[j]=i;
			 y[j]=350;
			 p.getChildren().add(text);

			 p.getChildren().add(line);
		 }
		 
		 p.getChildren().add(hight);
		 p.getChildren().add(widthLine);
		 
		 root.setContent(p);
	
		 for(int i=0;i<data.length;i++) {
			 System.out.println((data[i].getPercentage()/totalPercentage)*300);
			 precnt=300- Math.floor((data[i].getPercentage()/totalPercentage)*300);
			 System.out.println("per"+precnt);
			 for(int j=x[i]-25;j<x[i]+25;j++) {
				 Line line=new Line(j, precnt, j, 350);
				line.setStyle(" -fx-stroke:" + "#" + rgbToString(data[i].getrColor(), data[i].getgColor(), data[i].getbColor())+ ";");

				 p.getChildren().add(line);
			 }
		 }
		 
		 
	 }
	 public void xGraph(ScrollPane  root,BorderPane root2) {
		 double precnt;
		 int value = 10;
		 Pane p=new Pane();
		 int width =0;
		 int totalPercentage=0;
		int []x=new  int [data.length];
		int []y=new  int [data.length];
		int[][]xYV=new int[data.length][2];

		 for(int i=0;i<data.length;i++) {
			 width+=80;
			 
		 }
		 for(int i=0;i<data.length;i++) {
			 	totalPercentage+=data[i].getPercentage();
		 }
		 
		 
		 Line hight=new Line(50, 50, 50, 350);
		 Line widthLine=new Line(50, 350,width,350);

		 
		 
		 for(int i=50;i<350;i+=30) {
			 Line line=new Line(50,i,45,i);

			

			 p.getChildren().add(line);

		 }
		 for(int i=350;i>50;i-=30) {

			 Text text=new Text(value+"");
			 value+=10;
			 text.setX(30);
			 text.setY(i-25);

			 p.getChildren().add(text);

		 }
		 Pane pane=new Pane();
			Label [ ]colorLabel=new Label[data.length];
			Label [ ]nameLabel=new Label[data.length];
			Label [ ]prLabel=new Label[data.length];

			HBox []hbox=new HBox[data.length];
			VBox vbox=new VBox(10); 
			ScrollPane spn=new ScrollPane();

			
			
			for(int i=0;i<data.length;i++) {
				colorLabel[i]=new Label();
				colorLabel[i].setMinSize(60, 30);;
				colorLabel[i].setStyle(" -fx-background-color:" + "#" + decimalToHexTwoDigits((int)data[i].getrColor())
				+ decimalToHexTwoDigits((int) data[i].getgColor())
				+ decimalToHexTwoDigits((int) data[i].getbColor()) + ";");
				nameLabel[i]=new Label(data[i].getNameForData());
				hbox[i]=new HBox(10);
				nameLabel[i].setMinSize(60, 30);
				nameLabel[i].setMaxSize(60, 30);
				prLabel[i]=new Label(Math.floor((data[i].getPercentage()/totalPercentage)*100)+"%");
				prLabel[i].setMaxSize(60, 30);
				prLabel[i].setMinSize(60, 30);

				hbox[i].getChildren().addAll(nameLabel[i],colorLabel[i],prLabel[i]);
				vbox.getChildren().add(hbox[i]);
			}
			spn.setContent(vbox);
			root2.setBottom(spn);
		 Label l3=new Label(welcomeGUI.getTitle());
		 l3.setStyle("-fx-font-size: 32pt;");
		 root2.setTop(l3); 
		 root2.setAlignment(l3, Pos.CENTER);
		 for(int i=80,j=0;i<width ;i+=55,j++) {
			 Line line=new Line(i,350,i,355);
			Text text=new Text(data[j].getNameForData());
			 text.setX(i-10);
			 text.setY(365);
			 x[j]=i;
			 p.getChildren().add(text);

			 p.getChildren().add(line);
		 }
		 
		 p.getChildren().add(hight);
		 p.getChildren().add(widthLine);
		 
		 root.setContent(p);
	
		 for(int i=0;i<data.length;i++) {
			 precnt=300-(Math.floor((data[i].getPercentage()/totalPercentage)*300));
			 Circle c=new Circle(5);
			 c.setFill(Color.BLACK);
			 c.setCenterX(x[i]);
			 c.setCenterY(precnt);
			 p.getChildren().add(c);
			 xYV[i][0]=x[i];
			 xYV[i][1]=(int) precnt;
		 }
		 Line l2=new Line(xYV[0][0], xYV[0][1],50,350);
		 p.getChildren().add(l2);

		 for(int i=0;i<xYV.length-1;i++) {
			 Line l=new Line(xYV[i][0], xYV[i][1], xYV[i+1][0], xYV[i+1][1]);
					 p.getChildren().add(l);
		 }
		 
		 
	 }
	 public static String rgbToString(double rgb, double rgb2, double rgb3) {
		    String rs = Integer.toHexString((int)(rgb ));
		    String gs = Integer.toHexString((int)(rgb2 ));
		    String bs = Integer.toHexString((int)(rgb3));
		    return rs + gs + bs;
		}
	 public void graphArc(BorderPane root, int hight,int width) {
			int array[]=new int[data.length];
			double total=0;
		       	double radius=120;
			Pane pane=new Pane();
			Label [ ]colorLabel=new Label[data.length];
			Label [ ]nameLabel=new Label[data.length];
			Label [ ]perLabel=new Label[data.length];

			HBox []hbox=new HBox[data.length];
			VBox vbox=new VBox(10); 
			ScrollPane spn=new ScrollPane();
			double p=0;
			for(int i=0;i<data.length;i++) {
				p+=data[i].getPercentage();
			}
			
			double []alpah=new double[data.length];
			for(int i=0;i<data.length;i++) {
				total+=data[i].getPercentage();
				colorLabel[i]=new Label();
				colorLabel[i].setMinSize(60, 30);;
				colorLabel[i].setStyle(" -fx-background-color:" + "#" + decimalToHexTwoDigits((int)data[i].getrColor())
				+ decimalToHexTwoDigits((int) data[i].getgColor())
				+ decimalToHexTwoDigits((int) data[i].getbColor()) + ";");
				nameLabel[i]=new Label(data[i].getNameForData());
				nameLabel[i].setMinSize(60, 30);
				
				perLabel[i]=new Label(Math.floor((data[i].getPercentage()/p)*100)+"");
				perLabel[i].setMinSize(60, 30);
				perLabel[i].setMaxSize(60, 30);
				hbox[i]=new HBox(10);
				hbox[i].getChildren().addAll(nameLabel[i],colorLabel[i],perLabel[i]);
				vbox.getChildren().add(hbox[i]);
			}
			spn.setContent(vbox);
			root.setBottom(spn);;
			for(int i=0;i<data.length;i++) {
				array[i]=(int) data[i].getPercentage();
			}
			Arrays.sort(array);
			for(int i=0;i<data.length;i++) {
	       alpah[i]=(data[i].getPercentage()/total)*360;
	       System.out.println(data[i].getNameForData()+alpah[i]);
	       
			}
			Data d=new Data("x", 0);
			Data dFirst = null;
			Label title=new Label(welcomeGUI.getTitle());
			title.setStyle("-fx-font-size: 32pt;");
			
			root.setTop(title);
			root.setAlignment(title, Pos.CENTER);
				d.setbColor(0);
			d.setgColor(0);
			d.setbColor(255);
		for(int i=array.length-1;i>=0;i--) {
				for(int j=data.length-1;j>=0;j--) {
					
					if(array[i]==data[j].getPercentage()) {
						dFirst=data[j];
						break;
					}
				}	       

					drow(pane, hight/2, width/2, radius,4*(alpah[i]), 0, dFirst.getrColor(), dFirst.getgColor(),dFirst.getbColor());
					drow(pane, hight/2, width/2, 0.8*radius,4*(alpah[i]), 0, d.getrColor(), d.getgColor(),d.getbColor());
					radius=radius*0.7;

				//drow(pane, hight/2, width/2, 60,alpah[1], startAlpha, data[1].getrColor(), data[1].getgColor(),data[1].getbColor());
				//drow(pane, hight/2, width/2, 60,alpah[2], startAlpha, data[2].getrColor(), data[2].getgColor(),data[2].getbColor());
			
//		startAlpha+=alpah[i];
//		a+=alpah[i];
			}
		root.setCenter(pane);
			 
			
		}
	 
}
