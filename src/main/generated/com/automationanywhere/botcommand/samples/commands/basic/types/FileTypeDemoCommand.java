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

public final class FileTypeDemoCommand implements BotCommand {
  private static final Logger logger = LogManager.getLogger(FileTypeDemoCommand.class);

  private static final Messages MESSAGES_GENERIC = MessagesFactory.getMessages("com.automationanywhere.commandsdk.generic.messages");

  @Deprecated
  public Optional<Value> execute(Map<String, Value> parameters, Map<String, Object> sessionMap) {
    return execute(null, parameters, sessionMap);
  }

  public Optional<Value> execute(GlobalSessionContext globalSessionContext,
      Map<String, Value> parameters, Map<String, Object> sessionMap) {
    logger.traceEntry(() -> parameters != null ? parameters.toString() : null, ()-> sessionMap != null ?sessionMap.toString() : null);
    FileTypeDemo command = new FileTypeDemo();
    HashMap<String, Object> convertedParameters = new HashMap<String, Object>();
    if(parameters.containsKey("anyFile") && parameters.get("anyFile") != null && parameters.get("anyFile").get() != null) {
      convertedParameters.put("anyFile", parameters.get("anyFile").get());
      if(!(convertedParameters.get("anyFile") instanceof String)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","anyFile", "String", parameters.get("anyFile").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("anyFile") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","anyFile"));
    }

    if(parameters.containsKey("localFile") && parameters.get("localFile") != null && parameters.get("localFile").get() != null) {
      convertedParameters.put("localFile", parameters.get("localFile").get());
      if(!(convertedParameters.get("localFile") instanceof String)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","localFile", "String", parameters.get("localFile").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("localFile") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","localFile"));
    }

    if(parameters.containsKey("repositoryFile") && parameters.get("repositoryFile") != null && parameters.get("repositoryFile").get() != null) {
      convertedParameters.put("repositoryFile", parameters.get("repositoryFile").get());
      if(!(convertedParameters.get("repositoryFile") instanceof String)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","repositoryFile", "String", parameters.get("repositoryFile").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("repositoryFile") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","repositoryFile"));
    }

    try {
      command.accept((String)convertedParameters.get("anyFile"),(String)convertedParameters.get("localFile"),(String)convertedParameters.get("repositoryFile"));Optional<Value> result = Optional.empty();
      return logger.traceExit(result);
    }
    catch (ClassCastException e) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.IllegalParameters","accept"));
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
