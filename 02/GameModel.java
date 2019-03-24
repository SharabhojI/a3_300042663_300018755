import java.util.Random;

public class GameModel {

	private int width, height, steps;

    private Solution sol;

	/**
     * our board. board[i][j] is true is in this
     * solution, the cell (j,i) is tapped
     */
	private boolean[][] board;

	/**
     * the constructor. Creates an all OFF game of 
     * width width and of height height.
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

    	for (int a = 0; a<this.height; a++){
    		for (int b = 0; b<this.width; b++){

    			board[a][b] = !board[a][b];

    			if (isValid(a,b+1)){
    				board[a][b+1] = !board[a][b+1];
    			}
    			if (isValid(a,b-1)){
    				board[a][b-1] = !board[a][b-1];
    			}
    			if (isValid(a+1,j)){
    				board[a+1][b] = !board[a+1][b];
    			}
    			if (isValid(a-1,b)){
    				board[a-1][b] = !board[a-1][b];
    			}
    		}
    	}

    	steps++;
    }

	private boolean isValid(int i,int j){

        if (i<0 || i>=this.height || j<0 || j>=this.width){
            return false;
        }

        return true;
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
    	GameModel modelo = new GameModel();
    	//gets upper value for random number
    	int upper = (this.getHeight()*this.getWidth());
    	Random rand = new Random();
    	int r = rand.nextInt(upper);
    	int count = 0;
    	while(count < r){
    		for(int i = rand.nextInt(this.getHeight())){
    			for(int j = rand.nextInt(this.getWidth())){
    				int boolval = rand.nextInt(2);
    				if(boolval == 0){
    					modelo.set(i,j,true);
    				}
    				else{
    					modelo.set(i,j,false);
    				}
    			}
    		}
    		count++;
    		Solution sol = new Solution(modelo.getWidth(),modelo.getHeight());
    		if(sol.finish(modelo) == false){
    			this.randomize();
    		}
    		else{
    			this.board = modelo.board;
    		}
    	}
    }

    /**
     * Forces the model to find a minimal size instance of Solution for the current model.
     */
    public void setSolution(){
    	sol = new Solution(this.getWidth(), this.getHeight());
    	sol = LightsOut.solveShortest(this);
    }

    public boolean solutionSelects(int i, int j){
        if(sol != null){
            return(sol.get(j,i)==true);
        }
        return false;
    }
}
