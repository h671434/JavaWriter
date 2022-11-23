package model.codewriter;

import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Files;

import model.models.ClassModel;
import model.models.ProjectModel;

public class CodeWriter {
	
	public static void writeClass(ClassModel cmodel, File dir) throws Exception {

		File classfile = new File(dir + "/" + cmodel.getName()+ " .java");
		
		PrintWriter writer = new PrintWriter(classfile);
		
		writer.write(cmodel.toCode());
		
		writer.close();
	}
	
	public static void writeProject(ProjectModel project, File dir) throws Exception {

		File projectfolder = new File(dir + "/" + project.getName());
		
		projectfolder.mkdir();
		
		File projectfile = new File(projectfolder + "/.project");
		
		String projecttext = ""
				+ "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n"
				+ "<projectDescription>\r\n"
				+ "	<name>" + project.getName() + "</name>\r\n"
				+ "	<comment></comment>\r\n"
				+ "	<projects>\r\n"
				+ "	</projects>\r\n"
				+ "	<buildSpec>\r\n"
				+ "		<buildCommand>\r\n"
				+ "			<name>org.eclipse.jdt.core.javabuilder</name>\r\n"
				+ "			<arguments>\r\n"
				+ "			</arguments>\r\n"
				+ "		</buildCommand>\r\n"
				+ "	</buildSpec>\r\n"
				+ "	<natures>\r\n"
				+ "		<nature>org.eclipse.jdt.core.javanature</nature>\r\n"
				+ "	</natures>\r\n"
				+ "</projectDescription>";
		
		PrintWriter writer = new PrintWriter(projectfile);
		writer.write(projecttext);
		writer.close();
					
		File classpathfile = new File(projectfolder + "/.classpath");
		
    	String classpathtext = ""
    			+ "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n"
    			+ "<classpath>\r\n"
    			+ "\t<classpathentry kind=\"con\" path=\"org.eclipse.jdt.launching.JRE_CONTAINER\">\r\n"
    			+ "\t" + "\t<attributes>\r\n"
    			+ "\t" + "\t" + "\t<attribute name=\"module\" value=\"true\"/>\r\n"
    			+ "\t" + "\t</attributes>\r\n"
    			+ "\t</classpathentry>\r\n"
    			+ "\t<classpathentry kind=\"src\" path=\"src\"/>\r\n"
    			+ "\t<classpathentry kind=\"con\" path=\"org.eclipse.jdt.USER_LIBRARY/JavaFX\"/>\r\n"
    			+ "\t<classpathentry kind=\"output\" path=\"bin\"/>\r\n"
    			+ "</classpath>";	

    	writer = new PrintWriter(classpathfile);
    	writer.write(classpathtext);
    	writer.close(); 
		 	
		File settingsfolder = new File(projectfolder + "/.settings");
		File srcfolder = new File(projectfolder + "/src");
		File binfolder = new File(projectfolder + "/bin");
		
		settingsfolder.mkdir();
		srcfolder.mkdir();
		binfolder.mkdir();	

		File settingsfile = new File(settingsfolder + "/org.eclipse.core.resources.prefs");
    	
    	String settingstext = ""
    			+ "eclipse.preferences.version=1\r\n"
    			+ "encoding/<project>=UTF-8\r\n";
		
    	writer = new PrintWriter(settingsfile);
		writer.write(settingstext);
		writer.close();
	
		for(ClassModel c : project.getClasses())
			writeClass(c, srcfolder);
	}

}
