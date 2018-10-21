package application;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class ColorNumberControl extends HBox {
	TextField textArea = new TextField();
	Label label;

	public ColorNumberControl(String textForLabel) {
		super();
		// TODO Auto-generated constructor stub
		textArea.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// TODO Auto-generated method stub
				int counter = 0;
				for (int i = 0; i < newValue.length(); i++) {
					if (Character.isDigit(newValue.charAt(i)) || newValue.charAt(i) == '.') {
						counter++;
					}
				}
				if (!newValue.equals("")) {
					if (counter == newValue.length()) {

						if (Double.parseDouble(newValue) < 0) {
							textArea.setText("0");
						} else if (Double.parseDouble(newValue) > 255) {
							textArea.setText("255");
						}
					} else {
						textArea.setText("0");

					}
				}
			}
		});
		label = new Label(textForLabel);
		textArea.setMaxSize(60, 30);
		label.setMinSize(60, 30);
		this.getChildren().addAll(label, textArea);

	}

}
