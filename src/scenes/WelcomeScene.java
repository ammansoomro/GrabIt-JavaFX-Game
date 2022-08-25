package scenes;

import java.nio.file.Files;
import java.nio.file.Paths;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import videogame.BearFruitChallenge;

public class WelcomeScene extends GeneralScene {
  private static final String BACKGROUND_IMAGE = "Assets/BearMascot.png";
  private Image background;

  public WelcomeScene() {
    super();
    showWelcomeMessage();
  }

  private void showWelcomeMessage() {
    try {
      background = new Image(Files.newInputStream(Paths.get(BACKGROUND_IMAGE)));
    } catch (Exception e) {
      e.printStackTrace();
    }
    Font myFont = Font.font("Arial", FontWeight.BOLD, 32);
    gc.setFont(myFont);
    gc.setFill(Color.web("#b34e00"));
    gc.fillText("FRUIT BURST", 315, 360);
    myFont = Font.font("Arial", FontWeight.NORMAL, 20);
    gc.setFont(myFont);
    gc.setFill(Color.WHITE);
    gc.fillText("Press Spacebar to Start", 315, 385);
  }

  @Override
  public void draw() {
    activeKeys.clear();
    new AnimationTimer() {

      @Override
      public void handle(long currentNanoTime) {
        gc.setFill(Color.web("130a00"));
        gc.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gc.drawImage(background, 220, 0, 400.0, 400.0);
        showWelcomeMessage();
        if (activeKeys.contains(KeyCode.SPACE)) {
          this.stop();
          BearFruitChallenge.setScene(BearFruitChallenge.GAME_SCENE);
        } else if (activeKeys.contains(KeyCode.ESCAPE)) {
          this.stop();
          BearFruitChallenge.exit();
        }
      }
    }
      .start();
  }
}
