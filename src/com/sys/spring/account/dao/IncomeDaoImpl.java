package com.sys.spring.account.dao;

import java.util.ArrayList;
import java.util.List;

import com.sys.spring.account.domain.Account;
import com.sys.spring.account.domain.Income;
import com.sys.spring.dao.AbstractDBDao;
import com.sys.util.Logs;

/** 
 * by dyong 2010-6-16
 */
public class IncomeDaoImpl extends AbstractDBDao implements IncomeDao {

	public int insertIncome(Income in) {
		long start = System.currentTimeMillis() ;
		StringBuffer buf = new StringBuffer() ;
		buf.append(this.getClass().getName()).append("|").append("insertIncome") ;
		
		String sql = "INSERT INTO income(uid,title,money,kindId,kindTitle,datetime,userId,userName) VALUES (?,?,?,?,?,?,?,?)" ;
//		log.info(sql+"	"+kind.getTitle()+"	"+kind.getNote()+"	"+kind.getParentId()) ;
		Object[] params = {in.getUid(),in.getTitle(),in.getMoney(),in.getKindid(),in.getKindtitle(),in.getDatetime(),in.getUserId(),in.getUsername()} ;
		
		int result = this.update(sql, params) ;

		buf.append("|").append(sql)
		.append("|").append(params.toString())
		.append("|").append(result)
		.append("|").append(System.currentTimeMillis() - start) ;
		Logs.info(buf) ;
		return result ;
	}

	public int updateIncome(Income income) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void deleteIncome(String id) {
		long start = System.currentTimeMillis() ;
		StringBuffer buf = new StringBuffer() ;
		buf.append(this.getClass().getName()).append("|").append("deleteIncome") ;
		
		String sql = "delete from income where id=?" ;
//		log.info(sql+"	"+kind.getTitle()+"	"+kind.getNote()+"	"+kind.getParentId()) ;
		Object[] params = {id} ;
		
		int result = this.update(sql, params) ;

		buf.append("|").append(sql)
		.append("|").append(params.toString())
		.append("|").append(result)
		.append("|").append(System.currentTimeMillis() - start) ;
		Logs.info(buf) ;
	}

	public Income findIncomeById(String id) {
		long start = System.currentTimeMillis() ;
		StringBuffer buf = new StringBuffer() ;
		buf.append(this.getClass().getName()).append("|").append("insertIncome") ;
		
		String sql = "select uid,title,money,kindId,kindTitle,datetime,userId,userName from income where id=?" ;
//		log.info(sql+"	"+kind.getTitle()+"	"+kind.getNote()+"	"+kind.getParentId()) ;
		Object[] params = {id} ;
		
		Income result = this.selectObject(sql, params, Income.class) ;

		buf.append("|").append(sql)
		.append("|").append(params.toString())
		.append("|").append(result.getId())
		.append("|").append(System.currentTimeMillis() - start) ;
		Logs.info(buf) ;
		return result ;
	}

	public List<Income> findIncomeList(String begin, String end, Income income) {
		long start = System.currentTimeMillis() ;
		StringBuffer buf = new StringBuffer() ;
		buf.append(this.getClass().getName()).append("|").append("findIncomeList") ;
		
		StringBuffer sql = new StringBuffer("select * from income where datetime>=? and datetime<=? ") ;
		
		ArrayList<Object> params = new ArrayList<Object>() ;
		params.add(begin) ;
		params.add(end) ;
		
		if(income.getKid()>0){
			sql.append(" and kid=?") ;
			params.add(income.getKid()) ;
		}
		if(income.getKindid()!=null && income.getKindid().length()>0){
			sql.append(" and kindid=?") ;
			params.add(income.getKindid()) ;
		}
		if(income.getUserId()!=null){
			sql.append(" and userid=?") ;
			params.add(income.getUserId()) ;
		}
		if(income.getTitle()!=null){
			sql.append(" and title like ?") ;
			params.add("%"+income.getTitle()+"%") ;
		}
		if(income.getMoney()>0){
			sql.append(" and money>?") ;
			params.add(income.getMoney()) ;
		}
		sql.append(" order by datetime desc") ;
		
		buf.append("|").append(sql) ;
		buf.append("|").append(this.list2String(params)) ;
		List<Income> list = this.selectList(sql.toString(), params.toArray(), Income.class) ;
		
		buf.append("|").append(list.size()) ;
		buf.append("|").append(System.currentTimeMillis() - start) ;
		Logs.info(buf) ;
		return list ;
	
	}
	

}
