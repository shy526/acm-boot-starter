package com.github.qing.form;

import org.springframework.util.StringUtils;

/**
 * @author qing
 */
public class FormFactory {
    private final static FormProcessor DEFAULT_FORM_PROCESSOR = new PropertiesForm();

    public static FormProcessor getFormProcessor(String formName) {
        FormProcessor result = DEFAULT_FORM_PROCESSOR;
        if (StringUtils.isEmpty(formName)) {
            return result;
        }
        switch (formName) {
            case "json": {
                result = new JsonForm();
                break;
            }
            case "properties": {
                result = new PropertiesForm();
                break;
            }
            default: {

            }
        }
        return result;
    }
}
