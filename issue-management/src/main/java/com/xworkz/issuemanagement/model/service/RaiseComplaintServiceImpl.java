package com.xworkz.issuemanagement.model.service;


import com.xworkz.issuemanagement.dto.RaiseComplaintDTO;
import com.xworkz.issuemanagement.model.repository.RaiseComplaintRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RaiseComplaintServiceImpl implements RaiseComplaintService {


    @Autowired
    private RaiseComplaintRepo raiseComplaintRepo;

    @Override
    public boolean saveRaiseComplaintData(RaiseComplaintDTO raiseComplaintDTO) {

        log.info("saveRaiseComplaintData method running RaiseComplaintServiceImpl");

        boolean data = raiseComplaintRepo.saveRaiseComplaintData(raiseComplaintDTO);
        if (data) {
            log.info("saveRaiseComplaintData  successful in RaiseComplaintServiceImpl..");
            return data;
        } else {
            log.info("saveRaiseComplaintData noy successful in RaiseComplaintServiceImpl..");
        }


        return true;
    }
}
//    @Override
//    public RaiseComplaintDTO findByUserId(int id) {
//        log.info("findByUserId method running in RaiseComplaintServiceImpl");
//        return raiseComplaintRepo.findByUserId(id);    }
//}
