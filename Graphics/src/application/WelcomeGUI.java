package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class WelcomeGUI extends Application{
	static Data []data;
	static String title;
	static String dataType;
	ToggleGroup toggleGroup;
	ToggleGroup toggleGroup2;
	 CheckBox  autoChooseColorCheckBox;
	 int[] redArray=new int[255];
	 int[] greenArray=new int[255];
	 int[] blueArray=new int[255];

	
	public static void main(String[] args) {
		launch(args);
	}
	

	public WelcomeGUI() {
		super();
		// TODO Auto-generated constructor stub
	}


	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub

		Label welcomeLabel=new Label("");
		Label chooselabel=new Label("choose the color system");
		Label chooselabel2=new Label("choose the graph type");

		Label browesLabel=new Label("Choose input file");
		Button browes=new Button("Browes file");
		Button next=new Button("Next");
		 toggleGroup=new ToggleGroup();
		RadioButton rgbColor=new RadioButton("RGB");
		RadioButton cynColor=new RadioButton("CYN");
		RadioButton hsvColor=new RadioButton("HSV");
		toggleGroup2=new ToggleGroup();
		RadioButton piGraph=new RadioButton("Pi");
		RadioButton barGraph=new RadioButton("Bar");
		RadioButton cicleGraph=new RadioButton("Cicle");
		RadioButton doteGraph=new RadioButton("Dote");
		cicleGraph.setSelected(true);
		rgbColor.setSelected(true);
	    rgbColor.setToggleGroup(toggleGroup);
	    cynColor.setToggleGroup(toggleGroup);
	    hsvColor.setToggleGroup(toggleGroup);
	    piGraph.setToggleGroup(toggleGroup2);
	    barGraph.setToggleGroup(toggleGroup2);
	    cicleGraph.setToggleGroup(toggleGroup2);
	    doteGraph.setToggleGroup(toggleGroup2);
	    HBox hbox=new HBox(10);
	    hbox.getChildren().addAll(piGraph,barGraph,cicleGraph,doteGraph);

	      autoChooseColorCheckBox=new CheckBox("Choose random colors");
		BorderPane root =new BorderPane();
		HBox browseHBox=new HBox(40);
		HBox colorHbox=new HBox(10);
				VBox iteamVBox=new VBox(10);
		//iteamVBox.setAlignment(Pos.CENTER);
		colorHbox.setAlignment(Pos.CENTER);
		hbox.setAlignment(Pos.CENTER);
		root.setBottom(next);
		root.setTop(welcomeLabel);
		root.setAlignment(welcomeLabel, Pos.CENTER);
		root.setAlignment(autoChooseColorCheckBox, Pos.BASELINE_CENTER);
		root.setPadding(new Insets(10, 10, 10, 10));
		browseHBox.getChildren().addAll(browesLabel, browes);
		colorHbox.getChildren().addAll(rgbColor,cynColor,hsvColor);
		iteamVBox.getChildren().addAll(browseHBox,chooselabel,colorHbox,chooselabel2,hbox,autoChooseColorCheckBox);
		root.setCenter(iteamVBox);
		Scene scene = new Scene(root,300,300);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
		browes.setOnAction(e->{
			try {
				selectFile();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		});		root.setAlignment(next, Pos.CENTER);

		next.setOnAction(e->{
			try {
				System.out.println("hi");
				nextFunction();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				
			}
		});
	}



	private void  nextFunction() throws IOException {
		// TODO Auto-generated method stub
	String	choose= toggleGroup.getSelectedToggle().toString();
	String choose2= toggleGroup2.getSelectedToggle().toString();
	if(autoChooseColorCheckBox.isSelected()) {
		for(int i=0;i<data.length;i++) {
			int red =(int) Math.floor(Math.random()*255);
			int green =(int) Math.floor(Math.random()*255);
			int blue =(int) Math.floor(Math.random()*255);

			if(redArray[red]==0) {
				redArray[red]=red;
				data[i].setrColor(red);
				}else {
					while(redArray[red]!=0) {
						red =(int) Math.floor(Math.random()*255);
					}
					redArray[red]=red;
					
				}
			if(greenArray[green]==0) {
				greenArray[green]=green;
				data[i].setgColor(green);
				}else {
					while(redArray[green]!=0) {
						green =(int) Math.floor(Math.random()*255);
					}
					redArray[green]=green;
					
				}
			if(blueArray[blue]==0) {
				greenArray[blue]=blue;
				data[i].setbColor(blue);
				}else {
					while(blueArray[blue]!=0) {
						blue =(int) Math.floor(Math.random()*255);
					}
					redArray[blue]=blue;
					
				}
			
		}
	}
	 RGB rgb=new RGB(choose,choose2);

	
	}


	private void selectFile() throws IOException {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("choose file");
		fileChooser.setTitle("Open Resource File");
		 File file=fileChooser.showOpenDialog(null);
		 readFromTextFile(file);
		 dataType=getDataType(file);
		 title=getTitleForFile(file);
		
	}
	
	
	public void readFromTextFile(File file) throws IOException {
		
		String [] lines;
		int counter=0;
		BufferedReader bufferedReader=new BufferedReader(new FileReader(file));
		while(bufferedReader.readLine()!=null) {
			counter++;
			
		}
		bufferedReader.close();
		bufferedReader=new BufferedReader(new FileReader(file));
		lines=new String[counter];
		data=new Data[counter-2];
		for(int i=0;i<lines.length;i++) {
			lines[i]=bufferedReader.readLine();
			System.out.println(lines[i]);

		}
		bufferedReader.close();
		for(int i=2,j=0;i<lines.length;i++,j++) {
			String []s=lines[i].split(" ");
			
			data[j]=new Data(s[0], Double.parseDouble(s[1]));
		}
		 
	}
	
	public String getTitleForFile(File file) throws IOException {
		BufferedReader bufferedReader=new BufferedReader(new FileReader(file));
		String title =bufferedReader.readLine();
		bufferedReader.close();
		return title;
	}
	public String getDataType(File file) throws IOException {
		BufferedReader bufferedReader=new BufferedReader(new FileReader(file));
		bufferedReader.readLine();
		String dataType =bufferedReader.readLine();
		dataType=dataType.substring(0, dataType.indexOf(" "));
		bufferedReader.close();
		return dataType;
	}
	
	public Data [] getData() {

		return data; 
	}
	 public String getDatatype() {
		 return dataType;
	 }
	 public String  getTitle() {
		 return title;
	 }
	
	

}
