package entities;

import java.io.File;
import java.io.IOException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class AbstractLevel implements Level {
	protected String location, title, author, version;
	protected int hexagonAmount, checkpointAmount;
	protected double width, height;
	protected Element HexaTripLevel, LevelProperties;
	protected Hexagon HexArray[];
	
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
			this.height = LevelProperties.getAttribute("height").getDoubleValue();
			this.width = LevelProperties.getAttribute("width").getDoubleValue();
			this.hexagonAmount = LevelProperties.getAttribute("hexagonAmount").getIntValue();
			this.checkpointAmount = LevelProperties.getAttribute("checkpointAmount").getIntValue();
			this.HexArray = new Hexagon[hexagonAmount];
			
			for( int i = 0; i < this.hexagonAmount; i++) {
				
			}
			int hexindex = 0;
			double x_temp = 0, y_temp = 0, w_temp = 0, h_temp = 0;
			HexagonType type_temp = null;
			for( Element i : HexaTripLevel.getChildren()) {
				if(i.getName() == "hexagon") {
					x_temp = 0; y_temp = 0; w_temp = 0; h_temp = 0;
					type_temp = null;
					
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
							System.err.println("invalid HexagonType in XML File at Hexagon " + hexindex + "!");
							break;
					}
					
					if (x_temp != 0 && y_temp != 0 && w_temp != 0 && h_temp != 0 && type_temp != null)
						this.HexArray[hexindex] = new AbstractHexagon(x_temp, y_temp, w_temp, h_temp, type_temp);
					
					hexindex++;
				}
			}
			
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
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
	public double getWidth() {
		return this.width;
	}

	@Override
	public double getHeight() {
		return this.width;
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

}
