package sprites;

// Make the Sprite Move using different images.
public class AnimatedSprite extends Sprite {
  public static final int TOTAL_MOVEMENTS = 2;
  public static final int RIGHT = 0;
  public static final int LEFT = 1;
	//Sprite Image Change Speed 
  public static final byte SPRITE_CHANGE = 7;

  protected int currentDirection; //Left || Right
  protected byte currentSprite;
  protected byte currentSpriteChange;

  protected int[][] spriteXCoordinates = new int[TOTAL_MOVEMENTS][];
  protected int[][] spriteYCoordinates = new int[TOTAL_MOVEMENTS][];

  public AnimatedSprite(int width, int height) {
    super(width, height);
	
	//Initial Direction is set to Right.
    currentDirection = RIGHT;
    currentSprite = 0;
    currentSpriteChange = 0;
  }

  public void animate(int movement) {
	 //If the Direction is Changed from Left to Right or Vice Versa
    if (movement != currentDirection) {
      currentDirection = movement;
      currentSprite = 0;
      currentSpriteChange = 0;
    } else {
    	//If it keeps moving in the same direction.
      currentSpriteChange++;
      if (currentSpriteChange >= SPRITE_CHANGE) {
        currentSpriteChange = 0;
        currentSprite =
          (byte) ((currentSprite + 1) % spriteXCoordinates[currentDirection].length);
      }
    }
    updateSpriteCoordinates();
  }

  protected void updateSpriteCoordinates() {
    spriteX = spriteXCoordinates[currentDirection][currentSprite];
    spriteY = spriteYCoordinates[currentDirection][currentSprite];
  }
}
