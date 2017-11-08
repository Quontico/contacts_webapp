package controller;

import org.apache.log4j.Logger;
import org.quartz.SchedulerException;
import util.QuartzSchedule;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "FrontController", urlPatterns = {"/FrontController"})
public class FrontController extends HttpServlet {

    private static final Logger LOGGER = Logger.getRootLogger();
    private static final long serialVersionUID = 1L;

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) {

        ActionFactory client = new ActionFactory();
        ActionCommand command = client.defineCommand(req);
        String page = null;
        try {
            page = command.execute(req, resp);
        } catch (Exception e) {
            LOGGER.error("Error in the request processing, FrontController: ", e);
        }
        try {
            if (page != null) {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
                dispatcher.forward(req, resp);
            } else {
                resp.sendRedirect(PageURL.ERROR_PAGE);
            }
        } catch (Exception e) {
            LOGGER.error("There is some exception in FrontController: " + e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        processRequest(request, response);
    }
}
