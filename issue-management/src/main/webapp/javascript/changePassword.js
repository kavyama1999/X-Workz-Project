let fieldsChecks = {
    "oldPassword": false,
    "newPassword": false,
    "confirmPassword": false
};

function validateAndEnableSubmit() {
    let allFieldsValid = Object.values(fieldsChecks).every(value => value);
    let submitButton = document.getElementById("submit");

    if (allFieldsValid) {
        submitButton.removeAttribute("disabled");
    } else {
        submitButton.setAttribute("disabled", "true");
    }
}

function oldPasswordValidation() {
    console.log("oldPasswordValidation");
    let oldPassword = document.getElementById("oldPassword");
    let error = document.getElementById("oldPasswordError");
    let oldPasswordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*()_+\[\]{}|;:,.<>?])[A-Za-z\d!@#$%^&*()_+\[\]{}|;:,.<>?]{10,}$/;

    if (oldPasswordRegex.test(oldPassword.value)) {
        error.innerHTML = "";
        fieldsChecks["oldPassword"] = true;
    } else {
        error.innerHTML = "Password must be at least 10 characters long, with at least one uppercase letter, one lowercase letter, one number, and one special character.";
        error.style.color = "red";
        fieldsChecks["oldPassword"] = false;
    }

    validateAndEnableSubmit();
}

function newPasswordValidation() {
    console.log("newPasswordValidation");
    let newPassword = document.getElementById("newPassword");
    let error = document.getElementById("newPasswordError");
    let newPasswordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*()_+\[\]{}|;:,.<>?])[A-Za-z\d!@#$%^&*()_+\[\]{}|;:,.<>?]{10,}$/;

    if (newPasswordRegex.test(newPassword.value)) {
        error.innerHTML = "";
        fieldsChecks["newPassword"] = true;
    } else {
        error.innerHTML = "Password must be at least 10 characters long, with at least one uppercase letter, one lowercase letter, one number, and one special character.";
        error.style.color = "red";
        fieldsChecks["newPassword"] = false;
    }

    confirmPasswordValidation(); // Call confirmPasswordValidation to ensure both passwords match
    validateAndEnableSubmit();
}

function confirmPasswordValidation() {
    console.log("confirmPasswordValidation");
    let newPassword = document.getElementById("newPassword");
    let confirmPassword = document.getElementById("confirmPassword");
    let error = document.getElementById("confirmPasswordError");

    if (confirmPassword.value === newPassword.value && fieldsChecks["newPassword"]) {
        error.innerHTML = "";
        fieldsChecks["confirmPassword"] = true;
    } else {
        error.innerHTML = "Passwords do not match.";
        error.style.color = "red";
        fieldsChecks["confirmPassword"] = false;
    }

    validateAndEnableSubmit();
}
