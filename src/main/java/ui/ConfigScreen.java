package ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import lib.ConfigurableOption;
import main.Main;

public class ConfigScreen extends BorderPane {

	private BorderPane optionPane;
	private Button newGame = new Button("New Game");
	private Button viewScore = new Button("High Score");

	public ConfigScreen() {
		optionPane = new BorderPane();
		optionPane.setPrefSize(ConfigurableOption.screenWidth, ConfigurableOption.screenHeight);

		HBox topb = new HBox();
		Label txt = new Label("Shoot the bullet");
		txt.setFont(Font.font("Tahoma", FontWeight.BOLD, FontPosture.ITALIC, 30));
		txt.setTextFill(Color.BLACK);
		topb.getChildren().add(txt);
		topb.setStyle("-fx-background-color:blue;");
		topb.setAlignment(Pos.CENTER);
		setTop(topb);

		FlowPane settingP = new FlowPane(10, 5);
		settingP.setPadding(new Insets(10, 10, 10, 10));
		Label widtxt = new Label("WIDTH");
		Label heitxt = new Label("HEIGHT");
		Button apply = new Button("Apply");
		TextField widtf = new TextField("" + ConfigurableOption.screenWidth);
		widtf.setPrefWidth(100);
		TextField heitf = new TextField("" + ConfigurableOption.screenHeight);
		heitf.setPrefWidth(100);
		apply.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					ConfigurableOption.screenWidth = Integer.parseInt(widtf.getText());
					ConfigurableOption.screenHeight = Integer.parseInt(heitf.getText());
					Main.instance.resizeStage();
				} catch (NumberFormatException e) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error");
					alert.setHeaderText(null);
					if (!widtf.getText().matches("[0-9]+")) {
						alert.setContentText("For input string: \"" + widtf.getText() + "\"");
					} else {
						alert.setContentText("For input string: \"" + heitf.getText() + "\"");
					}
					alert.showAndWait();

					widtf.setText("" + ConfigurableOption.screenWidth);
					heitf.setText("" + ConfigurableOption.screenHeight);
				}

			}
		});
		
		HBox widb = new HBox(10);
		HBox heib = new HBox(10);
		widb.getChildren().addAll(widtxt,widtf);
		heib.getChildren().addAll(heitxt,heitf);
		widb.setAlignment(Pos.CENTER);
		heib.setAlignment(Pos.CENTER);
		settingP.getChildren().addAll(widb, heib,apply);
		settingP.setHgap(60);
		settingP.setAlignment(Pos.CENTER);
		
		GridPane othersetP = new GridPane();
		Label cmintxt = new Label("Creation min delay");
		Label cmaxtxt = new Label("Creation max delay");
		Label omintxt = new Label("Object min duration");
		Label omaxtxt = new Label("Object max duration");
		Label tltxt = new Label("Time limit (sec)");
		Spinner<Integer> cmin = new Spinner<Integer>(30, 150, ConfigurableOption.objectCreationMinDelay, 10);
		Spinner<Integer> cmax = new Spinner<Integer>(200, 400, ConfigurableOption.objectCreationMaxDelay, 10);
		Spinner<Integer> omin = new Spinner<Integer>(50, 500, ConfigurableOption.objectMinDuration, 10);
		Spinner<Integer> omax = new Spinner<Integer>(600, 1000, ConfigurableOption.objectMaxDuration, 10);
		Spinner<Integer> tl = new Spinner<Integer>(30, 300, ConfigurableOption.timelimit, 10);
		cmin.setPrefWidth(100);
		cmax.setPrefWidth(100);
		omin.setPrefWidth(100);
		omax.setPrefWidth(100);
		tl.setPrefWidth(100);
		
		HBox cminb = new HBox(10);
		HBox cmaxb = new HBox(10);
		HBox ominb = new HBox(10);
		HBox omaxb = new HBox(10);
		HBox tlb = new HBox(10);
		cminb.getChildren().addAll(cmintxt,cmin);
		cmaxb.getChildren().addAll(cmaxtxt,cmax);
		ominb.getChildren().addAll(omintxt,omin);
		omaxb.getChildren().addAll(omaxtxt,omax);
		tlb.getChildren().addAll(tltxt,tl);
		cminb.setPrefSize(ConfigurableOption.screenWidth/2, ConfigurableOption.screenHeight/2);
		cmaxb.setPrefSize(ConfigurableOption.screenWidth/2, ConfigurableOption.screenHeight/2);
		ominb.setPrefSize(ConfigurableOption.screenWidth/2, ConfigurableOption.screenHeight/2);
		omaxb.setPrefSize(ConfigurableOption.screenWidth/2, ConfigurableOption.screenHeight/2);
		tlb.setPrefSize(ConfigurableOption.screenWidth/2, ConfigurableOption.screenHeight/2);
		cminb.setAlignment(Pos.CENTER);
		cmaxb.setAlignment(Pos.CENTER);
		ominb.setAlignment(Pos.CENTER);
		omaxb.setAlignment(Pos.CENTER);
		tlb.setAlignment(Pos.CENTER);
		
		othersetP.add(cminb, 0, 0);
		othersetP.add(cmaxb, 1, 0);
		othersetP.add(ominb, 0, 1);
		othersetP.add(omaxb, 1, 1);
		othersetP.add(tlb, 0, 2);
		othersetP.setAlignment(Pos.CENTER);
		
		VBox cenb = new VBox();
		cenb.getChildren().addAll(settingP,othersetP);
		cenb.setAlignment(Pos.CENTER);
		setCenter(cenb);
		
		HBox botp = new HBox(ConfigurableOption.screenWidth / 8);
		botp.setStyle("-fx-background-color:green;");
		botp.setPadding(new Insets(5, 5, 5, 5));
		newGame.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				ConfigurableOption.objectCreationMinDelay = cmin.getValue();
				ConfigurableOption.objectCreationMaxDelay = cmax.getValue();
				ConfigurableOption.objectMaxDuration = omax.getValue();
				ConfigurableOption.objectMinDuration = omin.getValue();
				ConfigurableOption.timelimit = tl.getValue();
				Main.instance.toggleScene();
			}
		});
		viewScore.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				HighScoreUtility.displayTop10();
			}
		});
		botp.getChildren().addAll(newGame,viewScore);
		botp.setAlignment(Pos.CENTER);
		setBottom(botp);
	}

	public void applyResize() {
		this.setPrefSize(ConfigurableOption.screenWidth, ConfigurableOption.screenHeight);
	}
}
