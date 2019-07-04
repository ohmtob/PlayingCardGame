import java.util.*;

public class PlayingCardDeck implements CardDeck
{
    private List<PlayingCard> cards;

    private List<CardPlayer> players = new ArrayList<CardPlayer>();

    private Map<CardPlayer, List<PlayingCard>> cardsPlayerMap = new HashMap<CardPlayer, List<PlayingCard>>();

    private int currentPlayerIdx = 0;

    private static final int numberOfCardsPerPlayer  = 9;

    private int numberOfPlayers = 2;

    PlayingCardDeck()
    {
        cards = PlayingCard.getPackOfCards();
    }

    private void distributeCardsForPlayers(List<CardPlayer> plys)
    {
        this.players = plys;
        PlayingCard.shuffleCards(cards);
        if (cardsPlayerMap.size() == 0)
            cardsPlayerMap.clear();

        int m = 0;
        for (CardPlayer pl : players)
        {
            pl.setPoints(0);
            List<PlayingCard> cds = new ArrayList<PlayingCard>();
            int cardLimit = m + numberOfCardsPerPlayer;
            for (int i = m; i < cardLimit; i++)
            {
                cds.add(cards.get(i));
            }
            m = cardLimit;
            cardsPlayerMap.put(pl, cds);
        }
    }

    @Override
    public void playGame(int numberOfPlayers)
    {
        this.numberOfPlayers = numberOfPlayers;
        createMultipleUser(numberOfPlayers);
        int i;
        System.out.println("Game started.....  ");
        System.out.println();
        List<PlayingCard> selCards = new ArrayList<PlayingCard>();
        PlayingCard maxCard = null;
        CardPlayer maxPlayer = new CardPlayer (0);
        distributeCardsForPlayers(players);
        for (int j = 0; j < numberOfCardsPerPlayer; j++)
        {
            int s = 0;
            do {
                CardPlayer player = getNextPlayer ( );
                System.out.println ( );
                System.out.println ("1. Display hidden cards available");
                System.out.println ("2. Stop game");
                System.out.println ( );
                System.out.println ("Player: " + player.getPlayerId ( ));
                System.out.print ("Please enter your option: ");

                Scanner in = new Scanner (System.in);
                i = in.nextInt ( );

                switch (i) {
                    case 1:
                        displayCardsForPlayer (player);
                        System.out.println ( );
                        System.out.println ("Select your hidden card number: ");

                        in = new Scanner (System.in);
                        int m = in.nextInt ( );
                        PlayingCard c = cardsPlayerMap.get (player).get (m - 1);
                        System.out.println ("Card selected: " + c.toString ( ));
                        cardsPlayerMap.get (player).remove (m - 1);
                        if (maxCard == null) {
                            maxCard = c;
                            maxPlayer = player;
                        } else {
                            if (maxCard.compareTo (c) < 0) {
                                maxCard = c;
                                maxPlayer = player;
                            }
                        }
                        selCards.add (c);

                        break;
                    case 2:
                        return;

                    default:
                        return;
                }







                System.out.println();
                s++;
            } while (s < players.size());
            if (maxPlayer.getPlayerId() > 0)
                maxPlayer.setPoints((maxPlayer.getPoints()) + 1);
            maxCard = null;
            maxPlayer = null;
            displayScores();
        }
    }

    private void displayScores()
    {
        for (CardPlayer pl : players)
        {
            System.out.println("Player " + pl.getPlayerId() + " Score -> " + pl.getPoints());
        }

    }

    private void displayCardsForPlayer(CardPlayer pl)
    {
        int cards = cardsPlayerMap.get(pl).size();
        for (int i = 0; i < cards;)
        {
            System.out.print((++i) + " ");
        }
    }

    @Override
    public void displayWinners()
    {
        Collections.sort(players);
        int maxPoints = 0;
        Map<String, List<CardPlayer>> playerPointsMap = new TreeMap<String, List<CardPlayer>>();
        for (CardPlayer p : players)
        {

            maxPoints = p.getPoints();
            if (playerPointsMap.get(maxPoints + "") != null)
            {
                List<CardPlayer> lst = playerPointsMap.get(maxPoints + "");
                lst.add(p);
                playerPointsMap.put(maxPoints + "", lst);
            }
            else
            {
                List<CardPlayer> lst = new ArrayList<CardPlayer>();
                lst.add(p);
                playerPointsMap.put(maxPoints + "", lst);
            }
        }

        String pts = Integer.toString (players.get (players.size ( ) - 1).getPoints ( ));
        if (playerPointsMap.get(pts) != null && playerPointsMap.get(pts).size() > 1)
        {
            System.out.println("Its a draw among the following players ");
            for (CardPlayer p : players)
            {
                System.out.println("Player number: " + p.getPlayerId());
                System.out.println ( );
                System.out.println ("Press any key to get back to the menu." );
            }
        }
        else if (playerPointsMap.get(pts) != null)
        {
            System.out.println("And the winner is:");
            System.out.println("Player number: " + playerPointsMap.get(pts).get(0).getPlayerId());
            System.out.println ( );
            System.out.println ("Press any key to get back to the menu." );
        }
    }


    private void createMultipleUser(int j)
    {
      if ((players.size() !=0) && (players.size () <=5) )
        {
            players.clear();
        }

        for (int i = 0; i < j; i++)
        {
            int id = i + 1;
            CardPlayer usr = new CardPlayer (id);
            players.add(usr);
        }
        distributeCardsForPlayers(players);
    }

    private CardPlayer getNextPlayer()
    {

        CardPlayer p;
        if (currentPlayerIdx == players.size())
        {
            currentPlayerIdx = 1;
            p = players.get(0);
        }
        else
        {
            p = players.get(currentPlayerIdx);
            currentPlayerIdx++;
        }

        return p;
    }

}