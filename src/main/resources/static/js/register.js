$(function () {
    let signUpBtn = $("#signUp");
    signUpBtn.on("submit", sendRegisterForm);
    let menuButton = $(".menu")

    function sendRegisterForm() {

        let user = {
            name: this.elements.name.value,
            email: this.elements.email.value,
            password: this.elements.password.value,
            gender: this.elements.gender.value,
        };

        $.ajax({
            url: "http://localhost:8080/register",
            type: "POST",
            data: JSON.stringify(user),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
        }).done(function () {
        }).fail(function (xhr, status, err) {
            console.log(xhr, status, err);
        });

        return false;

    }
    menuButton.on("mouseenter" , changeButton);

    function changeButton(){
        menuButton.style.color="red";
    }

});
