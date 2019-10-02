/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadoraracionales;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author Hot Cakes
 */
public class FXMLDocumentController implements Initializable {
    @FXML
    private Button btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btnDen,btnDiv,btnMult,btnRes,btnSum,btnCLR,btnPOW,btnSqrt,btnEq;
    
    @FXML
    private TextField displayLabel;
    
    //AAAAAAAAAAAAAAAAAAAAA
    boolean clearOnNextDigit;
    final int MAX_INPUT_LENGTH = 20;
    final int INPUT_MODE = 0;
    final int RESULT_MODE = 1;

    int displayMode;
    String lastOperator;
    //Racional lastNumber;
    Racional lastNumber;
    
   // btn0.setOnAction(event->check((Button) event.getSource()));
    
    
    @FXML
    private void ClickBoton(ActionEvent e) {
        System.out.println(check((Button) e.getSource()) + " presionado");
        switch (check((Button) e.getSource())){
            case "btn0":
                System.out.println("0 presionado");
                addDigitToDisplay(0);
                break;
            case "btn1":
                System.out.println("1 presionado");
                addDigitToDisplay(1);
                break;
            case "btn2":
                System.out.println("2 presionado");
                addDigitToDisplay(2);
                break;
            case "btn3":
                System.out.println("3 presionado");
                addDigitToDisplay(3);
                break;
            case "btn4":
                System.out.println("4 presionado");
                addDigitToDisplay(4);
                break;
            case "btn5":
                System.out.println("5 presionado");
                addDigitToDisplay(5);
                break;
            case "btn6":
                System.out.println("6 presionado");
                addDigitToDisplay(6);
                break;
            case "btn7":
                System.out.println("7 presionado");
                addDigitToDisplay(7);
                break;
            case "btn8":
                System.out.println("8 presionado");
                addDigitToDisplay(8);
                break;
            case "btn9":
                System.out.println("9 presionado");
                addDigitToDisplay(9);
                break;
            case "btnDen":
                System.out.println("Den presionado");
                setDisplayString(getDisplayString()+ "/");
                break;
            case "btnCLR":
                System.out.println("CLR presionado");
                clearAll();
                break;
            case "btnDiv":
                System.out.println("Div presionado");
                processOperator("/");
		break;
            case "btnMult":
                System.out.println("Mult presionado");
                processOperator("*");
		break;
            case "btnRes":
                System.out.println("Res presionado");
                processOperator("-");
		break;
            case "btnSum":
                System.out.println("Sum presionado");
                processOperator("+");
		break;
            case "btnPOW":
                System.out.println("POW presionado");
                processOperator("^");
		break;
            case "btnSqrt":
                System.out.println("Sqrt presionado");
                processOperator("sqrt");
		break;
            case "btnEq":
                System.out.println("Eq presionado");
                processEquals();
		break;
                
            
        }


        
    }
       
    private String check(Button button){
        return button.getId();
    }
    
    private Racional getRac(){
        String inputString = getDisplayString();
        String[] str = inputString.split("/", 2);
        
        return new Racional(Integer.parseInt(str[0]),Integer.parseInt(str[1]));
    }
    
    void setDisplayString(String s){
        displayLabel.setText(s);
    }

    String getDisplayString (){
	return displayLabel.getText();
    }
        
    void addDigitToDisplay(int digit){
		if (clearOnNextDigit)
			setDisplayString("");

		String inputString = getDisplayString();
		
		if (inputString.indexOf("0") == 0){
			inputString = inputString.substring(1);
		}

		if ((!inputString.equals("0") || digit > 0)  && inputString.length() < MAX_INPUT_LENGTH){
			setDisplayString(inputString + digit);
		}
		

		//displayMode = INPUT_MODE;
		clearOnNextDigit = false;
	}

    void clearAll()	{
        setDisplayString("0");
        lastOperator = "0";
        lastNumber = new Racional(0,1);
        displayMode = INPUT_MODE;
        clearOnNextDigit = true;
    }
    
 
    
    Racional processLastOperator(){
        Racional result = new Racional(0,1);
        Racional numberInDisplay = getRac();
        System.out.println(numberInDisplay);
        System.out.println("operando con "+ numberInDisplay+"-------------------------");
        System.out.println("Ult:: "+ lastOperator);
        if (lastOperator.equals("/"))		
            result = lastNumber.dividir(numberInDisplay);			
        if (lastOperator.equals("*"))
            result = lastNumber.multiplicar(numberInDisplay);
        if (lastOperator.equals("-"))
            result = lastNumber.restar(numberInDisplay);
        if (lastOperator.equals("+"))
            result = lastNumber.sumar(numberInDisplay);
        if (lastOperator.equals("sqrt"))
            result = lastNumber.cuadrado();
//                if(lastOperator.equals("^"))
//                                  result = Math.pow(lastNumber, numberInDisplay);
//                if(lastOperator.equals("mod"))
//                                  result = lastNumber % numberInDisplay;

        return result;
    }
    
    void processOperator(String op) {
        System.out.println(op);
        Racional numberInDisplay = getRac();
        System.out.println(numberInDisplay);
        if (!lastOperator.equals("0")){
                try{
                        Racional result = processLastOperator();
                        displayResult(result);
                        lastNumber = result;
                }
                catch (Exception e){
                    System.out.println("error");
                }
        }

        else{
                lastNumber = numberInDisplay;
        }

        clearOnNextDigit = true;
        lastOperator = op;
        
    }
    
    void processEquals(){
        Racional result = new Racional(0,1);
                try			
                {
                    result = processLastOperator();
                    displayResult(result);
                }

                catch (Exception e)	{
                        System.out.println("Nope!");
                }

                lastOperator = "0";

    }
    
    void displayResult(Racional result){
        setDisplayString(result.toString());
        lastNumber = result;
        displayMode = RESULT_MODE;
        clearOnNextDigit = true;
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clearAll();
    }
}
