public class InteractiveObject extends GameObject {
  private String type; // {"what", }
  private boolean isGenerated;

  public InteractiveObject(String url, String type) {
    super(url);
    this.type = type;
    isGenerated = false;
  }

  public void generateObject(GameScreen gameScreen) {
    if (!isGenerated) {
      Level currentLevel = gameScreen.getCurrentLevel();
      if ("what".equals(type)) {
        // generate a new coin
        GameObject coin0 = new Collections(this.getX() + 0.5 * currentLevel.getGroundWidth(),
            (this.getY() - 1.5 * currentLevel.getGroundHeight()));
        currentLevel.addGameObjects(coin0, currentLevel.getCollections());
        gameScreen.getPaneCenter().getChildren().add(coin0);
      }
      // set isGenerated
      isGenerated = true;
    }

  }

}
