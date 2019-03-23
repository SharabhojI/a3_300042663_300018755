public class GameModel {

 // Your code here
	private int width, height, steps;
	private Solution board;

	public GameModel(int width, int height){
		this.height = height;
		this.width = width;

		board = new Solution(width, height);
		reset();
	}

	public int getHeight(){
		return this.height;
	}

	public int getWidth(){
		return this.width;
	}

	public boolean isON(int i, int j){
		// boolean result = false;

		// if (board[i][j] == true){
		// 	result = true;
		// }

		// return result;

		return false;
	}

	public void reset(){
		// for(int i = 0; i < getHeight(); i++){
		// 	for(int j = 0; j < getWidth(); j++){
		// 		board[i][j] = false;
		// 	} 
		// }
		// this.steps = 0;

	}

	public void set(int i, int j, boolean value){
		//board[i][j] = value;
	}

	public String toString(){
		return(board.toString());
	}

	public void click(int i, int j){
		this.steps += 1;
		//todo

	}

	public int getNumberOfSteps(){
		return steps;
	}

	public boolean isFinished(){
		//todo
		return true;
	}

	public void setSolution(){
		//todo
	}

	public boolean solutionSelects(int i, int j){
		//todo
		return true;
	}
}
