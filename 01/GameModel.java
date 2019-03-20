public class GameModel {

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
		return(board.oddNeighborhood(i, j));
	}

	public void reset(){
		for(int i = 0; i < getHeight(); i++){
			for(int j = 0; j < getWidht(); j++){
				board[i][j] = false;
			} 
		}
		this.steps = 0;
	}

	public void set(int i, int j, boolean value){
		board[i][j] = value;
	}

	public String toString(){
		return(board.toString());
	}
}
