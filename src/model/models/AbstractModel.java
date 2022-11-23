package model.models;

public abstract class AbstractModel {

    protected String name;
    
    public AbstractModel() {
    	
    }

	public AbstractModel(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public boolean equals(AbstractModel model) {
		return name.equalsIgnoreCase(model.getName());
	}
	
	public boolean equals(String name) {
		return this.name.equalsIgnoreCase(name);
	}
	
    public String nameToUpperCase() {
    	return name.substring(0, 1).toUpperCase() + name.substring(1);
    }
    
    public abstract String toString();
 
}