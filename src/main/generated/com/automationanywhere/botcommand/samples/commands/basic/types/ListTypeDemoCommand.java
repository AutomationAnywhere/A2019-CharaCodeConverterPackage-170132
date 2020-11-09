package com.automationanywhere.botcommand.samples.commands.basic.types;

import com.automationanywhere.bot.service.GlobalSessionContext;
import com.automationanywhere.botcommand.BotCommand;
import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.commandsdk.i18n.Messages;
import com.automationanywhere.commandsdk.i18n.MessagesFactory;
import java.lang.ClassCastException;
import java.lang.Deprecated;
import java.lang.Double;
import java.lang.NullPointerException;
import java.lang.Object;
import java.lang.String;
import java.lang.Throwable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ListTypeDemoCommand implements BotCommand {
  private static final Logger logger = LogManager.getLogger(ListTypeDemoCommand.class);

  private static final Messages MESSAGES_GENERIC = MessagesFactory.getMessages("com.automationanywhere.commandsdk.generic.messages");

  @Deprecated
  public Optional<Value> execute(Map<String, Value> parameters, Map<String, Object> sessionMap) {
    return execute(null, parameters, sessionMap);
  }

  public Optional<Value> execute(GlobalSessionContext globalSessionContext,
      Map<String, Value> parameters, Map<String, Object> sessionMap) {
    logger.traceEntry(() -> parameters != null ? parameters.toString() : null, ()-> sessionMap != null ?sessionMap.toString() : null);
    ListTypeDemo command = new ListTypeDemo();
    HashMap<String, Object> convertedParameters = new HashMap<String, Object>();
    if(parameters.containsKey("sourceList") && parameters.get("sourceList") != null && parameters.get("sourceList").get() != null) {
      convertedParameters.put("sourceList", parameters.get("sourceList").get());
      if(!(convertedParameters.get("sourceList") instanceof List)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","sourceList", "List", parameters.get("sourceList").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("sourceList") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","sourceList"));
    }

    if(parameters.containsKey("itemPositionNumber") && parameters.get("itemPositionNumber") != null && parameters.get("itemPositionNumber").get() != null) {
      convertedParameters.put("itemPositionNumber", parameters.get("itemPositionNumber").get());
      if(!(convertedParameters.get("itemPositionNumber") instanceof Double)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","itemPositionNumber", "Double", parameters.get("itemPositionNumber").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("itemPositionNumber") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","itemPositionNumber"));
    }
    if(convertedParameters.containsKey("itemPositionNumber")) {
      try {
        if(convertedParameters.get("itemPositionNumber") != null && !((double)convertedParameters.get("itemPositionNumber") >= 0)) {
          throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.GreaterThanEqualTo","itemPositionNumber", "0"));
        }
      }
      catch(ClassCastException e) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","itemPositionNumber", "Number", convertedParameters.get("itemPositionNumber").getClass().getSimpleName()));
      }
      catch(NullPointerException e) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","itemPositionNumber"));
      }

    }
    try {
      Optional<Value> result =  Optional.ofNullable(command.get((List<Value>)convertedParameters.get("sourceList"),(Double)convertedParameters.get("itemPositionNumber")));
      return logger.traceExit(result);
    }
    catch (ClassCastException e) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.IllegalParameters","get"));
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
