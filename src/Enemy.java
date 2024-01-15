
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class Enemy extends GameObject {
  private static final double ENEMY_WIDTH = 40;
  private static final double ENEMY_HEIGHT = 40;
  private double startX, startY, endX, endY;
  static String[] statusArray = { "Stand", "Walk", "Death" };
  private Image[] images = new Image[statusArray.length];
  private int status = 0;

  public Enemy(double startX, double startY, double endX, double endY) {
    super(startX, startY);
    this.startX = startX;
    this.startY = startY;
    this.endX = endX;
    this.endY = endY;
    for (int j = 0; j < statusArray.length; j++) {
      images[j] = new Image(("media/images/goomba" + statusArray[j] + ".png"));
    }
    this.setFitWidth(ENEMY_WIDTH);
    this.setFitHeight(ENEMY_HEIGHT);
    this.setStatus(status);
    this.setImage();
    this.setdX(-8);
    enermyMoveLoop();

  }

  public void setImage() {
    this.setImage(images[this.getStatus()]);
  }

  public void enermyMoveLoop() {

    Timeline timeline = new Timeline(

        new KeyFrame(Duration.millis(10), event -> {
          // move
          if (this.getX() < Math.min(this.getEndX(), this.getStartX())
              || this.getX() > Math.max(this.getEndX(),
                  this.getStartX())) {
            // define boundary
            this.setdX(-this.getdX());
          }
          this.setX(this.getX() + this.getdX());
        }),
        new KeyFrame(Duration.seconds(0.2), event -> {
          // change piture
          status = status++ / 2;
          this.setStatus(status);
          this.setImage();
        }));

    timeline.setCycleCount(Animation.INDEFINITE);
    timeline.play();

  }

  public void enermyDeath() {
    this.setStatus(2);
    this.setImage();
  }

  public static double getEnemyWidth() {
    return ENEMY_WIDTH;
  }

  public static double getEnemyHeight() {
    return ENEMY_HEIGHT;
  }

  public double getStartX() {
    return startX;
  }

  public void setStartX(double startX) {
    this.startX = startX;
  }

  public double getStartY() {
    return startY;
  }

  public void setStartY(double startY) {
    this.startY = startY;
  }

  public double getEndX() {
    return endX;
  }

  public void setEndX(double endX) {
    this.endX = endX;
  }

  public double getEndY() {
    return endY;
  }

  public void setEndY(double endY) {
    this.endY = endY;
  }

}
