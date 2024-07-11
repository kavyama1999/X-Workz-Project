//////package com.xworkz.imageupload.dto;
//////
//////import org.hibernate.validator.constraints.NotEmpty;
//////import org.springframework.web.multipart.MultipartFile;
//////
//////import javax.persistence.*;
//////import javax.validation.constraints.*;
//////
//////
//////@Entity
//////@Table(name = "contact_data")
//////public class ImageDTO {
//////
//////
//////
//////
//////
//////
//////
//////        @Id
//////        @GeneratedValue(strategy = GenerationType.IDENTITY)
//////        private Integer id;  //to avoid dataType error we can change datatype as Non-primitive
//////
////////for string we can use @NotEmpty
//////
////////    create table contact_data(id int  primary key auto_increment,contact_name varchar(50),email varchar(50),mobile bigint,comments varchar(500));
//////
//////        @NotEmpty(message = "Name cannot be empty")
//////        @Size(min = 2, max = 30, message = "Name should contain only alphabetic letters")
//////        @Pattern(regexp = "^[a-zA-Z ]+$", message = "Name should contain only alphabetic letters")
//////        @Column(name = "contact_name")
//////        private String name;
//////
//////
//////
//////
//////
//////
//////        @NotEmpty(message = "Please provide comments")
//////        @Size(min = 2, max = 300, message = "Comments should contain between 2 and 300 characters")
//////        @Pattern(regexp = "^[a-zA-Z ]+$", message = "Comments should contain only alphabetic letters and spaces")
//////        private String comments;
//////
//////
//////  private MultipartFile  imageFile;
//////
//////
//////
//////        public Integer getId() {
//////            return id;
//////        }
//////
//////        public void setId(Integer id) {
//////            this.id = id;
//////        }
//////
//////        public String getName() {
//////            return name;
//////        }
//////
//////        public void setName(String name) {
//////            this.name = name;
//////        }
//////
//////
//////
//////        public String getComments() {
//////            return comments;
//////        }
//////
//////        public void setComments(String comments) {
//////            this.comments = comments;
//////        }
//////
//////
//////    public MultipartFile getImageFile() {
//////        return imageFile;
//////    }
//////
//////    public void setImageFile(MultipartFile imageFile) {
//////        this.imageFile = imageFile;
//////    }
//////
//////
//////    @Override
//////    public String toString() {
//////        return "ImageDTO{" +
//////                "id=" + id +
//////                ", name='" + name + '\'' +
//////                ", comments='" + comments + '\'' +
//////                ", imageFile=" + imageFile +
//////                '}';
//////    }
//////}
////
////
////package com.xworkz.xworkzProject.dto;
////
////import lombok.*;
////
////import javax.persistence.*;
////import java.time.LocalDateTime;
////@Getter@Setter@AllArgsConstructor@NoArgsConstructor@ToString
////@Entity
////@Table(name = "image_upload")
////public class ImageDownloadDTO {
////
////
////    @Id
////    @GeneratedValue(strategy = GenerationType.IDENTITY)
////    private int id;
////
////    @Column(name = "image_name")
////    private String imageName;
////
////    @Column(name = "image_size")
////    private long imageSize;
////
////    @Column(name = "image_type")
////    private String imageType;
////
////    @Column(name = "user_id")
////    private int userId;
////
////    @Column(name = "created_by")
////    private String createdBy;
////
////    @Column(name = "created_at")
////    private LocalDateTime createdAt;
////
////    @Column(name = "modified_by")
////    private String modifiedBy;
////
////    @Column(name = "modified_at")
////    private LocalDateTime modifiedAt;
////
////    @Column(name = "status")
////    private String status;
////}
//
//
////package com.xworkz.imageupload.controller;
//
//import org.apache.commons.io.IOUtils;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.servlet.ServletOutputStream;
//import javax.servlet.http.HttpServletResponse;
//import java.io.*;
//import java.nio.file.AccessDeniedException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//
////@Component
////@RequestMapping("/")
////public class ImageController {
////
////    public ImageController()
////    {
////        System.out.println("Created ImageController");
////    }
////
////    @PostMapping("upload-file")
////    public String uploadImage(@RequestParam MultipartFile file) throws IOException {
////        System.out.println("Image upload");
////        System.out.println("file getName :" +file.getName());
////        System.out.println("file getSize :" +file.getSize());
////        System.out.println("file getContentType : " +file.getContentType());
////        System.out.println("file getResource :" +file.getResource());
////        System.out.println("file getBytes: " +file.getBytes());
////        System.out.println("file getOriginalFilename " +file.getOriginalFilename());
////
////
////
////        byte[] bytes=file.getBytes();
////        Path path= Paths.get("C:\\Users\\kavya\\image",file.getOriginalFilename());
////        Files.write(path,bytes);
////
////        System.out.println("fileName:"+file.getOriginalFilename()+"ContentType:"+file.getContentType());
//        //return "ContactForm";
//
////    }
////
////    @GetMapping("download")
////    public void download(HttpServletResponse response,@RequestParam String fileName)
////    {
////        System.out.println("Running download method...");
////        response.setContentType("image/jpeg");
////        File file=new File("C:\\Users\\kavya\\image\\"+fileName);
////        try {
////            InputStream buffer=new BufferedInputStream(new FileInputStream(file));
////            ServletOutputStream outputStream =response.getOutputStream();
////            IOUtils.copy(buffer,outputStream);
////            response.flushBuffer();
////        } catch (AccessDeniedException e) {
////            System.err.println("Access denied: " + e.getMessage());
////        }catch (IOException e) {
////            e.printStackTrace();
////
////            //throw new RuntimeException(e);
////        }
////
////    }
////
////}