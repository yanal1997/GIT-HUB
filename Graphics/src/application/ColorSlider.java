package application;

import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;


public class ColorSlider extends HBox{
	private float value;
	private float min,max,spaces,block;
	Slider slider = new Slider();
	Label label ;

	public ColorSlider(float min, float max, double d, double e, double f,String text) {
		super();
		this.min = min;
		this.max = max;
		this.spaces = (float) d;
		this.block = (float) e;
		this.value = (float) f;
		label=new Label(text);
		slider.setMin(min);
		slider.setMax(max); // max value
		slider.setValue(f); // initial value
		slider.setShowTickLabels(true);
		slider.setShowTickMarks(true);
		slider.setMajorTickUnit(d);// spaces
		slider.setBlockIncrement(e);
		Image blueImage=new Image("file:///C:/Users/yanal/OneDrive/Desktop/GIT%20HUB/Graphics/src/b.jpg");
		//ImageView imVBlue=new ImageView();
		//imVBlue.setImage(blueImage);
		this.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());

		this.getChildren().addAll(label,slider);
	}
	

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

}
