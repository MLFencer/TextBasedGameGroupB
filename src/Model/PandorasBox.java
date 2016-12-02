package Model;

import java.util.ArrayList;
import java.util.Random;

public class PandorasBox {

	private Enemy e1= new Enemy("Hydra",30,15,0,0,0,90,1);
	private Enemy e2= new Enemy("Scarlac",30,15,0,0,0,90,1);
	private Enemy e3= new Enemy("Kree Warrior",40,15,0,0,0,90,1);
	private Enemy e4= new Enemy("Skeleton Warrior",10,15,0,0,0,90,1);
	private Enemy e5= new Enemy("Living Armor",15,15,0,0,0,90,1);
	private Enemy e6= new Enemy("Killer Croc",50,15,0,0,0,90,1);
	private Enemy e7= new Enemy("John Cena",50,20,0,0,0,90,1);
	private Enemy e8= new Enemy("Klingon Warrior",40,15,0,0,0,90,1);
	private Enemy e9= new Enemy("StormTrooper",15,5,0,0,0,90,1);
	private Enemy e0= new Enemy("Brown Coat",20,15,0,0,0,90,1);


	private Item i1= new Item("Excalibur", 30, Item.types.Weapon, Item.weaponTypes.Light, 10);
	private Item i2= new Item("Iron Sword",10, Item.types.Weapon, Item.weaponTypes.Light, 30);
	private Item i3= new Item("War Hammer",20, Item.types.Weapon, Item.weaponTypes.Heavy, 20);
	private Item i4= new Item("Cutlass", 15, Item.types.Weapon, Item.weaponTypes.Light, 25);
	private Item i5= new Item("Bandage",20, Item.types.Meds, Item.weaponTypes.Null, 0);
	private Item i6= new Item("Healing Potion",100, Item.types.Meds, Item.weaponTypes.Null, 0);
	public Item i7= new Item("Minor Healing Potion",30, Item.types.Meds, Item.weaponTypes.Null, 0);
	private Item i8= new Item("Suture Kit",40, Item.types.Meds, Item.weaponTypes.Null, 0);
	private Item i9= new Item("Fishing String and Hook",15, Item.types.Meds, Item.weaponTypes.Null, 0);
	private Item i0= new Item("Rags",10, Item.types.Meds, Item.weaponTypes.Null, 0);
	private Item i10= new Item("The First Blade",80, Item.types.Weapon, Item.weaponTypes.Light, 1);


	public ArrayList<Item> getItems(){
		ArrayList<Item>i=new ArrayList<Item>();
		i.add(i1);
		i.add(i2);
		i.add(i3);
		i.add(i4);
		i.add(i5);
		i.add(i6);
		i.add(i7);
		i.add(i8);
		i.add(i9);
		i.add(i0);
		i.add(i10);
		return i;
	}
	public ArrayList<Enemy> getEnemies(){
		ArrayList<Enemy>e=new ArrayList<Enemy>();
		e.add(e1);
		e.add(e2);
		e.add(e3);
		e.add(e4);
		e.add(e5);
		e.add(e6);
		e.add(e7);
		e.add(e8);
		e.add(e9);
		e.add(e0);
		return e;
	}

