////
////@RestController
////@RequestMapping("/")
////public class SignUpRestController {
////    @Autowired
////    private CrisisService crisisService;
////
////    @GetMapping("/validateEmail/email/{email}")
////    public String validateEmail(@PathVariable String email) {
////        CrisisDto crisis = crisisService.findByEmail(email);
////        return crisis != null ? "Email is already Exist" : "";
////    }
////
////    @GetMapping("/validateContactNumber/{contactNumber}")
////    public String validateContactNumber(@PathVariable Long contactNumber) {
////        {
////            CrisisDto existingContactNumber = crisisService.findByContactNumber(contactNumber);
////            return existingContactNumber != null ? "Contact number already exists" : "";
////        }}
//
//
//.......................
//
//
//signup jsp  <script>
//function validEmail() {
//    console.log("Validate email");
//    let email = document.getElementById("email").value.trim();
//    console.log(email);
//    let error = document.getElementById("emailError");
//       const request = new XMLHttpRequest();
//    console.log(request);
//    request.open("GET", "http://localhost:8080/crisisManagement/validateEmail/email/" + encodeURIComponent(email));
//    console.log(request);
//    request.send();
//    request.onload = function() {
//        var ref = this.responseText;
//        console.log(ref);
//        error.innerHTML = ref;
//    }
//}
//
//function validContactNumber() {
//    console.log("Validate contact number");
//    let contactNumber = document.getElementById("contactNumber").value.trim();
//    console.log(contactNumber);
//    let error = document.getElementById("contactNumberError");
//      const request = new XMLHttpRequest();
//    request.open("GET", "http://localhost:8080/crisisManagement/validateContactNumber/" + encodeURIComponent(contactNumber));
//    request.send();
//    request.onload = function() {
//        var ref = this.responseText;
//        console.log(ref);
//        error.innerHTML = ref;
//    }
//}
//
//
//   </script>




//function emailValidation() {
//    let element = document.getElementById("email");
//    let error = document.getElementById("emailError");
//    let emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
//
//    if (emailRegex.test(element.value)) {
//        $.ajax({
//            type: "GET",
//            url: "/check-email",
//            data: { email: element.value },
//            success: function(response) {
//                if (response.exists) {
//                    error.innerHTML = "Email is already in use.";
//                    error.style.color = "red";
//                    fieldsChecks["email"] = false;
//                } else {
//                    error.innerHTML = "";
//                    fieldsChecks["email"] = true;
//                }
//                validateAndEnableSubmit();
//            },
//            error: function() {
//                error.innerHTML = "Error checking email.";
//                error.style.color = "red";
//                fieldsChecks["email"] = false;
//                validateAndEnableSubmit();
//            }
//        });
//    } else {
//        error.innerHTML = "Invalid email address.";
//        error.style.color = "red";
//        fieldsChecks["email"] = false;
//        validateAndEnableSubmit();
//    }
//}
