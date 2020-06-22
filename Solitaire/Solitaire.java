import java.util.*;
import java.lang.Math;

/**
 * Plays a game of solitaire with methods:
 * main, Solitaire, getStockCard, getWasteCard,getFoundationCard, 
 * getPile, stockClicked, wasteClicked, foundationCLicked, pileClicked
 * @author Allison John
 * @version 1-8-17
 */

public class Solitaire
{
    public static void main(String[] args)
    {
        new Solitaire(); //run the solitare game
    }

    private Stack<Card> stock;
    private Stack<Card> waste;
    private Stack<Card>[] foundations;
    private Stack<Card>[] piles;
    private SolitaireDisplay display;

    /**
     * Constuctror creates the list of foundations, a list of piles, the stock, the waste, and the display
     */
    public Solitaire()
    {
        foundations = new Stack[4];
        for (int i=0; i<foundations.length; i++)
        {
            foundations[i]=new Stack<Card>();
        }
        piles = new Stack[7];
        for (int i=0; i<piles.length; i++)
        {
            piles[i]=new Stack<Card>();
        }
        stock = new Stack<Card>();
        waste = new Stack<Card>();
        createStock();
        deal();
        display = new SolitaireDisplay(this);
    }

    /**
     * Adds all 52 cards to the stock in random order
     */
    private void createStock()
    {
        //create ordered arraylist of cards
        ArrayList<Card> cards = new ArrayList<Card>();
        String[] suits = {"h","s","d","c"};
        for (int i=1; i<=13; i++)
        {
            for (int j=0; j<4; j++)
            {
                cards.add(new Card(i, suits[j]));
            }
        }
        //add the cards in random order to the stock
        while (cards.size()>0)
        {
            int index = (int) (Math.random()*cards.size());
            stock.push(cards.remove(index));
        }
    }

    /**
     * Deals cards to the 7 piles with 7 on index 0, 6 on index 1 etc.
     */
    private void deal()
    {
        for (int i=0; i<7; i++) //the pile
        {
            for (int j=0; j<7-i; j++) //the number of cards
            {
                piles[i].push(stock.pop());
            }
            piles[i].peek().turnUp();
        }
    }

    /**
     * Moves top three cards from the stock to the waste or whatever is left
     */
    private void dealThreeCards()
    {
        for (int i=0; i<3; i++)
        {
            if (!stock.isEmpty())
            {
                waste.push(stock.pop());
                waste.peek().turnUp();
            }
        }
    }

    /**
     * Flips around the waste deck to become the stock
     */
    private void resetStock()
    {
        while (!waste.isEmpty())
        {
            stock.push(waste.pop());
            stock.peek().turnDown();
        }
    }

    /**
     * returns the card on top of the stock,
     * or null if the stock is empty
     */
    public Card getStockCard()
    {
        if (!stock.isEmpty())
        {
            return stock.peek();
        }
        return null;
    }

    /**
     * @return the card on top of the waste,
     *         or null if the waste is empty
     */
    public Card getWasteCard()
    {
        if (!waste.isEmpty())
        {
            return waste.peek();
        }
        return null;
    }

    /**
     * Gets the top card from the foundation at the index if there is one
     * @precondition  0 <= index < 4
     * @return returns the card on top of the given
    foundation, or null if the foundation
    is empty
     */
    public Card getFoundationCard(int index)
    {
        if (!foundations[index].isEmpty())
        {
            return foundations[index].peek();
        }
        return null;
    }

    /**
     * Gets stack of cards from the pile
     * @precondition  0 <= index < 7
     * @return a reference to the given pile
     */
    public Stack<Card> getPile(int index)
    {
        return piles[index];
    }

    /**
     * called when the stock is clicked, deals three cards to the waste or resets
     */
    public void stockClicked()
    {
        if (!display.isWasteSelected() && !display.isPileSelected())
        {
            if (!stock.isEmpty())
            {
                dealThreeCards();
            }
            else
            {
                resetStock();
            }
        }
        System.out.println("stock clicked");
    }

