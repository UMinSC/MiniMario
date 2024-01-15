import java.util.ArrayList;

public class Level {
  private int levelNumber;
  private ArrayList<GameObject> gameObjects = new ArrayList<>();
  private ArrayList<GameObject> blockOjects = new ArrayList<>();
  private ArrayList<GameObject> interactiveObjects = new ArrayList<>();
  private ArrayList<GameObject> enermies = new ArrayList<>();
  private ArrayList<GameObject> collections = new ArrayList<>();
  private GameObject flagpole;
  private double groundWidth = 50, groundHeight = 25;

  public Level(int levelNumber) {
    // this.newGame = newGame;
    this.levelNumber = levelNumber;
    loadLevelResources();

  }

  public void setNumber(int number) {
    this.levelNumber = number;
  }

  public int getLevelNumber() {
    return levelNumber;
  }

  public ArrayList<GameObject> getGameObjects() {
    return this.gameObjects;
  }

  public void addGameObjects(GameObject object, ArrayList<GameObject> list) {
    list.add(object);
  }

  private void loadLevelResources() {
    // Load resources for the current level, such as images for ground, bricks,
    // water, enemies, etc.

    // level 1
    if (levelNumber == 1) {
      // initial map

      int mapX = (int) Math.round(MiniMarioGame.WIDTH / groundWidth);
      int mapY = (int) Math.round(MiniMarioGame.HEIGHT / groundHeight);
      int[][] map = new int[mapY][mapX];
      for (int y = 0; y < mapY; y++) {
        for (int x = 0; x < mapX; x++) {
          map[y][x] = 0;
        }
      }

      // add flagpole
      flagpole = new GameObject("media/images/flagpole.png");
      int flagpoleHeight = 368;
      flagpole.setX((mapX - 2) * groundWidth);
      flagpole.setY((mapY - 2) * groundHeight - flagpoleHeight);
      this.addGameObjects(flagpole, gameObjects);

      // collection list________________________________________
      int coinX = 6, coinY = 6;
      GameObject coin1 = new Collections((coinX + 0.5) * groundWidth, (coinY - 0.5) * groundHeight);
      this.addGameObjects(coin1, collections);

      // blockOjects list________________________________________
      // add ground
      for (int y = mapY - 2; y < mapY; y++) {
        for (int x = 0; x < mapX; x++) {
          GameObject ground = new GameObject("media/images/ground.png");
          ground.setX(x * groundWidth);
          ground.setY(y * groundHeight);
          map[y][x] = 1;
          this.addGameObjects(ground, blockOjects);
        }
      }
      // add bricks
      for (int x : new int[] { 3, 5, 6, 10, 11, 12, 13, 14, 15 }) {
        int y = 12;
        GameObject brick = new GameObject("media/images/brick.png");
        brick.setX(x * groundWidth);
        brick.setY(y * groundHeight);
        map[y][x] = 2;
        this.addGameObjects(brick, blockOjects);
      }
      for (int x : new int[] { 5, 6, 7, 12, 14 }) {
        int y = 7;
        GameObject brick = new GameObject("media/images/brick.png");
        brick.setX(x * groundWidth);
        brick.setY(y * groundHeight);
        map[y][x] = 2;
        this.addGameObjects(brick, blockOjects);
      }

      // interactiveObjects list________________________________________
      // add whatRectangle
      int[][] whats = { { 12, 4 }, { 7, 13 } };
      for (int i = 0; i < whats.length; i++) {
        GameObject whatRectangle = new InteractiveObject("media/images/what.png", "what");
        whatRectangle.setX(whats[i][1] * groundWidth);
        whatRectangle.setY(whats[i][0] * groundHeight);
        this.addGameObjects(whatRectangle, interactiveObjects);
      }

      // enermies list________________________________________
      // add enermy
      Enemy enemy1 = new Enemy(8 * groundWidth, 18 * groundHeight - Enemy.getEnemyHeight() + 5, 3 * groundWidth,
          18 * groundHeight - Enemy.getEnemyHeight());
      this.addGameObjects(enemy1, enermies);
      Enemy enemy2 = new Enemy(15 * groundWidth, 12 * groundHeight - Enemy.getEnemyHeight(), 10 * groundWidth,
          12 * groundHeight - Enemy.getEnemyHeight());
      this.addGameObjects(enemy2, enermies);

      // add all lists to gameObjects list
      gameObjects.addAll(blockOjects);
      gameObjects.addAll(interactiveObjects);
      gameObjects.addAll(enermies);
      gameObjects.addAll(collections);
    }

  }

  public ArrayList<GameObject> getBlockOjects() {
    return blockOjects;
  }

  public void setBlockOjects(ArrayList<GameObject> blockOjects) {
    this.blockOjects = blockOjects;
  }

  public ArrayList<GameObject> getInteractiveObjects() {
    return interactiveObjects;
  }

  public void setInteractiveObjects(ArrayList<GameObject> interactiveObjects) {
    this.interactiveObjects = interactiveObjects;
  }

  public ArrayList<GameObject> getEnermies() {
    return enermies;
  }

  public void setEnermies(ArrayList<GameObject> enermies) {
    this.enermies = enermies;
  }

  public ArrayList<GameObject> getCollections() {
    return collections;
  }

  public void setCollections(ArrayList<GameObject> collections) {
    this.collections = collections;
  }

  public GameObject getFlagpole() {
    return flagpole;
  }

  public void setFlagpole(GameObject flagpole) {
    this.flagpole = flagpole;
  }

  public double getGroundWidth() {
    return groundWidth;
  }

  public void setGroundWidth(double groundWidth) {
    this.groundWidth = groundWidth;
  }

  public double getGroundHeight() {
    return groundHeight;
  }

  public void setGroundHeight(double groundHeight) {
    this.groundHeight = groundHeight;
  }

}
