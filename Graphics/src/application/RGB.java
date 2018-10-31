package application;
	


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import javax.xml.ws.handler.MessageContext.Scope;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class RGB extends Application {
	File file;
	double []rgb;
	String choose;
	String choose2;


	public RGB(String choose,String choose2) {
		super();
		this.choose=choose;
		this.choose2=choose2;
		start(new Stage());
		// TODO Auto-generated constructor stub
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			WelcomeGUI welcomeGUI=new WelcomeGUI();
			BorderPane root = new BorderPane();
			Data []data=welcomeGUI.data;
			String dataType=welcomeGUI.getDatatype();
			if(choose.contains("RGB")) {
				rgbGui(root, data, dataType);

			}
			else if(choose.contains("CYN")) {
				cmyGui(root, data, dataType);
			}
			else if(choose.contains("HSV")) {
				HSVGui(root, data, dataType);
			}
			
			
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void rgbGui(BorderPane root, Data []data,String title) {
	    Label color=new Label();
	    color.setMinSize(120, 50);

		//this is right said to border pane
		ScrollPane  scrollPane=new ScrollPane ();
		VBox scrollPaneVBox=new VBox(10);
		HBox []arrayHBox=new HBox [data.length];
		Label []arrayLabelColor=new Label[data.length];
		Label []arrayLabel=new Label[data.length];
		HBox titleHBox=new HBox(10);//title =data type label+color label
		Label  dataTypeLabel=new Label(title);
		Label  colorLabel=new Label("Color");
		dataTypeLabel.setMinSize(60, 30);
		colorLabel.setMinSize(60, 30);
		titleHBox.getChildren().addAll(dataTypeLabel,colorLabel);
		scrollPaneVBox.getChildren().add(titleHBox);
		for(int i=0;i<data.length;i++) {
			arrayLabel[i]=new Label(data[i].getNameForData());
			arrayLabel[i].setMinSize(60, 30);
			arrayLabelColor[i]=new Label();
			arrayLabelColor[i].setMinSize(60, 30);//************************************************************
			arrayLabelColor[i].setStyle(" -fx-background-color:" + "#" + decimalToHexTwoDigits((int)data[i].getrColor())
			+ decimalToHexTwoDigits((int) data[i].getgColor())
			+ decimalToHexTwoDigits((int) data[i].getbColor()) + ";");
			arrayHBox[i]=new HBox(10);
			arrayHBox[i].getChildren().addAll(arrayLabel[i],arrayLabelColor[i]);
			scrollPaneVBox.getChildren().addAll(arrayHBox[i]);
		}
		scrollPane.setContent(scrollPaneVBox);
		scrollPane.setPadding(new Insets(10));
		root.setLeft(scrollPane);
		root.setPadding(new Insets(10, 10, 10, 10));
		//this to center said in border pane
		ColorSlider red=new ColorSlider(0, 255, 25, 5, 120, "RED");
		ColorSlider blue=new ColorSlider(0, 255, 25, 5, 120, "Blue");
		ColorSlider green=new ColorSlider(0, 255, 25, 5, 120, "Green");
		red.label.setMinSize(60, 30);
		green.label.setMinSize(60, 30);
		blue.label.setMinSize(60, 30);

		ComboBox<String> dataComboBox=new  ComboBox<>();
		for(int i=0; i<data.length;i++) {
			dataComboBox.getItems().add(data[i].getNameForData());
		}
		dataComboBox.setValue("choose item");
		ColorNumberControl redControl=new ColorNumberControl("RED");
		ColorNumberControl greenControl=new ColorNumberControl("Green");
		ColorNumberControl buleControl=new ColorNumberControl("Blue");
	//**********************	setPromptText("your location");
		redControl.textArea.appendText( red.getValue()+"");
		greenControl.textArea.appendText(green.getValue()+"");
		buleControl.textArea.appendText(blue.getValue()+"");

		red.slider.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				redControl.textArea.clear();
			int i=	(int) red.slider.getValue();
				redControl.textArea.appendText(i+"");
				color.setStyle(" -fx-background-color:" + "#" + decimalToHexTwoDigits((int)red.slider.getValue())
				+ decimalToHexTwoDigits((int) green.slider.getValue())
				+ decimalToHexTwoDigits((int) blue.slider.getValue()) + ";");
				
			}
			
		});
		green.slider.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				greenControl.textArea.clear();
			int i=	(int) green.slider.getValue();
			greenControl.textArea.appendText(i+"");
			color.setStyle(" -fx-background-color:" + "#" + decimalToHexTwoDigits((int)red.slider.getValue())
			+ decimalToHexTwoDigits((int) green.slider.getValue())
			+ decimalToHexTwoDigits((int) blue.slider.getValue()) + ";");
			
			}
			
		});
		blue.slider.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				buleControl.textArea.clear();
			int i=	(int) blue.slider.getValue();
			buleControl.textArea.appendText(i+"");
			color.setStyle(" -fx-background-color:" + "#" + decimalToHexTwoDigits((int)red.slider.getValue())
			+ decimalToHexTwoDigits((int) green.slider.getValue())
			+ decimalToHexTwoDigits((int) blue.slider.getValue()) + ";");
			
			}
			
		});
		redControl.textArea.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				red.slider.setValue(Double.parseDouble(redControl.textArea.getText()));
				color.setStyle(" -fx-background-color:" + "#" + decimalToHexTwoDigits((int)red.slider.getValue())
				+ decimalToHexTwoDigits((int) green.slider.getValue())
				+ decimalToHexTwoDigits((int) blue.slider.getValue()) + ";");
				
			}
		});
