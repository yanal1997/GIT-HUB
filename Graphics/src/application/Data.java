package application;

public class Data {
	private String nameForData;
	private double percentage;
	private double rColor;
	private double gColor;
	private double bColor;
	private String color;
	public Data(String nameForData, double percentage) {
		super();
		this.nameForData = nameForData;
		this.percentage = percentage;
	}
	public String getNameForData() {
		return nameForData;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public void setNameForData(String nameForData) {
		this.nameForData = nameForData;
	}
	public double getPercentage() {
		return percentage;
	}
	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}
	public double getrColor() {
		return rColor;
	}
	public void setrColor(double rColor) {
		this.rColor = rColor;
	}
	public double getgColor() {
		return gColor;
	}
	public void setgColor(double gColor) {
		this.gColor = gColor;
	}
	public double getbColor() {
		return bColor;
	}
	public void setbColor(double bColor) {
		this.bColor = bColor;
	}

}
