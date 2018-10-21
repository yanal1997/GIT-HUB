package application;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.Vector;

public class Graph {
	int BFS=10;
	int DFs=10;
	int DFSBIDI=10;
	int DFSIT=10;
	int DFSLimted=10;
	Queue <City>finalCity = new LinkedList<City>();
	Queue finalCity2 = new LinkedList<City>();
	Queue finalCityA = new LinkedList<City>();
	Queue finalCityLimited = new LinkedList<City>();
	Queue finalCityB = new LinkedList<City>();
	//
	Queue finalCityDFSIerative = new LinkedList<City>();

	Queue finalCityDFS = new LinkedList<City>();
	int V;
	boolean visited[];
	City[] arrayCity;
	LinkedList<Integer>[] adjListArray;

	// constructor
	Graph(int V, City[] city) {

		this.V = V;
		this.arrayCity = city;
		// define the size of array as
		// number of vertices

		// Create a new list for each vertex
		// such that adjacent nodes can be stored'
		adjListArray = new LinkedList[V];
		for (int i = 0; i < V; i++) {
			adjListArray[i] = new LinkedList<>();
		}
		visited = new boolean[V];
		for (int i = 0; i < visited.length; i++) {
			visited[i] = false;

		}
	}

// Adds an edge to an undirected graph 
	static void addEdge(Graph graph, int src, int dest) {
		// Add an edge from src to dest.
		graph.adjListArray[src].add(dest);

		// Since graph is undirected, add an edge from dest
		// to src also
		// graph.adjListArray[dest].add(src);
	}

// A utility function to print the adjacency list  
// representation of graph 
	public void printGraph(Graph graph) {
		for (int v = 0; v < graph.V; v++) {
			System.out.println("Adjacency list of vertex " + v);
			System.out.print("head");
			for (Integer pCrawl : graph.adjListArray[v]) {
				System.out.print(" -> " + pCrawl);
			}
			System.out.println("\n");
		}
	}

	public Queue BFS(int s, int goal) {
		// Mark all the vertices as not visited(By default
		// set as false)
		boolean visited[] = new boolean[V];
		 BFS+=10;
		// Create a queue for BFS
		LinkedList<Integer> queue = new LinkedList<Integer>();

		// Mark the current node as visited and enqueue it
		visited[s] = true;
		queue.add(s);
		BFS+=10;
		while (queue.size() != 0) {
			// Dequeue a vertex from queue and print it
			s = queue.poll();
			finalCity.add(getCityById(s));
			System.out.print(s + " ");
			if (s == goal) {
				BFS+=10;
				break;
			}

			// Get all adjacent vertices of the dequeued vertex s
			// If a adjacent has not been visited, then mark it
			// visited and enqueue it
			Iterator<Integer> i = adjListArray[s].listIterator();
			while (i.hasNext()) {
				int n = i.next();
				if (!visited[n]) {
					BFS+=10;
					visited[n] = true;
					queue.add(n);
				}
			}
		}
		return finalCity;
	}

	int j = 0;

	

	void DFSIerative(int s, int goal) {
		 DFSIT+=10;
		// Initially mark all vertices as not visited
		Vector<Boolean> visited = new Vector<Boolean>(V);
		for (int i = 0; i < V; i++)
			visited.add(false);
		 DFSIT+=10;
		// Create a stack for DFS
		Stack<Integer> stack = new Stack<>();

		// Push the current source node
		stack.push(s);
		finalCityDFSIerative.add(getCityById(s));
		while (!stack.empty()) {
			if (s == goal) {
				 DFSIT+=10;

				break;
			}
			// Pop a vertex from stack and print it
			s = stack.peek();
			stack.pop();

			// Stack may contain same vertex twice. So
			// we need to print the popped item only
			// if it is not visited.
			if (visited.get(s) == false) {
				 DFSIT+=10;

				// System.out.print(s + " ");
				visited.set(s, true);
			}

			// Get all adjacent vertices of the popped vertex s
			// If a adjacent has not been visited, then puah it
			// to the stack.
			Iterator<Integer> itr = adjListArray[s].iterator();

			while (itr.hasNext()) {
				int v = itr.next();
				if (!visited.get(v)) {
					 DFSIT+=10;

					if (v == goal) {
						 DFSIT+=10;

						finalCityDFSIerative.add(getCityById(v));
						s = goal;
						break;
					}
					stack.push(v);
					finalCityDFSIerative.add(getCityById(v));
				}
			}

		}
	}

