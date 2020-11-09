package com.automationanywhere.botcommand.samples.commands.basic.types;

import com.automationanywhere.bot.service.GlobalSessionContext;
import com.automationanywhere.botcommand.BotCommand;
import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.data.model.table.Table;
import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.commandsdk.i18n.Messages;
import com.automationanywhere.commandsdk.i18n.MessagesFactory;
import java.lang.ClassCastException;
import java.lang.Deprecated;
import java.lang.Double;
import java.lang.Object;
import java.lang.String;
import java.lang.Throwable;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class TableTypeDemoCommand implements BotCommand {
  private static final Logger logger = LogManager.getLogger(TableTypeDemoCommand.class);

  private static final Messages MESSAGES_GENERIC = MessagesFactory.getMessages("com.automationanywhere.commandsdk.generic.messages");

  @Deprecated
  public Optional<Value> execute(Map<String, Value> parameters, Map<String, Object> sessionMap) {
    return execute(null, parameters, sessionMap);
  }

  public Optional<Value> execute(GlobalSessionContext globalSessionContext,
      Map<String, Value> parameters, Map<String, Object> sessionMap) {
    logger.traceEntry(() -> parameters != null ? parameters.toString() : null, ()-> sessionMap != null ?sessionMap.toString() : null);
    TableTypeDemo command = new TableTypeDemo();
    HashMap<String, Object> convertedParameters = new HashMap<String, Object>();
    if(parameters.containsKey("table") && parameters.get("table") != null && parameters.get("table").get() != null) {
      convertedParameters.put("table", parameters.get("table").get());
      if(!(convertedParameters.get("table") instanceof Table)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","table", "Table", parameters.get("table").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("table") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","table"));
    }

    if(parameters.containsKey("row") && parameters.get("row") != null && parameters.get("row").get() != null) {
      convertedParameters.put("row", parameters.get("row").get());
      if(!(convertedParameters.get("row") instanceof Double)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","row", "Double", parameters.get("row").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("row") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","row"));
    }

    if(parameters.containsKey("col") && parameters.get("col") != null && parameters.get("col").get() != null) {
      convertedParameters.put("col", parameters.get("col").get());
      if(!(convertedParameters.get("col") instanceof Double)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","col", "Double", parameters.get("col").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("col") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","col"));
    }

    try {
      Optional<Value> result =  Optional.ofNullable(command.printName((Table)convertedParameters.get("table"),(Double)convertedParameters.get("row"),(Double)convertedParameters.get("col")));
      return logger.traceExit(result);
    }
    catch (ClassCastException e) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.IllegalParameters","printName"));
    }
    catch (BotCommandException e) {
      logger.fatal(e.getMessage(),e);
      throw e;
    }
    catch (Throwable e) {
      logger.fatal(e.getMessage(),e);
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.NotBotCommandException",e.getMessage()),e);
    }
  }
}
