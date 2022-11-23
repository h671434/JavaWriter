package model.models;

import java.util.ArrayList;

public class ProjectModel extends AbstractModel{
	
	private ArrayList<ClassModel> classes;

	public ProjectModel() {
		super();
		classes = new ArrayList<ClassModel>();
	}
	
	public ProjectModel(String name) {
		super(name);
		classes = new ArrayList<ClassModel>();
	}
	
	public ArrayList<ClassModel> getClasses() {
		return classes;
	}
	
	public void setClasses(ArrayList<ClassModel> classes) {
		this.classes = classes;
	}

	public void addClass(ClassModel newclass) {
		classes.add(newclass);
	}
	
	public void addClass(String name) {
		classes.add(new ClassModel(name));
	}
	
	public ClassModel getClass(String name) {
		ClassModel cmodel = null;
		
		for(ClassModel c : classes) 
			if(c.equals(name))
				cmodel = c;
		
		return cmodel;
	}
	
	public void removeClass(String name) {
		classes.remove(getClass(name));
	}
	
	public void removeClass(ClassModel cmodel) {
		classes.remove(cmodel);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
