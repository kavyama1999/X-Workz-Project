package com.xworkz.issuemanagement.model.repository;

import com.xworkz.issuemanagement.dto.RaiseComplaintDTO;

public interface RaiseComplaintRepo {



    boolean saveRaiseComplaintData(RaiseComplaintDTO raiseComplaintDTO);


   //RaiseComplaintDTO findByUserId(int id);

}
