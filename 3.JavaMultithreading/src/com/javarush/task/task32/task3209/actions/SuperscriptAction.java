package com.javarush.task.task32.task3209.actions;

import javax.swing.text.StyledEditorKit;
import java.awt.event.ActionEvent;

/**
 * Created by engelsun on 6/2/2017.
 */
public class SuperscriptAction extends StyledEditorKit.StyledTextAction {
    /**
     * Creates a new StyledTextAction from a string action name.
     *
     */
    public SuperscriptAction(String nm) {
        super(nm);
    }

    public SuperscriptAction() {
        super(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
