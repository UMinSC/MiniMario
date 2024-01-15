import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class Collections extends GameObject {
  private Image[] images;
  private int i = 0;

  public Collections(double midX, double y) {
    super(midX - 15, y);
    images = new Image[4];
    for (int i = 0; i < 4; i++) {
      images[i] = new Image(("media/images/coin" + i + ".png"));
    }
    CollectionAnimation();
  }

  public void CollectionAnimation() {

    Timeline timeline = new Timeline(
        new KeyFrame(Duration.seconds(0.2), event -> {
          // change piture
          i = ++i % 4;
          this.setImage(images[i]);
        }));

    timeline.setCycleCount(Animation.INDEFINITE);
    timeline.play();

  }

}
