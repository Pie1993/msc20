package msc20.configuration;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	private String ATTACHS_FOLDER = "WEB-INF/resources/attachs/";
	private int MAX_UPLOAD_SIZE = 5 * 1024 * 1024; // 5 MB

	@Override
	protected void customizeRegistration(Dynamic registration) {
		registration.setMultipartConfig(getMultipartConfigElement());
	}

	private MultipartConfigElement getMultipartConfigElement() {
		MultipartConfigElement multipartConfigElement = new MultipartConfigElement(ATTACHS_FOLDER, MAX_UPLOAD_SIZE,
				MAX_UPLOAD_SIZE * 2, MAX_UPLOAD_SIZE / 2);
		return multipartConfigElement;
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { DispatcherConfiguration.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

}