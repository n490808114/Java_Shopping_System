package xyz.n490808114.shopWeb.order;

public class RemoteControlTest{
    public static void main(String[] args) {
        RemoteControl control = new RemoteControl();
        GarageDoor door = new GarageDoor();

        GarageDoorOpenCommand openCommand = new GarageDoorOpenCommand(door);
        GarageDoorCloseCommand closeCommand = new GarageDoorCloseCommand(door);

        for (int i = 0; i < 6; i++) {
            control.setCommand(i, openCommand, closeCommand);
            control.pressOnButton(i);
        }
        for(int x = 0 ;x < 20 ; x ++){
            control.undo();
        }
        System.out.println(control.toString());
    }
}