package com.antonioaltieri.telegram.botapi;


import com.antonioaltieri.telegram.botapi.types.Message;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HandlerNotifier {

    private final static Logger logger = Logger.getLogger(com.antonioaltieri.telegram.botapi.HandlerNotifier.class.getName());

    private Object objectWithHandlers;
    private Method defaultHandler;
    private List<MessageFilter> messageHandlers = new ArrayList<>();

    public HandlerNotifier(Object objectWithHandlers) {
        this.objectWithHandlers = objectWithHandlers;
        try {
            defaultHandler=this.getClass().getDeclaredMethod("defaultHandler");
        } catch (NoSuchMethodException e) {
            logger.warning("defaultHandler not setted. It can cause Unexpected exceptions and behavior. Please set a defaultHandler ");
            defaultHandler=null;
        }
        indexHandlers();
    }

    private void indexHandlers() {
        Method[] declaredMethods = objectWithHandlers.getClass().getDeclaredMethods();
        for (Method method : declaredMethods) {
            if (method.isAnnotationPresent(MessageHandler.class)) {
                logger.info("Found MessageHandler: " + method.getName());
                MessageHandler messageHandlerAnnotation = method.getAnnotation(MessageHandler.class);
                messageHandlers.add(new MessageHandlerFilter(messageHandlerAnnotation.contentTypes(), method));
            } else if (method.isAnnotationPresent(CommandHandler.class)) {
                logger.info("Found CommandHandler: " + method.getName());
                CommandHandler commandHandlerAnnotation = method.getAnnotation(CommandHandler.class);
                messageHandlers.add(0,new CommandHandlerFilter(commandHandlerAnnotation.value(), method));
            } else if (method.isAnnotationPresent(DefaultHandler.class)) {
                logger.info("Found DefaultHandler: " + method.getName());
                defaultHandler = method;
            }
        }

    }
    public void defaultHandler(){

    }
    public void notifyHandlers(final Message message) {
        Method handler = null;
        for (MessageFilter filter : messageHandlers) {
            boolean isValid=filter.valid(message);
            if (isValid) {
                logger.info("Handler "+filter.toString()+ "will handle the message "+message.getText());
                handler = filter.getHandler();
                break;
            }
        }

        if (handler == null)
            handler = defaultHandler;

        final Method handlerToExecute = handler;
        notifyMessageHandler(handlerToExecute, message);
    }

    private void notifyMessageHandler(Method handler, Message message) {
        try {
            handler.invoke(objectWithHandlers, message);
        } catch (IllegalAccessException | InvocationTargetException e) {
            logger.log(Level.SEVERE, "An exception occurred while trying to invoke handler '" + handler.getName() + "'", e);
        }
    }

    private interface MessageFilter {
        boolean valid(Message message);
        Method getHandler();
    }

    private static class MessageHandlerFilter implements MessageFilter {

        private Method handler;
        private List<Message.Type> contentTypes;

        public MessageHandlerFilter(Message.Type[] contentTypes, Method handler) {
            this.contentTypes = Arrays.asList(contentTypes);
            this.handler = handler;
        }

        @Override
        public boolean valid(Message message) {
            logger.info(this.toString()+ " can handle "+message.getText()+" ?"+ contentTypes.contains(message.getType()));
            return contentTypes.contains(message.getType());
        }

        @Override
        public Method getHandler() {
            return handler;
        }
    }

    private static class CommandHandlerFilter implements MessageFilter {

        private Method handler;
        private List<String> commands;

        public CommandHandlerFilter(String[] commands, Method handler) {
            this.handler = handler;
            this.commands = Arrays.asList(commands);
        }

        @Override
        public boolean valid(Message message) {
            boolean valid=isCommand(message) && commands.contains(extractCommand(message));
            logger.info(this.toString()+ " can handle "+message.getText()+"? "+ valid);

            return valid;
        }

        private String extractCommand(Message message) {
            if (isCommand(message)) {
                String text = message.getText();
                String cmdTrg[]=text.split(" ")[0].split("@");
                String cmd=cmdTrg[0].substring(1);
                String target=null;
                if(cmdTrg.length>=2) target=cmdTrg[1];
                if(target==null || target.equalsIgnoreCase(Properties.BotUser.getUsername()))
                return cmd;
            }
            return null;
        }

        private boolean isCommand(Message message) {
            return message.getType() == Message.Type.TEXT && message.getText().startsWith("/");
        }

        @Override
        public Method getHandler() {
            return handler;
        }
    }

}
