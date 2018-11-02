package me.saru.gamehub;

import me.saru.gamehub.domain.exception.ValidCustomException;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@Component
public class CustomErrorAttributes extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(
            WebRequest webRequest,
            boolean includeStackTrace) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, includeStackTrace);
        Throwable error = getError(webRequest);
        if (error instanceof ValidCustomException) {
            errorAttributes.put("errors", ((ValidCustomException) error).getErrors());

        }
        return errorAttributes;
    }
}
