import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class MiniMarioGame extends Application {
  private Scene scene;
  private LoginScreen loginScreen;
  private GameScreen gameScreen;
  private GameOverScreen gameOverScreen;
  private CheckManager checkManager;

  private Scoreboard scoreboard = new Scoreboard();
  private Player currentPlayer;
  public Mario mario;

  public static final int WIDTH = 1000;
  public static final int HEIGHT = 500;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    // initiate game

    scoreboard = new Scoreboard();

    loginScreen = new LoginScreen(this);
    scene = new Scene(loginScreen, WIDTH, HEIGHT);
    primaryStage.setTitle("Mini Mario");
    primaryStage.setScene(scene);
    primaryStage.show();

    // start at loginScreen
    showLoginScreen();

  }

  public void showLoginScreen() {
    scene.setRoot(loginScreen);
  }

  public void showGameScreen() {
    gameScreen = new GameScreen(this);
    mario = gameScreen.getMario();
    checkManager = new CheckManager(gameScreen);

    scene.setRoot(gameScreen);
    mario.requestFocus();

    mario.setOnKeyPressed(e -> {
      if (e.getCode() == KeyCode.LEFT) {
        mario.moveLeft();
      }
      if (e.getCode() == KeyCode.RIGHT) {
        mario.moveRight();
      }
      if (e.getCode() == KeyCode.UP) {
        mario.moveUp();
      }
      if (e.getCode() == KeyCode.DOWN) {
        mario.moveDown();
      }
      checkManager.update();
      // mario.moveFall();
    });

  }

  public void showGameOverScreen() {
    gameOverScreen = new GameOverScreen(this);
    scene.setRoot(gameOverScreen);
  }

  public void createPlayer(String playerName) {
    Player newPlayer = new Player(playerName);
    scoreboard.addPlayer(newPlayer);
    loginScreen.setSelectedPlayerIndex(getPlayerListSize() - 1);
  }

  public void setCurrentPlayer(Player player) {
    currentPlayer = player;
  }

  public Scoreboard getScoreboard() {
    return scoreboard;
  }

  public void setScoreboard(Scoreboard scoreboard) {
    this.scoreboard = scoreboard;
  }

  public Player getCurrentPlayer() {
    return currentPlayer;
  }

  public int getPlayerListSize() {
    return scoreboard.getPlayerList().size();
  }

  public ArrayList<Player> getPlayerList() {
    return scoreboard.getPlayerList();
  }

  public GameScreen getGameScreen() {
    return gameScreen;
  }

  public void setGameScreen(GameScreen gameScreen) {
    this.gameScreen = gameScreen;
  }

  public LoginScreen getLoginScreen() {
    return loginScreen;
  }

  public void setLoginScreen(LoginScreen loginScreen) {
    this.loginScreen = loginScreen;
  }

}
