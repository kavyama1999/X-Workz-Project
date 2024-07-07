let fieldsChecks = {
    "oldPassword": false,
    "newPassword": false,
    "confirmPassword": false
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

function oldPasswordValidation() {
    let oldPassword = document.getElementById("oldPassword");
    let error = document.getElementById("oldPasswordError");

    let oldPasswordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{10}$/;

    if (oldPasswordRegex.test(oldPassword.value)) {
        error.innerHTML = "";
        fieldsChecks["oldPassword"] = true;
    } else {
        error.innerHTML = "Password must be exactly 10 characters long, with at least one uppercase letter, one lowercase letter, one number, and one special character.";
        error.style.color = "red";
        fieldsChecks["oldPassword"] = false;
    }

    validateAndEnableSubmit();
}

function newPasswordValidation() {
    let newPassword = document.getElementById("newPassword");
    let error = document.getElementById("newPasswordError");

    let newPasswordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{10}$/;

    if (newPasswordRegex.test(newPassword.value)) {
        error.innerHTML = "";
        fieldsChecks["newPassword"] = true;
    } else {
        error.innerHTML = "Password must be exactly 10 characters long, with at least one uppercase letter, one lowercase letter, one number, and one special character.";
        error.style.color = "red";
        fieldsChecks["newPassword"] = false;
    }

//    confirmPasswordValidation();
    validateAndEnableSubmit();
}

function confirmPasswordValidation() {
    let newPassword = document.getElementById("newPassword");
    let confirmPassword = document.getElementById("confirmPassword");
    let error = document.getElementById("confirmPasswordError");

    if (confirmPassword.value !== newPassword.value) {
        error.innerHTML = "Passwords do not match.";
        error.style.color = "red";
        fieldsChecks["confirmPassword"] = false;
    } else {
        error.innerHTML = "";
        fieldsChecks["confirmPassword"] = true;
    }

    validateAndEnableSubmit();
}
