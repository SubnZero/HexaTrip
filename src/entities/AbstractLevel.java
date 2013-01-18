package entities;

/*	
 *	Level Class
 */

import static game.Boot.cleanUp;

import java.io.File;
import java.io.IOException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class AbstractLevel implements Level {
	protected String location, title, author, version;
	protected int hexagonAmount, checkpointAmount;
	protected Element HexaTripLevel, LevelProperties;
	protected Hexagon HexArray[];
	protected Checkpoint CheckArray[];
	protected double highestX;
	
	public AbstractLevel(String location) {
		this.location = location;
	}

	@Override
	public void draw() {
		if(this.HexArray != null) {
			for(Hexagon hex : this.HexArray) {
				if(hex != null)
					hex.draw();
			}
		}

	}

	@Override
	public void load() {
		try {
			File levelFile = new File(location);
			SAXBuilder reader = new SAXBuilder();
			Document document = reader.build(levelFile);
			HexaTripLevel = document.getRootElement();
			LevelProperties = HexaTripLevel.getChild("levelproperties");
			
			this.title = HexaTripLevel.getAttributeValue("title");
			this.author = HexaTripLevel.getAttributeValue("author");
			this.version = HexaTripLevel.getAttributeValue("version");
			this.hexagonAmount = LevelProperties.getAttribute("hexagons").getIntValue();
			this.checkpointAmount = LevelProperties.getAttribute("checkpoints").getIntValue();
			if(this.checkpointAmount < 2) {
				System.err.println("ERROR! You need at least 2 checkpoints (start and finish)!");
				System.exit(1);
			}
			this.HexArray = new Hexagon[hexagonAmount];
			this.CheckArray = new Checkpoint[checkpointAmount];
			
			int hexindex = 0;
			int checkindex = 0;
			double x_temp = 0, y_temp = 0, w_temp = 0, h_temp = 0;
			HexagonType type_temp = null;
			
			for( Element i : HexaTripLevel.getChildren()) {
				if(i.getName() == "hexagon" && (hexindex+1) <= this.hexagonAmount) {
					
					x_temp = i.getAttribute("x").getDoubleValue();
					y_temp = i.getAttribute("y").getDoubleValue();
					w_temp = i.getAttribute("w").getDoubleValue();
					h_temp = i.getAttribute("h").getDoubleValue();
					
					switch(i.getAttributeValue("type")) {
						case "NONE":
							type_temp = HexagonType.HEX_NONE;
							break;
						case "DEFAULT":
							type_temp = HexagonType.HEX_DEFAULT;
							break;
						case "RAMP":
							type_temp = HexagonType.HEX_RAMP;
							break;
						default:
							System.err.println("invalid HexagonType in XML File at Hexagon " + (hexindex+1) + "!");
							break;
					}
					
					if (x_temp != 0 && y_temp != 0 && w_temp != 0 && h_temp != 0 && type_temp != null)
						this.HexArray[hexindex] = new AbstractHexagon(x_temp, y_temp, w_temp, h_temp, type_temp);
					
					if(x_temp + w_temp > highestX)
						highestX = x_temp + w_temp;
					hexindex++;
					x_temp = 0; y_temp = 0; w_temp = 0; h_temp = 0;
					type_temp = null;
				} else if (i.getName() == "checkpoint" && (checkindex+1) <= this.checkpointAmount) {
					
					x_temp = i.getAttribute("x").getDoubleValue();
					y_temp = i.getAttribute("y").getDoubleValue();
					
					if (x_temp != 0 && y_temp != 0)
						this.CheckArray[checkindex] = new AbstractCheckpoint(x_temp, y_temp);
							
					if(x_temp > highestX)
						highestX = x_temp;
					checkindex++;
					x_temp = 0; y_temp = 0;
				}
			}
			if(hexindex+1 < this.hexagonAmount)
				System.err.println("WARNING! Only " + (hexindex+1) + " of " + this.hexagonAmount + " Hexagons are declared!");
			if(checkindex+1 < this.checkpointAmount)
				System.err.println("WARNING! Only " + (checkindex+1) + " of " + this.checkpointAmount + " Checkpoints are declared!");
				
		} catch (JDOMException e) {
			e.printStackTrace();
			cleanUp();
		} catch (IOException e) {
			e.printStackTrace();
			cleanUp();
		}
		
	}

	@Override
	public String getTitle() {
		return this.title;
	}

	@Override
	public String getAuthor() {
		return this.author;
	}

	@Override
	public String getVersion() {
		return this.version;
	}

	@Override
	public int getHexagonAmount() {
		return this.hexagonAmount;
	}

	@Override
	public int getCheckpointAmount() {
		return this.checkpointAmount;
	}

	@Override
	public double getGravity() {
		return 9.81d;
	}

	@Override
	public double getXStart() {
		return this.CheckArray[0].getX();
	}

	@Override
	public double getYStart() {
		return this.CheckArray[0].getY();
	}

	@Override
	public double getXFinish() {
		return this.CheckArray[this.checkpointAmount-1].getX();
	}

	@Override
	public double getYFinish() {
		return this.CheckArray[this.checkpointAmount-1].getY();
	}

	@Override
	public double getHighestX() {
		return this.highestX;
	}

}
