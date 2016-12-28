package chess.view;

import java.awt.Button;
import java.awt.TextArea;
import java.awt.event.ActionEvent;

import com.sun.glass.ui.Accessible.EventHandler;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
public class mapController {

    @FXML
    private Label nameLabel;

    @FXML
    private TextField message;

    @FXML
    private TextField input;

    @FXML
    private Label a8;
    @FXML
    private Label a7;



    @FXML
    private Label a6;
    @FXML
    private Label a5;
    @FXML
    private Label a4;
    @FXML
    private Label a3;
    @FXML
    private Label a2;
    @FXML
    private Label a1;

    @FXML
    private Label b8;
    @FXML
    private Label b7;
    @FXML
    private Label b6;
    @FXML
    private Label b5;
    @FXML
    private Label b4;
    @FXML
    private Label b3;
    @FXML
    private Label b2;
    @FXML
    private Label b1;

    @FXML
    private Label c8;
    @FXML
    private Label c7;
    @FXML
    private Label c6;
    @FXML
    private Label c5;
    @FXML
    private Label c4;
    @FXML
    private Label c3;
    @FXML
    private Label c2;
    @FXML
    private Label c1;

    @FXML
    private Label d8;
    @FXML
    private Label d7;
    @FXML
    private Label d6;
    @FXML
    private Label d5;
    @FXML
    private Label d4;
    @FXML
    private Label d3;
    @FXML
    private Label d2;
    @FXML
    private Label d1;

    @FXML
    private Label e8;
    @FXML
    private Label e7;
    @FXML
    private Label e6;
    @FXML
    private Label e5;
    @FXML
    private Label e4;
    @FXML
    private Label e3;
    @FXML
    private Label e2;
    @FXML
    private Label e1;

    @FXML
    private Label f8;
    @FXML
    private Label f7;
    @FXML
    private Label f6;
    @FXML
    private Label f5;
    @FXML
    private Label f4;
    @FXML
    private Label f3;
    @FXML
    private Label f2;
    @FXML
    private Label f1;

    @FXML
    private Label g8;
    @FXML
    private Label g7;
    @FXML
    private Label g6;
    @FXML
    private Label g5;
    @FXML
    private Label g4;
    @FXML
    private Label g3;
    @FXML
    private Label g2;
    @FXML
    private Label g1;

    @FXML
    private Label h8;
    @FXML
    private Label h7;
    @FXML
    private Label h6;
    @FXML
    private Label h5;
    @FXML
    private Label h4;
    @FXML
    private Label h3;
    @FXML
    private Label h2;
    @FXML
    private Label h1;

    public static boolean whitesTurn = true;
    public static boolean Try_draw = false;
    public static boolean draw = false;
    private String temp;
    private String source;
    private String dest;

