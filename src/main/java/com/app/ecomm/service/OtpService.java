package com.app.ecomm.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.ecomm.entity.Users;
import com.app.ecomm.entity.VerificationCode;
import com.app.ecomm.repo.UserRepo;
import com.app.ecomm.repo.VerificationCodeRepo;

@Service
public class OtpService {

	@Autowired
	private EmailService emailService;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private VerificationCodeRepo verificationCodeRepo;
	
	public static String generateOtp() {
		int otpLength=6;
		
		Random rand=new Random();
		
		StringBuilder otp=new StringBuilder(otpLength);
		
		for(int i=0;i<otpLength;i++) {
			otp.append(rand.nextInt(10));
		}
		
		return otp.toString();
	}
	
	public void sentLoginOtp(String email) throws Exception{
		
		
        VerificationCode verificationCode=verificationCodeRepo.findByEmail(email);
		
		if(verificationCode!=null) {
			verificationCodeRepo.delete(verificationCode);
		}
		
		String otp=OtpService.generateOtp();
		
		VerificationCode code=new VerificationCode();
		code.setOtp(otp);
		code.setEmail(email);
		verificationCodeRepo.save(code);
		
		String subject="ecomm otp";
		String text="your otp is : "+otp+" .";
		
		emailService.sendOtpVerificationMail(email, otp, subject, text);
		
		
	}
	
}
