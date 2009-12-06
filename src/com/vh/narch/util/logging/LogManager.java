/*
 * << Narch >>
 * by VanHelsin 2004
 * GNU General Public License
 * http://www.gnu.org/copyleft/gpl.html
 */
package com.vh.narch.util.logging;

import java.io.File;
import java.io.RandomAccessFile;
import java.sql.Connection;
import java.sql.Timestamp;

import com.vh.narch.database.ConnectionManager;
import com.vh.narch.util.SystemProperties;

/**
 * Implementa um gerenciador para os logs do sistema
 */
public class LogManager {

    public static void log(String message) {
        try {
	        LogVO log = getLogVO(message);
            String path = SystemProperties.getProperty("log.path");
	        if (path.equals("screen")) { 
	            screenLog(log); 
	        } else if (path.equals("database")) { 
	            databaseLog(log); 
	        } else { 
	            fileLog(log, path); 
	        }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void screenLog(LogVO log) {
        System.out.println(log.getTime() + " - " + log.getMessage());
    }

    private static void databaseLog(LogVO log) throws Exception {
        Connection con = ConnectionManager.getConnection();
        new LogDAO().insert(log, con);
    }

    private static void fileLog(LogVO log, String path) throws Exception {
        File file = new File(path + "//narch.log");
        if (file != null) {
            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            raf.seek(raf.length());
            raf.writeChars(log.getTime() + " - " + log.getMessage() + "\n");
        }
    }

    private static LogVO getLogVO(String message) {
        LogVO log = new LogVO();
        log.setMessage(message);
        log.setTime(new Timestamp(System.currentTimeMillis()));
        return log;
    }

}