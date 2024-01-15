import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;

public class Mario extends GameObject {
  // game status attributes with Mario
  private final IntegerProperty coin;
  boolean isGameOver, isGameWin, canMoveLeft, canMoveRight, canMoveUp;
  Level currentLevel;

  // png resources
  static String[] directionArray = { "Right", "Left" };
  static String[] statusArray = { "Walk", "Jump" };
  private Image[][] images = new Image[directionArray.length][statusArray.length];

  public Mario(double x, double y, Level currentLevel) {
    super(x, y);
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < statusArray.length; j++) {
        images[i][j] = new Image(("media/images/mario" + directionArray[i] + statusArray[j] + ".png"));
      }
    }
    this.setdX(30);
    this.setdY(30);
    // this.setImage(images[0][0]);
    this.setDirection(0);
    this.setStatus(0);
    this.setImage();
    this.setGravity(20);
    this.coin = new SimpleIntegerProperty(0);
    this.canMoveLeft = true;
    this.canMoveRight = true;
    this.setFalling(false);
    this.canMoveUp = true;
    this.currentLevel = currentLevel;
  }

  public void moveLeft() {
    this.setDirection(1);
    setImage();
    if (canMoveLeft && getX() > getdX()) {
      this.setX(this.getX() - getdX());
    }
  }

  public void moveRight() {
    this.setDirection(0);
    setImage();
    if (canMoveRight && getX() < MiniMarioGame.WIDTH - getdX()) {
      this.setX(this.getX() + getdX());
    }
  }

  public void moveUp() {
    this.setStatus(1);
    setImage();
    if (getY() > getdX()) {
      this.setY(this.getY() - getdY());
    }
  }

  public void moveDown() {
    this.setStatus(1);
    setImage();
    if (getY() + getHeight() < MiniMarioGame.HEIGHT - currentLevel.getGroundHeight() - getdX()) {
      this.setY(this.getY() + getdY());
    }
  }

  public void setImage() {
    this.setImage(images[this.getDirection()][this.getStatus()]);
  }

  public void winCoin() {
    setCoin(getCoin() + 1);
  }

  public IntegerProperty coinProperty() {
    return coin;
  }

  public int getCoin() {
    return coin.get();
  }

  public void setCoin(int value) {
    coin.set(value);
  }

  public boolean isCanMoveLeft() {
    return canMoveLeft;
  }

  public void setCanMoveLeft(boolean canMoveLeft) {
    this.canMoveLeft = canMoveLeft;
  }

  public boolean isCanMoveRight() {
    return canMoveRight;
  }

  public void setCanMoveRight(boolean canMoveRight) {
    this.canMoveRight = canMoveRight;
  }

  public boolean isCanMoveUp() {
    return canMoveUp;
  }

  public void setCanMoveUp(boolean canMoveUp) {
    this.canMoveUp = canMoveUp;
  }

}
