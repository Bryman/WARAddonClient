package de.idrinth.waraddonclient.cli;

import java.util.Properties;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class AliasParser extends DefaultParser {

  @Override
  public CommandLine parse(
      Options options, String[] arguments, Properties properties, boolean stopAtNonOption)
      throws ParseException {
    Options internals = new Options();

    for (Option option : options.getOptions()) {
      internals.addOption(option);
      if (option instanceof AliasOption) {
        AliasOption aliasOption = (AliasOption) option;
        for (String alias : aliasOption.getAliases()) {
          internals.addOption(
              new AliasedOption(
                  alias,
                  aliasOption.hasArg(),
                  aliasOption.getDescription(),
                  aliasOption.getLongOpt()));
        }
      }
    }
    CommandLine cmd = super.parse(internals, arguments, properties, stopAtNonOption);
    CommandLine.Builder builder = new CommandLine.Builder();
    for (Option option : cmd.getOptions()) {
      if (!(option instanceof AliasedOption)) {
        builder.addOption(option);
      } else {
        AliasedOption aliased = (AliasedOption) option;
        aliased.setLongOpt(aliased.getOriginal());
        builder.addOption(aliased);
      }
    }
    cmd.getArgList().forEach(builder::addArg);
    return builder.build();
  }
}
