import java.util.Scanner;

public class PlayingCardGame
{
    private static Scanner in = new Scanner(System.in);



    public PlayingCardGame()
    {
    }

    public static void main(String[] args) {
        PlayingCardDeck sl = new PlayingCardDeck ();

        int menuSelection;
        do {
            GameMenu.displayMenu();
            Scanner scanner = new Scanner( System.in );
            menuSelection = scanner.nextInt();

            switch (menuSelection) {


                case 1:

                    System.out.println( "Select number of players(2 - 5) : " );
                    int i = scanner.nextInt ( );
                    sl.playGame(i);
                    sl.displayWinners();
                    in.nextLine();
                    break;

                case 2:
                    GameRules.displayGameRules();
                    in.nextLine();
                    break;

                case 3:
                    System.out.println( "You have chosen to quit the game." );
                    System.out.println( "Please come back another time!" );
                    System.exit( 0 );
                    in.nextLine();
                    break;

                default:
                    System.out.println( "Enter 1-3: " );


            }
        } while (menuSelection != 0);
    }
}
