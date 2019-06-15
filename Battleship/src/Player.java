import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class Player
{
    private String name;
    private List<Ship> ships;
    private int[][] guessBoard;

    public Player(String name)
    {
        this.name = name;
        guessBoard = new int[10][10];
        ships = new ArrayList<>();
        populateShips();
    }

    public String getName()
    {
        return name;
    }

    public List<Ship> getShips()
    {
        return ships;
    }

    /**
     * Returns how many ships are currently in this
     *   Player's ships List.
     *
     * @return
     */
    public int getNumberOfShips()
    {
        return ships.size();
    }

    /**
     * Returns the ship that occupies the specified
     *   Location loc.  Returns null if this Player
     *   does not control a ship at Location loc.
     *
     * @param loc
     * @return
     */
    public Ship getShip(Location loc)
    {
        for(int j = 0; j < ships.size(); j++)
        {
            Ship b = ships.get(j);
            List<Location> locs = b.getLocations();

            for(int i = 0; i < locs.size(); i++)
            {
                if (locs.get(i).getRow() == loc.getRow() && locs.get(i).getCol() == loc.getCol())
                    return b;
            }
        }

        return null;
    }

    public void addShip(Ship ship)
    {
        ships.add(ship);
    }

    public void removeShip(Ship ship)
    {
        ships.remove(ship);
    }

    /**
     * Returns true if this Player controls a ship
     *   at the specified Location loc; false
     *   otherwise.
     *
     * @param loc
     * @return
     */
    public boolean hasShipAtLocation(Location loc)
    {
        for(int j = 0; j < ships.size(); j++)
        {
            Ship b = ships.get(j);
            List<Location> locs = b.getLocations();

            for(int i = 0; i < locs.size(); i++)
            {
                if (locs.get(i).getRow() == loc.getRow() && locs.get(i).getCol() == loc.getCol())
                    return true;
            }
        }

        return false;
    }

    public int[][] getGuessBoard()
    {
        return guessBoard;
    }

    /**
     * Returns true if obj is an instanceof Player and
     *   instance variables are equivalent.
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj)
    {
        if(obj instanceof Player)
        {
            Player play = (Player) obj;

            if(play.getName().equals(getName()) &&
                    Arrays.deepEquals(play.getGuessBoard(), getGuessBoard()) &&
                    play.getShips().equals(getShips()))
                return true;
        }

        return false;
    }

    /**
     * Attack the specified Player at the specified Location.
     *
     * Return true if the attack resulted in a ship sinking;
     *   false otherwise.
     *
     * @param enemy
     * @param loc
     * @return
     */
    public abstract boolean attack(Player enemy, Location loc);


    /**
     * Construct an instance of
     *
     *   AircraftCarrier,
     *   Destroyer,
     *   Submarine,
     *   Cruiser, and
     *   PatrolBoat
     *
     * and add them to this Player's list of ships.
     */
    public abstract void populateShips();


}
