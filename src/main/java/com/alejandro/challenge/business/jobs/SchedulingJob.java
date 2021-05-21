package com.alejandro.challenge.business.jobs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alejandro.challenge.common.constants.NumberConstants;
import com.alejandro.challenge.repository.AccountRepository;

@Component
public class SchedulingJob {
	
	
	private static final Logger log = LoggerFactory.getLogger(SchedulingJob.class);

	@Autowired
	private AccountRepository repository;
	
	 @Scheduled(cron="0 0 0 * * ?")
	public void resetAttemptsAtMidngh() {
		this.repository.findAll().stream()
			.filter(account -> account.getAttempts() > NumberConstants.ZERO)
			.forEach(account -> {
				account.setAttempts(NumberConstants.ZERO);
				this.repository.save(account);
				log.info("Account attempts updated", account);
			});
	}

}
