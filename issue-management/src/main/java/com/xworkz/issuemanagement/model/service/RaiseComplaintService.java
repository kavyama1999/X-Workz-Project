package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.RaiseComplaintDTO;
import com.xworkz.issuemanagement.dto.SignUpDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public interface RaiseComplaintService {

//save
    boolean saveRaiseComplaintData(RaiseComplaintDTO raiseComplaintDTO);


    // RaiseComplaintDTO findByUserId(int id);

    //find user id and stored in raiseComplaint table
    public Optional<RaiseComplaintDTO> findByUserId(int id);

    public Optional<RaiseComplaintDTO> findBySignedInUser(HttpServletRequest request);


    //used for view raised complaint

    List<RaiseComplaintDTO> getComplaintsByUserId(int userId);

    //edit
    public RaiseComplaintDTO getComplaintById(int complaintId) ;

    //update

  List <RaiseComplaintDTO> updateRaiseComplaintUserDetails(RaiseComplaintDTO raiseComplaintDTO);




    }