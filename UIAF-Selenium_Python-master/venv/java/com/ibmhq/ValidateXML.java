package com.ibmhq;

import java.io.File;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

public class ValidateXML {
	public String validator(String testdata) {
		String strstatus = null;
		try {
			String[] inputfile = testdata.split("\\|");
			String schemaFilePath1 = System.getProperty("user.dir") + "\\Selenium\\TestData\\DBFiles\\SchemaFiles\\"
					+ inputfile[0];
			String schemaFilePath = schemaFilePath1.replace("\\", "\\\\");
			File schemaFile = new File(schemaFilePath);
			System.out.println(schemaFilePath);
			String xmlFilePath1 = System.getProperty("user.dir") + "\\Selenium\\TestData\\DBFiles\\DBOutput\\"
					+ inputfile[1];
			String xmlFilePath = xmlFilePath1.replace("\\", "\\\\");
			Source xmlFile = new StreamSource(new File(xmlFilePath));
			SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
			Schema schema = schemaFactory.newSchema(schemaFile);
			Validator validator = schema.newValidator();
			System.out.println("Validating");
			validator.validate(xmlFile);
			System.out.println(xmlFile.getSystemId() + " is valid");
			System.out.println("Validation completed");
			strstatus = "Passed%%";
		} catch (Exception e) {
			strstatus = "Failed%%" + e.getMessage();
		}
		return strstatus;
	}
}
