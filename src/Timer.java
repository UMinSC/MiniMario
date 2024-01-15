
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Timer extends Label {
  private int countTime;
  private Timeline timeline;
  private GameScreen gameScreen;
  private MiniMarioGame newGame;
  private int[] countTimeSetting = { 60, 90, 120 };
  ImageView timerLogo = new ImageView(new Image("media/images/timerLogo.png"));

  private int timeLeft;
  private boolean isTimeEnd = false;

  public Timer(int levelNumber, GameScreen gameScreen) {
    super();
    this.gameScreen = gameScreen;
    this.countTime = countTimeSetting[levelNumber - 1];
    this.timeLeft = countTime;

    timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
      this.timeLeft--;
      this.setText(formatTime(timeLeft));
      timerLogo.setFitHeight(30);
      timerLogo.setFitWidth(30);
      this.setGraphic(timerLogo);
      this.setContentDisplay(ContentDisplay.LEFT);

      if (timeLeft <= 0) {
        gameScreen.setGameOver();
      }
    }));

    timeline.setCycleCount(countTime); // Count down from 60 seconds
    timeline.play();
  }

  public boolean isTimeEnd() {
    return isTimeEnd;
  }

  private String formatTime(int timeLeft) {
    int minutes = timeLeft / 60;
    int remainingSeconds = timeLeft % 60;
    return String.format("Time Left: %02d:%02d", minutes, remainingSeconds);
  }

  public void stopTimer() {
    if (timeline != null) {
      timeline.stop();
    }
  }

}
