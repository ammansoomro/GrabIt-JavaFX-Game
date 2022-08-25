package sprites;

import java.nio.file.Files;
import java.nio.file.Paths;
import javafx.scene.image.Image;

public class Fruit extends Sprite {
  private static final String IMAGE_PATH = "assets/fruits.png";
  public static int MAX_FRUITS = 2;
  public static int FRUIT_WIDTH = 30;
  public static int FRUIT_HEIGHT = 30;
  public static float STEP_INCREMENT = 0f;

  //Generate a Fruit from all the Fruits available based on the random number generated.
  public Fruit() {
    this((int) (Math.random() * MAX_FRUITS));
  }

  public Fruit(int fruitType) {
    super(FRUIT_WIDTH, FRUIT_HEIGHT);
    try {
      //Load the Fruits.
      spriteImage = new Image(Files.newInputStream(Paths.get(IMAGE_PATH)));
    } catch (Exception e) {
      e.printStackTrace();
    }
    switch (fruitType) {
      case 0:
        //Banana
        this.spriteX = 4;
        this.spriteY = 40;
        break;
      case 1:
        //Strawberry
        this.spriteX = 42;
        this.spriteY = 40;
        break;
    }
  }

  public void move() {
    //Making the Fruits fall down.
    this.y += (int) (1 + STEP_INCREMENT);
  }

  //Speed of the fruits is increased when the player catches 5 (0.2 * 5 ==> 1) fruits to increase the difficulty.
  public void increaseDifficulty() {
    STEP_INCREMENT += 0.2f;
  }
}
