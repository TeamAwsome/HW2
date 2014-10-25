
public class Player {
	
	private int playerId;
	private String username;
	private String password;
	private double posX;
	private double posY;
	private double posZ;
	private double heading;
	private double roll;
	private double pitch;
	
	public Player(){
	
	}
	//new player to register
	public Player(String username, String password){
		this.username = username;
		this.password = password;
		this.posX = 0;
		this.posY = 0;
		this.posZ = 0;
		this.heading = 0;
		this.roll = 0;
		this.pitch = 0;
	}
	
	public Player(String username, 
			double x, double y, double z, 
			double h){
		this.username = username;
		this.posX = x;
		this.posY = y;
		this.posZ = z;
		this.heading = h;
		this.roll = 0;
		this.pitch = 0;
	}//modified this constructor a bit and removed password as parameter, because the client heartbeat wont send password all the time
	
	

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getPosX() {
		return posX;
	}

	public void setPosX(double posX) {
		this.posX = posX;
	}

	public double getPosY() {
		return posY;
	}

	public void setPosY(double posY) {
		this.posY = posY;
	}

	public double getPosZ() {
		return posZ;
	}

	public void setPosZ(double posZ) {
		this.posZ = posZ;
	}

	public double getHeading() {
		return heading;
	}

	public void setHeading(double heading) {
		this.heading = heading;
	}

	public double getRoll() {
		return roll;
	}

	public void setRoll(double roll) {
		this.roll = roll;
	}

	public double getPitch() {
		return pitch;
	}

	public void setPitch(double pitch) {
		this.pitch = pitch;
	}
	
	
}
