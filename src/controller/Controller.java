package controller;

import java.io.File;
import java.util.ArrayList;

import model.codewriter.CodeWriter;
import model.models.ClassModel;
import model.models.ProjectModel;
import model.models.VariableModel;

public class Controller {

	private ProjectModel project;
	private ClassModel cmodel;
	private VariableModel variable;
	
	private File dir;
	
	public enum StatusCode {
		OK, ERROR, 
		CLASS_EXISTS, VARIABLE_EXISTS, 
		CLASS_NOTFOUND, VARIABLE_NOTFOUND, 
		DIR_NOTFOUND
	}

	public Controller() {
		super();
		project = new ProjectModel();
	}

	public Controller(ProjectModel project) {
		this.project = project;
	}
	
	public ArrayList<ClassModel> getClasses() {
		return project.getClasses();
	}
	
	public ArrayList<VariableModel> getVariables() {
		return cmodel.getVariables();
	}
	
	public String getClassCode() {
		return cmodel.toCode();
	}
	
	public StatusCode newClass() {

		StatusCode status = StatusCode.OK;

		cmodel = new ClassModel();
		project.addClass(cmodel);
	
		return status;	
	}
	
	public StatusCode newClass(String name) {

		StatusCode status = StatusCode.OK;

		if(project.getClass(name) == null ) {
			cmodel = new ClassModel(name);
			project.addClass(cmodel);
		} else {
			status = StatusCode.CLASS_EXISTS;
		}
		return status;	
	}
	
	public StatusCode newVariable() {

		StatusCode status = StatusCode.OK;
		
		variable = new VariableModel();
		cmodel.addVariable(variable);

		return status;	
	}
	
	public StatusCode selectClass(String name) {

		StatusCode status = StatusCode.OK;

		cmodel = project.getClass(name);
		
		if(cmodel == null)
			status = StatusCode.CLASS_NOTFOUND;

		return status;	
	}
	
	public StatusCode selectVariable(String name) {

		StatusCode status = StatusCode.OK;

		variable = cmodel.getVariable(name);
		
		if(variable == null)
			status = StatusCode.VARIABLE_NOTFOUND;

		return status;	
	}
	
	public StatusCode refactorProject(String name) {
		
		StatusCode status = StatusCode.OK;

		project.setName(name);

		return status;	
	}
	
	public StatusCode refactorClass(String name) {
		
		StatusCode status = StatusCode.OK;

		cmodel.setName(name);

		return status;	
	}
	
	public StatusCode refactorVariable(String type, String name) {
		
		StatusCode status = StatusCode.OK;

		variable.setName(name);
		variable.setDataType(type);

		return status;	
	}	
	
	public StatusCode deleteClass() {
		
		StatusCode status = StatusCode.OK;
		
		if (cmodel == null)
			status = StatusCode.CLASS_NOTFOUND;
		else
			project.removeClass(cmodel);
		
		return status;
	}
	
	public StatusCode deleteVariable() {
		
		StatusCode status = StatusCode.OK;
		
		if (variable == null)
			status = StatusCode.VARIABLE_NOTFOUND;
		else
			cmodel.removeVariable(variable);
		
		return status;
	}
	
	public StatusCode SelectDir(File dir) {
		
		StatusCode status = StatusCode.OK;
		
		this.dir = dir;
		
		return status;
	}
	
	public StatusCode writeProject() {
		
		StatusCode status = StatusCode.OK;
		
		if(cmodel == null) {
			status = StatusCode.CLASS_NOTFOUND;
		} else {
			try {
				
				CodeWriter.writeProject(project, dir);
				
			} catch (Exception e) {
				e.printStackTrace();
				status = StatusCode.ERROR;
			}
		}
		
		return status;
	}
	
	public StatusCode writeClass() {
		
		StatusCode status = StatusCode.OK;
		
		if(cmodel == null) {
			status = StatusCode.CLASS_NOTFOUND;
		} else {
			try {
				
				CodeWriter.writeClass(cmodel, dir);
				
			} catch (Exception e) {
				e.printStackTrace();
				status = StatusCode.ERROR;
			}
		}
		
		return status;
	}
}