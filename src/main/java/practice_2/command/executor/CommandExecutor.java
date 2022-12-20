package practice_2.command.executor;

import practice_2.command.CommandType;

public interface CommandExecutor {
    int execute(String text);

    CommandType getCommandType();

}
