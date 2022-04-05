package DataObjects;

import java.util.ArrayList;
import java.util.List;

import UtilityConnections.FilterConnection;


public class FilterFunctions {

	private ArrayList<String> filterResultList;
	private List<String> resultList;
	
	
	public void setTopFilter() throws Exception {
		filterResultList = new FilterConnection().sortByTop();
	}
	
	public void setRatingFilter() throws Exception {
		filterResultList = new FilterConnection().sortByRating();
	}
	
	public void setSearch(String search) throws Exception {
		filterResultList = new FilterConnection().search(search);
	}
	
	public void setDateFilter() throws Exception {
		filterResultList = new FilterConnection().sortByDate();
	}
	
	public void setSubmissions() throws Exception {
		filterResultList = new FilterConnection().sortSubmissions();
	}
	
	public int getPageAmount() {
		return (int)Math.ceil(filterResultList.size()/10.0);
	}
	
	public List<String> getResultsPage(int index) {
		int start = (index-1)*10;
		int end = start + 10;
		
		if(filterResultList.size() < index*10) {
			end = start + (filterResultList.size()%10);
		}
		
		resultList = filterResultList.subList(start, end);
		return resultList; 
	}
}
