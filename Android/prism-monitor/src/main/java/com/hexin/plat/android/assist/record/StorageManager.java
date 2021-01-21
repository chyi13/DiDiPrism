package com.hexin.plat.android.assist.record;

import android.content.Context;

import com.google.gson.Gson;
import com.hexin.plat.android.assist.model.CaseBean;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class StorageManager {
    private static StorageManager storageManager;

    public synchronized static StorageManager getInstance() {
        if (storageManager == null) {
            synchronized (StorageManager.class) {
                if (storageManager == null) {
                    storageManager = new StorageManager();
                }
            }
        }
        return storageManager;
    }

    StorageManager() {

    }

    Context context;

    public void init(Context context) {
        this.context = context;
    }

    public void saveRecord(CaseBean caseBean) {
        if (caseBean == null) {
            return;
        }
        Gson gson = new Gson();
        String caseJson = gson.toJson(caseBean);
        String caseFileName = "case" + "_" + caseBean.getApp() + "_" + caseBean.getName() + "_" + caseBean.getCreateTime() + ".json";
        File caseFile = new File(getLocalStorageSubDir(LOCAL_STORAGE_PATH_CASE_NAME), caseFileName);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(caseFile));
            writer.write(caseJson);
            writer.flush();
            writer.close();
            // TODO
        } catch (IOException e) {
            e.printStackTrace();
            // TODO
        }
    }

    public ArrayList<CaseBean> loadRecords() {
        ArrayList<CaseBean> caseList = new ArrayList<>();
        File caseFolder = getLocalStorageSubDir(LOCAL_STORAGE_PATH_CASE_NAME);
        if (caseFolder.isDirectory()) {
            File[] files = caseFolder.listFiles();
            for (File file : files) {
                if (file.getName().startsWith("case")) {
                    Gson gson = new Gson();
                    caseList.add(gson.fromJson(readFileAsString(file), CaseBean.class));
                }
            }
        }
        return caseList;
    }

    File localStorage;
    final String LOCAL_STORAGE_PATH_NAME = "localStorage";
    final String LOCAL_STORAGE_PATH_CASE_NAME = "case";

    private File getLocalStorageDir() {
        if (localStorage == null) {
            localStorage = new File(context.getExternalFilesDir(null), LOCAL_STORAGE_PATH_NAME);
        }
        if (!localStorage.exists()) {
            localStorage.mkdirs();
        }
        return localStorage;
    }

    private File getLocalStorageSubDir(String name) {
        File subDir = new File(getLocalStorageDir(), name);
        if (!subDir.exists()) {
            subDir.mkdirs();
        }
        return subDir;
    }

    private String readFileAsString(File file) {
        String content = "";
        if (file != null && file.exists()) {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                StringBuilder sb = new StringBuilder();
                String line;
                while((line = br.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                content = sb.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return content;
    }
}
