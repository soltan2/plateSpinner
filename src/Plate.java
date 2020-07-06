// --------------------------------------------------------
// DO NOT EDIT ANYTHING BELOW THIS LINE (except to add JavaDocs)
// --------------------------------------------------------
/**
 * Representation of plate: Number of plates and a string value of plate.
 */
public class Plate {
    /**
     * number of plates.
     */
    private int number;

    /**
     * Constructor to initialize the number of plates.
     * 
     * @param number
     *            of plates.
     */
    public Plate(int number) {
        this.number = number;
    }

    /**
     * print the character value of the plate in parentheses.
     * 
     * @return the string value of the plate.
     */
    public String toString() {
        return "(" + ((char) (this.number + 96)) + ")";
    }

    /**
     * get number of plates in a list.
     * 
     * @return the number of plates.
     */
    public int getNumber() {
        return this.number;
    }
}
