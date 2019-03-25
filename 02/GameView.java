import javax.swing.*;
import java.awt.*;
import static java.awt.BorderLayout.*;

// your other import here if needed

/**
 * The class <b>GameView</b> provides the current view of the entire Game. It extends
 * <b>JFrame</b> and lays out a matrix of <b>GridButton</b> (the actual game) and 
 * two instances of JButton. The action listener for the buttons is the controller.
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */

public class GameView extends JFrame {

    private GameModel gameModel;
    private GameController gameController;
    private JButton reset, random, quit;
    private JCheckBox solution;
    private JLabel stepCount;
    private JPanel dotPanel, menuPanel, stepsPanel;


    /**
     * Constructor used for initializing the Frame
     * 
     * @param gameModel
     *            the model of the game (already initialized)
     * @param gameController
     *            the controller
     */

    public GameView(GameModel gameModel, GameController gameController) {

        this.gameModel = gameModel;
        this.gameController = gameController;

        GridLayout dotGrid = new GridLayout(gameModel.getHeight(), gameModel.getWidth());
        dotPanel = new JPanel(dotGrid);

        GridLayout menuGrid = new GridLayout(4,1);
        menuPanel = new JPanel(menuGrid);

        GridLayout stepsGrid = new GridLayout(1,1);
        stepsPanel = new JPanel(stepsGrid);

        reset = new JButton("Reset");
        random = new JButton("Random");
        quit = new JButton("Quit");

        solution = new JCheckBox("Solution");
        solution.addItemListener(this.gameController);

        stepCount = new JLabel("Number of steps: " + gameModel.getNumberOfSteps());

        menuPanel.add(reset);
        menuPanel.add(random);
        menuPanel.add(solution);
        menuPanel.add(quit);

        update();

        setSize(1500,1500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(menuPanel, EAST);
        add(stepCount, SOUTH);
        pack();

        setVisible(true);

    }

    /**
     * updates the status of the board's GridButton instances based 
     * on the current game model, then redraws the view
     */

    public void update(){

        remove(dotPanel);

        GridLayout dotGrid = new GridLayout(gameModel.getHeight(), gameModel.getWidth());
        dotPanel = new JPanel(dotGrid);

        for (int i = 0; i<gameModel.getHeight(); i++){
            for (int j = 0; j<gameModel.getWidth(); j++){
                GridButton b = new GridButton(j,i);
                dotPanel.add(b);
            }
        }

        add(dotPanel, WEST);
        revalidate();

        //steps.setText("Number of steps: ");

    }

    /**
     * returns true if the ``solution'' checkbox
     * is checked
     *
     * @return the status of the ``solution'' checkbox
     */

    public boolean solutionShown(){

        // YOUR CODE HERE
        return solution.isSelected();
    }

}
