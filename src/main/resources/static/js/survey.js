$(function () {
    let text = $('#textBtn');
    let select = $('#selectBtn');
    let radio = $('#radioBtn');
    let check = $('#checkBtn');

    let surveyTable = $("#surveyTable");
    let last;
    let counter = 0;

    function findLast() {
        return $('tr.tab-new:last');
    }


    $("#cancel").on("click", function () {
        window.location = "/app/admin/surveys/";
    });

    $("#cancelFS").on("click", function () {
        window.location = "/app/admin/freeSurveys/";
    });

    surveyTable.on("click", "#deleteQuestion", function () {
        $(this).closest("tr").remove();
    });

    surveyTable.on("click", "#deleteDivQuestion", function () {
        $(this).closest("tr").remove();
    });

    surveyTable.on("click", "#addBtn", function () {
        $(this).closest("tr.query-row").after(query);
    });

    text.on("click", function () {
        last = findLast();
        last.after(newText)

    });

    select.on("click", function () {
        last = findLast();
        last.after(newSelect);
    });

    radio.on("click", function () {
        last = findLast();
        last.after(newRadio);
    });

    check.on("click", function () {
        last = findLast();
        last.after(newCheck);
    });


    // let newText = "<tr class='tab-new'><td>Question Text</td><td><input type=\"text\" class=\"form\" placeholder=\"name\" th:value=\"${question.name}\" required><br><input type=\"hidden\" th:value=\"${question.questionType}\" th:attr=\"name='questionType[text]'\"></td>" +
    //     "<td><button id='deleteDivQuestion'>Del</button></td></tr>";

let newText = "<tr class='tab-new'> " +
    "<td>Question Text</td> " +
    "<td><input type=\"text\" class=\"form\" placeholder=\"name\" th:field=\"*{name}\" required><br> " +
    "<input type=\"hidden\" th:field=\"$*{questionType}\" th:attr=\"name='questionType[text]'\"></td> " +
    "<td><button id='deleteDivQuestion'>Del</button></td></tr>"

    let newSelect = "<tr class='tab-new'>" +
        "<div>" +
        "<tr>" +
        "<td>Question Select</td><td><input type='text' class='form' placeholder='name' th:value='${question.name}' required><br><input type=\"hidden\" th:value=\"${question.questionType}\" th:attr=\"name='questionType[select]'\"></td>" +
        "<td><button id='deleteDivQuestion'>Del</button></td>" +
        "</tr>" +
        "<tr class='query-row'>" +
        "<td><input id='query' type='text' class='form' placeholder='value' th:value='${question.query}' required></td>" +
        "<td><button id='addBtn'>+</button><button id='deleteQuestion'>Del</button></td>" +
        "</tr>" +
        "</div>" +
        "</tr>";

    let newRadio = "<tr class='tab-new'>" +
        "<div>" +
        "<tr>" +
        "<td>Question Radio</td><td><input type='text' class='form' placeholder='name' th:value='${question.name}' required><br><input type=\"hidden\" th:value=\"${question.questionType}\" th:attr=\"name='questionType[radio]'\"></td>" +
        "<td><button id='deleteDivQuestion'>Del</button></td>" +
        "</tr>" +
        "<tr class='query-row'>" +
        "<td><input id='query' type='text' class='form' placeholder='value' th:value='${question.query}' required></td>" +
        "<td><button id='addBtn'>+</button><button id='deleteQuestion'>Del</button></td>" +
        "</tr>" +
        "</div>" +
        "</tr>";

    let newCheck = "<tr class='tab-new'>" +
        "<div>" +
        "<tr>" +
        "<td>Question Check</td><td><input type='text' class='form' placeholder='name' th:value='${question.name}' required><br><input type=\"hidden\" th:value=\"${question.questionType}\" th:attr=\"name='questionType[check]'\"></td>" +
        "<td><button id='deleteDivQuestion'>Del</button></td>" +
        "</tr>" +
        "<tr class='query-row'>" +
        "<td><input id='query' type='text' class='form' placeholder='value' th:value='${question.query}' required></td>" +
        "<td><button id='addBtn'>+</button><button id='deleteQuestion'>Del</button></td>" +
        "</tr>" +
        "</div>" +
        "</tr>";

    let query = "<tr class='query-row'>" +
        "<td><input id='query' type='text' class='form' placeholder='value' th:value='${question.query}' required></td>" +
        "<td><button id='addBtn'>+</button><button id='deleteQuestion'>Del</button></td>" +
        "</tr>"

    // let newSelect = $(
    //     "<tr class='tab-new'>" +
    //     "<td>Question Select</td><td><input type='text' class='form' placeholder='name' th:value='${question.name}' required><br></td><td><button id='deleteAllSelect'>Del</button></td>" +
    //     "</tr>" +
    //     "<tr class='query-row'>" +
    //     "<td><input id='query' type='text' class='form' placeholder='value' th:value='${question.query}' required></td>" +
    //     "<td><button id='addBtn'>+</button><button id='deleteQuestion'>Del</button></td>" +
    //     "</tr>");

});