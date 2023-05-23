

import java.util.*;


/**
* A class that validates whether a word is on a boggle board.
* @author Akshan Agarwal
* @author Henry Russell
* @time 1 week
*/
public class WordOnBoardFinder implements IWordOnBoardFinder {
    

    /**
    * A method that finds a particular word on a boggle board
    * 
    * @param board the BoggleBoard with which we are playing on
    * @param word the word we need to find on the board
    * @return a list of BoardCells that make up the given word
    */
    public List<BoardCell> cellsForWord(BoggleBoard board, String word) {
        // IMPLEMENT ME!!
    	// Looks in the Boggle board and figures out the board cells needed
    	// to construct a given word, using a recursive procedure.
    	// The method documentation (in the interface) provides additional details.
        
        // For now, we simply return an empty list. Thus, the program
        // will not be able to validate any of the words keyed in by
        // the human player. Replace the following return statement
        // once you complete your method definition.
        List<BoardCell> list = new ArrayList<BoardCell>();
        
        word = word.replaceAll("qu", "q");
        for(int r = 0; r < board.size(); r++) {
            for(int c = 0; c < board.size(); c++){
                // list = new ArrayList<BoardCell>();
                if(wordFromIndex(board, r,c, word, list,0))
                {                   
                    
                    return list;
                }
                
            }
        }
        return list;
    }

    /** a helper function that returns as a boolean wheather we can form a specific word by by starting from a specific cell on a boggle board.
    *
    * @param word the string of the word we want to check
    * @param row the row we are looking at
    * @param col row the row we are looking at
    * @param board the BoggleBoard we are playing on
    * @param words row the row we are looking a
    * @return true if the word can be formed starting at that particular index and false if not
    */
    private Boolean wordFromIndex(BoggleBoard board, int row, int col, String word, List<BoardCell> list, int ind){

        if(ind == word.length()){ //if we made it to the end of the word, return true!
            return true;
          }
        if (board.getFace(row, col).charAt(0) != word.charAt(ind)){ //if the cell doesn't equal the letter of the word we want, false
            return false;
        }
        if (list.contains(new BoardCell(row, col))){ //if we already have the cell in our list, false
            return false;
        }
          
        BoardCell cell = new BoardCell(row, col);
        String str = board.getFace(row, col);
        if(str.equals("q")){
            str = "qu";
         }
        int[] colNext = {-1, 0,1,-1,1,-1,0,1};
        int[] rowNext= {-1,-1,-1,0,0,1,1,1};
      
 
         list.add(cell);  //since we already passed our checks, the tile is the letter we want, so add it to list
         ind++; //this tells us we are now looking for the next letter in our word on the recursive call

            for (int i = 0; i < colNext.length; i++){ //loop through all nieghbors
     
                if(!isOutOfBounds(board,row+rowNext[i],col+colNext[i])){ //make sure the neighbor is in bounds
    
                      if(wordFromIndex(board, row+rowNext[i], col+colNext[i], word, list,ind)){ //recursive call, run method for next letter of word
                      return true;
                }
                }
                
            }

        list.remove(list.size()-1); //backtrack here, so we remove the cell from the list
        ind --; //this makes sure we check for the right index on the word
                  return false;
            
         
        }
                            
                       
    /**
    * A helper method to check if we can access an adjacent boggle cell
    * @param board the board we are playing on
    * @param row the row we are in
    * @param col the column we are in
    * @return true if the cell is out of bounds, false if in bounds
    */
    public Boolean isOutOfBounds(BoggleBoard board, int row, int col){
        if ((col >= board.size()) || (row >= board.size()) ||(col<0) || (row <0)){// checks if the row or column number is greater than the number of rows and columns
            return true;
        }
        return false;
    }
}
