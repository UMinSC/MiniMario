
public class Player {
  private int bestScore;
  private String name;
  private int rank;

  public Player(String name) {
    this.name = name;
    this.bestScore = 0;
  }

  public String getName() {
    return name;
  }

  public int getBestScore() {
    return bestScore;
  }

  public void setBestScore(int newScore) {
    if (newScore > this.bestScore)
      this.bestScore = newScore;
  }

  public int getRank() {
    return rank;
  }

  public void setRank(int rank) {
    this.rank = rank;
  }

}
