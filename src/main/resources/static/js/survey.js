$(function () {
    let text = $('#textBtn');
    let select = $('#selectBtn');
    let radio = $('#radioBtn');
    let check = $('#checkBtn');

    let surveyTable = $("#surveyTable");

    let counter = 0;

    function findLast() {
        return $('tr.tab-new:last');
    }


    $("#cancel").on("click", function () {
        window.location = "/app/admin/surveys/";
    });

    surveyTable.on("click", "#deleteQuestion", function () {
        $(this).closest("tr").remove();
    });

    surveyTable.on("click", "#deleteDivQuestion", function () {
        $(this).closest("tr.tab-new").remove();
    });

    surveyTable.on("click", "#addBtn", function () {
        $(this).closest("tr.query-row").after(query);
    });

   text.on("click", function () {
       let last = findLast();
       last.after(newText)

    });


    select.on("click", function () {
        let last = findLast();
        last.after(newSelect);
    });

    radio.on("click", function () {

    });

    check.on("click", function () {

    });


    let newText = "<tr class='tab-new'><td>Question Text</td><td><input type=\"text\" class=\"form\" placeholder=\"name\" th:value=\"${question.name}\" required><br></td><td><button id='deleteDivQuestion'>Del</button></td></tr>";
    // let newSelect = $(
    //     "<tr class='tab-new'>" +
    //     "<td>Question Select</td><td><input type='text' class='form' placeholder='name' th:value='${question.name}' required><br></td><td><button id='deleteAllSelect'>Del</button></td>" +
    //     "</tr>" +
    //     "<tr class='query-row'>" +
    //     "<td><input id='query' type='text' class='form' placeholder='value' th:value='${question.query}' required></td>" +
    //     "<td><button id='addBtn'>+</button><button id='deleteQuestion'>Del</button></td>" +
    //     "</tr>");

    let query = "<tr class='query-row'>" +
        "<td><input id='query' type='text' class='form' placeholder='value' th:value='${question.query}' required></td>" +
        "<td><button id='addBtn'>+</button><button id='deleteQuestion'>Del</button></td>" +
        "</tr>"

    let newSelect = "<tr class='tab-new'>" +
        "<div>" +
        "<tr>" +
        "<td>Question Select</td><td><input type='text' class='form' placeholder='name' th:value='${question.name}' required><br></td><td><button id='deleteDivQuestion'>Del</button></td>" +
        "</tr>" +
        "<tr class='query-row'>" +
        "<td><input id='query' type='text' class='form' placeholder='value' th:value='${question.query}' required></td>" +
        "<td><button id='addBtn'>+</button><button id='deleteQuestion'>Del</button></td>" +
        "</tr>" +
        "</div>" +
        "</tr>";


});