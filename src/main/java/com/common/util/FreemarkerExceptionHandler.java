package com.common.util;

import java.io.IOException;
import java.io.Writer;

import freemarker.core.Environment;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

public class FreemarkerExceptionHandler implements TemplateExceptionHandler {

	public void handleTemplateException(TemplateException te, Environment env,Writer out) throws TemplateException {
		try {
			out.write("[Error: " + te.getMessage() + "]");
		} catch (IOException e) {
			throw new TemplateException("Failed to print error message. Cause: " + e, env);
		}
	}

}
