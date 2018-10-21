package colors;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class ColorLabels extends HBox {
	Label text = new Label();
	Label color = new Label("");
	Label value = new Label();

	public ColorLabels(String str, String val) {
		this.setSpacing(10);
		this.getChildren().addAll(color, text, value);
		color.setMinSize(20, 20);
		color.setStyle(" -fx-background-color:" + "#" + val + ";");
		text.setText(str);
		value.setText("0");

	}

	public void setValue(String val) {
		if (val.contains("."))
			value.setText(val.substring(0, val.indexOf(".")));
		else
			value.setText(val);
	}
}
