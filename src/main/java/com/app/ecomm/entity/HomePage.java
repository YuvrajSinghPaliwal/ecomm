package com.app.ecomm.entity;

//import java.util.List;
//import java.util.Objects;
//
//
//
//public class HomePage {
//
//	private List<HomeCategory> grid;
//	
//	private List<HomeCategory> shopByCategories;
//	
//	private List<HomeCategory> electricCategories;
//	
//	private List<HomeCategory> dealCategories;
//	
//	private List<Deal> deals;
//
//	public List<HomeCategory> getGrid() {
//		return grid;
//	}
//
//	public void setGrid(List<HomeCategory> grid) {
//		this.grid = grid;
//	}
//
//	public List<HomeCategory> getShopByCategories() {
//		return shopByCategories;
//	}
//
//	public void setShopByCategories(List<HomeCategory> shopByCategories) {
//		this.shopByCategories = shopByCategories;
//	}
//
//	public List<HomeCategory> getElectricCategories() {
//		return electricCategories;
//	}
//
//	public void setElectricCategories(List<HomeCategory> electricCategories) {
//		this.electricCategories = electricCategories;
//	}
//
//	public List<HomeCategory> getDealCategories() {
//		return dealCategories;
//	}
//
//	public void setDealCategories(List<HomeCategory> dealCategories) {
//		this.dealCategories = dealCategories;
//	}
//
//	public List<Deal> getDeals() {
//		return deals;
//	}
//
//	public void setDeals(List<Deal> deals) {
//		this.deals = deals;
//	}
//
//	public HomePage(List<HomeCategory> grid, List<HomeCategory> shopByCategories, List<HomeCategory> electricCategories,
//			List<HomeCategory> dealCategories, List<Deal> deals) {
//		super();
//		this.grid = grid;
//		this.shopByCategories = shopByCategories;
//		this.electricCategories = electricCategories;
//		this.dealCategories = dealCategories;
//		this.deals = deals;
//	}
//
//	public HomePage() {
//		super();
//	}
//
//	@Override
//	public String toString() {
//		return "HomePage [grid=" + grid + ", shopByCategories=" + shopByCategories + ", electricCategories="
//				+ electricCategories + ", dealCategories=" + dealCategories + ", deals=" + deals + "]";
//	}
//
//	@Override
//	public int hashCode() {
//		return Objects.hash(dealCategories, deals, electricCategories, grid, shopByCategories);
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		HomePage other = (HomePage) obj;
//		return Objects.equals(dealCategories, other.dealCategories) && Objects.equals(deals, other.deals)
//				&& Objects.equals(electricCategories, other.electricCategories) && Objects.equals(grid, other.grid)
//				&& Objects.equals(shopByCategories, other.shopByCategories);
//	}
//	
//	
//	
//}
