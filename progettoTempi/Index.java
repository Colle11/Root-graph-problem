
public class Index {
	
	// index's color
	public enum Color {
		BLACK, RED
	}
	
	// index's style
	public enum Style {
		SOLID, DASHED
	}
	
	// instance attributes
	private final int value;
	private Index next;
	private Color color;
	private Style style;
	
	// Index constructor
	public Index(int val){
		value = val;
		next = null;
		color = Color.BLACK;
		style = Style.SOLID;
	}
	
	// method to get value
	public int getValue(){
		return value;
	}
	
	// method to see if there is a next element
	public boolean hasNext(){
		return (next != null);
	}
	
	// method to get the next element
	public Index next(){
		return next;
	}
	
	// method to set next
	public void setNext(Index nxt){
		next = nxt;
	}
	
	// method to get color
	public Color getColor(){
		return color;
	}
	
	// method to set color
	public void setColor(Color clr){
		color = clr;
	}
	
	// method to get style
	public Style getStyle(){
		return style;
	}
	
	// method to set style
	public void setStyle(Style stl){
		style = stl;
	}
	
	// method to get a String object representing the value of the Index Object
	public String toString(){
		return getValue() + "";
	}
	
}