greenControl.textArea.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				green.slider.setValue(Double.parseDouble(greenControl.textArea.getText()));
		
				color.setStyle(" -fx-background-color:" + "#" + decimalToHexTwoDigits((int)red.slider.getValue())
				+ decimalToHexTwoDigits((int) green.slider.getValue())
				+ decimalToHexTwoDigits((int) blue.slider.getValue()) + ";");
				
			}
		});
buleControl.textArea.setOnAction(new EventHandler<ActionEvent>() {
	
	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		blue.slider.setValue(Double.parseDouble(buleControl.textArea.getText()));
		color.setStyle(" -fx-background-color:" + "#" + decimalToHexTwoDigits((int)red.slider.getValue())
		+ decimalToHexTwoDigits((int) green.slider.getValue())
		+ decimalToHexTwoDigits((int) blue.slider.getValue()) + ";");
		
	}
});
color.setStyle(" -fx-background-color:" + "#" + decimalToHexTwoDigits((int)red.slider.getValue())
+ decimalToHexTwoDigits((int) green.slider.getValue())
+ decimalToHexTwoDigits((int) blue.slider.getValue()) + ";");
	  //  color.setEditable(false);
		Button ok=new Button("ok");
		Button next=new Button("Next");
		VBox vBoxRGB=new VBox(10);
		next.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				try {
					CircleGraph cG=new CircleGraph(choose2);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		HBox h=new HBox(10);
		h.getChildren().addAll(next,ok);
		vBoxRGB.getChildren().addAll(dataComboBox,red,green,blue,redControl,greenControl,buleControl,color,h);
		root.setCenter(vBoxRGB);
		ok.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int index = 0;
				String type=dataComboBox.getValue();
				for(int i=0;i<data.length;i++) {
					if(type.equals(data[i].getNameForData())) {
						index=i;
						break;
					}
				}
				arrayLabelColor[index].setStyle(" -fx-background-color:" + "#" + decimalToHexTwoDigits((int)red.slider.getValue())
				+ decimalToHexTwoDigits((int) green.slider.getValue())
				+ decimalToHexTwoDigits((int) blue.slider.getValue()) + ";");
				data[index].setrColor((int)red.slider.getValue());
				data[index].setgColor((int)green.slider.getValue());
				data[index].setbColor((int)blue.slider.getValue());

				try {
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
		});
		
		
		

		
	}

	public static String decimalToHexTwoDigits(int num) {
		double res1 = 0;
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
	public void cmyGui(BorderPane root, Data []data,String title) {
	    Label color=new Label();
	    color.setMinSize(120, 50);

		//this is right said to border pane
		ScrollPane  scrollPane=new ScrollPane ();
		VBox scrollPaneVBox=new VBox(10);
		HBox []arrayHBox=new HBox [data.length];
		Label []arrayLabelColor=new Label[data.length];
		Label []arrayLabel=new Label[data.length];
		HBox titleHBox=new HBox(10);//title =data type label+color label
		Label  dataTypeLabel=new Label(title);
		Label  colorLabel=new Label("Color");
		dataTypeLabel.setMinSize(60, 30);
		colorLabel.setMinSize(60, 30);
		titleHBox.getChildren().addAll(dataTypeLabel,colorLabel);
		scrollPaneVBox.getChildren().add(titleHBox);
		for(int i=0;i<data.length;i++) {
			arrayLabel[i]=new Label(data[i].getNameForData());
			arrayLabel[i].setMinSize(60, 30);
			arrayLabelColor[i]=new Label();
			arrayLabelColor[i].setMinSize(60, 30);
			arrayLabelColor[i].setStyle(" -fx-background-color:" + "#" + decimalToHexTwoDigits((int)data[i].getrColor())
			+ decimalToHexTwoDigits((int) data[i].getgColor())
			+ decimalToHexTwoDigits((int) data[i].getbColor()) + ";");
			arrayHBox[i]=new HBox(10);
			arrayHBox[i].getChildren().addAll(arrayLabel[i],arrayLabelColor[i]);
			scrollPaneVBox.getChildren().addAll(arrayHBox[i]);
		}
		scrollPane.setContent(scrollPaneVBox);
		scrollPane.setPadding(new Insets(10));
		root.setLeft(scrollPane);
		root.setPadding(new Insets(10, 10, 10, 10));
		//this to center said in border pane
		ColorSlider c=new ColorSlider(0, 255, 25, 5, 120, "C");
		ColorSlider y=new ColorSlider(0, 255, 25, 5, 120, "Y");
		ColorSlider m=new ColorSlider(0, 255, 25, 5, 120, "M");
		ComboBox<String> dataComboBox=new  ComboBox<>();
		for(int i=0; i<data.length;i++) {
			dataComboBox.getItems().add(data[i].getNameForData());
		}
		dataComboBox.setValue("choose item");
		ColorNumberControl cControl=new ColorNumberControl("C");
		ColorNumberControl mControl=new ColorNumberControl("M");
		ColorNumberControl yControl=new ColorNumberControl("U");
	//**********************	setPromptText("your location");
		cControl.textArea.appendText( c.getValue()+"");
		mControl.textArea.appendText(m.getValue()+"");
		yControl.textArea.appendText(y.getValue()+"");

		c.slider.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				cControl.textArea.clear();
			int i=	(int) c.slider.getValue();
				cControl.textArea.appendText(i+"");
				color.setStyle(" -fx-background-color:" + "#" + decimalToHexTwoDigits((int)RGBToCMY(c.slider.getValue()))
				+ decimalToHexTwoDigits((int)RGBToCMY( m.slider.getValue()))
				+ decimalToHexTwoDigits((int)RGBToCMY( y.slider.getValue())) + ";");
				
			}
			
		});
		m.slider.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				mControl.textArea.clear();
			int i=	(int) m.slider.getValue();
			mControl.textArea.appendText(i+"");
			color.setStyle(" -fx-background-color:" + "#" + decimalToHexTwoDigits((int)RGBToCMY(c.slider.getValue()))
			+ decimalToHexTwoDigits((int)RGBToCMY( m.slider.getValue()))
			+ decimalToHexTwoDigits((int)RGBToCMY( y.slider.getValue())) + ";");
			
			}
			
		});
		y.slider.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				yControl.textArea.clear();
			int i=	(int) y.slider.getValue();
			yControl.textArea.appendText(i+"");
			color.setStyle(" -fx-background-color:" + "#" + decimalToHexTwoDigits((int)RGBToCMY(c.slider.getValue()))
			+ decimalToHexTwoDigits((int)RGBToCMY( m.slider.getValue()))
			+ decimalToHexTwoDigits((int)RGBToCMY( y.slider.getValue())) + ";");
			
			}
			
		});
		cControl.textArea.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				c.slider.setValue(Double.parseDouble(cControl.textArea.getText()));
				color.setStyle(" -fx-background-color:" + "#" + decimalToHexTwoDigits((int)RGBToCMY(c.slider.getValue()))
				+ decimalToHexTwoDigits((int)RGBToCMY( m.slider.getValue()))
				+ decimalToHexTwoDigits((int)RGBToCMY( y.slider.getValue())) + ";");
				
			}
		});
