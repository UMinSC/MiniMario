import java.util.ArrayList;
import java.util.Iterator;

public class CheckManager {
  GameScreen gameScreen;
  Level currentLevel;
  Mario mario;
  private ArrayList<GameObject> gameObjects;
  private ArrayList<GameObject> blockOjects;
  private ArrayList<GameObject> interactiveObjects;
  private ArrayList<GameObject> enermies;
  private ArrayList<GameObject> collections;
  private GameObject flagpole;

  public CheckManager(GameScreen gameScreen) {
    this.gameScreen = gameScreen;
    mario = gameScreen.getMario();
    currentLevel = gameScreen.getCurrentLevel();
    gameObjects = currentLevel.getGameObjects();
    blockOjects = currentLevel.getBlockOjects();
    interactiveObjects = currentLevel.getInteractiveObjects();
    enermies = currentLevel.getEnermies();
    collections = currentLevel.getCollections();
    flagpole = currentLevel.getFlagpole();

  }

  public void update() {

    // Check for collisions and handle game logic based on the level's elements
    // check flagpole
    if (mario.intersects(flagpole)) {
      gameScreen.setGameWin();
    }

    Iterator<GameObject> collectionsIterator = collections.iterator();
    while (collectionsIterator.hasNext()) {
      GameObject coin = collectionsIterator.next();
      if (mario.intersects(coin)) {
        mario.winCoin();
        collectionsIterator.remove();
        gameScreen.getPaneCenter().getChildren().remove(coin);
      }
    }

    // block------------------------------------------------
    // Iterator<GameObject> blocksIterator = blockOjects.iterator();
    // while (blocksIterator.hasNext()) {
    // GameObject block = blocksIterator.next();
    // if (mario.intersects(block)) {
    // // Mario touches block's top side
    // mario.setFalling(!(mario.getY() + mario.getHeight() >= block.getY()));
    // // Mario touches block's bottom side
    // if (mario.getY() >= block.getY() + block.getHeight()
    // && mario.getY() - mario.getdY() <= block.getY() + block.getHeight()) {
    // mario.setCanMoveUp(false);
    // }
    // // Mario touches block's left side
    // if (mario.getX() + mario.getWidth() >= block.getX()) {
    // mario.setCanMoveRight(false);
    // }
    // // Mario touches block's right side
    // else if (mario.getX() <= block.getX() + block.getWidth()) {
    // mario.setCanMoveLeft(false);
    // }

    // } else {
    // // if mario is not intersects with any block, it is on the air and could fall
    // mario.setFalling(true);
    // }
    // }

    Iterator<GameObject> enermiesIterator = enermies.iterator();
    while (enermiesIterator.hasNext()) {
      GameObject enermy = enermiesIterator.next();
      if (mario.intersects(enermy)) {
        if (mario.getY() + mario.getHeight() - mario.getdY() < enermy.getY()) {// mairo is higher than enermy
          mario.winCoin();
          enermiesIterator.remove();
          gameScreen.getPaneCenter().getChildren().remove(enermy);
        } else {
          // add an animation of mario die
          ((Enemy) enermy).enermyDeath();
          gameScreen.setGameOver();
        }
      }

    }

    for (GameObject whats : interactiveObjects) {
      if (mario.intersects(whats)) {
        if (mario.getY() + mario.getdY() >= whats.getY() + whats.getHeight()) {
          ((InteractiveObject) whats).generateObject(gameScreen);
        }
      }
    }
  }

}
