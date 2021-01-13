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
        counter++;
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

    $("#saveBtn").submit(function (event) {
        event.preventDefault();
        ajaxPost();
    });

    function Question(question, type, query) {
        this.question = question;
        this.type = type;
        this.query = query;
    }

    let questionArray = [];

    function createQuestion() {
        let question = new Question(
            document.getElementById("question").value,
            document.getElementById("type").value,
            // $('.question').get().map(function (el) {
            //     return el.value
            // }),
            // $('#type').get().map(function (el) {
            //     return el.value
            // }),

            // document.getElementById("query").valueOf(),
        );
        questionArray.push(question);
    }

    function questionList() {

        for (let i = 0; i < counter; i++) {
            questionArray = $('.question').get().map(function(el) { return el.value });
            // createQuestion()
            // questionArray.push($('#question').eq(i).val());
            // $('#question').each(function () {
            // questionArray = (document.getElementById('question').value);
            // questionArray.push(createQuestion());
            // createQuestion();
            // });
        }
        console.log(questionArray);
        return questionArray;
    }

    $(document).on("click", function () {
        console.log(questionList())
    });

    $('#ok').on("click", function () {

        console.log(questionList())
    });


    function ajaxPost() {

        let questList = questionList();
        let jsonStr = JSON.stringify(questList)


        let survey = {
            name: $("#name").val(),
            questions: jsonStr
        };

        $.ajax({
            type: "POST",
            contentType: "application/json; charset=utf-8",
            url: "http://localhost:8080/app/admin/surveys/add",
            data: JSON.stringify(survey),
            dataType: 'json',
        }).done(function (dataReturnedByServer) {
            if (successHandlerFn !== undefined) {
                successHandlerFn(dataReturnedByServer);
            }
        }).fail(function (xhr, status, err) {
            console.log(xhr, status, err);
        });

    }


    let newText = "<tr class='tab-new'> " +
        "<td>Question Text</td> " +
        "<td><input type='text' class='form question' placeholder='name' name='question' value='' id='question' required><br> " +
        // "<td><input type='text' class='form' placeholder='name' name='question' value='' id='question" + (counter++) + "' required><br> " +
        "<input type='hidden' name='questionType' value='text' id='type'></td> " +
        "<td><button id='ok'>OK</button><button id='deleteDivQuestion'>Del</button></td></tr>"

    // let newText = "<tr class='tab-new'> " +
    //     "<td>Question Text</td> " +
    //     "<td><input type=\"text\" class=\"form\" placeholder=\"name\" th:field=\"*{name}\" required><br> " +
    //     "<input type=\"hidden\" th:field=\"$*{questionType}\" th:attr=\"name='questionType[text]'\"></td> " +
    //     "<td><button id='deleteDivQuestion'>Del</button></td></tr>"

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