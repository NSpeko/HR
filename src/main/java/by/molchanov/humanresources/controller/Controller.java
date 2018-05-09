package by.molchanov.humanresources.controller;

import by.molchanov.humanresources.command.ConcreteCommand;
import by.molchanov.humanresources.command.ResponseType;
import by.molchanov.humanresources.database.ConnectionPool;
import by.molchanov.humanresources.exception.CustomBrokerException;
import by.molchanov.humanresources.factory.OperationFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

import static by.molchanov.humanresources.constant.SessionRequestAttributeNames.COMMAND;

@WebServlet("/controller")
public class Controller extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger();

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("page_location");
    private static final String MAIN_PAGE = RESOURCE_BUNDLE.getString("page.location.main");
    private static final String ERROR_PAGE = RESOURCE_BUNDLE.getString("page.location.error");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        handleRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        handleRequest(request, response);
    }

    @Override
    public void destroy() {
        ConnectionPool.getInstance().closePool();
    }

    private void handleRequest(HttpServletRequest request, HttpServletResponse response) {
        OperationFactory operationFactory = OperationFactory.getInstance();
        RequestHolder requestHolder = new RequestHolder(request);
        String requestCommand = requestHolder.getCommand();
        ConcreteCommand command = operationFactory.getConcreteCommand(requestCommand);
        ResponseType responseType = operationFactory.getResponseType(requestCommand);
        try {
            command.execute(requestHolder);
            requestHolder.update(request);
        } catch (CustomBrokerException e) {
            LOGGER.warn(e.getMessage(), e);
            responseType = ResponseType.REDIRECT;
        }
        try {
            switch (responseType) {
                case FORWARD:
                    getServletContext().getRequestDispatcher(MAIN_PAGE).forward(request, response);
                    break;
                case REDIRECT:
                    response.sendRedirect(request.getContextPath() + ERROR_PAGE);
                    break;
                default:
                    response.sendRedirect(request.getContextPath() + ERROR_PAGE);
            }
        } catch (ServletException | IOException e) {
            LOGGER.fatal("Fatal servlet error!");
        }
    }
}
