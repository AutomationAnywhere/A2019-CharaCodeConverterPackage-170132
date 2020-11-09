package com.automationanywhere.botcommand.samples.commands.basic.types;

import com.automationanywhere.bot.service.GlobalSessionContext;
import com.automationanywhere.botcommand.Condition;
import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.data.impl.WindowValue;
import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.commandsdk.i18n.Messages;
import com.automationanywhere.commandsdk.i18n.MessagesFactory;
import java.lang.ClassCastException;
import java.lang.Object;
import java.lang.String;
import java.lang.Throwable;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class WindowTypeDemoCondition implements Condition {
  private static final Logger logger = LogManager.getLogger(WindowTypeDemoCondition.class);

  private static final Messages MESSAGES_GENERIC = MessagesFactory.getMessages("com.automationanywhere.commandsdk.generic.messages");

  public boolean test(Map<String, Value> parameters) {
    return test(null, parameters, null);
  }

  public boolean test(GlobalSessionContext globalSessionContext, Map<String, Value> parameters,
      Map<String, Object> sessionMap) {
    logger.traceEntry(() -> parameters != null ? parameters.toString() : null, ()-> sessionMap != null ?sessionMap.toString() : null);
    WindowTypeDemo command = new WindowTypeDemo();
    HashMap<String, Object> convertedParameters = new HashMap<String, Object>();
    if(parameters.containsKey("windowValue") && parameters.get("windowValue") != null && parameters.get("windowValue").get() != null) {
      convertedParameters.put("windowValue", parameters.get("windowValue").get());
      if(!(parameters.get("windowValue") instanceof WindowValue)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","windowValue", "WindowValue", parameters.get("windowValue").get().getClass().getSimpleName()));
      }
    }

    try {
      boolean result = command.windowExists((WindowValue)parameters.get("windowValue") );
      return logger.traceExit(result);
    }
    catch (ClassCastException e) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.IllegalParameters","windowExists"));
    }
    catch (BotCommandException e) {
      logger.fatal(e.getMessage(), e);
      throw e;
    }
    catch (Throwable e) {
      logger.fatal(e.getMessage(),e);
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.NotBotCommandException",e.getMessage()),e);
    }
  }
}
