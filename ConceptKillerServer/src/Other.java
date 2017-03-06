
public class Other {

	public static Level world = new Level();

	public static void generate(){
		world.generateMap();
	}
	public static String playerList(){
		String s=" ";
		for(int i=0;i<ServerMain.threads.size();i++){
			s.concat(ServerMain.threads.get(i).player.getName());
		}
		return s;
	}
}
