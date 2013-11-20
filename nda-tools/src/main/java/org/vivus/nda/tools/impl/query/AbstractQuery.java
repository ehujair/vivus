package org.vivus.nda.tools.impl.query;

import java.util.ArrayList;
import java.util.List;

import org.vivus.nda.tools.context.ICommandExecutor;
import org.vivus.nda.tools.query.IQuery;
import org.vivus.nda.tools.query.IQueryProperty;
import org.vivus.nda.tools.query.Page;

public abstract class AbstractQuery<T> implements IQuery<T> {
	private List<OrderBy> orderBys = new ArrayList<OrderBy>();
	private ICommandExecutor commandExecutor;
	private Page page = new Page(0, Integer.MAX_VALUE);

	public AbstractQuery(ICommandExecutor commandExecutor) {
		this.commandExecutor = commandExecutor;
	}

	protected ICommandExecutor getCommandExecutor() {
		return commandExecutor;
	}

	@Override
	public List<T> list() {
		return list(new Page(0, Integer.MAX_VALUE));
	}

	@Override
	public List<T> list(Page page) {
		this.page = page;
		return listPage(page);
	}

	protected abstract List<T> listPage(Page page);

	@Override
	public T unique() {
		List<T> list = list(new Page(0, 10));
		if (list == null || list.isEmpty())
			throw new RuntimeException("No result found.");
		if (list.size() > 1)
			throw new RuntimeException("Too many result found.");
		return list.get(0);
	}

	public int getFirstResult() {
		return page.getFirstResult();
	}

	public int getFirstRow() {
		return page.getFirstResult() + 1;
	}

	public int getLastRow() {
		if (page.getMaxResults() == Integer.MAX_VALUE) {
			return Integer.MAX_VALUE;
		}
		return page.getFirstResult() + page.getMaxResults() + 1;
	}

	public int getMaxResults() {
		return page.getMaxResults();
	}

	public String getOrderByString() {
		if (orderBys.isEmpty())
			return "";
		StringBuffer sb = new StringBuffer(" order by ");
		for (int i = 0; i < orderBys.size() - 1; i++) {
			sb.append(orderBys.get(i).toString() + ", ");
		}
		sb.append(orderBys.get(orderBys.size() - 1).toString());
		return sb.toString();
	}

	protected IQuery<T> orderBy(OrderBy orderBy) {
		orderBys.add(orderBy);
		return this;
	}

	public class OrderBy {
		IQueryProperty property;
		Order order = Order.ASC;

		public OrderBy(IQueryProperty property) {
			this.property = property;
		}

		public OrderBy(IQueryProperty property, Order order) {
			this.property = property;
			this.order = order;
		}

		public IQueryProperty getQueryProperty() {
			return property;
		}

		public Order getOrder() {
			return order;
		}

		public String toString() {
			return property.getName() + " " + order.name();
		}
	}
}
