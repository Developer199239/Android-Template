package com.surroundapps.jsonreadwrite;

import android.os.Environment;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.surroundapps.jsonreadwrite.dataModel.ServerConfig;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Murtuza Rahman on 2/27/19.
 */

public class FileReadWrite {

    private static final String TAG = FileReadWrite.class.getSimpleName();

    private static File configFile = null;
    private static FileWriter fileWriter = null;
    private static FileReader fileReader = null;
    private static final String FILE_NAME = "config_file.json";

    private static void createLogFile() {
        File root = new File(Environment.getExternalStorageDirectory(), "VCHUB");
        if (!root.exists()) {
            root.mkdirs();
        }
        if (configFile == null) {
            configFile = new File(root, FILE_NAME);
        }
    }

    public static boolean ifConfigFileExist() {
        createLogFile();
        if(configFile.exists() && !configFile.isDirectory()) {
            return true;
        }
        return false;
    }

    private static void initFileWriter() throws Exception{
        try {
            createLogFile();
            if (fileWriter == null) {
                fileWriter = new FileWriter(configFile, false);
            }
        } catch (Exception e) {
            throw new Exception(""+e.getMessage());
        }
    }

    private static void closeFileWriter() throws Exception{
        try {
            if (fileWriter != null) {
                fileWriter.close();
            }
        } catch (Exception e) {
            throw new Exception(""+e.getMessage());
        }
    }

    private static void initFileReader() throws Exception{
        try {
            createLogFile();
            if (fileReader == null) {
                fileReader = new FileReader(configFile);
            }
        } catch (Exception e) {
            throw new Exception(""+e.getMessage());
        }
    }

    private static void closeFileReader() {
        try {
            if (fileReader != null) {
                fileReader.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            fileReader = null;
        }
    }


    public static void writeJsonToSDCard(ServerConfig obj) throws Exception{

        initFileWriter();

        try {
            Gson gson = new Gson();
            gson.toJson(obj, fileWriter);
        }  catch (Exception e) {
            throw new Exception(""+e.getMessage());
        }
        finally {
            closeFileWriter();
        }
    }

    public static ServerConfig readJsonFromSDCard() throws Exception{
        initFileReader();
        try {
            Gson gson = new Gson();
            ServerConfig serverConfig = gson.fromJson(fileReader, ServerConfig.class);
            return serverConfig;
        } catch (Exception e) {
            throw new Exception(""+e.getMessage());
        }
        finally {
            closeFileReader();
        }
    }
}
