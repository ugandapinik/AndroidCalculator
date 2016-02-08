package calculatorv2.jewelmahmud.com;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.EditText;


public class Calculatorv2Activity extends Activity implements OnClickListener{

	/*	VARIABLES AREA
	 * =============================*/
	private Double operand1 = 0d;
	private Double operand2 = 0d;
	private Double answer = 0d;
	
	private String operator = "";
	
	
	/*	DISPLAY AREA
	 * =============================*/
	private EditText screen;
	

	/*	BUTTONS	- NUMBERS
	 * =============================*/
	private Button one;
	private Button two;
	private Button three;
	private Button four;
	private Button five;
	private Button six;
	private Button seven;
	private Button eight;
	private Button nine;
	private Button zero;
	

	/*	BUTTONS	- OTHERS
	 * =============================*/
	private Button dot;
	private Button clear;
	private Button cancel;
	private Button delete;
	

	private Button plus;
	private Button minus;
	private Button division;
	private Button multiply;
	private Button equal;
	
	
	private boolean _clearScreen = true;
	
	private boolean _operatorState = false;
	private boolean _insertState = false;
	private boolean _lastClick = false;
					
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
        
        /*	SCREEN AREA
    	 * =============================*/
        screen = (EditText)findViewById(R.id.screen);
        
        
        /*	BUTTONS
    	 * =============================*/

        zero = (Button)findViewById(R.id.zero);
        zero.setOnClickListener(this);
        
        one = (Button)findViewById(R.id.one);
        one.setOnClickListener(this);
        
        two = (Button)findViewById(R.id.two);
        two.setOnClickListener(this);
        
        three = (Button)findViewById(R.id.three);
        three.setOnClickListener(this);
        
        four = (Button)findViewById(R.id.four);
        four.setOnClickListener(this);
        
        five = (Button)findViewById(R.id.five);
        five.setOnClickListener(this);
        
        six = (Button)findViewById(R.id.six);
        six.setOnClickListener(this);
        
        seven = (Button)findViewById(R.id.seven);
        seven.setOnClickListener(this);
        
        eight = (Button)findViewById(R.id.eight);
        eight.setOnClickListener(this);
        
        nine = (Button)findViewById(R.id.nine);
        nine.setOnClickListener(this);
        
        
        /*	OTHERS - BUTTON
    	 * =============================*/

        dot = (Button)findViewById(R.id.dot);
        dot.setOnClickListener(this);
        

        clear = (Button)findViewById(R.id.clear);
        clear.setOnClickListener(this);

        cancel = (Button)findViewById(R.id.cancel);
        cancel.setOnClickListener(this);

        delete = (Button)findViewById(R.id.delete);
        delete.setOnClickListener(this);
        
        

        /*	OPERAND - BUTTON
    	 * =============================*/
        plus = (Button)findViewById(R.id.plus);
        plus.setOnClickListener(this);
        
        minus = (Button)findViewById(R.id.minus);
        minus.setOnClickListener(this);
        
        division = (Button)findViewById(R.id.division);
        division.setOnClickListener(this);
        
        multiply = (Button)findViewById(R.id.multiply);
        multiply.setOnClickListener(this);
        
