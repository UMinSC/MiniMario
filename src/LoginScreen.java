
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class LoginScreen extends GridPane {
  private MiniMarioGame newGame;
  Button createPlayerBtn, selectPlayerBtn, startGameBtn;
  VBox displayScoreBoard;

  private int selectedPlayerIndex;

  public LoginScreen(MiniMarioGame newGame) {
    this.newGame = newGame;
    selectedPlayerIndex = newGame.getPlayerListSize() - 1;
    // Initialize UI elements and layout for the login screen
    addBackGround();

    ColumnConstraints col1Constraints = new ColumnConstraints(250);
    ColumnConstraints spacerColumn = new ColumnConstraints(150);
    ColumnConstraints col2Constraints = new ColumnConstraints(500);
    getColumnConstraints().addAll(col1Constraints, spacerColumn, col2Constraints);

    addChoosePanel();
    addScoreBoard();

    createPlayerBtn.setOnAction(e -> {
      createPlayerAlert();
    });

    selectPlayerBtn.setOnAction(e -> {

      updateSelectedPlayerColor();
      displayScoreBoard.requestFocus();

      displayScoreBoard.setOnKeyPressed(event -> {
        if (event.getCode() == KeyCode.UP) {
          moveSelectionUp();
        } else if (event.getCode() == KeyCode.DOWN) {
          moveSelectionDown(newGame.getPlayerListSize());
        }
        System.out.println(selectedPlayerIndex);

      });
    });

    startGameBtn.setOnAction(e -> {
      if (selectedPlayerIndex >= 0 && selectedPlayerIndex < newGame.getPlayerListSize()) {
        newGame.setCurrentPlayer(newGame.getPlayerList().get(selectedPlayerIndex));
      }
      newGame.showGameScreen();
      // newGame.showGameOverScreen();
    });

  }

  // add screen layout _________________________________

  public void addBackGround() {
    GameObject bg01 = new GameObject("media/images/bgLevel01.png");
    bg01.setX(0);
    bg01.setY(0);
    getChildren().add(bg01);

  }

  public void addChoosePanel() {
    VBox choosePannel = new VBox();
    choosePannel.setAlignment(Pos.CENTER);
    choosePannel.setSpacing(10);
    ImageView gameLogo = new ImageView("media/images/gameLogo.png");
    gameLogo.setX(500);
    gameLogo.setY(20);

    createPlayerBtn = new Button("Create New Player");
    selectPlayerBtn = new Button("Select Existing Player");
    startGameBtn = new Button("Start Game");

    int buttonWidth = 180;
    createPlayerBtn.setPrefWidth(buttonWidth);
    selectPlayerBtn.setPrefWidth(buttonWidth);
    startGameBtn.setPrefWidth(buttonWidth);

    choosePannel.getChildren().addAll(gameLogo, createPlayerBtn, selectPlayerBtn, startGameBtn);
    add(choosePannel, 0, 0);
  }

  public void addScoreBoard() {
    getChildren().remove(displayScoreBoard);
    displayScoreBoard = newGame.getScoreboard().getChart();
    displayScoreBoard.setAlignment(Pos.CENTER);
    displayScoreBoard.setSpacing(10);
    add(displayScoreBoard, 2, 0);
  }

  private void createPlayerAlert() {
    // Code to handle creating a new player
    TextField playerNameField = new TextField();
    playerNameField.setPromptText("Enter the player name:");

    // create new player alertPane
    Alert createPlayerAlert = new Alert(AlertType.NONE);
    createPlayerAlert.setTitle("Create a new Player");
    createPlayerAlert.getDialogPane().setContent(new HBox());
    HBox hBox = (HBox) createPlayerAlert.getDialogPane().getContent();
    hBox.getChildren().add(playerNameField);
    createPlayerAlert.getButtonTypes().addAll(
        new ButtonType("Submit", ButtonData.OK_DONE),
        new ButtonType("Cancel", ButtonData.CANCEL_CLOSE));

    createPlayerAlert.showAndWait().ifPresent(response -> {
      if (response.getButtonData() == ButtonData.OK_DONE) {
        String inputText = playerNameField.getText();
        newGame.createPlayer(!inputText.isEmpty() ? inputText : "NewPlayer");
        addScoreBoard();
        displayScoreBoard.requestFocus();
      }
    });
  }

  // selected player__________________________
  void moveSelectionUp() {
    if (selectedPlayerIndex > 0) {
      selectedPlayerIndex--;
      updateSelectedPlayerColor();
    }
  }

  void moveSelectionDown(int totalPlayers) {
    if (selectedPlayerIndex < totalPlayers - 1) {
      selectedPlayerIndex++;
      updateSelectedPlayerColor();
    }
  }

  void updateSelectedPlayerColor() {
    VBox scoreboardRows = (VBox) displayScoreBoard.getChildren().get(2);
    for (int i = 0; i < scoreboardRows.getChildren().size(); i++) {
      HBox rowHBox = (HBox) scoreboardRows.getChildren().get(i);
      for (int j = 0; j < rowHBox.getChildren().size(); j++) {
        Label label = (Label) rowHBox.getChildren().get(j);
        if (i == selectedPlayerIndex) {
          label.setTextFill(Color.YELLOW);
        } else {
          label.setTextFill(Color.WHITE);
        }
      }
    }
  }

  public int getSelectedPlayerIndex() {
    return selectedPlayerIndex;
  }

  public void setSelectedPlayerIndex(int selectedPlayerIndex) {
    this.selectedPlayerIndex = selectedPlayerIndex;
  }

}
