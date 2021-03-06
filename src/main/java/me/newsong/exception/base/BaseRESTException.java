package me.newsong.exception.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.newsong.exception.annotation.RESTAnnotationUtil;
import me.newsong.exception.domain.RESTFieldError;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import me.newsong.util.InternationalizeUtil;

public class BaseRESTException extends RuntimeException {
    private HttpStatus status;
    private int code;
    private List<RESTFieldError> error;
    private String moreInfoURL;

    public BaseRESTException() {
    }

    public BaseRESTException(Object rejectedValue) {
        RESTAnnotationUtil.setAttr(this);
        this.error = Arrays.asList(new RESTFieldError(RESTAnnotationUtil.getFieldName(this), rejectedValue, InternationalizeUtil
                .getMessage("i18n." + RESTAnnotationUtil.getMsgKey(this), LocaleContextHolder.getLocale())));
    }

    public BaseRESTException(List<RESTFieldError> error) {
        RESTAnnotationUtil.setAttr(this);
        this.error = error;
    }

    public static List<RESTFieldError> toRestFieldErrorList(List<FieldError> errors) {
        List<RESTFieldError> fieldErrors = new ArrayList<>(errors.size());
        for (FieldError error : errors) {
            fieldErrors.add(new RESTFieldError(error));
        }
        return fieldErrors;
    }

    public List<RESTFieldError> getErrors() {
        return error;
    }

    public void setErrors(List<RESTFieldError> error) {
        this.error = error;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public int getCode() {
        return code;
    }


    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMoreInfoURL() {
        return moreInfoURL;
    }

    public void setMoreInfoURL(String moreInfoURL) {
        this.moreInfoURL = moreInfoURL;
    }
}
