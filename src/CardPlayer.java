public class CardPlayer implements Comparable<CardPlayer>
{

    CardPlayer(int id)
    {
        this.playerId = id;
    }

    private int playerId;

    private String playerName;

    private int points;

    private String result;

    int getPlayerId()
    {
        return playerId;
    }

    public void setPlayerName(String playerName)
    {
        this.playerName = playerName;
    }

    int getPoints()
    {
        return points;
    }

    void setPoints(int points)
    {
        this.points = points;
    }

    public void setResult(String result)
    {
        this.result = result;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + playerId;
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CardPlayer other = (CardPlayer) obj;
        return playerId == other.playerId;
    }

    @Override
    public int compareTo(CardPlayer o)
    {
        return Integer.compare (this.getPoints ( ), o.getPoints ( ));
    }
}