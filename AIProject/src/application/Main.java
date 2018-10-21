package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class Main extends Application {
	Button getPath = new Button("Get path");
	Text[] text;
	TextArea timeAlgorithm=new TextArea();

	TextArea spaceAlgorithm=new TextArea();
	int limitForLimitsearch;

	ComboBox<String> startCity = new ComboBox<>();
	
	ComboBox<String> goalCity = new ComboBox<>();

	@Override
	public void start(Stage primaryStage) {

		try {
			getPath.setStyle("-fx-background-color: linear-gradient(#ff5400, #be1d00);\r\n" + 
					"    -fx-background-radius: 30;\r\n" + 
					"    -fx-background-insets: 0;\r\n" + 
					"    -fx-text-fill: white;");
			timeAlgorithm.setEditable(false);
			spaceAlgorithm.setEditable(false);
			Pane paneImage = new Pane();
			Image image = new Image("file:///E:/eclipse-workspace/AIProject/src/application/dd2.svg.png");
			ImageView imv = new ImageView();
			imv.setFitHeight(700);
			imv.setFitWidth(400);
			imv.setImage(image);
			imv.setOnMouseClicked(e -> {

				System.out.println("[" + e.getX() + ", " + e.getY() + "]\n");

			});
			Label startCityLabel = new Label("Choose Start City");
			startCityLabel.setTextFill(Color.WHEAT);
			Label goalCityLabel = new Label("Choose goal City");
			goalCityLabel.setTextFill(Color.WHEAT);

			
			startCity.setPromptText("your location");
			goalCity.setPromptText("target Locatin");
			HBox fromCity = new HBox(startCityLabel, startCity);
			fromCity.setPadding(new Insets(10));
			HBox toCity = new HBox(goalCityLabel, goalCity);
			toCity.setPadding(new Insets(10));
			ToggleGroup group = new ToggleGroup();
			RadioButton bredth = new RadioButton("Bredth search");
			bredth.setTextFill(Color.WHEAT);
			RadioButton depth = new RadioButton("Depth search");
			depth.setTextFill(Color.WHEAT);
			RadioButton depthLimitd = new RadioButton("Depth Limited serach");
			depthLimitd.setTextFill(Color.WHEAT);
			RadioButton biDirctional = new RadioButton("Bi-Dirctional search");
			biDirctional.setTextFill(Color.WHEAT);
			RadioButton iterative = new RadioButton("iterative search");
			iterative.setTextFill(Color.WHEAT);
			RadioButton greedy = new RadioButton("Greedy");
			greedy.setTextFill(Color.WHEAT);
			RadioButton aStar = new RadioButton("A-star");
			aStar.setTextFill(Color.WHEAT);

			bredth.setToggleGroup(group);
			bredth.setMinSize(140, 10);
			depth.setToggleGroup(group);
			depth.setMinSize(140, 10);

			biDirctional.setToggleGroup(group);
			biDirctional.setMinSize(140, 10);

			iterative.setToggleGroup(group);
			iterative.setMinSize(140, 10);

			greedy.setToggleGroup(group);
			greedy.setMinSize(140, 10);

			aStar.setToggleGroup(group);
			aStar.setMinSize(140, 10);
			depthLimitd.setToggleGroup(group);
			depthLimitd.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					boolean flage = true;
					while (flage) {
						TextInputDialog dialog = new TextInputDialog("ex.15");
						dialog.setTitle("Warining");
						dialog.setHeaderText("Enter limit for search:");
						dialog.setContentText("Limit:");
						int letter = 0;
						Optional<String> result = dialog.showAndWait();
						String res = result.get();
						for (int i = 0; i < res.length(); i++) {
							if (Character.isDigit(res.charAt(i))) {
								letter++;
							}
						}
						if (letter == res.length()) {
							flage = false;
							limitForLimitsearch = Integer.parseInt(res);
						}
					}
				}
			});
			Label labelTimeAlgrithm=new Label("Time :");
			labelTimeAlgrithm.setTextFill(Color.WHEAT);
			Label labelSpaceAlgrithm=new Label("Space:");
			labelSpaceAlgrithm.setTextFill(Color.WHEAT);
			HBox time=new HBox(3);
			HBox space=new HBox(3);
			time.getChildren().addAll(labelTimeAlgrithm,timeAlgorithm);
			space.getChildren().addAll(labelSpaceAlgrithm,spaceAlgorithm);
			VBox timeAndSpace=new VBox(10);
			timeAndSpace.getChildren().addAll(time,space);
			HBox h1 = new HBox(3);
			HBox h2 = new HBox(3);
			HBox h3 = new HBox(3);
			HBox h4 = new HBox(3);
			h1.getChildren().addAll(bredth, depth);
			h2.getChildren().addAll(biDirctional, iterative);
			h3.getChildren().addAll(aStar, greedy);
			h4.getChildren().add(depthLimitd);
			timeAlgorithm.setMaxSize(60, 30);
			spaceAlgorithm.setMaxSize(60, 30);
			aStar.setSelected(true);
			BorderPane root = new BorderPane();
			paneImage.getChildren().add(imv);

			File file = new File("E:\\eclipse-workspace\\AIProject\\src\\application\\city");
			City[] arrayCity = getCityInformation(file);
			text = new Text[arrayCity.length];
			for (int i = 0; i < arrayCity.length; i++) {
				startCity.getItems().add(arrayCity[i].name);
				goalCity.getItems().add(arrayCity[i].name);
				text[i] = new Text(arrayCity[i].name);
				if (arrayCity[i].name.equals("Qiryat shemona")) {
					System.out.println("sss");
					text[i].setX(arrayCity[i].getCenterX() - 70);
					text[i].setY(arrayCity[i].getCenterY() - 10);
					paneImage.getChildren().add(text[i]);
					paneImage.getChildren().add(arrayCity[i]);
				} else {
					text[i].setX(arrayCity[i].getCenterX() - 10);
					text[i].setY(arrayCity[i].getCenterY() - 10);
					paneImage.getChildren().add(text[i]);
					paneImage.getChildren().add(arrayCity[i]);

				}
			}
			file = new File("E:\\eclipse-workspace\\AIProject\\src\\application\\ad");
			Graph graph = drowGraph(file, arrayCity);
			Greedy greedyObject = new Greedy(arrayCity);
			// System.out.println(graph.pathExistsBidirectional(arrayCity[2],
			// arrayCity[10]));
			// Greedy g=new Greedy(arrayCity);

			// Queue<City> queue=g.computePaths(arrayCity [5],arrayCity [26]);
			// Queue<City> queue=graph.getBIDIPath();
			// graph.LimitdDFS(arrayCity[1], arrayCity[2] , 40);

			// Queue<City> queue=graph.getDFSIerativePath();

			// for(City a: queue) {
			// System.out.println(a.cityId);
			// }

			/*
			 * graph.dfs(arrayCity[0],arrayCity[22]); Queue<City> queue=graph.getDFS(); int
			 * c=0; for(City a :queue) { c++; System.out.println(a.cityId); }
			 * System.out.println("c  "+c);
			 */

			// Greedy g = new Greedy(arrayCity);
			// g.AstarSearch(arrayCity[4], arrayCity[25]);// ***************************
			// List<City> path = g.getPath(arrayCity[25]);

			root.setCenter(paneImage);
			root.setStyle("-fx-background-color: #0000FF;");
			HBox buttonHBox = new HBox();
			getPath.setMinSize(80, 10);
			getPath.setAlignment(Pos.CENTER);
			buttonHBox.getChildren().add(getPath);
			buttonHBox.setAlignment(Pos.CENTER);
			VBox cityVbox = new VBox(fromCity, toCity, h1, h2, h3, h4, buttonHBox,timeAndSpace);
			cityVbox.setSpacing(15);
			cityVbox.setPadding(new Insets(10));
			getPath.setOnAction(e -> search(greedyObject, graph, paneImage, group, arrayCity, imv));// ****************************************************
			root.setRight(cityVbox);
			Scene scene = new Scene(root, 750, 700);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Map");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void search(Greedy greedyObject, Graph graph, Pane paneImage, ToggleGroup group, City[] arrayCity,
			ImageView imv) {
		// TODO Auto-generated method stub
        timeAlgorithm.clear();
        spaceAlgorithm.clear();
		Queue<City> finalQueue;
		City start = gitCityInfo(startCity.getValue(), arrayCity);
		City goal = gitCityInfo(goalCity.getValue(), arrayCity);
		String selectedradio = group.getSelectedToggle().toString();
		if (selectedradio.contains("Bredth search")) {
			finalQueue = graph.BFS(start.cityId, goal.cityId);
			addGreedyUnunformPathToGUI(finalQueue, paneImage);
			 timeAlgorithm.appendText(graph.BFS+"");
		        spaceAlgorithm.appendText(finalQueue.size()+"");
			
		} else if (selectedradio.contains("Depth search")) {
			graph.dfs(start, goal);
			finalQueue = graph.getDFS();
			addDFSToGUI(finalQueue, paneImage, goal);
			timeAlgorithm.appendText(graph.DFs+"");
	        spaceAlgorithm.appendText(finalQueue.size()+"");
		} else if (selectedradio.contains("Depth Limited serach")) {
			if (!graph.LimitdDFS(start, goal, limitForLimitsearch)) {

				Alert alert = new Alert(AlertType.ERROR, "Goal Node not found within depth limit", ButtonType.OK);
				alert.show();
				// System.out.println(finalQueue.size());
				// addGreedyUnunformPathToGUI(finalQueue, paneImage);

			} else {
				finalQueue = graph.getLimtedDepth();
				addGreedyUnunformPathToGUI(finalQueue, paneImage);
				timeAlgorithm.appendText(graph.DFSLimted+"");
		        spaceAlgorithm.appendText(finalQueue.size()+"");
			}
		} else if (selectedradio.contains("Bi-Dirctional search")) {
			graph.pathExistsBidirectional(start, goal);
			finalQueue = graph.getBIDIPath();
			addGreedyUnunformPathToGUI(finalQueue, paneImage);
			timeAlgorithm.appendText(graph.DFSBIDI+"");
	        spaceAlgorithm.appendText(finalQueue.size()+"");
		} else if (selectedradio.contains("iterative search")) {
			graph.DFSIerative(start.cityId, goal.cityId);
			finalQueue = graph.finalCityDFSIerative;
			addGreedyUnunformPathToGUI(finalQueue, paneImage);
			timeAlgorithm.appendText(graph.DFSIT+"");
	        spaceAlgorithm.appendText(finalQueue.size()+"");
		} else if (selectedradio.contains("Greedy")) {
			finalQueue = greedyObject.computePaths(start, goal);
			addGreedyUnunformPathToGUI(finalQueue, paneImage);
			timeAlgorithm.appendText(greedyObject.greedyTime+"");
	        spaceAlgorithm.appendText(finalQueue.size()+"");
		} else if (selectedradio.contains("A-star")) {
			greedyObject.AstarSearch(start, goal);
			List<City> path = greedyObject.getPath(goal);
			addAStarGraphToGUI(path, paneImage);
			timeAlgorithm.appendText(greedyObject.aStarTime+"");
	        spaceAlgorithm.appendText(path.size()+"");
		}

	}

	private void addGreedyUnunformPathToGUI(Queue<City> queue, Pane paneImage) {
		double[][] vertex = new double[queue.size()][2];
		Line line;
		int i = 0;
		for (City city : queue) {
			vertex[i][0] = city.x;
			vertex[i][1] = city.y;
			i++;
		}
		for (int j = 0; j < vertex.length - 1; j++) {
			line = new Line(vertex[j][0], vertex[j][1], vertex[j + 1][0], vertex[j + 1][1]);
			line.setFill(Color.RED);

			paneImage.getChildren().add(line);

		}
	}

	private void addDFSToGUI(Queue<City> queue, Pane paneImage, City target) {
		double[][] vertex = new double[queue.size()][2];
		Line line;
		int i = 0;
		for (City city : queue) {
			if (city.cityId == target.cityId) {
				vertex[i][0] = city.x;
				vertex[i][1] = city.y;
				break;
			} else {
				vertex[i][0] = city.x;
				vertex[i][1] = city.y;
				i++;
			}
		}

		for (int j = 0; j < i; j++) {
			line = new Line(vertex[j][0], vertex[j][1], vertex[j + 1][0], vertex[j + 1][1]);
			line.setFill(Color.RED);
			paneImage.getChildren().add(line);

		}
	}

	private void addAStarGraphToGUI(List<City> path, Pane paneImage) {
		// TODO Auto-generated method stub
		double[][] vertex = new double[path.size()][2];
		Line line;
		int i = 0;
		for (City city : path) {
			vertex[i][0] = city.x;
			vertex[i][1] = city.y;
			i++;
		}
		for (int j = 0; j < vertex.length - 1; j++) {
			line = new Line(vertex[j][0], vertex[j][1], vertex[j + 1][0], vertex[j + 1][1]);

			paneImage.getChildren().add(line);

		}
	}

	private void getXAndY(ImageView iv) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		launch(args);
	}

	public String[] readFile(File file) throws IOException {
		String[] line;
		int count = 0;
		BufferedReader br = new BufferedReader(new FileReader(file));
		while (br.readLine() != null) {
			count++;
		}
		br.close();
		br = new BufferedReader(new FileReader(file));
		line = new String[count];
		for (int i = 0; i < count; i++) {
			line[i] = br.readLine();

		}
		return line;
	}

	public City[] getCityInformation(File file) throws IOException {

		String line[] = readFile(file);
		City[] city = new City[line.length];
		String[] spliter;
		for (int i = 0; i < line.length; i++) {
			spliter = line[i].split("#");
			city[i] = new City(spliter[0], i, Integer.parseInt(spliter[1]), Integer.parseInt(spliter[2]));
		}
		return city;

	}

	public Graph drowGraph(File file, City[] city) throws IOException {
		String[] line = readFile(file);
		String[] spliter;
		String[] jSpliter;
		Edge[] edge;
		Graph graph = new Graph(city.length, city);

		for (int i = 0; i < line.length; i++) {
			spliter = line[i].split("#");
			edge = new Edge[spliter.length];
			for (int j = 0; j < spliter.length; j++) {
				jSpliter = spliter[j].split("@");
				City c = new City(jSpliter[0]);
				edge[j] = new Edge(c, Double.parseDouble(jSpliter[1]));
				for (int k = 0; k < city.length; k++) {
					if (city[k].name.equals(jSpliter[0])) {
						int id = city[k].cityId;

						graph.addEdge(graph, i, id);
						break;

					}
				}

			}
			city[i].adjacencies = edge;
		}
		return graph;
	}

	private City gitCityInfo(String cityName, City[] arrayCity) {
		for (int i = 0; i < arrayCity.length; i++) {
			if (arrayCity[i].name.equals(cityName)) {
				return arrayCity[i];
			}
		}
		return new City("not found city name");
	}
	// *************************************************************************************

}
