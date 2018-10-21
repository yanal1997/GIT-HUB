package colors;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class CircularButton extends StackPane {
	Line curser = new Line(0, 0, 130, 0);
	Circle head = new Circle(65, 0, 5, Color.NAVAJOWHITE);
	Label valueLabel = new Label("Hue = 0");
	private float val = 0;
	Line line1 = new Line(80, 0, 80, 170);

	public CircularButton() {
		this.getChildren().addAll( curser, line1, head, valueLabel);
		line1.setVisible(false);
		line1.setStroke(Color.ORANGE);
		curser.setTranslateX(0);
		curser.setStroke(Color.BLACK);
		line1.setTranslateX(70);
		head.setTranslateX(65);

		valueLabel.setTranslateX(130);
		valueLabel.setTranslateY(55);
		this.setId("h");

	}

	public void handle(double x, double y) {
		double yAxis = -y + 78;
		double xAxis = x - 65;
		double theta = Math.atan2(yAxis, xAxis);
		setTheta(theta);
		setVal((int) Math.toDegrees(theta));
	}

	public void setTheta(double theta) {
		this.curser.setEndX((int) 130 * Math.cos(-theta));
		this.curser.setEndY((int) 130 * Math.sin(-theta));
		this.head.setTranslateX(this.curser.getEndX() / 2);
		this.head.setTranslateY(this.curser.getEndY() / 2);
		this.curser.setTranslateX(0);
	}

	public float getVal() {
		return val;
	}

	public void setVal(float val) {
		System.out.println(val);
		while (val < 0)
			val += 360;
		if (val > 360)
			val = val % 360;
		this.val = val;
		this.valueLabel.setText("Hue = " + (val+"").substring(0, (val+"").indexOf(".")));
		colors.Color.setHSV(this.val, colors.Color.getSaturation(), colors.Color.getValue());
		setTheta(Math.toRadians(colors.Color.getHue()));
	}
}
