package sprites;

import java.nio.file.Files;
import java.nio.file.Paths;
import javafx.scene.image.Image;
import scenes.GeneralScene;

public class MainCharacter extends AnimatedSprite {
  public static final int MAIN_CHARACTER_WIDTH = 96;
  public static final int MAIN_CHARACTER_HEIGHT = 96;
  private static final String IMAGE_PATH = "assets/bear.png";
  private static final int STEP = 4; //Character Moving Speed

  public MainCharacter() {
    super(MAIN_CHARACTER_WIDTH, MAIN_CHARACTER_HEIGHT);
    try {
      spriteImage = new Image(Files.newInputStream(Paths.get(IMAGE_PATH)));
    } catch (Exception e) {
      e.printStackTrace();
    }

    spriteXCoordinates[RIGHT] = new int[] { 480, 576, 672, 576 };
    spriteYCoordinates[RIGHT] = new int[] { 0, 0, 0, 0 };
    spriteXCoordinates[LEFT] = new int[] { 1248, 1344, 1440, 1344 };
    spriteYCoordinates[LEFT] = new int[] { 0, 0, 0, 0 };
  }

  public void move(int movement) {
    //Move the Character to LEFT
    int newX = x;
    if (movement == LEFT) {
      newX = newX - Math.min(STEP, x);
    }
    //Move the Character to RIGHT
    else if (movement == RIGHT) {
      newX = newX + Math.min(STEP, GeneralScene.GAME_WIDTH - MAIN_CHARACTER_WIDTH - x);
    }
    //SEND COORDINATES to MoveTo function in Sprite.java to move the Character to that location.
    moveTo(newX, y);
    animate(movement);
  }

  //Reset Position when the game ends.
  public void resetPosition() {
    moveTo(
      GeneralScene.GAME_WIDTH / 2 - MAIN_CHARACTER_WIDTH / 2,
      GeneralScene.GAME_HEIGHT - 30 - MAIN_CHARACTER_HEIGHT
    );
  }
}
