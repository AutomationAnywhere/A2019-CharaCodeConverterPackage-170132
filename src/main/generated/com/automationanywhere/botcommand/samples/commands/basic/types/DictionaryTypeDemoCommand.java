package com.automationanywhere.botcommand.samples.commands.basic.types;

import com.automationanywhere.bot.service.GlobalSessionContext;
import com.automationanywhere.botcommand.BotCommand;
import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.commandsdk.i18n.Messages;
import com.automationanywhere.commandsdk.i18n.MessagesFactory;
import java.lang.ClassCastException;
import java.lang.Deprecated;
import java.lang.Object;
import java.lang.String;
import java.lang.Throwable;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class DictionaryTypeDemoCommand implements BotCommand {
  private static final Logger logger = LogManager.getLogger(DictionaryTypeDemoCommand.class);

  private static final Messages MESSAGES_GENERIC = MessagesFactory.getMessages("com.automationanywhere.commandsdk.generic.messages");

  @Deprecated
  public Optional<Value> execute(Map<String, Value> parameters, Map<String, Object> sessionMap) {
    return execute(null, parameters, sessionMap);
  }

  public Optional<Value> execute(GlobalSessionContext globalSessionContext,
      Map<String, Value> parameters, Map<String, Object> sessionMap) {
    logger.traceEntry(() -> parameters != null ? parameters.toString() : null, ()-> sessionMap != null ?sessionMap.toString() : null);
    DictionaryTypeDemo command = new DictionaryTypeDemo();
    HashMap<String, Object> convertedParameters = new HashMap<String, Object>();
    if(parameters.containsKey("keyValuePair") && parameters.get("keyValuePair") != null && parameters.get("keyValuePair").get() != null) {
      convertedParameters.put("keyValuePair", parameters.get("keyValuePair").get());
      if(!(convertedParameters.get("keyValuePair") instanceof Map)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","keyValuePair", "Map", parameters.get("keyValuePair").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("keyValuePair") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","keyValuePair"));
    }

    if(parameters.containsKey("key") && parameters.get("key") != null && parameters.get("key").get() != null) {
      convertedParameters.put("key", parameters.get("key").get());
      if(!(convertedParameters.get("key") instanceof String)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","key", "String", parameters.get("key").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("key") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","key"));
    }

    try {
      Optional<Value> result =  Optional.ofNullable(command.extract((Map<String, Value>)convertedParameters.get("keyValuePair"),(String)convertedParameters.get("key")));
      return logger.traceExit(result);
    }
    catch (ClassCastException e) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.IllegalParameters","extract"));
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
