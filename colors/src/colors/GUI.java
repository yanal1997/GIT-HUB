package colors;

import java.time.Duration;

import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
//import test.BasicFrame;

public class GUI extends Application {
	static colors.Color color = new colors.Color();
	int i = 0;

	public void start(Stage window) throws Exception {
		BorderPane layout = new BorderPane();
		Scene scene = new Scene(layout, 1300, 800);
		window.setTitle("Colors project");
		// the above part
		HBox top = new HBox();
		VBox topCMY = new VBox();
		VBox topHSV = new VBox();
		VBox topRGB = new VBox();
		top.getChildren().addAll(topCMY, topHSV, topRGB);
		top.setAlignment(Pos.CENTER);

		// the bottom part
		BorderPane btm = new BorderPane();

		VBox btmCMY = new VBox();
		VBox btmHSV = new VBox();
		VBox btmRGB = new VBox();
		btm.setLeft(btmCMY);
		btm.setCenter(btmHSV);
		btm.setRight(btmRGB);
		// setting them to the layout
		layout.setTop(top);
		layout.setBottom(btm);

		// cmy top labels
		MySlider cSlader = new MySlider(0, 255, 0, 50, 5, "C", Color.getCyan());
		MySlider mSlader = new MySlider(0, 255, 0, 50, 5, "M", Color.getMagenta());
		MySlider ySlader = new MySlider(0, 255, 0, 50, 5, "Y", Color.getYellow());
		// rgb top labels
		MySlider rSlader = new MySlider(0, 255, 0, 50, 5, "R", Color.getCyan());
		MySlider gSlader = new MySlider(0, 255, 0, 50, 5, "G", Color.getGreen());
		MySlider bSlader = new MySlider(0, 255, 0, 50, 5, "B", Color.getBlue());
		// hsv top
		CircularButton hueCirle = new CircularButton();
		MySlider satSlader = new MySlider(0, 1, 0, 0.2, 0.1, "S", Color.getSaturation());
		MySlider valSlader = new MySlider(0, 1, 0, 0.2, 0.1, "V", Color.getValue());
		// Color label of rgb part
		Label ColoredLabel = new Label();

		layout.setPadding(new Insets(40));
		topCMY.setAlignment(Pos.CENTER);
		topCMY.setSpacing(10);
		topCMY.getChildren().addAll(cSlader, mSlader, ySlader);

		topRGB.getChildren().addAll(rSlader, gSlader, bSlader);
		topRGB.setAlignment(Pos.CENTER_RIGHT);
		topRGB.setSpacing(10);
		// VBox sv=new VBox();
		// sv.getChildren().addAll(satSlader,valSlader);
		// sv.setAlignment(Pos.CENTER_LEFT);
		topHSV.getChildren().addAll(new VBox(satSlader, valSlader), hueCirle);
		topHSV.setAlignment(Pos.BOTTOM_CENTER);
		topHSV.setSpacing(35);
		top.setSpacing(230);
		// hsv top
		// hue
		// topHSV.setPadding(new Insets(100, 0, 0, 250));
		btmHSV.setPadding(new Insets(0, 300, 0, 300));
		initializeColorsLabels(ColoredLabel, 250, 150);

		// rgb bottom part
		ColorLabels redLabels = new ColorLabels("Red", "0000FF");
		ColorLabels greenLabels = new ColorLabels("Green", "0000FF");
		ColorLabels blueLabels = new ColorLabels("Blue", "0000FF");
		btmRGB.getChildren().addAll(redLabels, greenLabels, blueLabels);
		btmRGB.setSpacing(20);
		// hsv bottom part
		ColorLabels hueLabels = new ColorLabels("Hue", "FFFFFF");
		ColorLabels satLabels = new ColorLabels("Satruation", "FFFFFF");
		ColorLabels valLabels = new ColorLabels("Value", "FFFFFF");
		btmHSV.setSpacing(20);
		btmHSV.getChildren().addAll(hueLabels, satLabels, valLabels);
		// hsv bottom part
		ColorLabels cLabels = new ColorLabels("Cyan", "00FFFF");
		ColorLabels mLabels = new ColorLabels("Magenta", "FF00FF");
		ColorLabels yLabels = new ColorLabels("Yellow", "FFFF00");
		btmCMY.setSpacing(20);
		btmCMY.getChildren().addAll(cLabels, mLabels, yLabels);
		layout.setCenter(ColoredLabel);
		BorderPane.setAlignment(ColoredLabel, Pos.BOTTOM_CENTER);
		btm.setPadding(new Insets(10, 10, 10, 10));

		hueCirle.setOnMouseDragged(e -> {
			hueCirle.handle(e.getX(), e.getY());
			hueLabels.setValue((hueCirle.getVal() + "").substring(0, 3));
			Color.setHSV(hueCirle.getVal(), Color.getSaturation(), Color.getValue());

			colors.Color.HSVToCMY();
			colors.Color.HSVToRGB();
			rSlader.setText(colors.Color.getBlue() + "");
			gSlader.setText(colors.Color.getGreen() + "");
			bSlader.setText(colors.Color.getBlue() + "");
			cSlader.setText(colors.Color.getCyan() + "");
			mSlader.setText(colors.Color.getMagenta() + "");
			ySlader.setText(colors.Color.getYellow() + "");
			initializeColorsLabels(ColoredLabel, 250, 150);
			// initializeColorsLabels(CMYColor, 200, 200);
			// initializeColorsLabels(HSVColor, 200, 200);
			setColorsPureLabels(redLabels, greenLabels, blueLabels, hueLabels, satLabels, valLabels, cLabels, mLabels,
					yLabels, cSlader, mSlader, ySlader, rSlader, gSlader, bSlader, satSlader, valSlader, ColoredLabel,
					redLabels);
			// initializeColorsLabels(ColoredLabel, 250, 150);

		});

		handleSliders(cSlader, mSlader, ySlader, rSlader, gSlader, bSlader, satSlader, valSlader, ColoredLabel,
				redLabels, greenLabels, blueLabels, hueLabels, satLabels, valLabels, cLabels, mLabels, yLabels,
				hueCirle);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		layout.setId("layout");

		window.setScene(scene);
		window.show();
	}

