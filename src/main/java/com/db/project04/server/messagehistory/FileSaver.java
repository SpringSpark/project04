package com.db.project04.server.messagehistory;

import com.db.project04.exceptions.FileException;

import java.io.*;
import java.nio.file.Paths;

public class FileSaver {

        public static final String LOG_FILES_DIRECTORY = Paths.get(".", "logs").toString();
        private static final String PREFIX_PATH = Paths.get(LOG_FILES_DIRECTORY, "log_").toString();

        private File fileToSaveInto;
        private String encoding;
        private PrintWriter printWriter;

        public String getLogFilename() {
            return fileToSaveInto.getAbsolutePath();
        }

        public FileSaver(String encoding) throws FileException{
            this.encoding = encoding;

            File logDirectory = new File(LOG_FILES_DIRECTORY);
            if (!logDirectory.exists()) {
                logDirectory.mkdir();
            }
            createLogFile();
            try {
                printWriter = new PrintWriter(
                                new BufferedWriter(
                                    new FileWriter(fileToSaveInto, false)));

            } catch (IOException e1) {
                throw new FileException("could not write into file", e1);
            }

        }

        public void save(String output) throws FileException {
            printWriter.println(output);
        }

        private void createLogFile() throws FileException {
            long timestamp = System.currentTimeMillis();
            fileToSaveInto = new File(PREFIX_PATH + timestamp);

            if (!fileToSaveInto.exists()) {
                try {
                    fileToSaveInto.createNewFile();
                } catch (IOException e) {
                    throw new FileException("could not create new empty file", e);
                }
            }
        }

    public PrintWriter getPrintWriter() {
        return printWriter;
    }
}

