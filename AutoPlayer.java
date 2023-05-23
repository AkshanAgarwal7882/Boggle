
import java.util.*;

/** Class simulates the computer player. Finds all possible words that can be formed 
 * on a particular board by forming all possible letter combinations that are not dead ends
 * and checking it against a given lexicon.
 * @author Akshan Agarwal
 * @author Henry Russell
 * @time 1 week
 */

public class AutoPlayer extends AbstractAutoPlayer {
    /**
    * A method that finds all possible words on a boggle board
    * 
    * @param board the BoggleBoard with which we are playing on
    * @param lex an object from the ILexicon interface that allows us to check if a word
    * can potentially be formed from a given path
    * @return a list of all possible words
    */
    public List<String> findAllValidWords(BoggleBoard board, ILexicon lex) {
        // IMPLEMENT ME!
    	// Looks in the Boggle board and finds all words that can be formed
    	// (given the lexicon), using a recursive procedure.
        
        // For now, we simply return an empty list. Thus, the program
        // will not actually find any words on the board for now. Replace the 
        // following return statement once you complete your method definition.

       
       
        List<String> list = new ArrayList<String>();
        
        
        for(int r = 0; r < board.size(); r++) {
            for(int c = 0; c < board.size(); c++){
                wordsFromIndex("", r,c, board, list, new ArrayList<BoardCell>(), lex);
          
            }
        }
        return list;
    }
    
    /** a helper function that returns all the possible words that can be found
      * by starting from a specific cell on a boggle board.
      * @param s the string of the word we want to check
      * @param row the row we are looking at
      * @param col row the row we are looking at
      * @param board the BoggleBoard we are playing on
      * @param list a list of words we have found on our board
      * @param lst a list of boggle cells we have used
      * @param lex an object from the ILexicon interface that allows us to check if a word
      * can potentially be formed from a given path
      * @return false if our current word path is a dead-end
      */
     
    public boolean wordsFromIndex(String s, int row, int col, BoggleBoard board, List<String> list, List<BoardCell> lst, ILexicon lex){
        
        //base case: if we have a word, add it to the dic
        //if lst already has row and col, quit
        if (lex.wordStatus(s) == (LexStatus.WORD) && !list.contains(s)) // if the current wordpath is a word not in list it adds it to the list
        {
             list.add(s);
        }
        if((lex.wordStatus(s) == (LexStatus.NOT_WORD)))// base case: if the current wordpath is a dead-end returns false
        {
            return false;
        }

            BoardCell cell = new BoardCell(row, col);
            if(lst.contains(cell) == false){// checks to ensure cells are not reused
                lst.add(cell);
            String str = board.getFace(row, col);
                if(str.equals("q")){
                str = "qu";
                }

            String newWord = s+str;
            int[] colNext = {-1, 0,1,-1,1,-1,0,1};
            int[] rowNext= {-1,-1,-1,0,0,1,1,1};

            
            for (int i = 0; i < colNext.length; i++){ //loop through all neighbors
                    
                    if(!isOutOfBounds(board,row+rowNext[i],col+colNext[i])){ //make sure the neighbor is in bounds
                            
                            wordsFromIndex(newWord, row+rowNext[i], col+colNext[i], board, list, lst, lex); //recursive call
                            
                    
                    }
                    
                }
                lst.remove(cell);// removes the cell from the list to try other wordpaths

                if(s.length() > 1){// substrings the string to try other wordpaths
                    s = s.substring(0, s.length() - 1);
                }

            }

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
            if ((col >= board.size()) || (row >= board.size()) ||(col<0) || (row <0)){ // checks if the row or column number is greater than the number of rows and columns
                return true;
            }
            return false;
    }
}
