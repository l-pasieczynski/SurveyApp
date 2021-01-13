// $(function(){
//
//     let submit = $(".add-form");
//     submit.on('submit',sendForm);
//
//     function sendForm() {
//         console.log("done")
//         let survey = {
//             name: $(".title-survey").text,
//             questions:$("<h3>").text
//         };
//         let question = {
//             answer: [this.elements.fruits.value,
//                      this.elements.meals.value,
//                      this.elements.drinks.value,
//                      this.elements.ingredients.values]
//         }
//         $.ajax({
//             url: "http://localhost:8282/books",
//             type: "POST",
//             data: JSON.stringify(survey,question),
//             contentType: "application/json; charset=utf-8",
//             dataType: "json",
//         }).done(function () {
//             alert("survey has been send");
//         }).fail(function (xhr, status, err) {
//             console.log(xhr, status, err);
//         });
//
//         return false;
//     }
//
//
//
// });
//