	public void biDirectionalsearch(int start, int goal) {
		boolean[] start_visited = new boolean[V];
		boolean[] goal_visited = new boolean[V];
		int[] start_Parent = new int[V];
		int[] goal_Parent = new int[V];
		LinkedList startQueue = new LinkedList<Integer>();
		LinkedList goalQueue = new LinkedList<Integer>();
		int intersectNode = -1;
		for (int i = 0; i < V; i++) {
			start_visited[i] = false;
			goal_visited[i] = false;
		}
		startQueue.push(start);
		start_visited[start] = true;
		start_Parent[start] = -1;
		goalQueue.push(goal);
		goal_visited[goal] = true;
		goal_Parent[goal] = -1;
		while (!startQueue.isEmpty() && !goalQueue.isEmpty()) {
			BFSBIDI(startQueue, start_visited, start_Parent);

			BFSBIDI(goalQueue, goal_visited, goal_Parent);
			intersectNode = isIntersecting(start_visited, goal_visited);
			if (intersectNode != -1) {
				System.out.println("Path exist between " + start + "and " + goal);
				System.out.println("Intersection at: " + intersectNode);
				printPath(start_Parent, goal_Parent, start, goal, intersectNode);
				break;
			}

		}

	}

	private void printPath(int[] start_Parent, int[] goal_Parent, int start, int goal, int intersectNode) {
		// TODO Auto-generated method stub
		LinkedList path = new LinkedList<Integer>();
		int city = intersectNode;
		while (intersectNode != start) {
			System.out.println("the city is" + city);
			path.addFirst(start_Parent[city]);
			city = start_Parent[city];
		}
		city = intersectNode;
		while (city != goal) {
			path.add(goal_Parent[city]);
			city = goal_Parent[city];
		}
		System.out.println("the path is*");
		for (int i = 0; i < path.size(); i++) {
			System.out.println(i + " ");
		}
		System.out.println();

	}

	private int isIntersecting(boolean[] start_visited, boolean[] goal_visited) {
		// TODO Auto-generated method stub
		int intersectNode = -1;
		for (int i = 0; i < V; i++) {
			if (start_visited[i] && goal_visited[i]) {
				return i;
			}
		}
		return -1;
	}

	private void BFSBIDI(LinkedList<Integer> s, boolean[] visited, int[] Parent) {
		// TODO Auto-generated method stub
		int current = s.removeFirst();
		for (int i = 0; i < adjListArray.length; i++) {
			if (!visited[i]) {
				Parent[i] = current;

				visited[i] = true;
				s.push(i);
			}

		}
	}

	public boolean pathExistsBidirectional(City a, City b) {
		// BFS on both nodes at the same time
		Queue<City> queueA = new LinkedList<City>();
		Queue<City> queueB = new LinkedList<City>();
		Set<City> visitedA = new HashSet<City>();
		Set<City> visitedB = new HashSet<City>();
		DFs+=10;
		DFs+=10;
		DFs+=10;
		DFs+=10;
		visitedA.add(a);
		finalCityA.add(a);
		visitedB.add(b);
		queueA.add(a);
		finalCityB.add(b);
		queueB.add(b);

		while (!queueA.isEmpty() || !queueB.isEmpty()) {
			if (pathExistsBidirectionalHelper(queueA, visitedA, visitedB, b)) {
				finalCityA.add(b);
				DFs+=10;
				finalCity2 = finalCityA;
				return true;
			}
			if (pathExistsBidirectionalHelper(queueB, visitedB, visitedA, b)) {
				finalCityB.add(a);
				DFs+=10;
				finalCity2 = finalCityB;

				return true;
			}
		}

		return false;
	}

	private boolean pathExistsBidirectionalHelper(Queue<City> queue, Set<City> visitedFromThisSide,
			Set<City> visitedFromThatSide, City b) {
		if (!queue.isEmpty()) {
			City adjacentCity;
			City next = queue.remove();
			for (int i = 0; i < next.adjacencies.length; i++) {
				adjacentCity = gitCityInfo(next.adjacencies[i].goal.name);
				if (adjacentCity.name.equals("not found city name")) {
					System.out.println(adjacentCity.name);
				}
				if (visitedFromThatSide.contains(adjacentCity)) {
					if (adjacentCity.name.equals(b.name)) {
						finalCityA.add(adjacentCity);
						finalCityB.add(adjacentCity);

					}

					return true;
				} else if (visitedFromThisSide.add(adjacentCity)) {
					queue.add(adjacentCity);
					finalCityA.add(adjacentCity);
					finalCityB.add(adjacentCity);
				}
			}
		}
		return false;
	}

