import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;

public class GameOverScreen extends StackPane {
  private MiniMarioGame newGame;

  public GameOverScreen(MiniMarioGame newGame) {
    super();
    this.newGame = newGame;

    // add game over bg
    GameObject bg = new GameObject("media/images/game-over.png");
    bg.setX(0);
    bg.setY(0);
    bg.fitHeightProperty().bind(this.heightProperty());
    bg.fitWidthProperty().bind(this.widthProperty());
    getChildren().add(bg);

    // add button
    Button restartBtn = new Button("restart this game");
    Button chooseBtn = new Button("back to choose screen");
    HBox btnBox = new HBox(10, restartBtn, chooseBtn);
    btnBox.setAlignment(Pos.CENTER);
    btnBox.setTranslateY(100);
    getChildren().addAll(btnBox);
    this.setAlignment(Pos.BOTTOM_CENTER);

    restartBtn.setOnAction(e -> {
      newGame.showGameScreen();
    });

    chooseBtn.setOnAction(e -> {
      newGame.showLoginScreen();
    });
  }

}
