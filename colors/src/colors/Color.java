package colors;

import java.util.Arrays;

public class Color {
	private static float red = 0;
	private static float green = 0;
	private static float blue = 0;

	private static float cyan = 1;
	private static float magenta = 1;
	private static float yellow = 1;

	private static float hue = 0;
	private static float saturation = 0;
	private static float value = 0;

	public Color() {
	}
	
	public static float getRed() {
		return red*255;
	}

	public static float getGreen() {
		return green*255;
	}

	public static float getBlue() {
		return blue*255;
	}

	public static float getCyan() {
		return cyan*255;
	}

	public static float getMagenta() {
		return magenta*255;
	}

	public static float getYellow() {
		return yellow*255;
	}

	public static float getHue() {
		return hue;
	}

	public static float getSaturation() {
		return saturation;
	}

	public static float getValue() {
		return value;
	}

	public static void setRGB(float red, float green, float blue) {
		float max = 0;
		red = red / 255;
		green = green / 255;
		blue = blue / 255;

		if (red > 1 || green > 1 || blue > 1) {
			max = Math.max(red, Math.max(green, blue));
			Color.blue = blue / max;
			Color.red = red / max;
			Color.green = green / max;
		} else {
			Color.blue = blue;
			Color.red = red;
			Color.green = green;
		}
		if (red < 0 || green < 0 || blue < 0) {
			if (red < 0)
				Color.red = 0;
			if (green < 0)
				Color.green = 0;
			if (blue < 0)
				Color.blue = 0;
		}
	}

	public static void setCMY(float cyan, float magenta, float yellow) {
		float max = 0;
		if (cyan > 1 || magenta > 1 || yellow > 1) {
			max = Math.max(cyan, Math.max(magenta, yellow));
			Color.cyan = cyan / max;
			Color.magenta = magenta / max;
			Color.yellow = yellow / max;
		} else {
			Color.cyan = cyan;
			Color.magenta = magenta;
			Color.yellow = yellow;
		}
		if (red < 0 || green < 0 || blue < 0) {
			if (red < 0)
				Color.red = 0;
			if (green < 0)
				Color.green = 0;
			if (blue < 0)
				Color.blue = 0;
		}
	}

	public static void setHSV(float hue, float satruation, float value) {
		while (hue < 0)
			hue += 360;
		if (hue > 360)
			hue = hue % 360;
		Color.hue = hue;

		if (satruation < 0)
			satruation = 0;
		else if (satruation > 1)
			satruation = 1;
		Color.saturation = satruation;

		if (value > 1)
			value = 1;
		else if (value < 0)
			value = 0;
		Color.value = value;
	}

	public static void RGBToCMY() {
		Color.magenta = 1 - Color.green;
		Color.cyan = 1 - Color.red;
		Color.yellow = 1 - Color.blue;
	}

	public static void CMYToRGB() {
		Color.green = 1 - Color.magenta;
		Color.red = 1 - Color.cyan;
		Color.blue = 1 - Color.yellow;
	}
	public static void RGBToHSV() {
		float max = Math.max(Color.red, Math.max(Color.green, Color.blue));
		float min = Math.min(Color.red, Math.min(Color.green, Color.blue));
		float mid = Mid(Color.red, Color.blue, Color.green);

		Color.value = max;
		if (Color.value == 0) {
			Color.hue = 0;
			Color.saturation = 0;
		} else {
			Color.saturation = (max - min) / max;
			if (Color.saturation == 0)
				Color.hue = 0;
			else {
				float alpha = 60 * (mid - min) / (max - min);
				if (max == Color.red && min == Color.blue)
					Color.hue = alpha;
				else if (max == Color.green && min == Color.blue)
					Color.hue = 120 - alpha;
				else if (max == Color.green && min == Color.red)
					Color.hue = alpha + 120;
				else if (max == Color.blue && min == Color.red)
					Color.hue = 240 - alpha;
				else if (max == Color.blue && min == Color.green)
					Color.hue = alpha + 240;
				else
					Color.hue = 360 - alpha;
			}

		}
//		System.out.println("sat"+getSaturation());
//		System.out.println("val"+getValue());
	}
	public static void HSVToRGB() {
		float max = Color.value;
		float min = max * (1 - Color.saturation);
		float mid = 0;
		int intValue = (int) Math.floor(Color.hue / 60);
		float alpha = Color.hue / 60 - intValue;
		if (intValue % 2 == 1)
			alpha = 1 - alpha;
		mid = min + alpha * (max - min);
		switch (intValue) {
		case 0:
			Color.red = max;
			Color.green = mid;
			Color.blue = min;
			break;
		case 1:
			Color.red = mid;
			Color.green = max;
			Color.blue = min;
			break;
		case 2:
			Color.red = min;
			Color.green = max;
			Color.blue = mid;
			break;
		case 3:
			Color.red =min;
			Color.green = mid;
			Color.blue = max;
			break;
		case 4:
			Color.red = mid;
			Color.green = min;
			Color.blue = max;
			break;
		case 5:
			Color.red = max;
			Color.green = min;
			Color.blue = mid;
			break;
					}
	}
	public static void HSVToCMY(){
		Color.HSVToRGB();
		Color.RGBToCMY();
	}
	public static void CMYToHSV(){
		Color.CMYToRGB();
		Color.RGBToHSV();
	}
	
	private static float Mid(float red2, float blue2, float green2) {
		float[] rgb = new float[3];
		rgb[0] = red2;
		rgb[1] = green2;
		rgb[2] = blue2;
		Arrays.sort(rgb);
		return rgb[1];
	}

	public static float getRedPercentage() {
		return Color.red;
	}

	public static float getGreenPercentage() {
		return Color.green;
	}

	public static float getBluePercentage() {
		return Color.blue;
	}

	public static float getCyanPercentage() {
		return Color.cyan;
	}

	public static float getMPercentage() {
		return Color.magenta;
	}

	public static float getYPercentage() {
		return Color.yellow;
	}

	public static float getHuePercentage() {
		return Color.hue;
	}

	public static float getSatPercentage() {
		return Color.saturation;
	}

	public static float getValPercentage() {
		return Color.value;
	}
}
