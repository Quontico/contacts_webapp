package controller;

public interface PageURL {
    String ERROR_PAGE = "/jsp/error.jsp";
    String ADD_OR_EDIT = "/jsp/Contact.jsp";
    String LIST_CONTACT = "/jsp/listContact.jsp";
    String SEARCH = "/jsp/Search.jsp";
    String SEND_EMAIL = "/jsp/Email.jsp";
    String LIST_CONTACT_COMMAND = "/FrontController?action=LIST_OF_CONTACTS";
    String SEARCH_COMMAND = "/FrontController?action=SEARCH_CONTACTS";
}
