package de.idrinth.waraddonclient.gui.themes;

import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.*;

public class FlatDarkLookAndFeelInfo extends UIManager.LookAndFeelInfo {
  public FlatDarkLookAndFeelInfo() {
    super("Flat Dark", FlatDarkLaf.class.getName());
  }
}
