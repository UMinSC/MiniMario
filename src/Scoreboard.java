import java.util.ArrayList;
import java.util.Collections;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class Scoreboard {
  private ArrayList<Player> playerList;

  public Scoreboard() {
    playerList = new ArrayList<>();
    Player player1 = new Player("player01");
    player1.setBestScore(2);
    addPlayer(player1);
    Player player2 = new Player("player02");
    player2.setBestScore(3);
    addPlayer(player2);
    Player player3 = new Player("player03");
    addPlayer(player3);
  }

  // manage player__________________________
  public void addPlayer(Player player) {
    playerList.add(player);
  }

  public void sortedPlayers() {
    if (playerList.size() > 1)
      Collections.sort(playerList, (p1, p2) -> Integer.compare(p2.getBestScore(), p1.getBestScore()));

  }

  // display score board tabel__________________________
  public VBox getChart() {
    VBox tableView = new VBox();
    tableView.setSpacing(10);
    sortedPlayers();
    tableView.getChildren().add(headerHBox());
    tableView.getChildren().add(new Separator());
    tableView.getChildren().add(scoreboardRows(this.playerList));
    return tableView;
  }

  private HBox headerHBox() {
    HBox headerHBox = new HBox();
    headerHBox.setSpacing(20);
    String[] headers = { "Rank", "Player", "Best Score" };
    for (int i = 0; i < headers.length; i++) {
      Label headLabel = formatLabel(headers[i]);
      headLabel.setStyle("-fx-font-weight: bold");
      headerHBox.getChildren().add(headLabel);
    }
    return headerHBox;
  }

  private VBox scoreboardRows(ArrayList<Player> players) {
    VBox vbox = new VBox();
    vbox.setSpacing(10);
    int rank = 1;
    for (Player player : players) {
      HBox rowHBox = new HBox();
      rowHBox.setSpacing(20);

      Label rankLabel = formatLabel(Integer.toString(rank));
      Label nameLabel = formatLabel(player.getName());
      Label bestScoreLabel = formatLabel(Integer.toString(player.getBestScore()));

      rowHBox.getChildren().addAll(rankLabel, nameLabel, bestScoreLabel);
      vbox.getChildren().add(rowHBox);
      rank++;
    }

    return vbox;
  }

  // creat scoreboard new label in the same format
  private Label formatLabel(String string) {
    Label fLabel = new Label(String.format("%-15s", string));
    fLabel.setAlignment(Pos.CENTER);
    fLabel.setTextFill(Color.WHITE);
    fLabel.setPrefWidth(150);
    return fLabel;

  }

  public ArrayList<Player> getPlayerList() {
    return playerList;
  }

  public void setPlayerList(ArrayList<Player> playerList) {
    this.playerList = playerList;
  }

}
