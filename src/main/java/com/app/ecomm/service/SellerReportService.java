package com.app.ecomm.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.ecomm.entity.Seller;
import com.app.ecomm.entity.SellerReport;
import com.app.ecomm.repo.SellerReportRepo;

@Service
public class SellerReportService {

	@Autowired
	private SellerReportRepo sellerReportRepo;
	
	
	public SellerReport getSellerReport(Seller seller) throws Exception{
		Optional<SellerReport> report=sellerReportRepo.findBySellerId(seller.getId());
		
		if(report.isEmpty()) {
			SellerReport r=new SellerReport();
			r.setSeller(seller);
			sellerReportRepo.save(r);
			return r;
		}
		
		return report.get();
				
	}
	
    public SellerReport updateSellerReport(SellerReport sellerReport) {
		return sellerReportRepo.save(sellerReport);
	}
	
}
