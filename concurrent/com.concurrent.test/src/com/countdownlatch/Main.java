package com.countdownlatch;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		SystemStarter startup = SystemStarter.getInstance();
		startup.startUp();
	}
}
