package colors;

import java.text.Format;
import java.text.NumberFormat;

import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;

public class MySlider extends HBox {
	Slider slider = new Slider();
	Label text = new Label();

	double value;

	public MySlider(int min, int max, double initVal, double spaces, double blockInc, String st, float value) {
		slider.setMin(min);
		slider.setMax(max); // max value
		slider.setValue(initVal); // initial value
		slider.setShowTickLabels(true);
		slider.setShowTickMarks(true);
		slider.setMajorTickUnit(spaces);// spaces
		slider.setBlockIncrement(blockInc);
		if(st.equalsIgnoreCase("c"))
			this.setId("c");
		else if(st.equalsIgnoreCase("m"))
			this.setId("m");
		else if(st.equalsIgnoreCase("y"))
			this.setId("y");
		else if(st.equalsIgnoreCase("r"))
			this.setId("r");
		else if(st.equalsIgnoreCase("g"))
			this.setId("g");
		else if(st.equalsIgnoreCase("b"))
			this.setId("b");
		else if(st.equalsIgnoreCase("s"))
			this.setId("s");
		else if(st.equalsIgnoreCase("v"))
			this.setId("v");
		text.textProperty().bindBidirectional(slider.valueProperty(), (NumberFormat.getNumberInstance()));
		this.setSpacing(10);
		text.setPrefWidth(60);
		this.value = value;
		text.setText((value+"").substring(0, 3));
	//	Label txtLabel = new Label(String.format("%4s", st));
		this.getChildren().addAll( slider, text);
		

	}

	public String getText() {
		return text.getText();
	}

	public void setText(String str) {
		this.value=Float.parseFloat(str);
		if(str.contains("."))
		this.text.setText(str.substring(0,str.indexOf(".")));
		else
			this.text.setText(str);
	}

	public double getValue() {
		return value;
	}
}
