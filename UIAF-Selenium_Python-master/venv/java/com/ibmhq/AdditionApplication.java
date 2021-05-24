package com.ibmhq;


import py4j.GatewayServer;
public class AdditionApplication {
	 public String addition(int first, int second) {
		 System.out.println(first+second);
		    return ("lija");
		  }

		  public static void main(String[] args) {
		    AdditionApplication app = new AdditionApplication();
		    
		    GatewayServer server = new GatewayServer(app);
		    server.start();
		    System.out.println("BHARATH");
		  }

}
