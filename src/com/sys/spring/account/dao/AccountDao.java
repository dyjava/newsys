package com.sys.spring.account.dao;

import java.util.List;

import com.sys.spring.account.domain.Account;
import com.sys.spring.account.domain.AccountTable;
import com.sys.spring.user.domain.User;


/** 
 * by dyong 2010-6-16
 */
public interface AccountDao {

	public int insertAccount(Account account) ;	//insert
	
	public int updateAccount(Account account) ;	//update
	
	public void deleteAccount(Account account) ;	//delete
	
	public Account findAccountById(int id) ;	//find one object by id
	
	public List<Account> findAccountList(String begin,String end,Account account) ; 	//find object list by parames
	
	public List<AccountTable> findAccountTableList(String begin,String end,Account account,User user,String type) ;	// find tables by count
}
