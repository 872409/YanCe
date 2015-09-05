package com.tianbin.yance.network;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskPool {
	
	private int nThreads = 10;
	private ExecutorService newFixedThreadPool;
	
	public TaskPool(){
		newFixedThreadPool = Executors.newFixedThreadPool(nThreads);
	}
	
	public void addTask(Runnable runnable){
		newFixedThreadPool.execute(runnable);
	}
	
	public void shutDown(){
		newFixedThreadPool.shutdownNow();
	}

	public int getnThreads() {
		return nThreads;
	}

	public void setnThreads(int nThreads) {
		this.nThreads = nThreads;
	}
}
