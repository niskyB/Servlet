package helper;

import javax.servlet.http.HttpServletRequest;

public class GetParam {

    public static String getParamString(HttpServletRequest request,
            String param, String label, int min, int max) {
        // Get param
        String value = request.getParameter(param).trim();

        // Check value
        if (value == null) {
            request.setAttribute(param + "ErrorMessage", label + " can not be blank");
            return null;
        }
        if (value.length() < min) {
            request.setAttribute(param + "ErrorMessage", label + " must be greater than " + min);
            return null;
        }
        if (value.length() > max) {
            request.setAttribute(param + "ErrorMessage", label + " must be less than " + max);
            return null;
        }
        return value;
    }

    public static Integer getParamInteger(HttpServletRequest request,
            String param, String label, int min, int max) {
        Integer value;

        // Get and convert param to Integer
        try {
            value = Integer.parseInt(request.getParameter(param));
        } catch (Exception e) {
            value = null;
        }

        // Check value
        if (value == null) {
            request.setAttribute(param + "ErrorMessage", label + " is invalid.");
            return null;
        }

        // Get length of Integer and check it
        int length = Integer.toString(value).length();

        if (length < min) {
            request.setAttribute(param + "ErrorMessage", label + " must contain at least " + min + " numbers.");
            return null;
        }
        if (length > max) {
            request.setAttribute(param + "ErrorMessage", label + " can contain maximum " + max + " numbers.");
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
