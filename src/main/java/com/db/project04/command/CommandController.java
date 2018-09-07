package com.db.project04.command;

import java.util.regex.Pattern;


/**
 * Class which is needed to parse raw commands (e.g. from console) which come as input
 * and create concrete Chat command (now supports getting history).
 */

public class CommandController {
    public static final Pattern COMMAND_STRING_PATTERN = Pattern.compile("\\/([A-Za-z]+)(.*)");
    public static final Pattern PREPARATION_COMMAND_STRING_PATTERN = Pattern.compile("(\\S+) (.+)");

    protected CommandController(){}
}
