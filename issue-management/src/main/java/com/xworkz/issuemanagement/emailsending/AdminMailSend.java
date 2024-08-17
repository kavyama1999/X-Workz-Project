package com.xworkz.issuemanagement.emailsending;


import com.xworkz.issuemanagement.dto.RegisterDepartmentAdminDTO;
import com.xworkz.issuemanagement.dto.SignUpDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AdminMailSend {


    @Autowired
    private JavaMailSender javaMailSender;


    public AdminMailSend()
    {
        log.info("No parameters in AdminMailSend..");
    }


    public void sendPassword(RegisterDepartmentAdminDTO registerDepartmentAdminDTO) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(registerDepartmentAdminDTO.getEmail());
        message.setSubject("SignIn password");
        message.setText("Dear " + registerDepartmentAdminDTO.getAdminName() + " " + ", You have been successfully Registered ,\n" +
                "Please Login in through this password: " + registerDepartmentAdminDTO.getPassword() + "\n" +
                "Thanks and Regards,\n" + " " +
                "X-Workz Project Team");
        javaMailSender.send(message);
    }

//******************************************************

    public void forgotPassword(RegisterDepartmentAdminDTO registerDepartmentAdminDTO) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(registerDepartmentAdminDTO.getEmail());
        message.setSubject("SignIn password");
        message.setText("Dear " + registerDepartmentAdminDTO.getAdminName() + " " + ",  A new password has been sent to your email. ,\n\n" +
                "Please Login in through new password: " + registerDepartmentAdminDTO.getPassword() + "\n" +
                "Thanks and Regards,\n" + " " +
                "X-Workz Project Team");
        javaMailSender.send(message);
    }



    //********************************************************

    public void sendChangePassword(RegisterDepartmentAdminDTO registerDepartmentAdminDTO,String newPassword) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(registerDepartmentAdminDTO.getEmail());
        message.setSubject("Change password");
        message.setText("Dear " + registerDepartmentAdminDTO.getAdminName() +  ", A Change Password has been sent to your email. ,\n\n" +
                "Please Sign in through Change password: " + newPassword+ "\n\n" +
                "Thanks and Regards,\n" + " " +
                "X-workz Project Team");
        javaMailSender.send(message);
    }



}
