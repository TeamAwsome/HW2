import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.Socket;
import java.util.ArrayList;


public class ServerThread extends Thread{
	
	BufferedReader fromclient;
	InputStreamReader isr;
	PrintWriter toclient;
	Socket clientSocket;
	String str,username,password; //request msg
	String request[],response;
	DataInputStream dis;
	double x,y,z,h;
	Player temp;
	ArrayList<Player> allPlayers;
	
	
	public ServerThread(Socket clientSocket) {
		super();
		this.clientSocket = clientSocket;
	}
	
	public static ArrayList<Player> fetchHeartBeat(Player p) //in this method send a call to database get the list of all players store than data in a arraylist of Player datatype and send it back
	{
		ArrayList<Player> playerList = new ArrayList<Player>();
		
		playerList.add(new Player("John",1.0,2.0,3.0,4.0));
		playerList.add(new Player("Jane",4.0,5.0,6.0,7.0));
		
		return playerList; 
	}
	
	public void run()
	{
		while(true)
		{
		try{
		System.out.println("Got a client");
		System.out.println("Client Address "+ clientSocket.getInetAddress().toString());
		System.out.println("Waiting for the request...");
		
		try{
		isr = new InputStreamReader(clientSocket.getInputStream());
		fromclient = new BufferedReader(isr);
		toclient = new PrintWriter(clientSocket.getOutputStream());
		}catch(IOException e){
	        System.out.println("IO error in server thread");}
		

		
		str = fromclient.readLine();
		request = str.split(",");
		System.out.println(request[0]);
		
		
		if(request[0].equals("1"))  //registration request
		{
			username = request[1];
			password = request[2];
			response = "1"; //append registration success or fail after this, the digit 1 here will specify that this is a response to register request
			
			// check if the username already exists in the database and add a user to the database
			response = response+ "success"; //or fail
			toclient.println(response);
			System.out.println("Response sent to the client " + toclient.checkError());
		}
		else if(request[0].equals("2"))  //login request
		{
			username = request[1];
			password = request[2];
			
			// validate if the user exists in database and send a reply to the client
			response  = "2"; //append login success or fail after this, the digit 2 here will specify that this is a response to login request
			response = response+ "success"; //or fail
			toclient.println(response);
			System.out.println("Response sent to the client " + toclient.checkError());
		}
		else if(Integer.parseInt(request[0])==3) //client heartbeat with its co-ordinates
		{
			username = request[1];
			x = Double.parseDouble(request[2]);
			y = Double.parseDouble(request[3]);
			z = Double.parseDouble(request[4]);
			h = Double.parseDouble(request[5]);
			
			temp = new Player(username,x,y,z,h); //splitting data and storing respective values in player object, now add this data to database
			
			allPlayers = fetchHeartBeat(temp); // check above method fetchHeartbeat, which will insert data inside temp into database and send the co-ordinates of other players in a arraylist
			
			response = "3";
			for(Player p : allPlayers)
			{
				response = response+"/"+p.getUsername()+","+String.valueOf(p.getPosX())+","+String.valueOf(p.getPosY())+","+String.valueOf(p.getPosZ())+","+String.valueOf(p.getHeading());
			}
			System.out.println(response);
			toclient.println(response);
		System.out.println("Response sent to the client " + toclient.checkError());
		}
		
		
		
		//response = "Default Message";
		//toclient.println(response);
		//System.out.println("Response sent to the client " + toclient.checkError());
				
		if(str.equals("bye"))
		{
			fromclient.close();
			toclient.close();
			clientSocket.close();
			break;
		}
		}
	catch(NullPointerException e){this.interrupt();
	break;
	}
		// when a client disconnects write the client disconnection logic over here
	catch(Exception ex){ System.out.println("Client Disconnect");
	this.interrupt();
	break;}
	}
}
	
	

}
