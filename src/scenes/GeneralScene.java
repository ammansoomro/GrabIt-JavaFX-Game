package scenes;

import java.util.HashSet;
import java.util.Set;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public abstract class GeneralScene extends Scene {
  public static final int GAME_WIDTH = 816;
  public static final int GAME_HEIGHT = 480;

  private StackPane root = new StackPane();
  protected GraphicsContext gc;
  protected Set<KeyCode> activeKeys;
  protected Set<KeyCode> releasedKeys;
  protected MediaPlayer mediaPlayer;
  protected Media sound;

  public GeneralScene() {
    // Call Scene Constructor to Initialize it.
    super(new StackPane(), GAME_WIDTH, GAME_HEIGHT);
    root = new StackPane();
    this.setRoot(root);

    Canvas canvas = new Canvas(GAME_WIDTH, GAME_HEIGHT);
    root.getChildren().add(canvas);
    gc = canvas.getGraphicsContext2D();

    activeKeys = new HashSet<>();
    releasedKeys = new HashSet<>();
    this.setOnKeyPressed(
        event -> {
          activeKeys.add(event.getCode());
        }
      );
    this.setOnKeyReleased(
        event -> {
          activeKeys.remove(event.getCode());
          releasedKeys.add(event.getCode());
        }
      );
  }

  public abstract void draw();
}
