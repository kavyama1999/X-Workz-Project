let fieldsChecks = {
    "firstName": false,
    "lastName": false,
    "email": false,
    "contactNumber": false,
    "alternateContactNumber": false,
    "address": false,
    "agree": false
};

function validateAndEnableSubmit() {
    let flag = false;

    for (let value of Object.values(fieldsChecks)) {
        if (!value) {
            flag = true;
            break;
        }
    }

    if (!flag) {
        document.getElementById("submit").removeAttribute("disabled");
    } else {
        document.getElementById("submit").setAttribute("disabled", "");
    }
}

// FirstName
function firstNameValidation() {
    let element = document.getElementById("firstName");
    let error = document.getElementById("firstNameError");
    let nameRegex = /^[A-Za-z ]+$/;

    if (element.value.length > 3 && element.value.length < 30 && nameRegex.test(element.value)) {
        error.innerHTML = "";
        fieldsChecks["firstName"] = true;
    } else {
        error.innerHTML = "Invalid name. Name should be alphabetic characters only and length should be greater than 3 and less than 30.";
        error.style.color = "red";
        fieldsChecks["firstName"] = false;
    }
    validateAndEnableSubmit();
}

// LastName
function lastNameValidation() {
    let element = document.getElementById("lastName");
    let error = document.getElementById("lastNameError");
    let nameRegex = /^[A-Za-z ]+$/;

    if (element.value.length > 1 && element.value.length <= 20 && nameRegex.test(element.value)) {
        error.innerHTML = "";
        fieldsChecks["lastName"] = true;
    } else {
        error.innerHTML = "Invalid name. Name should be alphabetic characters only and length should be greater than 1 and less than 20.";
        error.style.color = "red";
        fieldsChecks["lastName"] = false;
    }
    validateAndEnableSubmit();
}

// Email
function emailValidation() {
             let element = document.getElementById("email");
            let error = document.getElementById("emailError");

     <!----Regular expression pattern for validating email address--!>
    let emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

     <!---Check if the email is valid--!>
    if (emailRegex.test(element.value)) {
       //Email is valid
     error.innerHTML = "";
     fieldsChecks["email"] = true;
     } else {
      //email is Invalid
     error.innerHTML = "Invalid email address.";
     error.style.color = "red";
     fieldsChecks["email"] = false;
      }
     validateAndEnableSubmit();
    }






// Contact Number
function contactNumberValidation() {
    let element = document.getElementById("contactNumber");
    let error = document.getElementById("contactNumberError");
    let mobileRegex = /^\d{10}$/;

    if (mobileRegex.test(element.value)) {
        error.innerHTML = "";
        fieldsChecks["contactNumber"] = true;
    } else {
        error.innerHTML = "Invalid contact number. It should be exactly 10 digits.";
        error.style.color = "red";
        fieldsChecks["contactNumber"] = false;
    }
    validateAndEnableSubmit();
}



// Alternate Contact Number
function alternateContactNumberValidation() {
    let element = document.getElementById("alternateContactNumber");
    let error = document.getElementById("altContactNbrError");
    let mobileRegex = /^\d{10}$/;

    if (mobileRegex.test(element.value)) {
        error.innerHTML = "";
        fieldsChecks["alternateContactNumber"] = true;
    } else {
        error.innerHTML = "Invalid contact number. It should be exactly 10 digits.";
        error.style.color = "red";
        fieldsChecks["alternateContactNumber"] = false;
    }
    validateAndEnableSubmit();
}

// Address
function addressValidation() {
    let element = document.getElementById("address");
    let error = document.getElementById("addressError");

    if (element.value.length > 3 && element.value.length < 300) {
        error.innerHTML = "";
        fieldsChecks["address"] = true;
    } else {
        error.innerHTML = "Address should be greater than 3 and less than 300 characters.";
        error.style.color = "red";
        fieldsChecks["address"] = false;
    }
    validateAndEnableSubmit();
}

// Agree
function agreeValidation() {
    let element = document.getElementById("agree");
    let error = document.getElementById("agreeError");

    if (element.checked) {
        error.innerHTML = "";
        fieldsChecks["agree"] = true;
    } else {
        error.innerHTML = "Please agree to the terms.";
        error.style.color = "red";
        fieldsChecks["agree"] = false;
    }
    validateAndEnableSubmit();
}
