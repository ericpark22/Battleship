import java.util.Random;

public class ComputerPlayer extends Player
{
    private int[][] comp;
    public ComputerPlayer(String name)
    {
        super(name);
        comp = getGuessBoard();
    }

    /**
     * Randomly chooses a Location that has not been
     *   attacked (Location loc is ignored).  Marks
     *   the attacked Location on the guess board
     *   with a positive number if the enemy Player
     *   controls a ship at the Location attacked;
     *   otherwise, if the enemy Player does not
     *   control a ship at the attacked Location,
     *   guess board is marked with a negative number.
     *
     * If the enemy Player controls a ship at the attacked
     *   Location, the ship must add the Location to its
     *   hits taken.  Then, if the ship has been sunk, it
     *   is removed from the enemy Player's list of ships.
     *
     * Return true if the attack resulted in a ship sinking;
     *   false otherwise.
     *
     * @param enemy The Player to attack.
     * @param loc The Location to attack.
     * @return
     */
    @Override
    public boolean attack(Player enemy, Location loc)
    {
        boolean check = false;
        Location compLoc = new Location(0, 0);

        while(!check)
        {
            Random rand = new Random();
            int row = rand.nextInt(10);
            int col = rand.nextInt(10);

            if(comp[row][col] == 0)
            {
                check = true;
                compLoc = new Location(row, col);
            }
        }

        if(enemy.hasShipAtLocation(compLoc))
        {
            comp[compLoc.getRow()][compLoc.getCol()] = 1;
            Ship hit = enemy.getShip(compLoc);
            hit.takeHit(compLoc);

            if(hit.isSunk())
            {
                enemy.removeShip(hit);
                return true;
            }
        }

        else
            comp[compLoc.getRow()][compLoc.getCol()] = -1;

        return false;
    }

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
    @Override
    public void populateShips()
    {
        AircraftCarrier airCraft = new AircraftCarrier(new Location(0, 0), new Location(0, 1) , new Location(0, 2), new Location(0, 3), new Location(0, 4));
        airCraft.addLocation(new Location(0, 0));
        Destroyer destroy = new Destroyer(new Location(1, 0), new Location(1, 1), new Location(1, 2), new Location(1, 3));
        Submarine sub = new Submarine(new Location(2, 0), new Location(2, 1), new Location(2, 2));
        Cruiser cruise = new Cruiser(new Location(3, 0), new Location(3, 1), new Location(3, 2));
        PatrolBoat patrol = new PatrolBoat(new Location(4, 0), new Location(4, 1));

        addShip(airCraft);
        addShip(destroy);
        addShip(sub);
        addShip(cruise);
        addShip(patrol);

    }
}
