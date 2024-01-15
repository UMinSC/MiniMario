
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class GameScreen extends BorderPane {

  private MiniMarioGame newGame;
  private Level currentLevel;
  private boolean isGameOver = false, isGameWin = false;
  public Mario mario;
  HBox paneTop;
  Pane paneCenter;
  Timer timer;
  ImageView coinLogo = new ImageView(new Image("media/images/coinLogo.png"));
  ImageView playerLogo = new ImageView(new Image("media/images/player.png"));

  public GameScreen(MiniMarioGame newGame) {
    super();
    this.newGame = newGame;
    coinLogo.setFitWidth(30);
    coinLogo.setFitHeight(30);
    playerLogo.setFitHeight(30);
    playerLogo.setFitWidth(30);

    int levelNumber = 1;
    paneTop = new HBox();
    paneCenter = new Pane();
    paneTop.setSpacing(50);
    loadLevel(levelNumber);

    setTop(paneTop);
    setCenter(paneCenter);

  }

  // load game screen resource in different level___________________
  private void loadLevel(int levelNumber) {
    addBackGround(levelNumber);
    loadGameResource(1);
    loadTopBox(levelNumber);
  }

  private void loadTopBox(int levelNumber) {
    // 3 labels
    Label playerName = new Label(newGame.getCurrentPlayer().getName(), playerLogo);
    playerName.setContentDisplay(ContentDisplay.LEFT);
    Label currentCoin = new Label("Current Coin: " + getMario().getCoin(), coinLogo);
    currentCoin.textProperty().bind(getMario().coinProperty().asString());
    timer = new Timer(levelNumber, this);
    // set font color and size
    setLabelLayout(playerName);
    setLabelLayout(currentCoin);
    setLabelLayout(timer);
    paneTop.getChildren().addAll(playerName, currentCoin, timer);

  }

  private void setLabelLayout(Label label) {
    Color white = Color.WHITE;
    label.setTextFill(white);
    label.setFont(new Font(20));
  }

  private void loadGameResource(int levelNumber) {
    currentLevel = new Level(levelNumber);
    paneCenter.getChildren().clear();

    for (GameObject gameObject : currentLevel.getGameObjects()) {
      paneCenter.getChildren().add(gameObject);
    }
    mario = new Mario(50, 400, currentLevel);
    paneCenter.getChildren().add(mario);
  }

  private void addBackGround(int levelNumber) {
    GameObject bg = new GameObject("media/images/bgLevel0" + levelNumber + ".png");
    bg.setX(0);
    bg.setY(0);
    getChildren().add(bg);
  }

  public Mario getMario() {
    return mario;
  }

  public MiniMarioGame getNewGame() {
    return newGame;
  }

  public void setNewGame(MiniMarioGame newGame) {
    this.newGame = newGame;
  }

  public Level getCurrentLevel() {
    return currentLevel;
  }

  public void setCurrentLevel(Level currentLevel) {
    this.currentLevel = currentLevel;
  }

  public void displayGameWin() {
    Text gameWin = new Text("You Win!");
    gameWin.setFont(new Font(150));
    gameWin.setFill(Color.YELLOW);

    gameWin.setX(200);
    gameWin.setY(300);
    getChildren().add(gameWin);

    // set player Best Score
    newGame.getCurrentPlayer().setBestScore(getMario().getCoin());

    // add button
    Button restartBtn = new Button("restart this game");
    Button chooseBtn = new Button("back to choose screen");
    restartBtn.setMinWidth(180);
    chooseBtn.setMinWidth(180);
    HBox btnBox = new HBox(10, restartBtn, chooseBtn);
    btnBox.setAlignment(Pos.CENTER);
    btnBox.setLayoutX(500);
    btnBox.setLayoutY(400);
    btnBox.setPrefWidth(500);
    getChildren().add(btnBox);

    restartBtn.setOnAction(e -> {
      newGame.showGameScreen();
    });

    chooseBtn.setOnAction(e -> {
      newGame.getLoginScreen().addScoreBoard();
      newGame.showLoginScreen();
    });

    timer.stopTimer();

  }

  public void setMario(Mario mario) {
    this.mario = mario;
  }

  public boolean isGameOver() {
    return isGameOver;

  }

  public void setGameOver() {
    this.isGameOver = true;
    newGame.showGameOverScreen();
  }

  public boolean isGameWin() {
    return isGameWin;
  }

  public void setGameWin() {
    this.isGameWin = true;

    displayGameWin();
  }

  public Pane getPaneCenter() {
    return paneCenter;
  }

}
