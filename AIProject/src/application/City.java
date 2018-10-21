package application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

class City extends Circle implements Comparable<City>
{
    public  String name;
    public Edge[] adjacencies;
    public double minDistance = Double.POSITIVE_INFINITY;
    public City previous;
    public  String argName;
    public double gScores;
    public  double hScores;
    public double fHcores = 0;
    public int cityId;
    public int x;
    public int y;
    
 public City(String name) {
		super(7);
		this.name = name;
	}

City(String name,int cityId,int x,int y) {
		super(x,y,7);
		this.setFill(Color.BISQUE);
		this.x=x;
		this.y=y;
		
		this.name = name;
		this.cityId=cityId;
	}

	@Override
	public int compareTo(City arg0) {
		// TODO Auto-generated method stub
        return Double.compare(minDistance, arg0.minDistance);
	}
	public boolean haveAdjcent() {
		if(adjacencies.length==0 || adjacencies.length<0) {
			return false;
		}
		else {
			return true;
		}
	}

}
