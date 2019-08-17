package chap8;

import java.util.ArrayList;
import java.util.List;

public class Macro {

    private final List<Action> actions;

    public Macro() {
        actions = new ArrayList<>();
    }

    public void record(Action action) {
        actions.add(action);
    }

    public void run() {
        actions.forEach(Action::perform);
    }

    public static void main(String[] args) {
        Macro macro = new Macro();
        Editor editor = new CommandEditor();
        macro.record(editor::open);
        macro.record(editor::save);
        macro.record(editor::close);
        macro.run();
    }
}
