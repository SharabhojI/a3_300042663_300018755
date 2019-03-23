import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GridButton extends JButton {


    private int row;
    private int column;
    private boolean isOn;
    private boolean isClicked;


    /**
     * Constructor used for initializing a GridButton at a specific
     * Board location.
     * 
     * @param column
     *            the column of this Cell
     * @param row
     *            the row of this Cell
     */

    public GridButton(int column, int row) {

        setBackground(Color.WHITE);

        this.column = column;
        this.row = row;

        setIcon(new ImageIcon(GridIcon()));


    }

   /**
    * sets the icon of the button to reflect the
    * state specified by the parameters
    * @param isOn true if that location is ON
    * @param isClicked true if that location is
    * tapped in the model's current solution
    */ 
    public void setState(boolean isOn, boolean isClicked) {

        this.isOn = isOn;
        this.isClicked = isClicked;
    }

 

    /**
     * Getter method for the attribute row.
     * 
     * @return the value of the attribute row
     */

    public int getRow() {
        return row;
    }

    /**
     * Getter method for the attribute column.
     * 
     * @return the value of the attribute column
     */

    public int getColumn() {
        return column;
    }

    private Image GridIcon(){

        try{
            return ImageIO.read(getClass().getResource("Icons/Light-1.png"));
        }
        catch (IOException e){
            e.printStackTrace();
        }

        return null;
    }
}
