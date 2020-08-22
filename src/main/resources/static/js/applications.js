$(function (){
    let showMenu = $("#menu");
    let menu = $(".xxx");
    showMenu.on('mouseenter', hiddenMenu);
    showMenu.siblings("li").on("mouseleave",hiddenMenu);
    function hiddenMenu (){
        menu.toggleClass("show-menu");

    }







})