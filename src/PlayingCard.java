import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayingCard implements Comparable<PlayingCard>
{
    private PlayingCard()
    {
    }


    private CARDNUMBER cdNumber;
    private CARDTYPE cdType;

    private CARDNUMBER getCdNumber()
    {
        return cdNumber;
    }

    static List<PlayingCard> getPackOfCards()
    {
        List<PlayingCard> crdLst = new ArrayList<PlayingCard>();

        for (CARDTYPE types : CARDTYPE.values())
        {
            for (CARDNUMBER cNums : CARDNUMBER.values())
            {
                PlayingCard cd = new PlayingCard ();
                cd.cdNumber = cNums;
                cd.cdType = types;
                crdLst.add(cd);
            }
        }
        return crdLst;
    }

    static void shuffleCards(List<PlayingCard> cards)
    {
        Collections.shuffle(cards);
    }

    @Override
    public int compareTo(PlayingCard o)
    {
        if (this.getCdNumber() == o.getCdNumber())
        {
            return 0;
        }
        else if (this.getCdNumber().getOrd() > o.getCdNumber().getOrd())
        {
            return 1;
        }
        else
            return -1;
    }

    @Override
    public String toString()
    {
        return "CARD [cdNumber=" + cdNumber + ", cdType=" + cdType + "]";
    }
}
