package xyz.n490808114.shopWeb.order;

public class GarageDoorOpenCommand implements Command{
    private GarageDoor garageDoor;

    public GarageDoorOpenCommand(GarageDoor garageDoor){
        this.garageDoor = garageDoor;
    }
    @Override
    public void execute() {
        garageDoor.open();
    }
    @Override
    public void undo() {
        garageDoor.close();
    }
}