package chap8;

public class CommandEditor implements Editor {
    @Override
    public void save() {
        System.out.println("Command Save");
    }

    @Override
    public void open() {
        System.out.println("Command Open");
    }

    @Override
    public void close() {
        System.out.println("Command Close");
    }
}
