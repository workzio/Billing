package com.java.business.HeadOffice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.java.business.HeadOffice.entity.InTranscation;
import com.java.business.HeadOffice.repository.InTranscationRepository;

@Service
public class InTranscationService {

	@Autowired
	private InTranscationRepository inTranscationRepository;

	public Iterable<InTranscation> listAll() {
		return inTranscationRepository.findAll();
	}

	public void bankDetails(InTranscation inTranscation) {
		this.inTranscationRepository.save(inTranscation);
	}

	public InTranscation getBankById(long id) {
		return inTranscationRepository.findById(id).get();
	}

	public void update(InTranscation inTranscation, long id) {
		inTranscationRepository.save(inTranscation);
	}

	//////// delete/////////
	public void deleteInTranscationById(Long id) {
		inTranscationRepository.deleteById(id);

	}

	/////// edit///////
	public void save(InTranscation existingInTranscation) {
		inTranscationRepository.save(existingInTranscation);
	}

	public InTranscation findById(Long id) {
		return inTranscationRepository.findById(id).get();
	}
}
