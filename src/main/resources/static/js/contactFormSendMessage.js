$(function () {
    let sendBtn = $("#contact-form-submit");
    sendBtn.on("submit", sendContactForm);


    function sendContactForm() {

        let message = {
            name: this.elements.name.value,
            email: this.elements.email.value,
            message: this.elements.message.value,
        };

        $.ajax({
            url: "http://localhost:8080/contact",
            type: "POST",
            data: JSON.stringify(message),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
        }).done(function () {
        }).fail(function (xhr, status, err) {
            console.log(xhr, status, err);
        });

        return false;
    }
});