	public String getRandomDescription(){
	String[] descriptions= new String[15];

	descriptions[0] = "A crack in the ceiling above the middle of the north wall allows a trickle of water to flow down to the floor. The water pools near the base of the wall, and a rivulet runs along the wall an out into the hall. The water smells fresh.";

	descriptions[1] = "Thick cobwebs fill the corners of the room, and wisps of webbing hang from the ceiling and waver in a wind you can barely feel. One corner of the ceiling has a particularly large clot of webbing within which a goblin's bones are tangled.";

	descriptions[2] = "Tapestries decorate the walls of this room. Although they may once have been brilliant in hue, they now hang in graying tatters. Despite the damage of time and neglect, you can perceive once-grand images of wizards' towers, magical beasts, and symbols of spellcasting. The tapestry that is in the best condition bulges out weirdly, as though someone stands behind it (an armless statue of a female human spellcaster).";

	descriptions[3] = "Rats inside the room shriek when they hear the door open, then they run in all directions from a putrid corpse lying in the center of the floor. As these creatures crowd around the edges of the room, seeking to crawl through a hole in one corner, they fight one another. The stinking corpse in the middle of the room looks human, but the damage both time and the rats have wrought are enough to make determining its race by appearance an extremely difficult task at best.";

	descriptions[4] = "Corpses and pieces of corpses hang from hooks that dangle from chains attached to thick iron rings. Most appear humanoid but a few of the body parts appear more monstrous. You don't see any heads, hands, or feet -- all seem to have been chopped or torn off. Neither do you see any guts in the horrible array, but several thick leather sacks hang from hooks in the walls, and they are suspiciously wet and the leather looks extremely taut -- as if it' under great strain.";

	descriptions[5] = "This chamber is clearly a prison. Small barred cells line the walls, leaving a 15-foot-wide pathway for a guard to walk. Channels run down either side of the path next to the cages, probably to allow the prisoners' waste to flow through the grates on the other side of the room. The cells appear empty but your vantage point doesn't allow you to see the full extent of them all.";

	descriptions[7] = "Huge rusted metal blades jut out of cracks in the walls, and rusting spikes project down from the ceiling almost to the floor. This room may have once been trapped heavily, but someone triggered them, apparently without getting killed. The traps were never reset and now seem rusted in place";

	descriptions[8] = "Neither light nor dark can penetrate the gloom in this chamber. An unnatural shade fills it, and the room's farthest reaches are barely visible. Near the room's center, you can just barely perceive a lump about the size of a human lying on the floor. (It might be a dead body, a pile of rags, or a sleeping monster that can take advantage of the room's darkness.) ";

	descriptions[9] = "A dozen statues stand or kneel in this room, and each one lacks a head and stands in a posture of action or defense. All are garbed for battle. It's difficult to tell for sure without their heads, but two appear to be dwarves, one might be an elf, six appear human, and the rest look like they might be orcs";

	descriptions[10] = "Burning torches in iron sconces line the walls of this room, lighting it brilliantly. At the room's center lies a squat stone altar, its top covered in recently spilled blood. A channel in the altar funnels the blood down its side to the floor where it fills grooves in the floor that trace some kind of pattern or symbol around the altar. Unfortunately, you can't tell what it is from your vantage point.";

	descriptions[11] = "Fire crackles and pops in a small cooking fire set in the center of the room. The smoke from a burning rat on a spit curls up through a hole in the ceiling. Around the fire lie several fur blankets and a bag. It looks like someone camped here until not long ago, but then left in a hurry.";

	descriptions[12] = "You gaze into the room and hundreds of skulls gaze coldly back at you. They're set in niches in the walls in a checkerboard pattern, each skull bearing a half-melted candle on its head. The grinning bones stare vacantly into the room, which otherwise seems empty.";

	descriptions[13] = "You open the door to confront a room of odd pillars. Water rushes down from several holes in the ceiling, and each hole is roughly a foot wide. The water pours in columns that fall through similar holes in the floor, flowing down to some unknown depth. Each of the eight pillars of water drops as much liquid as a stream in winter thaw. The floor is damp and looks slippery.";

	descriptions[14] = "You peer into this room and spot the white orb of a skull lying on the floor. Suddenly a stone falls from the ceiling and smashes the skull to pieces. An instant later, another stone from the ceiling drops to strike the floor and shatter. You hear a low rumbling and cracking noise. ";

	descriptions[6] = "A stone throne stands on a foot-high circular dais in the center of this cold chamber. The throne and dais bear the simple adornments of patterns of crossed lines -- a pattern also employed around each door to the room.";

	Random r = new Random();

	return descriptions[r.nextInt(15)];
	}



	//These Items will not be added to the ArrayList!
	public Item fist=new Item("Fist",0,Item.types.Weapon, Item.weaponTypes.Light, 999999);
}