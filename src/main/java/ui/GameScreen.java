package ui;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import lib.ConfigurableOption;
import lib.IRenderableHolder;
import lib.IRenderableObject;
import lib.InputUtility;

public class GameScreen extends StackPane {

	private IRenderableHolder renderableHolder;
	private Canvas canvas;

	public GameScreen(IRenderableHolder holder) {
		super();
		this.canvas = new Canvas(ConfigurableOption.screenWidth, ConfigurableOption.screenHeight);
		this.renderableHolder = holder;
		this.getChildren().add(canvas);
		this.addListener();
	}

	public void paintComponenet() {
		GraphicsContext gc = this.canvas.getGraphicsContext2D();
		gc.setFill(Color.BLACK);
		gc.clearRect(0, 0, this.canvas.getWidth(), this.canvas.getHeight());
		gc.fillRect(0, 0, this.canvas.getWidth(), this.canvas.getHeight());
		for (IRenderableObject renderable : renderableHolder.getSortedRenderableObject()) {
			renderable.render(gc);
		}
	}

	public void requestFocusForCanvas() {
		this.requestFocus();
	}

	private void addListener() {
		this.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (event.getButton() == MouseButton.PRIMARY) {
					InputUtility.setMouseLeftDown(true);
					InputUtility.setMouseLeftLastDown(true);
				}
			}
		});
		this.setOnMouseReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				InputUtility.setMouseLeftDown(false);
			}

		});
		this.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				InputUtility.setMouseOnScreen(true);
			}

		});
		this.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				InputUtility.setMouseOnScreen(false);
			}

		});
		this.setOnMouseMoved(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (InputUtility.isMouseOnScreen()) {
					InputUtility.setMouseX((int) event.getX());
					InputUtility.setMouseY((int) event.getY());
				}
			}

		});
		this.setOnMouseDragged(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (InputUtility.isMouseOnScreen()) {
					InputUtility.setMouseX((int) event.getX());
					InputUtility.setMouseY((int) event.getY());
				}
			}

		});
		this.setOnKeyPressed(new EventHandler<KeyEvent>(){

			@Override
			public void handle(KeyEvent event) {
				InputUtility.setKeyPressed(event.getCode(), true);
				if(!InputUtility.getKeyTriggerFlag(event.getCode())){
					InputUtility.setKeyTriggered(event.getCode(), true);
				}
				InputUtility.setKeyTriggerFlag(event.getCode(), true);
			}
			
		});
		this.setOnKeyReleased(new EventHandler<KeyEvent>(){

			@Override
			public void handle(KeyEvent event) {
				InputUtility.setKeyPressed(event.getCode(), false);
				InputUtility.setKeyTriggerFlag(event.getCode(), false);
			}
			
		});
	}

	public void applyResize() {
		this.setPrefSize(ConfigurableOption.screenWidth, ConfigurableOption.screenHeight);
		this.canvas.setWidth(ConfigurableOption.screenWidth);
		this.canvas.setHeight(ConfigurableOption.screenHeight);
	}
}