        equal = (Button)findViewById(R.id.equal);
        equal.setOnClickListener(this);
        
        
    }
    
    
    
    
    
    
    
    
    @Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		screen = (EditText)findViewById(R.id.screen);
        
		
	}








	/*	INSERT TEXT - DISPLAY
	 * =============================*/
    public void insertText(String text){
    	if (this._clearScreen) {
			screen.setText("");
			this._clearScreen = false;
		}
    	
    	this._insertState = true;
    	this._lastClick = true;
    	
    	screen.append(text);
    }
    
    
    
    
    /*	SETTING OPERATOR - BUTTON
	 * =============================*/
    public void setOperator(String operator){
    	
    	//if screen contains '.' (point)
    	if(screen.getText().toString().contains(".")){
    		screen.setText("0");
    	}
    	
    	//calculate
    	if(this._insertState && this._lastClick && this._operatorState ){
    		calculate();
    	}
    	
    	//OperandOne
    	if(screen.getText().length() > 0 ){
    		this.operand1 = Double.parseDouble(screen.getText().toString());
    		
    	}
    	
    	this._operatorState = true;
    	this._clearScreen = true;
    	this._lastClick = false;
    	
    	if(operator.equals("+"))		this.operator = "+";
    	else if(operator.equals("-"))	this.operator = "-";
    	else if(operator.equals("*"))	this.operator = "*";
    	else if(operator.equals("/"))	this.operator = "/";
    	
    }
    
    
    /*	CALCULATE
	 * =============================*/
    public void calculate(){
    	//if screen contains '.' (point)
    	if(screen.getText().toString().contains(".")){
    		screen.setText("0");
    	}
    	
    	//OperandOne
    	if(screen.getText().length() > 0 ){
    		this.operand2 = Double.parseDouble(screen.getText().toString());
    	}
    	
    	
    	
    	if(this.operator.equals("+")){
    		this.answer = this.operand1 + this.operand2;
    		
    	}else if(this.operator.equals("-")){
    		this.answer = this.operand1 - this.operand2;
    		
    	}else if(this.operator.equals("*")){
    		this.answer = this.operand1 * this.operand2;
    		
    	}else if(this.operator.equals("/")){
    		//divide by zero
    		if(this.operand2 == 0){
    			screen.setText("Can't divide by zero");
    			this.operand1 = 0d;
    			this.operand2 = 0d;
    			this.answer = 0d;
    			this.operator = "";
    			this._operatorState = false ;
    			this._insertState = false;
    			this._lastClick = false;
    			this._clearScreen = true;
    			screen.setText("0");
    		}
    		this.answer = this.operand1 / this.operand2;
    		
    	}else{
    		this.answer = Double.parseDouble(screen.getText().toString());
    	}
    	
    	
    	screen.setText(this.answer + "");
    	
    }
    
    

    /*	ONCLICK - BUTTON
	 * =============================*/
	public void onClick(View sender) {
		
		
		switch (sender.getId()) {
			
			case R.id.one: insertText("1"); break;
			case R.id.two: insertText("2"); break;
			case R.id.three: insertText("3"); break;
			case R.id.four: insertText("4"); break;
			case R.id.five: insertText("5"); break;
			case R.id.six: insertText("6"); break;
			case R.id.seven: insertText("7"); break;
			case R.id.eight: insertText("8"); break;
			case R.id.nine: insertText("9"); break;
			case R.id.zero: insertText("0"); break;
			
			case R.id.dot:
				if(!screen.getText().toString().contains(".") || this._operatorState){
					insertText(".");
				}
				break;
				
			case R.id.plus: setOperator("+");  break;
			case R.id.minus: setOperator("-");  break;
			case R.id.division: setOperator("/");  break;
			case R.id.multiply: setOperator("*");  break;
			case R.id.equal : 
				
				if (!this.operator.equals(null)) {
					if (this.operator.contains("+")) setOperator("+");
					else if(this.operator.contains("-")) setOperator("-");
					else if(this.operator.contains("*")) setOperator("*");
					else if(this.operator.contains("/")) setOperator("/");
				}
				
				break;
			
			/**
			 * CLEAR
			 */			
			case R.id.cancel :
				this.operand1 = 0d;
    			this.operand2 = 0d;
    			this.answer = 0d;
    			this.operator = "";
    			this._operatorState = false ;
    			this._insertState = false;
    			this._lastClick = false;
    			this._clearScreen = true;
    			screen.setText("0");
    			
    		/**
    		 * CANCEL
    		 */
			case R.id.clear : 
				screen.setText("0");
				this._clearScreen = true;
				break;
				
			case R.id.delete:
    			if(screen.getText().toString().length() > 1){
	    			 String screen_new = screen.getText().toString().substring(0, screen.getText().toString().length()-1);
	    			 screen.setText(screen_new);
	    			 this._clearScreen = false;
   			 	}else{
   			 		 screen.setText("0");
   			 		this._clearScreen = true;
   			 	}
	   			 break;
		
		}
		
		
		
		
	}//onClick
	
}













