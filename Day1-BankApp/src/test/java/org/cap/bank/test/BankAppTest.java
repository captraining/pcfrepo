package org.cap.bank.test;

import static org.junit.Assert.*;

import org.cap.dao.AccountDao;
import org.cap.dto.Account;
import org.cap.dto.Address;
import org.cap.dto.Customer;
import org.cap.exception.InsufficientBalanceException;
import org.cap.exception.InvalidInitialAmountException;
import org.cap.service.AcccountService;
import org.cap.service.AccountServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class BankAppTest {
	
	private AcccountService accService;
	
	@Mock
	private AccountDao accountDao;
	
	
	@Before
	public void beforeMethod(){
		//accService=new AccountServiceImpl();
		
		MockitoAnnotations.initMocks(this);
		accService=new AccountServiceImpl(accountDao);
		
	}
	

	@Test
	public void test() {
		
		assertEquals(9,accService.addNumbers(5, 4));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void test_addaccount_raise_Exception_ifcustomer_null() throws InvalidInitialAmountException{
		Customer customer=null;
		accService.addAccount(customer, 1000);
	}

	@Test
	public void test_findAccountById(){
		Account account=new Account();
		account.setAccountNo(1001);
		account.setAmount(2000);
		Customer customer=new Customer();
		customer.setCustName("Jack");
		customer.setCustAddress(new Address());
		account.setCustomer(customer);
		
		//Declaration
		Mockito.when(accountDao.findAccountById(1001)).thenReturn(account);
		
		
		//Actual Business Logic
		Account account2=accService.findAccountById(1001);
		
		//Vertification
		Mockito.verify(accountDao).findAccountById(1001);
	
		
		assertEquals(2000, account2.getAmount(),0.0);
	}
	
	
	@Test
	public void test_Withdrawal_Method() throws InsufficientBalanceException{
		Account account=new Account();
		account.setAccountNo(1001);
		account.setAmount(2000);
		Customer customer=new Customer();
		customer.setCustName("Jack");
		customer.setCustAddress(new Address());
		account.setCustomer(customer);
		
		//Declaration
		Mockito.when(accountDao.findAccountById(1001)).thenReturn(account);
		
		
		//Actual Business Logic
		Account account2=accService.withdraw(1001, 500);
		
		//Verification
		Mockito.verify(accountDao).findAccountById(1001);
		
		
		assertEquals(1500, account2.getAmount(),0.0);
		
	}
	
	@Test
	public void test_Deposit_Method() throws InsufficientBalanceException{
		Account account=new Account();
		account.setAccountNo(1001);
		account.setAmount(2000);
		Customer customer=new Customer();
		customer.setCustName("Jack");
		customer.setCustAddress(new Address());
		account.setCustomer(customer);
		
		//Declaration
		Mockito.when(accountDao.findAccountById(1001)).thenReturn(account);
		
		
		//Actual Business Logic
		Account account2=accService.deposit(1001, 500);
		
		//Verification
		Mockito.verify(accountDao).findAccountById(1001);
		
		
		assertEquals(2500, account2.getAmount(),0.0);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
