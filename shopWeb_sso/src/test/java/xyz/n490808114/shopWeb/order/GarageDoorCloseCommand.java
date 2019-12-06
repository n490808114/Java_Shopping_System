package xyz.n490808114.shopWeb.order;

public  class GarageDoorCloseCommand implements Command{
    private GarageDoor door;
    public GarageDoorCloseCommand(GarageDoor door){
        this.door = door;
    }
    @Override
    public void execute() {
        door.close();
    }
    @Override
    public void undo() {
        door.open();
    }

}