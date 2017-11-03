package controller;

import controller.commands.*;

public enum CommandEnum {

    ADD_OR_EDIT_CONTACT {{
        this.command = new AddOrEditContact();
    }},
    CONTACT_FORM {{
        this.command = new ContactForm();
    }},
    DELETE_CONTACT {{
        this.command = new DeleteContacts();
    }},
    LIST_OF_CONTACTS {{
        this.command = new ListOfContacts();
    }},
    SEARCH_CONTACTS {{
        this.command = new SearchContact();
    }},
    SEARCH_FORM {{
        this.command = new SearchForm();
    }},
    MAIL_FORM {{
        this.command = new MailForm();
    }},
    SEND_EMAILS {{
        this.command = new SendEmails();
    }},
    GET_AVATAR {{
        this.command = new GetAvatar();
    }},
    GET_FILE {{
        this.command = new GetFileCommand();
    }};

    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }
}