    /**
     * called when the waste is clicked, selects or unselects it
     */
    public void wasteClicked()
    {
        if (display.isWasteSelected())
            display.unselect();
        else if (!display.isPileSelected() && !waste.isEmpty())
            display.selectWaste();
        System.out.println("waste clicked");
    }

    /**
     * called when given foundation is clicked, adds the card from the selected deck
     * @precondition  0 <= index < 4
     */
    public void foundationClicked(int index)
    {
        if (display.isPileSelected())
        {
            if (!piles[display.selectedPile()].isEmpty() && canAddToFoundation(piles[display.selectedPile()].peek(), index))
            {
                foundations[index].push(piles[display.selectedPile()].pop());
            }
        }
        else if (display.isWasteSelected())
        {
            if (!waste.isEmpty() && canAddToFoundation(waste.peek(), index))
            {
                foundations[index].push(waste.pop());
            }
        }
        System.out.println("foundation #" + index + " clicked");
    }

    /**
     * called when given pile is clicked, either moves cards from other selected deck to the pile,
     * or selects the pile
     * @precondition  0 <= index < 7
     */
    public void pileClicked(int index)
    {
        if (display.isWasteSelected())
        {
            if (!waste.isEmpty() && canAddToPile(waste.peek(), index))
                piles[index].push(waste.pop());
        }
        else if (display.isPileSelected())
        {
            Stack<Card> cards = removeFaceUpCards(display.selectedPile());
            if (!cards.isEmpty() && canAddToPile(cards.peek(), index))
            {
                addToPile(cards, index);
            } else {
                addToPile(cards, display.selectedPile());
            }
            display.unselect();
        }
        else if (!piles[index].isEmpty() && !piles[index].peek().isFaceUp())
        {
            piles[index].peek().turnUp();
        }
        else
            display.selectPile(index);
        System.out.println("pile #" + index + " clicked");
    }

    /**
     * takes off the face up cards and returns them in a stack
     * @precondition 0<=index<7
     * @postcondition removes all face-up cards on the top of the given 
     *                pile; returns a stack containing these cards
     */
    private Stack<Card> removeFaceUpCards(int index)
    {
        Stack<Card> ups = new Stack<Card>();
        while(!piles[index].isEmpty() && piles[index].peek().isFaceUp())
        {
            ups.push(piles[index].pop());
        }
        return ups;
    }

    /**
     * Adds all cards from the stack to the pile in their origional order
     * @precondition 0<=index<7
     * @postcondition removes elements from cards, and adds them to the given pile.
     */
    private void addToPile(Stack<Card> cards, int index)
    {
        while(!cards.isEmpty())
        {
            piles[index].push(cards.pop());
        }
    }

    /**
     * makes sure the move to a pile is valid
     * @precondition 0<=index<7
     * @postcondition Returns true if the given card can be 
     *                legally moved to the top of the given pile
     */
    private boolean canAddToPile(Card card, int index)
    {
        if (piles[index].isEmpty()) //if the pile is empty, you can only move a king
        {
            if (card.getRank()==13)
                return true;
            return false;
        }
        Card topCard = piles[index].peek(); //otherwise, it must opposite color and next rank on a face up card
        return (topCard.isFaceUp() && (card.isRed()!=topCard.isRed() && topCard.getRank()==1+card.getRank()));
    }

    /**
     * makes sure the move to a foundation is valid
     * @precondition 0<=index<4
     * @postcondition returns true if the given card can be legally 
     *                moved to the top of the given foundation
     */
    private boolean canAddToFoundation(Card card, int index)
    {
        if (foundations[index].isEmpty()) //must place the ace first
        {
            return (card.getRank()==1);
        }
        else //then must be same suit and next rank
        {
            return (card.getSuit()==foundations[index].peek().getSuit() && 
                card.getRank()==1+foundations[index].peek().getRank());
        }
    }
}