import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.*;
import javax.swing.*;


// YOUR OTHER IMPORTS HERE IF NEEDED

/**
 * The class <b>GameController</b> is the controller of the game. It is a listener
 * of the view, and has a method <b>play</b> which computes the next
 * step of the game, and  updates model and view.
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */


public class GameController implements ActionListener, ItemListener {

    private GameView gameView;
    private GameModel gameModel;
    private int height;
    private int width;
    private boolean[][] board;


    /**
     * Constructor used for initializing the controller. It creates the game's view 
     * and the game's model instances
     * 
     * @param width
     *            the width of the board on which the game will be played
     * @param height
     *            the height of the board on which the game will be played
     */
    public GameController(int width, int height) {

        this.gameModel = new GameModel(width,height);
        this.gameView = new GameView(gameModel, GameController.this);

        board = new boolean[gameModel.getHeight()][gameModel.getWidth()];

        for (int i = 0; i<gameModel.getHeight(); i++){
            for (int j = 0; j<gameModel.getWidth(); j++){

                board[i][j] = gameModel.isON(i,j);
            }
        }        
    }


    /**
     * Callback used when the user clicks a button (reset, 
     * random or quit)
     *
     * @param e
     *            the ActionEvent
     */

    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() instanceof GridButton){
            GridButton button = (GridButton)e.getSource();
            button.setState(!gameModel.isON(button.getRow(), button.getColumn()), gameModel.solutionSelects(button.getRow(), button.getColumn()));
        }

        if(e.getActionCommand().equals("Reset")) {
            gameModel.reset();
            gameView.update();

        }

        if(e.getActionCommand().equals("Quit")) {
            //gameView.quit();

        }

        if(e.getActionCommand().equals("Random")) {
            gameModel.randomize();
            gameView.update();

        }

        for (int i = 0; i<gameModel.getHeight(); i++){
            for (int j = 0; j<gameModel.getWidth(); j++){

                if (e.getActionCommand().equals(board[i][j])){
                    gameModel.click(i,j);
                    gameView.update();
                }
            }
        }

    }

    /**
     * Callback used when the user select/unselects
     * a checkbox
     *
     * @param e
     *            the ItemEvent
     */

    public void itemStateChanged(ItemEvent e){

        // YOU CODE HERE
        JCheckBox box = (JCheckBox)e.getSource(); 
        if(e.getStateChange() == ItemEvent.SELECTED){
        	box.setSelected(true);
        	gameModel.setSolution();
            gameView.update();
        }
        else{
        	box.setSelected(false);
        }
    }

    // YOUR OTHER METHODS HERE

}
