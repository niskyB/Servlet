package helper;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author locnh
 */
public class GetParam {

    public static String getParamString(HttpServletRequest request, String param, String label, int min, int max) {
        String value = request.getParameter(param);
        if (value == null) {
            request.setAttribute(param + "ErrorMessage", label + " can not be empty");
            return null;
        }
        if (value.trim().length() < min) {
            request.setAttribute(param + "ErrorMessage", label + " must be greater than or equal " + min);
            return null;
        }
        if (value.trim().length() > max) {
            request.setAttribute(param + "ErrorMessage", label + " must be less than or equal " + max);
            return null;
        }
        return value;
    }

    public static Integer getParamInteger(HttpServletRequest request, String param, String label, int min, int max) {
        String temp = request.getParameter(param);
        Integer value;
        try {
            value = Integer.parseInt(temp);
        } catch (Exception e) {
            value = null;
        }
        if (value == null) {
            request.setAttribute(param + "ErrorMessage", label + " must be greater than or equal " + min);
            return null;
        }
        int length = Integer.toString(value).length();
        if (length < min) {
            request.setAttribute(param + "ErrorMessage", label + " must be greater than or equal " + min);
            return null;
        }
        if (length > max) {
            request.setAttribute(param + "ErrorMessage", label + " must be less than or equal " + max);
            return null;
        }
        return value;
    }

    public static Float getParamFloat(HttpServletRequest request, String param, String label, float min, float max) {
        String temp = request.getParameter(param);
        Float value;
        try {
            value = Float.parseFloat(temp);
        } catch (Exception e) {
            value = null;
        }
        if (value == null) {
            request.setAttribute(param + "ErrorMessage", label + " is invalid");
            return null;
        }
        if (value < min) {
            request.setAttribute(param + "ErrorMessage", label + " must be greater than or equal " + min);
            return null;
        }
        if (value > max) {
            request.setAttribute(param + "ErrorMessage", label + " must be less than or equal " + max);
            return null;
        }
        return value;
    }
}