	private City gitCityInfo(String cityName) {
		for (int i = 0; i < arrayCity.length; i++) {
			if (arrayCity[i].name.equals(cityName)) {
				return arrayCity[i];
			}
		}
		return new City("not found city name");
	}

	public Queue getBIDIPath() {
		return finalCity2;
	}

	public Queue getLimtedDepth() {
		return finalCityLimited;
	}

	public Queue getDFSIerativePath() {
		return finalCityDFSIerative;
	}

	public Queue getDFS() {
		return finalCityDFS;
	}

	public boolean LimitdDFS(City startCity, City goalCity, int limit) {
		boolean[] vistedCity = new boolean[arrayCity.length];
		for (int i = 0; i < vistedCity.length; i++) {
			vistedCity[i] = false;
		}
		Stack<Integer> nodeStack = new Stack<>();
		 DFSLimted+=10;
		ArrayList<City> visitedNodes = new ArrayList<City>();
		nodeStack.add(startCity.cityId);
		limit--;
		 DFSLimted+=10;
		 DFSLimted+=10;
		finalCityLimited.add(startCity);
		vistedCity[startCity.cityId] = true;
		int depth = 1;

		while (!nodeStack.isEmpty()) {

			if (startCity.cityId == goalCity.cityId) {
				 DFSLimted+=10;
				if(limit>=depth) {
					 DFSLimted+=10;
					System.out.println("d"+depth);
					System.out.println("l"+limit);
					return true;
				}else if(limit<depth) {
					 DFSLimted+=10;
					return false;
				}		
			}
			if (depth <= limit) {
				 DFSLimted+=10;
				int current = nodeStack.pop();
				
				City currentCity = getCityById(current);
				// System.out.println(current.cityId);
				if (current == goalCity.cityId) {
					limit--;
					 DFSLimted+=10;
					finalCityLimited.add(goalCity);
					System.out.println("Goal node found");

					if(limit>=depth) {
						System.out.println("d"+depth);
						 DFSLimted+=10;
						System.out.println("l"+limit);
						return true;
					}else if(limit<depth) {
						 DFSLimted+=10;
						return false;
					}				} else {  DFSLimted+=10;
					visitedNodes.add(currentCity);
					for (int i = 0; i < currentCity.adjacencies.length; i++) {
						City t = gitCityInfo(currentCity.adjacencies[i].goal.name);
						if (!vistedCity[t.cityId]) {
							 DFSLimted+=10;
							if (t.cityId == goalCity.cityId) {
								 DFSLimted+=10;

								finalCityLimited.add(t);
								startCity = goalCity;
								if(limit>=depth) {
									 DFSLimted+=10;

									System.out.println("d"+depth);
									System.out.println("l"+limit);
								return true;
							}else if(limit<depth) {
								 DFSLimted+=10;

								return false;
							}
								}
							vistedCity[t.cityId] = true;
							finalCityLimited.add(t);
							nodeStack.add(t.cityId);
							limit--;
							 DFSLimted+=10;


						}
					}
					depth++;

				}
			} else {
				System.out.println("Goal Node not found within depth limit");
				return false;
			}
		}

		return false;
	}

	private City getCityById(int current) {
		// TODO Auto-generated method stub
		for (int i = 0; i < arrayCity.length; i++) {
			if (arrayCity[i].cityId == current) {
				return arrayCity[i];
			}
		}
		return null;
	}

	public void dfs(City startCity, City target) {
		DFs+=10;
		finalCityDFS.add(startCity);
		Edge[] edge = startCity.adjacencies;
		visited[startCity.cityId] = true;
		for (int i = 0; i < startCity.adjacencies.length; i++) {
			if(startCity.cityId==target.cityId) {
				DFs+=10;
				break;
			}
			City newCity = gitCityInfo(startCity.adjacencies[i].goal.name);
			if (!visited[newCity.cityId]) {
				DFs+=10;
				visited[newCity.cityId]=true;
				finalCityDFS.add(newCity);
				System.out.println(newCity.name);
			//	if (newCity.cityId == target.cityId) {
				//	System.out.println("gool");
			//		startCity=target;
				//	break;
//				}

				dfs(newCity, target);
			}
		}
	}
}