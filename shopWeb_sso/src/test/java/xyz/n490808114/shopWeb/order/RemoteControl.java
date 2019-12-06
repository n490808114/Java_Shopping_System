package xyz.n490808114.shopWeb.order;

import java.util.EmptyStackException;
import java.util.Stack;

public class RemoteControl{

    private Command[] onCommands;
    private Command[] offCommands;

    private Stack<Command> stack;

    public RemoteControl(){
        NoCommand noCommand = new NoCommand();
        onCommands = new Command[7];
        offCommands = new Command[7];
        for (int i = 0; i < offCommands.length; i++) {
            onCommands[i] = noCommand;
            offCommands[i] = noCommand;
        }
        stack = new Stack<>();
    }
    public void setCommand(int slot,Command onCommand,Command offCommand){
        onCommands[slot] = onCommand;
        offCommands[slot] = offCommand;
    }
    public void pressOnButton(int slot){
        onCommands[slot].execute();
        stack.push(onCommands[slot]);
    }
    public void pressOffButton(int slot){
        offCommands[slot].execute();
        stack.push(offCommands[slot]);
    }
    public void undo(){
        try{
            Command command = stack.pop();
            command.undo();
        }catch(EmptyStackException ignore){}
    }
    public String toString(){
        StringBuffer buffer = new StringBuffer();
        buffer.append("==============REMOTE CONTROL==============");
        for (int i = 0; i < offCommands.length; i++) {
            buffer.append("slot:"+i+": ON>>>" + onCommands[i].getClass().getSimpleName() + ";    "+ "OFF>>>"+offCommands[i].getClass().getSimpleName() + "\n");
        }
        buffer.append("==========================================");
        return buffer.toString();
    }
}