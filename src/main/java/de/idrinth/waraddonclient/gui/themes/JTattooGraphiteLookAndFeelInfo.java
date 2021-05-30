package de.idrinth.waraddonclient.gui.themes;

import com.jtattoo.plaf.graphite.GraphiteLookAndFeel;
import javax.swing.*;

public class JTattooGraphiteLookAndFeelInfo extends UIManager.LookAndFeelInfo {
  public JTattooGraphiteLookAndFeelInfo() {
    super("JTattoo Graphite", GraphiteLookAndFeel.class.getName());
  }
}
