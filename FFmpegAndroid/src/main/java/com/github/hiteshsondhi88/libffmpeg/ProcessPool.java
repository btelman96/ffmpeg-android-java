package com.github.hiteshsondhi88.libffmpeg;

import android.util.SparseArray;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Created by Brendon on 9/1/2018.
 */
public class ProcessPool {
    private static volatile HashMap<String, FFmpegExecuteAsyncTask> taskSparseArray = new HashMap<>(4);

    public synchronized static boolean isFull(){
        return taskSparseArray.size() >= 4;
    }

    public synchronized static FFmpegExecuteAsyncTask get(String UUID){
        return taskSparseArray.get(UUID);
    }

    public synchronized static void put(String UUID, FFmpegExecuteAsyncTask task){
        taskSparseArray.put(UUID, task);
    }

    public synchronized static boolean killAll() {
        boolean killedAll = true;
        for(FFmpegExecuteAsyncTask task : taskSparseArray.values()){
            killedAll = killedAll && Util.killAsync(task);
        }
        return killedAll;
    }

    public synchronized static void remove(String uuid) {
        taskSparseArray.remove(uuid);
    }
}
