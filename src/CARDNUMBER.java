public enum CARDNUMBER {


        TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(11), QUEEN(12), KING(13), ACE(
            14);

        private int     ord;

        private CARDNUMBER(int i)
        {
            this.ord = i;
        }


         // Returns the ordinal(ordningsnummer på svenska) position of the enum

        public int getOrd()
        {
            return ord;
        }
    }

