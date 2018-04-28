package by.molchanov.humanresources.controller;

import by.molchanov.humanresources.command.ConcreteCommand;
import by.molchanov.humanresources.command.ResponseType;
import by.molchanov.humanresources.exception.CustomException;
import by.molchanov.humanresources.factory.OperationFactory;
import by.molchanov.humanresources.resource.PageLocationManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.molchanov.humanresources.command.ResponseType.FORWARD;
import static by.molchanov.humanresources.command.ResponseType.REDIRECT;

@WebServlet("/controller")
public class Controller extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request, response);
    }

    private void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OperationFactory operationFactory = new OperationFactory();
        RequestHolder requestHolder = new RequestHolder(request);
        String requestCommand = request.getParameter("command");
        ConcreteCommand command = operationFactory.getConcreteCommand(requestCommand);
        ResponseType responseType = operationFactory.getResponseType(requestCommand);
        command.execute(requestHolder);
        String page = "/page/main.jsp";
        try {
            requestHolder.update(request);
        } catch (CustomException e) {
            //add handler
        }
        switch (responseType) {
            case FORWARD:
                getServletContext().getRequestDispatcher(page).forward(request, response);
                break;
            case REDIRECT:
                response.sendRedirect(request.getContextPath() + page);
                break;
            default:
                page = PageLocationManager.getInstance().getProperty("page.location.index");
                response.sendRedirect(request.getContextPath() + page);
        }
    }
}
