package by.molchanov.humanresources.controller;

import by.molchanov.humanresources.command.ConcreteCommand;
import by.molchanov.humanresources.command.ResponseType;
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

import static by.molchanov.humanresources.constant.SessionRequestAttributeNames.COMMAND;

@WebServlet("/controller")
public class Controller extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger();

    private static final String PAGE_MAIN_ADDRESS = "/page/main.jsp";
    private static final String PAGE_ERROR_ADDRESS = "/page/error.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        handleRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        handleRequest(request, response);
    }

    private void handleRequest(HttpServletRequest request, HttpServletResponse response) {
        OperationFactory operationFactory = OperationFactory.getInstance();
        RequestHolder requestHolder = new RequestHolder(request);
        String requestCommand = request.getParameter(COMMAND);
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
                    getServletContext().getRequestDispatcher(PAGE_MAIN_ADDRESS).forward(request, response);
                    break;
                case REDIRECT:
                    response.sendRedirect(request.getContextPath() + PAGE_ERROR_ADDRESS);
                    break;
                default:
                    response.sendRedirect(request.getContextPath() + PAGE_ERROR_ADDRESS);
            }
        } catch (ServletException | IOException e) {
            LOGGER.fatal("Fatal servlet error!");
        }
    }
}
