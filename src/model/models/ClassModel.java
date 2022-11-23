package model.models;

import java.util.ArrayList;

public class ClassModel extends AbstractModel{

	private ArrayList<VariableModel> variables;
	
	public ClassModel() {
		super();
		variables = new ArrayList<VariableModel>();
	}
	
	public ClassModel(String name) {
		super(name);
		variables = new ArrayList<VariableModel>();
	}
	
	public ArrayList<VariableModel> getVariables() {
		return variables;
	}
	
	public void setVariables(ArrayList<VariableModel> variables) {
		this.variables = variables;
	}
	
	public void addVariable(VariableModel var) {
		variables.add(var);
	}
	
	public void addVariable(String name, String datatype) {
		variables.add(new VariableModel(name, datatype));
	}
	
	public VariableModel getVariable(String name) {
		VariableModel var = null;
		
		for (VariableModel v : variables) 
			if (v.equals(name)) 
				var = v;
			
		return var;
	}
	
	public void removeVariable(VariableModel var) {
		variables.remove(var);
	}
	
	public void removeVariable(String name) {
		variables.remove(getVariable(name));
	}
	
	public String constructorToCode() {
		String code = ""
				+ "\tpublic " + name + "() {\n"
				+ "\n"
				+ "\t}\n"
				+ "\n"
				+ "\tpublic " + name + "(";
		
		for(VariableModel var : variables) {
			code += var.getDataType() + " " + var.getName();
			if (!var.equals(variables.get(variables.size() - 1)))
				code += ", ";
		}
		
		code += ") {\n";
		
		for(VariableModel var : variables)
			code += "\t\tthis." + var.getName() + " = " + var.getName() + ";\n";
		
		code += "\t}\n"
				+ "\n";
		
		return code;
	}
	
	public String toCode() {
		String code = ""
				+ "package model;\n"
				+ "\n"
				+ "public class " + name + " {\n"
				+ "\n";
		
		for(VariableModel var : variables)
			code += "\tprivate " + var.getDataType() + " " + var.getName() + ";\n";
		
		code += "\n";
		
		code += constructorToCode();
		
		for (VariableModel var : variables)
			code += var.toCode();
		
		code += "}";
		
		return code;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}


}
