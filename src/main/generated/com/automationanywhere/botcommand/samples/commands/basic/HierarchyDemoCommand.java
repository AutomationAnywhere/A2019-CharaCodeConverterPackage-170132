package com.automationanywhere.botcommand.samples.commands.basic;

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

public final class HierarchyDemoCommand implements BotCommand {
  private static final Logger logger = LogManager.getLogger(HierarchyDemoCommand.class);

  private static final Messages MESSAGES_GENERIC = MessagesFactory.getMessages("com.automationanywhere.commandsdk.generic.messages");

  @Deprecated
  public Optional<Value> execute(Map<String, Value> parameters, Map<String, Object> sessionMap) {
    return execute(null, parameters, sessionMap);
  }

  public Optional<Value> execute(GlobalSessionContext globalSessionContext,
      Map<String, Value> parameters, Map<String, Object> sessionMap) {
    logger.traceEntry(() -> parameters != null ? parameters.toString() : null, ()-> sessionMap != null ?sessionMap.toString() : null);
    HierarchyDemo command = new HierarchyDemo();
    HashMap<String, Object> convertedParameters = new HashMap<String, Object>();
    if(parameters.containsKey("cuisine") && parameters.get("cuisine") != null && parameters.get("cuisine").get() != null) {
      convertedParameters.put("cuisine", parameters.get("cuisine").get());
      if(!(convertedParameters.get("cuisine") instanceof String)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","cuisine", "String", parameters.get("cuisine").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("cuisine") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","cuisine"));
    }
    if(convertedParameters.get("cuisine") != null) {
      switch((String)convertedParameters.get("cuisine")) {
        case "ITALIAN" : {
          if(parameters.containsKey("italianDish") && parameters.get("italianDish") != null && parameters.get("italianDish").get() != null) {
            convertedParameters.put("italianDish", parameters.get("italianDish").get());
            if(!(convertedParameters.get("italianDish") instanceof String)) {
              throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","italianDish", "String", parameters.get("italianDish").get().getClass().getSimpleName()));
            }
          }
          if(convertedParameters.get("italianDish") == null) {
            throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","italianDish"));
          }
          if(convertedParameters.get("italianDish") != null) {
            switch((String)convertedParameters.get("italianDish")) {
              case "PIZZA" : {

              } break;
              case "PASTA" : {

              } break;
              default : throw new BotCommandException(MESSAGES_GENERIC.getString("generic.InvalidOption","italianDish"));
            }
          }


        } break;
        case "INDIAN" : {
          if(parameters.containsKey("indianDish") && parameters.get("indianDish") != null && parameters.get("indianDish").get() != null) {
            convertedParameters.put("indianDish", parameters.get("indianDish").get());
            if(!(convertedParameters.get("indianDish") instanceof String)) {
              throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","indianDish", "String", parameters.get("indianDish").get().getClass().getSimpleName()));
            }
          }
          if(convertedParameters.get("indianDish") == null) {
            throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","indianDish"));
          }


        } break;
        default : throw new BotCommandException(MESSAGES_GENERIC.getString("generic.InvalidOption","cuisine"));
      }
    }

    try {
      command.getOrder((String)convertedParameters.get("cuisine"),(String)convertedParameters.get("italianDish"),(String)convertedParameters.get("indianDish"));Optional<Value> result = Optional.empty();
      return logger.traceExit(result);
    }
    catch (ClassCastException e) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.IllegalParameters","getOrder"));
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
