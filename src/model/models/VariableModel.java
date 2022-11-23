package model.models;

public class VariableModel extends AbstractModel{

    private String datatype;
    
    boolean writeget;
    boolean writeset;
    boolean writeequals;

    public VariableModel() {
    	super();
    }

    public VariableModel(String datatype, String name) {
    	super(name);
        this.datatype = datatype;
        
        writeget = false;
        writeset = false;
        writeequals = false;
    }

    public String getDataType() {
        return datatype;
	}

    public void setDataType(String datatype) {
        this.datatype = datatype;
    }

    public boolean getWriteGet() {
        return writeget;
	}

    public void setWriteGet(boolean writeget) {
        this.writeget = writeget;
    }
    
    public String getToCode() {
    	String code = ""
    			+ "\tpublic " + datatype + " get" + nameToUpperCase() + "() {\n"
    			+ "\t\treturn " + name + ";\n"
    			+ "\t}\n"
    			+ "\n";
    
    	return code;
    }
    
    public boolean getWriteSet() {
        return writeset;
	}

    public void setWriteSet(boolean writeset) {
        this.writeset = writeset;
    }
    
    public String setToCode() {
    	String code = ""
    			+ "\tpublic void set" + nameToUpperCase() + "(" + datatype + " " + name + ") {\n"
    			+ "\t\tthis." + name + " = " + name + ";\n"
    			+ "\t}\n"
    			+ "\n";
    
    	return code;
    }
    
    public boolean getWriteEquals() {
        return writeequals;
    }

    public void setWriteEquals(boolean writeequals) {
        this.writeequals = writeequals;
    }
    
    public String equalsToCode() {
    	String code = ""
				+ "\tpublic boolean equals(" + datatype + " " + name + ") {\n"
				+ "\t\tboolean equal = false;\n";
    	
    	if(datatype.equals("String"))
    		code += "\t\tif (this." + name + ".equals(" + name + ")\n"
    				+ "\t\t\tequal = true\n";
    	else
    		code += "\t\tif (this." + name + " == " + name + ")\n"
    				+ "\t\t\tequal = true\n";
    	
    	code += "\t\treturn equal;\n"
    			+ "\t}\n"
    			+ "\n";
    	
    	return code;
    }
    
	public String toCode() {
		String code = "";
		
		if(writeget)
			code += getToCode();
		if(writeset)
			code += setToCode();
		if(writeequals)
			code += equalsToCode();
		
		return code;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
}
