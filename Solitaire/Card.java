
/**
 * Represents a card in a standard deck with a suit and rank with the following methods:
 * Card, getRank, getSuit, isRed, isFaceUp, turnUp, turnDown, getFileName, 
 * @author Allison John
 * @version 1-8-18
 */
public class Card
{
    private int rank;
    private String suit;
    private boolean isFaceUp;

    /**
     * Constructor for objects of class Card
     * @param r the rank
     * @param s the suit
     */
    public Card(int r, String s)
    {
        // initialise instance variables
        rank = r;
        suit = s;
        isFaceUp = false;
    }

    /**
     * Accessor method returns the card's rank
     * @return the rank 
     */
    public int getRank()
    {
        return rank;
    }
    
    /**
     * Accessor method returns the card's suit
     * @return the suit
     */
    public String getSuit()
    {
        return suit;
    }
    
    /**
     * Whether or not the card's suit is red (a diamond or heart)
     * @return true if it's red, otherwise false 
     */
    public boolean isRed()
    {
        return (suit.equals("d") || suit.equals("h"));
    }
    
    /**
     * Whether or not the card is face up
     * @return true if it's up, otherwise false
     */
    public boolean isFaceUp()
    {
       return isFaceUp;
    }
    
    /**
     * Changes the orientation of the card to face up
     */
    public void turnUp()
    {
        isFaceUp = true;
    }
    
    /**
     * Changes the orientation of the card to face down
     */
    public void turnDown()
    {
        isFaceUp = false;
    }
    
    /**
     * The corresponding file name for the card's suit and rank
     * @return the file name
     */
    public String getFileName()
    {
       if (isFaceUp)
       {
           String str = "cards/";
           //find rank string
           if (rank==1) {   str+="a";  }
           else if (rank>=2 && rank<=9) {  str+=rank;  }
           else if (rank==10) {  str+="t"; }
           else if (rank==11) {  str+="j"; }
           else if (rank==12) {  str+="q"; }
           else if (rank==13) {  str+="k"; }
           //find suit string
           str+=suit+".gif";
           return str;
        }
       return "cards/back.png";
    }
}
