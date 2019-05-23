package app.controller;

import app.StudentCalc;
import app.Helper.Loan;
import app.Helper.Payment;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class LoanCalcViewController implements Initializable   {

	private StudentCalc SC = null;
	
	
	@FXML
	private TextField LoanAmount;
	
	@FXML
	private TextField InterestRate;
	
	@FXML
	private Label lblTotalPayments;
	
	@FXML
	private Label lblTotalInterest;
	
	@FXML
	private TextField NbrOfYears;
	
	@FXML
	private DatePicker fstPmtDate;
	
	@FXML
	private TextField addPmt;
	
	@FXML
	private TableView tblV;
	
	@FXML
	private TableColumn pmtNbrCol;
	
	@FXML
	private TableColumn pmtDateCol;
	
	@FXML
	private TableColumn pmtCol;
	
	@FXML
	private TableColumn addPmtCol;
	
	@FXML
	private TableColumn interestCol;
	
	@FXML
	private TableColumn principleCol;
	
	@FXML
	private TableColumn balanceCol;
	
	//Decimal Format for Labels Total Payment and Total Interest
	DecimalFormat df = new DecimalFormat("#,###,##0.00");
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

	public void setMainApp(StudentCalc sc) {
		this.SC = sc;
	}
	
	/**
	 * btnCalcLoan - Fire this event when the button clicks
	 * 
	 * @version 1.0
	 * @param event
	 */
	@FXML
	private void btnCalcLoan(ActionEvent event) {

		double dLoanAmount = Double.parseDouble(LoanAmount.getText());
		double interestRate = Double.parseDouble(InterestRate.getText())/100.0;
		int term = Integer.parseInt(NbrOfYears.getText());
		double additionPmt = Double.parseDouble(addPmt.getText());
		LocalDate firstDate = fstPmtDate.getValue(); ;
	//System.out.println("Loan Amount: " + dLoanAmount);	
		lblTotalPayments.setText("123");
	//System.out.println("Number of years: " +term);
	//System.out.println("Interest Rate: "+ interestRate);
	//System.out.println("First Date: "+ firstDate);
		Loan myLoan = createLoan(dLoanAmount, interestRate, term, additionPmt, firstDate);
		setLabels(myLoan);
		setTable(myLoan, firstDate);
		
	
	}
	
	public Loan createLoan(double loanAmount, double interestRate, int term, double addPay,LocalDate ld) {
		return new Loan(loanAmount, interestRate, term, addPay,ld);
		
	}
	
	public void setLabels(Loan l) {
		lblTotalPayments.setText(df.format(Math.round(l.calcTotalPayment()*100.0)/100.0));
		lblTotalInterest.setText(df.format(Math.round(l.calcInterestPayment()*100.0)/100.0));
	}
	
	public void setTable(Loan l, LocalDate ld) {
		LinkedList<Payment> list = l.getLoanPayments();
		ObservableList<Payment> oList = FXCollections.observableArrayList(list);
		
		pmtNbrCol.setCellValueFactory(new PropertyValueFactory<Payment,String>("myPmtNbr"));
		
		pmtDateCol.setCellValueFactory(new PropertyValueFactory<Payment,String>("pmtDate"));
		
		pmtCol.setCellValueFactory(new PropertyValueFactory<Payment, String>("monthPay"));
		
		addPmtCol.setCellValueFactory(new PropertyValueFactory<Payment,String>("extraPayment"));
		
		interestCol.setCellValueFactory(new PropertyValueFactory<Payment,String>("interest"));
		
		principleCol.setCellValueFactory(new PropertyValueFactory<Payment,String>("principle"));
		
		balanceCol.setCellValueFactory(new PropertyValueFactory<Payment,String>("balance"));
		
		
		tblV.setItems(oList);
		
		
		
	}
	
}