mControl.textArea.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				m.slider.setValue(Double.parseDouble(mControl.textArea.getText()));
		
				color.setStyle(" -fx-background-color:" + "#" + decimalToHexTwoDigits((int)RGBToCMY(c.slider.getValue()))
				+ decimalToHexTwoDigits((int)RGBToCMY( m.slider.getValue()))
				+ decimalToHexTwoDigits((int)RGBToCMY( y.slider.getValue())) + ";");
			}
		});
yControl.textArea.setOnAction(new EventHandler<ActionEvent>() {
	
	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		y.slider.setValue(Double.parseDouble(yControl.textArea.getText()));
		color.setStyle(" -fx-background-color:" + "#" + decimalToHexTwoDigits((int)RGBToCMY(c.slider.getValue()))
		+ decimalToHexTwoDigits((int)RGBToCMY( m.slider.getValue()))
		+ decimalToHexTwoDigits((int)RGBToCMY( y.slider.getValue())) + ";");
		
	}
});
color.setStyle(" -fx-background-color:" + "#" + decimalToHexTwoDigits((int)RGBToCMY(c.slider.getValue()))
+ decimalToHexTwoDigits((int)RGBToCMY( m.slider.getValue()))
+ decimalToHexTwoDigits((int)RGBToCMY( y.slider.getValue())) + ";");
	  //  color.setEditable(false);
		Button ok=new Button("ok");
		Button next=new Button("Next");
		VBox vBoxRGB=new VBox(10);
		next.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				try {
					CircleGraph cG=new CircleGraph(choose2);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		HBox h=new HBox(10);
		h.getChildren().addAll(next,ok);
		vBoxRGB.getChildren().addAll(dataComboBox,c,m,y,cControl,mControl,yControl,color,h);
		root.setCenter(vBoxRGB);
		ok.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int index = 0;
				String type=dataComboBox.getValue();
				for(int i=0;i<data.length;i++) {
					if(type.equals(data[i].getNameForData())) {
						index=i;
						break;
					}
				}
				arrayLabelColor[index].setStyle(" -fx-background-color:" + "#" + decimalToHexTwoDigits((int)RGBToCMY(c.slider.getValue()))
				+ decimalToHexTwoDigits((int)RGBToCMY( m.slider.getValue()))
				+ decimalToHexTwoDigits((int)RGBToCMY( y.slider.getValue())) + ";");
				data[index].setrColor((int)RGBToCMY(c.slider.getValue()));
				data[index].setgColor((int)RGBToCMY(m.slider.getValue()));
				data[index].setbColor((int)RGBToCMY(y.slider.getValue()));

				try {
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
		});
		
		
		

		
	}
	public  double RGBToCMY(double color) {
		return	color=255-color;
		}
	public  double[] HSVToRGB(double saturation,double hue,double value) {
		double red = 0,green=0,blue=0;
		double max = value;
		double min = max * (1 - saturation);
		double mid = 0;
		int intValue = (int) Math.floor(hue / 60);
		double alpha = hue / 60 - intValue;
		if (intValue % 2 == 1)
			alpha = 360 - alpha;
		mid = min + alpha * (max - min);
		switch (intValue) {
		case 0:
			red = max;
			green = mid;
			blue = min;
			break;
		case 1:
			red = mid;
			green = max;
			blue = min;
			break;
		case 2:
			red = min;
			green = max;
			blue = mid;
			break;
		case 3:
			red =min;
			green = mid;
			blue = max;
			break;
		case 4:
			red = mid;
			green = min;
			blue = max;
			break;
		case 5:
			red = max;
			green = min;
			blue = mid;
			break;
					}
		
		double []array={red,green,blue};
		System.out.println("a0"+array[0]);
		System.out.println("a1"+array[1]);

		System.out.println("a2"+array[2]);

		return array;
	}
	public void HSVGui(BorderPane root, Data []data,String title) {
	    Label color=new Label();
	    color.setMinSize(120, 50);

		//this is right said to border pane
		ScrollPane  scrollPane=new ScrollPane ();
		VBox scrollPaneVBox=new VBox(10);
		HBox []arrayHBox=new HBox [data.length];
		Label []arrayLabelColor=new Label[data.length];
		Label []arrayLabel=new Label[data.length];
		HBox titleHBox=new HBox(10);//title =data type label+color label
		Label  dataTypeLabel=new Label(title);
		Label  colorLabel=new Label("Color");
		dataTypeLabel.setMinSize(60, 30);
		colorLabel.setMinSize(60, 30);
		titleHBox.getChildren().addAll(dataTypeLabel,colorLabel);
		scrollPaneVBox.getChildren().add(titleHBox);
		for(int i=0;i<data.length;i++) {
			arrayLabel[i]=new Label(data[i].getNameForData());
			arrayLabel[i].setMinSize(60, 30);
			arrayLabelColor[i]=new Label();
			arrayLabelColor[i].setMinSize(60, 30);
			arrayLabelColor[i].setStyle(" -fx-background-color:" + "#" + decimalToHexTwoDigits((int)data[i].getrColor())
			+ decimalToHexTwoDigits((int) data[i].getgColor())
			+ decimalToHexTwoDigits((int) data[i].getbColor()) + ";");
			arrayHBox[i]=new HBox(10);
			arrayHBox[i].getChildren().addAll(arrayLabel[i],arrayLabelColor[i]);
			scrollPaneVBox.getChildren().addAll(arrayHBox[i]);
		}
		scrollPane.setContent(scrollPaneVBox);
		scrollPane.setPadding(new Insets(10));
		root.setLeft(scrollPane);
		root.setPadding(new Insets(10, 10, 10, 10));
		//this to center said in border pane
		ColorSlider hue=new ColorSlider(0, 360, 25, 5, 200, "hue");
		ColorSlider value=new ColorSlider(0, 1, 0.2, 0.2, 0.5, "vale");
		ColorSlider saturation=new ColorSlider(0, 1, 0.2, 0.2, 0.5, "saturation");
		ComboBox<String> dataComboBox=new  ComboBox<>();
		for(int i=0; i<data.length;i++) {
			dataComboBox.getItems().add(data[i].getNameForData());
		}
		dataComboBox.setValue("choose item");
		ColorNumberControl hueControl=new ColorNumberControl("Hue");
		ColorNumberControl valueControl=new ColorNumberControl("Value");
		ColorNumberControl saturationControl=new ColorNumberControl("Saturation");
	//**********************	setPromptText("your location");
		hueControl.textArea.appendText( hue.getValue()+"");
		valueControl.textArea.appendText(value.getValue()+"");
		saturationControl.textArea.appendText(saturation.getValue()+"");

		hue.slider.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				hueControl.textArea.clear();
			int i=	(int) hue.slider.getValue();
				hueControl.textArea.appendText(i+"");
				 rgb=HSVToRGB(saturation.slider.getValue(), hue.slider.getValue(), value.slider.getValue());
				color.setStyle(" -fx-background-color:" + "#" + rgbToString(rgb[0],rgb[1],rgb[2])+ ";");
				
			}
			
		});
		saturation.slider.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				saturationControl.textArea.clear();
			int i=	(int) saturation.slider.getValue();
			saturationControl.textArea.appendText(i+"");
			 rgb=HSVToRGB(saturation.slider.getValue(), hue.slider.getValue(), value.slider.getValue());
			color.setStyle(" -fx-background-color:" + "#" + rgbToString(rgb[0],rgb[1],rgb[2])+ ";");
			}
			
		});
		value.slider.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				valueControl.textArea.clear();
			int i=	(int) value.slider.getValue();
			valueControl.textArea.appendText(i+"");
			 rgb=HSVToRGB(saturation.slider.getValue(), hue.slider.getValue(), value.slider.getValue());
			color.setStyle(" -fx-background-color:" + "#" + rgbToString(rgb[0],rgb[1],rgb[2])+ ";");
			}
			
		});
		hueControl.textArea.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				hue.slider.setValue(Double.parseDouble(hueControl.textArea.getText()));
				 rgb=HSVToRGB(saturation.slider.getValue(), hue.slider.getValue(), value.slider.getValue());
				color.setStyle(" -fx-background-color:" + "#" + rgbToString(rgb[0],rgb[1],rgb[2])+ ";");
			}
		});
