package de.idrinth.waraddonclient.gui.themes;

import com.formdev.flatlaf.FlatIntelliJLaf;

import javax.swing.*;

public class FlatIntelliJLookAndFeelInfo extends UIManager.LookAndFeelInfo {
    public FlatIntelliJLookAndFeelInfo() {
        super("Flat IntelliJ", FlatIntelliJLaf.class.getName());
    }
}
