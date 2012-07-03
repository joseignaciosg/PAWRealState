package ar.edu.itba.it.paw.web.common;

import java.util.*;

import org.apache.wicket.markup.html.form.upload.*;
import org.apache.wicket.util.string.*;
import org.apache.wicket.validation.*;
import org.apache.wicket.validation.validator.*;

public class MultipleFileTypeValidator extends AbstractValidator<Collection<FileUpload>>{

    private static final long serialVersionUID = 1L;
    private List<String> mimeTypes;
    
    public MultipleFileTypeValidator (String... mimeTypes) {
            super();
            this.mimeTypes = Arrays.asList(mimeTypes);
    }

    @Override
    protected void onValidate(IValidatable<Collection<FileUpload>> validatable) {
            for (FileUpload f : validatable.getValue()) {
                    String uploadType = f.getContentType();
                    if (uploadType == null || !mimeTypes.contains(uploadType))
                            error(validatable);
            }
    }

    @Override
    protected Map<String, Object> variablesMap(IValidatable<Collection<FileUpload>> validatable) {
            final Map<String, Object> map = super.variablesMap(validatable);
            String types = "'" + Strings.join("', '", mimeTypes.toArray(new String[0])) + "'";
            map.put("allowed", types);
            return map;
    }

}