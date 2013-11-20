package org.vivus.nda.tools.impl.query;

import java.util.List;

import org.vivus.nda.tools.context.CommandContext;
import org.vivus.nda.tools.context.ICommand;
import org.vivus.nda.tools.context.ICommandExecutor;
import org.vivus.nda.tools.entity.FileItem;
import org.vivus.nda.tools.file.IFileQuery;
import org.vivus.nda.tools.query.Page;

public class FileQuery extends AbstractQuery<FileItem> implements IFileQuery {
	String id;
	String name;
	String nameLike;
	String type;
	long min, max;

	public FileQuery(ICommandExecutor commandExecutor) {
		super(commandExecutor);
	}

	@Override
	public long count() {
		return getCommandExecutor().execute(new ICommand<Long>() {
			@Override
			public Long execute(CommandContext commandContext) {
				return commandContext.getFileManager().countByCriteria(FileQuery.this);
			}
		});
	}

	@Override
	protected List<FileItem> listPage(final Page page) {
		return getCommandExecutor().execute(new ICommand<List<FileItem>>() {
			@Override
			public List<FileItem> execute(CommandContext commandContext) {
				return commandContext.getFileManager().findByCriteria(FileQuery.this, page);
			}
		});
	}

	@Override
	public IFileQuery id(String id) {
		this.id = id;
		return this;
	}

	@Override
	public IFileQuery name(String name) {
		this.name = name;
		return this;
	}

	@Override
	public IFileQuery nameLike(String nameLike) {
		this.nameLike = nameLike;
		return this;
	}

	@Override
	public IFileQuery type(String type) {
		this.type = type;
		return this;
	}

	@Override
	public IFileQuery sizeLessThan(long size) {
		this.max = size;
		return this;
	}

	@Override
	public IFileQuery sizeGreaterThan(long size) {
		this.min = size;
		return this;
	}

	@Override
	public IFileQuery sizeBetween(long min, long max) {
		this.min = min;
		this.max = max;
		return this;
	}

	@Override
	public IFileQuery orderById() {
		orderBy(new OrderBy(FileItemProperty.ID));
		return this;
	}

	@Override
	public IFileQuery orderById(Order order) {
		orderBy(new OrderBy(FileItemProperty.ID, order));
		return this;
	}

	@Override
	public IFileQuery orderByName() {
		orderBy(new OrderBy(FileItemProperty.NAME));
		return this;
	}

	@Override
	public IFileQuery orderByName(Order order) {
		orderBy(new OrderBy(FileItemProperty.NAME, order));
		return this;
	}

	@Override
	public IFileQuery orderByPath() {
		orderBy(new OrderBy(FileItemProperty.PATH));
		return this;
	}

	@Override
	public IFileQuery orderByPath(Order order) {
		orderBy(new OrderBy(FileItemProperty.PATH, order));
		return this;
	}

	@Override
	public IFileQuery orderByType() {
		orderBy(new OrderBy(FileItemProperty.TYPE));
		return this;
	}

	@Override
	public IFileQuery orderByType(Order order) {
		orderBy(new OrderBy(FileItemProperty.TYPE, order));
		return this;
	}

	@Override
	public IFileQuery orderBySize() {
		orderBy(new OrderBy(FileItemProperty.SIZE));
		return this;
	}

	@Override
	public IFileQuery orderBySize(Order order) {
		orderBy(new OrderBy(FileItemProperty.SIZE, order));
		return this;
	}

}
