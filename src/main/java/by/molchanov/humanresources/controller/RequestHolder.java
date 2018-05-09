package by.molchanov.humanresources.controller;

import by.molchanov.humanresources.exception.CustomBrokerException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.*;

import static by.molchanov.humanresources.constant.SessionRequestAttributeNames.COMMAND;
import static by.molchanov.humanresources.constant.SessionRequestAttributeNames.HASH;

public class RequestHolder {
    private static final int PRIMARY_HASH = 0;
    private Map<String, Object> requestAttribute = new HashMap<>();
    private Map<String, Object> sessionAttribute = new HashMap<>();
    private Map<String, String[]> requestParameter = new HashMap<>();
    private List<String> sessionAttributeForDelete = new ArrayList<>();
    private String command;

    public RequestHolder(HttpServletRequest request) {
        command = "";
        Object retrievedObject;
        String retrievedName;
        requestParameter = request.getParameterMap();
        HttpSession session = request.getSession();
        Enumeration<String> sessionAttributeNames = session.getAttributeNames();
        Enumeration<String> requestAttributeNames = request.getAttributeNames();
        while (requestAttributeNames.hasMoreElements()) {
            retrievedName = requestAttributeNames.nextElement();
            retrievedObject = request.getAttribute(retrievedName);
            requestAttribute.put(retrievedName, retrievedObject);
        }

        Integer currentHash = PRIMARY_HASH;
        Integer hash = (Integer) session.getAttribute(HASH);
        for (Map.Entry<String, String[]> pair: requestParameter.entrySet())
        {
            currentHash = Arrays.hashCode(pair.getValue());
        }
        if (hash == null) {
            session.setAttribute(HASH, PRIMARY_HASH);
        } else if (!currentHash.equals(hash)) {
            session.removeAttribute(HASH);
            session.setAttribute(HASH, currentHash);
            command = request.getParameter(COMMAND);
        }
        while (sessionAttributeNames.hasMoreElements()) {
            retrievedName = sessionAttributeNames.nextElement();
            retrievedObject = session.getAttribute(retrievedName);
            sessionAttribute.put(retrievedName, retrievedObject);
        }
    }

    public String getCommand() {
        return command;
    }

    public void addRequestAttribute(String key, Object value) {
        requestAttribute.put(key, value);
    }

    public void addSessionAttribute(String key, Object value) {
        removeSessionAttribute(key);
        sessionAttribute.put(key, value);
    }

    public Object getRequestAttribute(String key) {
        return requestAttribute.get(key);
    }

    public Object getSessionAttribute(String key) {
        return sessionAttribute.get(key);
    }

    public String getSingleRequestParameter(int position, String key) {
        return requestParameter.get(key)[position];
    }

    public String[] getRequestParameter(String key) {
        return requestParameter.get(key);
    }

    public void removeSessionAttribute(String ... attributeForDelete) {
        for (String attribute: attributeForDelete) {
            sessionAttributeForDelete.add(attribute);
            sessionAttribute.remove(attribute);
        }
    }

    public void update(HttpServletRequest request) throws CustomBrokerException {
        String key;
        Object value;
        for (Map.Entry<String, Object> attribute : requestAttribute.entrySet()) {
            key = attribute.getKey();
            value = attribute.getValue();
            request.setAttribute(key, value);
        }
        HttpSession session = request.getSession();
        for (String attributeForDelete: sessionAttributeForDelete) {
            session.removeAttribute(attributeForDelete);
        }
        for (Map.Entry<String, Object> attribute : sessionAttribute.entrySet()) {
            key = attribute.getKey();
            value = attribute.getValue();
//            if (value instanceof Serializable) {
                session.setAttribute(key, value);
//            } else {
//                throw new CustomBrokerException("Try to add non-serializable object to session!");
//            }
        }
    }
}
