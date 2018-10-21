package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class Greedy {
	int aStarTime=10;
	int greedyTime=10;
	City []arryaCity;
	Queue <City>cities=new LinkedList<City>();
	public Greedy(City []array) {
		super();
		this.arryaCity=array;
		// TODO Auto-generated constructor stub
	}

	public  Queue computePaths(City source,City goale) {
		source.minDistance = 0.;
		PriorityQueue<City> vertexQueue = new PriorityQueue<City>();
		

		source=getCityInformationByCityName(source.name);
		greedyTime+=10;
		vertexQueue.add(source);
		cities.add(source);
		while (!vertexQueue.isEmpty()) {
			if(source.cityId==goale.cityId) {
				greedyTime+=10;

				break;
			}
			City u = vertexQueue.poll();
			u=getCityInformationByCityName(u.name);
			if(u.name.equals("name not found")) {
				greedyTime+=10;

				System.out.println( u.name);
			}
			// Visit each edge exiting u
			for (int i=0;i<u.adjacencies.length;i++) {//Edge e : u.adjacencies) {
				City newCity=getCityInformationByCityName(u.adjacencies[i].goal.name);
				double weight = u.adjacencies[i].weight;
				double distanceThroughU = u.minDistance + weight;
				if (distanceThroughU < newCity.minDistance) {
					greedyTime+=10;
vertexQueue.remove(newCity );

					newCity.minDistance = distanceThroughU;
					newCity.previous = u;
					vertexQueue.add(newCity);
					cities.add(newCity);

					if(newCity.cityId==goale.cityId) {
						greedyTime+=10;

						source=goale;
						break;
					}
				}
			}
		}
		return cities;
	}

	public void AstarSearch(City source, City goal) {
		calculatH(goal);
		aStarTime+=10;
		Set<City> explor = new HashSet<City>();
		PriorityQueue<City> queue = new PriorityQueue<City>(20, new Comparator<City>() {

			@Override
			public int compare(City o1, City o2) {
				// TODO Auto-generated method stub
				if (o1.fHcores > o2.fHcores) {
					return 1;
				} else if (o1.fHcores < o2.fHcores) {
					return -1;
				} else {
					return 0;
				}
			}
		});
		source.gScores = 0;
		source=getCityInformationByCityName(source.name);
		System.out.println("source name "+source.name);
		queue.add(source);
		boolean found = false;
		while ((!queue.isEmpty()) && (!found)) {
			aStarTime+=10;
			City current = queue.poll();
			current=getCityInformationByCityName(current.name);
			explor.add(current);
			if (current.name.equals(goal.name)) {
				aStarTime+=10;
				found = true;
			}
			/*
			 * if child node has been evaluated and the newer f_score is higher, skip
			 */
			 for(int i=0; i<current.adjacencies.length;i++){
				 Edge edge=current.adjacencies[i];
				 City n=edge.goal;
                 City child = edge.goal;
                 child=getCityInformationByCityName(child.name);
                 if(child.name.equals("name not found")) {
                	 aStarTime+=10;
                	 System.out.println("current "+current.name);
                	 System.out.println("adjecnt "+n.name);
                	 
                 }
                 double cost = edge.weight;
                 double temp_g_scores = current.gScores + cost;
                 double temp_f_scores = temp_g_scores + child.hScores;

                 /*if child node has been evaluated and 
                 the newer f_score is higher, skip*/
                

                 if((explor.contains(child)) && (temp_f_scores >= child.fHcores)){
                	 aStarTime+=10;
                         continue;
                 }

                 /*else if child node is not in queue or 
                 newer f_score is lower*/
                 
                 else if((!queue.contains(child)) || 
                         (temp_f_scores < child.fHcores)){
                	 aStarTime+=10;

                         child.previous = current;
                         child.gScores = temp_g_scores;
                         child.fHcores = temp_f_scores;

                         if(queue.contains(child)){
                        	 aStarTime+=10;

                                 queue.remove(child);
                         }
                         

                         queue.add(child);
                    	


                 }

         }

 }

}


	 public static List<City> getPath(City target){
         List<City> path = new ArrayList<City>();
 
 for(City node = target; node!=null; node = node.previous){
     path.add(node);
 }

 Collections.reverse(path);

 return path;
 }
	 private City getCityInformationByCityName(String cityName) {
		 for(int i=0;i<arryaCity.length;i++) {
			 if(arryaCity[i].name.equals(cityName)) {
				 aStarTime+=10;
				 return arryaCity[i];
			 }
		 }
		 System.out.println("****************"+cityName);
		 City city=new City("name not found");
		 return city;
	 }
	 private void calculatH(City goal) {
		 for(int i=0;i<arryaCity.length;i++) {
			City c= arryaCity[i];
			double x=Math.pow((c.getCenterX()-goal.getCenterX()), 2);
			double y=Math.pow((c.getCenterY()-goal.getCenterY()),2);
			 c.hScores=Math.sqrt(x+y);
		 }
		 
	 }
}

