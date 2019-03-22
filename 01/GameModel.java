public class GameModel {

	private int width, height, steps;
	private boolean[][] board;

	public GameModel(int width, int height){
		this.height = height;
		this.width = width;
		board = new boolean[height][width]; 
	}

	public int getHeight(){
		return this.height;
	}

	public int getWidth(){
		return this.width;
	}

	public boolean isON(int i, int j){
		boolean result = false;

		if (board[i][j] == true){
			result = true;
		}

		return result;
	}

	public void reset(){
		for(int j = 0; j < getHeight(); j++){
			for(int i = 0; i < getWidth(); i++){
				board[j][i] = false;
			} 
		}
		this.steps = 0;
	}

	public void set(int i, int j, boolean value){
		board[j][i] = value;
	}

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
}