saturationControl.textArea.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				saturation.slider.setValue(Double.parseDouble(saturationControl.textArea.getText()));
				 rgb=HSVToRGB(saturation.slider.getValue(), hue.slider.getValue(), value.slider.getValue());

				color.setStyle(" -fx-background-color:" + "#" + rgbToString(rgb[0],rgb[1],rgb[2])+ ";");
			}
		});
valueControl.textArea.setOnAction(new EventHandler<ActionEvent>() {
	
	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		value.slider.setValue(Double.parseDouble(valueControl.textArea.getText()));
		 rgb=HSVToRGB(saturation.slider.getValue(), hue.slider.getValue(), value.slider.getValue());

		color.setStyle(" -fx-background-color:" + "#" + rgbToString(rgb[0],rgb[1],rgb[2])+ ";");
		
	}
});
 rgb=HSVToRGB(saturation.slider.getValue(), hue.slider.getValue(), value.slider.getValue());

color.setStyle(" -fx-background-color:" + "#" + rgbToString(rgb[0],rgb[1],rgb[2])+ ";");
	  //  color.setEditable(false);
		Button ok=new Button("ok");
		Button next=new Button("Next");
		VBox vBoxRGB=new VBox(10);
		next.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				try {
					CircleGraph cG=new CircleGraph(choose2);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		HBox h=new HBox(10);
		h.getChildren().addAll(next,ok);
		vBoxRGB.getChildren().addAll(dataComboBox,hue,saturation,value,hueControl,saturationControl,valueControl,color,h);
		root.setCenter(vBoxRGB);
		ok.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int index = 0;
				String type=dataComboBox.getValue();
				for(int i=0;i<data.length;i++) {
					if(type.equals(data[i].getNameForData())) {
						index=i;
						break;
					}
				}
				arrayLabelColor[index].setStyle(" -fx-background-color:" + "#" + rgbToString(rgb[0],rgb[1],rgb[2])+ ";");
				data[index].setColor(" -fx-background-color:" + "#" + rgbToString(rgb[0],rgb[1],rgb[2])+ ";");
				try {
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
		});
		
		
		

		
	}
	public static String rgbToString(double rgb, double rgb2, double rgb3) {
	    String rs = Integer.toHexString((int)(rgb * 256));
	    String gs = Integer.toHexString((int)(rgb2 * 256));
	    String bs = Integer.toHexString((int)(rgb3 * 256));
	    return rs + gs + bs;
	}

}
