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

public final class CharcodeConverterCommand implements BotCommand {
  private static final Logger logger = LogManager.getLogger(CharcodeConverterCommand.class);

  private static final Messages MESSAGES_GENERIC = MessagesFactory.getMessages("com.automationanywhere.commandsdk.generic.messages");

  @Deprecated
  public Optional<Value> execute(Map<String, Value> parameters, Map<String, Object> sessionMap) {
    return execute(null, parameters, sessionMap);
  }

  public Optional<Value> execute(GlobalSessionContext globalSessionContext,
      Map<String, Value> parameters, Map<String, Object> sessionMap) {
    logger.traceEntry(() -> parameters != null ? parameters.toString() : null, ()-> sessionMap != null ?sessionMap.toString() : null);
    CharcodeConverter command = new CharcodeConverter();
    HashMap<String, Object> convertedParameters = new HashMap<String, Object>();
    if(parameters.containsKey("sOriginalFile") && parameters.get("sOriginalFile") != null && parameters.get("sOriginalFile").get() != null) {
      convertedParameters.put("sOriginalFile", parameters.get("sOriginalFile").get());
      if(!(convertedParameters.get("sOriginalFile") instanceof String)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","sOriginalFile", "String", parameters.get("sOriginalFile").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("sOriginalFile") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","sOriginalFile"));
    }

    if(parameters.containsKey("sOriginalCode") && parameters.get("sOriginalCode") != null && parameters.get("sOriginalCode").get() != null) {
      convertedParameters.put("sOriginalCode", parameters.get("sOriginalCode").get());
      if(!(convertedParameters.get("sOriginalCode") instanceof String)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","sOriginalCode", "String", parameters.get("sOriginalCode").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("sOriginalCode") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","sOriginalCode"));
    }
    if(convertedParameters.get("sOriginalCode") != null) {
      switch((String)convertedParameters.get("sOriginalCode")) {
        case "Shift_JIS" : {

        } break;
        case "UTF-8" : {

        } break;
        case "EUC-JP" : {

        } break;
        default : throw new BotCommandException(MESSAGES_GENERIC.getString("generic.InvalidOption","sOriginalCode"));
      }
    }

    if(parameters.containsKey("sTargetFile") && parameters.get("sTargetFile") != null && parameters.get("sTargetFile").get() != null) {
      convertedParameters.put("sTargetFile", parameters.get("sTargetFile").get());
      if(!(convertedParameters.get("sTargetFile") instanceof String)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","sTargetFile", "String", parameters.get("sTargetFile").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("sTargetFile") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","sTargetFile"));
    }

    if(parameters.containsKey("sTargetCode") && parameters.get("sTargetCode") != null && parameters.get("sTargetCode").get() != null) {
      convertedParameters.put("sTargetCode", parameters.get("sTargetCode").get());
      if(!(convertedParameters.get("sTargetCode") instanceof String)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","sTargetCode", "String", parameters.get("sTargetCode").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("sTargetCode") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","sTargetCode"));
    }
    if(convertedParameters.get("sTargetCode") != null) {
      switch((String)convertedParameters.get("sTargetCode")) {
        case "Shift_JIS" : {

        } break;
        case "UTF-8" : {

        } break;
        case "EUC-JP" : {

        } break;
        default : throw new BotCommandException(MESSAGES_GENERIC.getString("generic.InvalidOption","sTargetCode"));
      }
    }

    try {
      command.action((String)convertedParameters.get("sOriginalFile"),(String)convertedParameters.get("sOriginalCode"),(String)convertedParameters.get("sTargetFile"),(String)convertedParameters.get("sTargetCode"));Optional<Value> result = Optional.empty();
      return logger.traceExit(result);
    }
    catch (ClassCastException e) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.IllegalParameters","action"));
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
