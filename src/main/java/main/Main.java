package main;
import ui.GameScreen;
import ui.ConfigScreen;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import lib.GameloopUtility;
import lib.MainLogic;

public class Main extends Application {

	public static Main instance;
	
	private Stage primaryStage;
	private Scene configScene;
	private Scene gameScene;
	private MainLogic gameLogic;
	private GameScreen gameScreen;
	private ConfigScreen configScreen;
	
	private boolean isGameSceneShown = false;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		instance = this;
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Shoot the bullet");
		this.primaryStage.setResizable(false);
		this.primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			
			@Override
			public void handle(WindowEvent event) {
				System.exit(0);
			}
		});
		
		this.gameLogic = new MainLogic();
		
		this.configScreen = new ConfigScreen();
		this.configScene = new Scene(configScreen);
		
		gameScreen = new GameScreen(this.gameLogic);
		this.gameScene = new Scene(gameScreen);
		gameScreen.requestFocusForCanvas();
		this.primaryStage.setScene(this.configScene);
		this.resizeStage();
		this.primaryStage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
	
	public void toggleScene(){
		if (this.isGameSceneShown){
			this.primaryStage.setScene(configScene);
			this.gameLogic.onExit();
			System.out.println("To Config Screen");
		}
		else{
			this.gameLogic.onStart();
			GameloopUtility.runGameLoop(gameLogic);
			this.primaryStage.setScene(gameScene);
			System.out.println("To Game Screen");
		}
		this.isGameSceneShown = !this.isGameSceneShown;
	}
	
	public void resizeStage(){
		this.configScreen.applyResize();
		this.gameScreen.applyResize();
		primaryStage.sizeToScene();
	}
	
	public void drawGameScreen(){
		this.gameScreen.paintComponenet();
	}
	
	
	
	
}
