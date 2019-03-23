public class GameModel {

	private int width, height, steps;

	/**
     * our board. board[i][j] is true is in this
     * solution, the cell (j,i) is tapped
     */
	private boolean[][] board;

	/**
     * Constructor. Creates an instance of GameModel 
     * for a board of size <b>heightxwidth</b>. That 
     * solution does not have any board position
     * value explicitly specified yet.
     *
     * @param width
     *  the width of the board
     * @param height
     *  the height of the board
     */
	public GameModel(int width, int height){
		this.height = height;
		this.width = width;
		this.steps = 0;
		board = new boolean[height][width]; 
	}

	/**
     * Getter for height.
     */
	public int getHeight(){
		return this.height;
	}
	/**
     * Getter for width.
     */
	public int getWidth(){
		return this.width;
	}

	/**
	* Returns true if the location at row i and column j
	* is ON, false otherwise.
	*/
	public boolean isON(int i, int j){
		boolean result = false;

		if (board[i][j] == true){
			result = true;
		}

		return result;
	}

	/**
	* Resets the model to all OFF.
	*/
	public void reset(){
		for(int j = 0; j < getHeight(); j++){
			for(int i = 0; i < getWidth(); i++){
				board[j][i] = false;
			} 
		}
		this.steps = 0;
	}

	/**
	* Sets the location (i,j) of the model to value.
	*/
	public void set(int i, int j, boolean value){
		board[j][i] = value;
	}

	/**
	* Returns a String representation of the model.
	*/
	public String toString(){

        String boardStr = "";
        for (int i = 0; i<height; i++){
        	boardStr = boardStr + "[";

        	for (int j = 0; j<width; j++){
        		boardStr = boardStr + board[i][j];

        		if (j != width-1){
        			boardStr = boardStr + ",";
        		}
        	}

        	boardStr = boardStr + "]\n";
        }
        return boardStr;
    }

    /**
     * Updates the model after the dot at location row i and column j is clicked. 
     * It does change the state of that dot and the state of its neighborhood 
     * according to the logic of the game.
     */
    public void click(int i, int j){

    }

    /**
     * The number of times the method click has been called since the last reset 
     * (or since the beginning if the game was never reset).
     */
    public int getNumberOfSteps(){
    	return steps;
    }

    /**
     * Restarts the game with a solvable random board instead of an all OFF board.
     */
    public void randomize(){

    }

    /**
     * Forces the model to find a minimal size instance of Solution for the current model.
     */
    public void setSolution(){
    	
    }
}
