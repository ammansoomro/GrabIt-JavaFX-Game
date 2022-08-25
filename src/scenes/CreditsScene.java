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

public class CreditsScene extends GeneralScene {
	  private static final String BACKGROUND_IMAGE = "Assets/death02.png";
	  private Image background;

  public CreditsScene() {
    super();
  }

  private void showCreditsMessage() {
	  try {
	      background = new Image(Files.newInputStream(Paths.get(BACKGROUND_IMAGE)));

	    } catch (Exception e) {
	      e.printStackTrace();
	    }
    Font myFont = Font.font("Arial", FontWeight.BOLD, 32);
    gc.setFont(myFont);
    gc.setFill(Color.RED);
    gc.fillText("GAME OVER", 325, 320);

    myFont = Font.font("Arial", FontWeight.NORMAL, 20);
    gc.setFont(myFont);
    gc.setFill(Color.YELLOW);
    gc.fillText("YOUR SCORE: " + GameScene.points, 340, 360);
  }

  @Override
  public void draw() {
	 //Clear Any active keys from previous scenes.
    activeKeys.clear();
    new AnimationTimer() {

      @Override
      public void handle(long currentNanoTime) {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gc.drawImage(background, 370, 160,100.0,100.0);
        showCreditsMessage();

        if (activeKeys.contains(KeyCode.SPACE)) {
          this.stop();
          BearFruitChallenge.setScene(BearFruitChallenge.WELCOME_SCENE);
        }
      }
    }
      .start();
  }
}
