package de.idrinth.waraddonclient.gui.themes;

import com.jtattoo.plaf.mcwin.McWinLookAndFeel;
import javax.swing.*;

public class JTattooMcWinLookAndFeelInfo extends UIManager.LookAndFeelInfo {
  public JTattooMcWinLookAndFeelInfo() {
    super("JTattoo McWin", McWinLookAndFeel.class.getName());
  }
}
