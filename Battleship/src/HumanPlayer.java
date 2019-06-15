public class HumanPlayer extends Player
{
    private int[][] human;

    public HumanPlayer(String name)
    {
        super(name);
        human = getGuessBoard();
    }

    /**
     * Attack the specified Location loc.  Marks
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
     * @param enemy
     * @param loc
     * @return
     */
    @Override
    public boolean attack(Player enemy, Location loc)
    {
        if(enemy.hasShipAtLocation(loc))
        {
            human[loc.getRow()][loc.getCol()] = 1;
            Ship hit = enemy.getShip(loc);
            hit.takeHit(loc);

            if(hit.isSunk())
            {
                enemy.removeShip(hit);
                return true;
            }
        }

        else
            human[loc.getRow()][loc.getCol()] = -1;

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
