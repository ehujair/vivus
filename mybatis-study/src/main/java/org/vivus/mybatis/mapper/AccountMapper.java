package org.vivus.mybatis.mapper;

import java.util.List;

import org.vivus.mybatis.domain.Account;

public interface AccountMapper {
	/**
	 * Not support,no configuration in xml
	 * @param account
	 */
	int insert(Account account);

	int insertAccount(Account account);

	int updateAccount(Account account);

	int deleteAccount(Account account);

	Account selectAccount(String id);

	Account selectAccountByName(String userName);

	List<Account> selectAccountDynamic(Account account);
}
