
import javafx.scene.image.ImageView;

public class GameObject extends ImageView {
  GameScreen gameScreen;
  Level currentLevel;
  // location x,y

  // speed
  private double dX, dY;
  private double gravity;

  private String type;

  static String[] directionArray;
  static String[] statusArray;
  private int direction, status;

  private boolean isFalling, isDeath;

  public GameObject(String url) {
    super(url);
  }

  public GameObject(double x, double y) {
    super();
    this.setX(x);
    this.setY(y);

  }

  public double getdX() {
    return dX;
  }

  public void setdX(double dX) {
    this.dX = dX;
  }

  public double getdY() {
    return dY;
  }

  public void setdY(double dY) {
    this.dY = dY;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public int getDirection() {
    return direction;
  }

  public void setDirection(int direction) {
    this.direction = direction;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public double getGravity() {
    return gravity;
  }

  public void setGravity(double gravity) {
    this.gravity = gravity;
  }

  public boolean isFalling() {
    return isFalling;
  }

  public void setFalling(boolean isFalling) {
    this.isFalling = isFalling;
  }

  public void setDeath(boolean isDeath) {
    this.isDeath = isDeath;
  }

  public boolean isDeath() {
    return isDeath;
  }

  public void moveFall() {
    while (isFalling()) {
      this.setY(this.getY() + getGravity());
      try {
        Thread.sleep(100); // 100 ms
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  public boolean intersects(ImageView other) {
    return this.getBoundsInParent().intersects(other.getBoundsInParent());
  }

  public double getHeight() {
    return getImage().getHeight();
  }

  public double getWidth() {
    return getImage().getWidth();
  }
}
