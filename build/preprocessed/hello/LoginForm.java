/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;

import com.exploringxml.xml.Node;
import com.exploringxml.xml.Xparse;
import java.io.*;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.*;

/**
 *
 * @author Syndarin
 */
public class LoginForm extends Form implements CommandListener {

    private final static String title = "Авторизуйтесь, пожалуйста";
    private MainScreen root;
    private TextField login;
    private TextField password;
    private StringItem statusLine;

    public LoginForm(MainScreen root) {
        super(title);
        this.root = root;

        login = new TextField("Login", "", 12, TextField.ANY);
        password = new TextField("Password", "", 12, TextField.ANY);
        statusLine = new StringItem("", "");

        append(login);
        append(password);
        append(statusLine);

        setCommandListener(this);

        addCommand(new Command("Enter", Command.OK, 1));

        addCommand(new Command("Exit", Command.EXIT, 1));
    }

    public void commandAction(Command c, Displayable d) {
        int commandType = c.getCommandType();

        switch (commandType) {
            case Command.EXIT:
                root.destroyApp(true);
                break;
            case Command.OK:
                initAuth();
                break;
        }
    }

    private void initAuth() {
        String loginString = login.getString();
        String passwordString = password.getString();

        String[] nodes = {"type", "user", "pass"};
        String[] values = {"bonuslogin", loginString, passwordString};

        XMLHelper helper = new XMLHelper();
        String xml = helper.generateXML("request", nodes, values);

        try {
            String response = sendPostRequest(xml);
            Session session = parseServerResponse(response);

            if (session.getStatus() != 1) {
                statusLine.setText("Вы успешно авторизованы!");
                root.setActionPage(session);
            } else {
                statusLine.setText("Неправильные учетные данные!");
            }

        } catch (IOException ex) {
            ex.printStackTrace();
            statusLine.setText("Ошибка связи с сервером!");
        }
    }

    private Session parseServerResponse(String rawXML) {
        Session session = new Session();
        Xparse parser = new Xparse();

        Node rootElement = parser.parse(rawXML);

        Node rootNode = rootElement.find("response", new int[]{1});

        Node statusNode = rootNode.find("status", new int[]{1});
        String statusString = statusNode.getCharacters();
        int status = Integer.parseInt(statusString);
        session.setStatus(status);

        if (status == 0) {

            Node idKassiNode = rootNode.find("id_kassi", new int[]{1});
            String idKassi = idKassiNode.getCharacters();
            session.setIdKassi(idKassi);

            Node sessionIdNode = rootNode.find("sessions", new int[]{1});
            String sessionId = sessionIdNode.getCharacters();
            session.setSessionHash(sessionId);

            Node nameNode = rootNode.find("name", new int[]{1});
            String name = nameNode.getCharacters();
            session.setName(name);

        }

        return session;
    }

    private String sendPostRequest(String xml) throws IOException {

        HttpWorker http=new HttpWorker();
        return http.sendRequest(MainScreen.HOST, xml);
    }
}
