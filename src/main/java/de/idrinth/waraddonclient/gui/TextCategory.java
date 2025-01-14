package de.idrinth.waraddonclient.gui;

import de.idrinth.waraddonclient.model.GuiAddonList;
import de.idrinth.waraddonclient.model.addon.Addon;
import java.util.List;
import javax.swing.*;

public class TextCategory extends RowFilter<Addon, Integer> {

  private final java.util.regex.Pattern textfilter;

  private final GuiAddonList addonList;

  public TextCategory(String text, GuiAddonList addonList) {
    textfilter = java.util.regex.Pattern.compile("(?i)" + java.util.regex.Pattern.quote(text));
    this.addonList = addonList;
  }

  @Override
  public boolean include(Entry entry) {
    Addon addon = addonList.get(entry.getStringValue(1));
    if (addon == null) {
      return false;
    }
    if (!textfilter.matcher(addon.getName()).find()) {
      return false;
    }
    return isInAllowedCategory(addon);
  }

  private boolean isInAllowedCategory(Addon addon) {
    List<String> tags = addonList.getActiveTags();
    return tags.isEmpty() || tags.stream().anyMatch(addon::hasTag);
  }
}
