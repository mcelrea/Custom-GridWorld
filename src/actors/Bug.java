package actors;

import javafx.scene.image.Image;
import world.GridWorld;
import world.Location;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Bug extends Actor{

    public Bug(Location myLoc, GridWorld world) {
        super(myLoc, world);
        try {
            super.setImage(new Image(new FileInputStream(".\\images\\bug.png")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void act() {
        Location nextMove = getMyLoc().getLocInDirection(getDirection());
        System.out.println("NextMove: " + nextMove);
        if(getMyWorld().isValidLoc(nextMove) && getMyWorld().getActor(nextMove) == null) {
            getMyWorld().moveActor(this, nextMove);
            setMyLoc(nextMove);
        }
        else {
            super.act(); //turn baby!!!!
        }
    }

    public void laljkasdlfh() {

    }

}