    // Reference to the main application.
    private Chess chess;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public mapController() {
    }



    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {

    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setChess(Chess chess) {
        this.chess = chess;

    }

    /**
     * Shows the person overview inside the root layout.
     */
    public void showChessMap() {

    }

    public boolean checkBlackSpot(String str){
    	char c1,c2;
    	c1 = str.charAt(0);
    	c2 = str.charAt(1);
    	if(c1 == 'a' || c1 == 'c' || c1 == 'e' || c1 == 'g'){
    		if(Character.getNumericValue(c2)%2==0){
    			return false;
    		}
    		else{
    			return true;
    		}
    	}
    	else{
    		if(Character.getNumericValue(c2)%2==0){
    			return true;
    		}
    		else{
    			return false;
    		}
    	}
    }

    public boolean moveValid(String mess, Label[] labels){
    	char c = ' ';
    	char t = ' ';
    	char j = ' ';
    	char k = ' ';
    	int start = 0;
    	int end = 0;
    	String[] commands = mess.split("\\s+");
    	if(commands.length == 1){
    		if(commands[0].equals("draw") && Try_draw){
    			message.setText("Draw");
    			draw = true;
    		}
    		return false;
    	}
    	System.out.println("command[0] = "+commands[0]+" command[1] = "+commands[1]);
    	for(int i=0; i<64; i++){
    		if(labels[i].getId().equals(commands[0])){
    			if(commands[1].isEmpty()){
    				return false;
    			}
    			source = labels[i].getText();
    			System.out.println("source is "+source);
    			if(source.isEmpty() || source.equals("##")){
    				return false;
    			}
    			c = source.charAt(0);
    			System.out.println("c = "+c);
    			if(whitesTurn && c=='b'){
    				return false;
    			}
    			else if(!whitesTurn && c=='w'){
    				return false;
    			}
    			t = source.charAt(1);
    			System.out.println("t = "+t);
    			start = i;
    			break;
    		}
    	}
    	for(int i=0; i<64; i++){
    		if(labels[i].getId().equals(commands[1])){
    			if(t =='R'){
    				if(commands[1].charAt(0)!=commands[0].charAt(0)){
    					if(commands[1].charAt(1)!=commands[0].charAt(1)){
    						return false;
    					}
    					else{
    						if(commands[1].charAt(0)-commands[0].charAt(0)>1){
    							j=(char)(commands[0].charAt(0)+1);
    							k = commands[1].charAt(0);
    						}
    						else{
    							j=(char)(commands[1].charAt(0)+1);
    							k = commands[0].charAt(0);
    						}
    						while(j<k){
    							char[] x = new char[2];
    							x[0] = j;
    							x[1] = commands[1].charAt(1);
    							String route = String.valueOf(x);
    							for(int p=0; p<64; p++){
    								if(labels[p].getId().equals(route)){
    									if(!labels[p].getText().equals("") && !labels[p].getText().equals("##")){
    										return false;
    									}
    								}
    							}
    							j++;
    						}
    					}
    				}
    				else{
    					if(commands[1].charAt(1)-commands[0].charAt(1)>1){
    						j = (char)(commands[0].charAt(1)+1);
    						k = commands[1].charAt(1);
    					}
    					else{
    						j=(char)(commands[1].charAt(1)+1);
    						k = commands[0].charAt(1);
    					}
						while(j<k){
							char[] x = new char[2];
							x[0] = commands[1].charAt(0);
							x[1] = j;
							String route = String.valueOf(x);
							for(int p=0; p<64; p++){
								if(labels[p].getId().equals(route)){
									if(!labels[p].getText().equals("") && !labels[p].getText().equals("##")){
										return false;
									}
								}
							}
							j++;
						}
					}

    			}

    			else if(t == 'N'){
    				System.out.println(("vertical change = ")+Math.abs(commands[1].charAt(0)-commands[0].charAt(0)));
    				System.out.println(("hetical change = ")+Math.abs(commands[1].charAt(1)-commands[0].charAt(1)));
    				if(Math.abs(commands[1].charAt(0)-commands[0].charAt(0)) == 1){
    					if(Math.abs(commands[1].charAt(1)-commands[0].charAt(1)) != 2){
        					return false;
        				}
    				}
    				else if(Math.abs(commands[1].charAt(0)-commands[0].charAt(0)) == 2){
    					if(Math.abs(commands[1].charAt(1)-commands[0].charAt(1)) != 1){
        					return false;
        				}
    				}
    				else{
    					return false;
    				}
    			}
    			else if(t == 'B'){
    				if(Math.abs(commands[1].charAt(0)-commands[0].charAt(0)) != Math.abs(commands[1].charAt(1)-commands[0].charAt(1))){
    					return false;
    				}

    				if(commands[1].charAt(0)>commands[0].charAt(0)){
    					if(commands[1].charAt(1)>commands[0].charAt(1)){
    						k = (char)(commands[0].charAt(1)+1);
    						for(j = (char)(commands[0].charAt(0)+1); j<commands[1].charAt(0); j++){
        						char[] x = new char[2];
    							x[0] = j;
    							x[1] = k;
    							String route = String.valueOf(x);
    							for(int p=0; p<64; p++){
    								if(labels[p].getId().equals(route)){
    									if(!labels[p].getText().equals("") && !labels[p].getText().equals("##")){
    										return false;
    									}
    								}
    							}
    							k++;
        					}
    					}else{
    						k = (char)(commands[1].charAt(1)+1);
    						for(j = (char)(commands[0].charAt(0)-1); j>commands[1].charAt(0); j--){
        						char[] x = new char[2];
    							x[0] = j;
    							x[1] = k;
    							String route = String.valueOf(x);
    							for(int p=0; p<64; p++){
    								if(labels[p].getId().equals(route)){
    									if(!labels[p].getText().equals("") && !labels[p].getText().equals("##")){
    										return false;
    									}
    								}
    							}
    							k++;
        					}
    					}
    				}
    				else{
    					if(commands[1].charAt(1)>commands[0].charAt(1)){
    						k = (char)(commands[0].charAt(1)+1);
    						for(j = (char)(commands[0].charAt(0)-1); j>commands[1].charAt(0); j--){
        						char[] x = new char[2];
    							x[0] = j;
    							x[1] = k;
    							String route = String.valueOf(x);
    							for(int p=0; p<64; p++){
    								if(labels[p].getId().equals(route)){
    									if(!labels[p].getText().equals("") && !labels[p].getText().equals("##")){
    										return false;
    									}
    								}
    							}
    							k++;
        					}
    					}else{
    						k = (char)(commands[1].charAt(1)+1);
    						for(j = (char)(commands[0].charAt(0)+1); j<commands[1].charAt(0); j++){
        						char[] x = new char[2];
    							x[0] = j;
    							x[1] = k;
    							String route = String.valueOf(x);
    							for(int p=0; p<64; p++){
    								if(labels[p].getId().equals(route)){
    									if(!labels[p].getText().equals("") && !labels[p].getText().equals("##")){
    										return false;
    									}
    								}
    							}
    							k++;
        					}
    					}

    				}
    			}
    			else if(t == 'Q'){
    				if(commands[1].charAt(0)!=commands[0].charAt(0)){
    					if(commands[1].charAt(1)!=commands[0].charAt(1)){
    						if(Math.abs(commands[1].charAt(0)-commands[0].charAt(0)) != Math.abs(commands[1].charAt(1)-commands[0].charAt(1))){
    	    					return false;
    	    				}
    						else{
    							if(commands[1].charAt(0)>commands[0].charAt(0)){
    		    					if(commands[1].charAt(1)>commands[0].charAt(1)){
    		    						k = (char)(commands[0].charAt(1)+1);
    		    						for(j = (char)(commands[0].charAt(0)+1); j<commands[1].charAt(0); j++){
    		        						char[] x = new char[2];
    		    							x[0] = j;
    		    							x[1] = k;
    		    							String route = String.valueOf(x);
    		    							for(int p=0; p<64; p++){
    		    								if(labels[p].getId().equals(route)){
    		    									if(!labels[p].getText().equals("") && !labels[p].getText().equals("##")){
    		    										return false;
    		    									}
    		    								}
    		    							}
    		    							k++;
    		        					}
    		    					}else{
    		    						k = (char)(commands[1].charAt(1)+1);
    		    						for(j = (char)(commands[0].charAt(0)-1); j>commands[1].charAt(0); j--){
    		        						char[] x = new char[2];
    		    							x[0] = j;
    		    							x[1] = k;
    		    							String route = String.valueOf(x);
    		    							for(int p=0; p<64; p++){
    		    								if(labels[p].getId().equals(route)){
    		    									if(!labels[p].getText().equals("") && !labels[p].getText().equals("##")){
    		    										return false;
    		    									}
    		    								}
    		    							}
    		    							k++;
    		        					}
    		    					}
    		    				}
    		    				else{
    		    					if(commands[1].charAt(1)>commands[0].charAt(1)){
    		    						k = (char)(commands[0].charAt(1)+1);
    		    						for(j = (char)(commands[0].charAt(0)-1); j>commands[1].charAt(0); j--){
    		        						char[] x = new char[2];
    		    							x[0] = j;
    		    							x[1] = k;
    		    							String route = String.valueOf(x);
    		    							for(int p=0; p<64; p++){
    		    								if(labels[p].getId().equals(route)){
    		    									if(!labels[p].getText().equals("") && !labels[p].getText().equals("##")){
    		    										return false;
    		    									}
    		    								}
    		    							}
    		    							k++;
    		        					}
    		    					}else{
    		    						k = (char)(commands[1].charAt(1)+1);
    		    						for(j = (char)(commands[0].charAt(0)+1); j<commands[1].charAt(0); j++){
    		        						char[] x = new char[2];
    		    							x[0] = j;
    		    							x[1] = k;
    		    							String route = String.valueOf(x);
    		    							for(int p=0; p<64; p++){
    		    								if(labels[p].getId().equals(route)){
    		    									if(!labels[p].getText().equals("") && !labels[p].getText().equals("##")){
    		    										return false;
    		    									}
    		    								}
    		    							}
    		    							k++;
    		        					}
    		    					}

    		    				}
    						}
    					}
    					else{
    						if(commands[1].charAt(0)-commands[0].charAt(0)>1){
    							j=(char)(commands[0].charAt(0)+1);
    							k = commands[1].charAt(0);
    						}
    						else{
    							j=(char)(commands[1].charAt(0)+1);
    							k = commands[0].charAt(0);
    						}
    						while(j<k){
    							char[] x = new char[2];
    							x[0] = j;
    							x[1] = commands[1].charAt(1);
    							String route = String.valueOf(x);
    							for(int p=0; p<64; p++){
    								if(labels[p].getId().equals(route)){
    									if(!labels[p].getText().equals("") && !labels[p].getText().equals("##")){
    										return false;
    									}
    								}
    							}
    							j++;
    						}
    					}
    				}
    				else{
    					if(commands[1].charAt(1)-commands[0].charAt(1)>1){
    						j = (char)(commands[0].charAt(1)+1);
    						k = commands[1].charAt(1);
    					}
    					else{
    						j=(char)(commands[1].charAt(1)+1);
    						k = commands[0].charAt(1);
    					}
						while(j<k){
							char[] x = new char[2];
							x[0] = commands[1].charAt(0);
							x[1] = j;
							String route = String.valueOf(x);
							for(int p=0; p<64; p++){
								if(labels[p].getId().equals(route)){
									if(!labels[p].getText().equals("") && !labels[p].getText().equals("##")){
										return false;
									}
								}
							}
							j++;
						}
    				}
    			}
    			else if(t == 'K'){
    				if(Math.abs(commands[1].charAt(1)-commands[0].charAt(1))>1){
    					return false;
    				}
    				if(Math.abs(commands[1].charAt(0)-commands[0].charAt(0))>2){
    					return false;
    				}
    				if(Math.abs(commands[1].charAt(0)-commands[0].charAt(0)) == 2){
    					if(whitesTurn){
    						if(!commands[0].equals("e1")){
    							return false;
    						}
    						if(commands[1].charAt(0)>commands[0].charAt(0)){
        						//move to right
        						if(!f1.getText().equals("") || !g1.getText().equals("##") || !h1.getText().equals("wR")){
        							return false;
        						}
        						//check battle zone
        						if(e3.getText().equals("bN") || g3.getText().equals("bN") || d2.getText().equals("bN") || h2.getText().equals("bN")
        								|| e2.getText().equals("bN") || f3.getText().equals("bN") || h3.getText().equals("bN")){
        							return false;
        						}
        						if(e2.getText().equals("bp") || f2.getText().equals("bp") || g2.getText().equals("bp") || h2.getText().equals("bp")){
        							return false;
        						}

        						for(j = '2'; j<'9'; j++){
        							char[] x = new char[2];
        							x[0] = 'f';
        							x[1] = j;
        							String route = String.valueOf(x);
        							for(int p=0; p<64; p++){
        								if(labels[p].getId().equals(route)){
        									if(!labels[p].getText().equals("") && !labels[p].getText().equals("##")){
        										if(labels[p].getText().equals("bR") || labels[p].getText().equals("bQ")){
        											return false;
        										}
        										else{
        											j = '9';
        											break;
        										}
        									}
        								}
        							}
        						}
        						for(j = '2'; j<'7'; j++){
        							char[] x = new char[2];
        							x[0] = 'e';
        							x[1] = j;
        							String route = String.valueOf(x);
        							for(int p=0; p<64; p++){
        								if(labels[p].getId().equals(route)){
        									if(!labels[p].getText().equals("") && !labels[p].getText().equals("##")){
        										if(labels[p].getText().equals("bB") || labels[p].getText().equals("bQ")){
        											return false;
        										}
        										else{
        											j = '9';
        											break;
        										}
        									}
        								}
        							}
        							x[0]--;
        						}
        						for(j = '2'; j<'4'; j++){
        							char[] x = new char[2];
        							x[0] = 'g';
        							x[1] = j;
        							String route = String.valueOf(x);
        							for(int p=0; p<64; p++){
        								if(labels[p].getId().equals(route)){
        									if(!labels[p].getText().equals("") && !labels[p].getText().equals("##")){
        										if(labels[p].getText().equals("bB") || labels[p].getText().equals("bQ")){
        											return false;
        										}
        										else{
        											j = '9';
        											break;
        										}
        									}
        								}
        							}
        							x[0]++;
        						}
        						for(j = '2'; j<'9'; j++){
        							char[] x = new char[2];
        							x[0] = 'g';
        							x[1] = j;
        							String route = String.valueOf(x);
        							for(int p=0; p<64; p++){
        								if(labels[p].getId().equals(route)){
        									if(!labels[p].getText().equals("") && !labels[p].getText().equals("##")){
        										if(labels[p].getText().equals("bR") || labels[p].getText().equals("bQ")){
        											return false;
        										}
        										else{
        											j = '9';
        											break;
        										}
        									}
        								}
        							}
        						}
        						for(j = '2'; j<'8'; j++){
        							char[] x = new char[2];
        							x[0] = 'f';
        							x[1] = j;
        							String route = String.valueOf(x);
        							for(int p=0; p<64; p++){
        								if(labels[p].getId().equals(route)){
        									if(!labels[p].getText().equals("") && !labels[p].getText().equals("##")){
        										if(labels[p].getText().equals("bB") || labels[p].getText().equals("bQ")){
        											return false;
        										}
        										else{
        											j = '9';
        											break;
        										}
        									}
        								}
        							}
        							x[0]--;
        						}
        						for(j = '2'; j<'3'; j++){
        							char[] x = new char[2];
        							x[0] = 'h';
        							x[1] = j;
        							String route = String.valueOf(x);
        							for(int p=0; p<64; p++){
        								if(labels[p].getId().equals(route)){
        									if(!labels[p].getText().equals("") && !labels[p].getText().equals("##")){
        										if(labels[p].getText().equals("bB") || labels[p].getText().equals("bQ")){
        											return false;
        										}
        										else{
        											j = '9';
        											break;
        										}
        									}
        								}
        							}
        							x[0]++;
        						}
        						//change the position
        						e1.setText("");
        						g1.setText("wK");
        						h1.setText("");
        						f1.setText("wR");
        						return true;
        					}
    						else{
    							//move to left
    							if(!d1.getText().equals("") || !c1.getText().equals("##") || !b1.getText().equals("") || !a1.getText().equals("wR")){
        							return false;
        						}
    							//check battle zone
        						if(a3.getText().equals("bN") || b3.getText().equals("bN") || c3.getText().equals("bN") || d3.getText().equals("bN")
        								|| e3.getText().equals("bN") || a2.getText().equals("bN") || b2.getText().equals("bN") || d2.getText().equals("bN") || e2.getText().equals("bN") || f2.getText().equals("bN")){
        							return false;
        						}
        						if(a2.getText().equals("bp") || b2.getText().equals("bp") || c2.getText().equals("bp") || d2.getText().equals("bp") || e2.getText().equals("bp")){
        							return false;
        						}

        						for(j = '2'; j<'9'; j++){
        							char[] x = new char[2];
        							x[0] = 'b';
        							x[1] = j;
        							String route = String.valueOf(x);
        							for(int p=0; p<64; p++){
        								if(labels[p].getId().equals(route)){
        									if(!labels[p].getText().equals("") && !labels[p].getText().equals("##")){
        										if(labels[p].getText().equals("bR") || labels[p].getText().equals("bQ")){
        											return false;
        										}
        										else{
        											j = '9';
        											break;
        										}
        									}
        								}
        							}
        						}
        						for(j = '2'; j<'8'; j++){
        							char[] x = new char[2];
        							x[0] = 'c';
        							x[1] = j;
        							String route = String.valueOf(x);
        							for(int p=0; p<64; p++){
        								if(labels[p].getId().equals(route)){
        									if(!labels[p].getText().equals("") && !labels[p].getText().equals("##")){
        										if(labels[p].getText().equals("bB") || labels[p].getText().equals("bQ")){
        											return false;
        										}
        										else{
        											j = '9';
        											break;
        										}
        									}
        								}
        							}
        							x[0]++;
        						}
        						for(j = '2'; j<'3'; j++){
        							char[] x = new char[2];
        							x[0] = 'a';
        							x[1] = j;
        							String route = String.valueOf(x);
        							for(int p=0; p<64; p++){
        								if(labels[p].getId().equals(route)){
        									if(!labels[p].getText().equals("") && !labels[p].getText().equals("##")){
        										if(labels[p].getText().equals("bB") || labels[p].getText().equals("bQ")){
        											return false;
        										}
        										else{
        											j = '9';
        											break;
        										}
        									}
        								}
        							}
        							x[0]--;
        						}
        						for(j = '2'; j<'9'; j++){
        							char[] x = new char[2];
        							x[0] = 'c';
        							x[1] = j;
        							String route = String.valueOf(x);
        							for(int p=0; p<64; p++){
        								if(labels[p].getId().equals(route)){
        									if(!labels[p].getText().equals("") && !labels[p].getText().equals("##")){
        										if(labels[p].getText().equals("bR") || labels[p].getText().equals("bQ")){
        											return false;
        										}
        										else{
        											j = '9';
        											break;
        										}
        									}
        								}
        							}
        						}
        						for(j = '2'; j<'7'; j++){
        							char[] x = new char[2];
        							x[0] = 'd';
        							x[1] = j;
        							String route = String.valueOf(x);
        							for(int p=0; p<64; p++){
        								if(labels[p].getId().equals(route)){
        									if(!labels[p].getText().equals("") && !labels[p].getText().equals("##")){
        										if(labels[p].getText().equals("bB") || labels[p].getText().equals("bQ")){
        											return false;
        										}
        										else{
        											j = '9';
        											break;
        										}
        									}
        								}
        							}
        							x[0]++;
        						}
        						for(j = '2'; j<'4'; j++){
        							char[] x = new char[2];
        							x[0] = 'b';
        							x[1] = j;
        							String route = String.valueOf(x);
        							for(int p=0; p<64; p++){
        								if(labels[p].getId().equals(route)){
        									if(!labels[p].getText().equals("") && !labels[p].getText().equals("##")){
        										if(labels[p].getText().equals("bB") || labels[p].getText().equals("bQ")){
        											return false;
        										}
        										else{
        											j = '9';
        											break;
        										}
        									}
        								}
        							}
        							x[0]--;
        						}
        						for(j = '2'; j<'9'; j++){
        							char[] x = new char[2];
        							x[0] = 'd';
        							x[1] = j;
        							String route = String.valueOf(x);
        							for(int p=0; p<64; p++){
        								if(labels[p].getId().equals(route)){
        									if(!labels[p].getText().equals("") && !labels[p].getText().equals("##")){
        										if(labels[p].getText().equals("bR") || labels[p].getText().equals("bQ")){
        											return false;
        										}
        										else{
        											j = '9';
        											break;
        										}
        									}
        								}
        							}
        						}
        						for(j = '2'; j<'6'; j++){
        							char[] x = new char[2];
        							x[0] = 'e';
        							x[1] = j;
        							String route = String.valueOf(x);
        							for(int p=0; p<64; p++){
        								if(labels[p].getId().equals(route)){
        									if(!labels[p].getText().equals("") && !labels[p].getText().equals("##")){
        										if(labels[p].getText().equals("bB") || labels[p].getText().equals("bQ")){
        											return false;
        										}
        										else{
        											j = '9';
        											break;
        										}
        									}
        								}
        							}
        							x[0]++;
        						}
        						for(j = '2'; j<'5'; j++){
        							char[] x = new char[2];
        							x[0] = 'c';
        							x[1] = j;
        							String route = String.valueOf(x);
        							for(int p=0; p<64; p++){
        								if(labels[p].getId().equals(route)){
        									if(!labels[p].getText().equals("") && !labels[p].getText().equals("##")){
        										if(labels[p].getText().equals("bB") || labels[p].getText().equals("bQ")){
        											return false;
        										}
        										else{
        											j = '9';
        											break;
        										}
        									}
        								}
        							}
        							x[0]--;
        						}
        						//change the position
        						e1.setText("");
        						c1.setText("wK");
        						a1.setText("");
        						d1.setText("wR");
        						return true;
    						}
    					}
    					else{//black side
    						if(!commands[0].equals("e8")){
    							return false;
    						}
    						if(commands[1].charAt(0)>commands[0].charAt(0)){
        						//move to right
        						if(!f8.getText().equals("##") || !g8.getText().equals("") || !h8.getText().equals("bR")){
        							System.out.println("false on line 899");
        							return false;
        						}
        						//check battle zone
        						if(e7.getText().equals("wN") || h7.getText().equals("wN") || d7.getText().equals("wN") || e6.getText().equals("wN")
        								|| f6.getText().equals("wN") || g6.getText().equals("wN") || h6.getText().equals("wN")){
        							System.out.println("false on line 905");
        							return false;
        						}
        						if(e7.getText().equals("wp") || f7.getText().equals("wp") || g7.getText().equals("wp") || h7.getText().equals("wp")){
        							System.out.println("false on line 909");
        							return false;
        						}

        						for(j = '7'; j>'0'; j--){
        							char[] x = new char[2];
        							x[0] = 'f';
        							x[1] = j;
        							String route = String.valueOf(x);
        							for(int p=0; p<64; p++){
        								if(labels[p].getId().equals(route)){
        									if(!labels[p].getText().equals("") && !labels[p].getText().equals("##")){
        										if(labels[p].getText().equals("wR") || labels[p].getText().equals("wQ")){
        											System.out.println("false on line 919");
        											return false;
        										}
        										else{
        											j = '0';
        											break;
        										}
        									}
        								}
        							}
        						}
        						for(j = '7'; j>'2'; j--){
        							char[] x = new char[2];
        							x[0] = 'e';
        							x[1] = j;
        							String route = String.valueOf(x);
        							for(int p=0; p<64; p++){
        								if(labels[p].getId().equals(route)){
        									if(!labels[p].getText().equals("") && !labels[p].getText().equals("##")){
        										if(labels[p].getText().equals("wB") || labels[p].getText().equals("wQ")){
        											System.out.println("false on line 938");
        											return false;
        										}
        										else{
        											j = '0';
        											break;
        										}
        									}
        								}
        							}
        							x[0]--;
        						}
        						for(j = '7'; j>'5'; j--){
        							char[] x = new char[2];
        							x[0] = 'g';
        							x[1] = j;
        							String route = String.valueOf(x);
        							for(int p=0; p<64; p++){
        								if(labels[p].getId().equals(route)){
        									if(!labels[p].getText().equals("") && !labels[p].getText().equals("##")){
        										if(labels[p].getText().equals("wB") || labels[p].getText().equals("wQ")){
        											System.out.println("false on line 958");
        											return false;
        										}
        										else{
        											j = '0';
        											break;
        										}
        									}
        								}
        							}
        							x[0]++;
        						}
        						for(j = '7'; j>'0'; j--){
        							char[] x = new char[2];
        							x[0] = 'g';
        							x[1] = j;
        							String route = String.valueOf(x);
        							for(int p=0; p<64; p++){
        								if(labels[p].getId().equals(route)){
        									if(!labels[p].getText().equals("") && !labels[p].getText().equals("##")){
        										if(labels[p].getText().equals("wR") || labels[p].getText().equals("wQ")){
        											System.out.println("false on line 978");
        											return false;
        										}
        										else{
        											j = '0';
        											break;
        										}
        									}
        								}
        							}
        						}
        						for(j = '7'; j>'1'; j--){
        							char[] x = new char[2];
        							x[0] = 'f';
        							x[1] = j;
        							String route = String.valueOf(x);
        							for(int p=0; p<64; p++){
        								if(labels[p].getId().equals(route)){
        									if(!labels[p].getText().equals("") && !labels[p].getText().equals("##")){
        										if(labels[p].getText().equals("wB") || labels[p].getText().equals("wQ")){
        											System.out.println("false on line 997");
        											return false;
        										}
        										else{
        											j = '0';
        											break;
        										}
        									}
        								}
        							}
        							x[0]--;
        						}
        						for(j = '7'; j>'6'; j--){
        							char[] x = new char[2];
        							x[0] = 'h';
        							x[1] = j;
        							String route = String.valueOf(x);
        							for(int p=0; p<64; p++){
        								if(labels[p].getId().equals(route)){
        									if(!labels[p].getText().equals("") && !labels[p].getText().equals("##")){
        										if(labels[p].getText().equals("wB") || labels[p].getText().equals("wQ")){
        											System.out.println("false on line 1017");
        											return false;
        										}
        										else{
        											j = '0';
        											break;
        										}
        									}
        								}
        							}
        							x[0]++;
        						}
        						//change the position
        						e8.setText("");
        						g8.setText("bK");
        						h8.setText("");
        						f8.setText("bR");
        						return true;
        					}
    						else{
    							//move to left
    							if(!d1.getText().equals("##") || !c1.getText().equals("") || !b1.getText().equals("##") || !a1.getText().equals("bR")){
        							return false;
        						}
    							//check battle zone
        						if(a6.getText().equals("wN") || b6.getText().equals("wN") || c6.getText().equals("wN") || d6.getText().equals("wN")
        								|| e6.getText().equals("wN") || a7.getText().equals("wN") || b7.getText().equals("wN") || d7.getText().equals("wN") || e7.getText().equals("wN") || f7.getText().equals("wN")){
        							return false;
        						}
        						if(a7.getText().equals("wp") || b7.getText().equals("wp") || c7.getText().equals("wp") || d7.getText().equals("wp") || e7.getText().equals("wp")){
        							return false;
        						}

        						for(j = '7'; j>'0'; j--){
        							char[] x = new char[2];
        							x[0] = 'b';
        							x[1] = j;
        							String route = String.valueOf(x);
        							for(int p=0; p<64; p++){
        								if(labels[p].getId().equals(route)){
        									if(!labels[p].getText().equals("") && !labels[p].getText().equals("##")){
        										if(labels[p].getText().equals("wR") || labels[p].getText().equals("wQ")){
        											return false;
        										}
        										else{
        											j = '0';
        											break;
        										}
        									}
        								}
        							}
        						}
        						for(j = '7'; j>'1'; j--){
        							char[] x = new char[2];
        							x[0] = 'c';
        							x[1] = j;
        							String route = String.valueOf(x);
        							for(int p=0; p<64; p++){
        								if(labels[p].getId().equals(route)){
        									if(!labels[p].getText().equals("") && !labels[p].getText().equals("##")){
        										if(labels[p].getText().equals("wB") || labels[p].getText().equals("wQ")){
        											return false;
        										}
        										else{
        											j = '0';
        											break;
        										}
        									}
        								}
        							}
        							x[0]++;
        						}
        						for(j = '7'; j>'6'; j--){
        							char[] x = new char[2];
        							x[0] = 'a';
        							x[1] = j;
        							String route = String.valueOf(x);
        							for(int p=0; p<64; p++){
        								if(labels[p].getId().equals(route)){
        									if(!labels[p].getText().equals("") && !labels[p].getText().equals("##")){
        										if(labels[p].getText().equals("wB") || labels[p].getText().equals("wQ")){
        											return false;
        										}
        										else{
        											j = '0';
        											break;
        										}
        									}
        								}
        							}
        							x[0]--;
        						}
        						for(j = '7'; j>'0'; j--){
        							char[] x = new char[2];
        							x[0] = 'c';
        							x[1] = j;
        							String route = String.valueOf(x);
        							for(int p=0; p<64; p++){
        								if(labels[p].getId().equals(route)){
        									if(!labels[p].getText().equals("") && !labels[p].getText().equals("##")){
        										if(labels[p].getText().equals("wR") || labels[p].getText().equals("wQ")){
        											return false;
        										}
        										else{
        											j = '0';
        											break;
        										}
        									}
        								}
        							}
        						}
        						for(j = '7'; j>'2'; j--){
        							char[] x = new char[2];
        							x[0] = 'd';
        							x[1] = j;
        							String route = String.valueOf(x);
        							for(int p=0; p<64; p++){
        								if(labels[p].getId().equals(route)){
        									if(!labels[p].getText().equals("") && !labels[p].getText().equals("##")){
        										if(labels[p].getText().equals("wB") || labels[p].getText().equals("wQ")){
        											return false;
        										}
        										else{
        											j = '0';
        											break;
        										}
        									}
        								}
        							}
        							x[0]++;
        						}
        						for(j = '7'; j>'5'; j--){
        							char[] x = new char[2];
        							x[0] = 'b';
        							x[1] = j;
        							String route = String.valueOf(x);
        							for(int p=0; p<64; p++){
        								if(labels[p].getId().equals(route)){
        									if(!labels[p].getText().equals("") && !labels[p].getText().equals("##")){
        										if(labels[p].getText().equals("wB") || labels[p].getText().equals("wQ")){
        											return false;
        										}
        										else{
        											j = '0';
        											break;
        										}
        									}
        								}
        							}
        							x[0]--;
        						}
        						for(j = '7'; j>'0'; j--){
        							char[] x = new char[2];
        							x[0] = 'd';
        							x[1] = j;
        							String route = String.valueOf(x);
        							for(int p=0; p<64; p++){
        								if(labels[p].getId().equals(route)){
        									if(!labels[p].getText().equals("") && !labels[p].getText().equals("##")){
        										if(labels[p].getText().equals("wR") || labels[p].getText().equals("wQ")){
        											return false;
        										}
        										else{
        											j = '0';
        											break;
        										}
        									}
        								}
        							}
        						}
        						for(j = '7'; j>'3'; j--){
        							char[] x = new char[2];
        							x[0] = 'e';
        							x[1] = j;
        							String route = String.valueOf(x);
        							for(int p=0; p<64; p++){
        								if(labels[p].getId().equals(route)){
        									if(!labels[p].getText().equals("") && !labels[p].getText().equals("##")){
        										if(labels[p].getText().equals("wB") || labels[p].getText().equals("wQ")){
        											return false;
        										}
        										else{
        											j = '0';
        											break;
        										}
        									}
        								}
        							}
        							x[0]++;
        						}
        						for(j = '7'; j>'4'; j--){
        							char[] x = new char[2];
        							x[0] = 'c';
        							x[1] = j;
        							String route = String.valueOf(x);
        							for(int p=0; p<64; p++){
        								if(labels[p].getId().equals(route)){
        									if(!labels[p].getText().equals("") && !labels[p].getText().equals("##")){
        										if(labels[p].getText().equals("wB") || labels[p].getText().equals("wQ")){
        											return false;
        										}
        										else{
        											j = '0';
        											break;
        										}
        									}
        								}
        							}
        							x[0]--;
        						}
        						//change the position
        						e8.setText("");
        						c8.setText("bK");
        						a8.setText("");
        						d8.setText("bR");
        						return true;
    						}
    					}

    				}
    			}
    			else if(t == 'p'){
    				if(whitesTurn){
    					if(commands[1].charAt(1)-commands[0].charAt(1)!=2){
    						if(commands[1].charAt(1)-commands[0].charAt(1)!=1){
    							return false;
    						}
    						else{
    							if(commands[1].charAt(0) == commands[0].charAt(0)){
    								if(!labels[i].getText().isEmpty()){
    									if(!labels[i].getText().equals("##")){
    										return false;
    									}
    								}
    							}
    							else if(Math.abs(commands[1].charAt(0)-commands[0].charAt(0)) == 1){
    								if(!labels[i].equals("") && labels[i].getText().charAt(0) != 'b'){
    									return false;
    								}
    							}
    							else if(Math.abs(commands[1].charAt(0)-commands[0].charAt(0)) > 1){
        							return false;
        						}
    						}
    					}
    					else{
        					if(commands[1].charAt(0) != commands[0].charAt(0)){
        						return false;
        					}
        					else if(!labels[i].getText().equals("") && !labels[i].getText().equals("##")){
        						return false;
        					}
        					char[] x = new char[2];
        					x[0] = commands[0].charAt(0);
        					x[1] = (char)(commands[0].charAt(1)+1);
        					String route = String.valueOf(x);
							for(int p=0; p<64; p++){
								if(labels[p].getId().equals(route)){
									if(!labels[p].getText().equals("") && !labels[p].getText().equals("##")){
										return false;
									}
								}
							}
        				}
    				}
    				else{

        				if(commands[1].charAt(1)-commands[0].charAt(1)!=-2){
        					if(commands[1].charAt(1)-commands[0].charAt(1)!=-1){
        						return false;
        					}
        					else{
        						if(commands[1].charAt(0) == commands[0].charAt(0)){
        							if(!labels[i].getText().isEmpty()){
        								if(!labels[i].getText().equals("##")){
        									return false;
        								}
        							}
        						}
        						else if(Math.abs(commands[1].charAt(0)-commands[0].charAt(0)) == 1){

        							if(labels[i].getText().charAt(0) != 'w'){
    									return false;
    								}
        						}
        						else if(Math.abs(commands[1].charAt(0)-commands[0].charAt(0)) > 1){
        							return false;
        						}
        					}
        				}
        				else{
        					if(commands[1].charAt(0) != commands[0].charAt(0)){
        						return false;
        					}
        					else if(!labels[i].getText().equals("") && !labels[i].getText().equals("##")){
        						return false;
        					}
        					char[] x = new char[2];
        					x[0] = commands[0].charAt(0);
        					x[1] = (char)(commands[1].charAt(1)+1);
        					String route = String.valueOf(x);
							for(int p=0; p<64; p++){
								if(labels[p].getId().equals(route)){
									if(!labels[p].getText().equals("") && !labels[p].getText().equals("##")){
										return false;
									}
								}
							}
        				}

    				}
    			}
    			dest = labels[i].getText();
    			if(!labels[i].getText().equals("") && !labels[i].getId().equals("##")){
    				if(whitesTurn && dest.charAt(0)=='w'){
    					return false;
    				}
    				else if(!whitesTurn && dest.charAt(0)=='b'){
    					return false;
    				}
    			}
    			if(checkBlackSpot(labels[start].getId())){
    				labels[start].setText("##");
    			}
    			else{
    				labels[start].setText("");
    			}
    			end = i;
    			labels[i].setText(source);
    			if(t == 'p'){
    				if(labels[i].getId().charAt(1)=='1' || labels[i].getId().charAt(1)=='8'){
    					if(whitesTurn){
    						labels[i].setText("wQ");
    					}
    					else{
    						labels[i].setText("bQ");
    					}
    				}
    			}
    			if(commands.length == 3){
    				if(commands[2].equals("draw?")){
    					Try_draw = true;
    				}
    			}
    			return true;
    		}
    	}
    	return false;
    }


    @FXML
    private void handleTFAction() {
    	Label[] labels = {a8,a7,a6,a5,a4,a3,a2,a1,
    			b8,b7,b6,b5,b4,b3,b2,b1,
    			c8,c7,c6,c5,c4,c3,c2,c1,
    			d8,d7,d6,d5,d4,d3,d2,d1,
    			e8,e7,e6,e5,e4,e3,e2,e1,
    			f8,f7,f6,f5,f4,f3,f2,f1,
    			g8,g7,g6,g5,g4,g3,g2,g1,
    			h8,h7,h6,h5,h4,h3,h2,h1};

    	temp = input.getText();
    	String[] commands = temp.split("\\s+");
    	if(commands.length > 1){
    		for(int i=0; i<64; i++){
    			if(labels[i].getId().equals(commands[0])){
        			source = labels[i].getText();
        		}
        		if(labels[i].getId().equals(commands[1])){
        			dest = labels[i].getText();
        		}
    		}
    	}
    	if(moveValid(temp, labels)){
    		if(!dest.isEmpty()){
    			if(dest.charAt(1) == 'K'){
    				input.setText("");
    				if(whitesTurn){
    					message.setText("White wins");
    				}
    				else{
    					message.setText("Black wins");
    				}
    			}
    		}

    		if(whitesTurn){
    			whitesTurn = false;
    			nameLabel.setText("Black's move");
    			input.setText("");
    			message.setText("");
    		}
    		else{
    			whitesTurn = true;
    			nameLabel.setText("White's move");
    			input.setText("");
    			message.setText("");
    		}
    	}
    	else{
    		input.setText("");
    		if(!draw){
    			message.setText("Illegal move, try again");
    		}
    	}
    }

}