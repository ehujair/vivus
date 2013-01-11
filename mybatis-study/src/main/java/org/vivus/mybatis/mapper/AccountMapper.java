package org.vivus.mybatis.mapper;

import java.util.List;

import org.vivus.mybatis.domain.Account;

public interface AccountMapper {
	Account selectAccount(String userName);

	List<Account> findAll();

	void insert(Account account);

	void update(Account account);

	void delete(Account account);
}
