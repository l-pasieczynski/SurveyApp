$(function (){
    let showMenu = $("#menu");
    let menu = $(".xxx");
    showMenu.on('mouseenter', hiddenMenu);
    showMenu.siblings("li").on("mouseleave",hiddenMenu);
    function hiddenMenu (){
        menu.toggleClass("show-menu");

    }

    let submit = $("#submitpass");
    let newPassword = $("#pass1");
    let newPasswordRepeat = $("#pass2");
    let form = $("#change-password");

    form.bind("keypress",function (e){
        if(e.keyCode==13){
            return false;
        }
    })

    newPasswordRepeat.keyup(function (){

        if(newPassword.val()!==newPasswordRepeat.val()){
            newPasswordRepeat.css("border-color","red")
            submit.hide();

        }else{
            newPasswordRepeat.css("border-color","green");
            submit.show();
        }
    });








})