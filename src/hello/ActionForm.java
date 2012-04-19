/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;

import com.exploringxml.xml.Node;
import com.exploringxml.xml.Xparse;
import java.io.IOException;
import javax.microedition.lcdui.*;

/**
 *
 * @author Syndarin
 */
public class ActionForm extends Form implements CommandListener {

    private final static String title = "Введите код";
    private MainScreen root;
    private Session session;
    private TextField sum;
    private TextField code;
    private StringItem statusLine;

    public ActionForm(MainScreen root, Session session) {
        super(title);
        this.root = root;
        this.session = session;

        sum = new TextField("Сумма:", "", 10, TextField.NUMERIC);
        code = new TextField("Акционный код:", "", 10, TextField.ANY);
        statusLine=new StringItem("", "");
        
        append(sum);
        append(code);
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
                sendCode();
                break;
        }
    }

    private void sendCode() {
        String sumString = sum.getString();
        String codeString = code.getString();

        String[] nodes = {"type", "id_kassi", "summa", "code", "sessions"};
        String[] values = {"bonususes", session.getIdKassi(), sumString, codeString, session.getSessionHash()};

        XMLHelper helper = new XMLHelper();
        String requestXml = helper.generateXML("request", nodes, values);

        try {
            String answer = sendRequest(requestXml);
            CodeResponse response = parseResponse(answer);
            
            if(response.getStatus()==0){
                statusLine.setText("Сумма с учетом скидки "+response.getPercent()+"% - "+response.getSum());
            }else{
                statusLine.setText(response.getText());
            }
            
        } catch (IOException ex) {
            ex.printStackTrace();
            statusLine.setText("Ошибка соединения с сервером!");
        }
    }

    private String sendRequest(String xml) throws IOException {

        HttpWorker http=new HttpWorker();
        return http.sendRequest(MainScreen.HOST, xml);
    }

    private CodeResponse parseResponse(String xml) {

        CodeResponse response = new CodeResponse();

        Xparse parser = new Xparse();
        Node document = parser.parse(xml);

        Node rootNode = document.find("response", new int[]{1});

        Node statusNode = rootNode.find("status", new int[]{1});
        String statusString = statusNode.getCharacters();
        int status = Integer.parseInt(statusString);
        response.setStatus(status);

        Node textStatusNode = rootNode.find("text_status", new int[]{1});
        String textStatus = textStatusNode.getCharacters();
        response.setText(textStatus);

        if (status == 0) {

            Node sumNewNode = rootNode.find("summ_new", new int[]{1});
            String newSumm = sumNewNode.getCharacters();
            response.setSum(newSumm);

            Node percentNode = rootNode.find("percent", new int[]{1});
            String percentString = percentNode.getCharacters();
            response.setPercent(percentString);

        }

        return response;
    }
}
