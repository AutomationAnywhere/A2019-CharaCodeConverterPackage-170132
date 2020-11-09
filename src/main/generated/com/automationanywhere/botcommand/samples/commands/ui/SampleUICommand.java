package com.automationanywhere.botcommand.samples.commands.ui;

import com.automationanywhere.bot.service.GlobalSessionContext;
import com.automationanywhere.botcommand.BotCommand;
import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.data.impl.TableValue;
import com.automationanywhere.botcommand.data.impl.WindowValue;
import com.automationanywhere.botcommand.data.model.Point;
import com.automationanywhere.botcommand.data.model.Taskbot;
import com.automationanywhere.botcommand.data.model.UIObject;
import com.automationanywhere.botcommand.data.model.image.Image;
import com.automationanywhere.botcommand.data.model.record.Record;
import com.automationanywhere.botcommand.data.model.region.Region;
import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.commandsdk.i18n.Messages;
import com.automationanywhere.commandsdk.i18n.MessagesFactory;
import com.automationanywhere.core.security.SecureString;
import java.lang.Boolean;
import java.lang.ClassCastException;
import java.lang.Deprecated;
import java.lang.Object;
import java.lang.String;
import java.lang.Throwable;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class SampleUICommand implements BotCommand {
  private static final Logger logger = LogManager.getLogger(SampleUICommand.class);

  private static final Messages MESSAGES_GENERIC = MessagesFactory.getMessages("com.automationanywhere.commandsdk.generic.messages");

  @Deprecated
  public Optional<Value> execute(Map<String, Value> parameters, Map<String, Object> sessionMap) {
    return execute(null, parameters, sessionMap);
  }

  public Optional<Value> execute(GlobalSessionContext globalSessionContext,
      Map<String, Value> parameters, Map<String, Object> sessionMap) {
    logger.traceEntry(() -> parameters != null ? parameters.toString() : null, ()-> sessionMap != null ?sessionMap.toString() : null);
    SampleUI command = new SampleUI();
    HashMap<String, Object> convertedParameters = new HashMap<String, Object>();
    if(parameters.containsKey("text") && parameters.get("text") != null && parameters.get("text").get() != null) {
      convertedParameters.put("text", parameters.get("text").get());
      if(!(convertedParameters.get("text") instanceof String)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","text", "String", parameters.get("text").get().getClass().getSimpleName()));
      }
    }

    if(parameters.containsKey("num") && parameters.get("num") != null && parameters.get("num").get() != null) {
      convertedParameters.put("num", parameters.get("num").get());
      if(!(convertedParameters.get("num") instanceof String)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","num", "String", parameters.get("num").get().getClass().getSimpleName()));
      }
    }

    if(parameters.containsKey("bool") && parameters.get("bool") != null && parameters.get("bool").get() != null) {
      convertedParameters.put("bool", parameters.get("bool").get());
      if(!(convertedParameters.get("bool") instanceof Boolean)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","bool", "Boolean", parameters.get("bool").get().getClass().getSimpleName()));
      }
    }
    if(parameters.containsKey("boolChild1") && parameters.get("boolChild1") != null && parameters.get("boolChild1").get() != null) {
      convertedParameters.put("boolChild1", parameters.get("boolChild1").get());
      if(!(convertedParameters.get("boolChild1") instanceof String)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","boolChild1", "String", parameters.get("boolChild1").get().getClass().getSimpleName()));
      }
    }


    if(parameters.containsKey("select") && parameters.get("select") != null && parameters.get("select").get() != null) {
      convertedParameters.put("select", parameters.get("select").get());
      if(!(convertedParameters.get("select") instanceof String)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","select", "String", parameters.get("select").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("select") != null) {
      switch((String)convertedParameters.get("select")) {
        case "Select option 1" : {

        } break;
        default : throw new BotCommandException(MESSAGES_GENERIC.getString("generic.InvalidOption","select"));
      }
    }

    if(parameters.containsKey("radio") && parameters.get("radio") != null && parameters.get("radio").get() != null) {
      convertedParameters.put("radio", parameters.get("radio").get());
      if(!(convertedParameters.get("radio") instanceof String)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","radio", "String", parameters.get("radio").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("radio") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","radio"));
    }
    if(convertedParameters.get("radio") != null) {
      switch((String)convertedParameters.get("radio")) {
        case "Radio option 1" : {

        } break;
        case "Radio option 2" : {

        } break;
        default : throw new BotCommandException(MESSAGES_GENERIC.getString("generic.InvalidOption","radio"));
      }
    }

    if(parameters.containsKey("file") && parameters.get("file") != null && parameters.get("file").get() != null) {
      convertedParameters.put("file", parameters.get("file").get());
      if(!(convertedParameters.get("file") instanceof String)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","file", "String", parameters.get("file").get().getClass().getSimpleName()));
      }
    }

    if(parameters.containsKey("variable") && parameters.get("variable") != null && parameters.get("variable").get() != null) {
      convertedParameters.put("variable", parameters.get("variable").get());
      if(!(convertedParameters.get("variable") instanceof Boolean)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","variable", "Boolean", parameters.get("variable").get().getClass().getSimpleName()));
      }
    }

    if(parameters.containsKey("textarea") && parameters.get("textarea") != null && parameters.get("textarea").get() != null) {
      convertedParameters.put("textarea", parameters.get("textarea").get());
      if(!(convertedParameters.get("textarea") instanceof String)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","textarea", "String", parameters.get("textarea").get().getClass().getSimpleName()));
      }
    }

    if(parameters.containsKey("code") && parameters.get("code") != null && parameters.get("code").get() != null) {
      convertedParameters.put("code", parameters.get("code").get());
      if(!(convertedParameters.get("code") instanceof String)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","code", "String", parameters.get("code").get().getClass().getSimpleName()));
      }
    }

    if(parameters.containsKey("dictionaryType") && parameters.get("dictionaryType") != null && parameters.get("dictionaryType").get() != null) {
      convertedParameters.put("dictionaryType", parameters.get("dictionaryType").get());
      if(!(convertedParameters.get("dictionaryType") instanceof Map)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","dictionaryType", "Map", parameters.get("dictionaryType").get().getClass().getSimpleName()));
      }
    }

    if(parameters.containsKey("listType") && parameters.get("listType") != null && parameters.get("listType").get() != null) {
      convertedParameters.put("listType", parameters.get("listType").get());
      if(!(convertedParameters.get("listType") instanceof List)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","listType", "List", parameters.get("listType").get().getClass().getSimpleName()));
      }
    }

    if(parameters.containsKey("boolType") && parameters.get("boolType") != null && parameters.get("boolType").get() != null) {
      convertedParameters.put("boolType", parameters.get("boolType").get());
      if(!(convertedParameters.get("boolType") instanceof String)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","boolType", "String", parameters.get("boolType").get().getClass().getSimpleName()));
      }
    }

    if(parameters.containsKey("taskBot") && parameters.get("taskBot") != null && parameters.get("taskBot").get() != null) {
      convertedParameters.put("taskBot", parameters.get("taskBot").get());
      if(!(convertedParameters.get("taskBot") instanceof Taskbot)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","taskBot", "Taskbot", parameters.get("taskBot").get().getClass().getSimpleName()));
      }
    }

    if(parameters.containsKey("window") && parameters.get("window") != null && parameters.get("window").get() != null) {
      convertedParameters.put("window", parameters.get("window").get());
      if(!(parameters.get("window") instanceof WindowValue)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","window", "WindowValue", parameters.get("window").get().getClass().getSimpleName()));
      }
    }

    if(parameters.containsKey("tableType") && parameters.get("tableType") != null && parameters.get("tableType").get() != null) {
      convertedParameters.put("tableType", parameters.get("tableType").get());
      if(!(convertedParameters.get("tableType") instanceof TableValue)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","tableType", "TableValue", parameters.get("tableType").get().getClass().getSimpleName()));
      }
    }

    if(parameters.containsKey("credential") && parameters.get("credential") != null && parameters.get("credential").get() != null) {
      convertedParameters.put("credential", parameters.get("credential").get());
      if(!(convertedParameters.get("credential") instanceof SecureString)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","credential", "SecureString", parameters.get("credential").get().getClass().getSimpleName()));
      }
    }

    if(parameters.containsKey("keyPress") && parameters.get("keyPress") != null && parameters.get("keyPress").get() != null) {
      convertedParameters.put("keyPress", parameters.get("keyPress").get());
      if(!(convertedParameters.get("keyPress") instanceof String)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","keyPress", "String", parameters.get("keyPress").get().getClass().getSimpleName()));
      }
    }

    if(parameters.containsKey("dateTime") && parameters.get("dateTime") != null && parameters.get("dateTime").get() != null) {
      convertedParameters.put("dateTime", parameters.get("dateTime").get());
      if(!(convertedParameters.get("dateTime") instanceof ZonedDateTime)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","dateTime", "ZonedDateTime", parameters.get("dateTime").get().getClass().getSimpleName()));
      }
    }

    if(parameters.containsKey("variableMap") && parameters.get("variableMap") != null && parameters.get("variableMap").get() != null) {
      convertedParameters.put("variableMap", parameters.get("variableMap").get());
      if(!(convertedParameters.get("variableMap") instanceof Map)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","variableMap", "Map", parameters.get("variableMap").get().getClass().getSimpleName()));
      }
    }

    if(parameters.containsKey("coordinate") && parameters.get("coordinate") != null && parameters.get("coordinate").get() != null) {
      convertedParameters.put("coordinate", parameters.get("coordinate").get());
      if(!(convertedParameters.get("coordinate") instanceof Point)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","coordinate", "Point", parameters.get("coordinate").get().getClass().getSimpleName()));
      }
    }

    if(parameters.containsKey("image") && parameters.get("image") != null && parameters.get("image").get() != null) {
      convertedParameters.put("image", parameters.get("image").get());
      if(!(convertedParameters.get("image") instanceof Image)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","image", "Image", parameters.get("image").get().getClass().getSimpleName()));
      }
    }

    if(parameters.containsKey("region") && parameters.get("region") != null && parameters.get("region").get() != null) {
      convertedParameters.put("region", parameters.get("region").get());
      if(!(convertedParameters.get("region") instanceof Region)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","region", "Region", parameters.get("region").get().getClass().getSimpleName()));
      }
    }

    if(parameters.containsKey("properties") && parameters.get("properties") != null && parameters.get("properties").get() != null) {
      convertedParameters.put("properties", parameters.get("properties").get());
      if(!(convertedParameters.get("properties") instanceof Set)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","properties", "Set", parameters.get("properties").get().getClass().getSimpleName()));
      }
    }

    if(parameters.containsKey("uiObject") && parameters.get("uiObject") != null && parameters.get("uiObject").get() != null) {
      convertedParameters.put("uiObject", parameters.get("uiObject").get());
      if(!(convertedParameters.get("uiObject") instanceof UIObject)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","uiObject", "UIObject", parameters.get("uiObject").get().getClass().getSimpleName()));
      }
    }

    if(parameters.containsKey("record") && parameters.get("record") != null && parameters.get("record").get() != null) {
      convertedParameters.put("record", parameters.get("record").get());
      if(!(convertedParameters.get("record") instanceof Record)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","record", "Record", parameters.get("record").get().getClass().getSimpleName()));
      }
    }

    if(parameters.containsKey("help") && parameters.get("help") != null && parameters.get("help").get() != null) {
      convertedParameters.put("help", parameters.get("help").get());
      if(!(convertedParameters.get("help") instanceof Record)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","help", "Record", parameters.get("help").get().getClass().getSimpleName()));
      }
    }

    try {
      command.execute();Optional<Value> result = Optional.empty();
      return logger.traceExit(result);
    }
    catch (ClassCastException e) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.IllegalParameters","execute"));
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
