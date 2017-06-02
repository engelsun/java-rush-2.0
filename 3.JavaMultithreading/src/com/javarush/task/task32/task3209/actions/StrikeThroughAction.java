package com.javarush.task.task32.task3209.actions;

import javax.swing.text.StyledEditorKit;
import java.awt.event.ActionEvent;

/**
 * Created by engelsun on 6/2/2017.
 */
public class StrikeThroughAction extends StyledEditorKit.StyledTextAction {
    /**
     * Creates a new StyledTextAction from a string action name.
     *
     */
    public StrikeThroughAction(String nm) {
        super(nm);
    }

    public StrikeThroughAction() {
        super(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
