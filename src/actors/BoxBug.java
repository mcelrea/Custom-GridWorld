package actors;

import javafx.scene.image.Image;
import world.GridWorld;
import world.Location;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class BoxBug extends Bug{

    private int sideLength;
    private int currentStep;

    public BoxBug(Location myLoc, GridWorld world, int sideLength) {
        super(myLoc, world);
        this.sideLength = sideLength;
        currentStep = 0;
        try {
            super.setImage(new Image(new FileInputStream(".\\images\\boxBug.png")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void act() {

        Location nextMove = getMyLoc().getLocInDirection(getDirection());
        if(getMyWorld().isValidLoc(nextMove) && getMyWorld().getActor(nextMove) == null) {
            getMyWorld().moveActor(this, nextMove);
            setMyLoc(nextMove);
            currentStep++;
            if(currentStep == sideLength) {
                turnRight();
                turnRight();
                currentStep = 0;
            }
        }
        else {//he couldn't move
            turnRight();
            turnRight();
            currentStep = 0;
        }
    }
}
