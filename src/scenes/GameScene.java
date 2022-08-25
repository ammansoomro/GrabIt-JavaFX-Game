package scenes;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import sprites.Fruit;
import sprites.MainCharacter;
import videogame.BearFruitChallenge;

public class GameScene extends GeneralScene {
  private static final String BACKGROUND_IMAGE = "Assets/background.png";


  private Image background;
  private MainCharacter bear;
  private Fruit fruit = null;
  public static int points = 0;
  private int lives = 3;
  
  public GameScene() {
    super();
    try {
      background = new Image(Files.newInputStream(Paths.get(BACKGROUND_IMAGE)));
      bear = new MainCharacter();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void draw() {
    reset();
    activeKeys.clear();
    bear.moveTo(380, 375);
    new AnimationTimer() {

      @Override
      public void handle(long currentNanoTime) {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);

        gc.drawImage(background, 0, 0);
        bear.draw(gc);
        if (fruit != null) {
          fruit.draw(gc);
        }
        HUD();

        if (activeKeys.contains(KeyCode.ESCAPE)) {
          this.stop();
          BearFruitChallenge.setScene(BearFruitChallenge.WELCOME_SCENE);
        } else if (activeKeys.contains(KeyCode.ENTER)) {
          this.stop();
          BearFruitChallenge.setScene(BearFruitChallenge.CREDITS_SCENE);
        } else if (activeKeys.contains(KeyCode.LEFT)) {
          bear.move(MainCharacter.LEFT);
        } else if (activeKeys.contains(KeyCode.RIGHT)) {
          bear.move(MainCharacter.RIGHT);
        }
        if (fruit == null) {
          fruit = new Fruit();
          fruit.moveTo(
        	// Spawn Fruits at Random Location.
            (int) (Math.random() * (GeneralScene.GAME_WIDTH - Fruit.FRUIT_WIDTH)),
            0
          );
        } else {
          fruit.move();
          if (fruit.collidesWith(bear)) {
            points += 10;
            fruit.increaseDifficulty();
            fruit = null;
          } else if (fruit.getY() > GeneralScene.GAME_HEIGHT) {
            lives--;
            fruit = null;
          }
        }
        if (lives == 0) {
          this.stop();
          BearFruitChallenge.setScene(BearFruitChallenge.CREDITS_SCENE);
        }
      }
    }
      .start();
  }

  private void reset() {
    bear.resetPosition();
    lives = 3;
    points = 0;
    Fruit.STEP_INCREMENT = 0;
  }

  private void HUD() {
    Font myfont = Font.font("Arial", FontWeight.BOLD, 18);
    gc.setFont(myfont);
    gc.setFill(Color.web("130a00"));
    gc.fillText("Score: " + points, 20, GeneralScene.GAME_HEIGHT - 15);

    gc.setFill(Color.YELLOW);
    gc.fillText(
      "Lives: " + lives,
      GeneralScene.GAME_WIDTH - 100,
      GeneralScene.GAME_HEIGHT - 15
    );
  }
}
