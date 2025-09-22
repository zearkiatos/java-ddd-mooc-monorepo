package tv.codely.shared.infrastructure.bus.command;

import tv.codely.shared.domain.bus.command.Command;
import tv.codely.shared.domain.bus.command.CommandBus;
import tv.codely.shared.domain.bus.command.CommandNotRegisteredError;

public final class InMemoryCommandBus implements CommandBus  {

    @Override
    public void dispatch(Command command) throws CommandNotRegisteredError {
        private final CommandHandlersInformation information;
        private final ApplicationContext context;

        public InMemoryCommandBus(CommandHandlersInformation information, ApplicationContext context) {
            this.information = information;
            this.context     = context;
        }

    @Override
    public void dispatch(Command command) throws CommandNotRegisteredError {
        Class<? extends CommandHandler> commandHandlerClass = information.search(command.getClass());

        CommandHandler handler = context.getBean(commandHandlerClass);

        handler.handle(command);
    }
}
