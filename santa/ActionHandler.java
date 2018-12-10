
import java.awt.event.*;
import java.util.LinkedList;
import java.util.List;


public class ActionHandler implements ActionListener{

    final List<Integer> holderInt = new LinkedList<Integer>();
    final List<String> holderString = new LinkedList<String>();

    private SantaManager runner;

    String text;

    public ActionHandler(SantaManager r){
        runner = r;
    }

    public void reset()
    {
        holderString.clear();
        holderInt.clear();
    }
    
    public void actionPerformed(ActionEvent e) {
        text = runner.input.getText();
        if (runner.input.getText().matches("[0-9]+"))
        {
            synchronized (holderInt){
                holderInt.add(Integer.parseInt(runner.input.getText()));
                holderInt.notify();
            }
        } else
        {
            synchronized (holderString){
                holderString.clear();

                holderString.add(runner.input.getText());
                holderString.notify();
                
            }
        }
        runner.input.setText("");

    }

}