	private void initializeColorsLabels(Label ColorLabel, int width, int height) {
		ColorLabel.setStyle(" -fx-background-color:" + "#" + decimalToHexTwoDigits((int) colors.Color.getRed())
			+ decimalToHexTwoDigits((int) colors.Color.getGreen())
			+ decimalToHexTwoDigits((int) colors.Color.getBlue()) + ";");
		ColorLabel.setMinSize(width, height);
	}

	public static void main(String[] args) {
		Application.launch(args);
		// System.out.println(decimalToHexTwoDigits(15));
	}

	public void handleSliders(MySlider cSlader, MySlider mSlader, MySlider ySlader, MySlider rSlader, MySlider gSlader,
			MySlider bSlader, MySlider satSlader, MySlider valSlader, Label ColoredLabel, ColorLabels redLabels,
			ColorLabels greenLabels, ColorLabels blueLabels, ColorLabels hueLabels, ColorLabels satLabels,
			ColorLabels valLabels, ColorLabels cLabels, ColorLabels mLabels, ColorLabels yLabels,
			CircularButton hueCirle) {
		rSlader.slider.setOnMouseClicked(e -> {
			colors.Color.setRGB((float) rSlader.slider.getValue(), (float) gSlader.slider.getValue(),
					(float) bSlader.slider.getValue());
	Color.setHSV((float) hueCirle.getVal(), (float) satSlader.getValue(), (float) valSlader.getValue());
			colors.Color.RGBToCMY();
			colors.Color.RGBToHSV();
			
			cSlader.setText(colors.Color.getCyan() + "");
			satSlader.setText(colors.Color.getSaturation() + "");
			valSlader.setText(colors.Color.getValue() + "");
			System.out.println(Color.getSaturation());
			satLabels.setValue(Color.getSaturation()+"");
			initializeColorsLabels(ColoredLabel, 250, 150);
			setColorsPureLabels(redLabels, greenLabels, blueLabels, hueLabels, satLabels, valLabels, cLabels, mLabels,
					yLabels, cSlader, mSlader, ySlader, rSlader, gSlader, bSlader, satSlader, valSlader, ColoredLabel,
					redLabels);
			hueCirle.setVal(Color.getHue());

		});
		gSlader.slider.setOnMouseClicked(e -> {
			colors.Color.setRGB((float) rSlader.slider.getValue(), (float) gSlader.slider.getValue(),
					(float) bSlader.slider.getValue());
//			Color.setHSV((float) hueCirle.getVal(), (float) satSlader.getValue(), (float) valSlader.getValue());
			colors.Color.RGBToCMY();
			colors.Color.RGBToHSV();
			mSlader.setText(colors.Color.getMagenta() + "");
			satSlader.setText(colors.Color.getSaturation() + "");
			valSlader.setText(colors.Color.getValue() + "");
			initializeColorsLabels(ColoredLabel, 250, 150);
			// initializeColorsLabels(CMYColor, 200, 200);
			// initializeColorsLabels(HSVColor, 200, 200);
			setColorsPureLabels(redLabels, greenLabels, blueLabels, hueLabels, satLabels, valLabels, cLabels, mLabels,
					yLabels, cSlader, mSlader, ySlader, rSlader, gSlader, bSlader, satSlader, valSlader, ColoredLabel,
					redLabels);
			hueCirle.setVal(Color.getHue());

		});
		bSlader.slider.setOnMouseClicked(e -> {
			colors.Color.setRGB((float) (rSlader.slider.getValue()), (float) (gSlader.slider.getValue()),
					(float) (bSlader.slider.getValue()));
			Color.setHSV((float) hueCirle.getVal(), (float) satSlader.getValue(), (float) valSlader.getValue());
			colors.Color.RGBToCMY();
			colors.Color.RGBToHSV();
			ySlader.setText(colors.Color.getYellow() + "");
			satSlader.setText(colors.Color.getSaturation() + "");
			valSlader.setText(colors.Color.getValue() + "");
			initializeColorsLabels(ColoredLabel, 250, 150);
			// initializeColorsLabels(CMYColor, 200, 200);
			// initializeColorsLabels(HSVColor, 200, 200);
			setColorsPureLabels(redLabels, greenLabels, blueLabels, hueLabels, satLabels, valLabels, cLabels, mLabels,
					yLabels, cSlader, mSlader, ySlader, rSlader, gSlader, bSlader, satSlader, valSlader, ColoredLabel,
					redLabels);
			hueCirle.setVal(Color.getHue());

		});
		cSlader.slider.setOnMouseClicked(e -> {
			colors.Color.setCMY((float) cSlader.slider.getValue(), (float) mSlader.slider.getValue(),
					(float) ySlader.slider.getValue());
			// Color.setHSV((float)hueCirle.getVal(),
			// (float)satSlader.getValue(), (float)valSlader.getValue());
			colors.Color.CMYToHSV();
			colors.Color.CMYToRGB();
			rSlader.setText(colors.Color.getRed() + "");
			satSlader.setText(colors.Color.getSaturation() + "");
			valSlader.setText(colors.Color.getValue() + "");
			initializeColorsLabels(ColoredLabel, 250, 150);
			// initializeColorsLabels(CMYColor, 200, 200);
			// initializeColorsLabels(HSVColor, 200, 200);
			setColorsPureLabels(redLabels, greenLabels, blueLabels, hueLabels, satLabels, valLabels, cLabels, mLabels,
					yLabels, cSlader, mSlader, ySlader, rSlader, gSlader, bSlader, satSlader, valSlader, ColoredLabel,
					redLabels);
			hueCirle.setVal(Color.getHue());

		});
		mSlader.slider.setOnMouseClicked(e -> {
			colors.Color.setCMY((float) cSlader.slider.getValue(), (float) mSlader.slider.getValue(),
					(float) ySlader.slider.getValue());
			colors.Color.CMYToHSV();
			colors.Color.CMYToRGB();
			gSlader.setText(colors.Color.getGreen() + "");
			satSlader.setText(colors.Color.getSaturation() + "");
			valSlader.setText(colors.Color.getValue() + "");
			initializeColorsLabels(ColoredLabel, 250, 150);
			// initializeColorsLabels(CMYColor, 200, 200);
			// initializeColorsLabels(HSVColor, 200, 200);
			setColorsPureLabels(redLabels, greenLabels, blueLabels, hueLabels, satLabels, valLabels, cLabels, mLabels,
					yLabels, cSlader, mSlader, ySlader, rSlader, gSlader, bSlader, satSlader, valSlader, ColoredLabel,
					redLabels);
			hueCirle.setVal(Color.getHue());

		});
		ySlader.slider.setOnMouseClicked(e -> {
			colors.Color.setCMY((float) cSlader.slider.getValue(), (float) mSlader.slider.getValue(),
					(float) ySlader.slider.getValue());
			colors.Color.CMYToHSV();
			colors.Color.CMYToRGB();
			bSlader.setText(colors.Color.getBlue() + "");
			satSlader.setText(colors.Color.getSaturation() + "");
			valSlader.setText(colors.Color.getValue() + "");
			initializeColorsLabels(ColoredLabel, 250, 150);
			// initializeColorsLabels(CMYColor, 200, 200);
			// initializeColorsLabels(HSVColor, 200, 200);
			setColorsPureLabels(redLabels, greenLabels, blueLabels, hueLabels, satLabels, valLabels, cLabels, mLabels,
					yLabels, cSlader, mSlader, ySlader, rSlader, gSlader, bSlader, satSlader, valSlader, ColoredLabel,
					redLabels);
			hueCirle.setVal(Color.getHue());

		});
		satSlader.slider.setOnMouseClicked(e -> {
			colors.Color.setHSV(hueCirle.getVal(), (float) satSlader.slider.getValue(),
					(float) valSlader.slider.getValue());
			colors.Color.HSVToCMY();
			colors.Color.HSVToRGB();
			rSlader.setText(colors.Color.getRed() + "");
			gSlader.setText(colors.Color.getGreen() + "");
			bSlader.setText(colors.Color.getBlue() + "");
			cSlader.setText(colors.Color.getCyan() + "");
			mSlader.setText(colors.Color.getMagenta() + "");
			ySlader.setText(colors.Color.getYellow() + "");
			initializeColorsLabels(ColoredLabel, 250, 150);
			// initializeColorsLabels(CMYColor, 200, 200);
			// initializeColorsLabels(HSVColor, 200, 200);
			setColorsPureLabels(redLabels, greenLabels, blueLabels, hueLabels, satLabels, valLabels, cLabels, mLabels,
					yLabels, cSlader, mSlader, ySlader, rSlader, gSlader, bSlader, satSlader, valSlader, ColoredLabel,
					redLabels);
		//	hueCirle.setVal(Color.getHue());

		});
		valSlader.slider.setOnMouseClicked(e -> {
			colors.Color.setHSV(hueCirle.getVal(), (float) satSlader.slider.getValue(),
					(float) valSlader.slider.getValue());
			colors.Color.HSVToCMY();
			colors.Color.HSVToRGB();
			rSlader.setText(colors.Color.getRed() + "");
			gSlader.setText(colors.Color.getGreen() + "");
			bSlader.setText(colors.Color.getBlue() + "");
			cSlader.setText(colors.Color.getCyan() + "");
			mSlader.setText(colors.Color.getMagenta() + "");
			ySlader.setText(colors.Color.getYellow() + "");
			initializeColorsLabels(ColoredLabel, 250, 150);
			// initializeColorsLabels(CMYColor, 200, 200);
			// initializeColorsLabels(HSVColor, 200, 200);
			setColorsPureLabels(redLabels, greenLabels, blueLabels, hueLabels, satLabels, valLabels, cLabels, mLabels,
					yLabels, cSlader, mSlader, ySlader, rSlader, gSlader, bSlader, satSlader, valSlader, ColoredLabel,
					redLabels);
			hueCirle.setVal(Color.getHue());

		});
	}

	private void setColorsPureLabels(ColorLabels redLabels, ColorLabels greenLabels, ColorLabels blueLabels,
			ColorLabels hueLabels, ColorLabels satLabels, ColorLabels valLabels, ColorLabels cLabels,
			ColorLabels mLabels, ColorLabels yLabels, MySlider cSlader, MySlider mSlader, MySlider ySlader,
			MySlider rSlader, MySlider gSlader, MySlider bSlader, MySlider satSlader, MySlider valSlader,
			Label coloredLabel, ColorLabels redLabels2) {
	redLabels.setValue(Color.getRed() + "");
	greenLabels.setValue((Color.getGreen() + ""));
		blueLabels.setValue((Color.getBlue() + ""));

		cLabels.setValue((Color.getCyan() + ""));
		mLabels.setValue((Color.getMagenta() + ""));
		yLabels.setValue((Color.getYellow() + ""));
			System.out.println("hue"+Color.getHue());
		hueLabels.setValue((Color.getHue() + ""));
		satLabels.setValue((Color.getSatPercentage() + ""));
		valLabels.setValue((Color.getValPercentage() + ""));

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
