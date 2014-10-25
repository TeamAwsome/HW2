import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
	
	public static void main(String args[])
	{
		ServerSocket ss = null;
		Socket clientSocket = null;
		
	    try{
	        ss = new ServerSocket(1235); // can also use static final PORT_NUM , when defined
	    }
	    catch(IOException e){
	    e.printStackTrace();
	    System.out.println("Server error");
	    }
	    
	    while(true)
	    {
	        try{
	        	System.out.println("Server Listening......");
	            clientSocket= ss.accept();
	            System.out.println("connection Established");
	            ServerThread st=new ServerThread(clientSocket);
	            st.start();
	        }

	    catch(Exception e){
	        e.printStackTrace();
	        System.out.println("Connection Error"); }
	    }
	}

}
