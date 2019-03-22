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
		for(int i = 0; i < getHeight(); i++){
			for(int j = 0; j < getWidth(); j++){
				board[i][j] = false;
			} 
		}
		this.steps = 0;
	}

	public void set(int i, int j, boolean value){
		board[i][j] = value;
	}

	public String toString(){

        String boardStr = "";
        for (int i = 0; i<this.height; i++){
            boardStr = boardStr + "[";
            for (int j = 0; j<this.width; j++){
                boardStr = boardStr + this.board[i][j];

                if (j != (this.width - 1)){
                    boardStr = boardStr + ",";
                }
            }

            boardStr = boardStr + "]\n";
        }

        return boardStr;
    }
}
