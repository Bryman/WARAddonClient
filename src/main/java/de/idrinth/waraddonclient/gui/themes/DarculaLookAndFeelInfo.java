package de.idrinth.waraddonclient.gui.themes;

import com.bulenkov.darcula.DarculaLaf;

import javax.swing.*;

public class DarculaLookAndFeelInfo extends UIManager.LookAndFeelInfo {
    public DarculaLookAndFeelInfo() {
        super("Darcula", DarculaLaf.class.getName());
    }
}
