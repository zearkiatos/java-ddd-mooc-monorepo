package tv.codely.apps.mooc.backend.command;

import tv.codely.shared.domain.ServiceInjectable;
import tv.codely.shared.infrastructure.cli.ConsoleCommand;

@ServiceInjectable
public final class FakeCommand extends ConsoleCommand {
    @Override
    public void execute(String[] args) {
        info("This is a fake command!");
    }
}
