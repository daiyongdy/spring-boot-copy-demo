package com.hbdiy.sb.config;

import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by john on 2016/9/9.
 */
public class MyFreeMakerView extends FreeMarkerView {
    private static final String BASE_PATH = "basePath";

    @Override
    protected void exposeHelpers(Map model, HttpServletRequest request) throws Exception {
        model.put(BASE_PATH, request.getContextPath());
        super.exposeHelpers(model, request);
    }
}
