package org.vivus.nda.tools.query;

public class Page {

	protected int firstResult;
	protected int maxResults;

	public Page(int firstResult, int maxResults) {
		if (firstResult < 0)
			throw new IllegalArgumentException("firstResult must larger than 0: " + firstResult);
		if (maxResults < 0)
			throw new IllegalArgumentException("maxResults must larger than 0: " + maxResults);
		this.firstResult = firstResult;
		this.maxResults = maxResults;
	}

	public int getFirstResult() {
		return firstResult;
	}

	public int getMaxResults() {
		return maxResults;
	}
}